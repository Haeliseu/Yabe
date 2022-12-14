package fr.eni.javaee.dal;

import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO{
	
	// Querys
	private static final String SQL_RECHERCHE_MOT_CLEFS = 
			"SELECT nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur "
					+ "FROM encheres "
						+ "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
						+ "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
					+ "WHERE nom_article LIKE ? "
					+ "GROUP BY nom_article, date_fin_encheres, articles_vendus.no_utilisateur;";
	
	private static final String SQL_CATEGORIE = 
			"SELECT nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur "
					+ "FROM encheres "
						+ "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
						+ "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
						+ "INNER JOIN categories ON categories.no_categorie = articles_vendus.no_categorie "
					+ "WHERE nom_article LIKE ? "
					+ "GROUP BY nom_article, date_fin_encheres, articles_vendus.no_utilisateur;";
	
	private static final String SQL_ACHATS_ENCHERES_OUVERTES =
			"SELECT nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur "
			+ "FROM encheres "
				+ "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
				+ "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
			+ "WHERE date_debut_encheres < getdate() "
				+ "AND date_fin_encheres > getdate() "
			+ "GROUP BY nom_article, date_fin_encheres, articles_vendus.no_utilisateur;";
	
	private static final String SQL_ACHATS_ENCHERES_EN_COURS =
			"SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur"
				+ "FROM encheres "
				+ "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article"
				+ "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur"
				+ "WHERE articles_vendus.no_article IN ("
														+ "SELECT no_article "
														+ "FROM encheres "
														+ "WHERE ENCHERES.no_utilisateur = ?"
														+ ") "
				+ "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur;";
	
	private static final String SQL_ACHATS_ENCHERES_REMPORTEES =
			"";
	
	private static final String SQL_VENTES_EN_COURS =
			"";
	
	private static final String SQL_VENTES_NON_DEBUTEES =
			"";
	
	private static final String SQL_VENTES_TERMINEES =
			"";
	
	
	// Methods
	@Override
	public List<ArticleVendu> encheresOuvertes() {
		return null;
	}

	@Override
	public List<ArticleVendu> mesEncheresEnCours(int idUtilisateur) {
		return null;
	}

	@Override
	public List<ArticleVendu> mesEncheresRemportees(int idUtilisateur) {
		return null;
	}

	@Override
	public List<ArticleVendu> mesVentesEnCours(int idUtilisateur) {
		return null;
	}

	@Override
	public List<ArticleVendu> ventesNonDebutees() {
		return null;
	}

	@Override
	public List<ArticleVendu> ventesTerminees() {
		return null;
	}
	
}
