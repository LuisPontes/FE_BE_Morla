package lp.utils;

import javax.servlet.http.HttpServletRequest;

import ResourcesOBJ.CoreografiaPOJO;

public class Validate {

	public static CoreografiaPOJO validateRequest(HttpServletRequest request){
		
		CoreografiaPOJO obj = new CoreografiaPOJO();
		String titulo = request.getParameter("titulo");
		if ( titulo != null && !titulo.isEmpty() ) 
		{
			obj.setTitulo(titulo);
		}
		String Ano = request.getParameter("Ano");
		if ( Ano != null && !Ano.isEmpty() ) 
		{
			obj.setAno(Ano);
		}
		String Sinopse = request.getParameter("Sinopse");
		if ( Sinopse != null && !Sinopse.isEmpty() ) 
		{
			obj.setSinopse(Sinopse);
		}
		String Coreografia = request.getParameter("Coreografia");
		if ( Coreografia != null && !Coreografia.isEmpty() ) 
		{
			obj.setCoreografia(Coreografia);
		}
		String Interpretacao = request.getParameter("Interpretacao");
		if ( Interpretacao != null && !Interpretacao.isEmpty() ) 
		{
			obj.setInterpretacao(Interpretacao);
		}
		String Figurinos = request.getParameter("Figurinos");
		if ( Figurinos != null && !Figurinos.isEmpty() ) 
		{
			obj.setFigurinos(Figurinos);
		}
		String Musica = request.getParameter("Musica");
		if ( Musica != null && !Musica.isEmpty() ) 
		{
			obj.setMusica(Musica);
		}
		String Edicao_Musical = request.getParameter("Edicao_Musical");
		if ( Edicao_Musical != null && !Edicao_Musical.isEmpty() ) 
		{
			obj.setEdicaoMusical(Edicao_Musical);
		}
		String Fotografia = request.getParameter("Fotografia");
		if ( Fotografia != null && !Fotografia.isEmpty() ) 
		{
			obj.setFotografia(Fotografia);
		}
		String Video = request.getParameter("Video");
		if ( Video != null && !Video.isEmpty() ) 
		{
			obj.setVideo(Video);
		}
		String Duracao = request.getParameter("Duracao");
		if ( Duracao != null && !Duracao.isEmpty() ) 
		{
			obj.setDuracao(Duracao);
		}
		String Class_etaria = request.getParameter("Class_etaria");
		if ( Class_etaria != null && !Class_etaria.isEmpty() ) 
		{
			obj.setClassE(Class_etaria);
		}
		String Ap_Publicas = request.getParameter("Ap_Publicas");
		if ( Ap_Publicas != null && !Ap_Publicas.isEmpty() ) 
		{
			obj.setAp_publicas(Ap_Publicas);
		}
		String Agradecimentos = request.getParameter("Agradecimentos");
		if ( Agradecimentos != null && !Agradecimentos.isEmpty()  ) 
		{
			obj.setAgradecimentos(Agradecimentos);
		}	
		return obj;
	}
}
