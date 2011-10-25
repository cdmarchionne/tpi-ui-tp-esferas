package dominio;

public class PosicionableNulo extends Posicionable{

	@Override
	public String getName() {
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

