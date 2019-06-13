import java.io.*;
import java.util.ArrayList;

/**
 * Classe permettant de manipuler la liste des fiches gÃ©nÃ©alogiques et de les charger ou enregistrer
 * dans un fichier texte.
 * @author Claude.Duvallet@gmail.com
 *
 */
public class FichierGenealogique {

	private ArrayList<FicheGenealogique> listeFiches;
	//si le fichier a été charger.
	private boolean charger ;

	/**
	 * Constructeur par dÃ©faut.
	 */
	public FichierGenealogique (){
		listeFiches = new ArrayList<FicheGenealogique>();
	}

	/**
	 * MÃ©thode permettant de tester si une fiche gÃ©nÃ©alogique existe dÃ©jÃ
	 * @param fiche la fiche gÃ©nÃ©alogique Ã  recherche.
	 * @return la fiche si elle existe sinon null.
	 */
	public FicheGenealogique contains (FicheGenealogique fiche){
		int nombreDeFiches = listeFiches.size();
		int compteur=0;
		FicheGenealogique ficheCourante;
		// On parcours la liste des fiches et on compare chaque fiche de la liste avec la fiche courante.
		while (compteur<nombreDeFiches){
			ficheCourante=(FicheGenealogique)listeFiches.get(compteur);
			if (ficheCourante.equals(fiche)) return ficheCourante;
			compteur++;
		}
		return null;
	}

	/**
	 * Cette mÃ©thode permet d'ajouter une nouvelle fiche gÃ©nÃ©alogique si elle n'existe pas.
	 * @param fiche la nouvelle fiche Ã  ajouter.
	 */
	public void addFicheGenealogique(FicheGenealogique fiche){
		FicheGenealogique ficheExistante;
		if ((ficheExistante=contains(fiche))==null){
			listeFiches.add(fiche);
			ficheExistante=fiche;
		}
		else {
			if ((ficheExistante.getDateDeNaissance()==null) || (ficheExistante.getDateDeNaissance().equals("")))
				ficheExistante.setDateDeNaissance(fiche.getDateDeNaissance());
			if ((ficheExistante.getVilleDeNaissance()==null) || (ficheExistante.getVilleDeNaissance().equals("")))
				ficheExistante.setVilleDeNaissance(fiche.getVilleDeNaissance());
			if ((ficheExistante.getDeptDeNaissance()==null) || (ficheExistante.getDeptDeNaissance().equals("")))
				ficheExistante.setDeptDeNaissance(fiche.getDeptDeNaissance());
			if ((ficheExistante.getDateDeDeces()==null) || (ficheExistante.getDateDeDeces().equals("")))
				ficheExistante.setDateDeDeces(fiche.getDateDeDeces());
			if ((ficheExistante.getVilleDeDeces()==null) || (ficheExistante.getVilleDeDeces().equals("")))
				ficheExistante.setVilleDeDeces(fiche.getVilleDeDeces());
			if ((ficheExistante.getDeptDeDeces()==null) || (ficheExistante.getDeptDeDeces().equals("")))
				ficheExistante.setDeptDeDeces(fiche.getDeptDeDeces());
			if ((ficheExistante.getDateDeMariage()==null) || (ficheExistante.getDateDeMariage().equals("")))
				ficheExistante.setDateDeMariage(fiche.getDateDeMariage());
			if ((ficheExistante.getVilleDeMariage()==null) || (ficheExistante.getVilleDeMariage().equals("")))
				ficheExistante.setVilleDeMariage(fiche.getVilleDeMariage());
			if ((ficheExistante.getDeptDeMariage()==null) || (ficheExistante.getDeptDeMariage().equals("")))
				ficheExistante.setDeptDeMariage(fiche.getDeptDeMariage());
			if (ficheExistante.getPere()==null)
				ficheExistante.setPere(fiche.getPere());
			if (ficheExistante.getMere()==null)
				ficheExistante.setMere(fiche.getMere());
		}
		FicheGenealogique ficheResultat;
		if (ficheExistante.getPere()!=null){
			ficheResultat = getPere(ficheExistante);
			if (ficheResultat!=null) ficheExistante.setPere(ficheResultat);
		}
		if (ficheExistante.getMere()!=null){
			ficheResultat = getMere(ficheExistante);
			if (ficheResultat!=null) ficheExistante.setMere(ficheResultat);
		}
	}

