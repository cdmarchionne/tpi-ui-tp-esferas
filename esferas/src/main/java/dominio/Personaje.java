package dominio;

public class Personaje extends Posicionable {

	public static final String NOMBRE = "nombre";
	public static final String DISTANCIA = "distancia";

	private String nombre;
	private Integer distancia;

	public Personaje() {
		super();
		this.nombre = "";
		this.distancia = 0;
	}

	
	public Personaje(String nombre, Integer distancia) {
		super();
		this.nombre = nombre;
		this.distancia = distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Integer getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean esPersonaje(){
		return true;
	}
	
	public boolean esEsfera(){
		return false;
	}
	
	/**
	 * 
	 * @author mariano
	 *Algo para dejar contento al cliente
	 */
	public enum NombrePersonaje {
		Bulma("Bulma"),
		GOKU("Gokú"),
		GOHAN("Gohan"),
		CELL("Cell"),
		KRILIN("Krilin"),
		VIDEL("Videl"),
		VEGETA("Vegeta"),
		PICOLO("Picolo");
		
		
		private String nombrePersonaje;
		
		private NombrePersonaje(String nombre) {
			this.nombrePersonaje = nombre;
		}
		
		public String getNombrePersonaje() {
			return this.nombrePersonaje;
		}
	}
}
