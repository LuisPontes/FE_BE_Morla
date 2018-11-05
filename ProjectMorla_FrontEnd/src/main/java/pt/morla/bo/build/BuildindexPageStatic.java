package pt.morla.bo.build;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import pt.morla.bo.db.models.tb_content;
import pt.morla.bo.db.models.tb_separador;

public class BuildindexPageStatic {

	
	StringBuilder INDEX_PAGE = new StringBuilder();
	List<tb_separador> categoriasList = null;
	List<tb_content> contentsList = null;
	    
	public BuildindexPageStatic(List<tb_separador> categoriasList, List<tb_content> contentsList) {
		this.categoriasList=categoriasList;
		this.contentsList=contentsList;
	}

	public void build() {
		
		INDEX_PAGE.append("<!DOCTYPE HTML>\n" + 
				"<html xmlns:th=\"http://www.thymeleaf.org\"\n" + 
				"	xmlns:dt=\"http://www.thymeleaf.org/dandelion/datatables\">\n" + 
				"<head >"
				+ "<title>Catarina Morla</title>\n" + 
				"				<meta charset=\"utf-8\">\n" + 
				"				<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
				"				 	<link type=\"text/css\" th:href=\"@{/fe/css/bootstrap.min.css}\" rel=\"stylesheet\"/>\n" + 
				"				 	<link type=\"text/css\" th:href=\"@{/fe/css/bootstrap.css}\" rel=\"stylesheet\"/>\n" + 
				"				 	<link type=\"text/css\" th:href=\"@{/fe/css/bodyStyle.css}\" rel=\"stylesheet\" />\n" + 
				"				 	<link type=\"text/css\" th:href=\"@{/fe/css/full-slider.css}\" rel=\"stylesheet\" /> "+
				"					<link type=\"text/css\" th:href=\"@{/css/menu.css}\" rel=\"stylesheet\" />"+
				"</head>");
		
		INDEX_PAGE.append("<body>" );
		INDEX_PAGE.append(" <!-- Navigation -->\n" + 
				"    <nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n" + 
				"        <div class=\"container\">" + 
				"            <!-- Brand and toggle get grouped for better mobile display -->\n" + 
				"            <div class=\"navbar-header\">\n" + 
				"                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n" + 
				"                ");
		for (tb_separador tc : categoriasList) {
			if ( tc.getOrderView()==0) {
				INDEX_PAGE.append(" <span class=\"sr-only\">Toggle navigation</span>");
			}else {
				INDEX_PAGE.append(" <span class=\"icon-bar\"></span>");
			}
		}
		
		INDEX_PAGE.append(" </button>" + 
				"                \n" + 
				"                <a onclick=\"bodySlide('"+categoriasList.get(0).getOrderView()+"')\" class=\"navbar-brand\" href=\"#role0\">"+categoriasList.get(0).getNome()+"</a> \n" + 
				"               \n" + 
				"                 \n" + 
				"            </div>"+
				" <!-- Collect the nav links, forms, and other content for toggling -->\n" + 
				"            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" + 
				"                <ul class=\"nav navbar-nav\" id=\"menu_head\">");
		
		for (tb_separador tc : categoriasList) {
			if (tc.getOrderView()!=0) {
				INDEX_PAGE.append("<li> <a onclick=\"|bodySlide('"+tc.getOrderView()+"')|\" th:href=\"@{'#role"+tc.getOrderView()+"'}\" th:text=\""+tc.getNome()+"\" ></a></li> ");
			}
		}
		
		INDEX_PAGE.append("<li id=\"loader\">\n" + 
				"                        \n" + 
				"                    </li>\n" + 
				"                </ul>\n" + 
				"            </div>\n" + 
				"            \n" + 
				"            \n" + 
				"            <!-- /.navbar-collapse -->\n" + 
				"        </div>\n" + 
				"     </nav> \n" + 
				"      <script type=\"text/javascript\" th:src=\"@{/fe/js/menu.js}\"></script>");

      
		INDEX_PAGE.append("\n" + 
				"	<!-- Full Page Image Background Carousel Header -->\n" + 
				"	<header id=\"myCarousel\" class=\"carousel slide\">\n" + 
				"\n" + 
				"		<!-- Indicators -->\n" + 
				"		<ol class=\"carousel-indicators\" id=\"indx\">\n" + 
				"			<li id=\"ind0\" data-target=\"#myCarousel\" data-slide-to=\"0\"\n" + 
				"				class=\"active\"></li>\n" + 
				"			<li id=\"ind1\" data-target=\"#myCarousel\" data-slide-to=\"1\"></li>\n" + 
				"			<li id=\"ind2\" data-target=\"#myCarousel\" data-slide-to=\"2\"></li>\n" + 
				"			<li id=\"ind3\" data-target=\"#myCarousel\" data-slide-to=\"3\"></li>\n" + 
				"			<li id=\"ind4\" data-target=\"#myCarousel\" data-slide-to=\"4\"></li>\n" + 
				"		</ol>\n" + 
				"\n" + 
				"		<!-- Wrapper for Slides -->\n" + 
				"		<div class=\"carousel-inner\">\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"			<div class=\"item active\" id=\"0\">\n" + 
				"				<!-- Set the second background image using inline CSS below. -->\n" + 
				"				<div class=\"fill\" style=\"background-image: url(fe/recursos/bg.jpg);\"></div>\n" + 
				"				<div class=\"carousel-caption\">\n" + 
				"					<h2>\n" + 
				"						<a href=\"#role0\"> Catarina Morla </a>\n" + 
				"					</h2>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"			<div class=\"item\" id=\"1\">\n" + 
				"				<!-- Set the first background image using inline CSS below. -->\n" + 
				"				<div class=\"fill\" style=\"background-image: url(fe/recursos/1.jpg);\"></div>\n" + 
				"				<div class=\"carousel-caption\">\n" + 
				"					<h2>\n" + 
				"						<a href=\"#role1\">Coreografia</a>\n" + 
				"					</h2>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"			<div class=\"item\" id=\"2\">\n" + 
				"				<!-- Set the second background image using inline CSS below. -->\n" + 
				"				<div class=\"fill\" style=\"background-image: url(fe/recursos/4.jpg);\"></div>\n" + 
				"				<div class=\"carousel-caption\">\n" + 
				"					<h2>\n" + 
				"						<a href=\"#role2\">Figurinos</a>\n" + 
				"					</h2>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"			<div class=\"item\" id=\"3\">\n" + 
				"				<!-- Set the third background image using inline CSS below. -->\n" + 
				"				<div class=\"fill\" style=\"background-image: url(fe/recursos/2.jpg);\"></div>\n" + 
				"				<div class=\"carousel-caption\">\n" + 
				"					<h2>\n" + 
				"						<a href=\"#role3\">Vestuário</a>\n" + 
				"					</h2>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"			<div class=\"item\" id=\"4\">\n" + 
				"				<!-- Set the third background image using inline CSS below. -->\n" + 
				"				<div class=\"fill\" style=\"background-image: url(fe/recursos/3.jpg);\"></div>\n" + 
				"				<div class=\"carousel-caption\">\n" + 
				"					<h2>\n" + 
				"						<a href=\"#role4\">S.C.A.R.F.S</a>\n" + 
				"					</h2>\n" + 
				"				</div>\n" + 
				"			</div>\n" + 
				"			<!-- ************************************************************************************************* -->\n" + 
				"		</div>\n" + 
				"\n" + 
				"		<!-- Controls -->\n" + 
				"		<a onclick=\"bodySlide(10)\" class=\"left carousel-control\"\n" + 
				"			href=\"#myCarousel\" data-slide=\"prev\"> <span class=\"icon-prev\"></span>\n" + 
				"		</a> <a onclick=\"bodySlide(10)\" class=\"right carousel-control\"\n" + 
				"			href=\"#myCarousel\" data-slide=\"next\"> <span class=\"icon-next\"></span>\n" + 
				"		</a>\n" + 
				"\n" + 
				"	</header>\n" + 
				"\n" + 
				"	<!-- Page Content -->\n" + 
				"	<div class=\"container\">\n" + 
				"\n" + 
				"		<!-- ********************** Page Content catarina morla ******************************************-->\n" + 
				"		<div class=\"hidden\" id=\"role0\">\n" + 
				"			<h2>Page Content catarina morla -</h2>\n" + 
				"		</div>\n" + 
				"\n" + 
				"		<!-- ******************************* Page Content Coreográfia **************************************-->\n" + 
				"		<div class=\"hidden\" id=\"role1\">\n" + 
				"			<h2>Page Content coreografia -</h2>\n" + 
				"		</div>\n" + 
				"\n" + 
				"\n" + 
				"		<!-- ****************************** Page Content Figurinos ******************************************-->\n" + 
				"		<div class=\"hidden\" id=\"role2\">\n" + 
				"			<h2>Page Content catarina morla -</h2>\n" + 
				"		</div>\n" + 
				"\n" + 
				"		<!-- ******************************** Page Content Vestuário ****************************************-->\n" + 
				"		<div class=\"hidden\" id=\"role3\">\n" + 
				"			<h2>Page Content catarina morla -</h2>\n" + 
				"		</div>\n" + 
				"\n" + 
				"		<!-- ******************************** Page Content S.C.A.R.F.S ***************************************-->\n" + 
				"		<div class=\"hidden\" id=\"role4\">\n" + 
				"			<h2>Page Content catarina morla -</h2>\n" + 
				"		</div>\n" + 
				"\n" + 
				"	</div>\n" + 
				"	\n" + 
				"	<footer>\n" + 
				"			\n" + 
				"					<!-- jQuery -->\n" + 
				"				    <script type=\"text/javascript\" th:src=\"@{/fe/js/jquery.js}\" ></script>\n" + 
				"				    <!-- Bootstrap Core JavaScript -->\n" + 
				"					<script type=\"text/javascript\" th:src=\"@{/fe/js/bootstrap.min.js}\" /></script>\n" + 
				"					<!-- MENu -->\n" + 
				"					<script type=\"text/javascript\" th:src=\"@{/fe/js/scripts.js}\" /></script>\n" + 
				"						<!-- Custom JavaScript -->\n" + 
				"					<script>\n" + 
				"					    $('.carousel').carousel({\n" + 
				"					        interval: 0//5000 //changes the speed\n" + 
				"					    })\n" + 
				"    				</script>\n" + 
				"			\n" + 
				"		</footer>" + 
				"	\n" + 
				"</body>\n" + 
				"	\n" + 
				"</html>");
		
		
		
	}

	
	public StringBuilder getINDEX_PAGE() {
		return INDEX_PAGE;
	}

	public void saveAndReplaceFile() throws IOException {
		
		File file = new File("indextestes.html");
		if ( file.exists() ) {
			System.out.println("file exites!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n");
		}
		
//		BufferedWriter writer = null;
//		try {
//		    writer = new BufferedWriter(new FileWriter(file));
//		    writer.write(INDEX_PAGE.toString());
//		} finally {
//		    if (writer != null) writer.close();
//		}
	
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("indextestes.html"))) {
			bw.append(INDEX_PAGE);//Internally it does aSB.toString();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
