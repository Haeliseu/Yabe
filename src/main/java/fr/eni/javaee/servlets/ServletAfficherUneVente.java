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
import fr.eni.javaee.bll.ArticleVenduManager;
import fr.eni.javaee.bll.CategorieManager;
import fr.eni.javaee.bll.EnchereManager;
import fr.eni.javaee.bll.UserAccountManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.UserAccount;

/**
 * Servlet implementation class ServletAfficherUneVente
 */
@WebServlet("/ServletAfficherUneVente")
public class ServletAfficherUneVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticle = Integer.valueOf(request.getParameter("noArticle"));
				
		ArticleVendu article = new ArticleVendu(idArticle);
		Enchere enchere = null;
		UserAccount userAccount = null;
		Categorie categorie = null;
		
			try {
				article = ArticleVenduManager.getInstance().afficherArticle(article);
				categorie = CategorieManager.getInstance().rechCategorie(article.getCategorie());
				userAccount = UserAccountManager.getInstance().selectUser(article.getUserAccount().getNoUtilisateur());
				enchere = EnchereManager.getInstance().maxEnchereByArticle(article);
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("article", article);
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("categorie", categorie);
			request.setAttribute("enchere", enchere);
			
			HttpSession session = request.getSession(false);
			request.setAttribute("session", session);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherUneVente.jsp");
			rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
