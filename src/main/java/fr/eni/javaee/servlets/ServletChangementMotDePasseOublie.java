package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletChangementMotDePasseOublié
 */
@WebServlet("/ServletChangementMotDePasseOublie")
public class ServletChangementMotDePasseOublie extends HttpServlet {
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

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifMotDePasseOublie.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String motdepasse = request.getParameter("nouveauMotDePasse");

		String newMdp = request.getParameter("ConfirmationNouveauMotDePasse");

		String pseudo = request.getParameter("pseudo");

		String email = request.getParameter("email");

			try {
				UserAccountManager.getInstance().inserermdp(pseudo, email, motdepasse, newMdp);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
			rd.forward(request, response);
			
			} catch (BusinessException e) {
				
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifMotDePasseOublie.jsp");
			rd.forward(request, response);
			}
	}
}
