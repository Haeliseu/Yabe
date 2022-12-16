<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>

	<%@include file="header.jsp"%>

	<h2 class="text-center" style="margin-bottom: 1em;">Nouvelle vente</h2>

	<div class="container align-items-center">
		<div class="row">
			<div class="col">
				<!-- PHOTO -->
				<img alt="test" src="">
			</div>

			<div class="col">
				<!-- FORMULAIRE -->
				<form method="POST" action="<%=request.getContextPath()%>/ServletNouvelleVente">
					<div class="row">
						<div class="col">
							<label for="nomArticle">Article : </label> <input type="text"
								name="nomArticle" id="nomArticle">
						</div>
					</div>

					<br>

					<div class="row">
						<div class="col">
							<label for="descriptionArticle">Description : </label> <input
								type="text" name="descriptionArticle" id="descriptionArticle">
						</div>
					</div>

					<br>

					<div class="row">
						<div class="col">
							<label for="categorie">Catégorie : </label> <select
								class="form-select" aria-label="Default select example"
								name="categorie" id="categorie">
								<option selected>Toutes</option>
								<option value="1">Catégorie 1</option>
								<option value="2">Catégorie 2</option>
							</select>
						</div>
					</div>

					<br>
					
					
					<!--  EN COMMENTAIRE POUR LE MOMENT
					<div class="row">
						<div class="col">
							<label for="photoDeLArticle">Photo de l'article : </label> <input
								type="file" id="photoDeLArticle" name="photoDeLArticle"
								accept="image/png, image/jpeg">
						</div>
					</div>

					<br> -->

					<div class="row">
						<div class="col">
							<label for="miseAPrix">Mise à prix : </label> <input
								type="number" name="miseAPrix" id="miseAPrix"> 
						</div>
					</div>

					<br>

					<div class="row">
						<div class="col">
							<label for="debutEncheres">Début de l'enchère : </label> <input
								type="date" name="debutEncheres" id="debutEncheres">
						</div>
					</div>

					<br>

					<div class="row">
						<div class="col">
							<label for="finEncheres">Fin de l'enchère : </label> <input
								type="date" name="finEncheres" id="finEncheres">
						</div>
					</div>
					<br>
<fieldset><legend><h5 style="margin: 1em;">Retrait</h5></legend>
					<div class="row">
					
						<div class="col">
						
						
							
							<label for="retraitRue">Rue : </label> <input type="text"
								name="retraitRue" id="retraitRue">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col">
							<label for="retaitCP">Code postal : </label> <input type="text"
								name="retaitCP" id="retaitCP">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col">
							<label for="retaitVille">Ville : </label> <input type="text"
								name="retaitVille" id="retaitVille">
						</div>
					</div>
					</fieldset>
					<br>
					<div class="row">
							<input type="submit" value="Enregistrer">
							<br>
					</div>
				</form>
				<div class="row">
					<input type="submit" value="Annuler"> 
					<br> 
					<input type="submit" value="Annuler la vente">
				</div>
			</div>
		</div>
	</div>
</body>
</html>