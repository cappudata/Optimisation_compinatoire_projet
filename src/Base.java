import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Base {
	private int _coutBase = 0;
	private ArrayList<String> _entreprises = new ArrayList<String>();
	private String _namefichier;
	
	
	public Base(String fichier) {
		//lire le fichier 
		this._namefichier = fichier;
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String line;
			while((line = br.readLine()) != null) {
				if(line.length() >2) {
					this._entreprises.add(line);
				}
				
				if (line.length() <= 2 && this._coutBase == 0) {
					this._coutBase = Integer.parseInt(line);
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public int getCoutBase() {
		return this._coutBase;
	}
	

	public ArrayList<String> getEntreprises() {
		return this._entreprises;
	}
	
	
	public int tailleEntrepriseDansBase() {
		return this._entreprises.size();
	}
	
	
	public void afficherEntreprises() {
		for (String str : this._entreprises) {
			System.out.println(str);
		}
	}
	
	public String toString() {
		return this._namefichier.substring(11);
	}
	
	
	
}
