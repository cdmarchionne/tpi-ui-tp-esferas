package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;
import utils.Randomize;
import dominio.Esfera.CantidadEstrellas;
import dominio.Personaje.NombrePersonaje;

public class Mapa extends ObservableObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String CASILLEROS = "casilleros";
	public static final String ADD_CASILLERO = "addCasillero";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";
	public static final String FALTAN_CREAR_PERSONAJES = "faltanCrearPersonajes";
	public static final String FALTAN_CREAR_ESFERAS = "faltanCrearEsferas";
	public static final String HAY_PERSONAJE_Y_ESFERA_BUCADA = "hayPersonajeYEsferaBuscada";
	public static final String PUEDE_LLAMAR_A_SHENG_LONG = "puedeLlamarAShengLong";
	public static final String PUEDE_CAPTURAR = "puedeCapturarEsfera";
	public static final String ESFERA_BUSCADA = "esferaBuscada";
	public static final String PERSONAJE_BUSCADO = "personajeBuscado";
	public static final String LISTA_PERSONAJES = "listaPersonajes";
	public static final String LISTA_ESFERAS = "listaEsferas";
	public static final String DIMENSION = "dimension";
	public static final String MAPA = "mapa";

	private Punto<Integer> dimension;
	private List<Casillero> casilleros;

	private Esfera esferaBuscada;
	private Personaje personajeBuscado;

	public Mapa(Punto<Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new ArrayList<Casillero>();

		personajeBuscado = new Personaje(Personaje.NombrePersonaje.GOKU, 10);
		casilleros.add(new Casillero(new Punto<Integer>(0, 0), personajeBuscado));

		for (Esfera.CantidadEstrellas numeroEsfera : Esfera.CantidadEstrellas.values()) {
			Esfera esfera = new Esfera(numeroEsfera);
			this.ubicarEsferaRandom(esfera);
			this.esferaBuscada = esfera;
			if(!personajeBuscado.getInventario().contains(esfera)){
				this.personajeCapturaEsfera();
			}
		}
		this.esferaBuscada = null;
		System.out.println("El personaje " + personajeBuscado + " se coloco en la posicion " + personajeBuscado.getPosicion());
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
	public List<NombrePersonaje> getListaPersonajesNoCreadas() {
		List<NombrePersonaje> listaPersonajesNoCreadas = new ArrayList<Personaje.NombrePersonaje>();

		for (NombrePersonaje personajeExistente : Arrays.asList(NombrePersonaje
				.values())) {
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
	 * @return Devuelve una lista con las Esferas que todavia no fueron Cargados
	 *         en el Mapa
	 */
	public List<CantidadEstrellas> getListaEsferasNoCreadas() {

		List<CantidadEstrellas> listaEsferasNoCreadas = new ArrayList<Esfera.CantidadEstrellas>();

		for (CantidadEstrellas esferaExistente : Arrays
				.asList(CantidadEstrellas.values())) {
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
			Punto<Integer> ubicacionEsfera) {
		Punto<Integer> dif = new Punto<Integer>(
				(ubicacionPersonaje.getX() - ubicacionEsfera.getX()),
				(ubicacionPersonaje.getY() - ubicacionEsfera.getY()));
		return Math.abs(dif.getX()) + Math.abs(dif.getY());
	}

	/**
	 * Calculo la diferencia absoluta entre cada elemento del par
	 */
	private Integer calcularDistancia(Personaje personaje, Esfera esfera) {
		return this.diferenciaAbsoluta(this.buscarPosicion(personaje),
				this.buscarPosicion(esfera));
	}

	/**
	 * 
	 * @param personaje
	 * @param esfera
	 * @return true si puede capturar la esfera. ( tiene que estar dentro de la
	 *         distancia que puede recorrer el personaje
	 */
	public boolean puedeCapturarEsfera() {
		return personajeBuscado.getDistancia() >= calcularDistancia(
				personajeBuscado, esferaBuscada);
	}

	public String puedeCapturarEsferaMensaje() {
		String accion;

		if (this.puedeCapturarEsfera()) {
			accion = "llega";
		} else {
			accion = "no llega";
		}

		return "El personaje " + this.getPersonajeBuscado().getNombre() + " "
				+ accion + " a capturar la "
				+ this.getEsferaBuscada().toString();
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

		throw new UserException("El " + objeto.getClass().toString() + " "
				+ objeto.toString() + " no se encuentra en el mapa.");

	}

	public Posicionable buscarObjeto(Punto<Integer> posicion) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.isPosicion(posicion)) {
				return casillero.getObjeto();
			}
		}

		throw new UserException("La posicion " + posicion.toString()
				+ " se encuentra vacia en el mapa.");
	}

	public boolean hayObjetoEn(Punto<Integer> posicion) {

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.isPosicion(posicion)) {
				return true;
			}
		}

		return false;
	}

	public Boolean personajeCapturaEsfera() {
		Boolean puedeCapturarla = this.puedeCapturarEsfera();

		if (puedeCapturarla) {
			personajeBuscado.addToInventario(esferaBuscada);

			Casillero casilleroPersonaje = personajeBuscado.getCasillero();
			Casillero casilleroEsfera = esferaBuscada.getCasillero();

			casilleroEsfera.setObjeto(personajeBuscado);
			this.removeCasilla(casilleroPersonaje);
		}

		return puedeCapturarla;
	}

	public String personajeCapturaEsferaMensaje() {
		String accion;

		if (this.personajeCapturaEsfera()) {
			accion = "capturo";
		} else {
			accion = "no puede capturar";
		}

		return "El personaje " + this.getPersonajeBuscado().getNombre() + " "
				+ accion + " la " + this.getEsferaBuscada().toString();
	}

	public String llamarShenLongMensaje() {
		String accion;

		if (this.getPersonajeBuscado().puedeInvocarShengLong()) {
			accion = "puede";
		} else {
			accion = "no puede";
		}

		return "El personaje " + this.getPersonajeBuscado().getNombre() + " "
				+ accion + " llamar a Sheng Long";
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
		this.validatePosicionValida(casillero);
		this.validatePosicionDisponible(casillero.getPosicion());
		this.casilleros.add(casillero);
		this.actualizarVista();
	}

	private void validatePosicionValida(Casillero casillero) {
		Punto<Integer> posicion = casillero.getPosicion();
		if (!posicion.between(new Punto<Integer>(0, 0), this.getDimension())) {
			throw new UserException("La Posicion " + posicion + " del "
					+ casillero.getObjeto() + " no es valida.");
		}
	}

	/**
	 * Metodo que verifica la posicion del mapa esta vacia
	 */
	protected void validatePosicionDisponible(Punto<Integer> posicion) {
		for (Casillero casillero : this.getCasilleros()) {
			casillero.assertNoEstasEnPosicion(posicion);
		}
	}

	public void removeCasilla(Casillero casillero) {
		this.casilleros.remove(casillero);
		this.actualizarVista();
	}

	private void actualizarVista() {
		this.firePropertyChange(CASILLEROS, null, this.getCasilleros());
		this.firePropertyChange(LISTA_ESFERAS, null, this.getListaEsferas());
		this.firePropertyChange(LISTA_PERSONAJES, null,
				this.getListaPersonajes());
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

	public boolean isPuedeLlamarAShengLong() {
		return this.getPersonajeBuscado() != null;
	}

	public boolean isHayPersonajeYEsferaBuscada() {
		return (!this.getListaPersonajes().isEmpty())
				&& (!this.getListaEsferas().isEmpty());
	}

	public boolean isFaltanCrearPersonajes() {
		return (!this.getListaPersonajesNoCreadas().isEmpty());
	}

	public void actualizarFaltanCrearPersonajes() {
		this.firePropertyChange(FALTAN_CREAR_PERSONAJES, null,
				this.isFaltanCrearPersonajes());
	}

	public boolean isFaltanCrearEsferas() {
		return (!this.getListaEsferasNoCreadas().isEmpty());
	}

	public void actualizarFaltanCrearEsferas() {
		this.firePropertyChange(FALTAN_CREAR_ESFERAS, null,
				this.isFaltanCrearEsferas());
	}

	/**
	 * Obtengo todo el Tablero
	 */
	public List<List<Casillero>> getTablero() {
		List<List<Casillero>> tablero = new ArrayList<List<Casillero>>();

		for (int j = 0; j < this.getDimension().getY(); j++) {
			tablero.add(j, this.getFila(j));
		}

		System.out.println(tablero);

		return tablero;
	}

	/**
	 * Obtengo toda la Fila del Tablero
	 */
	public List<Casillero> getFila(int numeroFila) {
		List<Casillero> fila = new ArrayList<Casillero>();

		for (int i = 0; i < this.getDimension().getX(); i++) {
			Punto<Integer> posicion = new Punto<Integer>(i, numeroFila);
			if (this.hayObjetoEn(posicion))
				fila.add(this.buscarObjeto(posicion).getCasillero());
			else
				fila.add(new Casillero(posicion, new PosicionableNulo()));
		}

		return fila;
	}

	/**
	 * Obtengo toda la Columna del Tablero
	 */
	public List<Casillero> getColumna(int numeroColumna) {
		List<Casillero> columna = new ArrayList<Casillero>();

		for (int j = 0; j < this.getDimension().getY(); j++) {
			Punto<Integer> posicion = new Punto<Integer>(numeroColumna, j);
			if (this.hayObjetoEn(posicion))
				columna.add(this.buscarObjeto(posicion).getCasillero());
			else
				columna.add(new Casillero(posicion, new PosicionableNulo()));
		}

		return columna;
	}

	/**
	 * Imprimo los elementos que contiene el mapa
	 */
	public void imprimirMapa() {
		System.out.println("Mapa: \t{");
		for (Casillero casillero : this.getCasilleros()) {

			System.out.println("\t [ " + casillero.getPosicion().toString()
					+ " ; " + casillero.getObjeto().toString() + " ] ");
		}
		System.out.println("\t}");

	}

	/**
	 * Imprimo el tablero como una matriz dando detalles de su contenido.
	 */
	public void imprimirTablero() {
		Punto<Integer> dimension = this.getDimension();

		for (int i = 0; i < dimension.getX(); i++) {
			System.out.print((i + 1) + ":\t[ ");
			for (int j = 0; j < dimension.getY(); j++) {
				System.out.print(" ("
						+ objetoDelCasilleroString(new Punto<Integer>(i, j))
						+ ") ");
			}
			System.out.print(" ]\n");
		}
		System.out.print("\n");
	}

	private String objetoDelCasilleroString(Punto<Integer> posicion) {
		String objetoDelCasillero;

		if (this.hayObjetoEn(posicion)) {
			Posicionable objeto = this.buscarObjeto(posicion);
			if (objeto.esEsfera())
				objetoDelCasillero = "E."
						+ ((Esfera) objeto).getNumero().getCantidadEstrellas();
			else
				objetoDelCasillero = "P."
						+ ((Personaje) objeto).toString().charAt(0);
		} else {
			objetoDelCasillero = "   ";
		}

		return objetoDelCasillero;
	}

	public void reubicarEsferasDelPersonaje(Personaje personaje) {
		List<Esfera> esferas = personaje.getInventario();
		
		personaje.setInventario(new ArrayList<Esfera>());
		
		for (Esfera esfera : esferas) {
			esfera.setCasillero(null);
			this.ubicarEsferaRandom(esfera);
		}
	}

	/**
	 * Ubica una esfera en el Mapa generando posiciones aleatorias.
	 * Si la posicion esta ocuapado por un personaje le asigna esa esfera como capturada
	 * @param esfera
	 */
	private void ubicarEsferaRandom(Esfera esfera) {
		Punto<Integer> ubicacionRandom = new Punto<Integer>(-1, -1);

		while ((!existeEsferaAtrapadaEnLosPersonajes(esfera.getNumero()))
				&& (!existeEsferaEnElMapa(esfera.getNumero()))) {
			
			ubicacionRandom.setX(Randomize.randomInteger(0, this.getDimension().getX() - 1));
			ubicacionRandom.setY(Randomize.randomInteger(0, this.getDimension().getY() - 1));

 			if (this.hayObjetoEn(ubicacionRandom)) {
				Posicionable objetoPosicionable = this.buscarObjeto(ubicacionRandom);
				if (objetoPosicionable.esPersonaje()) {
					((Personaje) objetoPosicionable).addToInventario(esfera);
					System.out.println("La Esfera " + esfera + " fue atrapada por el personaje " + objetoPosicionable);
				}
			} else {
				casilleros.add(new Casillero(ubicacionRandom, esfera));
			}
		}
		
		System.out.println("La Esfera " + esfera + " se coloco en la posicion " + ubicacionRandom);
	}

}
