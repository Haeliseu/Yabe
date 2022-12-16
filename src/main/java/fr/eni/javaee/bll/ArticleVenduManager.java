package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.BusinessException;
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

	public ArticleVenduManager() {
	}

	public List<ArticleVendu> listeArticles(String motsClefs, String categorie, String radio, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException {
		
		BusinessException be = new BusinessException();
		
		
		List<ArticleVendu> articles = DAOFactory.getArticleVenduDAO().listeArticles(motsClefs, categorie, radio, achatsOuverts,
				achatsEncheresEnCours, achatsEncheresRemportees, ventesEnCours, ventesNonDebutees, ventesTerminees, idUser);
		return articles;
	}
	
	
	
	public void insertVente(
			String nomArticle, String description, int categorie,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, 
			int prixInitial, int noUtilisateur, 
			String retraitRue, int retraitCP, String retraitVille) {
		
		try {
			DAOFactory.getArticleVenduDAO().insertVente(
					nomArticle, description, categorie, 
					dateDebutEncheres, dateFinEncheres, prixInitial, 
					noUtilisateur, 
					retraitRue, retraitCP, retraitVille);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
