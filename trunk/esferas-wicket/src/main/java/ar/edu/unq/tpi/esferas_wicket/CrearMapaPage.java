package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import utils.Punto;
import dominio.Mapa;

public class CrearMapaPage extends TemplatePage<Punto<Integer>> {
	private static final long serialVersionUID = 1L;
	private static final String NAME_FORM = "nuevoMapaForm";

	public CrearMapaPage() {
		this.initPage(new Form<Punto<Integer>>(NAME_FORM, new CompoundPropertyModel<Punto<Integer>>(new Punto<Integer>(0,0))));
	}
	
	public CrearMapaPage(TemplatePage<?> paginaOrigen) {
		super(paginaOrigen);
	}
	
	protected void addFields() {
    	this.addPunto();
	}

	protected void addButtons() {
		this.getForm().add(new Button("aceptar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				CrearMapaPage.this.setMapa(new Mapa(CrearMapaPage.this.getForm().getModelObject()));
				this.setResponsePage(new MapaPage(CrearMapaPage.this));
			}
		});

		this.addButtonCancelToClear();
	}
	
}