import java.util.HashMap;
import java.util.LinkedList;


public class ProgDynamique {

	/**
	 * Il s'agit du tableau des valeurs des pièces di
	 */
	private static Integer[] pieceValeurs;
	
	/**
	 * C'est le tableau à 2 dimension où l'on remplira chaque case par les NBP(i,j)
	 */
	private static HashMap<Integer,HashMap<Integer,Integer>> tableau;
	
	/**
	 * C'est la nombre de pièces du système C
	 */
	private static int taille;
	
	/**
	 * Il s'agit du vecteur solution 
	 */
	private static LinkedList<Integer> X= new LinkedList<Integer>();
	
	public static HashMap<Integer,HashMap<Integer,Integer>> getTableau() {
		return tableau;
	}
	
	public static LinkedList<Integer> getX() {
		return X;
	}
	
	/**
	 * Initialise le vecteur candidat X
	 * @param X la LinkedList à initialiser
	 */
	public static void initLinkedList (LinkedList<Integer> X){
		for(int i=0;i<taille;i++){
			X.add(0);
		}
	}

	/**
	 * Initialise le tableau 
	 */
	public static void initTableau (){
		tableau = new HashMap<Integer,HashMap<Integer,Integer>>();
		HashMap<Integer,Integer> val = new HashMap<Integer,Integer>();
		for(int i=0;i<taille;i++){
			tableau.put(i, (HashMap<Integer, Integer>) val.clone());
		}
	}
	
	/**
	 * Algorithme principal de la méthode par Programmation Dynamique
	 * @param x le nombre de pièce utilisé dans le système Ci (c'est donc i)
	 * @param y la somme à rendre 
	 * @return le nombre de pièce à rendre
	 */
	public static int NBP(int x,int y){
		for(int j=0;j<=y;j++){
			for(int i=0;i<x;i++){
				if(j==0){
					tableau.get(i).put(j, 0);
				}
				else 
				{
					if(i==0)
					{
						if(j%pieceValeurs[i]==0){
							tableau.get(i).put(j, j/pieceValeurs[i]);
						}
						else{
							tableau.get(i).put(j, Integer.MAX_VALUE);
						}
					}
					else 
					{
						int n1,n2;
						int k = pieceValeurs[i];
						n1=tableau.get(i-1).get(j);
						
						if(j-k>=0){
							//Cas pour distinguer l'INFINI (car INFINI +1 n'est pas egal à INFINI)
							if(tableau.get(i).get(j-k)==Integer.MAX_VALUE){
								n2=tableau.get(i).get(j-k);
							}	
							else{
								n2=1+tableau.get(i).get(j-k);
							}		
						}
						else{
							n2=Integer.MAX_VALUE;
						}
						
						tableau.get(i).put(j,Math.min(n1, n2));
					}
				} 
			}
		}
		remplir(x-1,y); // fonction qui va parcourir le tableau
		return tableau.get(x-1).get(y);
	}
	
	/**
	 * Fonction qui parcours le tableau et va compter le nombre d'occurences pour 
	 * chaque pièce pour la somme à rendre
	 * @param x le nombre de pièce utilisé dans le système Ci (c'est donc i)
	 * @param y la somme à rendre 
	 */
	public static void remplir(int x, int y) {
		int Ncourrant=0;
		for(int j=y;j>=0;j--){
			for(int i =x;i>=0;i--){
				if(x==0){
					if(tableau.get(0).get(j)!=Integer.MAX_VALUE){
						X.set(0,tableau.get(0).get(j));
						j=0;
					}
					else{
						X.set(0,0);
						j=0;
					}
						
				}
				else if(i==x && j==y){
					Ncourrant =tableau.get(i).get(j);
				}
				else if(i==0 && tableau.get(i).get(j)!=Integer.MAX_VALUE){
					X.set(i,tableau.get(i).get(j));
					i=0;
					j=0;
				}
				else if(tableau.get(i).get(j)!=Ncourrant){
					while((tableau.get(i).get(j)==Integer.MAX_VALUE || tableau.get(i).get(j)>Ncourrant)&& j-pieceValeurs[i+1]>=0){
						j-=pieceValeurs[i+1];
					}
					X.set(i+1, Ncourrant -tableau.get(i).get(j) );
					Ncourrant=tableau.get(i).get(j);
				}
				if(i==0 && j!=0 && tableau.get(i).get(j)!=Integer.MAX_VALUE){
					i++;
				}
			}
		}
	}

	public static void setPieceValeurs(Integer[] pieceValeurs2) {
		ProgDynamique.pieceValeurs = pieceValeurs2;
	}

	public static void setTaille(int taille) {
		ProgDynamique.taille = taille;
	}
		

}
