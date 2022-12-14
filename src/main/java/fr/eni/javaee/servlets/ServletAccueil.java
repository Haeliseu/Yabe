package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean achats = true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// récupération des paramètres
		request.getParameter("motsClefs");
		request.getParameter("categorie");
		request.getParameter("radtioAchats");
		request.getParameter("checkEncheresOuvertes");
		request.getParameter("checkEncheresEnCours");
		request.getParameter("checkEncheresRemportees");
		request.getParameter("radtioVentes");
		request.getParameter("checkVentesEnCours");
		request.getParameter("checkVentesNonDebutees");
		request.getParameter("checkVentesTerminees");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
