package fr.eni.javaee.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;

public interface ArticlesVendusDAO {

	public List<ArticleVendu> listeArticles(String motsClefs, String categorie, String radio, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException;
}
