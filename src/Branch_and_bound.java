import java.util.ArrayList;

public class Branch_and_bound {
	
	
	//
	public static ListeBases regle_de_branchement(ArrayList<ListeBases> LesListeBases) {
		int currentBest = 0;
		ListeBases listeBases = null;
		for (ListeBases lb : LesListeBases) {
			currentBest = lb.getCoutListeBase();
			listeBases = lb;
			if (currentBest < lb.getCoutListeBase()) {
				currentBest = lb.getCoutListeBase();
				listeBases = lb;
			}
		}
		return listeBases;
	}
	
	
	public static void BBFunction(ListeBases listeBases, ListeEntreprises listeEntreprise) {
		int majorant_UB = listeBases.getCoutListeBase();
		int currentBest = 0;
		
		
	}
	
	
	
}
