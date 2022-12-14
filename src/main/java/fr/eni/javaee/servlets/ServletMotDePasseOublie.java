package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletMotDePasseOublie
 */
@WebServlet("/ServletMotDePasseOublie")
public class ServletMotDePasseOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserAccount userAccount = null;
		String pseudo = (String) request.getParameter("pseudo");
		String email = (String) request.getParameter("email");
		request.setAttribute("email", email);
		request.setAttribute("pseudo", pseudo);

		userAccount = UserAccountManager.getInstance().oublieMotDePasse(pseudo, email);

		if (userAccount != null) {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifMotDePasseOublie.jsp");
			rd.forward(request, response);

		} else {

			System.err.println("Username or Password incorrect");
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp");
			rs.forward(request, response);
		}

	}

}
