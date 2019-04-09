<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TFG View</title>
</head>
<body>
	<shiro:user>
    Welcome back <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
    <hr>
		<h3>Detalles del TFG:</h3>
		<ul>
			<li>Título: ${tfg.title}</li>
			<li>Autor: ${tfg.name}</li>
			<li>Email: ${tfg.email}</li>
			<li>Tutor: ${tfg.advisor.name}</li>
			<li>Estado: ${tfg.status}</li>
		</ul>
		<c:if test="${tfg.status == 3}">
		<form action="Form4TFGServlet" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="email" value="${tfg.email}" />
    		<input type="file" name="file" />
    		<button type="submit">Subir memoria</button>
		</form>
		</c:if>
		<c:if test="${tfg.status > 3}">
		<form action="ServeFileServlet" method="get">
			<input type="hidden" name="email" value="${tfg.email}" />
			<button type="submit">Descargar</button>
		</form>
		</c:if>
	</shiro:user>
</body>
</html>