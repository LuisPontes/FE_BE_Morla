package pt.morla.app.controllers;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
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
import pt.morla.app.bo.db.interfaces.IMenu;
import pt.morla.app.bo.db.interfaces.IProjectos;
import pt.morla.app.bo.db.models.categorias_tb;
import pt.morla.app.bo.db.models.images_tb;
import pt.morla.app.bo.db.models.menu_obj;
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
    IMenu daoMen;
    @Autowired
    private Environment props;
    
    private String ipServer = null;
    private List<categorias_tb> categoriasList = null;
    private List<projectos_tb> projectosList = null;
    List<images_tb> imagesList = null;
    private menu_obj menuObj = null;
    
    @PostConstruct
    private void init() {
    	
    	ipServer = getIpMachine(props.getProperty("network.interface"))+":"+props.getProperty("port.apache.server"); 
    	if (ipServer==null) {
			new Throwable("Ip Server is null!! [network.interface = "+props.getProperty("network.interface")+"] - [port.apache.server = "+props.getProperty("port.apache.server")+"]");
		}
    	
    	categoriasList = new ArrayList<categorias_tb>();
    	List<categorias_tb> categoriasListBd = (List<categorias_tb>)  daoCat.findAllActive();
		for ( int i = 0; i < categoriasListBd.size(); i++ ) {
			for ( categorias_tb cat : categoriasListBd ) {				
				if (cat!=null && cat.getOrderView()!=null && cat.getOrderView()==i) {
					cat.setSlug(cat.getNome().replace(" ",""));
					cat.setNome(cat.getNome().toUpperCase());
					categoriasList.add(cat);
				}
			}
		}
    	
		projectosList = new ArrayList<projectos_tb>();
		List<projectos_tb> projectosListBd = (List<projectos_tb>)  daoPro.findAllActive();
		for ( int i = 0; i < projectosListBd.size(); i++ ) {
			for ( projectos_tb cat : projectosListBd ) {				
				if (cat!=null && cat.getOrderView()!=null && cat.getOrderView()==i ) {
					projectosList.add(cat);
				}
			}
		}
		
		imagesList = daoImg.findAll();
		
		List<menu_obj> menuObj_list = (List<menu_obj>) daoMen.findAll();
		if ( !menuObj_list.isEmpty()) {
			menuObj = menuObj_list.get(0);
		}
	
    }

	@RequestMapping(value = { "/local" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index_local(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
		
		init();
		model.addAttribute("categorias", SortList(categoriasList));
		model.addAttribute("conteudos", projectosList);
		model.addAttribute("imagens", imagesList);
		model.addAttribute("ipServer", "serverlp.ddns.net:80");
		model.addAttribute("menuObj", menuObj);
		return "FE/index";
	}
	
	@RequestMapping(value = { "" }, method = { RequestMethod.GET,RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute user_obj userObj) {
		
		init();
		model.addAttribute("categorias", SortList(categoriasList));
		model.addAttribute("conteudos", projectosList);
		model.addAttribute("imagens", imagesList);
		model.addAttribute("ipServer", ipServer);
		model.addAttribute("menuObj", menuObj);
		return "FE/index";
	}
	

	private List<categorias_tb> SortList(List<categorias_tb> categoriasList2) {
    	
		List<categorias_tb> categoriasList_copy = new ArrayList<categorias_tb>();
		for ( int i = 0; i < categoriasList.size(); i++ ) {
			for ( categorias_tb cat : categoriasList ) {
				if (cat!=null && cat.getOrderView()==i) {
					categoriasList_copy.add(cat);
				}
			}
		}
		
		return categoriasList_copy;
	}


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
