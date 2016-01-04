import java.util.LinkedList;

public class EssaiSuccessif {

	/**
	 * Il s'agit de la somme à rendre.
	 */
	private static int N;
	
	/**
	 * Il s'agit du tableau des valeurs des pièces di
	 */
	private static Integer[] pieceValeurs;
	
	/**
	 * C'est la nombre de pièces du système C
	 */
	private static int taille;
	
	/**
	 * Il s'agit du vecteur candidat (solution actuelle)
	 */
	private static LinkedList<Integer> X= new LinkedList<Integer>();
	
	/**
	 * C'est le vecteur solution qui resoud le problème de manière optimale
	 */
	private static LinkedList<Integer> XOptimale= new LinkedList<Integer>();

	/**
	 * Il s'agit de la fonction encorepossible qui contient la condition d'arret et les conditions d'élagages
	 * @param i le numéro de la pièce actuelle
	 * @return <code>True</code> si c'est possible ou <code>False</code> s'il faut élaguer
	 */
	public static boolean encorepossible(int i) {
		return (i<taille-1) &&(somme(X)<somme(XOptimale)||somme(XOptimale)==0)&&(sommecomplete(X)+pieceValeurs[taille-1]<=N);
	}
	

	public static LinkedList<Integer> getX() {
		return X;
	}
	
	public static LinkedList<Integer> getXOptimale() {
		return XOptimale;
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
	 * Teste si la solution actuelle X est meilleure que la solution optimale Xoptimale
	 * @param i numéro de la pièce actuelle
	 * @return <code>True</code> si c'est le meilleur ou <code>False</code> sinon 
	 */
	public static boolean meilleur(int i) {
		return (somme(X)<somme(XOptimale)||somme(XOptimale)==0);
	}
	
	/**
	 * Teste si le candidat X verifie les critère de satisfiabilité par rapport à la somme à rendre N
	 * @param i numéro de la pièce actuelle
	 * @param x le nb d'occurence de la pièce actuelle
	 * @return <code>True</code> si il est satisfaisant ou <code>False</code> sinon 
	 */
	public static boolean satisfaisant(int i,int x) {
		return (sommecomplete(X)+x*pieceValeurs[i])<=N;
	}
	
	public static void setN(int n) {
		N = n;
	}


	public static void setPieceValeurs(Integer[] pieceValeurs2) {
		EssaiSuccessif.pieceValeurs = pieceValeurs2;
	}


	public static void setTaille(int taille) {
		EssaiSuccessif.taille = taille;
	}

	/**
	 * Espace des Solution Si de la pièce ci
	 * @param i numéro de la pièce actuelle
	 * @return le nb d'occurences maximales possibles pour la pièce ci
	 */
	public static int Si(int i) {
		return N/pieceValeurs[i]; 
	}

	/**
	 * Algorithme Principale de la méthode par Essai Successif
	 * @param i numero de la pièce actuelle
	 */
	public static void solOpt(int i){
		for(int x=0;x<=Si(i);x++){
			if (satisfaisant(i,x)){
				
				X.set(i, x);
				if(solutiontrouvé(i)){
					if(meilleur(i)){
						XOptimale=(LinkedList<Integer>) X.clone();
					}
				}
				else{					
					if(encorepossible(i)){
						solOpt(i+1);
					}
					
				}
				X.set(i,0);
				
			}
		}
	}

	/**
	 * Teste si le vecteur candidat X est solution du problème
	 * @param i numéro de la pièce actuelle
	 * @return <code>True</code> s'il est solution ou <code>False</code> sinon 
	 */
	public static boolean solutiontrouvé( int i) {
		return sommecomplete(X)==N;
	}

	/**
	 * Calcul la somme des entiers d'une LinkedList (calcul le nb de pièce utilisé pour X)
	 * @param X vecteur solution
	 * @return nb pièce utilisé
	 */
	public static int somme(LinkedList<Integer> X){
		int somme =0;
		for(int x:X){
			somme += x;
		}
		return somme;
	}
    /**
     * Calcul la somme actuelle  
     * @param X le vecteur candidat 
     * @return la somme actuelle ateinte par le candidat X
     */
	public static int sommecomplete(LinkedList<Integer> X){
		int somme =0;
		for(int j =0;j<taille;j++){
				somme += X.get(j)*pieceValeurs[j];
		}
		return somme;
	}

}
