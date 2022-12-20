<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h2 class="text-center" style="margin-bottom: 1em;">Nouvelle vente</h2>

	<div class="container align-items-center">
		<div class="row">
			<div class="col">
				<!-- PHOTO -->
				<img alt="" src="">
			</div>

			<div class="col">
				<!-- FORMULAIRE -->
				<form method="POST"
					action="<%=request.getContextPath()%>/ServletNouvelleVente">
					<div class="row">
							<label for="nomArticle">Article : </label> <input type="text"
								name="nomArticle" id="nomArticle">
					</div>

					<br>

					<div class="row">
							<label for="descriptionArticle">Description : </label> <input
								type="text" name="descriptionArticle" id="descriptionArticle">
					</div>

					<br>

					<div class="row">
							<label for="categorie">Catégorie : </label> <select
								class="form-select" aria-label="Default select example"
								name="categorie" id="categorie">
								<option selected>Toutes</option>
								<option value="1">Catégorie 1</option>
								<option value="2">Catégorie 2</option>
							</select>
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
							<label for="miseAPrix">Mise à prix : </label> <input
								type="number" name="miseAPrix" id="miseAPrix">
					</div>

					<br>

					<div class="row">
							<label for="debutEncheres">Début de l'enchère : </label> <input
								type="date" name="debutEncheres" id="debutEncheres">
					</div>

					<br>

					<div class="row">
							<label for="finEncheres">Fin de l'enchère : </label> <input
								type="date" name="finEncheres" id="finEncheres">
					</div>
					
					<br>
					
					<fieldset>
						<legend>Retrait</legend>
						<div class="row">

								<label for="retraitRue">Rue : </label> <input type="text"
									name="retraitRue" id="retraitRue"
									value="<%=request.getAttribute("Rue")%>">
						</div>
						
						<br>
						
						<div class="row">
								<label for="retaitCP">Code postal : </label> <input type="text"
									name="retaitCP" id="retaitCP"
									value="<%=request.getAttribute("CP")%>">
						</div>
						
						<br>
						
						<div class="row">
								<label for="retaitVille">Ville : </label> <input type="text"
									name="retaitVille" id="retaitVille"
									value="<%=request.getAttribute("Ville")%>">
						</div>
					</fieldset>
					
					<br>
					
					<div class="row">
						<input type="submit" name="enregistrer" value="Enregistrer"> 
					</div>
				</form>
				<div class="row">
					<input type="submit"  name="annuler" value="Annuler"> 
					<input type="submit"  name="annulerVente" value="Annuler la vente">
				</div>
			</div>
		</div>
	</div>



</body>

<jsp:include page="footer.jsp" />
</html>