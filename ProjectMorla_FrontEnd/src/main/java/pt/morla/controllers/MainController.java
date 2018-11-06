package pt.morla.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pt.morla.bo.build.BuildindexPageStatic;
import pt.morla.bo.db.interfaces.IContents;
import pt.morla.bo.db.interfaces.ISeparadores;
import pt.morla.bo.db.models.tb_content;
import pt.morla.bo.db.models.tb_separador;

@Controller
public class MainController {

	protected static Logger log = LoggerFactory.getLogger(MainController.class);
	@Autowired
    ISeparadores daoSep;
    @Autowired
    IContents daoCont;
   
    
    List<tb_separador> categoriasList = null;
    List<tb_content> contentsList = null;
    List<tb_separador> categoriasListActive = null;
    List<tb_content> contentsListActive = null;
    Model model = null;
    StringBuilder INDEX_PAGE = null;
    
    @PostConstruct
    private void init() { 
    	contentsListActive = daoCont.findAllActive();
    	categoriasListActive = daoSep.findAllActive();
    }
    
	@RequestMapping(value = { "/", "/index" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index( Model model ) {
		
		init();
		
		model.addAttribute("categorias", categoriasListActive);
		model.addAttribute("conteudos", contentsListActive);
		

		return "fe/index";
	}

	@RequestMapping(value = { "/db/refresh","/dashboard/refresh" }, method = { RequestMethod.GET })
	public String refresh(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		//refresh varables golbal
		init();
		
		categoriasList = (List<tb_separador>) daoSep.findAll();
		contentsList =  (List<tb_content>) daoCont.findAll();
		for (tb_separador ts : categoriasList) {
			for (tb_content tc : contentsList) {
				if ( tc.getCategoria_id().equals(ts.getId()+"") ) {
					tc.setCategoria_name(ts.getNome());
				}
			}
		}
		model.addAttribute("catgories", categoriasList);
		model.addAttribute("contents",contentsList);
		model.addAttribute("nTotalCat", categoriasList.size());
		model.addAttribute("tb_separador", new tb_separador());
		model.addAttribute("tb_content", new tb_content());
		model.addAttribute("redirectPage", "home");
		
		return "dashboard/index";
	}
	
	@RequestMapping(value = { "/db/build","/dashboard/build" }, method = { RequestMethod.GET })
	public String build(HttpServletRequest request, HttpServletResponse response,Model model) {
		
		categoriasList = (List<tb_separador>) daoSep.findAll();
		contentsList =  (List<tb_content>) daoCont.findAll();
		
		BuildindexPageStatic b = new BuildindexPageStatic(categoriasList,contentsList);
		
		try {
			b.build();
			b.saveAndReplaceFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard/index";
	}
	
}
