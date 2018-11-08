package lp.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ResourcesOBJ.CoreografiaPOJO;

public class Utils {
	
	public static String makeHtml(ResultSet rs) {		
		 
		StringBuilder HTML = new StringBuilder();
        try{
        	
           while (rs.next()) {
           	
		           	HTML.append("<div class='inic-temp'>");
		           	String titulo = rs.getString("titulo");
		           	
		           	if ( titulo != null && !titulo.isEmpty()) 
		           	{
		           		HTML.append("<a onclick='show("+titulo.replace(" ", "")+");' class='inic-temp-title' href='#role1'>" );
		           		HTML.append("<h1><b>"+titulo+"</b></h1></a>");
		           		HTML.append("<div class='hide' id='"+titulo.replace(" ", "")+"' style='float: left; margin-bottom: 5%;'>");
		           		HTML.append("<div style='float: left; width: 100%;'>");
		           		HTML.append("<img onclick='getmodal(this)' id='img_inic' class='img-inic' src='"+titulo+"' /></div>");          		
		           	}
		           	
		   			String Ano = rs.getString("ano");
		   			if ( Ano != null && !Ano.isEmpty()) 
		   			{
		   				HTML.append("<p style='margin-bottom: 50px;'>|"+Ano+"</p>");	            		
					}
		   			
		   			String Sinopse = rs.getString("sinopse");
		   			if ( Sinopse != null && !Sinopse.isEmpty()) 
		   			{
		   				HTML.append("<p style='margin-bottom: 50px;'>"+Sinopse+"</p>");
					}
		   			
		   			HTML.append("<hr class='line'>");
		   			
		   			String Coreografia = rs.getString("coreografia");
		   			if ( Coreografia != null && !Coreografia.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Coreografia|</b>"+Coreografia+"</p>");
					}
		   			
		   			String Interpretacao = rs.getString("interpretacao");
		   			if ( Interpretacao != null && !Interpretacao.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Interpretação|</b>"+Interpretacao+"</p>");
					}
		   			
		   			String Figurinos = rs.getString("figurinos");
		   			if ( Figurinos != null && !Figurinos.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Figurinos|</b>"+Figurinos+"</p>");
					}
		   			
		   			String Musica = rs.getString("musica");
		   			if ( Musica != null && !Musica.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Música|</b>"+Musica+"</p>");
					}
		   			
		   			String Edicao_Musical = rs.getString("edicaoMusical");
		   			if ( Edicao_Musical != null && !Edicao_Musical.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Edição Musical|</b>"+Edicao_Musical+"</p>");
					}
		   			
		   			String Fotografia = rs.getString("fotografia");
		   			if ( Fotografia != null && !Fotografia.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Fotografia|</b>"+Fotografia+"</p>");
					}
		   			
		   			String Video = rs.getString("video");
		   			if ( Video != null && !Video.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Vídeo|</b>"+Video+"</p>");
					}
		   			
		   			String Duracao = rs.getString("duracao");
		   			if ( Duracao != null && !Duracao.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Duração|</b>"+Duracao+"</p>");
					}
		   			
		   			String Class_etaria = rs.getString("classE");
		   			if ( Class_etaria != null  && !Class_etaria.isEmpty()) 
		   			{
		   				HTML.append("<p><b>Classificação etária|</b>"+Class_etaria+"</p>");
					}
		   			
		   			String Ap_Publicas = rs.getString("ap_publicas");
		   			if ( Ap_Publicas != null && !Ap_Publicas.isEmpty() ) 
		   			{
		   				HTML.append("<p><b>Apresentações Públicas|</b> "+Ap_Publicas+"</p>");
					}
		   			
		   			String Agradecimentos = rs.getString("agradecimentos");
		   			if ( Agradecimentos != null && !Agradecimentos.isEmpty() ) 
		   			{
		   				HTML.append("<p><b>Agradecimentos|</b>"+Agradecimentos+"</p>");
					}
		           	
		   			HTML.append("<hr class='line'>");
		   			HTML.append("<div class='container cont_img'>");
		   			
		   			HTML.append("</div>");
		   			HTML.append("<hr class='line'>");
		   			HTML.append("</div>");
		   			HTML.append("<hr style='height: 3px; border-width: 0; color: gray; background-color: gray; width: 100%;'>");
		   			HTML.append("</div>");
           		}
        }catch (Exception e) {
			return null;
		}
        
        return HTML.toString();        
	
	}

	public static String makeTableMultimedia(List<CoreografiaPOJO> rs) {
		StringBuilder html = new StringBuilder();
		 for (CoreografiaPOJO obj: rs) {
			html.append("<tr>"						
						+"<td>"+obj.getTitulo()+"</td>"
								+"<td><center>"
						+"<form  method='post' action='add_multimedia.jsp?action=img:"+obj.getTitulo().replace(" ", "")+"' enctype='multipart/form-data'>"
								+"<input type='file' name='file' multiple='multiple' required/> "
//								+"<input  type='Hidden' name='userid' value='"+obj.getTitulo()+"/>"
//								+"<input  type='Hidden' name='action' value='img:"+obj.getTitulo()+"'/>"
							+"<input type='submit' value='Upload imagem(s)' /> "
						+"</form>"
						+"</center>"
					+"</td>"
					+"<td ><center>"
						+"<form  method='post' action='add_multimedia.jsp?action=movie:"+obj.getTitulo().replace(" ", "")+"' enctype='multipart/form-data'>"
							+"<input type='file' name='file'  multiple='multiple' required/>" 
//							+"<input  type='Hidden' name='userid' value='"+obj.getTitulo()+"/>"
//							+"<input  type='Hidden' name='action' value='movie:"+obj.getTitulo()+"'/>"
							+"<input type='submit' value='Upload vidio(s)' /> "
						+"</form>"
						+"</center>"
					+"</td>"
					+"</tr>");
	}
		
	
		return html.toString();
	}
	
	
}
