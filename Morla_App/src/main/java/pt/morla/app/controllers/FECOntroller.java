package pt.morla.app.controllers;

import java.util.Base64;
import java.util.HashMap;
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
import pt.morla.app.bo.db.interfaces.IImages;
import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.models.categorias_tb;
import pt.morla.app.bo.db.models.images_tb;
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
    IImages daoImg;
    @Autowired
    private Environment props;
    
    List<categorias_tb> categoriasList = null;
    List<projectos_tb> projectosList = null;
    String separatorFiles = null; 
    private HashMap<String, images_tb> imagesMap =null;
    
    @PostConstruct
    private void init() {
    	HashMap<String, images_tb> imagesMap_aux = new HashMap<>();
    	imagesMap = new HashMap<>();
    	
    	List<images_tb> imagesList = daoImg.findAll();
    	if (imagesList.size()>0) {    		
			for (images_tb img : imagesList) {
				img.setEncode_to_str_Img(Base64.getEncoder().encodeToString(img.getImage()));
				img.setImage(null);
				imagesMap_aux.put(String.valueOf(img.getId()), img);
			}
		}
    	
    	categoriasList = (List<categorias_tb>)  daoCat.findAllActive();
    	for (categorias_tb c : categoriasList) {
			c.setSlug(c.getNome().replace(" ",""));
			c.setNome(c.getNome().toUpperCase());

			if ( c.getImg_backGround()!=null && !c.getImg_backGround().equals("") && imagesMap_aux.containsKey(c.getImg_backGround().trim())) {
				c.setImg_backGround(imagesMap_aux.get(c.getImg_backGround().trim()).getEncode_to_str_Img());
			}
			
		}
		projectosList = (List<projectos_tb>) daoPro.findAllActive();
		for (projectos_tb p : projectosList) {
			
			if ( p.getFoto_galeria()!=null && !p.getFoto_galeria().equals("") ) {
				if ( p.getFoto_galeria().contains(",") ) {
					for (String idstr : p.getFoto_galeria().split(",")) {
						
						if ( imagesMap_aux.containsKey(idstr.trim()) ) {
							imagesMap.put(idstr.trim(), imagesMap_aux.get(idstr.trim()));	
						}
					}	
				}else {
					imagesMap.put(p.getFoto_galeria().trim(), imagesMap_aux.get(p.getFoto_galeria().trim()));
				}

				//p.setListPathsFotoGaleria(p.getFoto_galeria().split(props.getProperty("separator.files")));
			}
			if ( p.getImg_capa()!=null && !p.getImg_capa().equals("")) {
				p.setImg_capa(imagesMap_aux.get(p.getImg_capa().trim()).getEncode_to_str_Img());
			}
		}
		
		
		separatorFiles = props.getProperty("separator.files");
    }

	
	@RequestMapping(value = { "" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
		
		init();
		model.addAttribute("categorias", categoriasList);
		model.addAttribute("conteudos", projectosList);
		model.addAttribute("imagens", imagesMap);

		return "FE/index";
	}
}
