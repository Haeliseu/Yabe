<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@page import="fr.eni.javaee.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>

<script>
	function verifyAchats() {
		var achats = document.getElementById('flexRadioDefault1').checked;
		if (achats == false) {
			document.getElementById("encheresOuvertes").disabled = true;
			document.getElementById("encheresEnCours").disabled = true;
			document.getElementById("encheresRemportees").disabled = true;
			document.getElementById("ventesEnCours").disabled = false;
			document.getElementById("ventesNonDebutees").disabled = false;
			document.getElementById("ventesTerminees").disabled = false;

			document.getElementById("encheresOuvertes").checked = false;
			document.getElementById("encheresEnCours").checked = false;
			document.getElementById("encheresRemportees").checked = false;
			document.getElementById("ventesEnCours").checked = false;
			document.getElementById("ventesNonDebutees").checked = false;
			document.getElementById("ventesTerminees").checked = false;
		} else {
			document.getElementById("encheresOuvertes").disabled = false;
			document.getElementById("encheresEnCours").disabled = false;
			document.getElementById("encheresRemportees").disabled = false;
			document.getElementById("ventesEnCours").disabled = true;
			document.getElementById("ventesNonDebutees").disabled = true;
			document.getElementById("ventesTerminees").disabled = true;

			document.getElementById("encheresOuvertes").checked = false;
			document.getElementById("encheresEnCours").checked = false;
			document.getElementById("encheresRemportees").checked = false;
			document.getElementById("ventesEnCours").checked = false;
			document.getElementById("ventesNonDebutees").checked = false;
			document.getElementById("ventesTerminees").checked = false;
		}
	}

	function verifyCheck() {
		document.getElementById('flexRadioDefault1').checked = false;
		document.getElementById('flexRadioDefault2').checked = false;
	}
</script>

</head>
<body>

	<%@include file="header.jsp"%>

	<%
	if (session.getAttribute("useraccount") != null) {
		UserAccount useraccount = (UserAccount) session.getAttribute("useraccount");
	%>
	<h5 style="text-align: center;"><%=useraccount.getPseudo()%>
		connecté(e)
	</h5>
	<hr>
	<%
	}
	%>

	<h2 class="text-center" style="margin-bottom: 1em;">Liste des
		enchères</h2>

	<div class="container">
		<form method="POST"
			action="<%=request.getContextPath()%>/ServletAccueil">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="row align-items-left">
							<h3 style="margin-bottom: .5em;">Filtres :</h3>
							<input type="text" class="form-control"
								style="margin-bottom: 1em;" aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-default" name="motsClefs">

							<h4 style="margin-bottom: .5em;">Catégorie :</h4>
							<select class="form-select" aria-label="Default select example"
								style="margin-bottom: 1em;" name="categorie">
								<option selected>Toutes</option>
								<option value="'1'">Catégorie 1</option>
								<option value="'2'">Catégorie 2</option>
							</select>

							<%
							if (session.getAttribute("useraccount") != null) {
							%>
							<!-- FILTRES DE RECHERCHE -->

							<div class="container align-items-center">
								<div class="row">
									<div class="col-lg-6 col-md-6">
										<div class="form-check">
											<input onclick="verifyAchats()" class="form-check-input"
												type="radio" name="radio" id="flexRadioDefault1"
												value="achats"> <label class="form-check-label"
												for="flexRadioDefault1"> Achats </label>
										</div>

										<div class="form-check chAchats">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkEncheresOuvertes" type="checkbox" value=""
												id="encheresOuvertes" disabled> <label
												class="form-check-label" for="encheresOuvertes">
												enchères ouvertes </label>

										</div>
										<div class="form-check chAchats">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkEncheresEnCours" type="checkbox" value=""
												id="encheresEnCours" disabled> <label
												class="form-check-label" for="encheresEnCours"> mes
												enchères en cours </label>
										</div>
										<div class="form-check chAchats">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkEncheresRemportees" type="checkbox" value=""
												id="encheresRemportees" disabled> <label
												class="form-check-label" for="encheresRemportees">
												mes enchères remportées </label>
										</div>
									</div>

									<div class="col-lg-6 col-md-6">
										<div class="form-check">
											<input onclick="verifyAchats()" class="form-check-input"
												type="radio" name="radio" id="flexRadioDefault2"
												value="ventes"> <label class="form-check-label"
												for="flexRadioDefault2"> Mes Ventes </label>
										</div>

										<div class="form-check chVentes">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkVentesEnCours" type="checkbox" value=""
												id="ventesEnCours" disabled> <label
												class="form-check-label" for="ventesEnCours"> mes
												ventes en cours </label>
										</div>
										<div class="form-check chVentes">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkVentesNonDebutees" type="checkbox" value=""
												id="ventesNonDebutees" disabled> <label
												class="form-check-label" for="ventesNonDebutees">
												ventes non débutées </label>
										</div>
										<div class="form-check chVentes">
											<input onclick="verifyCheck()" class="form-check-input"
												name="checkVentesTerminees" type="checkbox" value=""
												id="ventesTerminees" disabled> <label
												class="form-check-label" for="ventesTerminees">
												ventes terminées </label>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>
						</div>
					</div>

					<div class="col-auto align-self-center ">
						<input type="submit" class="btn btn-primary btn-lg"
							name="rechercher" value="Rechercher">
					</div>
				</div>
			</div>
		</form>
	</div>




	<div class="container">
		<div class="row align-items-center">
			<%
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) request.getAttribute("listeArticlesVendus");
			if (listeArticles != null) {
				for (ArticleVendu article : listeArticles) {
			%>

			<div class="card container col-lg-12"
				style="margin: .5em; width: 30rem;">
				<div class="row">
					<div class="col-lg-4">
						<img class="card-img-left" src="..." alt="">
						<!-- INSERTION DES IMAGES -->
					</div>
					<div class="col-lg-8">
						<div class="card-body">
							<h5 class="card-title"><%=article.getNomArticle()%></h5>
							<p class="card-text">
								Prix :
								<%=article.getPrix()%></p>
							<p class="card-text">
								Fin de l'enchère :
								<%=article.getDateFinEncheres()%></p>
							<p class="card-text">
								Vendeur : <a
									href="<%=request.getContextPath()%>/ServletAffichageProfilVendeur?idUser=<%=article.getIdVendeur()%>"><%=article.getPseudoVendeur()%></a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>


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

<jsp:include page="footer.jsp" />
</html>