package fr.eni.javaee.dal;

public class UserAccountDAOJdbcImpl implements UserAccountDAO {
	
	
	//select pour affichage 
	private final static String SELECT_ALL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEUR;"; 
	
	

}
