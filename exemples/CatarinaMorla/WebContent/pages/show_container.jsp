<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/CatarinaMorla/css/cssAdmin.css" />
<title>Todos os Conteudos</title>
</head>
<body style="width: 100%">
	<div id="rounded" style="width: 80%">
		

		<div id="main" class="container" style="width: 100%">
			<h2>
				Todos os Conteudos<a href="DashBoard.jsp" style="float: right;">DashBoard</a>
			</h2>
			<hr
				style="height: 3px; border-width: 0; color: gray; background-color: gray; width: 100;">



			<table border=1 style="width: 100%">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Ano</th>
						<th>Sinopse</th>
						<th>Coreográfia</th>
						<th>Interpretação</th>
						<th>figurinos</th>
						<th>musica</th>
						<th>edicao_Musical</th>
						<th>fotografia</th>
						<th>video</th>
						<th>duracao</th>
						<th>class_etaria</th>
						<th>ap_Publicas</th>
						<th>agradecimentos</th>
						<th colspan=2>Opções</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td><c:out value="${user.titulo}" /></td>
							<td><c:out value="${user.ano}" /></td>
							<td><c:out value="${user.sinopse}" /></td>
							<td><c:out value="${user.coreografia}" /></td>
							<td><c:out value="${user.interpretacao}" /></td>
							<td><c:out value="${user.figurinos}" /></td>
							<td><c:out value="${user.musica}" /></td>
							<td><c:out value="${user.edicaoMusical}" /></td>
							<td><c:out value="${user.fotografia}" /></td>
							<td><c:out value="${user.video}" /></td>
							<td><c:out value="${user.duracao}" /></td>
							<td><c:out value="${user.classE}" /></td>
							<td><c:out value="${user.ap_publicas}" /></td>
							<td><c:out value="${user.agradecimentos}" /></td>
							<td><a href="Con?action=edit&userId=<c:out value="${user.titulo}"/>">Update</a></td>
							<td><a href="Con?action=delete&userId=<c:out value="${user.titulo}"/>">Remover</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>




		</div>

		<div class="clear"></div>
	
	</div>
</body>
</html>