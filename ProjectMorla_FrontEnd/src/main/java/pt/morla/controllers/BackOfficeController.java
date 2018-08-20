package pt.morla.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_separador;

@Controller
public class BackOfficeController {

	protected static Logger log = LoggerFactory.getLogger(BackOfficeController.class);
	
   
    @Autowired
    ISeparadores dao;
	
	@RequestMapping(value = { "/dashboard", "/db" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String dashboard(HttpServletRequest request, HttpServletResponse response,ModelAndView model) {
		
		 List<tb_separador> r = (List<tb_separador>) dao.findAll();
		 for (tb_separador tb_separador : r) {
			 System.out.println("The user id is: " + tb_separador.getNome()); 
		}
		   
		
		return "dashboard/index";
	}
	
	
	
	
}
