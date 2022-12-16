package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;
import fr.eni.javaee.dal.DAOFactory;

public class UserAccountManager {

	private static UserAccountManager instance;

	public static UserAccountManager getInstance() {
		if (instance == null) {
			instance = new UserAccountManager();
		}
		return instance;
	}

	public boolean checkUser(String champ, String valeur) {

		boolean test = DAOFactory.getUserAccountDAO().checkUser(champ, valeur);
		return test;
	}

	private UserAccountManager() {
	}

	public UserAccount inserermdp(String pseudo, String email, String mot_de_passe, String confirmation)
			throws BusinessException {
		// 1. vérification des données
		BusinessException be = new BusinessException();
		validerMotDePasse(mot_de_passe, confirmation, be);

		if (be.hasErreurs()) { // s'il y a un problème, on lève l'exception
			throw be;
		}

		// 2. si on arrive ici, c'est que tout va bien
		UserAccount newUserAccount = new UserAccount(pseudo, email, mot_de_passe);
		DAOFactory.getUserAccountDAO().inserer(newUserAccount);

		return newUserAccount;
	}

	public UserAccount inserer(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, String confirmation) throws BusinessException {
		// 1. vérification des données
		BusinessException be = new BusinessException();
		validerPseudo(pseudo, email, be);
		validerMail(email, be);
		validerMotDePasse(mot_de_passe, confirmation, be);

		if (be.hasErreurs()) { // s'il y a un problème, on lève l'exception
			throw be;
		}

		// 2. si on arrive ici, c'est que tout va bien
		UserAccount newUserAccount = new UserAccount(pseudo, nom, prenom, email, telephone, rue, code_postal, ville,
				mot_de_passe, null, false);
		DAOFactory.getUserAccountDAO().inserer(newUserAccount);

		return newUserAccount;
	}

	public boolean verify(UserAccount userAccount, String mot_de_passe, String confirmation) throws BusinessException {
		// 1. vérification des données
		BusinessException be = new BusinessException();
		validerPseudo(userAccount.getPseudo(), userAccount.getEmail(), be);
		validerMail(userAccount.getEmail(), be);
		validerMotDePasse(mot_de_passe, confirmation, be);
		if (be.hasErreurs()) { // s'il y a un problème, on lève l'exception
			throw be;
		}
		// 2. si on arrive ici, c'est que tout va bien
		boolean newConnectUserAccount = DAOFactory.getUserAccountDAO().verify(userAccount);

		return newConnectUserAccount;
	}

	private void validerPseudo(String pseudo, String email, BusinessException be) {
		/*
		 * // TODO Auto-generated method stub
		 * if(!UserAccountManager.getInstance().checkUser("pseudo", pseudo)) {
		 * be.ajouterErreur(CodesErreurBLL.REGLE_UNIQUE_PSEUDO_ERREUR); }
		 */
	}

	private void validerMail(String email, BusinessException be) {
		/*
		 * // TODO Auto-generated method stub if
		 * (!UserAccountManager.getInstance().checkUser("email", email)) {
		 * be.ajouterErreur(CodesErreurBLL.REGLE_UNIQUE_MAIL_ERREUR); }
		 */
	}

	private void validerMotDePasse(String mot_de_passe, String confirmation, BusinessException be) {
		if (!mot_de_passe.equals(confirmation)) {
			be.ajouterErreur(CodesErreurBLL.REGLE_DIFFERENT_MDP_ERREUR);
		}
	}

	public UserAccount selectUser(int noUtilisateur) {
		return DAOFactory.getUserAccountDAO().selectUser(noUtilisateur);
	}

	public UserAccount oublieMotDePasse(String pseudo, String email) {
		return DAOFactory.getUserAccountDAO().oublieMotDePasse(pseudo, email);
	}

	public void newMdp(String mot_de_passe, String pseudo, String email) throws SQLException {
		DAOFactory.getUserAccountDAO().newMdp(mot_de_passe, pseudo, email);
	}

	public UserAccount updateUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int noUtilistaeur) throws SQLException {
		return DAOFactory.getUserAccountDAO().updateUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				noUtilistaeur);
	}

	public int selectProfil(String pseudo) throws SQLException {
		return DAOFactory.getUserAccountDAO().selectProfil(pseudo);

	}

	public void  supprimer(int noUtilisateur) throws SQLException {
		DAOFactory.getUserAccountDAO().supprimer(noUtilisateur);
		
	}
}
