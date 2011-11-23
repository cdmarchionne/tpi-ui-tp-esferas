package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import dominio.Esfera;

public class CrearEsferaPage extends PosicionablePage {
	private static final long serialVersionUID = 1L;
	private static final String NAME_FORM = "esferaForm";
	
	public CrearEsferaPage(PosicionablePage paginaOrigen) {
		super(paginaOrigen);
		Form<Esfera> form = new Form<Esfera>(NAME_FORM, this.createModel());
		this.add(form);
    	this.addFields(form);
		this.addButtonsOfPosicionable(form);
	}

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields(final Form<Esfera> form) {
		this.addPunto(form);
		form.add(new DropDownChoice<Esfera.CantidadEstrellas>(Esfera.NUMERO, this.getMapa().getListaEsferasNoCreadas()));
    	this.addFeedbackPanel(form);
	}

	protected CompoundPropertyModel<Esfera> createModel() {
		return new CompoundPropertyModel<Esfera>(new Esfera());
	}

}
