<%@page import="java.time.LocalDate"%>
<%@page import="fr.eni.javaee.bo.Categorie"%>
<%@page import="fr.eni.javaee.bo.UserAccount"%>
<%@page import="fr.eni.javaee.bo.Enchere"%>
<%@page import="fr.eni.javaee.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.article.nomArticle }-Détail de la vente</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="container align-items-center">

		<ul class="list-group">

			<% ArticleVendu articleRech = (ArticleVendu) request.getAttribute("articleRech");
			UserAccount userAccount = (UserAccount) request.getAttribute("userAccount");
			Enchere enchere = (Enchere) request.getAttribute("enchere");
			Categorie categorie = (Categorie) request.getAttribute("categorie");%>
			
			<li class="list-group-item">Vendeur : ${requestScope.userAccount.pseudo }</li>
			<input type="text" name="idVendeur" id="idVendeur" value="${requestScope.userAccount.noUtilisateur }" disabled hidden>
						
			<li class="list-group-item">Nom
				: ${requestScope.articleRech.nomArticle }</li>
			<li class="list-group-item">Description
				: ${requestScope.articleRech.description }</li>
			<li class="list-group-item">Catégorie
				: ${requestScope.categorie.libelle }</li>
			<li class="list-group-item">Mise à prix
				: ${requestScope.articleRech.prix }</li>
			<li class="list-group-item">Début de l'enchère
				: ${requestScope.articleRech.dateDebutEncheres }</li>
			<li class="list-group-item">Fin de l'enchère
				: ${requestScope.articleRech.dateFinEncheres }</li>

			<li class="list-group-item">Retrait :
				<ul class="list-group">
					<li class="list-group-item">Rue : ${requestScope.articleRech.rue }</li>
					<li class="list-group-item">Code postal
						: ${requestScope.articleRech.codePostal }</li>
					<li class="list-group-item">Ville
						: ${requestScope.articleRech.ville }</li>
				</ul>
			</li>
		</ul>
	</div>

	<%
	if (session != null && session.getAttribute("useraccount") == userAccount) {
	%>
	<div class="container">
		<div class="row">
			<a class="btn btn-primary col" style="margin: 1em;"
				href="<%=request.getContextPath()%>/%>">Modifier la vente</a> <a
				class="btn btn-primary col" style="margin: 1em;"
				href="<%=request.getContextPath()%>/ServletAccueil">Retour à
				l'accueil</a> <a class="btn btn-primary col" style="margin: 1em;"
				href="<%=request.getContextPath()%>/%>">Annuler la vente</a>
		</div>
	</div>
	<%
	} else if (session.getAttribute("useraccount") != null && articleRech.getDateFinEncheres().isAfter(LocalDate.now())){
	%>
	<div class="container">
		<div class="row">
		<form method="POST" action="<%=request.getContextPath()%>/ServletAfficherUneVente?idArticle=<%=articleRech.getIdArticle() %>">
		<label for="montantEnchere">Ma proposition : </label>
		<%if(enchere!=null){ %>
			<input type="number" name="montantEnchere" id="montantEnchere" value="<%=(enchere.getMontantEnchere()+1)%>">
		<%}else{ %>
		<input type="number" name="montantEnchere" id="montantEnchere">
		<%} %>
			<input type="submit" class="btn btn-primary col" value="Enchérir" style="margin: 1em;">
		</div>
		</form>
	</div>
	<%
	}
	%>


	<div style="text-align: center";>
		<%
		List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		if (listeCodesErreur != null) {
		%>
		<p style="color: red">Erreur, l'utilisateur n'a pas pu être ajouté
			:</p>
		<%
		for (int code : listeCodesErreur) {
		%>
		<p><%=code%>
			:
			<%=LecteurMessage.getMessageErreur(code)%></p>
		<%
		}
		}
		%>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>