package fr.eni.javaee.dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;

public interface ArticlesVendusDAO {

	public List<ArticleVendu> listeArticles(String motsClefs, String categorie, String radio, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException;
	
	public void insertVente(String nomArticle, String description, 
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, 
			int prixInitial, int prixVente, int noUtilisateur, int noCategorie) throws SQLException;
}
