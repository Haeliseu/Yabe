package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.UserAccount;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	// QUERYS
	private static final String SQL_LISTE_ENCHERES = "SELECT * FROM encheres WHERE no_article = ? ;";
	private static final String SQL_NOUVELLE_ENCHERE = 
			"INSERT INTO encheres "
			+ "(no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES "
			+ "(?, ?, ?, ?);";
	private static final String SQL_MAX_ENCHERE = "SELECT MAX(montant_enchere), no_utilisateur FROM encheres WHERE no_article = ? GROUP BY no_utilisateur;";
	
	// METHODS
	public List<Enchere> listeEncheresByArticle(ArticleVendu article) {
		List<Enchere> encheres = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_LISTE_ENCHERES);
			pstmt.setInt(1, article.getIdArticle());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserAccount userAccount = new UserAccount(rs.getInt("no_utilisateur"));
				ArticleVendu newArticle = new ArticleVendu(rs.getInt("no_article"));
				Enchere enchere = new Enchere(
						userAccount,
						newArticle,
						rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere")
						);
				encheres.add(enchere);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return encheres;
	}

	public void nouvelleEnchere(UserAccount userAccount, ArticleVendu article, LocalDate dateEnchere, int montantEnchere) {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_NOUVELLE_ENCHERE);
			
			pstmt.setInt(1, userAccount.getNoUtilisateur());
			pstmt.setInt(2, article.getIdArticle());
			pstmt.setDate(3, Date.valueOf(dateEnchere));
			pstmt.setInt(4, montantEnchere);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Enchere maxEnchereByArticle(ArticleVendu article) {
		Enchere maxEnchere = null;
		UserAccount userAccount = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_MAX_ENCHERE);
			pstmt.setInt(1, article.getIdArticle());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getInt(1) > 0) {
				userAccount = new UserAccount(rs.getInt("no_utilisateur"));
				maxEnchere = new Enchere(userAccount, rs.getInt("montant_enchere"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return maxEnchere;
	}
	
}
