package pt.morla.bo.build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import pt.morla.bo.db.interfaces.IContents;
import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_content;
import pt.morla.bo.db.models.tb_separador;

public class createHtmlIndexFile {
	
	@Autowired
	private Environment props;

	List<tb_content> cat = null;
	List<tb_separador> cont = null;
	
	public createHtmlIndexFile(ISeparadores daoSep, IContents daoCont) {

		try {
			cat = daoCont.findAllActive();
			Comparator<tb_separador> CaTcmp = new Comparator<tb_separador>() {
				@Override
				public int compare(tb_separador o1, tb_separador o2) {
					return Integer.valueOf(o1.getActive()).compareTo(Integer.valueOf(o2.getActive()));
				}
			};
			Collections.sort(cat, CaTcmp);
			
			if (cat!=null && !cat.isEmpty()) {
				for (tb_content t : cat) {
					System.out.println("\n\n"+t.toString()+"\n\n");
				}
			}
			
			cont = daoSep.findAllActive();
			if (cat!=null && !cat.isEmpty()) {
				for (tb_separador t : cont) {
					System.out.println("\n\n"+t.toString()+"\n\n");
				}
			}
			
			String menu = buildMenu(props.getProperty("build.index.menu.path"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String buildMenu(String myfile) throws IOException {
		String subStart = "=?=";
		String subFinish = "=/?=";
		String subSeparator = "=|=";
		StringBuilder content = new StringBuilder();
		String[] parts = null;
		
		try {
			String path = "/home/lpontes/Desktop/sapo-workspace/FE_BE_Morla/ProjectMorla_FrontEnd/src/main/resources/templates/fe/fragment/menu.html";
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       //System.out.println(line+"\n\n");
			       if ( line.contains(subStart) ) {
			    	   String replace = line.substring(line.indexOf(subStart)+3, line.indexOf(subFinish));
			    	   if ( replace.contains(subSeparator) ) {
			    		  parts = replace.split(subSeparator);
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
