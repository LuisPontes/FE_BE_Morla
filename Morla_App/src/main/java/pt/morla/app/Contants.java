package pt.morla.app;


public class Contants {

	public enum TypeCategoria {
		
		TYPE_HEADER("type_header"),
		TYPE_BIOGRAFIA("type_biografia"),
		TYPE_PROJECTO("type_projecto"),
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
