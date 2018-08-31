package pt.morla.bo.build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.core.env.Environment;

import pt.morla.bo.db.interfaces.IContents;
import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_content;
import pt.morla.bo.db.models.tb_separador;

public class createHtmlIndexFile {
	
	
	private Environment props;

	List<tb_content> cont = null;
	List<tb_separador> cat = null;
	
	public createHtmlIndexFile(ISeparadores daoSep, IContents daoCont,Environment props) {
		this.props=props;
		try {
			
			cont = daoCont.findAllActive();
			if (cat!=null && !cat.isEmpty()) {
				for (tb_content t : cont) {
					System.out.println("\n\n"+t.toString()+"\n\n");
				}
			}
			
			cat = daoSep.findAllActive();
			Collections.sort(cat,Comparator.comparingInt(tb_separador::getOrderView));
			if (cat!=null && !cat.isEmpty()) {
				for (tb_separador t : cat) {
					System.out.println("\n\n"+t.toString()+"\n\n");
				}
			}
			
			String menu = buildMenu(props.getProperty("build.index.menu.path"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String buildMenu(String myfile) throws IOException {
		
		String path = "/home/lpontes/Desktop/sapo-workspace/FE_BE_Morla/ProjectMorla_FrontEnd/src/main/resources/templates/fe_static/fragment/menu.html";
		String subStart = "=?=";
		String subFinish = "=/?=";
		String subSeparator = "=|=";
		StringBuilder content = new StringBuilder();
		String[] parts = null;
		
		try {
			
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       //System.out.println(line+"\n\n");
			       if ( line.contains(subStart) ) {
			    	   String replace = line.substring(line.indexOf(subStart)+3, line.indexOf(subFinish));
			    	   if ( replace.contains(subSeparator) ) {
			    		  parts = replace.split(subSeparator);
			    		  for (String p : parts) {
							
						}
			    	   }else {
			    		  parts =replace.split(":");
			    	   }
			    	   System.out.println(replace);
			       }else {
			    	   content.append(line);
			       }
			    }
			}
	       
	        // Files.write(path, replaced);
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
