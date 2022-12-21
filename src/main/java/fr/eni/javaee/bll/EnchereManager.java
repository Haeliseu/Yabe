package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.UserAccount;
import fr.eni.javaee.dal.DAOFactory;

public class EnchereManager {
	private static EnchereManager instance;

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	public EnchereManager() {
	}
	
	public List<Enchere> listeEncheresByArticle(ArticleVendu article){
		List<Enchere> encheres = null;
		BusinessException be = new BusinessException();
		
		if(article.getIdArticle() > 0) {
			encheres = DAOFactory.getEncheresDAO().listeEncheresByArticle(article);
		}else {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_ARTICLE_ERREUR);
		}
		
		return encheres;
	}
	
	public void nouvelleEnchere(UserAccount userAccount, ArticleVendu article, LocalDate dateEnchere, int montantEnchere) throws SQLException {
		BusinessException be = new BusinessException();
		int nbErreurs = 0;
		Enchere maxEnchere = null;
		
		// CONTROLE ENCHERE PRECEDENTE SI <
		maxEnchere = DAOFactory.getEncheresDAO().maxEnchereByArticle(article);
		
		// CHECK ARTICLE EXIST
		if(article.getIdArticle() <= 0) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_ARTICLE_ERREUR);
			nbErreurs++;
		}
		
		// CHECK USER EXIST
		if(userAccount.getNoUtilisateur() <= 0) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_USER_ERREUR);
			nbErreurs++;
		}
		
		// CHECK MAXENCHERE EXIST + MAXENCHERE > ENCHERE
		if(maxEnchere != null && maxEnchere.getMontantEnchere() > montantEnchere) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_MONTANT_ERREUR);
			nbErreurs++;
		}
				
		// CHECK DEBIT IS POSSIBLE
		int credit = 0;
		credit = UserAccountManager.getInstance().checkCredit(userAccount);
		
		if(credit < montantEnchere) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_CREDIT_RESTANT_INSUFFISANT_ERREUR);
			nbErreurs++;
		}
		
		// CHECK IF ERROR > 0 = NO UPDATE
		if(nbErreurs == 0) {
			
			// EXECUTION DU CREDIT SUR LE COMPTE DE L UTILISATEUR ENCHERISSEUR PRECEDENT
			if(maxEnchere != null) {
				int creditEncherisseurPrecedent;
				creditEncherisseurPrecedent = UserAccountManager.getInstance().checkCredit(maxEnchere.getUserAccount());
				DAOFactory.getUserAccountDAO().updateCredit(creditEncherisseurPrecedent, maxEnchere.getUserAccount());
			}
			
			// EXECUTION DU DEBIT SUR LE COMPTE DE L UTILISATEUR
			DAOFactory.getUserAccountDAO().updateCredit((credit - montantEnchere), userAccount);
			
			// EXECUTION DE L AJOUT DE LA NOUVELLE ENCHERE
			DAOFactory.getEncheresDAO().nouvelleEnchere(userAccount, article, dateEnchere, montantEnchere);
		}
	}
	
	public Enchere maxEnchereByArticle(ArticleVendu article) {
		Enchere maxEnchere = null;
		
		maxEnchere = DAOFactory.getEncheresDAO().maxEnchereByArticle(article);
		
		return maxEnchere;
	}
	
}
