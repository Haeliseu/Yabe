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
	<form action="<%=request.getContextPath() %>/ServletChangementMotDePasseOublie" method="post">
	<h3 style="margin-bottom: .5em;">Email:</h3>
	<input type="text" class="form-control" name="email"
		style="margin-bottom: 1em;" aria-label="Sizing example input"
		aria-describedby="inputGroup-sizing-default" value="<%=email%>"
		disabled>

	<h3 style="margin-bottom: .5em;">Pseudo:</h3>
	<input type="text" class="form-control" name="pseudo"
		style="margin-bottom: 1em;" aria-label="Sizing example input"
		aria-describedby="inputGroup-sizing-default" value="<%=pseudo%>"
		disabled>

	<h3 style="margin-bottom: .5em;">Nouveau mot de passe:</h3>
	<input type="password" class="form-control" name="nouveauMotDePasse"
		style="margin-bottom: 1em;" aria-label="Sizing example input"
		aria-describedby="inputGroup-sizing-default">

	<h3 style="margin-bottom: .5em;">Confirmation mot de passe:</h3>
	<input type="password" class="form-control"
		name="ConfirmationNouveauMotDePasse" style="margin-bottom: 1em;"
		aria-label="Sizing example input"
		aria-describedby="inputGroup-sizing-default">



	<input type="submit" class="btn btn-primary btn-lg"value="Validez">
</form>
</body>
</html>