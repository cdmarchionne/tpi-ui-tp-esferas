package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.PropertyModel;

import utils.Punto;
import dominio.Esfera;
import dominio.Mapa;
import dominio.Personaje;

public class MapaPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public MapaPage(final Mapa mapa) {
    	this.add(new Label("dim", new PropertyModel<Punto<Integer>>(mapa, Mapa.DIMENSION)));
    	this.add(new DropDownChoice<Esfera>("esferas", mapa.getListaEsferas(), new IChoiceRenderer<Esfera>() {
	         // Gets the display value that is visible to the end user.
	        public String getDisplayValue(Esfera esfera) {
				return esfera.getName();
	        }

	         // Gets the value that is invisble to the end user, and that is used as the selection id.
	        public String getIdValue(Esfera esfera, int index) {
				return ((Integer) index).toString();
	        }
	    }));
    	
    	this.add(new DropDownChoice<Personaje>("personajes", mapa.getListaPersonajes(), new IChoiceRenderer<Personaje>() {
	         // Gets the display value that is visible to the end user.
	        public String getDisplayValue(Personaje personaje) {
				return personaje.getName();
	        }

	         // Gets the value that is invisble to the end user, and that is used as the selection id.
	        public String getIdValue(Personaje personaje, int index) {
				return ((Integer) index).toString();
	        }
	    }));
    	

    	this.add(new DropDownChoice<Esfera.CantidadEstrellas>("sinEsferas", mapa.getListaEsferasNoCreadas()));
    	this.add(new DropDownChoice<Personaje.NombrePersonaje>("sinPersonajes", mapa.getListaPersonajesNoCreadas()));
    	
    }

//	private void add(DropDownChoice<Esfera> dropDownChoice,
//			IChoiceRenderer<Esfera> iChoiceRenderer) {
//		// TODO Auto-generated method stub
//		
//	}
    
}
