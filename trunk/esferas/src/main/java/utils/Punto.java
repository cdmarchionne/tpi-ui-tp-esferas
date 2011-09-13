package utils;


/**
 * Clase que modela el comportamiento de un Punto
 * 
 * @author cristian
 * 
 * @param <T>
 */
public class Punto<T> {

	public static final String PUNTO = "Punto";

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
		return "(" + this.getX() + this.getY() + ")";
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

}
