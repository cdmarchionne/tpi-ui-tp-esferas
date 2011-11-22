package utils;

import java.io.Serializable;

import org.uqbar.commons.model.UserException;

/**
 * Clase que modela el comportamiento de un Punto
 * 
 * @author cristian
 * 
 * @param <T>
 */
public class Punto<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PUNTO = "Punto";
	public static final String X = "x";
	public static final String Y = "y";

	private T x;
	private T y;

	public Punto(T x, T y) {
		super();
		this.x = x;
		this.y = y;
	}

	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}

	public void setX(T x) {
		this.x = x;
	}

	public void setY(T y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "( " + this.getX() + " , " + this.getY() + " )";
	}

	@Override
	public int hashCode() {
		return x.hashCode() ^ y.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (!(o instanceof Punto))
			return false;

		Punto<?> punto = (Punto<?>) o;
		return this.x.equals(punto.getX()) && this.y.equals(punto.getY());
	}

	public boolean between(Punto<Integer> inicio, Punto<Integer> fin) {
		try {
			@SuppressWarnings("unchecked")
			Punto<Integer> valorActual = (Punto<Integer>) this;
			return between(inicio.getX(), valorActual.getX(), fin.getX())
					&& between(inicio.getY(), valorActual.getY(), fin.getY());
		} catch (Exception e) {
			throw new UserException(
					"No se puede realizar la comparacion entre elementos distintos");
		}
	}

	private boolean between(Integer inicio, Integer valorActual, Integer fin) {
		return (inicio <= valorActual) && (valorActual < fin);
	}

}
