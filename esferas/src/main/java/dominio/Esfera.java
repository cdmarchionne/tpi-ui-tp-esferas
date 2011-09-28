package dominio;

import utils.Punto;

public class Esfera extends Posicionable {

	public static final String ESFERA = "esfera";
	public static final String NUMERO = "numero";
	public static final String CANTIDAD_ESTRELLAS = "cantidadEstrellas";

	private CantidadEstrellas numero;

	public Esfera() {
		super();
		this.numero = CantidadEstrellas.UNA;
	}

	public Esfera(CantidadEstrellas cantidad) {
		super();
		this.numero = cantidad;
	}

	public Esfera(Punto<Integer> posicion) {
		this();
		this.setCasillero(new Casillero(posicion, this));
	}

	public Esfera(Punto<Integer> posicion, CantidadEstrellas cantidad) {
		this(posicion);
		this.numero = cantidad;
	}

	@Override
	public String toString() {
		return "Esfera de " + numero.getCantidadEstrellas() + " Estrellas";
	}

	public CantidadEstrellas getNumero() {
		return numero;
	}

	public void setNumero(CantidadEstrellas numero) {
		this.numero = numero;
	}

	public boolean esPersonaje() {
		return false;
	}

	public boolean esEsfera() {
		return true;
	}

	/**
	 * 
	 * @author mariano
	 *         Algo para dejar contento al cliente
	 */

	public enum CantidadEstrellas {
		UNA(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7);

		private int cantidadEstrellas;

		private CantidadEstrellas(int cantidad) {
			this.cantidadEstrellas = cantidad;
		}

		public int getCantidadEstrellas() {
			return this.cantidadEstrellas;
		}

	}
}
