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
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletAffichageProfilVendeur
 */
@WebServlet("/ServletAffichageProfilVendeur")
public class ServletAffichageProfilVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserAccount idVendeur = null;
		String pseudo = request.getParameter("pseudo");

		try {
			idVendeur.setNoUtilisateur(UserAccountManager.getInstance().selectProfil(pseudo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		UserAccount userAccount= null;
		userAccount = UserAccountManager.getInstance().selectUser(idVendeur.getNoUtilisateur());
	
		request.setAttribute("userAccount", userAccount);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/monProfil.jsp");
		rd.forward(request, response);
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
