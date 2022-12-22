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
			+ "(?, ?, getdate(), ?);";
	private static final String SQL_MAX_ENCHERE = "SELECT MAX(montant_enchere) as montant_enchere FROM encheres WHERE no_article = ? ;";
	private static final String SQL_ID_MAX_ENCHERE = "SELECT no_utilisateur FROM encheres WHERE no_article = ? AND montant_enchere = ? ;";
	
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

	public void nouvelleEnchere(UserAccount userAccount, ArticleVendu article, int montantEnchere) {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_NOUVELLE_ENCHERE);
			
			pstmt.setInt(1, userAccount.getNoUtilisateur());
			pstmt.setInt(2, article.getIdArticle());
			pstmt.setInt(3, montantEnchere);
			
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
			
			if(rs.next() ) {
				
				maxEnchere = new Enchere(rs.getInt("montant_enchere"));
			}
			
			pstmt = cnx.prepareStatement(SQL_ID_MAX_ENCHERE);
			pstmt.setInt(1, article.getIdArticle());
			pstmt.setInt(2, maxEnchere.getMontantEnchere());
			rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				userAccount = new UserAccount(rs.getInt("no_utilisateur"));
			}
			
			maxEnchere.setUserAccount(userAccount);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return maxEnchere;
	}
	
}
