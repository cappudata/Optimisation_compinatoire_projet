import java.util.ArrayList;

public class Noeud {


	private String _name;
	private int _cout;
	private Noeud _filsGauche;
	private Noeud _filsDroit;
	private ArrayList<String> _listeEnt = new ArrayList<String>();
	private ArrayList<Boolean> _parcouruBase = new ArrayList<Boolean>();
	private ArrayList<String> _listeBase = new ArrayList<String>();
	
	public static ArrayList<Noeud> ListeNoeds = new ArrayList<Noeud>();
	//public static Noeud _racine;
	
	
	
	public Noeud(ListeBases listeBases, int cout) {
		this._name = "Racine";
		ListeNoeds.add(this);
		this._cout = cout;
		
		
		//Initialiser un tableau boolean pour savoir si ce noeu a parcouru quel base ?
		// True => déjà parcouru
		// False => pas encore
		int tailleListeBase = listeBases.getListeBases().size();
		for (int i=0; i<tailleListeBase; i++) {
			this._parcouruBase.add(false);
		}		
	}
	
	private Noeud(ArrayList<String> listeEnt) {
		this._listeEnt = listeEnt;
		
	}
	
	

	//v2
	public void appliquerBase(Base b, ArrayList<String> ListEntCherche, ArrayList<String> liste, int cout, double UB, Donnees bnb, int i ) {
		
		ArrayList<String> new_list = new ArrayList<String>(liste);
		Donnees bb = bnb;
			//filsDroit : on n'applique pas la base
			this.addFilsDroit(liste, "not applique" + b.toString(), this._cout,i);

			//filsGauche : on applique la base		
			ArrayList<String> listeEntBase = b.getEntreprises();
			for (String ent : ListEntCherche) {
				if (listeEntBase.contains(ent) ) {
					if (!new_list.contains(ent))
						new_list.add(ent);
				}
			}
			this.addFilsGauche(new_list, "applique" + b.toString(), cout, i);	
	}

	public void addFilsGauche(ArrayList<String> listEnt, String name, int cout, int i) {
		Noeud n = new Noeud(listEnt);
		n.setName(name);
		this._filsGauche = n;
		n.iniParcourirBase(_parcouruBase);
		n.setParcourirBase(i);
		n.setCout(cout);
		this.ListeNoeds.add(0,n);
	}
	
	
	public void addFilsDroit(ArrayList<String> listEnt, String name, int cout, int i) {
		Noeud n = new Noeud(listEnt);
		n.setName(name);
		this._filsDroit = n;
		n.iniParcourirBase(_parcouruBase);
		n.setParcourirBase(i);
		n.setCout(cout);
		this.ListeNoeds.add(0,n);
	}
	
	
	public ArrayList<String>  getListEntNoeud() {
		return _listeEnt;
	}
	
	
	public void addBase(String base) {
		
	}
	
	public void iniParcourirBase(ArrayList<Boolean> table) {
		this._parcouruBase = new ArrayList<Boolean>(table);
	}
	
	public void setParcourirBase(int i) {
		this._parcouruBase.set(i, true);
	}
	
	public ArrayList<Boolean> getParcourirBase() {
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
