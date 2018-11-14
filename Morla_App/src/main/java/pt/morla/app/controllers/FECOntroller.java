package pt.morla.app.controllers;

import java.util.List;

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

import pt.morla.app.bo.db.interfaces.ICategorias;
import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.models.categorias_tb;
import pt.morla.app.bo.db.models.projectos_tb;
import pt.morla.app.bo.db.models.user_obj;

@Controller
public class FECOntroller {

	protected static Logger log = LoggerFactory.getLogger(FECOntroller.class);
	
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
		separatorFiles = props.getProperty("separator.files");
    }

	
	@RequestMapping(value = { "" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
		
		init();
		model.addAttribute("categorias", categoriasList);
		model.addAttribute("conteudos", projectosList);
		

		return "FE/index";
	}
}
