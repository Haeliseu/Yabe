package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.dal.DAOFactory;

public class ArticleVenduManager {
	private static ArticleVenduManager instance;

	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}

	private ArticleVenduManager() {
	}

	public List<ArticleVendu> motClefs(String[] motsClefs, String categorie, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException {

		List<ArticleVendu> articles = DAOFactory.getArticleVenduDAO().listeArticles(motsClefs, categorie, achatsOuverts,
				achatsEncheresEnCours, achatsEncheresRemportees, ventesEnCours, ventesNonDebutees, ventesTerminees, idUser);
		return articles;
	}

}
