package pt.morla.app;


public class Contants {

	public static final String[] TypesCategorias = {"TYPE_HEADER" ,"TYPE_APRESENTACAO","TYPE_PROJECTOS","TYPE_FOOTER"}; 
	
	public enum TypeCategoria {
		
		TYPE_HEADER("type_header"),
		TYPE_APRESENTACAO("type_apresentaçao"),
		TYPE_PROJECTOS("type_projectos"),
		TYPE_FOOTER("type_footer");
		
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
