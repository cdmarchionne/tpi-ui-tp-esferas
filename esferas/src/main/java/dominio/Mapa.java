package dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;

public class Mapa extends ObservableObject {

	public static final String CASILLEROS = "casilleros";
	public static final String CASILLAS = "casillas";
	public static final String CALCULAR_DISTANCIA = "calcularDistancia";
	public static final String PUEDE_CAPTURAR = "puedeCapturarEsfera";

	private Punto<Integer> dimension;
	private Set<Casillero> casilleros;//No se usa
	private List<Casillero> casillas;
	
	public Mapa(Punto<Integer> dimension) {
		super();
		this.setDimension(dimension);
		casilleros = new HashSet<Casillero>();//No se usa
		casillas = new ArrayList<Casillero>();
		
		
		//Esfera e = new Esfera(3);
		Personaje p = new Personaje("Goku",4);
		casillas.add(p.getCasillero());
		
	}

	// ********************************************************
	// ** Acciones
	// ********************************************************

	public ArrayList<String> listaPersonajes(){
		
		ArrayList<String> resultado = new ArrayList<String>();
		
		for(Casillero casillero : this.getCasillas()){
			if(casillero.esPersonaje()){
				resultado.add(casillero.getObjeto().toString());			
			}
		}
	
	return resultado;
	}
	
	public ArrayList<String> listaEsferas(){
		ArrayList<String> resultado = new ArrayList<String>();
		for(Casillero casillero : this.getCasillas()){
			if(casillero.esEsfera()){
				resultado.add(casillero.getObjeto().toString());			
			}
		}
	
	return resultado;
	}

	

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
	public Punto<Integer> buscar(Posicionable objeto) {//No se usa

		for (Casillero casillero : this.getCasilleros()) {
			if (casillero.hasObject(objeto)) {
				return casillero.getPosicion();
			}
		}

		throw new UserException("El " + objeto.getClass().toString() + " " + objeto.toString()
				+ " no se encuenrta en el mapa.");

	}
	
	public Punto<Integer> buscar2(Posicionable objeto) {

		for (Casillero casillero : this.getCasillas()) {
			if (casillero.hasObject(objeto)) {
				return casillero.getPosicion();
			}
		}

		throw new UserException("El " + objeto.getClass().toString() + " " + objeto.toString()
				+ " no se encuenrta en el mapa.");

	}

	/**
	 * 
	 * @param personaje 
	 * @param esfera
	 * @return true si puede capturar la esfera. ( tiene que estar dentro de la distancia que puede
	 * recorrer el personaje
	 */
	public boolean puedeCapturarEsfera(Personaje personaje, Esfera esfera){
		Punto<Integer> posicionPersonaje = buscar2(personaje);
		Punto<Integer> posicionEsfera = buscar2(esfera);
		return personaje.getDistancia()>= diferenciaAbsoluta(posicionPersonaje,posicionEsfera);
		
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
	
	public List<Casillero> getCasillas() {
		return casillas;
	}

	public void setCasillas(ArrayList<Casillero> casillas) {
		this.casillas = casillas;
	}

	
	
	
}
