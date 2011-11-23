package dominio;

import java.io.Serializable;

import org.uqbar.commons.model.ObservableObject;
import org.uqbar.commons.model.UserException;

import utils.Punto;

public abstract class Posicionable extends ObservableObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String CASILLERO = "casillero";
	private Casillero casillero;

	public Posicionable() {
		super();
		this.setCasillero(new Casillero(this));
	}

	public Posicionable(Casillero casillero) {
		super();
		this.setCasillero(casillero);
	}

	public Casillero getCasillero() {
		return this.casillero;
	}

	public void setCasillero(Casillero casillero) {
		Casillero casilleroOLD = this.getCasillero();

		this.setProperty(CASILLERO, casillero);
		this.firePropertyChange(CASILLERO, casilleroOLD, this.getCasillero());
	}

	protected final Punto<Integer> getPosicion() {
		return this.casillero.getPosicion();
	}
	
	protected final void setPosicion(Punto<Integer> posicion) {
		this.casillero.setX(posicion.getX());
		this.casillero.setY(posicion.getY());
	}

	public Integer getX() {
		return getPosicion().getX();
	}

	public void setX(Integer x) {
		if (!validarEnteroPositivo(x)) {
			throw new UserException("Coordenada X (" + x + ") debe ser mayor a 0");
		}
		this.casillero.setX(x);
	}

	public Integer getY() {
		return getPosicion().getY();
	}

	public void setY(Integer y) {
		if (!validarEnteroPositivo(y)) {
			throw new UserException("Coordenada Y debe ser mayor a 0");
		}
		this.casillero.setY(y);
	}

	public abstract String getName();
	
	public abstract boolean esPersonaje();

	public abstract boolean esEsfera();

	private boolean validarEnteroPositivo(Integer numero) {
		return numero != null && numero >= 0;
	}

}
