package pt.morla.app.controllers;

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

import pt.morla.app.Contants;
import pt.morla.app.bo.db.interfaces.ICategorias;
import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.models.categorias_tb;
import pt.morla.app.bo.db.models.projectos_tb;
import pt.morla.app.bo.db.models.user_obj;

@Controller
@RequestMapping(value = { "/bo" })
public class BOController {

	
	protected static Logger log = LoggerFactory.getLogger(BOController.class);
	   

	user_obj user = null;
	
	@Autowired
	ICategorias daoCat;
    @Autowired
    IProjectos daoPro;
    
    @Autowired
    private Environment props;
    
    List<categorias_tb> categoriasList = null;
    List<projectos_tb> projectosList = null;
    
    @PostConstruct
    private void init() {
    	categoriasList = (List<categorias_tb>) daoCat.findAll();
		projectosList = (List<projectos_tb>) daoPro.findAll();
    }

	@RequestMapping(value = { "" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
    
		if( user!=null && user.isIsauth() ) {
			init();
	    	model = setAttributes(model,"home");
			return "dashboard/index";
		}
		else if ( user==null && userObj.getName()==null && userObj.getPass()==null && userObj.isIsauth()==false ) {
   
    		model.addAttribute("userObj", new user_obj());
    		return "dashboard/page_login";
		}
		else if(userObj.getName()!=null && userObj.getPass()!=null ) {
			//autenticar
			
			userObj.setIsauth(true);
			user = userObj;
			model = setAttributes(model,"home");
			return "dashboard/index";
		}
		return "dashboard/page_login";
    	
	}
    
	/**
	 * *Categorias
	 * */
	@RequestMapping(value = { "/addcat" }, method = { RequestMethod.POST })
	public String addcat(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute categorias_tb new_cat_obj) {
		
		if ( new_cat_obj.getNome()!=null) {
			new_cat_obj.setLastUpdate(new Date());
			new_cat_obj.mappingActive();
			if ( new_cat_obj.getFileDatas()!=null ) {
				new_cat_obj.setImg_backGround(doUpload(request, new_cat_obj.getFileDatas()));
			}
			log.info("Save id: "+daoCat.save(new_cat_obj));
			categoriasList = (List<categorias_tb>) daoCat.findAll();
		}
		model = setAttributes(model,"gestao-Categorias");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/delcat" }, method = { RequestMethod.GET })
	public String remove(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToRemove = Long.parseLong(ParameterMap.get("id")[0]);
		categorias_tb catRemove = null;
		if ( idToRemove!=null ) {
			for (categorias_tb t : categoriasList) {
				if( idToRemove.equals(t.getId()) || idToRemove == t.getId() ){
					catRemove = t;
				}
			}
			daoCat.remove(catRemove.getId());
			if ( catRemove.getImg_backGround()!=null ) {
				File img = new File(catRemove.getImg_backGround());
				if( img.delete() ){
					log.info("Delete Image [{}]!",catRemove.getImg_backGround());
				}else {
					log.info("Dont delete image [{}]",catRemove.getImg_backGround());
				}
			}
			categoriasList = (List<categorias_tb>) daoCat.findAll();
		}
		
		model = setAttributes(model,"gestao-Categorias");
		return "dashboard/index";
	}
	
	/**PROJECTOS*/

	@RequestMapping(value = { "/addCont" }, method = { RequestMethod.POST })
	public String addCont(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute projectos_tb new_cont_obj) {
		
		if ( new_cont_obj.getCategoria_id()!=null) {
			new_cont_obj.mappingActive();
			log.info("Save id: "+daoPro.save(new_cont_obj));
			projectosList = (List<projectos_tb>) daoPro.findAll();
		}
		
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/delCont" }, method = { RequestMethod.GET })
	public String removeCont(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToRemove = Long.parseLong(ParameterMap.get("id")[0]);
		projectos_tb contRemove = null;
		if ( idToRemove!=null ) {
			for (projectos_tb t : projectosList) {
				if( idToRemove.equals(t.getId()) || idToRemove == t.getId() ){
					contRemove = t;
				}
			}
			daoPro.remove(contRemove.getId());
			
			projectosList = (List<projectos_tb>) daoPro.findAll();
		}
		
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	/**********************************************************************************************************************/
	/* ACTIVETOR */
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
			daoCat.updateActiveFlag( Long.parseLong(ParameterMap.get("id")[0]),  Integer.parseInt(ParameterMap.get("value")[0]) );
			categoriasList = (List<categorias_tb>) daoCat.findAll();
			pagename = "gestao-Categorias";
		}
		else if ( type.equals("Conteudos") ) 
		{
			daoPro.updateActiveFlag( Long.parseLong(ParameterMap.get("id")[0]),  Integer.parseInt(ParameterMap.get("value")[0]) );
			projectosList = (List<projectos_tb>) daoPro.findAll();
			pagename = "gestao-Conteudos";
		}
		if ( ParameterMap.get("type")[0].startsWith("home") ) 
		{
			pagename = "home";
		}
		model = setAttributes(model,pagename);
		return "dashboard/index";
	}
	
    /*UPLOAD FILES*/
	 private String doUpload(HttpServletRequest request, MultipartFile[] multipartFiles) {
		 
	      try {
	      // Root Directory.
	      String uploadRootPath =  props.getProperty("upload.image.path");
	      System.out.println("uploadRootPath=" + uploadRootPath);
	 
	      File uploadRootDir = new File(uploadRootPath);
	      // Create directory if it not exists.
	      if (!uploadRootDir.exists()) {
	         uploadRootDir.mkdirs();
	      }
	      MultipartFile[] fileDatas = multipartFiles;
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
	               return props.getProperty("image.path")+"/"+name;
	               
	            } catch (Exception e) {
	            	e.printStackTrace();
	               System.out.println("Error Write file: " + name);
	               failedFiles.add(name);
	            }
	            
	         }
	      }
	      }catch (Exception e) {
	    	  e.printStackTrace();
	    	  return null;
		}
		return null;
	 
	}
    private Model setAttributes(Model model,String page) {
		model.addAttribute("catgories", categoriasList);
		model.addAttribute("projectos", projectosList);
		model.addAttribute("nTotalCat", categoriasList.size());
		model.addAttribute("nTotalPro", projectosList.size());
		model.addAttribute("categoriasObj", new categorias_tb());
		model.addAttribute("projectosObj", new projectos_tb());
		model.addAttribute("TypesCategorias",Contants.TypesCategorias);
		model.addAttribute("redirectPage", page);
	return model;
}
}
