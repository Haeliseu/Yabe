package fr.eni.javaee.dal;

import java.sql.SQLException;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public interface UserAccountDAO {

		void inserer(UserAccount userAccount) throws BusinessException, SQLException;
		
		public UserAccount selectUser(int noUtilisateur);
		
		public void supprimer (UserAccount userAccount)throws SQLException;
		
		public UserAccount oublieMotDePasse (String pseudo,String email);
		
		public boolean verify(UserAccount userAccount) throws BusinessException;
		
		public void newMdp(String mot_de_passe,String pseudo, String email) throws SQLException;

	
}
