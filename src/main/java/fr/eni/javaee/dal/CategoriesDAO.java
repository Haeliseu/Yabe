package fr.eni.javaee.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.bo.Categorie;

public interface CategoriesDAO {
	
	public void ajoutCategorie(String libelle) throws SQLException;
	public void suppCategorie(String libelle) throws SQLException;
	public List<Categorie> listCategorie() throws SQLException;
	public Categorie rechCategorie(Categorie categorie) throws SQLException;
}
