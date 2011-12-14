package ar.edu.unq.tpi.esferas_wicket;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import utils.Punto;
import dominio.Casillero;
import dominio.Esfera;
import dominio.Mapa;
import dominio.Personaje;

public class MapaPage extends TemplatePage<Mapa> {

	public static final String SHENG_LONG_PANEL = "shengLongPanel";
	public static final String TABLERO = "tablero";
	public static final String LLAMAR = "llamar";
	public static final String ATRAPAR = "atrapar";
	
	public MapaPage() {
		initMapa();
	}

	public MapaPage(TemplatePage<?> paginaOrigen) {
		super(paginaOrigen);
		initMapa();
	}
	
	protected void initMapa() {
		this.initPage(new Form<Mapa>("mapaForm",new CompoundPropertyModel<Mapa>(this.getMapa())));
		this.getForm().setOutputMarkupId(true);
	}

	protected void addFields() {
		this.add(new Label(Mapa.DIMENSION, new PropertyModel<Punto<Integer>>(this.getMapa(), Mapa.DIMENSION)));
		this.add(shengLongPanel());
		
		this.getForm().add(createDropDownChoicePersonaje());
		this.getForm().add(createDropDownChoiceEsfera());
		this.getForm().add(detalleTablero());
	}

	protected void addButtons() {
		this.addButtonsMapa();
		this.addButtonsBusqueda();
	}
	
	/**
	 * Crea y agrega los botones para la Creacione de Esferas y Personajes
	 */
	private void addButtonsMapa() {
		this.getForm().add(new Button("crearEsfera") {
			
			@Override
			public boolean isEnabled() {
				return (!MapaPage.this.getMapa().getListaEsferasNoCreadas().isEmpty());
			}
			
			@Override
			public void onSubmit() {
				this.setResponsePage(new CrearEsferaPage(MapaPage.this));
			}
		});
		
		this.getForm().add(new Button("crearPersonaje") {
			
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
	 * Crea y agrega los botones para la Busquedas de los Personajes (Esferas o ShengLong)
	 */
	@SuppressWarnings("rawtypes")
	private void addButtonsBusqueda() {
		Button buttonAtrapar = new Button(ATRAPAR) {
			
			@Override
			public boolean isEnabled() {
				Mapa mapa = MapaPage.this.getMapa();
				DropDownChoice<?> dropDownChoiceEsfera = (DropDownChoice<?>) MapaPage.this.getForm().get(Mapa.ESFERA_BUSCADA);
				DropDownChoice<?> dropDownChoicePersonaje = (DropDownChoice<?>) MapaPage.this.getForm().get(Mapa.PERSONAJE_BUSCADO);
				return (mapa.getPersonajeBuscado() != null) && (mapa.getEsferaBuscada() != null) && (!dropDownChoiceEsfera.getChoices().isEmpty())  && (!dropDownChoicePersonaje.getChoices().isEmpty());
			}
			
			@Override
			public void onSubmit() {
				MapaPage.this.getMapa().personajeCapturaEsfera();
				this.setResponsePage(MapaPage.this.getClass());
			}
		};
		buttonAtrapar.setOutputMarkupId(true);
		this.getForm().add(buttonAtrapar);
		
		AjaxLink buttonLlamar = new AjaxLink(LLAMAR) {
			
			@Override
			public boolean isEnabled() {
				Personaje personajeBuscado = MapaPage.this.getMapa().getPersonajeBuscado();
				DropDownChoice<?> dropDownChoicePersonaje = (DropDownChoice<?>) MapaPage.this.getForm().get(Mapa.PERSONAJE_BUSCADO);
				return (personajeBuscado != null) && (personajeBuscado.puedeInvocarShengLong()) && (!dropDownChoicePersonaje.getChoices().isEmpty());
			}
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				if(MapaPage.this.getMapa().getPersonajeBuscado().puedeInvocarShengLong()){
					MapaPage.this.getMapa().reubicarEsferasDelPersonaje(MapaPage.this.getMapa().getPersonajeBuscado());
					((ModalWindow) MapaPage.this.get(SHENG_LONG_PANEL)).show(target);
				}
			}

		};
		buttonLlamar.setOutputMarkupId(true);
		this.getForm().add(buttonLlamar);
	}
	
	private ListView<List<Casillero>> detalleTablero() {
		ListView<List<Casillero>> listView = new ListView<List<Casillero>>(TABLERO, this.getMapa().getTablero()) {
			
			@Override 
			protected void populateItem(ListItem<List<Casillero>> itemFila) {
				itemFila.add(new Label("numeroFila", String.valueOf(itemFila.getIndex())));
				ListView<Casillero> fila = new ListView<Casillero>("columnas", itemFila.getModelObject()) {
					
					@Override
					protected void populateItem(ListItem<Casillero> item) {
						item.add(new Image("imagenCasillero", "images/"+item.getModelObject().getObjeto().getName().toLowerCase()+".png"));
					}
				};
				itemFila.add(fila);
			}

		};
		listView.setOutputMarkupId(true);
		return listView;
	}
	
	protected DropDownChoice<Personaje> createDropDownChoicePersonaje() {
		
		DropDownChoice<Personaje> dropDownChoicePersonaje = new DropDownChoice<Personaje>(Mapa.PERSONAJE_BUSCADO, this.getMapa().getListaPersonajes(), new PosicionableChoiceRenderer<Personaje>());
		dropDownChoicePersonaje.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(MapaPage.this.getForm().get(ATRAPAR));
				target.add(MapaPage.this.getForm().get(LLAMAR));
			}
		});
		return dropDownChoicePersonaje;
	}
	
	protected DropDownChoice<Esfera> createDropDownChoiceEsfera() {
		
		DropDownChoice<Esfera> dropDownChoiceEsfera = new DropDownChoice<Esfera>(Mapa.ESFERA_BUSCADA, this.getMapa().getListaEsferas(), new PosicionableChoiceRenderer<Esfera>());

		dropDownChoiceEsfera.add(new AjaxFormComponentUpdatingBehavior("onchange") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(MapaPage.this.getForm().get(ATRAPAR));
			}
		});
		dropDownChoiceEsfera.setOutputMarkupId(true);
		return dropDownChoiceEsfera;
	}
	
	protected ModalWindow shengLongPanel() {
		
		ModalWindow modal = new ModalWindow(SHENG_LONG_PANEL);
		modal.setContent(new ShengLongPanel("content"));
		modal.setInitialWidth(300);
		modal.setInitialHeight(450);
		
		modal.setWindowClosedCallback(new WindowClosedCallback() {
			
			public void onClose(AjaxRequestTarget target) {
				target.add(MapaPage.this.getForm());
			}
		});
		
		return modal;
	}

}
