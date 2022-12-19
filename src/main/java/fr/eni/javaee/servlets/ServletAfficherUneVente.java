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
import fr.eni.javaee.bll.ArticleVenduManager;
import fr.eni.javaee.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAfficherUneVente
 */
@WebServlet("/ServletAfficherUneVente")
public class ServletAfficherUneVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticle = Integer.valueOf(request.getParameter("noArticle"));
		
		System.out.println(idArticle);
		
		ArticleVendu article = null;
		
			try {
				article = ArticleVenduManager.getInstance().afficherArticle(idArticle);
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("article", article);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherUneVente.jsp");
			rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
