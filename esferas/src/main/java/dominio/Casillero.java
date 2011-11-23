package dominio;

import java.io.Serializable;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;

public class Casillero extends ObservableObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String POSICION = "posicion";
	public static final String OBJETO = "objeto";

	private Punto<Integer> posicion;
	private Posicionable objeto;

	public Casillero() {
		super();
		this.posicion = null;
		this.objeto = null;
	}

	public Casillero(Punto<Integer> posicion, Posicionable posicionable) {
		this();
		this.posicion = posicion;
		this.setObjeto(posicionable);
	}

	public Casillero(Posicionable posicionable) {
		this(new Punto<Integer>((-1), (-1)), posicionable);
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
	
	public void setX(Integer x) {
		this.posicion.setX(x);
	}
	
	public void setY(Integer y) {
		this.posicion.setY(y);
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
