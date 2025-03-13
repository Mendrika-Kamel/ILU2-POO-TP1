package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
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
				if (!etals[i].isEtalOccupe()) {
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
				if (etal.getVendeur().equals(gaulois)) {
					return etal;
				}

			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (!etal.isEtalOccupe()) {
					nbEtalVide++;
				} else {
					chaine.append(etal.afficherEtal());
				}
				
			}
			
			chaine.append("Il reste " + nbEtalVide + " etals non utilisé dans le marché.");
			return chaine.toString();
		}
		
		
	}
	

	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder(vendeur.getNom() + "cherche un endroit pour vendre " +nbProduit +" "  
				+ produit);
		int indice = this.marche.trouverEtalLibre();
		if (indice==-1) {
			chaine.append("Aucun étal disponible.");

		} else {
			this.marche.utiliserEtal(indice,  vendeur, produit,  nbProduit);
			chaine.append("Le vendeur "+ vendeur.getNom() + " vend des fleurs à l'étal n°" + (indice+1) + ".");
		}
		return chaine.toString();
		
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal etals[] = this.marche.trouverEtals(produit);
		
		int nbEtals = etals.length;
		if (nbEtals == 0) {
			return "Il n'y a pas de vendeurs qui propose des " + produit + " au marché";
		} else if (nbEtals == 1) {
			return "Seul le vendeur "+ etals[0].getVendeur().getNom() + " propose des fleurs au marché.";
			
		} else {
			StringBuilder chaine = new StringBuilder("Les vendeurs qui proposent des fleurs sont :\n");
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				chaine.append("- " + etal.getVendeur().getNom()+ "\n");
			}
			return chaine.toString();
			
		}
		

	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return this.marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur);
		return etal.libererEtal();
		
	}
	
	public String afficherMarche() {
		return this.marche.afficherMarche();
	}
	
}