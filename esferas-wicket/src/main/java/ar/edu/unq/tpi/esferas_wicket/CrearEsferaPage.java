package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import dominio.Esfera;

public class CrearEsferaPage extends PosicionablePage<Esfera> {
	private static final String NAME_FORM = "esferaForm";
	
	public CrearEsferaPage(TemplatePage<?> paginaOrigen) {
		super(paginaOrigen, new Form<Esfera>(NAME_FORM, new CompoundPropertyModel<Esfera>(new Esfera())));
	}

	protected void addPosicionableFields() {
		this.getForm().add(new DropDownChoice<Esfera.CantidadEstrellas>(Esfera.NUMERO, this.getMapa().getListaEsferasNoCreadas()));
	}
	
}
