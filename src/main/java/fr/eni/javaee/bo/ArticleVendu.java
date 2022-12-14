package fr.eni.javaee.bo;

import java.time.LocalDate;

public class ArticleVendu {
	// Fields
	private int idArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int idVendeur;
	
	// Constructors
	private ArticleVendu() {}
	
	public ArticleVendu(String nomArticle, LocalDate dateFinEncheres, int prixInitial, int idVendeur) {
		this.setNomArticle(nomArticle);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrixInitial(prixInitial);
		this.setIdVendeur(idVendeur);
	}
	
	// Getters & Setters
	public int getIdVendeur() {
		return idVendeur;
	}
	public void setIdVendeur(int idVendeur) {
		this.idVendeur = idVendeur;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
}
