<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau mot de passe</title>
</head>
<body>

	<header>
		<jsp:include page="header.jsp" />
	</header>

	<%
	String email = (String) request.getAttribute("email");
	String pseudo = (String) request.getAttribute("pseudo");
	%>
	<form
		action="<%=request.getContextPath()%>/ServletChangementMotDePasseOublie"
		method="post">

		<input type="text" class="form-control" name="email"
			style="margin-bottom: 1em;" aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default" value="<%=email%>"
			hidden> <input type="text" class="form-control" name="pseudo"
			style="margin-bottom: 1em;" aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default" value="<%=pseudo%>"
			hidden>

		<h3 style="margin-bottom: .5em;">Nouveau mot de passe:</h3>
		<input type="password" class="form-control" name="nouveauMotDePasse"
			style="margin-bottom: 1em;" aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default">

		<h3 style="margin-bottom: .5em;">Confirmation mot de passe:</h3>
		<input type="password" class="form-control"
			name="ConfirmationNouveauMotDePasse" style="margin-bottom: 1em;"
			aria-label="Sizing example input"
			aria-describedby="inputGroup-sizing-default"> <input
			type="submit" class="btn btn-primary btn-lg" value="Validez">
	</form>
	<div style="text-align: center";>
		<%
		List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
		if (listeCodesErreur != null) {
		%>
		<p style="color: red">Erreur, le mot de passe n'a pas pu être changé
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
</body>
</html>