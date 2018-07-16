<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vinyls</title>
</head>
<body>
	<form>
		<select name="option">
			<c:forEach var="item" items="${options}">
				<option value="${item.key}">${item.value}</option>
			</c:forEach>
		</select> <input type="submit" value="Click">
	</form>
	<h1>Resultados</h1>
	<c:forEach var="item_title" items="${titulos}">
		<div>${item_title}</div>
	</c:forEach>

	<c:forEach var="item_artist" items="${artistas}">
		<div>${item_artist}</div>
	</c:forEach>

</body>
</html>