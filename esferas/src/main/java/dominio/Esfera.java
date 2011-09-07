package dominio;

public class Esfera implements ObjetosDragonBall {

	public static final String NUMERO = "numero";

	private int numero;

	public Esfera() {
		super();
		numero = 0;
	}

	public Esfera(int cantidad) {
		super();
		this.numero = cantidad;
	}

	@Override
	public String toString() {
		return "Esfera de " + Integer.toString(numero) + " Estrellas";
	}

	public int getCantidad() {
		return numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
