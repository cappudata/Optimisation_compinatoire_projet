import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class Donnees {
	
	
	private ArrayList<Base> ListBases;	
	private ArrayList<String> ListEntCherche;
	private ListeBases _listeBases;
	private ListeEntreprises _listeEntCherche;
	
	//UB = UperBound
	private double UB = Double.POSITIVE_INFINITY;
	
	
	public Donnees(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		this._listeBases = listeBases;
		this._listeEntCherche = listeEntreprise;
		this.ListBases = listeBases.getListeBases(); 
		this.ListEntCherche = listeEntreprise.getListeEntreprises();	
	}
	
	//Trier les base dans une listBase en fonction le cout
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
	
	
	//trier les couts des bases dans une listeBase 
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
	
	
	public double Glouton() {
		//double UB = Double.POSITIVE_INFINITY;
		int currentBest = 0;
		
		//LB = LowerBound
		int LB = _listeEntCherche.getCoutListeEntreprise() + _listeBases.getCoutListeBase();
		ArrayList<String> LEnt = new ArrayList<String>();
		ArrayList<String> listeEnt = _listeEntCherche.getListeEntreprises();
		
		//trier les bases en fonction le cout
		ArrayList<Base> ListBase = Donnees.trierListBase(_listeBases).getListeBases();
		
		int indice = 0;
		while(ListBase.size() != 0) {	
			Base k = ListBase.get(indice); 
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
	

	
	
	public double BnB() {
		int currentBest = 0;
		int LB = this._listeEntCherche.getCoutListeEntreprise() + this._listeBases.getCoutListeBase();
			
		Noeud racine = new Noeud(this._listeBases, LB);	
		ArrayList<Noeud> ListeNoeds = Noeud.ListeNoeds;
		
		int index = 0;
		while(ListeNoeds.size() !=0) {
			Noeud n = ListeNoeds.get(index);
			ListeNoeds.remove(index);
			recursivité(n, n.getListEntNoeud(), ListeNoeds, LB);	
		}
		return UB;
	}
	

	public void recursivité(Noeud n, ArrayList<String> listeEntDansNoeud, ArrayList<Noeud> ListeNoeds, int LB) {
		for (int i=0; i<ListBases.size(); i++) {		
			if (!n.getParcourirBase().get(i)) {
				int new_cout = n.getCout() + ListBases.get(i).getCoutBase();		
				if (new_cout > UB) {
					break;
				} else {
					if (n.getListEntNoeud().containsAll(ListEntCherche)) {
						this.UB = n.getCout();
						break;
					} else {
						n.appliquerBase(ListBases.get(i), ListEntCherche, listeEntDansNoeud, new_cout, UB, this, i);
						break;
					}
				}
			}
		}				
	}
}