	/**
	 * MÃ©thode permettant de retrouver la fiche gÃ©nÃ©alogique d'un pÃ¨re
	 * @param fiche celle dont on cherche le pÃ¨re.
	 * @return le rÃ©sultat de la recherche soit le pÃ¨re soit null.
	 */
	public FicheGenealogique getPere(FicheGenealogique fiche){
		FicheGenealogique ficheResultat;
		int nombreDeFiches = listeFiches.size();
		int compteur=0;
		if (fiche.getPere()!=null)
			while (compteur<nombreDeFiches){
				ficheResultat=(FicheGenealogique)listeFiches.get(compteur);
				if ((ficheResultat.getNom().equals(fiche.getPere().getNom())) &&
						(ficheResultat.getPrenom().equals(fiche.getPere().getPrenom()))){
					return ficheResultat;
				}
				compteur++;
			}

		return null;
	}

	/**
	 * MÃ©thode permettant de retrouver la fiche gÃ©nÃ©alogique d'un mÃ¨re
	 * @param fiche celle dont on cherche la mÃ¨re.
	 * @return le rÃ©sultat de la recherche soit le mÃ¨re soit null.
	 */
	public FicheGenealogique getMere(FicheGenealogique fiche){
		FicheGenealogique ficheResultat;
		int nombreDeFiches = listeFiches.size();
		int compteur=0;
		if (fiche.getMere()!=null)
			while (compteur<nombreDeFiches){
				ficheResultat=(FicheGenealogique)listeFiches.get(compteur);

				if ((ficheResultat.getNom().equals(fiche.getMere().getNom()))
						&& (ficheResultat.getPrenom().equals(fiche.getMere().getPrenom()))){
					return ficheResultat;
				}
				compteur++;
			}

		return null;
	}

	/**
	 * Lecture des fiches gÃ©nÃ©alogiques Ã  partir d'un fichier.
	 * @param nomDuFichier nom du fichier contenant les fiches.
	 */
	public void chargerFichier(String nomDuFichier){
		FileReader fr=null;
		String text;
		System.out.println("Chargement du fichier gÃ©nÃ©alogique "+nomDuFichier);
		try {
			// ouverture du fichier en mode lecture
			fr = new FileReader(nomDuFichier);
			BufferedReader bufferReader = new BufferedReader(fr);
			// Ã©criture de la ligne de texte
			while ((text=bufferReader.readLine())!=null)
				traiterLigne(text);
			// fermeture du fichier
			fr.close();
		} catch (IOException e) {
			System.out.println("ProblÃ¨me d'Ã©criture dans le fichier "+nomDuFichier);
		}
		// On va refaire les liens au niveau des pÃ¨res et des mÃ¨res
		int nombreDeFiches = listeFiches.size();
		int compteur=0;
		FicheGenealogique fg;

		while (compteur<nombreDeFiches){
			fg=(FicheGenealogique)listeFiches.get(compteur);
			FicheGenealogique ficheResultat;
			ficheResultat = getPere(fg);
			if (ficheResultat!=null)
				fg.setPere(ficheResultat);
			else fg.setPere(null);

			ficheResultat = getMere(fg);
			if (ficheResultat!=null)
				fg.setMere(ficheResultat);
			else fg.setMere(null);

			compteur++;
		}
		System.out.println("fichier charger "+nomDuFichier);

	}

