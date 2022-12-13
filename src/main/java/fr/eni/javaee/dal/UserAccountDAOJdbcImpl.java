package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.javaee.bo.UserAccount;

public class UserAccountDAOJdbcImpl implements UserAccountDAO {

	// select pour affichage
	private final static String SELECT_USER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEUR "
			+ "WHERE no_utilisateur =?;";
//
	private final static String ADD_USER = "INSERT INTO utilisateurs \r\n"
			+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)\r\n"
			+ "VALUES\r\n"
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private final static String DELETE_USER = "DELETE FROM utilisateurs WHERE no_utilisateur = ?;";
			
	
	public UserAccount selectUser() {
		UserAccount userAccount = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);

			ResultSet rs = pstmt.executeQuery(SELECT_USER);

			if (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				userAccount = new UserAccount(pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return userAccount;
	}
	
	public void inserer (UserAccount userAccount) throws SQLException {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			
			PreparedStatement pstmt = cnx.prepareStatement(ADD_USER);			
			pstmt.setString(1,userAccount.getPseudo());
			pstmt.setString(2,userAccount.getNom());
			pstmt.setString(3,userAccount.getPrenom());
			pstmt.setString(4,userAccount.getEmail());
			pstmt.setString(5,userAccount.getTelephone());
			pstmt.setString(6,userAccount.getRue());
			pstmt.setString(7,userAccount.getCode_postal());
			pstmt.setString(8,userAccount.getVille());
			pstmt.setInt(9,userAccount.getCredit());
			pstmt.setBoolean(10, userAccount.isAdministrateur());
		// Verifier éxections dans la catch
			pstmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
