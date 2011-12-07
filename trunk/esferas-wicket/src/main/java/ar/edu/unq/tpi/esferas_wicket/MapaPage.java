package ar.edu.unq.tpi.esferas_wicket;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import utils.Punto;
import dominio.Casillero;
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

		formMapa.add(new DropDownChoice<Personaje>(Mapa.PERSONAJE_BUSCADO, this.getMapa().getListaPersonajes(), new PosicionableChoiceRenderer<Personaje>()));
		formMapa.add(new DropDownChoice<Esfera>(Mapa.ESFERA_BUSCADA, this.getMapa().getListaEsferas(), new PosicionableChoiceRenderer<Esfera>()));
		
		this.addButtonsBusqueda(formMapa);
		
		this.add(tablaTablero());
	}

	/**
	 * Genera una Tabla con los detalles de los personajes
	 * @return
	 */
	private ListView<List<Casillero>> tablaTablero() {
		return new ListView<List<Casillero>>("tablero", this.getMapa().getTablero()) {
			private static final long serialVersionUID = 1L;
			
			@Override 
			protected void populateItem(ListItem<List<Casillero>> itemFila) {
				itemFila.add(new Label("numeroFila", String.valueOf(itemFila.getIndex())));
				ListView<Casillero> fila = new ListView<Casillero>("columnas", itemFila.getModelObject()) {
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void populateItem(ListItem<Casillero> item) {
						item.add(new Image("imagenCasillero", new Model("images/"+item.getModelObject().getObjeto().getName().toLowerCase()+".png")));
					}
				};
				itemFila.add(fila);
			}
		};
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
			public boolean isEnabled() {
				return (!MapaPage.this.getMapa().getListaEsferasNoCreadas().isEmpty());
			}
			
			@Override
			public void onSubmit() {
				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
		
		form.add(new Button("crearPersonaje") {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isEnabled() {
			return (!MapaPage.this.getMapa().getListaPersonajesNoCreadas().isEmpty());
		}
		
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
		public boolean isEnabled() {
			return (MapaPage.this.getMapa().getPersonajeBuscado() != null);
		}
			
			@Override
			public void onSubmit() {
				MapaPage.this.getMapa().personajeCapturaEsfera();
			}
		});
		
		form.add(new Button("llamar") {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isEnabled() {
			return (MapaPage.this.getMapa().getPersonajeBuscado() != null);
		}
		
		@Override
		public void onSubmit() {
//			this.setResponsePage(new Page(MapaPage.this.getMapa().getPersonajeBuscado());
			if(MapaPage.this.getMapa().getPersonajeBuscado().puedeInvocarShengLong()){
				MapaPage.this.getMapa().reubicarEsferasDelPersonaje(MapaPage.this.getMapa().getPersonajeBuscado());
			}
		}
		});
	}

}
