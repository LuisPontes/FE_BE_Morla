<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/CatarinaMorla/css/cssAdmin.css" />
<link rel="stylesheet" type="text/css" href="/CatarinaMorla/css/modalStyle.css" />
<title>Catarina Morla - Multimedia</title>

<%-- <c:out value="${user.titulo}" /> 

--%>

</head>
<body>

	<div id="rounded">
		<img src="/CatarinaMorla/img/top_bg.gif" alt="top" />

		<div id="main" class="container">
			<h2>
				Adicionar conteudo multimedia<a href="DashBoard.jsp" style="float: right;">DashBoard</a>
			</h2>
			<hr
				style="height: 3px; border-width: 0; color: gray; background-color: gray; width: 100;">

	  
				<table border=1 style="width: 100%">
					<thead>
						<tr>
							
							<th><center>Titulo</center></th>
							<th><center>Adicionar imagem:</center></th>
							<th><center>Adicionar vidio:</center></th>
						</tr>
					</thead>
					<tbody>
					
					${html}
						<%-- <c:forEach items="${users}" var="user">
							<tr>						
							<td>${user.titulo}</td>
							<td><center>
							    <form  method="post" action="add_multimedia.jsp" enctype="multipart/form-data">
									<input type="file" name="file" multiple="multiple" required/> 
									<input  type="Hidden" name="userid" value="<c:out value="${user.titulo}"/>"/>
									 <input  type="Hidden" name="action" value="img:${user.titulo}"/>
									<input type="submit" value="Upload imagem(s)" /> 
								</form>
								</center>
							</td>
							<td ><center>
								 <form  method="post" action="add_multimedia.jsp" enctype="multipart/form-data">
									<input type="file" name="file"  multiple="multiple" required/> 
									<input  type="Hidden" name="userid" value="<c:out value="${user.titulo}"/>"/>
									<input  type="Hidden" name="action" value="movie:${user.titulo}"/>
									<input type="submit" value="Upload vidio(s)" /> 
								</form>
								</center>
							</td>
							</tr>
						</c:forEach> --%>
					</tbody>
				</table>
				
		</div>

		<div class="clear"></div>
	
	</div>
	
	

</body>
</html>