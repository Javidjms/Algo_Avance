import java.util.ArrayList;

public class AlgoGlouton {
	
	/**
	 * Il s'agit de la somme � rendre.
	 */
	private static int N;
	
	/**
	 * Il s'agit du tableau des valeurs des pi�ces di
	 */
	private static Integer[] pieceValeurs;
	
	/**
	 * C'est la nombre de pi�ces du syst�me C
	 */
	private static int taille;
	
	/**
	 * Il s'agit du vecteur candidat (solution actuelle)
	 */
	private static ArrayList<Integer> X= new ArrayList<Integer>();
	
	public static ArrayList<Integer> getX() {
		return X;
	}
	
	public static void setN(int n) {
		N = n;
	}


	public static void setPieceValeurs(Integer[] pieceValeurs2) {
		AlgoGlouton.pieceValeurs = pieceValeurs2;
	}
	
	public static void setTaille(int taille) {
		AlgoGlouton.taille = taille;
	}

	/**
	 * Algorithme Principale de la m�thode Glouton
	 */
	public static void solGreedy(){
		int reste = N;
		for(int i=0;i<taille;i++){
			X.add(reste/pieceValeurs[i]);
			reste = reste%pieceValeurs[i];
		}
		// Cas Sp�cial Facultatif : Lorsque le reste est non nul, il n'y a pas de solutions donc le vecteur solution est mis � zero
		if(reste !=0){
			for(int i=0;i<taille;i++){
				X.set(i,0);
			}
		}
	}
	
}