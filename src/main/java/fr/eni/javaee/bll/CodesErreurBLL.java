package fr.eni.javaee.bll;

public interface CodesErreurBLL {
	
	public static final int REGLE_DIFFERENT_MDP_ERREUR = 20_002;
	
	public static final int VALIDATION_ACHATS_ERREUR = 20_101;
	
	
	// ARTICLES VENDUS
		// CREATION ARTICLE
	public static final int INSERT_VENTE_NOM_ARTICLE_ERREUR = 20_201;
	public static final int INSERT_VENTE_DESCRIPTION_ARTICLE_ERREUR = 20_201;
	public static final int INSERT_VENTE_CATEGORIE_ARTICLE_ERREUR = 20_202;
	public static final int	INSERT_VENTE_DATE_DEBUT_ENCHERES_ERREUR = 20_203;
	public static final int INSERT_VENTE_DATE_FIN_ENCHERES_ERREUR = 20_204;
	public static final int INSERT_VENTE_PRIX_INITIAL_ERREUR = 20_205;
	public static final int INSERT_VENTE_USER_ERREUR = 20_206;
	public static final int INSERT_VENTE_RUE_ERREUR = 20_207;
	public static final int INSERT_VENTE_VILLE_ERREUR = 20_208;
	public static final int INSERT_VENTE_CODE_POSTAL_ERREUR = 20_209;
	
		// AFFICHER ARTICLE
	public static final int INSERT_VENTE_ID_ARTICLE_ERREUR = 20_210;
	
	// ENCHERES
	public static final int INSERT_ENCHERE_USER_ERREUR = 20_220;
	public static final int INSERT_ENCHERE_ARTICLE_ERREUR = 20_221;
	public static final int INSERT_ENCHERE_MONTANT_ERREUR = 20_222;
	public static final int INSERT_ENCHERE_CREDIT_RESTANT_INSUFFISANT_ERREUR = 20_223;
}
