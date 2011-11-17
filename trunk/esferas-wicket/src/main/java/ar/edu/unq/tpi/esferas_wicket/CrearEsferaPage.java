package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import dominio.Casillero;
import dominio.Esfera;

public class CrearEsferaPage extends EsferaPage {
	private static final long serialVersionUID = 1L;
	
	public CrearEsferaPage(EsferaPage paginaOrigen) {
		super(paginaOrigen);
		Form<Esfera> form = new Form<Esfera>("esferaForm", this.createModel());
		this.add(form);
    	this.addFields(form);
		this.addButtons(form);
	}

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields(final Form<Esfera> form) {
		this.addPunto(form);
		this.add(new DropDownChoice<Esfera.CantidadEstrellas>("sinEsferas", this.getMapa().getListaEsferasNoCreadas()));
    	this.addFeedbackPanel(form);
	}

	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons(final Form<Esfera> form) {
		form.add(new Button("aceptar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// invoca la logica de negocio
				CrearEsferaPage.this.getMapa().addCasillero(form.getModelObject().getCasillero());
				// navegacion: vuelve a la pagina de busqueda.
				this.setResponsePage(MapaPage.class);		
				System.out.println("Aprete Aceptar");
			}
		});

		this.addButtonCancel(form);
	}
	
	protected CompoundPropertyModel<Esfera> createModel() {
		return new CompoundPropertyModel<Esfera>(new Esfera());
	}

}
