<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>

<script>
function verifyAchats(){
	var achats = document.getElementById('flexRadioDefault1').checked;
	if(achats == false){
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
	}else{
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

function verifyCheck(){
	document.getElementById('flexRadioDefault1').checked = false;
	document.getElementById('flexRadioDefault2').checked = false;
}

</script>

</head>
<body>

	<%@include file="header.jsp"%>

	<h2 class="text-center" style="margin-bottom: 1em;">Liste des
		enchères</h2>

	<div class="container" style="margin-bottom: 2em;">
		<div class="row align-items-center">

			<div class="col">
				<h3 style="margin-bottom: .5em;">Filtres :</h3>
				<input type="text" class="form-control" style="margin-bottom: 1em;"
					aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default">

				<h4 style="margin-bottom: .5em;">Catégorie :</h4>
				<select class="form-select" aria-label="Default select example"
					style="margin-bottom: 1em;">
					<option selected>Toutes</option>
					<option value="1">Catégorie 1</option>
					<option value="2">Catégorie 2</option>
					<option value="3">Catégorie 3</option>
				</select>

				<!-- TODO RECUPERATION DE LA VARIABLE DE CONNEXION -->

				<%if (checkConnect == true) {%>
				<!-- FILTRES DE RECHERCHE -->

				<div class="container align-items-center">
					<div class="row">
						<div class="col-lg-6 col-md-6">
							<div class="form-check">
								<input onclick="verifyAchats()" class="form-check-input" type="radio"
									name="flexRadioDefault" id="flexRadioDefault1"> <label
									class="form-check-label" for="flexRadioDefault1">
									Achats </label>
							</div>
							
							<div class="form-check chAchats">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="encheresOuvertes"> <label class="form-check-label"
									for="encheresOuvertes"> enchères ouvertes </label>

							</div>
							<div class="form-check chAchats">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="encheresEnCours"> <label class="form-check-label"
									for="encheresEnCours"> mes enchères en cours </label>
							</div>
							<div class="form-check chAchats">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="encheresRemportees" > <label
									class="form-check-label" for="encheresRemportees"> mes
									enchères remportées </label>
							</div>
						</div>

						<div class="col-lg-6 col-md-6">
							<div class="form-check">
								<input onclick="verifyAchats()" class="form-check-input" type="radio"
									name="flexRadioDefault" id="flexRadioDefault2"> <label
									class="form-check-label" for="flexRadioDefault2"> Mes
									Ventes </label>
							</div>

							<div class="form-check chVentes">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="ventesEnCours"> <label class="form-check-label"
									for="ventesEnCours"> mes ventes en cours </label>
							</div>
							<div class="form-check chVentes">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="ventesNonDebutees"> <label class="form-check-label"
									for="ventesNonDebutees"> ventes non débutées </label>
							</div>
							<div class="form-check chVentes">
								<input onclick="verifyCheck()" class="form-check-input" type="checkbox" value=""
									id="ventesTerminees"> <label class="form-check-label"
									for="ventesTerminees"> ventes terminées </label>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>

			<div class="col">
				<a href=""><button type="button" class="btn btn-primary btn-lg" name="rechercher">Rechercher</button></a>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row align-items-center">

			<!-- FAIRE LA BOUCLE D AFFICHAGE DES CARTES D ARTICLES -->

			<div class="card container col-lg-12" style="width: 30rem;">
				<div class="row">
					<div class="col-lg-4">
						<img class="card-img-left" src="..." alt="test">
						<!-- INSERTION DES IMAGES -->
					</div>
					<div class="col-lg-8">
						<div class="card-body">
							<h5 class="card-title">article.getNom</h5>
							<p class="card-text">Prix : "article.getPrix()"</p>
							<p class="card-text">Fin de l'enchère :
								"article.getFinEnchere()"</p>
							<p class="card-text">
								Vendeur : <a href="#">"article.getUser()"</a>
							</p>
						</div>
					</div>
				</div>
			</div>

			<div class="card container col-lg-12" style="width: 30rem;">
				<div class="row">
					<div class="col-lg-4">
						<img class="card-img-left" src="..." alt="test">
						<!-- INSERTION DES IMAGES -->
					</div>
					<div class="col-lg-8">
						<div class="card-body">
							<h5 class="card-title">article.getNom</h5>
							<p class="card-text">Prix : "article.getPrix()"</p>
							<p class="card-text">Fin de l'enchère :
								"article.getFinEnchere()"</p>
							<p class="card-text">
								Vendeur : <a href="#">"article.getUser()"</a>
							</p>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>

</body>
</html>