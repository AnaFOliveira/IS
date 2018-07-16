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
	<table border="2">
		<tr bgcolor="#9acd32">
			<td style="text-align: center"><b> Category </b></td>
			<td style="text-align: center"><b> Title </b></td>
			<td style="text-align: center"><b> Artist </b></td>
			<td style="text-align: center"><b> Year </b></td>
			<td style="text-align: center"><b> Rating </b></td>
			<td style="text-align: center"><b> Number of reviews </b></td>
			<td style="text-align: center"><b> Price </b></td>
		</tr>
		<c:forEach var="item_vinyl" items="${vinyls}">
			<tr>
				<td style="text-align: center">
					<div>${item_vinyl.genre}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.title}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.artist}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.year}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.rating}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.nreview}</div>
				</td>
				<td style="text-align: center">
					<div>${item_vinyl.price}</div>
				</td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach var="item_vinyl" items="${vinyls}">
		<h4>
			<div>${item_vinyl.title}</div>
		</h4>
		<table border="2">
			<tr bgcolor="#9acd32">
				<td style="text-align: center"><b> Song </b></td>
				<td style="text-align: center"><b> Duration </b></td>
				<td style="text-align: center"><b> Rating </b></td>
				<td style="text-align: center"><b> Number of Reviews </b></td>
				<td style="text-align: center"><b> Price </b></td>
			</tr>
			<c:forEach var="item_music" items="${item_vinyl.music}">
				<tr>
					<td style="text-align: center">
						<div>${item_music.name}</div>
					</td>
					<td style="text-align: center">
						<div>${item_music.duration}</div>
					</td>
					<td style="text-align: center">
						<div>${item_music.srating}</div>
					</td>
					<td style="text-align: center">
						<div>${item_music.snreview}</div>
					</td>
					<td style="text-align: center">
						<div>${item_music.sprice}</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>
</body>
</html>