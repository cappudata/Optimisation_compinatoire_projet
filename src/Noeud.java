import java.util.ArrayList;

public class Noeud {


	private String _name;
	private int _cout;
	private Noeud _filsGauche;
	private Noeud _filsDroit;
	private ArrayList<String> _listeEnt = new ArrayList<String>();
	private boolean[] _parcouruBase;

	
	public static ArrayList<Noeud> ListeNoeds = new ArrayList<Noeud>();
	//public static Noeud _racine;
	
	
	
	public Noeud(ListeBases listeBases) {
		this._name = "Racine";
		ListeNoeds.add(this);
		
		//Initialiser un tableau boolean pour savoir si ce noeu a parcouru quel base ?
		// True => déjà parcouru
		// False => pas encore
		int tailleListeBase = listeBases.getListeBases().size();
		this._parcouruBase = new boolean[tailleListeBase];
		for (int i=0; i<this._parcouruBase.length; i++) {
			this._parcouruBase[i] = false;
		}
	
	}
	
	public Noeud(ArrayList<String> listeEnt) {
		this._listeEnt = listeEnt;
		
	}
	
	
	public void appliquerBase(Base b, ArrayList<String> ListEntCherche, ArrayList<String> liste, int cout, double UB ) {
		
		int new_cout = cout + b.getCoutBase();
		if (new_cout < UB) {
			//filsDroit : on n'applique pas la base
			this.addFilsDroit(this._listeEnt, "not applique" + b.toString(), this._cout);

			//filsGauche : on applique la base		
			ArrayList<String> listeEntBase = b.getEntreprises();
			for (String ent : ListEntCherche) {
				if (listeEntBase.contains(ent) ) {
					if (!liste.contains(ent))
						liste.add(ent);
				}
			}
			this.addFilsGauche(liste, "applique" + b.toString(), new_cout);	
		} else {
			Branch_and_bound.recursivité(ListeNoeds.get(0), ListeNoeds.get(0).getListEntNoeud(), ListeNoeds, ListeNoeds.get(0).getCout(), UB);
		}
	}
	


	public void addFilsGauche(ArrayList<String> listEnt, String name, int cout) {
		Noeud n = new Noeud(listEnt);
		n.setName(name);
		this._filsGauche = n;
		n.iniParcourirBase(_parcouruBase);
		n.setCout(cout);
		this.ListeNoeds.add(0,n);
	}
	
	
	public void addFilsDroit(ArrayList<String> listEnt, String name, int cout) {
		Noeud n = new Noeud(listEnt);
		n.setName(name);
		this._filsDroit = n;
		n.iniParcourirBase(_parcouruBase);
		n.setCout(cout);
		this.ListeNoeds.add(0,n);
	}
	
	
	public ArrayList<String>  getListEntNoeud() {
		return _listeEnt;
	}
	
	
	public void iniParcourirBase(boolean[] table) {
		this._parcouruBase = table;
	}
	
	public void setParcourirBase(int i) {
		this._parcouruBase[i] = true;
	}
	
	public boolean[] getParcourirBase() {
		return this._parcouruBase;
	}
	
	public void setCout(int cout) {
		this._cout = cout;
	}
	
	public int getCout() {
		return this._cout;
	}
	
	public String getName() {
		return this._name;
	}
	
	public void setName(String name) {
		this._name = name;
	}
   
   public Noeud getNoeud(String txt)
   {
	   for (Noeud n : this.ListeNoeds) {
		   if (n.getName().equals(txt))
			   return n;
	   }
	   return null;
   }
	
	public Noeud getFilsDroit() {
		return this._filsDroit;
	}
	
	public Noeud getFilsGauche() {
		return this._filsGauche;
	}
	
	
	public void afficher() {
		for (String str : this._listeEnt) {
			System.out.print(str + " ");
		}
		
		if (this._filsGauche != null) {
			System.out.print("Gauche: ");
			this._filsGauche.afficher();
			System.out.println();
		}
		if (this._filsDroit != null) {
			System.out.print("Droit: ");
			this._filsDroit.afficher();
			System.out.println();
		}
	}
}
