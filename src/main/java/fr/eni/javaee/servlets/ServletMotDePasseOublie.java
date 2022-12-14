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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserAccount userAccount = null;
		String pseudo = "pseudo1";//(String)request.getParameter("pseudo");
		String email = "email1";//(String) request.getParameter("email");

		userAccount = UserAccountManager.getInstance().oublieMotDePasse(pseudo, email);
	
		if (pseudo.equals(userAccount.getPseudo())	&& email.equals(userAccount.getEmail())) {							
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifMotDePasseOublie.jsp");
			rd.forward(request, response);

		} else {
		
			
			
			System.err.println("Username or Password incorrect");
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/motDePasseOublie.jsp");
			rs.include(request, response);

		}

	}
			 
			
	
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
