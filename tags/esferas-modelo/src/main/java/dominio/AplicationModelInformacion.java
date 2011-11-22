package dominio;

import org.uqbar.commons.model.ObservableObject;

public class AplicationModelInformacion extends ObservableObject {

	public static final String TITULO = "titulo";
	public static final String MENSAJE = "mensaje";

	private String titulo;
	private String mensaje;

	public AplicationModelInformacion() {
		super();
	}

	public AplicationModelInformacion(String titulo, String mensaje) {
		this();
		this.titulo = titulo;
		this.mensaje = mensaje;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
