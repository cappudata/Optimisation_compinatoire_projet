import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class Branch_and_bound {
	
	
	
	public static ListeBases trierListBase(ListeBases ListeBasesDonnee) {
		int coutB = ListeBasesDonnee.getCoutListeBase();
		ArrayList<Base> listeBases = ListeBasesDonnee.getListeBases();
		ArrayList<Base> newlisteBases = new ArrayList<Base>();
		
		while(listeBases.size()>0) {
			int Mincout = listeBases.get(0).getCoutBase();
			int index = 0;
			for (int i = 0; i<listeBases.size(); i++) {
				if (listeBases.get(i).getCoutBase() < Mincout) {
					index = i;
					Mincout = listeBases.get(i).getCoutBase();
				}
			}
			newlisteBases.add(listeBases.get(index));
			listeBases.remove(index);
		}
		
		ListeBases lb = new ListeBases();
		lb.set_coutListeBase(coutB);
		lb.set_listeBases(newlisteBases);
		return lb;	
	}
	
	
	//trier les couts des bases dans une listeBase en fonction de l'heuristique
	public static ArrayList<Integer> trierCout(ListeBases LesListeBases) {
		ArrayList<Integer> LesCouts = new ArrayList<Integer>();
		for (Base b : LesListeBases.getListeBases()) {
			LesCouts.add(b.getCoutBase());
		}	
		Collections.sort(LesCouts);	
		return LesCouts;
	}
	
	
	public boolean Match(String entreprise, ArrayList<String> listeEntreprises) {
		for (String ent : listeEntreprises) {
			if (ent.equals(entreprise))
				return true;
		}
		return false;
	}
	
	public static double BBFunction_Binaire(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		double UB = Double.POSITIVE_INFINITY;
		int currentBest = 0;
		
		int LB = listeEntreprise.getCoutListeEntreprise() + listeBases.getCoutListeBase();
		ArrayList<String> LEnt = new ArrayList<String>();
		ArrayList<String> listeEnt = listeEntreprise.getListeEntreprises();
		
		//trier les bases en fonction le cout
		ArrayList<Base> ListBase = Branch_and_bound.trierListBase(listeBases).getListeBases();
		
		int indice = 0;
		//Tant que A n'est pas vide:
		while(ListBase.size() != 0) {
			
			Base k = ListBase.get(indice); //Base1.txt
			ListBase.remove(indice);
			//rentre dans la Base1.txt	
			for(int i = 0 ; i < k.getEntreprises().size()  ; i++ ) {			
				if( !(LB >= UB) ) {
					if (LEnt.containsAll(listeEnt)) {
						UB = LB;
						currentBest = i;
					} else {
						if (DeleteEntreprise(listeEnt, k, LEnt))
							LB = k.getCoutBase() + LB;
					}					
				}	
			}	
		}	
		
		for (String str : LEnt) {
			System.out.println(str);
		}
		return UB;
	}
	
	
	private static boolean DeleteEntreprise(ArrayList<String> listeEntCherche, Base k, ArrayList<String> listPourAjouter) {
		ArrayList<String> toRemove = new ArrayList<String>();
		boolean delete = false;
		
		for (String ent : listeEntCherche) {
				if( k.getEntreprises().contains(ent)) {
					listPourAjouter.add(ent);
					toRemove.add(ent);
					delete = true;
				}			
		}
		
		listeEntCherche.removeAll(toRemove);
		return delete;
	}
	
	
	private static int getIndice(ArrayList<String> entreprises, String rechercheEntreprise) {
		for(int  i= 0 ; i  < entreprises.size() ; i++) {
			if(entreprises.get(i) == rechercheEntreprise) {
				return i ;
			}
		}
		return -1;
	}	
}
