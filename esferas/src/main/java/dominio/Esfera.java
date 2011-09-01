package dominio;

public class Esfera implements ObjetosDragonBall {

	private final int cantidad;

	public Esfera(int cantidad) {
		super();
		this.cantidad = cantidad;
	}

	@Override
	public String getNombre() {
		return Integer.toString(cantidad) + " Estrellas";
	}

	public int getCantidad() {
		return cantidad;
	}

}
