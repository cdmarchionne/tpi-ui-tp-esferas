package ar.edu.unq.tpi.esferas_wicket;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.MinimumValidator;

import utils.Punto;
import dominio.Mapa;
import dominio.Posicionable;

public class PosicionablePage extends WebPage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private PosicionablePage paginaOrigen;
	
	public PosicionablePage() {
		super();
	}
	
	public PosicionablePage(PosicionablePage paginaOrigen) {
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
		campo.add(new MinimumValidator<Integer>(0));
		return campo;
	}
	
	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addPunto(final Form<?> form) {
		form.add(this.createRequiredIntegerField(Punto.X));
		form.add(this.createRequiredIntegerField(Punto.Y));
	}
	
	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtonsOfPosicionable(final Form<? extends Posicionable> form) {
		this.addCrearBotonAceptarParaPosicionables(form);
		this.addButtonCancelToReverse(form);
	}

	protected final void addCrearBotonAceptarParaPosicionables(final Form<? extends Posicionable> form) {
		form.add(new Button("aceptar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// invoca la logica de negocio
				PosicionablePage.this.getMapa().addCasillero(form.getModelObject().getCasillero());
				// navegacion: vuelve a la pagina de busqueda.
				this.setResponsePage(MapaPage.class);		
			}
		});
	}
	
	protected final void addButtonCancelToClear(final Form<?> form) {
		form.add(new Button("cancel") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onSubmit() {
					form.clearInput();
			}
		});
	}

	public final void addButtonCancelToReverse(final Form<?> form) {
		Button cancelButton = new Button("cancel") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit() {
					this.setResponsePage(paginaOrigen);		
			}
		};
		cancelButton.setDefaultFormProcessing(false);
		form.add(cancelButton);
	}

	public PosicionablePage getPaginaOrigen() {
		return paginaOrigen;
	}

	public void setPaginaOrigen(PosicionablePage paginaOrigen) {
		this.paginaOrigen = paginaOrigen;
	}

}
