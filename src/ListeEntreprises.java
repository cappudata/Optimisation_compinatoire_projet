import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListeEntreprises {
	private int _coutListeEntreprise = 0;	
	private ArrayList<String> _listeEntreprises = new ArrayList<String>();
	
	
	public ListeEntreprises(String fichier) {
		//lire le fichier 
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String line;
			while((line = br.readLine()) != null) {
				if(line.length() >2) {
					this._listeEntreprises.add(line);
				}			
				if (line.length() <= 2 ) {
					this._coutListeEntreprise = Integer.parseInt(line);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int getCoutListeEntreprise() {
		return this._coutListeEntreprise;
	}
	
	public ArrayList<String> getListeEntreprises() {
		return this._listeEntreprises;
	}
	
	
	public int tailleListeEntreprises() {
		return this._listeEntreprises.size();
	}
	
	public void afficherListeEntreprises() {
		for (String str : this._listeEntreprises) {
			System.out.println(str);
		}
	}
}
