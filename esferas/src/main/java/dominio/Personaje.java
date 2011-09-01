package dominio;

public class Personaje implements ObjetosDragonBall {

	private String nombre;
	private Integer distancia;

	public Personaje(String nombre, Integer distancia) {
		super();
		this.nombre = nombre;
		this.distancia = distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Integer getDistancia() {
		return distancia;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
