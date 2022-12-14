package fr.eni.javaee.dal;

import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;

public interface ArticlesVendusDAO {
	
	public List<ArticleVendu> encheresOuvertes();
	public List<ArticleVendu> mesEncheresEnCours(int idUtilisateur);
	public List<ArticleVendu> mesEncheresRemportees(int idUtilisateur);
	
	public List<ArticleVendu> mesVentesEnCours(int idUtilisateur);
	public List<ArticleVendu> ventesNonDebutees();
	public List<ArticleVendu> ventesTerminees();
}
