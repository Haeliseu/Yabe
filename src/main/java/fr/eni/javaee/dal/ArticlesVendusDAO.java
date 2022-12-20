package fr.eni.javaee.dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.UserAccount;

public interface ArticlesVendusDAO {
	
	public ArticleVendu afficherArticle(ArticleVendu article) throws SQLException;

	public List<ArticleVendu> listeArticles(String motsClefs, Categorie categorie, String radio, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException;
	
	public void insertVente(
			// DATA ARTICLE
			String nomArticle, String description, Categorie categorie,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, 
			int prixInitial, 
			// DATA USER
			UserAccount useraccount, 
			// DATA RETRAIT
			String rue, int cp, String ville) throws SQLException;
}
