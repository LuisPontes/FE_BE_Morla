package pt.morla.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_separador;

@Controller
@RequestMapping(value = { "/dashboard", "/db" })
public class BackOfficeController {

	protected static Logger log = LoggerFactory.getLogger(BackOfficeController.class);
	   
    @Autowired
    ISeparadores dao;
    
    List<tb_separador> categoriasList = null;
    
    @PostConstruct
    private void init() {
    	/*get all categorias*/
		categoriasList = (List<tb_separador>) dao.findAll();

    }
    @RequestMapping(value = { "" }, method = { RequestMethod.GET })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
    	model = getAttributes(model);
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/addcat" }, method = { RequestMethod.POST })
	public String add(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute tb_separador new_cat_obj) {
		
		if ( new_cat_obj.getNome()!=null) {
			new_cat_obj.setLastUpdate(new Date());
			log.info("Save id: "+dao.save(new_cat_obj));
			categoriasList = (List<tb_separador>) dao.findAll();
		}
		model = getAttributes(model);
		return "dashboard/index";
	}

	@RequestMapping(value = { "/delcat" }, method = { RequestMethod.POST })
	public String remove(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute tb_separador new_cat_obj) {
		
		if ( new_cat_obj.getId()!=null ) {
			dao.remove(new_cat_obj.getId());
			log.info("Remove cat id: "+new_cat_obj.getId());
			categoriasList = (List<tb_separador>) dao.findAll();
		}
		model = getAttributes(model);
		return "dashboard/index";
	}
	
	private Model getAttributes(Model model) {
		model.addAttribute("catgories", categoriasList);
		model.addAttribute("tb_separador", new tb_separador());
		return model;
	}
	
	
	  private tb_separador doUpload(HttpServletRequest request, tb_separador myUploadForm) {
		 
		      try {
		      // Root Directory.
		      String uploadRootPath = request.getServletContext().getRealPath("upload");
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
		               System.out.println("Error Write file: " + name);
		               failedFiles.add(name);
		            }
		         }
		      }
		      myUploadForm.setImg(uploadRootPath);
		     return myUploadForm;
		      }catch (Exception e) {
		    	  	return null;
			}
		 
		}
}
