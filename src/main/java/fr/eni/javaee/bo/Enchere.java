package fr.eni.javaee.bo;

import java.time.LocalDate;

public class Enchere {
	// Fields
	private int noUtilisateur;
	private int noArticle;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	//Constructors
	private Enchere() {}
	
	public Enchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) {
		this.setNoUtilisateur(noUtilisateur);
		this.setNoArticle(noArticle);
		this.setDateEnchere(dateEnchere);
		this.setMontantEnchere(montantEnchere);
	}
	
	// Getters & Setters
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
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
