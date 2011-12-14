package ar.edu.unq.tpi.esferas_wicket;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

public class ShengLongPanel extends Panel {

	public ShengLongPanel(String id) {
		super(id);
		this.add(new Image("shengLong", "images/shenglong.png"));
	}
	
}