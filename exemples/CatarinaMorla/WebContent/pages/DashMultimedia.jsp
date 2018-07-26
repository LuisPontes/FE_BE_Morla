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

	 			 ${popup}
				<table border=1 style="width: 100%">
					<thead>
						<tr>
							
							<th><center>Titulo</center></th>
							<th><center>Adicionar imagem:</center></th>
							<th><center>Adicionar vidio:</center></th>
						</tr>
					</thead>
					<tbody>					
					
							<tr>						
							<td>
								<center>
									<select >
									   <option value="NONE" label="--- Select ---"/>
									  ${options} 
									</select>
								</center>
							</td>
							<td><center>
							   <form method="POST" action="uploadMultipleFile" enctype="multipart/form-data">
									File1 to upload: <input type="file" name="file"><br /> 
									Name1: <input type="text" name="name"><br /> <br /> 
									File2 to upload: <input type="file" name="file"><br /> 
									Name2: <input type="text" name="name"><br /> <br />
									<input type="submit" value="Upload"> Press here to upload the file!
								</form>
								</center>
							</td>
							<td ><center>
								 <form  method="post" action="uploadMovie" enctype="multipart/form-data">
									<input type="file" name="file"  multiple="multiple" required/> 									
									<input type="submit" value="Upload vidio(s)" /> 
								</form>
								</center>
							</td>
							</tr>
										
					</tbody>
				</table>
				
		</div>

		<div class="clear"></div>
	
	</div>
	
	

</body>
</html>