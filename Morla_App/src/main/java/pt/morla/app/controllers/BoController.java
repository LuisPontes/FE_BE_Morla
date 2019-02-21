package pt.morla.app.controllers;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.security.MD5Encoder;
import org.json.JSONObject;
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
import pt.morla.app.FindAndReturnTextBetweenChar;
import pt.morla.app.bo.db.interfaces.ICategorias;
import pt.morla.app.bo.db.interfaces.IImages;
import pt.morla.app.bo.db.interfaces.IMenu;
import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.interfaces.IUsers;
import pt.morla.app.bo.db.models.categorias_tb;
import pt.morla.app.bo.db.models.images_tb;
import pt.morla.app.bo.db.models.menu_obj;
import pt.morla.app.bo.db.models.projectos_tb;
import pt.morla.app.bo.db.models.user_obj;

@Controller
@RequestMapping(value = { "/bo" })
public class BoController {
	
	protected static Logger log = LoggerFactory.getLogger(BoController.class);
	
	user_obj user = null;
	@Autowired
	ICategorias daoCat;
    @Autowired
    IProjectos daoPro;
    @Autowired
    IMenu daoMen;
    @Autowired
    IImages daoImg;  
    @Autowired
    IUsers daoU;  
    @Autowired
    private Environment props;
   
    
    private String ipServer = null;
    private List<categorias_tb> categoriasList = null;
    private List<projectos_tb> projectosList = null;
    private List<images_tb> imagesList = null;
    private HashMap<String, images_tb> imagesMap =null;
    private menu_obj menuObj =null;
    
    @PostConstruct
    private void init() {
    	//rasp_ethenet  eth0 
    	//local_wifi - wlp2s0
    	//getIpMachine("wlp2s0")
    	
    	log.info(">>>>>> Start Back Office Conttroller");
    	
    	
    	ipServer = getIpMachine(props.getProperty("network.interface"))+":"+props.getProperty("port.apache.server");
    	if (ipServer==null) {
			new Throwable("Ip Server is null!! [network.interface = "+props.getProperty("network.interface")+"] - [port.apache.server = "+props.getProperty("port.apache.server")+"]");
		}
    	log.info("Server IP ... "+ipServer);    	
    	
    	categoriasList = (List<categorias_tb>) daoCat.findAll(); 
    	log.info("Object categoriaList -> "+categoriasList.toString());
		
		projectosList = (List<projectos_tb>) daoPro.findAll();
		log.info("Object projectosList -> "+projectosList.toString());
		
		List<menu_obj> menuObj_list = (List<menu_obj>) daoMen.findAll();
		if ( !menuObj_list.isEmpty()) {
			menuObj = menuObj_list.get(0);
		}else {
			menuObj = new menu_obj();
		}
		log.info("Object menuObj_list -> "+menuObj_list.toString());
		imagesList = daoImg.findAll();
		log.info("Object imagesList -> "+imagesList.toString());
		imagesMap = createImagesMap();
    }
    
   
    
