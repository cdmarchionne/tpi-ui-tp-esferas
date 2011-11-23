package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import dominio.Personaje;

public class CrearPersonajePage extends PosicionablePage {
	private static final long serialVersionUID = 1L;
	private static final String NAME_FORM = "personajeForm";
	
	public CrearPersonajePage(PosicionablePage paginaOrigen) {
		super(paginaOrigen);
		Form<Personaje> form = new Form<Personaje>(NAME_FORM, this.createModel());
		this.add(form);
    	this.addFields(form);
		this.addButtonsOfPosicionable(form);
	}

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields(final Form<Personaje> form) {
		this.addPunto(form);
		form.add(new DropDownChoice<Personaje.NombrePersonaje>(Personaje.NOMBRE, this.getMapa().getListaPersonajesNoCreadas()));
		form.add(this.createRequiredIntegerField(Personaje.DISTANCIA));
    	this.addFeedbackPanel(form);
	}

	protected CompoundPropertyModel<Personaje> createModel() {
		return new CompoundPropertyModel<Personaje>(new Personaje());
	}

}
