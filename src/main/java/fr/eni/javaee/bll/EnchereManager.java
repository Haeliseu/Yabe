package fr.eni.javaee.bll;

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
		
		
		
		
		if(noArticle > 0) {
			encheres = DAOFactory.getEncheresDAO().listeEncheresByArticle(noArticle);
		}
		
		return encheres;
	}
	
	public void nouvelleEnchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) {
		BusinessException be = new BusinessException();
		int maxEnchere = 0;
		
		// CONTROLE ENCHERE PRECEDENTE SI <
		maxEnchere = DAOFactory.getEncheresDAO().maxEnchereByArticle(noArticle);
				
		if(maxEnchere > montantEnchere) {
			be.ajouterErreur(CodesErreurBLL.INSERT_VENTE_ID_ARTICLE_ERREUR);
		}
				
		// CONTROLE SI DEBIT POSSIBLE SUR LE CREDIT RESTANT
		
		
		// DEBIT SUR LE CREDIT DISPO SUR LE COMPTE
		
		
		if(be==null) {
			DAOFactory.getEncheresDAO().nouvelleEnchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
		}
	}
	
	public int maxEnchereByArticle(int noArticle) {
		int montantMaxEnchere = 0;
		
		
		
		return montantMaxEnchere;
	}
	
}
