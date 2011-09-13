package dominio;

import org.uqbar.commons.model.ObservableObject;

import utils.Punto;

public abstract class Posicionable extends ObservableObject implements ObjetosDragonBall {

	private Casillero casillero;

	public Posicionable() {
		super();
		this.casillero = new Casillero();
	}

	public Posicionable(Casillero casillero) {
		super();
		this.casillero = casillero;
	}

	public Casillero getCasillero() {
		return this.casillero;
	}

	public void setCasillero(Casillero casillero) {
		this.casillero = casillero;
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

}
