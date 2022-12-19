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
	
	public ArticleVendu afficherArticle(int idArticle) throws SQLException {
		BusinessException be = new BusinessException();

		ArticleVendu articleVendu = null;
		
		if(idArticle > 0) {
			articleVendu = DAOFactory.getArticleVenduDAO().afficherArticle(idArticle);
		}else {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_ID_ARTICLE_ERREUR);
		}
		
		return articleVendu;
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
			String retraitRue, int retraitCP, String retraitVille) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		if(nomArticle == null || nomArticle.isBlank()) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_NOM_ARTICLE_ERREUR);
		}
		
		if(description == null || description.isBlank()) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_DESCRIPTION_ARTICLE_ERREUR);
		}
		
		if(categorie == 0) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_CATEGORIE_ARTICLE_ERREUR);
		}
		
		if(dateDebutEncheres == null) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_DATE_DEBUT_ENCHERES_ERREUR);
		}
		
		if(dateFinEncheres == null) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_DATE_FIN_ENCHERES_ERREUR);
		}
		
		if(prixInitial < 0) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_PRIX_INITIAL_ERREUR);
		}
		
		if(DAOFactory.getUserAccountDAO().selectUser(noUtilisateur)==null) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_USER_ERREUR);
		}
		
		if(retraitRue==null||retraitRue.isBlank()) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_RUE_ERREUR);
		}
		
		if(retraitVille==null||retraitVille.isBlank()) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_VILLE_ERREUR);
		}
		
		if(retraitCP < 1000 || retraitCP > 98000) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_CODE_POSTAL_ERREUR);
		}
		
		if(be.hasErreurs()) {
			throw be;
		}
		
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
