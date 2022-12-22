package fr.eni.javaee.bo;

import java.time.LocalDate;

public class Enchere {
	// Fields
	private UserAccount userAccount;
	private ArticleVendu article;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	//Constructors
	private Enchere() {}
	
	public Enchere(int montantEnchere) {
		this.setMontantEnchere(montantEnchere);
	}
	
	public Enchere(UserAccount userAccount, int montantEnchere) {
		this.setUserAccount(userAccount);
		this.setMontantEnchere(montantEnchere);
	}
	
	public Enchere(UserAccount userAccount, ArticleVendu article, LocalDate dateEnchere, int montantEnchere) {
		this.setUserAccount(userAccount);
		this.setArticle(article);
		this.setDateEnchere(dateEnchere);
		this.setMontantEnchere(montantEnchere);
	}
	
	
	// Getters & Setters
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	
}
