package pt.morla.app.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    String separatorFiles = null; 
    @PostConstruct
    private void init() {
    	categoriasList = (List<categorias_tb>) daoCat.findAll();
		projectosList = (List<projectos_tb>) daoPro.findAll();
		for (projectos_tb p : projectosList) {
			if ( p.getFoto_galeria()!=null ) {
				p.setListPathsFotoGaleria(p.getFoto_galeria().split(props.getProperty("separator.files")));
			}
		}
		separatorFiles = props.getProperty("separator.files");
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
			if ( userObj.getName().equals("morla")) {
				if ( userObj.getPass().equals("morla")) {
					userObj.setIsauth(true);
					user = userObj;
					model = setAttributes(model,"home");
					return "dashboard/index";
				}
			}
		}
		model.addAttribute("userObj", new user_obj());
		return "dashboard/page_login";
    	
	}
    
	/**Categorias*/
	@RequestMapping(value = { "/addcat","/upCat" }, method = { RequestMethod.POST })
	public String addcat(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute categorias_tb new_cat_obj) {

		if (new_cat_obj.getNome() != null) {

			if (request.getRequestURL().toString().endsWith("/upCat")) {
				remove(request, response, model);
			}

			if (new_cat_obj.getFileDatas() != null) {
					new_cat_obj.setImg_backGround(doUpload(request, new_cat_obj.getFileDatas(), props.getProperty("upload.capa.path")));
			}

			
			new_cat_obj.setLastUpdate(new Date());
			new_cat_obj.mappingActive();

			log.info("Save id: " + daoCat.save(new_cat_obj));
		}
		
		categoriasList = (List<categorias_tb>) daoCat.findAll();
		
		model = setAttributes(model,"gestao-Categorias");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/editCat" }, method = { RequestMethod.GET })
	public String editCat(HttpServletRequest request, HttpServletResponse response,Model model){
		
		model = setAttributes(model,"gestao-Categorias");
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToEdit = Long.parseLong(ParameterMap.get("id")[0]);
		
		categorias_tb categoriasObj = null;
		for (categorias_tb p : categoriasList) {
			if (p.getId()==idToEdit) {
				categoriasObj = p;
				 break;
			}
		}
//		if ( projectosObj.getFoto_galeria()!=null && !projectosObj.getFoto_galeria().equals("")) {
//						
//		}
//		if ( projectosObj.getFoto_galeria()!=null && !projectosObj.getFoto_galeria().equals("")) {
//			
//		}
		if ( categoriasObj!=null) {
			model.addAttribute("categoriasObj", categoriasObj);
			model.addAttribute("editMode", "TYPE_Categoria");
		}
		
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
				File img = new File(props.getProperty("upload.capa.path")+File.separator+catRemove.getImg_backGround());
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
	
	/**CONTEUDOS */

	@RequestMapping(value = { "/addCont","/upCont" }, method = { RequestMethod.POST })
	public String add_OR_upCont(
			HttpServletRequest request, 
			HttpServletResponse response,
			Model model,
			@ModelAttribute projectos_tb new_cont_obj,
			@RequestParam("file") MultipartFile[] files			
			){
		
		try {	
			List<projectos_tb> projectBackUp = null;
			if ( request.getRequestURL().toString().endsWith("/upCont") ) 
			{
				projectBackUp = daoPro.findbyId(new_cont_obj.getId());
				removeCont(request, response, model);
			}	
			try{
			
//					if ( new_cont_obj.getVideo_link()!=null && new_cont_obj.getVideo_link().equals(",") ) {
//						new_cont_obj.setVideo_link("");
//					}
//					if ( new_cont_obj.getVideo_link()!=null && new_cont_obj.getVideo_link().startsWith(",") ) {
//						new_cont_obj.setVideo_link(new_cont_obj.getVideo_link().replaceAll(",", ""));
//					}
					
					if ( files!=null && files.length > 0 ) {
						if( new_cont_obj.getFoto_galeria()==null || new_cont_obj.getFoto_galeria().isEmpty() ) {
							new_cont_obj.setFoto_galeria(doUpload(request, files, props.getProperty("upload.image.path")));
						}else {
							new_cont_obj.setFoto_galeria(new_cont_obj.getFoto_galeria()+separatorFiles+doUpload(request, files, props.getProperty("upload.image.path")));
						}
					}
					if ( new_cont_obj.getFileDatas()!=null && new_cont_obj.getFileDatas()[0].getSize()>0) {
						new_cont_obj.setImg_capa(doUpload(request, new_cont_obj.getFileDatas(), props.getProperty("upload.capa.path")));
					}
					
				new_cont_obj.mappingActive();
				
				log.info("Save id: "+daoPro.save(new_cont_obj));
				
			}catch (Exception e) {
				log.info("\n\nError__:__BACKUP["+projectBackUp.get(0).toString()+"] ->Save id: "+daoPro.save(projectBackUp.get(0)));
			}
			projectosList = (List<projectos_tb>) daoPro.findAll();
	
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/editCont" }, method = { RequestMethod.GET })
	public String editCont(HttpServletRequest request, HttpServletResponse response,Model model){
		
		model = setAttributes(model,"gestao-Conteudos");
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToEdit = Long.parseLong(ParameterMap.get("id")[0]);
		projectos_tb projectosObj = null;
		for (projectos_tb p : projectosList) {
			if (p.getId()==idToEdit) {
				 projectosObj = p;
				 break;
			}
		}
//		if ( projectosObj.getFoto_galeria()!=null && !projectosObj.getFoto_galeria().equals("")) {
//						
//		}
//		if ( projectosObj.getFoto_galeria()!=null && !projectosObj.getFoto_galeria().equals("")) {
//			
//		}
		if ( projectosObj!=null) {
			model.addAttribute("projectosObj", projectosObj);
			for (categorias_tb c : categoriasList) {
				if ( projectosObj.getCategoria_id().equals(c.getId().toString()) ) {
					model.addAttribute("editMode", c.getType());
					break;
				}
			}
		}
		
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
			
			if ( !contRemove.getFoto_galeria().equals("") || contRemove.getFoto_galeria()!=null ) {
				for (String  path : contRemove.getFoto_galeria().split(separatorFiles) ) {
					File img = new File( props.getProperty("upload.image.path")+File.separator+path);
					if( img.delete() ){
						log.info("Delete Image [{}]!",path);
					}else {
						log.info("Dont delete image [{}]",path);
					}
				}
			}
		
			daoPro.remove(contRemove.getId());
			projectosList = (List<projectos_tb>) daoPro.findAll();
		}
		
		model = setAttributes(model,"gestao-Conteudos");
		return "dashboard/index";
	}
	
	/**********************************************************************************************************************/
	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String logout(Model model) {
    
		user=null;
		model.addAttribute("userObj", new user_obj());
		return "dashboard/page_login";
    	
	}
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
		else if ( type.equals("projecto") ) 
		{
			daoPro.updateActiveFlag( Long.parseLong(ParameterMap.get("id")[0]),  Integer.parseInt(ParameterMap.get("value")[0]) );
			projectosList = (List<projectos_tb>) daoPro.findAll();
			pagename = "gestao-Conteudos";
		}
		if ( ParameterMap.get("type")[0].startsWith("home") ) 
		{
			pagename = "home";
		}
		response.setHeader(HttpHeaders.LOCATION, "/bo");
		
		System.out.println(request.getRequestDispatcher("/bo"));
		model = setAttributes(model,pagename);
		return "dashboard/index";
	}
	
    /*UPLOAD FILES*/
	private String doUpload(HttpServletRequest request, MultipartFile[] multipartFiles,String uploadRootPath) {

		 StringBuilder pathFotoGalary = new StringBuilder();

	      try {
	 
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
	               pathFotoGalary.append(name+separatorFiles);
	               
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
	    
	    if ( pathFotoGalary.toString().endsWith(separatorFiles) ) 
	    {
			return pathFotoGalary.toString().substring(0, pathFotoGalary.toString().length()-3);
		}
	    else 
		{
			return pathFotoGalary.toString();
		}
		
	 
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
		model.addAttribute("editMode", "notEDIT");
	return model;
}
}
