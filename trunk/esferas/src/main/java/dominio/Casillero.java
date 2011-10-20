package dominio;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

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
		this.setObjeto(objeto);
	}

	public Casillero(Posicionable posicionable) {
		this();
		this.setObjeto(posicionable);
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
		Posicionable objetoOLD = this.getObjeto();

		this.setProperty(OBJETO, objeto);
		this.firePropertyChange(OBJETO, objetoOLD, this.getObjeto());
		objeto.setCasillero(this);
	}

	public boolean esPersonaje() {
		return this.getObjeto().esPersonaje();
	}

	public boolean esEsfera() {
		return this.getObjeto().esEsfera();
	}

	public void assertNoEstasEnPosicion(Punto<Integer> posicion) {
		if (isPosicion(posicion)) {
			throw new UserException("La Posicion " + posicion + " ya ocupada por: "
					+ this.getObjeto());
		}
	}

	public boolean isPosicion(Punto<Integer> posicion) {
		return this.getPosicion().equals(posicion);
	}
}