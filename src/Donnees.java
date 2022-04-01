import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class Donnees {
	
	private ListeBases _listeBases;
	private ArrayList<Base> ListBases;	
	
	private ListeEntreprises _listeEntCherche;
	private ArrayList<String> ListEntCherche;
	
	//UB = UperBound (Pout BnB)
	private double UB = Double.POSITIVE_INFINITY;
	
	//Noeud courant qui contient le meuilleur cout
	private Noeud _currentBest;
	
	
	public Donnees(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		this._listeBases = listeBases;
		this._listeEntCherche = listeEntreprise;
		this.ListBases = listeBases.getListeBases(); 
		this.ListEntCherche = listeEntreprise.getListeEntreprises();	
		this._currentBest = null;
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
	
	public double Glouton() {
		double UB_glouton = Double.POSITIVE_INFINITY;
		int LB_glouton = _listeEntCherche.getCoutListeEntreprise() + _listeBases.getCoutListeBase();
				
		ArrayList<String> LEnt = new ArrayList<String>(); // List pour ajouter les ents au cour de la recherche
		ArrayList<Base> LBase = new ArrayList<Base>(); //List pour ajouter les bases utilisées;
		
		boolean baseUsed = false;
		for(int ind_base=0; ind_base<ListBases.size(); ind_base++ ) {	
			Base k = ListBases.get(ind_base); 
			ArrayList<String> entreprises_de_baseK = k.getEntreprises();
			
			for (int i=0; i<this.ListEntCherche.size(); i++) {
				if (entreprises_de_baseK.contains( ListEntCherche.get(i)) && !(LEnt.contains(ListEntCherche.get(i))) ) {
					LEnt.add(ListEntCherche.get(i));
					baseUsed = true;
					if (!LBase.contains(k)) {
						LBase.add(k);
					}
				}
			}
			if (baseUsed) {
				LB_glouton += k.getCoutBase();
				baseUsed = false;
			}
			
			if (LEnt.containsAll(ListEntCherche)) {
				UB_glouton = LB_glouton;
				break;
			}
		}	
		System.out.print("Bases utilisés pour Glouton : [");
		for (Base b : LBase) {
			System.out.print(b + " ");
		}
		System.out.print("]");
		System.out.println("");
		return UB_glouton;
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
		
		System.out.print("Liste Base pour BnB : ");
		this._currentBest.afficherListBasesNoeud();
		return UB;
	}
	

	public void recursivité(Noeud n, ArrayList<String> listeEntDansNoeud, ArrayList<Noeud> ListeNoeds, int LB) {
		if (n.getListEntNoeud().containsAll(ListEntCherche)) {
			this.UB = n.getCout();
			this._currentBest = n;
		} else {
			for (int i=0; i<ListBases.size(); i++) {		
				if (!n.getParcourirBase().get(i)) {
					int new_cout = n.getCout() + ListBases.get(i).getCoutBase();		
					if (new_cout > UB) {
						break;
					} else {
						if (n.getListEntNoeud().containsAll(ListEntCherche)) {
							this.UB = n.getCout();
							this._currentBest = n;
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
	
	/*
	//trier les couts des bases dans une listeBase 
	public static ArrayList<Integer> trierCout(ListeBases LesListeBases) {
		ArrayList<Integer> LesCouts = new ArrayList<Integer>();
		for (Base b : LesListeBases.getListeBases()) {
			LesCouts.add(b.getCoutBase());
		}	
		Collections.sort(LesCouts);	
		return LesCouts;
	}
	*/
	
}
