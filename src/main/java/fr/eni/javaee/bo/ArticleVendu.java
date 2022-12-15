package fr.eni.javaee.bo;

import java.time.LocalDate;

public class ArticleVendu {
	// Fields
	private int idArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prix;
	private int prixVente;
	private String pseudoVendeur;

	// Constructors
	private ArticleVendu() {
	}

	public ArticleVendu(int idArticle, String nomArticle, LocalDate dateFinEncheres, int prix,
			String pseudoVendeur) {
		this();
		this.setIdArticle(idArticle);
		this.setNomArticle(nomArticle);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrix(prix);
		this.setPseudoVendeur(pseudoVendeur);
	}

	// Getters & Setters
	public String getPseudoVendeur() {
		return pseudoVendeur;
	}

	public void setPseudoVendeur(String pseudoVendeur) {
		this.pseudoVendeur = pseudoVendeur;
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

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prixInitial) {
		this.prix = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
}
