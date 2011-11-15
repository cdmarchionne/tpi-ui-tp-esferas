package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import utils.Punto;
import dominio.Mapa;

public class CrearMapaPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private Punto<Integer> punto;
	
	public CrearMapaPage() {
		Form<Punto<Integer>> form = new Form<Punto<Integer>>("nuevoMapaForm", this.createModel());
		this.add(form);
    	this.addFields(form);
		this.addButtons(form);

	}

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields(final Form<Punto<Integer>> form) {
		form.add(new RequiredTextField<Integer>(Punto.X,Integer.class));
    	form.add(new RequiredTextField<Integer>(Punto.Y,Integer.class));
    	form.add(new FeedbackPanel("feedbackPanel"));
	}

	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons(final Form<Punto<Integer>> form) {
		form.add(new Button("aceptar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// invoca la logica de negocio
				Mapa mapa = new Mapa(form.getModelObject());
				// navegacion: vuelve a la pagina de busqueda.
//				this.setMapa(mapa);
				this.redirectToInterceptPage(new MapaPage(mapa));
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
	
	
	protected CompoundPropertyModel<Punto<Integer>> createModel() {
		this.punto = new Punto<Integer>(0,0);
		return new CompoundPropertyModel<Punto<Integer>>(this.punto);
	}


}


