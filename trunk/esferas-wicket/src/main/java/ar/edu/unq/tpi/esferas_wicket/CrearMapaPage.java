package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import utils.Punto;

public class CrearMapaPage extends WebPage {

	private static final long serialVersionUID = -8852388658486519991L;

	public CrearMapaPage() {
		Form form = new Form("nuevoMapaForm", new CompoundPropertyModel(new Punto<Integer>(1, 1)));
		this.add(form);
    	this.addFields(form);
		this.addButtons(form);
	}
    

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields(Form form) {
    	form.add(new TextField<Integer>("x"));
    	form.add(new TextField<Integer>("y"));
		form.add(new FeedbackPanel("feedbackPanel"));
	}

	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons( Form form) {
		form.add(new Button("ok") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// invoca la logica de negocio
//				Mapa mapa = new Mapa(form.getModelObject());
				// navegacion: vuelve a la pagina de busqueda.
//				this.setResponsePage(new MapaPage(mapa));
				System.out.println("Aprete Aceptar");
			}
		});

		form.add(new Button("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
//				this.setResponsePage(CrearMapaPage.class);
				System.out.println("Aprete Cancelar");
			}
		});
	}

}
