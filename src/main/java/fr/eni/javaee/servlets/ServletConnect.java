package fr.eni.javaee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		    Cookie[] cookieRememberMe = request.getCookies();
		    if (cookieRememberMe != null) {
		        for (Cookie cookie : cookieRememberMe) {
		            if ("cookieRememberMe".equals(cookie.getName())) {
		                // Récupérer le pseudo et le mot de passe du cookie
		                String[] loginInfo = cookie.getValue().split(":");
		                String pseudo = loginInfo[0];
		                String email = loginInfo[0];
		                UserAccount useraccount = new UserAccount(pseudo, email);
		                boolean login = false;

		                // Vérifier si les informations de connexion de l'utilisateur sont valides
		                // (par exemple, en utilisant une requête SQL ou en appelant une méthode de vérification de mot de passe)
		                try {
							if (login = UserAccountManager.getInstance().connect(useraccount, pseudo) && login == true) {
								
								HttpSession session = request.getSession();
								session.setMaxInactiveInterval(5 * 60);
								session.setAttribute("useraccount", useraccount);

							    // Rediriger l'utilisateur vers la page d'accueil ou une autre page autorisée
							    response.sendRedirect("/WEB-INF/accueil.jsp");
							    return;
							}
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }
		    }
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
		String rememberMe = request.getParameter("rememberMe");
		
			boolean login = false;
			UserAccount useraccount = new UserAccount(pseudo, email, mot_de_passe);
					

	    	useraccount.setPseudo(pseudo);
	    	useraccount.setEmail(email);
	    	useraccount.setMot_de_passe(mot_de_passe);
	    	
			try {
				login = UserAccountManager.getInstance().connect(useraccount, mot_de_passe);
			
			if (login == true){ 
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(5 * 60);
				session.setAttribute("useraccount", useraccount);
				
				if ((request.getParameter("rememberMe"))!= null && rememberMe.equals("on")) {
					
					Cookie cookieRememberMe = new Cookie("cookieRememberMe", pseudo);
					cookieRememberMe.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookieRememberMe);
				}
				/*RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
		        rd.forward(request, response);
		        */
		        
		        response.sendRedirect(request.getContextPath() + "/ServletAccueil");
			
			}else {
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>Identifiant ou mot de passe incorrect.</font>");
				rd.include(request, response);
			}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  }
}
