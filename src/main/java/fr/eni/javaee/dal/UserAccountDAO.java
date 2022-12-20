package fr.eni.javaee.dal;

import java.sql.SQLException;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bo.UserAccount;

public interface UserAccountDAO {

		
		
		void inserer(UserAccount userAccount) throws BusinessException;
		
		public UserAccount selectUser(int noUtilisateur);
		
		public void supprimer (int idUser)throws SQLException;
		
		public UserAccount oublieMotDePasse (String pseudo,String email);
		
		public boolean verify(UserAccount userAccount) throws BusinessException;
		
		public void newMdp(String mot_de_passe,String pseudo, String email) throws SQLException;

		UserAccount updateUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
				String codePostal, String ville, int noUtilistaeur) throws SQLException;
		public boolean checkUser(String champ, String valeur);
		
		public int selectProfil(String pseudo) throws SQLException;

		public int checkCredit(int noUtilisateur) throws SQLException;

}
