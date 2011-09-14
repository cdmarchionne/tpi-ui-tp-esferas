package dominio;

import utils.Punto;

public class Esfera extends Posicionable {

	public static final String ESFERA = "esfera";
	public static final String NUMERO = "numero";

	private int numero;

	public Esfera() {
		super();
		this.numero = 0;
	}

	public Esfera(int cantidad) {
		super();
		this.numero = cantidad;
	}

	public Esfera(Punto<Integer> posicion) {
		this();
		this.setCasillero(new Casillero(posicion, this));
	}

	public Esfera(Punto<Integer> posicion, int cantidad) {
		this(posicion);
		this.numero = cantidad;
	}

	@Override
	public String toString() {
		return "Esfera de " + Integer.toString(numero) + " Estrellas";
	}

	public int getCantidad() {
		return numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public boolean esPersonaje(){
		return false;
	}
	
	public boolean esEsfera(){
		return true;
	}

	/**
	 * 
	 * @author mariano
	 *Algo para dejar contento al cliente
	 */
	
	public enum CantidadEstrellas {
		UNA("1"),
		DOS("2"),
		TRES("3"),
		CUATRO("4"),
		CINCO("5"),
		SEIS("6"),
		SIETE("7");
		
		private String cantidadEstrellas;
		
		private CantidadEstrellas(String cantidad) {
			this.cantidadEstrellas = cantidad;
		}
		
		public String getCantidadEstrellas() {
			return this.cantidadEstrellas;
		}
	}
}
