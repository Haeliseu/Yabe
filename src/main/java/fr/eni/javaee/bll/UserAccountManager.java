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
	
	public boolean verify(UserAccount userAccount) throws BusinessException{
		//1. vérification des données
		BusinessException be = new BusinessException();
		/*validerId(pseudo, email, be);
		validerMdp(mot_de_passe, be);
		validerMail(email, be);
		validerConn(pseudo, email, mot_de_passe, be);*/
		if(be.hasErreurs()) { //s'il y a un problème, on lève l'exception
			throw be;
		}
		//2. si on arrive ici, c'est que tout va bien
		boolean newConnectUserAccount = DAOFactory.getUserAccountDAO().verify(userAccount);
				
		return newConnectUserAccount;
	}


	private void validerMail(String email, BusinessException be) {
		// TODO Auto-generated method stub
		
	}

	private void validerMdp(String mot_de_passe, BusinessException be) {
		// TODO Auto-generated method stub
		
	}
	public UserAccount selectUser(int noUtilisateur) {
		return DAOFactory.getUserAccountDAO().selectUser(noUtilisateur);
	}
	
	public UserAccount oublieMotDePasse (String pseudo, String email) {
		return DAOFactory.getUserAccountDAO().oublieMotDePasse(pseudo, email);
	}
	
	public void newMdp(String mot_de_passe,String pseudo, String email) throws SQLException{
		DAOFactory.getUserAccountDAO().newMdp(mot_de_passe, pseudo, email);
	}

	public UserAccount updateUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int noUtilistaeur) throws SQLException {
		return DAOFactory.getUserAccountDAO().updateUser(pseudo, nom, prenom,email, telephone, rue,
			 codePostal, ville,  noUtilistaeur);
	}


}
