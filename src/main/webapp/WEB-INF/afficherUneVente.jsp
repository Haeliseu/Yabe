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

			<% ArticleVendu article = (ArticleVendu) request.getAttribute("article");
			UserAccount userAccount = (UserAccount) request.getAttribute("userAccount");
			Enchere enchere = (Enchere) request.getAttribute("enchere");
			Categorie categorie = (Categorie) request.getAttribute("categorie");%>
			
			<li class="list-group-item">Vendeur : ${requestScope.userAccount.pseudo }</li>
			<label name="idVendeur" disabled hidden>${requestScope.userAccount.noUtilisateur }</label>

			<li class="list-group-item">Nom
				: ${requestScope.article.nomArticle }</li>
			<li class="list-group-item">Description
				: ${requestScope.article.description }</li>
			<li class="list-group-item">Catégorie
				: ${requestScope.categorie.libelle }</li>
			<li class="list-group-item">Mise à prix
				: ${requestScope.article.prix }</li>
			<li class="list-group-item">Début de l'enchère
				: ${requestScope.article.dateDebutEncheres }</li>
			<li class="list-group-item">Fin de l'enchère
				: ${requestScope.article.dateFinEncheres }</li>

			<li class="list-group-item">Retrait :
				<ul class="list-group">
					<li class="list-group-item">Rue : ${requestScope.article.rue }</li>
					<li class="list-group-item">Code postal
						: ${requestScope.article.codePostal }</li>
					<li class="list-group-item">Ville
						: ${requestScope.article.ville }</li>
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
	} else if (session != null && enchere.getUserAccount().getNoUtilisateur()!= Integer.valueOf(session.getId())){
	%>
	<div class="container">
		<div class="row">
		<label for="montantEnchere">Ma proposition : </label>
			<input type="number" name="montantEnchere" id="montantEnchere" value="<%=(enchere.getMontantEnchere()+1)%>">
			<a class="btn btn-primary col" style="margin: 1em;"
				href="<%=request.getContextPath()%>/%>">Enchérir</a>
		</div>
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