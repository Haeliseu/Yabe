package fr.eni.javaee.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.bo.Enchere;

public interface EncheresDAO {
	
	List<Enchere> listeEncheresByArticle(int noArticle);
	
	void nouvelleEnchere(int noUtilisateur, int noArticle, LocalDate dateEnchere, int montantEnchere);
	
	Enchere maxEnchereByArticle(int noArticle);
	
}
