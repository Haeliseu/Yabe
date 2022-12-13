<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>

<%@include file="header.jsp" %>
	
	
<!-- TODO RECUPERATION DE LA VARIABLE DE CONNEXION -->

<% if (checkConnect == true){ %>
	<!-- FILTRES DE RECHERCHE -->
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
				  <label class="form-check-label" for="flexRadioDefault1">
				    Achats
				  </label>
				</div>
				
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="encheresOuvertes" checked>
				  <label class="form-check-label" for="encheresOuvertes">
				    enchères ouvertes
				  </label>
				</div>
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="encheresEnCours" >
				  <label class="form-check-label" for="encheresEnCours">
				    mes enchères en cours
				  </label>
				</div>
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="encheresRemportees" >
				  <label class="form-check-label" for="encheresRemportees">
				    mes enchères remportées
				  </label>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6">
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" >
				  <label class="form-check-label" for="flexRadioDefault2">
				    Mes Ventes
				  </label>
				</div>
				
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="ventesEnCours">
				  <label class="form-check-label" for="ventesEnCours">
				    mes ventes en cours
				  </label>
				</div>
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="ventesNonDebutees">
				  <label class="form-check-label" for="ventesNonDebutees">
				    ventes non débutées
				  </label>
				</div>
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="ventesTerminees">
				  <label class="form-check-label" for="ventesTerminees">
				    ventes terminées
				  </label>
				</div>
			</div>
		</div>
	</div>
<%} %>
	
	
</body>
</html>