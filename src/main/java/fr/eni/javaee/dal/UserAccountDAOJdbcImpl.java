package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public class UserAccountDAOJdbcImpl implements UserAccountDAO {

	// select pour affichage
	private final static String SELECT_USER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville  FROM UTILISATEURS "
			+ "WHERE no_utilisateur =?;";
//
	private final static String ADD_USER = "INSERT INTO utilisateurs"
			+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
			+ "VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private final static String DELETE_USER = "DELETE FROM utilisateurs WHERE no_utilisateur = ?;";

	private final static String MOT_DE_PASSE = "UPDATE utilisateurs SET mot_de_passe = ? WHERE no_utilisateur = ?;";

	// Select mot de passe oublié
	private final static String MDP_OUBLIE = "SELECT  email, pseudo FROM utilisateurs WHERE pseudo=? and email=?;";

	private final static String CONNECT = "SELECT * "
			+ "FROM utilisateurs"
			+ "WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?;";
	
	private final static String NEW_MDP ="UPDATE utilisateurs SET mot_de_passe = ? WHERE pseudo=? and email=?;";

	public UserAccount selectUser(int noUtilisateur) {
		
		UserAccount userAccount = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				userAccount = new UserAccount(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return userAccount;
	}

	public void inserer(UserAccount userAccount) throws SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(ADD_USER);
			pstmt.setString(1, userAccount.getPseudo());
			pstmt.setString(2, userAccount.getNom());
			pstmt.setString(3, userAccount.getPrenom());
			pstmt.setString(4, userAccount.getEmail());
			pstmt.setString(5, userAccount.getTelephone());
			pstmt.setString(6, userAccount.getRue());
			pstmt.setString(7, userAccount.getCode_postal());
			pstmt.setString(8, userAccount.getVille());
			pstmt.setString(9, userAccount.getMot_de_passe());
			pstmt.setInt(9, 0);
			pstmt.setBoolean(10, false);
			// Verifier éxections dans la catch
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void supprimer(UserAccount userAccount) throws SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, userAccount.getNoUtilisateur());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public UserAccount oublieMotDePasse(String pseudo, String email) {
		UserAccount userAccount = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(MDP_OUBLIE);

			pstmt.setString(1, pseudo);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String pseudoDql = rs.getString("pseudo");
				String emailDql = rs.getString("email");

				userAccount = new UserAccount(pseudo, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccount;
	}

	@Override
	public void connect(UserAccount newConnectUserAccount) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CONNECT);
			pstmt.setString(1, newConnectUserAccount.getPseudo());
			pstmt.setString(2, newConnectUserAccount.getEmail());
			pstmt.setString(3, newConnectUserAccount.getMot_de_passe());
		}
	}
	
	
	public  newMdp(String motdepasse,String pseudo, String email) throws SQLException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(NEW_MDP);
			pstmt.setString(1,motdepasse);
			pstmt.setString(2, pseudo);
			pstmt.setString(3,email);
			pstmt.executeUpdate();
								
		}
	}
	
}

