package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import utils.Punto;
import dominio.Esfera;
import dominio.Mapa;
import dominio.Personaje;

public class MapaPage extends PosicionablePage {

	private static final long serialVersionUID = 1L;
	
	public MapaPage() {
		initMapa();
	}

	protected void initMapa() {
		CompoundPropertyModel<Mapa> cpm = new CompoundPropertyModel<Mapa>(this.getMapa());
		
		this.add(new Label(Mapa.DIMENSION, new PropertyModel<Punto<Integer>>(this.getMapa(), Mapa.DIMENSION)));
		
		Form<Mapa> formMapa = new Form<Mapa>("mapaForm",cpm);
		this.add(formMapa);
		this.addFeedbackPanel(formMapa);
		this.addButtonsMapa(formMapa);

		this.add(new DropDownChoice<Personaje>(Mapa.PERSONAJE_BUSCADO, this.getMapa().getListaPersonajes(), new PosicionableChoiceRenderer<Personaje>()));
		this.add(new DropDownChoice<Esfera>(Mapa.ESFERA_BUSCADA, this.getMapa().getListaEsferas(), new PosicionableChoiceRenderer<Esfera>()));
		
		Form<Mapa> formBusqueda = new Form<Mapa>("busquedaForm",cpm);
		this.add(formBusqueda);
		this.addFeedbackPanel(formBusqueda);
		this.addButtonsBusqueda(formBusqueda);
	}
	
	public MapaPage(PosicionablePage paginaOrigen) {
		super(paginaOrigen);
		initMapa();
    }

	/**
	 * Crea y agrega los botones para las Creaciones de Esferas y Personajes
	 */
	private void addButtonsMapa(final Form<Mapa> form) {
		form.add(new Button("crearEsfera") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
		
		form.add(new Button("crearPersonaje") {
		private static final long serialVersionUID = 1L;

		@Override
		public void onSubmit() {
			this.setResponsePage(new CrearPersonajePage(MapaPage.this));
		}
		});
	}

	/**
	 * Crea y agrega los botones para las Creaciones de Esferas y Personajes
	 */
	private void addButtonsBusqueda(final Form<Mapa> form) {
		form.add(new Button("atrapar") {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onSubmit() {
//				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
		
		form.add(new Button("llamar") {
		private static final long serialVersionUID = 1L;

		@Override
		public void onSubmit() {
//			this.setResponsePage(new CrearPersonajePage(MapaPage.this));
		}
		});
	}

}
