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
	private int idVendeur;
	private String pseudoVendeur;
	private String categorie;
	private String rue;
	private String codePostal;
	private String ville;

	// Constructors
	private ArticleVendu() {
	}
	
	// Constructor insertArticle
	public ArticleVendu(int idArticle, String nomArticle, LocalDate dateFinEncheres, int prix,
			int idVendeur, String pseudoVendeur) {
		this();
		this.setIdArticle(idArticle);
		this.setNomArticle(nomArticle);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrix(prix);
		this.setIdVendeur(idVendeur);
		this.setPseudoVendeur(pseudoVendeur);
	}
	
	// Constructor afficherArticle
	public ArticleVendu(String nom, String description, String categorie,
			int prix, LocalDate debutEncheres, LocalDate finEncheres, 
			String retraitRue, String retraitCP, String retraitVille) {
		this.setNomArticle(nom);
		this.setDescription(description);
		this.setCategorie(categorie);
		this.setPrix(prix);
		this.setDateDebutEncheres(debutEncheres);
		this.setDateFinEncheres(finEncheres);
		this.setRue(retraitRue);
		this.setCodePostal(retraitCP);
		this.setVille(retraitVille);
		
	}

	// Getters & Setters
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public int getIdVendeur() {
		return idVendeur;
	}

	public void setIdVendeur(int idVendeur) {
		this.idVendeur = idVendeur;
	}
	
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
