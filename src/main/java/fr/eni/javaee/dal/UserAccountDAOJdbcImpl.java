package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.javaee.bo.UserAccount;

public class UserAccountDAOJdbcImpl implements UserAccountDAO {
	
	
	//select pour affichage 
	private final static String SELECT_USER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEUR "
												+ "WHERE no_utilisateur =?;"; 
												
	
	public UserAccount selectUser() {
			UserAccount userAccount = new UserAccount();
		
			try(Connection cnx= ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);
			
			ResultSet rs =pstmt.executeQuery(SELECT_USER);
				
			if (rs.next()) {
			String pseudo = rs.getString("pseudo");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			String rue = rs.getString("rue");
			String codePostal = rs.getString("code_postal");
			String ville = rs.getString("ville");
			UserAccount userAccount = new UserAccount (pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		
		}
		
		return userAccount;
}

}
