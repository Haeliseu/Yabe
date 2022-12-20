package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.Enchere;
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
	
	public List<Enchere> listeEncheresByArticle(int noArticle){
		List<Enchere> encheres = null;
		BusinessException be = new BusinessException();
		
		if(noArticle > 0) {
			encheres = DAOFactory.getEncheresDAO().listeEncheresByArticle(noArticle);
		}else {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_ARTICLE_ERREUR);
		}
		
		return encheres;
	}
	
	public void nouvelleEnchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) throws SQLException {
		BusinessException be = new BusinessException();
		int nbErreurs = 0;
		Enchere maxEnchere = null;
		
		// CONTROLE ENCHERE PRECEDENTE SI <
		maxEnchere = DAOFactory.getEncheresDAO().maxEnchereByArticle(noArticle);
		
		// CHECK ARTICLE EXIST
		if(noArticle <= 0) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_ARTICLE_ERREUR);
			nbErreurs++;
		}
		
		// CHECK USER EXIST
		if(noUtilisateur <= 0) {
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
		credit = UserAccountManager.getInstance().checkCredit(noUtilisateur);
		
		if(credit < montantEnchere) {
			be.ajouterErreur(CodesErreurBLL.INSERT_ENCHERE_CREDIT_RESTANT_INSUFFISANT_ERREUR);
			nbErreurs++;
		}
		
		// CHECK IF ERROR > 0 = NO UPDATE
		if(nbErreurs == 0) {
			
			// EXECUTION DU CREDIT SUR LE COMPTE DE L UTILISATEUR ENCHERISSEUR PRECEDENT
			if(maxEnchere != null) {
				int creditEncherisseurPrecedent;
				creditEncherisseurPrecedent = UserAccountManager.getInstance().checkCredit(maxEnchere.getNoUtilisateur()) + maxEnchere.getMontantEnchere();
				DAOFactory.getUserAccountDAO().updateCredit(creditEncherisseurPrecedent, maxEnchere.getNoUtilisateur());
			}
			
			// EXECUTION DU DEBIT SUR LE COMPTE DE L UTILISATEUR
			DAOFactory.getUserAccountDAO().updateCredit((credit - montantEnchere), noUtilisateur);
			
			// EXECUTION DE L AJOUT DE LA NOUVELLE ENCHERE
			DAOFactory.getEncheresDAO().nouvelleEnchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
		}
	}
	
	public Enchere maxEnchereByArticle(int noArticle) {
		Enchere maxEnchere = null;
		
		maxEnchere = DAOFactory.getEncheresDAO().maxEnchereByArticle(noArticle);
		
		return maxEnchere;
	}
	
}
