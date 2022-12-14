package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.BusinessException;
import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletConnect
 */
@WebServlet("/ServletConnect")
public class ServletConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String mot_de_passe = request.getParameter("mot_de_passe");
		
			
			UserAccountManager userAccountManager = null;
			UserAccount useraccount;
			try {
				useraccount = UserAccountManager.getInstance().connect(pseudo, email, mot_de_passe);
			
			if ((pseudo.equals(useraccount.getPseudo())||(email.equals(useraccount.getEmail())))
	                && mot_de_passe.equals(useraccount.getMot_de_passe())){
				
				HttpSession session = request.getSession();
				session.setAttribute("useraccount", useraccount);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		        rd.forward(request, response);
			
			}else {
				System.err.println("Identifiant ou mot de passe incorrect");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
		        rd.include(request, response);
			}
			} catch (BusinessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  }
}  

		
