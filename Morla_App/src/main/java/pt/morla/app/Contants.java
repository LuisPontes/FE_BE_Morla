package pt.morla.app;



public class Contants {

	public static final String[] TypesCategorias = {"TYPE_HEADER" ,"TYPE_APRESENTACAO","TYPE_PROJECTOS","TYPE_FOOTER"}; 
	public static final String COMMANDBASE_WGET 		= "sudo wget -kp --no-cache %s --directory-prefix=%s";	///usr/local/bin/wget
	
	public static final String SERVER_IP = "127.0.0.1";
	public static final String SERVER_PORT = ":8090";
	public static final String PATH_SITE_PROD = "/var/www/html/";
	public static final String FOLDER_APACHE_NAME = "morla.site";
	// wget -kp --no-cache 127.0.0.1:8090 --directory-prefix=/var/www/html/

	public enum TypeCategoria {
		
		TYPE_HEADER("type_header"),
		TYPE_APRESENTACAO("type_apresentaçao"),
		TYPE_PROJECTOS("type_projectos"),
		TYPE_FOOTER("type_footer"),
		TYPE_IMAGES("type_images");
		
		String type;

		String Type() {
			return type;
		}

		private TypeCategoria(String status) {
			this.type = status;
		}

		public static TypeCategoria getTypeCategoria(String type) {
			for (TypeCategoria c : TypeCategoria.values()) {
				if (type == c.type) {
					return c;
				}
			}
			throw new IllegalArgumentException("Nome de Tabela invalida!");
		}
	}
	
}