    private Model setAttributes(Model model,String page) {
    	/*OBJECTS DATA*/
		model.addAttribute("catgories", categoriasList);		
		model.addAttribute("projectos", projectosList);
		model.addAttribute("menuObj", menuObj);
		model.addAttribute("imagens",imagesList);
		model.addAttribute("nTotalCat", categoriasList.size());
		model.addAttribute("nTotalPro", projectosList.size());
		
		/*NEWS OBJECTS*/
		model.addAttribute("categoriasObj", new categorias_tb());
		model.addAttribute("projectosObj", new projectos_tb());
		
		/*CATEGORAY TYPE*/
		model.addAttribute("TypesCategorias",Contants.TypesCategorias);
		/*URL REDIRECT*/
		model.addAttribute("redirectPage", page);
		/*MODE EDIT*/
		model.addAttribute("editMode", "notEDIT");
		/*SERVER IP*/
		model.addAttribute("ipServer", ipServer);
		
		return model;
	}
    /*Controler paths*/
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
			user_obj U = (daoU.findByName(userObj.getName()).size() > 0 ? daoU.findByName(userObj.getName()).get(0) : null );
			if ( U != null && userObj.getPass().equals(U.getPass())) {
					userObj.setIsauth(true);
					userObj.setId(U.getId());
					user = userObj;
					model = setAttributes(model,"home");
					log.info("LOG IN -> "+userObj.toString());
				return "dashboard/index";
			}
			else if ( userObj.getName().equals("morla")) {
						if ( userObj.getPass().equals("morla")) {
								userObj.setIsauth(true);
								user = userObj;
								model = setAttributes(model,"home");
								log.info("LOG IN -> "+userObj.toString());
							return "dashboard/index";
						}
			}
			
		}
		model.addAttribute("userObj", new user_obj());
		return "dashboard/page_login";
    	
	}
    /*** DePLOY	 */
	@RequestMapping(value = { "/deploy" }, method = { RequestMethod.GET })
	public String deploy(
			HttpServletRequest request, 
			HttpServletResponse response,
			Model model) {
		
		String command = String.format(Contants.COMMANDBASE_WGET, Contants.SERVER_IP+Contants.SERVER_PORT ,Contants.PATH_SITE_PROD);	
		if( execCmd(command) ) {
			
		
			File f = new File(Contants.PATH_SITE_PROD+Contants.FOLDER_APACHE_NAME);
			if (f.exists() && f.isDirectory() ) {
				execCmd("sudo rm -r "+Contants.PATH_SITE_PROD+Contants.FOLDER_APACHE_NAME);
			}
			
			
			String moveCmd = String.format("sudo mv %s %s", Contants.PATH_SITE_PROD+Contants.SERVER_IP+Contants.SERVER_PORT,Contants.PATH_SITE_PROD+Contants.FOLDER_APACHE_NAME);
					//"sudo mv /var/www/html/127.0.0.1:8090/ /var/www/html/morla.site";
			execCmd(moveCmd);
			execCmd(String.format("sudo chmod 777 %s",Contants.PATH_SITE_PROD+Contants.FOLDER_APACHE_NAME));
			replaceLink(Contants.PATH_SITE_PROD+Contants.FOLDER_APACHE_NAME+"/index.html");
			

		}
		model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	/*================================MENU POINTS=====================*/

	@RequestMapping(value = { "/addMenu" }, method = { RequestMethod.POST })
	public String addMenu(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute menu_obj obj) {
		try {
			for (menu_obj m : daoMen.findAll()) {
				daoMen.remove(m.getId());	
			}
			
			obj.setId(Long.valueOf(1));
			daoMen.save(obj);	
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model = setAttributes(model,"home");
		return "dashboard/index";
	}


	/*================================CATEGORIAS POINTS=====================*/
	
	/* adicionar e update*/
	@RequestMapping(value = { "/addcat","/upCat" }, method = { RequestMethod.POST })
	public String addcat(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute categorias_tb new_cat_obj) {

		if (new_cat_obj.getNome() != null) {
			if (request.getRequestURL().toString().endsWith("/upCat")) {
				remove(request, response, model);
			}
			// SAVE CAPA
			if (new_cat_obj != null && new_cat_obj.getFileDatas().length > 0) {
				for (MultipartFile file : new_cat_obj.getFileDatas() ) {
					if ( file.getOriginalFilename()!=null && !file.getOriginalFilename().equals("null")) {
						File f = doUpload(file, props.getProperty("upload.capa.path"),props.getProperty("apache.capa.path"), null,false);
						if (f != null) {
							new_cat_obj.setImg_backGround(props.getProperty("apache.capa.path")+f.getName());
						}
						
					}
				}
			}
			new_cat_obj.setLastUpdate(new Date());
			new_cat_obj.mappingActive();

			if (request.getRequestURL().toString().endsWith("/addcat")) 
			{
				log.info("Save id: " + daoCat.save(new_cat_obj));
			}
			else if (request.getRequestURL().toString().endsWith("/upCat")) 
			{
				log.info("Save id: " + daoCat.update(
														new_cat_obj.getId(),
														new_cat_obj.getNome(),
														new_cat_obj.getImg_backGround(),
														new_cat_obj.getCor_backGround(),
														new_cat_obj.getOrderView(),
														new_cat_obj.getUrl(),
														new_cat_obj.getActive_flag(),
														new_cat_obj.getType()
													));
			}
			
		}
		
		categoriasList = (List<categorias_tb>) daoCat.findAll();
		
		//model = setAttributes(model,"gestao-Categorias");
		init();
		model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	/*EDIT*/
	@RequestMapping(value = { "/editCat" }, method = { RequestMethod.GET })
	public String editCat(HttpServletRequest request, HttpServletResponse response,Model model){
		
		model = setAttributes(model,"gestao-Categorias");		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToEdit = Long.parseLong(ParameterMap.get("id")[0]);
		
		categorias_tb categoriasObj = null;
		for (categorias_tb p : categoriasList) {
			if (p.getId().equals(idToEdit)) {
				categoriasObj = p;
				 break;
			}
		}

		if ( categoriasObj!=null) {
			model.addAttribute("categoriasObj", categoriasObj);
			model.addAttribute("editMode", "TYPE_Categoria");
		}
		return "dashboard/index";
	}
	/*REMOVE*/
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
			
			if ( catRemove.getImg_backGround()!=null && !catRemove.getImg_backGround().equals("") ) {		
				String[] sName = catRemove.getImg_backGround().split("/");
				removeImage(props.getProperty("upload.capa.path")+sName[sName.length-1]);
			}
			categoriasList = (List<categorias_tb>) daoCat.findAll();
		}
		
		//model = setAttributes(model,"gestao-Categorias");
		init();
		model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	
	/*======================= CONTEUDOS POINTS =======================*/
	
	/*ADICIONA CONTEUDOS*/
	@RequestMapping(value = { "/addCont","/upCont" }, method = { RequestMethod.POST })
	public String add_OR_upCont(
			HttpServletRequest request, 
			HttpServletResponse response,
			Model model,
			@ModelAttribute projectos_tb new_cont_obj	
			){
		
		try {	
			
			projectos_tb projectosObj_orig = null;
			
			if ( request.getRequestURL().toString().endsWith("/upCont") ) 
			{
				for (projectos_tb p : projectosList) {
					if (p.getId().equals(new_cont_obj.getId())) {
						projectosObj_orig = p;
						 break;
					}
				}
			}				
			try{				
				// SAVE CAPA
				if (new_cont_obj != null &&  new_cont_obj.getFileDatas()!=null &&  !new_cont_obj.getFileDatas()[0].getOriginalFilename().equals("") ) {
						for (MultipartFile file : new_cont_obj.getFileDatas() ) {
							
							if ( file!=null && projectosObj_orig!=null && projectosObj_orig.getImg_capa()!=null && !projectosObj_orig.getImg_capa().endsWith(file.getOriginalFilename())) 
							{
								String[] sName = projectosObj_orig.getImg_capa().split("/");
								removeImage(props.getProperty("upload.capa.path")+sName[sName.length-1]);
							}
							if ( file.getOriginalFilename()!=null && !file.getOriginalFilename().equals("null")) {
								File f = doUpload(file, props.getProperty("upload.capa.path"),props.getProperty("apache.capa.path"), null,false);
								if (f != null) {
									new_cont_obj.setImg_capa(props.getProperty("apache.capa.path")+f.getName());
								}
							}
							
						}
				}else {
					if (projectosObj_orig!=null && projectosObj_orig.getImg_capa()!=null) {
						new_cont_obj.setImg_capa(projectosObj_orig.getImg_capa());
					}					
				}
				
				new_cont_obj.mappingActive();				
				log.info("Save id: "+daoPro.save(new_cont_obj));				
				
			}catch (Exception e) {
				log.info("\n\nError__:__BACKUP[] ->Save id: ");
				e.printStackTrace();
			}
			projectosList = (List<projectos_tb>) daoPro.findAll();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		//model = setAttributes(model,"gestao-Conteudos");
		init();
		model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	/*EDIT*/
	@RequestMapping(value = { "/editCont" }, method = { RequestMethod.GET })
	public String editCont(HttpServletRequest request, HttpServletResponse response,Model model){
		
		model = setAttributes(model,"gestao-Conteudos");		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToEdit = Long.parseLong(ParameterMap.get("id")[0]);
		projectos_tb projectosObj = null;
		
		for (projectos_tb p : projectosList) {
			if (p.getId().equals(idToEdit)) {
				 projectosObj = p;
				 break;
			}
		}
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
	
	/*REMOVE*/
	@RequestMapping(value = { "/delCont" }, method = { RequestMethod.GET })
	public String removeCont(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idToRemove = Long.parseLong(ParameterMap.get("id")[0]);
		projectos_tb contRemove = null;
		if ( idToRemove!=null ) {
			for (projectos_tb t : projectosList) {
				if( idToRemove.equals(t.getId()) || idToRemove == t.getId() ){
					contRemove = t;
					break;
				}
			}
			daoPro.remove(contRemove.getId());
			
			//delete imagem de capa
			if ( contRemove.getImg_capa()!=null && !contRemove.getImg_capa().equals("") ) {
				String[] sName = contRemove.getImg_capa().split("/");
				removeImage(props.getProperty("upload.capa.path")+sName[sName.length-1]);
			}		
			//delete foto gallary
			
			for (Entry<String, images_tb> imgR : imagesMap.entrySet()) {
				if ( imgR.getValue().getIdFather().equals(idToRemove) ) {
						daoImg.remove(imgR.getValue().getId());
						removeImage(imgR.getValue().getPath_up()+imgR.getValue().getTitulo());
				}
			}
			
			projectosList = (List<projectos_tb>) daoPro.findAll();
		}
		
		//model = setAttributes(model,"gestao-Conteudos");
		init();
		model = setAttributes(model,"home");
		return "dashboard/index";
	}
	
	/*===================LOGOUT / SETTINGS=========*/
	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String logout(Model model) {
    
		user=null;
		model.addAttribute("userObj", new user_obj());
		return "dashboard/page_login";
    	
	}
	@RequestMapping(value = { "/settings" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String settings(Model model,@ModelAttribute user_obj userObj) {
    
		model.addAttribute("userObj", new user_obj());
		if (user == null || !user.isIsauth() ) {
			return "dashboard/page_login";
		}else		
			if ( 	user.isIsauth() && 
					userObj !=null &&
					userObj.getPass() != null &&
					!userObj.getPass().isEmpty() &&
					!userObj.getPassOld().isEmpty() &&
					userObj.getPassOld().equals(user.getPass())
					
				) 
			{
				// SAVE NEW PASS
				//MD5Encoder md5 = new MD5Encoder();
				//userObj.setPass();
				if ( user.getId() != null ) {
					daoU.remove(user.getId());
				}				
				daoU.save(userObj);
				model = setAttributes(model,"home");
				return "dashboard/index";
			}
			return "dashboard/page_setting";
    	
	}
	/*=====================METHODS AUX================*/
	
	/*Create map images*/
	 private HashMap<String, images_tb> createImagesMap(){
	    	imagesMap = new HashMap<>();    	
	    	List<images_tb> imagesList = daoImg.findAll();
	    	if (imagesList.size()>0) {    		
				for (images_tb img : imagesList) {						
					imagesMap.put(String.valueOf(img.getId()), img);
				}
			}
	    	return imagesMap;
	 }
	 
	/*GALLARY*/	
	@RequestMapping(value = { "/addGallary" }, method = { RequestMethod.POST,RequestMethod.GET })
	public String addGallary(HttpServletRequest request, HttpServletResponse response, Model model, Long id_father) {

		model = setAttributes(model, "gestao-Conteudos");
		Long idCont = null;
		if (id_father == null) {
			Map<String, String[]> ParameterMap = request.getParameterMap();
			idCont = Long.parseLong(ParameterMap.get("id")[0]);
		} 
		else 
		{
			idCont = id_father;
		}
		projectos_tb projectosObj = null;
		for (projectos_tb p : projectosList) {
			if (p.getId().equals(idCont) ) {
				projectosObj = p;
				break;
			}
		}
		if (projectosObj != null) {
			model.addAttribute("projectosObj", projectosObj);
			model.addAttribute("editMode", "TYPE_IMAGES");
		}

		return "dashboard/index";

	}
	/*GALLARY END POINT DEL IMAGE*/
	
	@RequestMapping(value = { "/delImage" }, method = { RequestMethod.POST,RequestMethod.GET })
	public String delImage(HttpServletRequest request, HttpServletResponse response, Model model)  {
	
				Map<String, String[]> ParameterMap = request.getParameterMap();
				Long idImgDel = Long.parseLong(ParameterMap.get("id")[0]);
				Long idProjFather = Long.parseLong(ParameterMap.get("idProj")[0]);
				
				daoImg.remove(idImgDel);
				removeImage(imagesMap.get(idImgDel+"").getPath_up()+imagesMap.get(idImgDel+"").getTitulo());
				
				return addGallary(request, response, model, idProjFather);
				
	}
	/*GALLARY END POINT*/	
	@RequestMapping(value = { "/server/uploads" }, method = { RequestMethod.POST,RequestMethod.GET })
	public JSONObject uploads(HttpServletRequest request, HttpServletResponse response, @RequestParam("qqfile") MultipartFile file)  {

		Map<String, String[]> ParameterMap = request.getParameterMap();
		Long idCont = Long.parseLong(ParameterMap.get("id")[0]);			
	
		File f = null;
		JSONObject json1 = new JSONObject();
		try {
				
					
				projectos_tb projAddImage = null;
					if ( idCont!=null ) {
						for (projectos_tb t : projectosList) {
							if( idCont.equals(t.getId()) || idCont == t.getId() ){
								projAddImage = t;
								break;
							}
						}
					}
				String saveDirectory =props.getProperty("upload.image.path")+projAddImage.getId()+"/";
			
				 f = doUpload(file, saveDirectory,props.getProperty("apache.image.path")+projAddImage.getId()+"/", projAddImage.getId(),true);
				
				if (f!=null) {
			            json1.put("success", true);
				}else {
						 json1.put("success", false);
				}
				response.getWriter().print(json1);
			    response.flushBuffer();
			    
		} catch (Exception e) {
			e.printStackTrace();			
		}
       
		 return json1;
		
	}
	
	/*** upload File**/
	private File doUpload(MultipartFile file,String saveDirectory,String showDirectory,Long idFather,boolean saveInBd) {

	      // Create directory if it not exists.
	File uploadRootDir = new File(saveDirectory);
	if (!uploadRootDir.exists()) {
	      uploadRootDir.mkdirs();
	 }
	      
      String name = file.getOriginalFilename();
      log.info("Client File Name = " + name);
      File serverFile = null;
      
      if (name != null && name.length() > 0) {
         try {
            // Create the file at server
            serverFile = new File(saveDirectory+name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();           
            log.info("Write file: " + serverFile);
            log.info("Save image in Server Folder [id="+idFather+"]... OK");
            
            if (saveInBd) {//SAVE IN BD
                images_tb new_obj_img = new images_tb();
                new_obj_img.setTitulo(file.getOriginalFilename());
                new_obj_img.setPath_up(saveDirectory);
                new_obj_img.setPath_show(showDirectory+name);
                new_obj_img.setIdFather(idFather.longValue());
                long id = daoImg.save(new_obj_img);
    			log.info("Save image[id="+idFather+"] in Data Base [id_bd="+id+"]... OK");
    			//refresh
    			imagesList = daoImg.findAll();
    			imagesMap = createImagesMap();
			}
           
            
         } catch (Exception e) {
         		e.printStackTrace();
         		log.info("Error Write file: " + name);
         		return null;
           
         }
      }
		return serverFile;
	}
	
	/* delete image*/
	private void removeImage(String img_capa) {
		File fDel = new File(img_capa);
		if (fDel.exists() && fDel.delete()) {
			log.info("Delete Image [{}]!",img_capa);	
		}else {
			log.info("ERROR - NOT Delete Image [{}]!",img_capa);
		}
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
		
		
		model = setAttributes(model,pagename);
		return "dashboard/index";
	}
	private boolean execCmd(String command) {
	
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		
		try {
			log.info("DEPLOY["+command+"]");
			p = rt.exec( command );
			p.waitFor(30, TimeUnit.SECONDS);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	/**/
	@SuppressWarnings("rawtypes")
	private String getIpMachine(String network_interface) {
		if (network_interface.startsWith("eth0")) {
			return "serverlp.ddns.net";
		}
		Enumeration e;
		try {
			e = NetworkInterface.getNetworkInterfaces();

			while (e.hasMoreElements()) { // wlp2s0 eth0
				NetworkInterface n = (NetworkInterface) e.nextElement();
				if (n.getName().equals(network_interface)) {
					Enumeration ee = n.getInetAddresses();
					while (ee.hasMoreElements()) {
						InetAddress i = (InetAddress) ee.nextElement();
						if (i.isSiteLocalAddress()) {
							return i.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
			return null;
		}
		return null;
	}
	/*replace links of index production*/
	private static void replaceLink(String file_path) {
		
		File f = new File(file_path);
		if (f.exists()) {		
			try (BufferedReader br = new BufferedReader(new FileReader(f))) {				
				String line,keyBegin="http://",keyEnd=":",strReplace="";
				String bufer = "";
			    while ((line = br.readLine()) != null) {	
			    	//System.out.println(line);
			    	if ( line.contains("http://") && line.contains(":80")) {
			    		//System.out.println(line);	
			    		FindAndReturnTextBetweenChar fr = new FindAndReturnTextBetweenChar();
			    		strReplace=fr.setParameters(line, keyBegin, keyEnd);
			    		bufer+=line.replace(keyBegin+strReplace+":80", "..");
			    		//System.out.println(line.replace(keyBegin+strReplace+":80", "..")+"\n\n");	
					}else {
						bufer+=line;
					}
			    }
			    
				 // SAVE FILES
			    if( f.exists() ) {
			    	f.delete();
			    }
			    PrintWriter writer = new PrintWriter(file_path, "UTF-8");
		    	writer.println(bufer);
		    	writer.close();
				    
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			log.info("File not exist... ["+file_path);
		}
}
}
