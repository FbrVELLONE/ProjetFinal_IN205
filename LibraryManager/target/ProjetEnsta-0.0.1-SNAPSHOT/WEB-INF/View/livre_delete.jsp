<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche livre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Suppression du livre n°${book.id}</h5> <!-- TODO : afficher l'id du livre au lieu de 42 -->
        <div class="row">
          <p>Êtes-vous sûr de vouloir supprimer le livre ${book.title} de ${book.author} (code ${book.isbn}) ?</p> <!-- TODO : compl�ter les trois informations ci-contre -->
	      <form action="livre_delete" method="post" class="col s12">
            <input type="hidden" value="${book.id}" name="id"> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer
	            <i class="material-icons right">delete</i>
	          </button>
	          <a class="btn waves-effect waves-light orange" href="/LibraryManager/livre_details?id=${book.id}">Annuler</a> <!-- TODO : remplacer idDuLivre par l'id du livre -->
	        </div>
	      </form>
	    </div>	    
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
