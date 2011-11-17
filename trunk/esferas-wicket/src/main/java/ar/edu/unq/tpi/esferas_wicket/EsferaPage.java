package ar.edu.unq.tpi.esferas_wicket;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import utils.Punto;
import dominio.Mapa;

public class EsferaPage extends WebPage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private EsferaPage paginaOrigen;
	
	public EsferaPage() {
		super();
	}
	
	public EsferaPage(EsferaPage paginaOrigen) {
		this();
		this.paginaOrigen = paginaOrigen;
	}

	/**
	 * Agrega el panel de errores al formulario
	 */
	protected void addFeedbackPanel(Form<?> form) {
    	form.add(new FeedbackPanel("feedbackPanel"));
	}

	public final Mapa getMapa() {
		return ((WicketApplication) this.getApplication()).getMapa();
	}

	public final void setMapa(Mapa mapa) {
		((WicketApplication) this.getApplication()).setMapa(mapa);
	}
	
	/**
	 * Creo un Campo de texto que se completa con un valor entero dentro de un formulario
	 * @param nombre
	 * @return
	 */
	public final RequiredTextField<Integer> createRequiredIntegerField(String nombre) {
		RequiredTextField<Integer> campo = new RequiredTextField<Integer>(nombre,Integer.class);
//		campo.add(validator);
		return campo;
	}
	
	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addPunto(final Form<?> form) {
		form.add(this.createRequiredIntegerField(Punto.X));
		form.add(this.createRequiredIntegerField(Punto.Y));
	}
	
	public final void addButtonCancel(final Form<?> form) {
		form.add(new Button("cancel") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onSubmit() {
					form.clearInput();
			}
		});
	}

	public EsferaPage getPaginaOrigen() {
		return paginaOrigen;
	}

	public void setPaginaOrigen(EsferaPage paginaOrigen) {
		this.paginaOrigen = paginaOrigen;
	}

}
