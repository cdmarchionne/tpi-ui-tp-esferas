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

}
