package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bll.ArticleVenduManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.dal.DAOFactory;

@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean achats = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String motsClefs = null;
		String categorie = null;
		String radio= null;
		boolean achatsOuverts = false;
		boolean achatsEncheresEnCours = false;
		boolean achatsEncheresRemportees = false;
		boolean ventesEnCours = false;
		boolean ventesNonDebutees = false;
		boolean ventesTerminees = false;
		int idUser = 9;

		// récupération des paramètres
		motsClefs = (String) request.getParameter("motsClefs");
		
		if (request.getParameter("categorie").equals("Toutes")) {
			categorie = null;
		}else {
			categorie = (String) request.getParameter("categorie");	
		}
		
		
		if(request.getParameter("checkEncheresOuvertes")==null){
			achatsOuverts = false;
		}else if (request.getParameter("checkEncheresOuvertes").equals("")) {
			achatsOuverts = true;
		}
		
		if(request.getParameter("checkEncheresEnCours")==null){
			achatsEncheresEnCours = false;
		}else if (request.getParameter("checkEncheresEnCours").equals("")) {
			achatsEncheresEnCours = true;
		}
		
		if(request.getParameter("checkEncheresRemportees")==null){
			achatsEncheresRemportees = false;
		}else if (request.getParameter("checkEncheresRemportees").equals("")) {
			achatsEncheresRemportees = true;
		}
		
		if(request.getParameter("checkVentesEnCours")==null){
			ventesEnCours = false;
		}else if (request.getParameter("checkVentesEnCours").equals("")) {
			ventesEnCours = true;
		}
		
		if(request.getParameter("checkVentesNonDebutees")==null){
			ventesNonDebutees = false;
		}else if (request.getParameter("checkVentesNonDebutees").equals("")) {
			ventesNonDebutees = true;
		}
		
		if(request.getParameter("checkVentesTerminees")==null){
			ventesTerminees = false;
		}else if (request.getParameter("checkVentesTerminees").equals("")) {
			ventesTerminees = true;
		}
		
		radio = request.getParameter("radio");
		
		ArticleVenduManager avManager = new ArticleVenduManager();
		List<ArticleVendu> listeArticlesVendus = null;
		try {
			listeArticlesVendus = avManager.listeArticles(motsClefs, categorie, radio, achatsOuverts, achatsEncheresEnCours,
						achatsEncheresRemportees, ventesEnCours, ventesNonDebutees, ventesTerminees, idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listeArticlesVendus", listeArticlesVendus);
		
		doGet(request, response);
	}
}
