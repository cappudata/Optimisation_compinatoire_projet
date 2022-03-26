import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class Branch_and_bound {
	
	
	private static ArrayList<Base> ListBases;	
	private static ArrayList<String> ListEntCherche;
	private ListeBases _listeBases;
	private ListeEntreprises _listeEntCherche;
	
	
	public Branch_and_bound(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		this.ListBases = listeBases.getListeBases(); 
		this.ListEntCherche = listeEntreprise.getListeEntreprises();
		
		this._listeBases = listeBases;
		this._listeEntCherche = listeEntreprise;
		
	}
	
	//Trier les base dans une listBase en fonction le cout
	public  ListeBases trierListBase(ListeBases ListeBasesDonnee) {
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
	
	
	//trier les couts des bases dans une listeBase 
	public static ArrayList<Integer> trierCout(ListeBases LesListeBases) {
		ArrayList<Integer> LesCouts = new ArrayList<Integer>();
		for (Base b : LesListeBases.getListeBases()) {
			LesCouts.add(b.getCoutBase());
		}	
		Collections.sort(LesCouts);	
		return LesCouts;
	}
	
	
	public  boolean Match(String entreprise, ArrayList<String> listeEntreprises) {
		for (String ent : listeEntreprises) {
			if (ent.equals(entreprise))
				return true;
		}
		return false;
	}
	
	//ça la fonctionne Glouton (il faut changer le nom) 
	public  double BBFunction_Binaire(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		double UB = Double.POSITIVE_INFINITY;
		int currentBest = 0;
		
		int LB = listeEntreprise.getCoutListeEntreprise() + listeBases.getCoutListeBase();
		ArrayList<String> LEnt = new ArrayList<String>();
		ArrayList<String> listeEnt = listeEntreprise.getListeEntreprises();
		
		//trier les bases en fonction le cout
		ArrayList<Base> ListBase = this.trierListBase(listeBases).getListeBases();
		
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
	
	
	private boolean DeleteEntreprise(ArrayList<String> listeEntCherche, Base k, ArrayList<String> listPourAjouter) {
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
	
	
	private int getIndice(ArrayList<String> entreprises, String rechercheEntreprise) {
		for(int  i= 0 ; i  < entreprises.size() ; i++) {
			if(entreprises.get(i) == rechercheEntreprise) {
				return i ;
			}
		}
		return -1;
	}	
	
	
	//Fonction qui permet de vérifier si la liste 1 contient tout les éléments de la liste 2 
	private  static boolean checkList (ArrayList<String> list1, ArrayList<String> list2) {
		if (list1.containsAll(list2)) 
			return true;
		return false;
		
	}
	
	
	public double BnB() {
		double UB = Double.POSITIVE_INFINITY;
		int currentBest = 0;
		
		//une liste vide pour ajouter les entresprises trouvé
		//Cette liste est pour ajouter dans le filsGauche
		ArrayList<String> liste = new ArrayList<String>();
	
		Noeud racine = new Noeud(this._listeBases);	
		ArrayList<Noeud> ListeNoeds = Noeud.ListeNoeds;
		
		int LB = this._listeEntCherche.getCoutListeEntreprise();
		int index = 0;
		while(ListeNoeds.size() !=0) {
			Noeud n = ListeNoeds.get(index);
			//ListeNoeds.remove(index);
			recursivité(n, liste, ListeNoeds, LB, UB);	
		}
		return UB;
	}
	
	public static void recursivité(Noeud n, ArrayList<String> listeEntDansNoeud, ArrayList<Noeud> ListeNoeds, int LB, double UB) {

		if (ListeNoeds.size()!= 0) {
			System.out.println("dsqd" + ListeNoeds.size());
			ListeNoeds.remove(0);
		
			for (int i=0; i<ListBases.size(); i++) {		
				if (!n.getParcourirBase()[i]) {
					n.appliquerBase(ListBases.get(i), ListEntCherche, n.getListEntNoeud(), n.getCout(), UB);
					n.getFilsGauche().setParcourirBase(i);
					n.getFilsDroit().setParcourirBase(i);
					if (checkList(ListEntCherche, n.getFilsGauche().getListEntNoeud())) {
						UB = n.getFilsGauche().getCout();
						ListeNoeds.remove(0);
						recursivité(ListeNoeds.get(0), ListeNoeds.get(0).getListEntNoeud(), ListeNoeds, ListeNoeds.get(0).getCout(), UB);
					} else {
						recursivité(ListeNoeds.get(0), ListeNoeds.get(0).getListEntNoeud(), ListeNoeds, ListeNoeds.get(0).getCout(), UB);
					}
				}
			}
		}
	}
	
}
