package fr.eni.javaee.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
		ArticleVendu articleRech = null;
		Enchere enchere = null;
		UserAccount userAccount = null;
		Categorie categorie = null;
		
			try {
				articleRech = ArticleVenduManager.getInstance().afficherArticle(article);
				categorie = CategorieManager.getInstance().rechCategorie(articleRech.getCategorie());
				userAccount = UserAccountManager.getInstance().selectUser(articleRech.getUserAccount().getNoUtilisateur());
				enchere = EnchereManager.getInstance().maxEnchereByArticle(articleRech);
				
			} catch (BusinessException | SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("articleRech", articleRech);
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("categorie", categorie);
			request.setAttribute("enchere", enchere);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherUneVente.jsp");
			rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserAccount userAccount = (UserAccount) session.getAttribute("useraccount");
		ArticleVendu article = new ArticleVendu(Integer.parseInt(request.getParameter("idArticle")));
		int montantEnchere = Integer.valueOf(request.getParameter("montantEnchere"));

		try {
			EnchereManager.getInstance().nouvelleEnchere(userAccount, article, montantEnchere);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
