package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;
import fr.eni.javaee.dal.DAOFactory;


public class UserAccountManager {
	
	private static UserAccountManager instance;
	
	public static UserAccountManager getInstance() {
		if(instance == null) {
			instance = new UserAccountManager();
		}
		return instance;
	}

	private UserAccountManager() {}
	
	public UserAccount inserer(String pseudo, String nom, String prenom, String email, String telephone, String rue, String code_postal, String ville, String mot_de_passe) throws BusinessException, SQLException {
		//1. vérification des données
		BusinessException be = new BusinessException();
		validerMdp(mot_de_passe, be);
		validerMail(email, be);
		if(be.hasErreurs()) { //s'il y a un problème, on lève l'exception
			throw be;
		}
		
		//2. si on arrive ici, c'est que tout va bien
		UserAccount newUserAccount = new UserAccount(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, null, false);
		DAOFactory.getUserAccountDAO().inserer(newUserAccount);
		
		return newUserAccount;
	}
	
	public UserAccount connect(String pseudo, String email, String mot_de_passe) throws BusinessException, SQLException {
		//1. vérification des données
		BusinessException be = new BusinessException();
		validerMdp(mot_de_passe, be);
		validerMail(email, be);
		if(be.hasErreurs()) { //s'il y a un problème, on lève l'exception
			throw be;
		}
		//2. si on arrive ici, c'est que tout va bien
		UserAccount newConnectUserAccount = new UserAccount(pseudo, email, mot_de_passe);
		DAOFactory.getUserAccountDAO().connect(newConnectUserAccount);
				
		return newConnectUserAccount;
	}


	private void validerMail(String email, BusinessException be) {
		// TODO Auto-generated method stub
		
	}

	private void validerMdp(String mot_de_passe, BusinessException be) {
		// TODO Auto-generated method stub
		
	}
	public UserAccount selectUser() {
		return DAOFactory.getUserAccountDAO().selectUser();
	}
	
	public UserAccount oublieMotDePasse () {
		return DAOFactory.getUserAccountDAO().oublieMotDePasse();
	}

}
