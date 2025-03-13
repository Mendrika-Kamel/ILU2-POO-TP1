package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		
		
		Gaulois bonemine = new Gaulois("Bonemine", 7);
	
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(abraracourcix);

		
		Etal etalFleur = village.rechercherEtal(bonemine);
		System.out.println(etalFleur.acheterProduit(10, abraracourcix));

		System.out.println("Fin du test");

		}
}
