package fr.eni.javaee.dal;

public class DAOFactory {
	
	public static UserAccountDAO getUserAccountDAO() {
		return new UserAccountDAOJdbcImpl();
	}
	
}
