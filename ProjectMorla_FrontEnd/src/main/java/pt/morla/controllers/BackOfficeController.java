package pt.morla.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import pt.morla.bo.db.interfaces.IContents;
import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_content;
import pt.morla.bo.db.models.tb_separador;

@Controller
@RequestMapping(value = { "/dashboard", "/db" })
public class BackOfficeController {

	protected static Logger log = LoggerFactory.getLogger(BackOfficeController.class);
	   
    @Autowired
    ISeparadores daoSep;
    @Autowired
    IContents daoCont;
    
    @Autowired
    private Environment props;
    
    List<tb_separador> categoriasList = null;
    List<tb_content> contentsList = null;
    
    @PostConstruct
    private void init() {
    	/*get all categorias*/
		categoriasList = (List<tb_separador>) daoSep.findAll();
		contentsList = (List<tb_content>) daoCont.findAll();
		for (tb_separador ts : categoriasList) {
			for (tb_content tc : contentsList) {
				if ( tc.getCategoria_id().equals(ts.getId()+"") ) {
					tc.setCategoria_name(ts.getNome());
				}
			}
		}
    }
    
    @RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
    	
    	model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/addcat" }, method = { RequestMethod.POST })
	public String addcat(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute tb_separador new_cat_obj) {
		
		if ( new_cat_obj.getNome()!=null) {
			new_cat_obj.setLastUpdate(new Date());
			new_cat_obj.mappingActive();
			if ( new_cat_obj.getFileDatas()!=null ) {
				new_cat_obj = doUpload(request, new_cat_obj);
			}
			log.info("Save id: "+daoSep.save(new_cat_obj));
			categoriasList = (List<tb_separador>) daoSep.findAll();
		}
		model = setAttributes(model,"gestao-Categorias");
		return "dashboard/index";
	}

	@RequestMapping(value = { "/delcat" }, method = { RequestMethod.GET })
	public String remove(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToRemove = Long.parseLong(ParameterMap.get("id")[0]);
		tb_separador catRemove = null;
		if ( idToRemove!=null ) {
			for (tb_separador t : categoriasList) {
				if( idToRemove.equals(t.getId()) || idToRemove == t.getId() ){
					catRemove = t;
				}
			}
			daoSep.remove(catRemove.getId());
			if ( catRemove.getImg()!=null ) {
				File img = new File(catRemove.getImg());
				if( img.delete() ){
					log.info("Delete Image [{}]!",catRemove.getImg());
				}else {
					log.info("Dont delete image [{}]",catRemove.getImg());
				}
			}
			categoriasList = (List<tb_separador>) daoSep.findAll();
		}
		
		model = setAttributes(model,"gestao-Categorias");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/addCont" }, method = { RequestMethod.POST })
	public String addCont(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute tb_content new_cont_obj) {
		
		if ( new_cont_obj.getCategoria_id()!=null) {
			new_cont_obj.mappingActive();
			log.info("Save id: "+daoCont.save(new_cont_obj));
			contentsList = (List<tb_content>) daoCont.findAll();
		}
		
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/delCont" }, method = { RequestMethod.GET })
	public String removeCont(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToRemove = Long.parseLong(ParameterMap.get("id")[0]);
		tb_content contRemove = null;
		if ( idToRemove!=null ) {
			for (tb_content t : contentsList) {
				if( idToRemove.equals(t.getId()) || idToRemove == t.getId() ){
					contRemove = t;
				}
			}
			daoCont.remove(contRemove.getId());
			
			contentsList = (List<tb_content>) daoCont.findAll();
		}
		
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/activeOrDesactive" }, method = { RequestMethod.GET })
	public String activeOrDesactve(HttpServletRequest request, HttpServletResponse response,Model model) {
		Map<String, String[]> ParameterMap = request.getParameterMap();
		String pagename = null,type = ParameterMap.get("type")[0];
		if ( type.startsWith("home") ) 
		{
			type = ParameterMap.get("type")[0].split("-")[1];
		}
		if ( type.equals("Categorias") ) 
		{
			daoSep.updateActiveFlag( Long.parseLong(ParameterMap.get("id")[0]),  Integer.parseInt(ParameterMap.get("value")[0]) );
			categoriasList = (List<tb_separador>) daoSep.findAll();
			pagename = "gestao-Categorias";
		}
		else if ( type.equals("Conteudos") ) 
		{
			daoCont.updateActiveFlag( Long.parseLong(ParameterMap.get("id")[0]),  Integer.parseInt(ParameterMap.get("value")[0]) );
			contentsList = (List<tb_content>) daoCont.findAll();
			pagename = "gestao-Conteudos";
		}
		if ( ParameterMap.get("type")[0].startsWith("home") ) 
		{
			pagename = "home";
		}
		model = setAttributes(model,pagename);
		return "dashboard/index";
	}
	
//	@RequestMapping(value = { "/build" }, method = { RequestMethod.GET })
//	public String build(HttpServletRequest request, HttpServletResponse response,Model model) {
//	
//		/*
//		 * criar um ficheiro estatico
//		//createHtmlIndexFile c = new createHtmlIndexFile(daoSep,daoCont,props);
//		*/
//		
//		model = setAttributes(model,"home");
//		return "dashboard/index";
//	}

	private Model setAttributes(Model model,String page) {
			model.addAttribute("catgories", categoriasList);
			model.addAttribute("contents", contentsList);
			model.addAttribute("nTotalCat", categoriasList.size());
			model.addAttribute("tb_separador", new tb_separador());
			model.addAttribute("tb_content", new tb_content());
			model.addAttribute("redirectPage", page);
		return model;
	}
	
	
	  private tb_separador doUpload(HttpServletRequest request, tb_separador myUploadForm) {
		 
		      try {
		      // Root Directory.
		      String uploadRootPath =  props.getProperty("upload.image.path");
		      System.out.println("uploadRootPath=" + uploadRootPath);
		 
		      File uploadRootDir = new File(uploadRootPath);
		      // Create directory if it not exists.
		      if (!uploadRootDir.exists()) {
		         uploadRootDir.mkdirs();
		      }
		      MultipartFile[] fileDatas = myUploadForm.getFileDatas();
		      //
		      List<File> uploadedFiles = new ArrayList<File>();
		      List<String> failedFiles = new ArrayList<String>();
		 
		      for (MultipartFile fileData : fileDatas) {
		 
		         // Client File Name
		         String name = fileData.getOriginalFilename();
		         System.out.println("Client File Name = " + name);
		 
		         if (name != null && name.length() > 0) {
		            try {
		               // Create the file at server
		               File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
		 
		               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		               stream.write(fileData.getBytes());
		               stream.close();
		               //
		               uploadedFiles.add(serverFile);
		               System.out.println("Write file: " + serverFile);
		            } catch (Exception e) {
		            	e.printStackTrace();
		               System.out.println("Error Write file: " + name);
		               failedFiles.add(name);
		            }
		            myUploadForm.setImg(uploadRootPath+"/"+name);
		         }
		      }
		     
		     return myUploadForm;
		      }catch (Exception e) {
		    	  e.printStackTrace();
		    	  return null;
			}
		 
		}
}
