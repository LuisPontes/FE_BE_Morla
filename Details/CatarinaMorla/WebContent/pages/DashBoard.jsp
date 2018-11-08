<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/CatarinaMorla/css/cssAdmin.css" />
<title>DashBoard - Catarina Morla</title>
</head>

	<body>
	
<div id="rounded">
<img src="/CatarinaMorla/img/top_bg.gif" alt="top" />
	<div id="main" class="container" style="display:inline-block" >
		  <h2>DashBoard<a href="login.jsp" style="float:right;">Log Out</a></h2>
		  <hr style="height:3px;border-width:0;color:gray;background-color:gray;width:100;">
		
			<div style="float:left;width: 10%">				
			  <ul id="navigation" style="height: 100px">
			    <li ><a href="add_container.jsp" >Adicionar conteudos</a></li> 			   
			   	<li ><a href="DashMultimedia.jsp" >Adicionar conteudo multimedia</a></li> 			  
			    <li ><a href="show_container.jsp" >Ver todos os Conteudos</a></li> 			   
			    <li >
			    <!--  <input type="button" name="refreshWeb" value="Actualizar Web Site!">  -->
			    <a style="background-color:#FF0000" name="refreshWeb" href="DashBoard.jsp?action=ActulizarWEB" >Actualizar Web Site!</a> 
			    </li>			    	   
		      </ul>	     
		    </div>
		    <div style="float:right;width: 70%">	
		   			<font color="green">${message}</font>
		    </div>
     </div>
     
     <div class="clear"></div>
</div>
	</body>
</html>