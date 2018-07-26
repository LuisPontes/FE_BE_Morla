
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/CatarinaMorla/css/cssAdmin.css" />

<title>Catarina Morla-Adicionar conteudos</title>
</head>
<body>



<div id="rounded">
<img src="/CatarinaMorla/img/top_bg.gif" alt="top" />

<div id="main" class="container">
    <h2>Adicionar conteudos!<a href="DashBoard.jsp" style="float:right;">DashBoard</a></h2>
    <hr style="height:3px;border-width:0;color:gray;background-color:gray;width:100;">
       
    <div class="clear"></div>
	
	<div class="" id="div_text" >
		 <br /> 
			 <form  action='add_container.jsp' name="frmAddUser">
		        <h1 >Template:		       
		       <select name="roleName" required>
		         	<option value="" selected='selected'></option>
				    <option value="1">Catarina Morla</option>
				    <option value="2" >Coreográfia</option>
				    <option value="3">Figurinos</option>
				    <option value="4">Vestuário</option>
				    <option value="5">S.C.A.R.F.S</option>
				</select>
				</h1>	        
		        <br /> 
		        
		        <div style="margin: 0 auto;width: 75%;">
		       			 <h1 style="width:100%;text-align: center;" >Titulo</h1>
		       			  	 <input style="width:100%" type="text" name="titulo" value="" required />
		       			    <br /> 
		       			 
		       	</div>
		  
						<br /> 
	            <div class="info"><h1>Ano:</div><div class="inText"><input  class="inText" type="date" name="Ano"  id="Ano"  value="" required/></h1></div>
						<br>
				<div class="info"><h1>Sinopse:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Sinopse"  id="Sinopse"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Coreografia:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Coreografia"  id="Coreografia"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Interpretacao:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Interpretacao"  id="Interpretacao"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Figurinos:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Figurinos"  id="Figurinos"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Musica:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Musica"  id="Musica"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Edicao Musical:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Edicao_Musical"  id="Edicao_Musical"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Fotografia:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Fotografia"  id="Fotografia"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Video:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Video"  id="Video"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Duracao:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Duracao"  id="Duracao"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Classe etaria:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Class_etaria"  id="Class_etaria"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Ap_Publicas:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Ap_Publicas"  id="Ap_Publicas"  value="" ></textarea></div>
						<br>
				<div class="info"><h1>Agradecimentos:</h1></div><div class="inText"><textarea  class="inText"type="textarea" name="Agradecimentos"  id="Agradecimentos"  value="" ></textarea></div>
						<br/>	    
						 <input  type="Hidden" name="action" value="add_text"/>
						 <input type="submit" value="Save" class="bt_next" style="width: 250px;height: 40px;margin: 2px;"/>  
        </form>
				<br>
					<br>
	
 
   </div>
  
  
    </div>
     <div class="clear"></div>
	
   
	
</div>


</body>
</html>