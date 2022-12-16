<%@page import="fr.eni.javaee.bo.UserAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Import BOOTSTRAP A FAIRE DANS LE HEAD DE LA PAGE-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- Import DE LA FEUILLE DE STYLE -->
<link href="css/style.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
 <nav class="navbar bg-light" style="margin-bottom:1em;"> 
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/ServletAccueil"><img alt="Logo Yabe" style="transform: rotate(180deg); border-radius:10px;" src="
    data:image/png;base64,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
    "> - ENI-Enchères</a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Naviguation</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
      
      <% if (session.getAttribute("useraccount") != null){ %>
      <!-- HEADER CONNECTE -->
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ServletAccueil">Enchères</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ServletNouvelleVente">Vendre un article</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ServletAffichageProfil">Mon Profil</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href=" <%=request.getContextPath()%>/ServletDisconnect">Déconnexion</a>
          </li>
       
       <%}else {%>
          <!-- HEADER DECONNECTE -->
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ServletRegister">S'inscrire</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/ServletConnect">Se connecter</a>
          </li>
		
		<%} %>

          
        </ul>
      </div>
    </div>
  </div>
</nav>


