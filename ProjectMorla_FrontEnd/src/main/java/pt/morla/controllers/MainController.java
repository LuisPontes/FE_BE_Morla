package pt.morla.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	protected static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = { "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {


		return "index";
	}
	
	
	
}
