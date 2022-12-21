package fr.eni.javaee.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.UserAccount;

public interface EncheresDAO {
	
	List<Enchere> listeEncheresByArticle(ArticleVendu article);
	
	void nouvelleEnchere(UserAccount userAccount, ArticleVendu article, LocalDate dateEnchere, int montantEnchere);
	
	Enchere maxEnchereByArticle(ArticleVendu article);
	
}
