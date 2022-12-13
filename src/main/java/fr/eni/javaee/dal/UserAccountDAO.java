package fr.eni.javaee.dal;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public interface UserAccountDAO {

	void insert(UserAccount useraccount) throws BusinessException;
}
