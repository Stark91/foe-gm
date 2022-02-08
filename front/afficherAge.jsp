<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Liste des Ages</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/css/style.css"></c:url>" />
        <link rel="shortcut icon" href="#">
	</head>
	<body>
		<%@ include file="sidenav.jsp" %>
		<div class="content">
			<div class="age-grid-list grid-list">
				<c:forEach items="${mapAge}" var="age" varStatus="boucle">
               		<a class="age-grid-list-button grid-list-button button" href="<c:url value="GestionGM"></c:url>?idAge=<c:out value="${age.value.id}"></c:out>">
	               		<span><c:out value="${age.value.name}"></c:out></span>
               		</a>
				</c:forEach>
			</div>
		</div>
	</body>
</html>