package fr.eni.javaee.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.UserAccount;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {
	
	// Query - afficherVente
	private static final String SQL_AFFICHER_VENTE=
			"SELECT articles_vendus.no_article, nom_article, description, "
			+ "MAX(montant_enchere) as montant_max_enchere, prix_initial, date_debut_encheres, date_fin_encheres, "
			+ "articles_vendus.no_utilisateur , pseudo, RETRAITS.rue, RETRAITS.code_postal, RETRAITS.ville, libelle "
			
			+ "FROM ARTICLES_VENDUS "
			+ "LEFT JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur "
			+ "LEFT JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article "
			+ "INNER JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article "
			+ "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie "
			+ "WHERE ARTICLES_VENDUS.no_article = ? "
			+ "GROUP BY articles_vendus.no_article, nom_article, description, prix_initial, date_debut_encheres, date_fin_encheres, articles_vendus.no_utilisateur, pseudo, RETRAITS.rue, RETRAITS.code_postal, RETRAITS.ville, libelle; ";
	
	// Méthode - afficherVente
	public ArticleVendu afficherArticle(int noArticle) throws SQLException {
		ArticleVendu article = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SQL_AFFICHER_VENTE);
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(!rs.getString("nom_article").isBlank()) {
				
				System.out.println("OK");
				
				article= new ArticleVendu(rs.getString("nom_article"), rs.getString("description"), 
				rs.getString("libelle"),rs.getInt("prix_initial"), 
				rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
				rs.getString("pseudo"), rs.getInt("no_utilisateur"),
				rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	
	// Query - insertVente
	private static final String SQL_INSERT_VENTE="INSERT INTO ARTICLES_VENDUS ("
			+ "nom_article,"
			+ "description,"
			+ "date_debut_encheres,"
			+ "date_fin_encheres,"
			+ "prix_initial,"
			+ "prix_vente,"
			+ "no_utilisateur,"
			+ "no_categorie) "
			+ "VALUES (?,?,?,?,?,null,?,?);";
	private static final String SQL_INSERT_RETRAIT="INSERT INTO RETRAITS ("
			+ "no_article,"
			+ "rue,"
			+ "code_postal,"
			+ "ville)"
			+ "VALUES (?,?,?,?);";
	
	// Méthode - insertVente
	public void insertVente(
							// DATA ARTICLE
							String nomArticle, String description, int Categorie,
							LocalDate dateDebutEncheres, LocalDate dateFinEncheres, 
							int prixInitial, 
							// DATA USER
							int noUtilisateur, 
							// DATA RETRAIT
							String rue, int cp, String ville) throws SQLException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
			cnx.setAutoCommit(false);
			
			PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT_VENTE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, nomArticle);
			pstmt.setString(2, description);
			pstmt.setDate(3, Date.valueOf(dateDebutEncheres));
			pstmt.setDate(4, Date.valueOf(dateFinEncheres));
			pstmt.setInt(5, prixInitial);
			pstmt.setInt(6, noUtilisateur);
			pstmt.setInt(7, Categorie);
			pstmt.executeUpdate();
			ResultSet clef = pstmt.getGeneratedKeys();
			clef.next();
			
			pstmt = cnx.prepareStatement(SQL_INSERT_RETRAIT);
			pstmt.setInt(1, clef.getInt(1));
			pstmt.setString(2,  rue);
			pstmt.setInt(3,  cp);
			pstmt.setString(4,  ville);
			pstmt.executeUpdate();
			
			cnx.commit();
			}catch (SQLException e) {
				cnx.rollback();
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Querys - listeArticles

	private static final String SQL_SELECT = " SELECT articles_vendus.no_article, nom_article, MAX(montant_enchere) as montant_max_enchere, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur ";
	private static final String SQL_FROM = " FROM encheres "
			+ " RIGHT JOIN articles_vendus ON encheres.no_article = articles_vendus.no_article "
			+ " LEFT JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur "
			+ " LEFT JOIN categories ON articles_vendus.no_categorie = categories.no_categorie ";

	private static final String SQL_WHERE = " WHERE ";
	private static final String SQL_AND = " AND ";

	private static final String SQL_MOT_CLEF = " nom_article LIKE ";
	private static final String SQL_CATEGORIE = " libelle = ";
	private static final String SQL_ACHATS_ENCHERES_OUVERTES = " date_debut_encheres < getdate() AND date_fin_encheres >= getdate() ";
	private static final String SQL_ACHATS_MES_ENCHERES_EN_COURS = " articles_vendus.no_article IN (SELECT no_article FROM encheres WHERE ENCHERES.no_utilisateur = ";
	private static final String SQL_ACHATS_MES_ENCHERES_REMPORTEES = " date_fin_encheres < getdate() AND articles_vendus.no_article IN (SELECT no_article FROM encheres WHERE ENCHERES.no_utilisateur = ";
	private static final String SQL_MES_VENTES_EN_COURS = SQL_ACHATS_ENCHERES_OUVERTES
			+ " AND articles_vendus.no_article IN (SELECT no_article FROM articles_vendus WHERE articles_vendus.no_utilisateur = ";
	private static final String SQL_VENTES_NON_DEBUTEES = " articles_vendus.no_article IN ( SELECT no_article FROM ARTICLES_VENDUS WHERE date_debut_encheres > getdate()) ";
	private static final String SQL_VENTES_TERMINEES = " articles_vendus.date_fin_encheres < getdate() ";

	private static final String SQL_GROUP_BY = " GROUP BY articles_vendus.no_article, nom_article, prix_initial, date_fin_encheres, articles_vendus.no_utilisateur; ";

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

	// Methode listeArticles
	@Override
	public List<ArticleVendu> listeArticles(String motsClefs, String categorie, String radio, boolean achatsOuverts,
			boolean achatsEncheresEnCours, boolean achatsEncheresRemportees, boolean ventesEnCours,
			boolean ventesNonDebutees, boolean ventesTerminees, int idUser) throws SQLException {

		List<ArticleVendu> articles = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			StringBuilder sbQuery = new StringBuilder();
			int compteurConditions = 0;
			sbQuery.append(SQL_SELECT);
			sbQuery.append(SQL_FROM);

			sbQuery.append(SQL_WHERE);

			sbQuery.append(SQL_MOT_CLEF + "'%" + motsClefs + "%' ");
			compteurConditions++;

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

			Statement pstmt = cnx.createStatement();

			// récupération du résultat et intégration des données dans une liste
			ResultSet rs = pstmt.executeQuery(sbQuery.toString());

			UserAccount userAccount = null;

			while (rs.next()) {

				int prix;

				if (rs.getInt("montant_max_enchere") > rs.getInt("prix_initial")) {
					prix = rs.getInt("montant_max_enchere");
				} else {
					prix = rs.getInt("prix_initial");
				}
								
				userAccount = UserAccountManager.getInstance().selectUser(rs.getInt("no_utilisateur"));

				ArticleVendu article = new ArticleVendu((int) rs.getInt("no_article"),
						(String) rs.getString("nom_article"), (LocalDate) rs.getDate("date_fin_encheres").toLocalDate(),
						(int) prix, rs.getInt("no_utilisateur"), userAccount.getPseudo());
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

}