	/**
	 * MÃ©thode permettant d'enregistrer toutes les fiches gÃ©nÃ©alogiques dans un fichier texte.
	 * @param nomDuFichier le nom du fichier Ã  enregistrer
	 */
	public void enregistrerFichier(String nomDuFichier){
		FileWriter fw=null;
		System.out.println("Enregistrement de toutes les fiches gÃ©nÃ©alogiques.");
		try {
			// ouverture du fichier en mode Ã©criture
			fw = new FileWriter(nomDuFichier,false);
			// Ã©criture des lignes de texte
			int nombreDeFiches = listeFiches.size();
			int compteur=0;
			FicheGenealogique fg;
			String ligne;
			while (compteur<nombreDeFiches){
				fg=(FicheGenealogique)listeFiches.get(compteur);
				ligne=fg.convertToString();
				fw.write(ligne);
				compteur++;
			}
			// fermeture du fichier
			fw.close();
		} catch (IOException e) {
			System.out.println("ProblÃ¨me d'Ã©criture dans le fichier "+nomDuFichier);
		}
	}

	/**
	 * MÃ©thode permettant de rÃ©cupÃ©rer les informations situÃ©es dans les lignes du fichier texte.
	 * Chaque ligne possÃ¨de toutes les informations d'une fiche gÃ©nÃ©alogique.
	 * @param text la ligne de texte Ã  traiter.
	 */
	private void traiterLigne(String text) {
		//System.out.println(text);
		String [] elements = text.split(";",16);
		FicheGenealogique ficheGenealogique = new FicheGenealogique (elements[0],elements[1],elements[2],elements[3],elements[4]);
		ficheGenealogique.setDateDeMariage(elements[5]);
		ficheGenealogique.setVilleDeMariage(elements[6]);
		ficheGenealogique.setDeptDeMariage(elements[7]);
		ficheGenealogique.setDateDeDeces(elements[8]);
		ficheGenealogique.setVilleDeDeces(elements[9]);
		ficheGenealogique.setDeptDeDeces(elements[10]);

		ficheGenealogique.setPere(new FicheGenealogique(elements[11],elements[12]));
		ficheGenealogique.setMere(new FicheGenealogique(elements[13],elements[14]));

		FicheGenealogique ficheResultat;
		ficheResultat = getPere(ficheGenealogique.getPere());
		if (ficheResultat!=null)
			ficheGenealogique.setPere(ficheResultat);

		ficheResultat = getPere(ficheGenealogique.getMere());
		if (ficheResultat!=null)
			ficheGenealogique.setMere(ficheResultat);
		//System.out.println(ficheGenealogique);
		listeFiches.add(ficheGenealogique);
	}

	/**
	 * @return la liste des fiches prÃ©sentes dans l'outil.
	 */
	public ArrayList<FicheGenealogique> getListeFiches() {
		return listeFiches;
	}

	public void supprimerFiche(int i)
	{
		for(FicheGenealogique fiche : this.listeFiches)
		{
			if(fiche.getPere() == this.listeFiches.get(i))
			{
				fiche.setPere(null);
			}
			if(fiche.getMere() == this.listeFiches.get(i))
			{
				fiche.setMere(null);
			}
		}
		this.listeFiches.remove(i);
	}
	public void supprimerFiche(FicheGenealogique fiche)
	{
		for(FicheGenealogique ficheTmp : this.listeFiches)
		{
			if(ficheTmp.getPere() == fiche)
			{
				ficheTmp.setPere(null);
			}
			if(ficheTmp.getMere() == fiche)
			{
				ficheTmp.setMere(null);
			}
		}
		this.listeFiches.remove(fiche);
	}

	/**
	 * @param listeFiches la nouvelle liste de fiches Ã  dÃ©finir.
	 */
	public void setListeFiches(ArrayList<FicheGenealogique> listeFiches) {
		this.listeFiches = listeFiches;
	}

	public boolean getCharger()
	{
		return this.charger;
	}
	public void setCharger(boolean b)
	{
		this.charger=b;
	}

	public FicheGenealogique rechercher(String nom, String prenom)
	{
		for(FicheGenealogique fiche : this.listeFiches)
		{
			if(fiche.getNom().equals(nom) && fiche.getPrenom().equals(prenom))
			{
				return fiche;
			}
		}
		return null;
	}

}
