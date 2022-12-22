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
	private UserAccount userAccount;
	private Categorie categorie;
	private String rue;
	private String codePostal;
	private String ville;

	// Constructors
	private ArticleVendu() {
	}
	
	public ArticleVendu(int idArticle) {
		this.setIdArticle(idArticle);
	}
	
	// Constructor insertArticle
	public ArticleVendu(int idArticle, String nomArticle, LocalDate dateFinEncheres, int prix,
			UserAccount userAccount) {
		this.setIdArticle(idArticle);
		this.setNomArticle(nomArticle);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrix(prix);
		this.setUserAccount(userAccount);
	}
	
	// Constructor afficherArticle
	public ArticleVendu(int idArticle, String nom, String description, Categorie categorie,
			int prix, LocalDate debutEncheres, LocalDate finEncheres, UserAccount userAccount,
			String retraitRue, String retraitCP, String retraitVille) {
		this.setIdArticle(idArticle);
		this.setNomArticle(nom);
		this.setDescription(description);
		this.setCategorie(categorie);
		this.setPrix(prix);
		this.setDateDebutEncheres(debutEncheres);
		this.setDateFinEncheres(finEncheres);
		this.setUserAccount(userAccount);
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
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
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
