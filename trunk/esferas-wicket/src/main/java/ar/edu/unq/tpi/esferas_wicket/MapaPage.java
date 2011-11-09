package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.uqbar.commons.model.UserException;

import utils.Punto;
import dominio.Mapa;

public class MapaPage extends WebPage {
	private static final long serialVersionUID = 1L;

    public MapaPage(final Mapa mapa) {
    	this.add(new Label("dimX", mapa.getDimension().getX().toString()));
    	this.add(new Label("dimY", mapa.getDimension().getY().toString()));
	}
    
}
