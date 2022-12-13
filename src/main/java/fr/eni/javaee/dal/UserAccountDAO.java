package fr.eni.javaee.dal;

import java.sql.SQLException;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public interface UserAccountDAO {

	void inserer(UserAccount useraccount) throws BusinessException, SQLException;
}
