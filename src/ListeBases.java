import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListeBases {
	private int _coutListeBase = 0 ;
	private ArrayList<Base> _listeBases = new ArrayList<Base>();
	private String _fichier;
	
	public ListeBases(String fichier) {
		this._fichier = fichier;
		//lire le fichier 
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String line;
			while((line = br.readLine()) != null) {
				if(line.length() >2) {
					this._listeBases.add(new Base("Data/Bases/"+line));
				}
				
				if (line.length() <= 2 && line.length() != 0) {
					this._coutListeBase = Integer.parseInt(line);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
		
	
	public int getCoutListeBase() {
		return this._coutListeBase;
	}
	
	
	public ArrayList<Base> getListeBases() {
		return this._listeBases;
	}
	
	
	public String getFichier() {
		return this._fichier;
	}
	
	public int tailleListeBases() {
		return this._listeBases.size();
	}
	
	
	public void afficherListeBases() {
		for (Base b : this._listeBases) {
			b.afficherEntreprises();
		}
	}
}
