package fr.eni.javaee.dal;

import java.sql.SQLException;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public interface UserAccountDAO {

		void inserer(UserAccount userAccount) throws BusinessException, SQLException;
		
		public UserAccount selectUser(int noUtilisateur);
		
		public void supprimer (UserAccount userAccount)throws SQLException;
		
		public UserAccount oublieMotDePasse (String pseudo,String email);
		
		void connect(UserAccount newConnectUserAccount)throws BusinessException, SQLException;
		
		public void newMdp(String motdepasse,String pseudo, String email) throws SQLException;
}
