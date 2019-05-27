/**
 * Cette classe permet de reprÃ©senter la fiche gÃ©nÃ©alogique d'une personne
 * @author Claude.Duvallet@gmail.com
 */
public class FicheGenealogique {

	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String dateDeMariage;
	private String dateDeDeces;
	private String villeDeNaissance;
	private String villeDeMariage;
	private String villeDeDeces;
	private String deptDeNaissance;
	private String deptDeMariage;
	private String deptDeDeces;
	private FicheGenealogique pere;
	private FicheGenealogique mere;
	
	/**
	 * Constructeur par dÃ©faut
	 * @param nom le nom de personne
	 * @param prenom le prÃ©nom de la personne
	 */
	public FicheGenealogique(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Constructeur avec les informations sur la date, le lieu et le dÃ©partement de naissance.
	 * @param nom le nom
	 * @param prenom le prÃ©nom
	 * @param dateDeNaissance la date de naissance
	 * @param villeDeNaissance la ville de naissance
	 * @param deptDeNaissance le dÃ©partement de naissance
	 */
	public FicheGenealogique(String nom, String prenom, String dateDeNaissance, 
							 String villeDeNaissance, String deptDeNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.villeDeNaissance = villeDeNaissance;
		this.deptDeNaissance = deptDeNaissance;
	}

	/**
	 * @return le nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom le nom Ã  dÃ©finir
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le prÃ©nom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom le prÃ©nom Ã  dÃ©finir
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return la date de naissance
	 */
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * @param dateDeNaissance la date de naissance Ã  dÃ©finir
	 */
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * @return la date de mariage de la personne
	 */
	public String getDateDeMariage() {
		return dateDeMariage;
	}

	/**
	 * @param dateDeMariage la date de mariage Ã  dÃ©finir
	 */
	public void setDateDeMariage(String dateDeMariage) {
		this.dateDeMariage = dateDeMariage;
	}

	/**
	 * @return la date de dÃ©cÃ¨s
	 */
	public String getDateDeDeces() {
		return dateDeDeces;
	}

	/**
	 * @param dateDeDeces la date de dÃ©cÃ¨s Ã  dÃ©finir
	 */
	public void setDateDeDeces(String dateDeDeces) {
		this.dateDeDeces = dateDeDeces;
	}

	/**
	 * @return la ville de naissance
	 */
	public String getVilleDeNaissance() {
		return villeDeNaissance;
	}

	/**
	 * @param villeDeNaissance la ville de naissance Ã  dÃ©finir
	 */
	public void setVilleDeNaissance(String villeDeNaissance) {
		this.villeDeNaissance = villeDeNaissance;
	}

	/**
	 * @return la ville de mariage
	 */
	public String getVilleDeMariage() {
		return villeDeMariage;
	}

	/**
	 * @param villeDeMariage la ville de mariage Ã  dÃ©finir
	 */
	public void setVilleDeMariage(String villeDeMariage) {
		this.villeDeMariage = villeDeMariage;
	}

	/**
	 * @return la ville de dÃ©cÃ¨s
	 */
	public String getVilleDeDeces() {
		return villeDeDeces;
	}

	/**
	 * @param villeDeDeces la ville de dÃ©cÃ¨s Ã  dÃ©finir
	 */
	public void setVilleDeDeces(String villeDeDeces) {
		this.villeDeDeces = villeDeDeces;
	}

	/**
	 * @return le dÃ©partement de naissance
	 */
	public String getDeptDeNaissance() {
		return deptDeNaissance;
	}

	/**
	 * @param deptDeNaissance le dÃ©partement de naissance Ã  dÃ©finir
	 */
	public void setDeptDeNaissance(String deptDeNaissance) {
		this.deptDeNaissance = deptDeNaissance;
	}

	/**
	 * @return le dÃ©partement de mariage
	 */
	public String getDeptDeMariage() {
		return deptDeMariage;
	}

	/**
	 * @param deptDeMariage le dÃ©partement de mariage Ã  dÃ©finir
	 */
	public void setDeptDeMariage(String deptDeMariage) {
		this.deptDeMariage = deptDeMariage;
	}

	/**
	 * @return le dÃ©partement de dÃ©cÃ¨s
	 */
	public String getDeptDeDeces() {
		return deptDeDeces;
	}

	/**
	 * @param deptDeDeces le dÃ©partement de dÃ©cÃ¨s Ã  dÃ©finir
	 */
	public void setDeptDeDeces(String deptDeDeces) {
		this.deptDeDeces = deptDeDeces;
	}

	/**
	 * @return la fiche gÃ©nÃ©alogique du pÃ¨re
	 */
	public FicheGenealogique getPere() {
		return pere;
	}

	/**
	 * @param pere la fiche gÃ©nÃ©alogique du pÃ¨re Ã  dÃ©finir
	 */
	public void setPere(FicheGenealogique pere) {
		this.pere = pere;
	}

	/**
	 * @return la fiche gÃ©nÃ©alogique de la mÃ¨re
	 */
	public FicheGenealogique getMere() {
		return mere;
	}

	/**
	 * @param mere la fiche gÃ©nÃ©alogique de la mÃ¨re Ã  dÃ©finir
	 */
	public void setMere(FicheGenealogique mere) {
		this.mere = mere;
	}
	
	/**
	 * MÃ©thode permettant de comparer la fiche gÃ©nÃ©alogique courante 
	 * avec une autre fiche afin de vÃ©rifier que les deux fiches sont identiques.
	 * @param ficheGenealogique la fiche gÃ©nÃ©alogique avec laquelle on compare la fiche courante
	 * @return un boolÃ©en reprÃ©sentant le rÃ©sultat de la comparaison
	 */
	public boolean equals(FicheGenealogique ficheGenealogique){
		if (!nom.equals(ficheGenealogique.getNom())) return false;
		if (!prenom.equals(ficheGenealogique.getPrenom())) return false;
		if (dateDeNaissance!=null)
			if (!dateDeNaissance.equals(ficheGenealogique.getDateDeNaissance())) return false;
		return true;
	}
	

	/**
	 * Methode permettant de convertir une fiche gÃ©nÃ©alogique 
	 * en une chaine de caractÃ¨res prÃªte Ã  Ãªtre enregistrÃ©e dans un fichier texte.
	 * @return une chaine de caractÃ¨res
	 */
	public String convertToString() {
		String resultat="";
		if (nom!=null) resultat+=nom;
		resultat+=";";
		if (prenom!=null) resultat+=prenom;
		resultat+=";";
		if (dateDeNaissance!=null) resultat+=dateDeNaissance;
		resultat+=";";
		if (villeDeNaissance!=null) resultat+=villeDeNaissance;
		resultat+=";";
		if (deptDeNaissance!=null) resultat+=deptDeNaissance;
		resultat+=";";
		if (dateDeMariage!=null) resultat+=dateDeMariage;
		resultat+=";";
		if (villeDeMariage!=null) resultat+=villeDeMariage;
		resultat+=";";
		if (deptDeMariage!=null) resultat+=deptDeMariage;
		resultat+=";";
		if (dateDeDeces!=null) resultat+=dateDeDeces;
		resultat+=";";
		if (villeDeDeces!=null) resultat+=villeDeDeces;
		resultat+=";";
		if (deptDeDeces!=null) resultat+=deptDeDeces;
		resultat+=";";

		if (pere!=null){
			if (pere.getNom()!=null)
				resultat+= pere.getNom();
			resultat+=";";
			if (pere.getPrenom()!=null)
				resultat+= pere.getPrenom();
			resultat+=";";
		}
		else
			resultat+=";;";
		if (mere!=null) {
			if (mere.getNom()!=null)
				resultat+= mere.getNom();
			resultat+=";";
			if (mere.getPrenom()!=null)
				resultat+= mere.getPrenom();
			resultat+=";";
		}
		else
			resultat+=";;";
		resultat+="\n";
		return resultat;
	}
	
	/**
	 * MÃ©thode permettant de reprÃ©senter la fiche gÃ©nÃ©alogique sous forme d'une chaine de caractÃ¨res. 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String resultat= "Personne [nom=" + nom + ", prenom=" + prenom + ",\n  dateDeNaissance=" + dateDeNaissance + ", villeDeNaissance="+ villeDeNaissance + ", deptDeNaissance=" + deptDeNaissance 
				+ ",\n  dateDeMariage=" + dateDeMariage  + ", villeDeMariage="+ villeDeMariage + ", deptDeMariage=" + deptDeMariage
				+ ",\n  dateDeDeces=" + dateDeDeces+ ", villeDeDeces=" + villeDeDeces + ", deptDeDeces=" + deptDeDeces;
		if (pere!=null){
			if (pere.getNom()!=null)
				resultat+= ",\n  PÃ¨re = "+pere.getNom();
			resultat+=" ";
			if (pere.getPrenom()!=null)
				resultat+= pere.getPrenom();
		}
		if (mere!=null) {
			if (mere.getNom()!=null)
				resultat+= ",\n  MÃ¨re = "+mere.getNom();
			resultat+=" ";
			if (mere.getPrenom()!=null)
				resultat+= mere.getPrenom();
		}
		
		resultat+="\n]";
		
		return resultat;
	}
}
