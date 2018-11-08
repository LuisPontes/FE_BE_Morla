package lp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import BD_Util.UserDao;
import ResourcesOBJ.CoreografiaPOJO;
import lp.utils.Utils;
import lp.utils.Validate;



@Controller
public class ControllerBase {
	
	DateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	ModelAndView indexModel;
	String str;
	UserDao dao;
	
	@PostConstruct
	public void init() {
	   //Make a page index
		System.out.println("init: Make a page index");
		dao = new UserDao();
		createIndex();		
	}
	
	private void createIndex() {		
		//str = dao.makeHtml();
		str = dao.getHTML();
		indexModel = new ModelAndView("index");
		indexModel.addObject("coreografia", str);		
	}

	@RequestMapping(value = {"/","/index"})
	public ModelAndView index(){		
	      return indexModel;
	}
	
	
	@RequestMapping(value = "/DashBoard")
	public ModelAndView dash(HttpServletRequest request, HttpServletResponse response,ModelAndView model){		
		String action = request.getParameter("action");
		StringBuilder message = new StringBuilder();
		if ( action != null ) {			
			message.append(format.format(new Date(System.currentTimeMillis()))+" : Start DashBoard\n");				
			try{
				
				if ( action.equalsIgnoreCase("ActulizarWEB") ) 
				{
					message.append(format.format(new Date(System.currentTimeMillis()))+" : "+action+"\n");
					//CRIAR UMA NOVE STRINGBUILDER COM OS CONTEUDO DA BD
					createIndex();		
					message.append(format.format(new Date(System.currentTimeMillis()))+" : "+str);
					message.append(format.format(new Date(System.currentTimeMillis()))+" : "+action+" Web site actualizado!\n");
				}
			}catch(Exception e){
				message.append(format.format(new Date(System.currentTimeMillis()))+" : action:"+e.toString()+"\n");
			}
		
		
		}
	    return new ModelAndView("DashBoard", "message", message);
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public String loginForm( HttpSession session,HttpServletRequest request, HttpServletResponse response ) {

		 try{
		String action = request.getParameter("action");
		
		if (action != null && action.equalsIgnoreCase("logout")) {
			return "login";
		}
		 }catch(Exception e){
			 System.out.println("Logout : "+e.toString());
		 }
		 	String Sid = session.getId();
			String nameLogin = request.getParameter("login");
			String senha = request.getParameter("senha");
			System.out.println(Sid+" : "+nameLogin+" : "+senha);
			
			if ( nameLogin != null && senha != null ) 
			{
				if( new UserDao().verifyLog(nameLogin, senha) )//if ( nameLogin.equalsIgnoreCase("1234") && senha.equalsIgnoreCase("1234") ) 
					{
						return "DashBoard.jsp?action=Bemvindo"+nameLogin;
					}	
			}
					
			
		return "login";
	  }
	
	
	@RequestMapping("/add_container")
	public ModelAndView add_container(HttpSession session,HttpServletRequest request, HttpServletResponse response,ModelAndView model){
		Boolean statusTX = false;
		String action = request.getParameter("action");
		
		if ( action != null) {		
		
		try{				
				System.out.println(action);
				if ( action.equalsIgnoreCase("add_text") ) 
				{
					String roleName = request.getParameter("roleName");
					System.out.println(roleName);
					
					switch (Integer.parseInt(roleName)) {
						case 1:
							roleName = "CatarinaMorla";
							
							break;
							
						case 2:
							roleName = "Coreografia";								
							CoreografiaPOJO obj = Validate.validateRequest(request);							
							statusTX= new UserDao().addObj(roleName,obj);
							System.out.println("Flag: "+statusTX+" -obj: "+obj.toString());
							break;
							
						case 3:
							roleName = "figurinos";
							break;
							
						case 4:
							roleName = "scarfs";
							break;
						}					
					}
					
				if ( statusTX == false ) 
				{
					return new ModelAndView("DashBoard", "message", "Erro - Dados nao guardados!");
				}
				else if ( statusTX == true ) 
				{
					return new ModelAndView("DashBoard", "message", "Sucesso - Dados guardados na base de dados!");
				}
		 }catch(Exception e){
			 return new ModelAndView("DashBoard", "message", "Erro - Dados nao guardados na base de dados!"+e.toString());
		 }
		}
	      return new ModelAndView("add_container");
	}
	
	@RequestMapping(value="/DashMultimedia")
	public ModelAndView add_multimedia(HttpSession session,HttpServletRequest request, HttpServletResponse response,ModelAndView model){
		
		String action = request.getParameter("action");
		if ( action != null) 
		{	
			
			String[] parts = action.split(":");
			
			if ( parts[0].contains("movie") ) 
			{
				System.err.println("movie: -- parts[0]"+parts[0]+"parts[1]"+parts[1]);
				//request.get
			}
			if ( parts[0].contains("img") ) 
			{
				System.err.println("img: -- parts[0]"+parts[0]+"parts[1]"+parts[1]);
			}
		}
		
		//model = new ModelAndView("DashMultimedia");				
		List<CoreografiaPOJO> rs = dao.getAlltitulos();
			
			if ( rs.size() > 0 ) 
			{
				
				StringBuilder option = new StringBuilder();		
				
				for (CoreografiaPOJO obj: rs){							
					System.err.println(obj.getTitulo());
					option.append(" <option value="+obj.getTitulo().toString()+" label="+obj.getTitulo().toString()+"/>");
				}
				if ( option.equals("") ) 
				{
					model.addObject("popup", "Nao existem Projectos no webSite! Adiciona primeiro Conteudos em : 'DasBoard -> Adicione conteudos!'");						
				}else 
				{
					model.addObject("options", option.toString());	
				}
				
//				String html = Utils.makeTableMultimedia(rs);
//				if ( html.isEmpty() ) 
//				{
//					model.addObject("html", "Nao existem Projectos no webSite! Adiciona primeiro Conteudos em : 'DasBoard -> Adicione conteudos!'");	
//				}
//				else 
//				{
//					model.addObject("html", html);	
//				}
				
			}
		
	    return model;
	}
	
	@RequestMapping("/show_container")
	public ModelAndView show_container(){
		ModelAndView model = new ModelAndView("show_container");
		model.addObject("users", dao.getAllUsers());
		return model;
	}
	
	
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();		

				System.out.println("Server File Location="+ serverFile.getAbsolutePath());
				
				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
	
}
