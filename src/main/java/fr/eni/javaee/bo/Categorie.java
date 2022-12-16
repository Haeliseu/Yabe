package fr.eni.javaee.bo;

public class Categorie {
	private String libelle;
	
	public Categorie(String libelle){
		this.setLibelle(libelle);
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
