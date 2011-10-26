package dominio;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.UserException;

public class Personaje extends Posicionable {

	public static final String NOMBRE_PERSONAJE = "nombrePersonaje";
	public static final String NOMBRE = "nombre";
	public static final String DISTANCIA = "distancia";

	private NombrePersonaje nombre;
	private Integer distancia;
	private List<Esfera> inventario;

	public Personaje() {
		super();
		this.nombre = NombrePersonaje.GOKU;
		this.distancia = 0;
		this.inventario = new ArrayList<Esfera>();
	}

	public Personaje(NombrePersonaje nombre, Integer distancia) {
		this();
		this.nombre = nombre;
		this.distancia = distancia;
	}

	public void setDistancia(Integer distancia) {
		if ((distancia == null) || (distancia < 0)) {
			throw new UserException(
					"La distancia que puede recorrer un personaje debe ser positiva");
		}
		this.distancia = distancia;
	}

	public Integer getDistancia() {
		return distancia;
	}

	@Override
	public String toString() {
		return this.getNombre().getNombrePersonaje();
	}

	public NombrePersonaje getNombre() {
		return nombre;
	}

	public void setNombre(NombrePersonaje nombre) {
		if (nombre == null) {
			throw new UserException("Seleccione un nombre valido");
		}
		this.nombre = nombre;
	}

	public boolean esPersonaje() {
		return true;
	}

	public boolean esEsfera() {
		return false;
	}

	public List<Esfera> getInventario() {
		return inventario;
	}

	public void addInventario(Esfera inventario) {
		this.inventario.add(inventario);
	}

	public void setInventario(List<Esfera> inventario) {
		this.inventario = inventario;
	}

	public Boolean puedeInvocarShengLong() {
		return (this.getInventario().size() == Esfera.CantidadEstrellas.values().length);
	}

	/**
	 * 
	 * @author mariano
	 *         Algo para dejar contento al cliente
	 */
	public enum NombrePersonaje {
		Bulma("Bulma"), GOKU("Gokú"), GOHAN("Gohan"), CELL("Cell"), KRILIN("Krilin"), VIDEL("Videl"), VEGETA(
				"Vegeta"), PICOLO("Picolo");

		private String nombrePersonaje;

		private NombrePersonaje(String nombre) {
			this.nombrePersonaje = nombre;
		}

		public String getNombrePersonaje() {
			return this.nombrePersonaje;
		}
	}
}
