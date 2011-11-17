package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import utils.Punto;
import dominio.Mapa;

public class CrearMapaPage extends EsferaPage {
	private static final long serialVersionUID = 1L;

	public CrearMapaPage() {
		Form<Punto<Integer>> form = new Form<Punto<Integer>>("nuevoMapaForm", this.createModel());
		this.add(form);
		this.addFeedbackPanel(form);
		this.addPunto(form);
		this.addButtons(form);
	}
	
	public CrearMapaPage(EsferaPage paginaOrigen) {
		super(paginaOrigen);
	}
	
	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons(final Form<Punto<Integer>> form) {
		form.add(new Button("aceptar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// navegacion: vuelve a la pagina de busqueda.
				CrearMapaPage.this.setMapa(new Mapa(form.getModelObject()));
				this.setResponsePage(new MapaPage(CrearMapaPage.this));
			}
		});

		this.addButtonCancel(form);
	}
	
	
	private CompoundPropertyModel<Punto<Integer>> createModel() {
		return new CompoundPropertyModel<Punto<Integer>>(new Punto<Integer>(0,0));
	}


}


