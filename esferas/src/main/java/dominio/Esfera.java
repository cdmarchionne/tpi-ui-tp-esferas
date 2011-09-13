package dominio;

import utils.Punto;

public class Esfera extends Posicionable implements ObjetosDragonBall {

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

}
