package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtals){
			etals = new Etal[nbEtals];
			
			for (int i=0; i<nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal( vendeur,  produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe() == true) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.contientProduit(produit)) {
					nbEtalProduit = nbEtalProduit + 1;
				}
			}
			
			Etal[] etalsProduit = new Etal[nbEtalProduit];
			
			int j=0;
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.contientProduit(produit)) {
					etalsProduit[j] = etals[i];
					j = j + 1;
					
				}
			}
			
			return etalsProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.getVendeur() == gaulois) {
					return etal;
				}

			}
			return null;
		}
		
		private String afficherMarche() {
			int nbEtalVide = 0;
			
		}
		
		
		
		
	}
	
	
}