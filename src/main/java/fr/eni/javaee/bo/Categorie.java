package fr.eni.javaee.bo;

public class Categorie {
	private int idCategorie;
	private String libelle;
	
	public Categorie(String libelle){
		this.setLibelle(libelle);
	}
	public Categorie(int idCategorie, String libelle){
		this.setIdCategorie(idCategorie);
		this.setLibelle(libelle);
	}

	
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
