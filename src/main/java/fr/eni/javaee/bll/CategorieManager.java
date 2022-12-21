package fr.eni.javaee.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.dal.DAOFactory;

public class CategorieManager {
	private static CategorieManager instance;

	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}

	public CategorieManager() {
	}
	public Categorie rechCategorie(Categorie categorieR) throws SQLException{
		Categorie categorie =null;
		categorie = DAOFactory.getCategoriesDAO().rechCategorie(categorieR);
		return categorie;
	}
	public void ajoutCategorie(String libelle) throws SQLException{
		DAOFactory.getCategoriesDAO().ajoutCategorie(libelle);
	}
	public void suppCategorie(String libelle) throws SQLException{
		DAOFactory.getCategoriesDAO().suppCategorie(libelle);
	}
	public List<Categorie> listCategorie() throws SQLException{
		List<Categorie> categories = null;
		categories = DAOFactory.getCategoriesDAO().listCategorie();
		return categories;
	}
}
