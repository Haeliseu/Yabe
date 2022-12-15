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
		String email = request.getParameter("pseudo");
		String mot_de_passe = request.getParameter("mot_de_passe");
			boolean login = false;
			UserAccount useraccount = new UserAccount(pseudo, email, mot_de_passe);
					
	    	useraccount.setPseudo(pseudo);
	    	useraccount.setEmail(email);
	    	useraccount.setMot_de_passe(mot_de_passe);
	    	System.out.println(useraccount.getPseudo());
	    	System.out.println(useraccount.getEmail());
	    	System.out.println(useraccount.getMot_de_passe());
			try {
				login = UserAccountManager.getInstance().verify(useraccount);
			
			if (login == true){
				
				HttpSession session = request.getSession();
				session.setAttribute("useraccount", useraccount);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		        rd.forward(request, response);
			
			}else {
				System.err.println("Identifiant ou mot de passe incorrect");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
		        rd.forward(request, response);
			}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  }
}  

		
