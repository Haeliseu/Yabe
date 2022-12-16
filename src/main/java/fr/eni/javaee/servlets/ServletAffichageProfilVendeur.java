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

		int idUser = parseInt(request.getParameter("pseudo"));
		System.out.println(idUser);
		try {
			int idVendeur;
			idVendeur = UserAccountManager.getInstance().selectUser(idUser);
			System.out.println(idVendeur);
			UserAccount userAccount = null;
			userAccount = UserAccountManager.getInstance().selectUser(idVendeur);
			System.out.println(userAccount);
			request.setAttribute("userAccount", userAccount);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	private int parseInt(String parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
