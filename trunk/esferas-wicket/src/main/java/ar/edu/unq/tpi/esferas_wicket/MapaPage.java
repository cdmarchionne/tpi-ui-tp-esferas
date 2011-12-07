package ar.edu.unq.tpi.esferas_wicket;

import java.util.List;

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

		this.add(new DropDownChoice<Personaje>(Mapa.PERSONAJE_BUSCADO, this.getMapa().getListaPersonajes(), new PosicionableChoiceRenderer<Personaje>()));
		this.add(new DropDownChoice<Esfera>(Mapa.ESFERA_BUSCADA, this.getMapa().getListaEsferas(), new PosicionableChoiceRenderer<Esfera>()));
		
		Form<Mapa> formBusqueda = new Form<Mapa>("busquedaForm",cpm);
		this.add(formBusqueda);
		this.addFeedbackPanel(formBusqueda);
		this.addButtonsBusqueda(formBusqueda);
		
		this.add(detallePersonaje());
		this.add(tablaTablero());
	}

	/**
	 * Genera una Tabla con los detalles de los personajes
	 * @return
	 */
	private ListView<Personaje> detallePersonaje() {
		return new ListView<Personaje>("personajes", this.getMapa().getListaPersonajes()) {
			private static final long serialVersionUID = 1L;
			// This method is called for each 'entry' in the list.
			@Override 
			protected void populateItem(ListItem<Personaje> item) {
				Personaje personaje = (Personaje) item.getModelObject();
				item.add(new Image("imagenPersonaje", new Model("images/"+personaje.getName().toLowerCase()+".png")));
				item.add(new Label(Personaje.NOMBRE, personaje.getName()));
				item.add(new Label("posicion", personaje.getPosicion().toString()));
				item.add(new Label(Personaje.DISTANCIA, personaje.getDistancia().toString()));
				item.add(new Label("cantidad", String.valueOf(personaje.getInventario().size())));
				
				ListView<Esfera> inventario = new ListView<Esfera>(Personaje.INVENTARIO, personaje.getInventario()) {
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void populateItem(ListItem<Esfera> itemEsfera) {
						itemEsfera.add(new Image("inventarioEsfera", new Model("images/"+itemEsfera.getModelObject().getName().toLowerCase()+".png")));
					}
				};
				item.add(inventario);
			}		
		};
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
//				ListView<Casillero> titulo = new ListView<Casillero>("columnas", itemFila.getModelObject()) {
//					private static final long serialVersionUID = 1L;
//					
//					@Override
//					protected void populateItem(ListItem<Casillero> item) {
//						item.add(new Label("numeroColumna", String.valueOf(item.getIndex())));
//					}
//				};
//				itemFila.add(titulo);
				
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
			public void onSubmit() {
//				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
		
		form.add(new Button("llamar") {
		private static final long serialVersionUID = 1L;

//		@Override
//		public boolean isEnabled() {
//			return (MapaPage.this.getMapa().getPersonajeBuscado() != null);
//		}
//		
		@Override
		public void onSubmit() {
//			this.setResponsePage(new CrearPersonajePage(MapaPage.this));
		}
		});
	}

}
