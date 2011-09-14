package dominio;

import org.uqbar.commons.model.ObservableObject;

import utils.Punto;

public abstract class Posicionable extends ObservableObject {

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
		this.setProperty(CASILLERO, casillero);
		if (!(this.getCasillero() == null) && !(this.getCasillero().equals(casillero))) {
			this.casillero.setObjeto(this);
		}
	}
	
	
	private Punto<Integer> getPosicion() {
		return this.casillero.getPosicion();
	}

	public Integer getX() {
		return getPosicion().getX();
	}

	public void setX(Integer x) {
		this.casillero.setPosicion(new Punto<Integer>(x, getPosicion().getY()));
	}

	public Integer getY() {
		return getPosicion().getY();
	}

	public void setY(Integer y) {
		this.casillero.setPosicion(new Punto<Integer>(getPosicion().getX(), y));
	}

	public abstract boolean esPersonaje();
	public abstract boolean esEsfera();
}
