package dominio;

import java.util.HashSet;
import java.util.Set;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;

public class Mapa extends ObservableObject {

	public static final String CASILLEROS = "casilleros";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";

	private Punto<Integer> dimension;
	private Set<Casillero> casilleros;

	public Mapa(Punto<Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new HashSet<Casillero>();
	}

	// ********************************************************
	// ** Acciones
	// ********************************************************

	/**
	 * Calculo la diferencia absoluta entre cada elemento del par
	 */
	public Integer calcularDistancia(Personaje personaje, Esfera esfera) {
		return this.diferenciaAbsoluta(this.buscar(personaje), this.buscar(esfera));
	}

	/**
	 * Calculo la diferencia absoluta entre cada elemento del par
	 */
	private Integer diferenciaAbsoluta(Punto<Integer> ubicacionPersonaje,
			Punto<Integer> ubicacionEsfera)
	{
		Punto<Integer> dif = new Punto<Integer>(
				(ubicacionPersonaje.getX() - ubicacionEsfera.getX()),
				(ubicacionPersonaje.getY() - ubicacionEsfera.getY()));
		return Math.abs(dif.getX()) + Math.abs(dif.getY());
	}

	/**
	 * Devuelvo la posicion en donde esta un Objeto, Personaje o Esfera.
	 */
	public Punto<Integer> buscar(ObjetosDragonBall objeto) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.hasObject(objeto)) {
				return casillero.getPosicion();
			}
		}

		throw new UserException("El Objeto " + objeto.toString() + " no se encuenrta en el mapa.");

	}

	// ********************************************************
	// ** Atributos
	// ********************************************************

	public void setCasilleros(Set<Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public Set<Casillero> getCasilleros() {
		return casilleros;
	}

	public void setDimension(Punto<Integer> dimension) {
		this.dimension = dimension;
	}

	public Punto<Integer> getDimension() {
		return dimension;
	}

}
