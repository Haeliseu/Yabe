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

	// Select mot de passe oublié
	private final static String MDP_OUBLIE = "SELECT  email, pseudo FROM utilisateurs WHERE pseudo=? and email=?;";

	private final static String CONNECT = "SELECT * " + "FROM utilisateurs "
			+ "WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?;";

	private final static String NEW_MDP = "UPDATE utilisateurs SET mot_de_passe = ? WHERE pseudo=? and email=?;";

	private final static String UPDATE_USER = "UPDATE SET pseudo= ?, nom= ?, prenom= ?, email= ?, telephone= ?, rue= ?, code_postal= ?, ville= ?  FROM UTILISATEURS "
			+ "WHERE no_utilisateur= ?;";

	private final static String DOUBLONCHECK = "SELECT ? FROM UTILISATEURS WHERE ? = '?';";

	public static String SELECT_PROFIL = "SELECT no_utilisateur WHERE pseudo  = ?;";

	public UserAccount selectUser(int noUtilisateur) {

		UserAccount userAccount = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);
			pstmt.setInt(1, noUtilisateur);
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
			pstmt.setInt(10, 0);
			pstmt.setBoolean(11, false);
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

	public boolean verify(UserAccount userAccount) throws BusinessException {
		boolean exist = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CONNECT);
			pstmt.setString(1, userAccount.getPseudo());
			pstmt.setString(2, userAccount.getEmail());
			pstmt.setString(3, userAccount.getMot_de_passe());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				userAccount.setNoUtilisateur(rs.getInt("no_utilisateur"));
				userAccount.setPseudo(rs.getString("pseudo"));
				userAccount.setNom(rs.getString("nom"));
				userAccount.setPrenom(rs.getString("prenom"));
				userAccount.setEmail(rs.getString("email"));
				userAccount.setTelephone(rs.getString("telephone"));
				userAccount.setRue(rs.getString("rue"));
				userAccount.setCode_postal(rs.getString("code_postal"));
				userAccount.setVille(rs.getString("ville"));
				userAccount.setMot_de_passe(rs.getString("mot_de_passe"));
				userAccount.setCredit(rs.getInt("credit"));
				userAccount.setAdministrateur(rs.getBoolean("administrateur"));

				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public void newMdp(String mot_de_passe, String pseudo, String email) throws SQLException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(NEW_MDP);
			pstmt.setString(1, mot_de_passe);
			pstmt.setString(2, pseudo);
			pstmt.setString(3, email);
			pstmt.executeUpdate();

		}
	}

	public UserAccount updateUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int noUtilistaeur) throws SQLException {
		// UPDATE pseudo, nom, prenom, email, telephone, rue, code_postal, ville
		UserAccount userAccount = new UserAccount();
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			pstmt.setString(6, rue);
			pstmt.setString(7, codePostal);
			pstmt.setString(8, ville);
			pstmt.setInt(9, noUtilistaeur);
			pstmt.executeUpdate();

		}
		return userAccount;
	}

	public boolean checkUser(String champ, String valeur) {
		boolean exist = false;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DOUBLONCHECK);
			pstmt.setString(1, champ);
			pstmt.setString(2, champ);
			pstmt.setString(3, valeur);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public UserAccount selectProfil(String pseudo, int noUtilisateur) throws SQLException {

		UserAccount userAccount = null;
		int noUtilisateurTr;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);
			ResultSet rs = pstmt.executeQuery();
			pstmt.setString(1, "pseudo");

			while (rs.next()) {
				noUtilisateurTr = rs.getInt("no_utilisateur");
				userAccount = new UserAccount(noUtilisateur);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return userAccount;

	}

	
}
