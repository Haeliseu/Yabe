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
		
		try {	
			UserAccountManager userAccountManager = null;
			UserAccount useraccount =  UserAccountManager.getInstance().connect(pseudo, email, mot_de_passe);
			if (pseudo.equals(useraccount.getPseudo())) {
				
			}
			request.setAttribute("useraccount", useraccount);

	} catch (SQLException e){
		//BusinessException be = new BusinessException();
		//be.ajouterErreur(CodesResultatServlets.FORMAT_EMAIL_ERREUR);
		//request.setAttribute("listeCodesErreur", be.getListeCodesErreur());
	}catch (BusinessException e){
		//request.setAttribute("listeCodesErreur", e.getListeCodesErreur());				
	}
           
           RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect.jsp");
           rd.forward(request, response);
        }
    }  

		
