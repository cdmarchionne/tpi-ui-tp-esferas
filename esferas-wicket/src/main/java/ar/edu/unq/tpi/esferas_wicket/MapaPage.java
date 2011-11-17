package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

import utils.Punto;
import dominio.Esfera;
import dominio.Mapa;
import dominio.Personaje;

public class MapaPage extends EsferaPage {

	private static final long serialVersionUID = 1L;
	
	public MapaPage() {
		initMapa();
	}

	protected void initMapa() {
		this.add(new Label("dim", new PropertyModel<Punto<Integer>>(this.getMapa(), Mapa.DIMENSION)));
		
		this.add(new DropDownChoice<Esfera>("esferas", this.getMapa().getListaEsferas(), new PosicionableChoiceRenderer<Esfera>()));
		this.add(new DropDownChoice<Personaje>("personajes", this.getMapa().getListaPersonajes(), new PosicionableChoiceRenderer<Personaje>()));
		
		this.add(new DropDownChoice<Esfera.CantidadEstrellas>("sinEsferas", this.getMapa().getListaEsferasNoCreadas()));
		this.add(new DropDownChoice<Personaje.NombrePersonaje>("sinPersonajes", this.getMapa().getListaPersonajesNoCreadas()));
		
		Form form = new Form("mapaForm");
		this.add(form);
		this.addFeedbackPanel(form);
		this.addButtons(form);
	}
	
	public MapaPage(EsferaPage paginaOrigen) {
		super(paginaOrigen);
		initMapa();
    }

	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons(final Form form) {
			form.add(new Button("crearEsfera") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
	}

}
