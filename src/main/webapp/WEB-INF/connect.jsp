<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Se connecter</title>
</head>
<body>
<header>
<jsp:include page="header.jsp" />
</header>
<div class="container">
 <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
         <div class="card" style="width: auto; box-shadow: 10px 5px 5px gray;">
            <div class="card-body">
               <form method="POST" action="" style="text-align: center;">
               <label for="pseudo">Identifiant :</label>
                  <input type="pseudo" name="identifiant" id="identifiant" class="form-control" required/>
                  <br>
                  <br>
                  <label for="mot_de_passe">Mot de passe :</label>
                  <input type="password" name="mot_de_passe" id="mot_de_passe" class="form-control" required/>
                  <br /><br />
                  <input type="checkbox" name="rememberMe" value="Se souvenir de moi" id="rememberMe"/>
                  <label for="rememberMe" id="rememberMe">Se souvenir de moi</label>
                  <br>
                  <br>
                  <input class="btn btn-primary" type="submit" name="formconnexion" value="Connexion"/>
                   <a id="mot_de_passe_oublié class="text-forgot-password" href="">Mot de passe oublié</a>
                  <br>
                  <br>
                  <input class="btn btn-primary" type="submit" name="formconnexion" value="Créer un compte"/>
               </form>
            </div>
         </div>

         
        
      </div>
</div>
</div>

</body>
</html>