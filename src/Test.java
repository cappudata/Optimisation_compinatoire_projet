import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		//String userDirectory = System.getProperty("user.dir");
		//System.out.println(userDirectory);
		
		//Base
		Base base1 = new Base("Data/Bases/Base_1.txt");	
		Base base2 = new Base("Data/Bases/Base_2.txt");
		Base base3 = new Base("Data/Bases/Base_3.txt");	
		Base base4 = new Base("Data/Bases/Base_4.txt");
		Base base5 = new Base("Data/Bases/Base_5.txt");	
		Base base6 = new Base("Data/Bases/Base_6.txt");
		Base base7 = new Base("Data/Bases/Base_7.txt");	
		Base base8 = new Base("Data/Bases/Base_8.txt");
		Base base9 = new Base("Data/Bases/Base_9.txt");	
		Base base10 = new Base("Data/Bases/Base_10.txt");
		Base base11 = new Base("Data/Bases/Base_11.txt");	
		Base base12 = new Base("Data/Bases/Base_12.txt");
		Base base13 = new Base("Data/Bases/Base_13.txt");	
		Base base14 = new Base("Data/Bases/Base_14.txt");
		Base base15 = new Base("Data/Bases/Base_15.txt");	
		Base base16 = new Base("Data/Bases/Base_16.txt");
		Base base17 = new Base("Data/Bases/Base_17.txt");	
		Base base18 = new Base("Data/Bases/Base_18.txt");
		Base base19 = new Base("Data/Bases/Base_19.txt");	
		Base base20 = new Base("Data/Bases/Base_20.txt");
		Base base21 = new Base("Data/Bases/Base_21.txt");	
		Base base22 = new Base("Data/Bases/Base_22.txt");
		Base base23 = new Base("Data/Bases/Base_23.txt");	

		//Liste Bases
		ListeBases listBases1 = new ListeBases("Data/Scénarios/Liste Bases/Liste_Bases1.txt");
		ListeBases listBases2 = new ListeBases("Data/Scénarios/Liste Bases/Liste_Bases2.txt");
		ListeBases listBases3 = new ListeBases("Data/Scénarios/Liste Bases/Liste_Bases3.txt");
		
		//Liste Entreprises
		ListeEntreprises listeEnt1 = new ListeEntreprises("Data/Scénarios/Liste Entreprises/Liste_Ent1.txt");
		ListeEntreprises listeEnt2 = new ListeEntreprises("Data/Scénarios/Liste Entreprises/Liste_Ent2.txt");
		ListeEntreprises listeEnt3 = new ListeEntreprises("Data/Scénarios/Liste Entreprises/Liste_Ent3.txt");
		
		
	
		ArrayList<Integer> ListCout = Branch_and_bound.trierCout(listBases2);
		System.out.println(ListCout);
		System.out.println(ListCout.size());
	
		
		
		//double cost1 = Branch_and_bound.BBFunction_Binaire(listBases1, listeEnt1);
		//System.out.println(  cost1);
		
		
		//double cost2 = Branch_and_bound.BBFunction_Binaire(listBases2, listeEnt2);
		//System.out.println( cost2);
		
		
		//double cost3 = Branch_and_bound.BBFunction_Binaire(listBases3, listeEnt3);
		//System.out.println( cost3);
		
		System.out.println("****************");
		
		ArrayList<String> dn1 = new ArrayList<String>();
		dn1.add("ed1");
		dn1.add("ed2");
		dn1.add("ed3");
		
		ArrayList<String> gn1 = new ArrayList<String>();
		gn1.add("eg1");
		gn1.add("eg2");
		gn1.add("eg3");
		
		ArrayList<String> gn2 = new ArrayList<String>();
		gn2.add("eg1_1");
		gn2.add("eg1_2");
	
		
		ArrayList<String> dn2 = new ArrayList<String>();
		dn2.add("ed1_1");
		dn2.add("ed1_2");
	
		
		Noeud racine = new Noeud();
		racine.addFilsDroit(dn1, "Dn1");
		racine.addFilsGauche(gn1, "Gn1");
		
		racine.getNoeud("Gn1").addFilsGauche(gn2, "Gn2");
		racine.getNoeud("Dn1").addFilsGauche(dn2, "Dn2");
		racine.afficher();
		
		
	}
}
