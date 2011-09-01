package dominio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Pair;

public class Mapa extends ObservableObject {

	public static final String CASILLEROS = "casilleros";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";

	private Pair<Integer, Integer> dimension;
	private Map<Pair<Integer, Integer>, Casillero> casilleros;

	public Mapa(Pair<Integer, Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new HashMap<Pair<Integer, Integer>, Casillero>();
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
	private Integer diferenciaAbsoluta(Pair<Integer, Integer> ubicacionPersonaje,
			Pair<Integer, Integer> ubicacionEsfera)
	{
		Pair<Integer, Integer> dif = new Pair<Integer, Integer>(
				(ubicacionPersonaje.getLeft() - ubicacionEsfera.getLeft()),
				(ubicacionPersonaje.getRight() - ubicacionEsfera.getRight()));
		return Math.abs(dif.getLeft()) + Math.abs(dif.getRight());
	}

	/**
	 * Devuelvo la posicion en donde esta un Objeto, Personaje o Esfera.
	 */
	public Pair<Integer, Integer> buscar(ObjetosDragonBall objeto) {
		Iterator<Entry<Pair<Integer, Integer>, Casillero>> entries = this.getCasilleros()
				.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<Pair<Integer, Integer>, Casillero> thisEntry = (Entry<Pair<Integer, Integer>, Casillero>) entries
					.next();
			Casillero value = (Casillero) thisEntry.getValue();

			if (value.getObjeto().equals(objeto)) {
				return (Pair<Integer, Integer>) thisEntry.getKey();
			}
		}

		throw new UserException("El Objeto " + objeto.getNombre() + " no se encuenrta en el mapa.");
	}

	// ********************************************************
	// ** Atributos
	// ********************************************************

	public void setCasilleros(Map<Pair<Integer, Integer>, Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public Map<Pair<Integer, Integer>, Casillero> getCasilleros() {
		return casilleros;
	}

	public void setDimension(Pair<Integer, Integer> dimension) {
		this.dimension = dimension;
	}

	public Pair<Integer, Integer> getDimension() {
		return dimension;
	}

}
