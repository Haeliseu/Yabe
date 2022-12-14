package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.bll.UserAccountManager;

/**
 * Servlet implementation class ServletChangementMotDePasseOublié
 */
@WebServlet("/ServletChangementMotDePasseOublié")
public class ServletChangementMotDePasseOublié extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String motdepasse =request.getParameter("nouveauMotDePasse");//
		String newMdp =request.getParameter("ConfirmationNouveauMotDePasse");
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");

		if (motdepasse.equals(newMdp)) {

			

			try {
				UserAccountManager.getInstance().newMdp(motdepasse, pseudo, email);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		} else {
			System.err.println("Confirmation de mot de passe incorrect");

			RequestDispatcher rd = request.getRequestDispatcher("s/ServletMotDePasseOublie.java");
			rd.include(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
