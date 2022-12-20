<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<div class="row" style="margin-top: 12%;"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="card"
					style="width: auto; box-shadow: 10px 5px 5px gray;">
					<div class="card-body">
						<form method="POST" action="/Yabe/ServletConnect"
							style="text-align: center;">
							<label for="pseudo">Identifiant :</label> <input type="text"
								name="pseudo" id="pseudo" class="form-control" required /> <br>
							<br> <label for="mot_de_passe">Mot de passe :</label> <input
								type="password" name="mot_de_passe" id="mot_de_passe"
								class="form-control" required /> <br />
							<br />
							<div class="row">
								<div class="col">
									<input class="btn btn-primary" type="submit"
										name="formconnexion" value="Connexion" />
								</div>
								<div class="col">
									<input type="checkbox" name="rememberMe"
										value="Se souvenir de moi" id="rememberMe" /> <label
										for="rememberMe" id="rememberMe">Se souvenir de moi</label>
								</div>
								<div class="w-70"></div>
								<div class="col"></div>
								<div class="col">
									<a id="mot_de_passe_oublie" class="text-forgot-password"
										href="<%=request.getContextPath()%>/ServletMotDePasseOublie">Mot
										de passe oublié</a>
								</div>
							</div>
						</form>
						<br>
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<input class="btn btn-primary" type="submit" name="formconnexion"
								value="Créer un compte" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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
</body>
<div class="fixed-bottom"><jsp:include page="footer.jsp" /></div>
</html>