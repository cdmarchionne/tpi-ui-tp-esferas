package dominio;

import java.io.Serializable;

public class PosicionableNulo extends Posicionable implements Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "nulo";
	}

	@Override
	public String toString() {
		return "nulo";
	}

	@Override
	public boolean esPersonaje() {
		return false;
	}

	@Override
	public boolean esEsfera() {
		return false;
	}
	
}

