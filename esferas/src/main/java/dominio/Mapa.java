package dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;
import dominio.Esfera.CantidadEstrellas;
import dominio.Personaje.NombrePersonaje;

public class Mapa extends ObservableObject {

	public static final String CASILLEROS = "casilleros";
	public static final String ADD_CASILLERO = "addCasillero";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";
	public static final String PUEDE_CAPTURAR = "puedeCapturarEsfera";
	public static final String ESFERA_BUSCADA = "esferaBuscada";
	public static final String PERSONAJE_BUSCADO = "personajeBuscado";
	public static final String LISTA_PERSONAJES = "listaPersonajes";
	public static final String LISTA_ESFERAS = "listaEsferas";

	private Punto<Integer> dimension;
	private List<Casillero> casilleros;

	private Esfera esferaBuscada;
	private Personaje personajeBuscado;

	public Mapa(Punto<Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new ArrayList<Casillero>();

		Esfera e = new Esfera(new Punto<Integer>(1, 1), Esfera.CantidadEstrellas.UNA);
		casilleros.add(e.getCasillero());

		// for (int i = 2; i <= Esfera.CantidadEstrellas.values().length; i++) {
		// casilleros.add((new Esfera(new Punto<Integer>(1, 1),
		// i)).getCasillero());
		// }
		Personaje p = new Personaje(Personaje.NombrePersonaje.GOKU, 4);
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
	 * @return Devuelve una lista con los Personajes que todavia no fueron
	 *         Cargados en el Mapa
	 */
	public List<NombrePersonaje> listaPersonajesNoCreadas() {
		List<NombrePersonaje> listaPersonajesNoCreadas = new ArrayList<Personaje.NombrePersonaje>();

		for (NombrePersonaje personajeExistente : Arrays.asList(NombrePersonaje.values())) {
			if (!existePersonajeEnElMapa(personajeExistente))
				listaPersonajesNoCreadas.add(personajeExistente);
		}
		return listaPersonajesNoCreadas;
	}

	private boolean existePersonajeEnElMapa(NombrePersonaje personajeExistente) {
		for (Personaje personajeDelMapa : this.getListaPersonajes()) {
			if (personajeExistente == (personajeDelMapa.getNombre()))
				return true;
		}
		return false;
	}

	/**
	 * @return Devuelve una lista con las Esferas que todavia no fueron
	 *         Cargados en el Mapa
	 */
	public List<CantidadEstrellas> listaEsferasNoCreadas() {

		List<CantidadEstrellas> listaEsferasNoCreadas = new ArrayList<Esfera.CantidadEstrellas>();

		for (CantidadEstrellas esferaExistente : Arrays.asList(CantidadEstrellas.values())) {
			if ((!existeEsferaAtrapadaEnLosPersonajes(esferaExistente))
					&& (!existeEsferaEnElMapa(esferaExistente)))
				listaEsferasNoCreadas.add(esferaExistente);
		}
		return listaEsferasNoCreadas;
	}

	private boolean existeEsferaEnElMapa(CantidadEstrellas esferaExistente) {
		for (Esfera esferaDelMapa : this.getListaEsferas()) {
			if (esferaExistente == (esferaDelMapa.getNumero()))
				return true;
		}
		return false;
	}

	private boolean existeEsferaAtrapadaEnLosPersonajes(CantidadEstrellas esferaExistente) {
		for (Personaje personajeDelMapa : this.getListaPersonajes()) {
			for (Esfera esferaDelPersonajeEnMapa : personajeDelMapa.getInventario()) {
				if (esferaExistente == (esferaDelPersonajeEnMapa.getNumero()))
					return true;
			}
		}
		return false;
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
				+ " no se encuentra en el mapa.");

	}

	public Posicionable buscarObjeto(Punto<Integer> posicion) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.getPosicion().equals(posicion)) {
				return casillero.getObjeto();
			}
		}

		throw new UserException("La posicion " + posicion.toString()
				+ " se encuentra vacia en el mapa.");
	}

	public boolean hayObjetoEn(Punto<Integer> posicion) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.getPosicion().equals(posicion)) {
				return true;
			}
		}

		return false;
	}

	public Boolean personajeCapturaEsfera() {
		Boolean puedeCapturarla = this.puedeCapturarEsfera();

		if (puedeCapturarla) {
			personajeBuscado.addInventario(esferaBuscada);

			Casillero casilleroPersonaje = personajeBuscado.getCasillero();
			Casillero casilleroEsfera = esferaBuscada.getCasillero();

			casilleroEsfera.setObjeto(personajeBuscado);
			this.removeCasilla(casilleroPersonaje);
		}

		return puedeCapturarla;
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
		this.actualizarVista();
	}

	public void removeCasilla(Casillero casillero) {
		this.casilleros.remove(casillero);
		this.actualizarVista();
	}

	private void actualizarVista() {
		this.firePropertyChange(CASILLEROS, null, this.getCasilleros());
		this.firePropertyChange(LISTA_ESFERAS, null, this.getListaEsferas());
		this.firePropertyChange(LISTA_PERSONAJES, null, this.getListaPersonajes());
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
