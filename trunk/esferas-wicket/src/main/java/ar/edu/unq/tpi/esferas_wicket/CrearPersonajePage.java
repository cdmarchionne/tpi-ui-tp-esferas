package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import dominio.Esfera;
import dominio.Personaje;

public class CrearPersonajePage extends PosicionablePage<Personaje> {
	private static final String NAME_FORM = "personajeForm";
	
	public CrearPersonajePage(TemplatePage<?> paginaOrigen) {
		super(paginaOrigen, new Form<Personaje>(NAME_FORM, new CompoundPropertyModel<Personaje>(new Personaje())));
		this.add(detallePersonaje());
	}

	protected void addPosicionableFields() {
		this.getForm().add(new DropDownChoice<Personaje.NombrePersonaje>(Personaje.NOMBRE, this.getMapa().getListaPersonajesNoCreadas()));
		this.getForm().add(this.createRequiredIntegerField(Personaje.DISTANCIA));
	}

	private ListView<Personaje> detallePersonaje() {
		return new ListView<Personaje>("personajes", this.getMapa().getListaPersonajes()) {
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
					
					@Override
					protected void populateItem(ListItem<Esfera> itemEsfera) {
						itemEsfera.add(new Image("inventarioEsfera", new Model("images/"+itemEsfera.getModelObject().getName().toLowerCase()+".png")));
					}
				};
				item.add(inventario);
			}		
		};
	}
	
}