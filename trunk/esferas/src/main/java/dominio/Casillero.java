package dominio;

import utils.Punto;

public class Casillero {

	private Punto<Integer> posicion;
	private ObjetosDragonBall objeto;

	public Casillero() {
		super();
	}

	public Casillero(Punto<Integer> posicion, ObjetosDragonBall objeto) {
		super();
		this.posicion = posicion;
		this.objeto = objeto;
	}

	public void setObjeto(Punto<Integer> posicion, ObjetosDragonBall objeto) {
		this.posicion = posicion;
		this.objeto = objeto;
	}

	public Boolean hasObject(ObjetosDragonBall objeto) {
		return this.getObjeto().equals(objeto);
	}

	public Punto<Integer> getPosicion() {
		return posicion;
	}

	public ObjetosDragonBall getObjeto() {
		return objeto;
	}

	public void setPosicion(Punto<Integer> posicion) {
		this.posicion = posicion;
	}

	public void setObjeto(ObjetosDragonBall objeto) {
		this.objeto = objeto;
	}

}
