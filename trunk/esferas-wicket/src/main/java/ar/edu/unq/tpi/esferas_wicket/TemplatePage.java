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

public abstract class TemplatePage<T> extends WebPage implements Serializable{
	public static final String FEEDBACK_PANEL = "feedbackPanel";

	private Form<T> form;
	
	private TemplatePage<?> paginaOrigen;
	
	public TemplatePage() {
		super();
	}
	
	public TemplatePage(TemplatePage<?> paginaOrigen) {
		this();
		this.paginaOrigen = paginaOrigen;
	}
	
	public TemplatePage(TemplatePage<?> paginaOrigen, Form<T> formulario) {
		this(paginaOrigen);
		this.initPage(formulario);
	}
	
	public void initPage(Form<T> formulario) {
		this.setForm(formulario);
		this.add(this.getForm());
    	this.addFields();
		this.addButtons();
		this.addFeedbackPanel();
	}

	protected abstract void addFields();
	
	protected abstract void addButtons();
	
	/**
	 * Agrega el panel de errores al formulario
	 */
	protected void addFeedbackPanel() {
    	this.form.add(new FeedbackPanel(FEEDBACK_PANEL));
	}

	/**
	 * Creo un Campo de texto que se completa con un valor entero dentro de un formulario
	 */
	public final RequiredTextField<Integer> createRequiredIntegerField(String nombre) {
		RequiredTextField<Integer> campo = new RequiredTextField<Integer>(nombre,Integer.class);
		campo.add(new MinimumValidator<Integer>(0));
		return campo;
	}
	
	/**
	 * Crea y agrega los controles para Manejar un Punto.
	 */
	protected void addPunto() {
		this.form.add(this.createRequiredIntegerField(Punto.X));
		this.form.add(this.createRequiredIntegerField(Punto.Y));
	}
	
	//*******************************************
	//			BUTTONS
	//*******************************************
	
	protected final void addButtonCancelToClear() {
		Button cancelClearButton = new Button("cancel") {
				@Override
				public void onSubmit() {
					form.clearInput();
			}
		};
		this.form.add(cancelClearButton);
	}

	public final void addButtonCancelToReverse() {
		Button cancelReverseButton = new Button("cancel") {
				@Override
				public void onSubmit() {
					this.setResponsePage(paginaOrigen);		
			}
		};
		cancelReverseButton.setDefaultFormProcessing(false);
		this.form.add(cancelReverseButton);
	}

	//*******************************************
	//			GETTER Y SETTER
	//*******************************************
	
	public TemplatePage<?> getPaginaOrigen() {
		return paginaOrigen;
	}

	public void setPaginaOrigen(TemplatePage<?> paginaOrigen) {
		this.paginaOrigen = paginaOrigen;
	}

	public Form<T> getForm() {
		return form;
	}

	public void setForm(Form<T> form) {
		this.form = form;
	}

	public final Mapa getMapa() {
		Object mapa = this.getSession().getAttribute(Mapa.MAPA);
		return (mapa==null) ? null : (Mapa) mapa;
	}

	public final void setMapa(Mapa mapa) {
		this.getSession().setAttribute(Mapa.MAPA, mapa);
	}
	
}
