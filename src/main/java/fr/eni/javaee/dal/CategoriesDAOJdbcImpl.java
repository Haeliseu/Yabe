package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.javaee.bo.Categorie;

public class CategoriesDAOJdbcImpl implements CategoriesDAO {
	
	// QUERYS
	private static final String SQL_CAT_AJOUT="INSERT INTO categories (libelle) VALUES (?);";
	private static final String SQL_CAT_SUPP="DELETE FROM categories WHERE libelle = ?";
	private static final String SQL_CAT_LIST="SELECT * FROM categories";
	
	// METHODS
	@Override
	public void ajoutCategorie(String libelle) throws SQLException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SQL_CAT_AJOUT);
			pstmt.setString(1, libelle);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void suppCategorie(String libelle) {
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SQL_CAT_SUPP);
			pstmt.setString(1, libelle);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Categorie> listCategorie() {
		
		List<Categorie> categories = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_CAT_LIST);
			
			while(rs.next()) {
				Categorie cat = new Categorie(rs.getString(1));
				categories.add(cat);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

}
