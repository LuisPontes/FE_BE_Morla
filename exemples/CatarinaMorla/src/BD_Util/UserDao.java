package BD_Util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ResourcesOBJ.CoreografiaPOJO;
import lp.utils.Utils;



public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = bdUtil.getConnection();
    }

  
    
    public boolean verifyLog(String user, String pwd){
    	PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("select * from bd_morla.users where Nome=? AND pwd=? ;");		
	        preparedStatement.setString(1, user);
	        preparedStatement.setString(2, pwd);
	        ResultSet rs = preparedStatement.executeQuery();
	
	        if (rs.next()) {	        	
	        	if ( rs.getString("Nome").trim().equals(user.trim()) ) {	        		
					if ( rs.getString("pwd").trim().equals(pwd.trim()) ) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }



	public void addConteudo(String roleName, String titulo, String ano, String sinopse, String coreografia,
			String interpretacao, String figurinos, String musica, String edicao_Musical, String fotografia,
			String video, String duracao, String class_etaria, String ap_Publicas, String agradecimentos) {
		
		PreparedStatement preparedStatement;		
		
		try {
			    preparedStatement = connection.prepareStatement("insert into "+roleName+"(titulo,ano,sinopse,coreografia,interpretacao,figurinos,musica,edicaoMusical,fotografia,video,duracao,classE,ap_publicas,agradecimentos) values (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,? );");
	            // Parameters start with 1
	            preparedStatement.setString(1, titulo);
	            preparedStatement.setString(2, ano);
	            preparedStatement.setString(3,sinopse);
	            preparedStatement.setString(4,coreografia );
	            preparedStatement.setString( 5,interpretacao);
	            preparedStatement.setString(6,figurinos );
	            preparedStatement.setString(7,musica );
	            preparedStatement.setString(8,edicao_Musical );
	            preparedStatement.setString(9,fotografia );
	            preparedStatement.setString(10,video );
	            preparedStatement.setString(11,duracao );
	            preparedStatement.setString(12,class_etaria );
	            preparedStatement.setString(13,ap_Publicas );
	            preparedStatement.setString(14,agradecimentos );
	            preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 public List<CoreografiaPOJO> getAllUsers() {
	        List<CoreografiaPOJO> users = new ArrayList<CoreografiaPOJO>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from bd_morla.Coreografia");
	            System.out.println("size: "+rs.getFetchSize());
	            while (rs.next()) {
	            	CoreografiaPOJO user = new CoreografiaPOJO();
		               user.setTitulo(rs.getString("titulo"));
		               System.out.println(rs.getString("titulo"));
		               user.setAno(rs.getString("ano"));
		               user.setSinopse(rs.getString("sinopse"));
		               user.setCoreografia(rs.getString("coreografia"));
		               user.setInterpretacao(rs.getString("interpretacao"));
		               user.setFigurinos(rs.getString("figurinos"));
		               user.setMusica(rs.getString("musica"));
		               user.setEdicaoMusical(rs.getString("edicaoMusical"));
		               user.setFotografia(rs.getString("fotografia"));
		               user.setVideo(rs.getString("video"));
		               user.setDuracao(rs.getString("duracao"));
		               user.setClassE(rs.getString("classE"));
		               user.setAp_publicas(rs.getString("ap_publicas"));
		               user.setAgradecimentos(rs.getString("agradecimentos"));
		               
	               
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return users;
	    }



	public List<CoreografiaPOJO> getAlltitulos() {
		 List<CoreografiaPOJO> users = new ArrayList<CoreografiaPOJO>();
	        try {
	            Statement statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery("select * from bd_morla.Coreografia");
	            while (rs.next()) {
	            	CoreografiaPOJO user = new CoreografiaPOJO();
		               user.setTitulo(rs.getString("titulo"));
		               users.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return users;
	}



	public String addImg_Movie(InputStream inputStream,String titulo,String tableName) {
		
		 String message;
		
         PreparedStatement statement;
		try {
				 statement = connection.prepareStatement("INSERT INTO bd_morla."+tableName+" (titulo, photo) values (?, ?)");	
				 
				 if( !titulo.trim().equals("") || titulo != null ){
			         statement.setString(1, titulo);     			            
			         if (inputStream != null) {
			             // fetches input stream of the upload file for the blob column
			             statement.setBlob(2, inputStream);
			             // sends the statement to the database server
				         int row = statement.executeUpdate();
				         if (row > 0) {
				             return message = "File uploaded and saved into database";
				         }
			         }else {
						return  message = "Error - InputStream is null!";
					}
				 }
		       
		} catch (SQLException e) {
			e.printStackTrace();
		}
		message = "Error file not saved in dataBase";
		
		return message ;
	}



//	public String makeHtml() {
//		Statement sta = null;
//		StringBuilder HTML;
//		try {			
//			 sta = connection.createStatement();
//	         ResultSet rs = sta.executeQuery("select * from bd_morla.Coreografia");
//	         
//	         HTML = new StringBuilder();
//	         
//	            while (rs.next()) {
//	            	
//	            	HTML.append("<div class='inic-temp'>");
//	            	String titulo = rs.getString("titulo");
//	            	
//	            	if ( titulo != null && !titulo.isEmpty()) {
//	            		HTML.append("<a onclick='show("+titulo.replace(" ", "")+");' class='inic-temp-title' href='#role1'>" );
//	            		HTML.append("<h1><b>"+titulo+"</b></h1></a>");
//	            		HTML.append("<div class='hide' id='"+titulo.replace(" ", "")+"' style='float: left; margin-bottom: 5%;'>");
//	            		HTML.append("<div style='float: left; width: 100%;'>");
//	            		HTML.append("<img onclick='getmodal(this)' id='img_inic' class='img-inic' src='"+titulo+"' /></div>");          		
//	            	}
//	            	
//	    			String Ano = rs.getString("ano");
//	    			if ( Ano != null && !Ano.isEmpty()) {
//	    				HTML.append("<p style='margin-bottom: 50px;'>|"+Ano+"</p>");	            		
//					}
//	    			
//	    			String Sinopse = rs.getString("sinopse");
//	    			if ( Sinopse != null && !Sinopse.isEmpty()) {
//	    				HTML.append("<p style='margin-bottom: 50px;'>"+Sinopse+"</p>");
//					}
//	    			
//	    			HTML.append("<hr class='line'>");
//	    			
//	    			String Coreografia = rs.getString("coreografia");
//	    			if ( Coreografia != null && !Coreografia.isEmpty()) {
//	    				HTML.append("<p><b>Coreografia|</b>"+Coreografia+"</p>");
//					}
//	    			
//	    			String Interpretacao = rs.getString("interpretacao");
//	    			if ( Interpretacao != null && !Interpretacao.isEmpty()) {
//	    				HTML.append("<p><b>Interpretação|</b>"+Interpretacao+"</p>");
//					}
//	    			
//	    			String Figurinos = rs.getString("figurinos");
//	    			if ( Figurinos != null && !Figurinos.isEmpty()) {
//	    				HTML.append("<p><b>Figurinos|</b>"+Figurinos+"</p>");
//					}
//	    			
//	    			String Musica = rs.getString("musica");
//	    			if ( Musica != null && !Musica.isEmpty()) {
//	    				HTML.append("<p><b>Música|</b>"+Musica+"</p>");
//					}
//	    			
//	    			String Edicao_Musical = rs.getString("edicaoMusical");
//	    			if ( Edicao_Musical != null && !Edicao_Musical.isEmpty()) {
//	    				HTML.append("<p><b>Edição Musical|</b>"+Edicao_Musical+"</p>");
//					}
//	    			
//	    			String Fotografia = rs.getString("fotografia");
//	    			if ( Fotografia != null && !Fotografia.isEmpty()) {
//	    				HTML.append("<p><b>Fotografia|</b>"+Fotografia+"</p>");
//					}
//	    			
//	    			String Video = rs.getString("video");
//	    			if ( Video != null && !Video.isEmpty()) {
//	    				HTML.append("<p><b>Vídeo|</b>"+Video+"</p>");
//					}
//	    			
//	    			String Duracao = rs.getString("duracao");
//	    			if ( Duracao != null && !Duracao.isEmpty()) {
//	    				HTML.append("<p><b>Duração|</b>"+Duracao+"</p>");
//					}
//	    			
//	    			String Class_etaria = rs.getString("classE");
//	    			if ( Class_etaria != null  && !Class_etaria.isEmpty()) {
//	    				HTML.append("<p><b>Classificação etária|</b>"+Class_etaria+"</p>");
//					}
//	    			
//	    			String Ap_Publicas = rs.getString("ap_publicas");
//	    			if ( Ap_Publicas != null && !Ap_Publicas.isEmpty() ) {
//	    				HTML.append("<p><b>Apresentações Públicas|</b> "+Ap_Publicas+"</p>");
//					}
//	    			
//	    			String Agradecimentos = rs.getString("agradecimentos");
//	    			if ( Agradecimentos != null && !Agradecimentos.isEmpty() ) {
//	    				HTML.append("<p><b>Agradecimentos|</b>"+Agradecimentos+"</p>");
//					}
//	            	
//	    			HTML.append("<hr class='line'>");
//	    			HTML.append("<div class='container cont_img'>");
//	    			
//	    			HTML.append("</div>");
//	    			HTML.append("<hr class='line'>");
//	    			HTML.append("</div>");
//	    			HTML.append("<hr style='height: 3px; border-width: 0; color: gray; background-color: gray; width: 100%;'>");
//	    			HTML.append("</div>");
//	    			
////	           html +="<div class='inic-temp'>"
////	        			+"<a onclick='show("+rs.getString("titulo").replace(" ", "")+");' class='inic-temp-title' href='#role1'>"
////	        			+"<h1><b>"+rs.getString("titulo")+"</b></h1></a>"
////	        		+"<div class='hide' id='"+rs.getString("titulo").replace(" ", "")+"' style='float: left; margin-bottom: 5%;'>"
////	        			+"<div style='float: left; width: 100%;'>"
////	        				+"<img onclick='getmodal(this)' id='img_inic' class='img-inic' src='"+rs.getString("titulo")+"' />"
////	        			+"</div>"
////	        			+"<p style='margin-bottom: 50px;'>|"+rs.getString("ano")+"</p>"
////	        			+"<p style='margin-bottom: 50px;'>"+rs.getString("titulo")+"</p>"
////	        			+"<hr class='line'>"
////	        			+"<p><b>Coreografia|</b>"+rs.getString("coreografia")+"</p>"
////	        			+"<p><b>Interpretação|</b>"+rs.getString("interpretacao")+"</p>"
////	        			+"<p><b>Figurinos|</b>"+rs.getString("figurinos")+"</p>"
////	        			+"<p><b>Música|</b>"+rs.getString("musica")+"</p>"
////	        			+"<p><b>Edição Musical|</b>"+rs.getString("edicaoMusical")+"</p>"
////	        			+"<p><b>Fotografia|</b>"+rs.getString("fotografia")+"</p>"
////	        			+"<p><b>Vídeo|</b>"+rs.getString("video")+"</p>"
//	        			
////	        			+"<p><b>Duração|</b>"+rs.getString("duracao")+"</p>"
////	        			+"<p><b>Classificação etária|</b>"+rs.getString("classE")+"</p>"
////	        			+"<p><b>Apresentações Públicas|</b> "+rs.getString("ap_publicas")+"</p>"
////	        			+"<p><b>Agradecimentos|</b>"+rs.getString("agradecimentos")+"</p>"
//
////	        			+"<hr class='line'>"
////	        			+"<div class='container cont_img'>"
//	        			
////	        				<div class='col-md-3 portfolio-item'>
////	        					<img onclick='getmodal(this)' id='img1' class='img-responsive' alt='legenda' src='./recursos/1.jpg' />
////	        				</div>		
////
////
////	        				<div id='myModal' class='modal'>
////	        					<span class='close' id='close'>×</span> <span class='pre' id='pre'><</span>
////	        					<span class='next' id='next'>></span> <img class='modal-content'
////	        						id='img01'>
////	        					<div id='caption'></div>
////	        				</div>
//	        				
////	        			+"</div>"
////	        			+"<hr class='line'>"
//
////	        			<div class='container video-principal'>
////	        				<video width='100%' height='100%' controls>
////	        					<source
////	        						src='./recursos/COREOGRAFIA/2015 DESIGNED TO FAIL/DESIGNED TO FAIL DE CIMA FINAL.mp4'
////	        						type='video/mp4'>
////	        				</video>
////
////	        			</div>
////	        			<div class='container video-secundario'>
////	        				<video width='49%' height='100%'
////	        					style='float: left; margin-right: 1%;' controls>
////	        					<source
////	        						src='./recursos/COREOGRAFIA/2015 DESIGNED TO FAIL/DESIGNED TO FAIL DE CIMA FINAL.mp4'
////	        						type='video/mp4'>
////	        				</video>
////	        				<video width='49%' height='100%'
////	        					style='float: right; margin-left: 1%;' controls>
////	        					<source
////	        						src='./recursos/COREOGRAFIA/2015 DESIGNED TO FAIL/DESIGNED TO FAIL DE CIMA FINAL.mp4'
////	        						type='video/mp4'>
////	        				</video>
////	        			</div>
//
////	        		+"</div>"
////	        		+"<hr style='height: 3px; border-width: 0; color: gray; background-color: gray; width: 100%;'>"
////	        	+"</div>";
//	            }
//	            
//	            return HTML.toString();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}



	@SuppressWarnings("unused")
	private String getIMG(String titulo) {
		Statement sta = null;
		try {			
			 sta = connection.createStatement();
	         ResultSet rs = sta.executeQuery("select photo from bd_morla.img where titulo = '"+titulo+"';");
	         return rs.getString("photo");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	public Boolean addObj(String roleName,CoreografiaPOJO obj ) {
	PreparedStatement preparedStatement;		
		
		try {
			    preparedStatement = connection.prepareStatement("insert into "+roleName+"(titulo,ano,sinopse,coreografia,interpretacao,figurinos,musica,edicaoMusical,fotografia,video,duracao,classE,ap_publicas,agradecimentos) values (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,? );");
	            // Parameters start with 1
	            preparedStatement.setString(1, obj.getTitulo());
	            preparedStatement.setString(2, obj.getAno());
	            preparedStatement.setString(3,obj.getSinopse());
	            preparedStatement.setString(4,obj.getCoreografia() );
	            preparedStatement.setString( 5,obj.getInterpretacao());
	            preparedStatement.setString(6,obj.getFigurinos() );
	            preparedStatement.setString(7,obj.getMusica() );
	            preparedStatement.setString(8,obj.getEdicaoMusical() );
	            preparedStatement.setString(9,obj.getFotografia() );
	            preparedStatement.setString(10,obj.getVideo() );
	            preparedStatement.setString(11,obj.getDuracao() );
	            preparedStatement.setString(12,obj.getClassE() );
	            preparedStatement.setString(13,obj.getAp_publicas() );
	            preparedStatement.setString(14,obj.getAgradecimentos() );
	          
	           if ( preparedStatement.executeUpdate() == 0) 
	           {
	        	   return false;
	           }
	        	   
	          
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}



	public String getHTML() {
		Statement sta = null;		
		try {			
			 sta = connection.createStatement();
	         ResultSet rs = sta.executeQuery("select * from bd_morla.Coreografia");
	         return Utils.makeHtml(rs); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	 
}