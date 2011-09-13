package dominio;

import org.uqbar.commons.model.ObservableObject;

import utils.Punto;

public class Casillero extends ObservableObject {

	public static final String POSICION = "posicion";
	public static final String OBJETO = "objeto";

	private Punto<Integer> posicion;
	private Posicionable objeto;

	public Casillero() {
		super();
		this.posicion = new Punto<Integer>(0, 0);
		this.objeto = null;
	}

	public Casillero(Punto<Integer> posicion, Posicionable objeto) {
		super();
		this.posicion = posicion;
		this.objeto = objeto;
	}

	public Boolean hasObject(Posicionable objeto) {
		return this.getObjeto().equals(objeto);
	}

	public Punto<Integer> getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto<Integer> posicion) {
		this.posicion = posicion;
	}

	public Posicionable getObjeto() {
		return objeto;
	}

	public void setObjeto(Posicionable objeto) {
		this.objeto = objeto;
	}

}
