import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Options;

public class RLMMO {

	/**
	 * Il s'agit de la somme à rendre.
	 */
	private static int N;
	
	/**
	 * Il s'agit du tableau des valeurs des pièces di
	 */
	private static Integer[] pieceValeurs;
	
	/**
	 * Affiche le vecteur Solution (nb d'occurences de chaques pieces ci)
	 * @param a le vecteur solution
	 */ 
	public static void afficher(List<Integer> a){
		for(int i=0;i<a.size();i++){
			if(a.get(i)!=0){
				System.out.println("Il y a " + a.get(i) + " pieces de "
						+ pieceValeurs[i]);
			}
		}
	}
	
	/**
	 * Configure les options E/S
	 * @param args
	 */
	public static void initOptions(String[] args){
		Options options = new Options();
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		
		options.addOption("d", true,"Ajouter Valeur d'une pièce");
		options.addOption("N", true, "Monnaie à Rendre");
		try {
			
			cmd = parser.parse(options, args);
			if(cmd.hasOption("d")&&cmd.hasOption("N"))
			{
				 N = Integer.parseInt(cmd.getOptionValue("N"));
				String[] pieceValeursString = cmd.getOptionValues("d");
				
				pieceValeurs=new Integer[pieceValeursString.length];
				for(int i=0;i<pieceValeurs.length;i++)
				{
					pieceValeurs[i]=Integer.parseInt(pieceValeursString[i]); 
				}
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialise les vecteurs solutions et le tableau des valeurs des pieces (Di) pour chaque 
	 * algorithme
	 */
	public static void initValue(){
		EssaiSuccessif.setN(N);
		EssaiSuccessif.setPieceValeurs(pieceValeurs);
		EssaiSuccessif.setTaille(pieceValeurs.length);
		EssaiSuccessif.initLinkedList(EssaiSuccessif.getX());
		EssaiSuccessif.initLinkedList(EssaiSuccessif.getXOptimale());
		AlgoGlouton.setN(N);
		AlgoGlouton.setPieceValeurs(pieceValeurs);
		AlgoGlouton.setTaille(pieceValeurs.length);
		ProgDynamique.setPieceValeurs(pieceValeurs);
		ProgDynamique.setTaille(pieceValeurs.length);
		ProgDynamique.initTableau();
		ProgDynamique.initLinkedList(ProgDynamique.getX());
	}
	
	/**
	 * Main principale utilisant les algorithmes
	 * @param args
	 */
	public static void main(String[] args){
		initOptions(args);
		if(pieceValeurs!=null){			
			Arrays.sort(pieceValeurs,Collections.reverseOrder());
			if(N<pieceValeurs[pieceValeurs.length-1]){
				System.out.println("Pas de Solution Possible");
			}
			else{
				initValue();
				EssaiSuccessif.solOpt(0);
				System.out.println("Test avec Essais Successifs");
				afficher(EssaiSuccessif.getXOptimale());
				AlgoGlouton.solGreedy();
				System.out.println("Test avec l'Algorithme Glouton");
				afficher(AlgoGlouton.getX());
				System.out.println("Test avec la Programmation Dynamique");
				ProgDynamique.NBP(pieceValeurs.length, N);
				afficher(ProgDynamique.getX());
			}
		}
	}
}
	
		

