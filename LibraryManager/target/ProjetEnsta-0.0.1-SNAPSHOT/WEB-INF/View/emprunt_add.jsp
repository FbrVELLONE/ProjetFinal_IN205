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
        <h1 class="page-announce-text valign">Emprunter un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Sélectionnez le livre et le membre emprunteur</h5>
        <div class="row">
	      <form action="emprunt_add" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s6">
	            <select id="idLivre" name="idLivre" class="browser-default">
	              <option value="" disabled selected>-- Livres --</option>
                <!--Code-->
                <c:if test="${!availableBookList.isEmpty()}">
                  <c:forEach items="${availableBookList}" var="book">
                    <option value="${book.id}">"${book.title}", ${book.author}</option>
                  </c:forEach>
                </c:if>

	            </select>
	          </div>
	          <div class="input-field col s6">
	            <select id="idMembre" name="idMembre" class="browser-default">
	              <option value="" disabled selected>-- Membres --</option>
                  <!--Code-->
                  <c:if test="${!availableMemberList.isEmpty()}">
                    <c:forEach items="${availableMemberList}" var="member">
                      <option value="${member.id}">${member.firstName} ${member.lastName}</option>
                    </c:forEach>
                  </c:if>
                    
	            </select>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer l'emprunt</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
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
