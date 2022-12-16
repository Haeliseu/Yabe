package fr.eni.javaee.dal;

public class DAOFactory {
	
	public static UserAccountDAO getUserAccountDAO() {
		return new UserAccountDAOJdbcImpl();
	}
	
	public static ArticlesVendusDAO getArticleVenduDAO() {
		return new ArticlesVendusDAOJdbcImpl();
	}
	
	public static CategoriesDAO getCategoriesDAO() {
		return new CategoriesDAOJdbcImpl();
	}
	
}
