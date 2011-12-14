package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import dominio.Posicionable;

public abstract class PosicionablePage<T extends Posicionable> extends TemplatePage<T> {

	public PosicionablePage(TemplatePage<?> paginaOrigen, Form<T> formulario) {
		super(paginaOrigen, formulario);
	}

	protected void addFields() {
		this.addPunto();
    	this.addPosicionableFields();
	}

	protected abstract void addPosicionableFields();
	
	protected void addButtons() {
		this.addButtonsOfPosicionable();
	}
	
	//*******************************************
	//			BUTTONS
	//*******************************************
	
	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtonsOfPosicionable() {
		this.addCrearBotonAceptarParaPosicionables();
		this.addButtonCancelToReverse();
	}

	protected final void addCrearBotonAceptarParaPosicionables() {
		Button aceptarButton = new Button("aceptar") {

			@Override
			public void onSubmit() {
				PosicionablePage.this.getMapa().addCasillero(PosicionablePage.this.getForm().getModelObject().getCasillero());
				this.setResponsePage(MapaPage.class);		
			}
		};
		this.getForm().add(aceptarButton);
	}
	


}
