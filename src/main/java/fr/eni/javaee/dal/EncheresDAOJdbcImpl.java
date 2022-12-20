package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.bo.Enchere;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	// QUERYS
	private static final String SQL_LISTE_ENCHERES = "SELECT * FROM encheres WHERE no_article = ? ;";
	private static final String SQL_NOUVELLE_ENCHERE = 
			"INSERT INTO encheres "
			+ "(no_utilisateur, no_article, date_enchere, montant_enchere) "
			+ "VALUES "
			+ "(?, ?, ?, ?);";
	private static final String SQL_MAX_ENCHERE = "SELECT MAX(montant_enchere) FROM encheres WHERE no_article = ? ;";
	
	// METHODS
	public List<Enchere> listeEncheresByArticle(int noArticle) {
		List<Enchere> encheres = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_LISTE_ENCHERES);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enchere enchere = new Enchere(
						rs.getInt("no_utilisateur"),
						rs.getInt("no_article"),
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

	public void nouvelleEnchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere) {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_NOUVELLE_ENCHERE);
			
			pstmt.setInt(1, noUtilisateur);
			pstmt.setInt(2, noArticle);
			pstmt.setDate(3, Date.valueOf(dateEnchere));
			pstmt.setInt(4, montantEnchere);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public int maxEnchereByArticle(int noArticle) {
		int montantMaxEncheres = 0;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_MAX_ENCHERE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getInt(1) > 0) {
				montantMaxEncheres = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return montantMaxEncheres;
	}
	
}
