package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {

	// Querys - Liste des requêtes SQL

	private static final String SQL_SELECT = " SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur, pseudo ";
	private static final String SQL_FROM = " FROM encheres "
			+ " INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
			+ " INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
			+ " INNER JOIN categories ON articles_vendus.no_categorie = categories.no_categorie ";

	private static final String SQL_WHERE = " WHERE ";
	private static final String SQL_AND = " AND ";

	private static final String SQL_MOT_CLEF = " nom_article LIKE ";
	private static final String SQL_CATEGORIE = " libelle = ";
	private static final String SQL_ACHATS_ENCHERES_OUVERTES = " date_debut_encheres < getdate() AND date_fin_encheres >= getdate() ";
	private static final String SQL_ACHATS_MES_ENCHERES_EN_COURS = " articles_vendus.no_article IN (SELECT no_article FROM encheres WHERE ENCHERES.no_utilisateur = ";
	private static final String SQL_ACHATS_MES_ENCHERES_REMPORTEES = " date_fin_encheres < getdate() AND articles_vendus.no_article IN (SELECT no_article FROM encheres WHERE ENCHERES.no_utilisateur = ";
	private static final String SQL_MES_VENTES_EN_COURS = SQL_ACHATS_ENCHERES_OUVERTES
			+ " articles_vendus.no_utilisateur = ";
	private static final String SQL_VENTES_NON_DEBUTEES = " articles_vendus.date_debut_encheres > getdate() ";
	private static final String SQL_VENTES_TERMINEES = " articles_vendus.date_fin_encheres > getdate() ";

	private static final String SQL_GROUP_BY = "GROUP BY articles_vendus.no_article, nom_article, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur, pseudo;";

	/*
	 * private static final String SQL_RECHERCHE_MOT_CLEFS = // = RECHERCHE PAR MOTS
	 * CLEFS = Champs input sous "Filtres" sur accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur, pseudo "
	 * + "FROM encheres " +
	 * "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
	 * +
	 * "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
	 * + " ? " +
	 * "GROUP BY articles_vendus.no_article, nom_article, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur, pseudo;"
	 * ; private static final String SQL_CATEGORIE = // = RECHERCHE PAR CATEGORIE =
	 * ListeBox sous "Categories" sur accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur, pseudo "
	 * + "FROM encheres " +
	 * "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
	 * +
	 * "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
	 * +
	 * "INNER JOIN categories ON categories.no_categorie = articles_vendus.no_categorie "
	 * + "WHERE libelle = ? " +
	 * "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur, pseudo;"
	 * ; private static final String SQL_ACHATS_ENCHERES_OUVERTES = // = RECHERCHE
	 * ACHATS ENCHERES OUVERTES = 1ère checkbox sous "Achats" sur accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur, pseudo "
	 * + "FROM encheres " +
	 * "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
	 * +
	 * "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
	 * + "WHERE date_debut_encheres < getdate() " +
	 * "AND date_fin_encheres >= getdate() " +
	 * "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur, pseudo;"
	 * ; private static final String SQL_ACHATS_MES_ENCHERES_EN_COURS = // =
	 * RECHERCHE ACHATS MES ENCHERES EN COURS = 2ème checkbox sous "Achats" sur
	 * accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur"
	 * + "FROM encheres " +
	 * "INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article"
	 * +
	 * "INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur"
	 * + "WHERE  date_debut_encheres < getdate() " +
	 * "AND date_fin_encheres > getdate() " + "AND articles_vendus.no_article IN ("
	 * + "SELECT no_article " + "FROM encheres " +
	 * "WHERE ENCHERES.no_utilisateur = ?" // SEUL ID UTILISATEUR + ") " +
	 * "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur;"
	 * ; private static final String SQL_ACHATS_MES_ENCHERES_REMPORTEES = // =
	 * RECHERCHE ACHATS MES ENCHERES REMPORTEES = 3ème checkbox sous "Achats" sur
	 * accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur "
	 * + "FROM encheres " +
	 * "		INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
	 * +
	 * "    	INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
	 * + "WHERE date_fin_encheres < getdate() " +
	 * "		AND articles_vendus.no_article IN ( " +
	 * "									SELECT no_article " +
	 * "									FROM encheres " +
	 * "									WHERE ENCHERES.no_utilisateur = ? " //
	 * SEUL ID UTILISATEUR + "									) " +
	 * "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur;"
	 * ; private static final String SQL_MES_VENTES_EN_COURS = // = RECHERCHE MES
	 * VENTES EN COURS = 1ère checkbox sous "Ventes" sur accueil.jsp
	 * "SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, date_fin_encheres, articles_vendus.no_utilisateur "
	 * + "FROM encheres " +
	 * "		INNER JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
	 * +
	 * "		INNER JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
	 * + "WHERE date_debut_encheres < getdate() " +
	 * "		AND date_fin_encheres >= getdate() " +
	 * "		AND articles_vendus.no_utilisateur = ? " // SEUL ID UTILISATEUR +
	 * "GROUP BY articles_vendus.no_article, nom_article, date_fin_encheres, articles_vendus.no_utilisateur;"
	 * ; private static final String SQL_VENTES_NON_DEBUTEES = // = RECHERCHE MES
	 * VENTES NON DEBUTEES = 2ème checkbox sous "Ventes" sur accueil.jsp
	 * "SELECT no_article, nom_article, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur "
	 * + "FROM articles_vendus " + "WHERE date_debut_encheres > getdate();"; private
	 * static final String SQL_VENTES_TERMINEES = // = RECHERCHE MES VENTES
	 * TERMINEES = 3ème checkbox sous "Ventes" sur accueil.jsp
	 * "SELECT no_article, nom_article, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur "
	 * + "FROM articles_vendus " + "WHERE date_fin_encheres < getdate();";
	 */

	// Methods
	@Override
	public List<ArticleVendu> listeArticles(String[] motsClefs, String categorie, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException {

		List<ArticleVendu> articles = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Construction du WHERE Mots Clefs dans un StringBuilder
			StringBuilder sbMotsClefs = new StringBuilder();

			if (motsClefs != null) {
				for (int i = 0; i < motsClefs.length; i++) {
					if (i > 1) {
						sbMotsClefs.append(SQL_AND);
					}
					sbMotsClefs.append(SQL_MOT_CLEF);
					sbMotsClefs.append(motsClefs[i].toString());
				}
			}

			StringBuilder sbQuery = new StringBuilder();
			int compteurConditions = 0;
			sbQuery.append(SQL_SELECT);
			sbQuery.append(SQL_FROM);
			sbQuery.append(SQL_WHERE);

			if (motsClefs != null) {
				sbQuery.append(sbMotsClefs.toString());
				compteurConditions++;
			}

			if (categorie != null) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_CATEGORIE + categorie);
				compteurConditions++;
			}

			if (achatsOuverts) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_ACHATS_ENCHERES_OUVERTES);
				compteurConditions++;
			}

			if (achatsEncheresEnCours) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_ACHATS_MES_ENCHERES_EN_COURS + idUser + " )");
				compteurConditions++;
			}

			if (achatsEncheresRemportees) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_ACHATS_MES_ENCHERES_REMPORTEES + idUser + " )");
				compteurConditions++;
			}

			if (ventesEnCours) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_MES_VENTES_EN_COURS + idUser + " )");
				compteurConditions++;
			}

			if (ventesNonDebutees) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_VENTES_NON_DEBUTEES);
				compteurConditions++;
			}

			if (ventesTerminees) {
				if (compteurConditions > 0) {
					sbQuery.append(SQL_AND);
				}
				sbQuery.append(SQL_VENTES_TERMINEES);
				compteurConditions++;
			}

			sbQuery.append(SQL_GROUP_BY);

			PreparedStatement pstmt = cnx.prepareStatement(sbQuery.toString());

			pstmt.setString(1, sbQuery.toString());

			// récupération du résultat et intégration des données dans une liste
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int prix;

				if (rs.getInt("montant_max_enchere") > rs.getInt("prix_initial")) {
					prix = rs.getInt("montant_max_enchere");
				} else {
					prix = rs.getInt("prix_initial");
				}

				ArticleVendu article = new ArticleVendu((int) rs.getInt("no_article"),
						(String) rs.getString("nom_article"), (LocalDate) rs.getDate("date_fin_encheres").toLocalDate(),
						(int) prix, (String) rs.getString("pseudo"));
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

}
