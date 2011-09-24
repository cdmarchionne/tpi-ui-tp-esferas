package dominio;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;

public class Mapa extends ObservableObject {

	public static final String CASILLEROS = "casilleros";
	public static final String ADD_CASILLERO = "addCasillero";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";
	public static final String PUEDE_CAPTURAR = "puedeCapturarEsfera";
	public static final String ESFERA_BUSCADA = "esferaBuscada";
	public static final String PERSONAJE_BUSCADO = "personajeBuscado";

	private Punto<Integer> dimension;
	private List<Casillero> casilleros;

	private Esfera esferaBuscada;
	private Personaje personajeBuscado;

	public Mapa(Punto<Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new ArrayList<Casillero>();

		Esfera e = new Esfera(new Punto<Integer>(1, 1), 1);
		casilleros.add(e.getCasillero());

		for (int i = 2; i <= Esfera.CantidadEstrellas.values().length; i++) {
			casilleros.add((new Esfera(new Punto<Integer>(1, 1), i)).getCasillero());
		}
		Personaje p = new Personaje("Goku", 4);
		casilleros.add(p.getCasillero());

		esferaBuscada = e;
		personajeBuscado = p;
	}

	// ********************************************************
	// ** Acciones
	// ********************************************************

	public ArrayList<Personaje> getListaPersonajes() {

		ArrayList<Personaje> resultado = new ArrayList<Personaje>();

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.esPersonaje()) {
				resultado.add((Personaje) casillero.getObjeto());
			}
		}

		return resultado;
	}

	public ArrayList<Esfera> getListaEsferas() {

		ArrayList<Esfera> resultado = new ArrayList<Esfera>();

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.esEsfera()) {
				resultado.add((Esfera) casillero.getObjeto());
			}
		}

		return resultado;
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
	 * Calculo la diferencia absoluta entre cada elemento del par
	 */
	private Integer calcularDistancia(Personaje personaje, Esfera esfera) {
		return this.diferenciaAbsoluta(this.buscarPosicion(personaje), this.buscarPosicion(esfera));
	}

	/**
	 * 
	 * @param personaje
	 * @param esfera
	 * @return true si puede capturar la esfera. ( tiene que estar dentro de la
	 *         distancia que puede
	 *         recorrer el personaje
	 */
	public boolean puedeCapturarEsfera() {
		return personajeBuscado.getDistancia() >= calcularDistancia(personajeBuscado, esferaBuscada);
	}

	/**
	 * Devuelvo la posicion en donde esta un Objeto, Personaje o Esfera.
	 */
	public Punto<Integer> buscarPosicion(Posicionable objeto) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.hasObject(objeto)) {
				return casillero.getPosicion();
			}
		}

		throw new UserException("El " + objeto.getClass().toString() + " " + objeto.toString()
				+ " no se encuenrta en el mapa.");

	}

	public Boolean personajeCapturaEsfera() {
		Boolean valor = this.puedeCapturarEsfera();

		if (valor) {
			Casillero casilleroPersonaje = personajeBuscado.getCasillero();
			esferaBuscada.getCasillero().setObjeto(personajeBuscado);
			this.removeCasilla(casilleroPersonaje);
			personajeBuscado.addInventario(esferaBuscada);
		}

		return valor;
	}

	// ********************************************************
	// ** Atributos
	// ********************************************************

	public void setDimension(Punto<Integer> dimension) {
		this.dimension = dimension;
	}

	public Punto<Integer> getDimension() {
		return dimension;
	}

	public List<Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(List<Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public void addCasillero(Casillero casillero) {
		this.casilleros.add(casillero);
	}

	public void removeCasilla(Casillero casillero) {
		this.casilleros.remove(casillero);
	}

	public Esfera getEsferaBuscada() {
		return esferaBuscada;
	}

	public Personaje getPersonajeBuscado() {
		return personajeBuscado;
	}

	public void setEsferaBuscada(Esfera esferaBuscada) {
		this.esferaBuscada = esferaBuscada;
	}

	public void setPersonajeBuscado(Personaje personajeBuscado) {
		this.personajeBuscado = personajeBuscado;
	}

}
