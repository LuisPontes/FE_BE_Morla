package pt.morla.app.controllers;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
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
    
    private String ipServer = null;
    private List<categorias_tb> categoriasList = null;
    private List<projectos_tb> projectosList = null;
    List<images_tb> imagesList = null;
    
    @PostConstruct
    private void init() {
    	
    	ipServer = getIpMachine(props.getProperty("network.interface"))+":"+props.getProperty("port.apache.server");    			 
    	categoriasList = (List<categorias_tb>)  daoCat.findAllActive();
    	for (categorias_tb c : categoriasList) {
			c.setSlug(c.getNome().replace(" ",""));
			c.setNome(c.getNome().toUpperCase());
		}
		projectosList = (List<projectos_tb>) daoPro.findAllActive();
		imagesList = daoImg.findAll();
	
    }

	
	@RequestMapping(value = { "" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
		
		init();
		model.addAttribute("categorias", categoriasList);
		model.addAttribute("conteudos", projectosList);
		model.addAttribute("imagens", imagesList);
		model.addAttribute("ipServer", ipServer);

		return "FE/index";
	}
	
	
//	@RequestMapping(value = { "/refresh" }, method = { RequestMethod.GET,RequestMethod.POST })
//	public String refresh(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
//		
//		init();
//		model.addAttribute("categorias", categoriasList);
//		model.addAttribute("conteudos", projectosList);
//		model.addAttribute("imagens", imagesList);
//		model.addAttribute("ipServer", ipServer);
//
//		return "FE/index";
//	}
	
	@SuppressWarnings("rawtypes")
	private String getIpMachine(String network_interface) {
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
							System.out.println(i.getHostAddress());
							return i.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		return null;
	}

}
