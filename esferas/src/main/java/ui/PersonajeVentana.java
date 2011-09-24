package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Personaje;

public class PersonajeVentana extends CasilleroVentana {

	public PersonajeVentana(WindowOwner owner) {
		super(owner, new Personaje());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		super.createFormPanel(mainPanel);

		panelDelPersonaje(new Panel(mainPanel));
	}

	private void panelDelPersonaje(Panel personajePanel) {
		personajePanel.setLayout(new ColumnLayout(2));

		Label nombreLabel = new Label(personajePanel);
		nombreLabel.setText("Nombre: ");

		Control nombre = new TextBox(personajePanel);
		nombre.bindValueToProperty(Personaje.NOMBRE);

		Label distanciaLabel = new Label(personajePanel);
		distanciaLabel.setText("Distancia: ");

		Control distancia = new TextBox(personajePanel);
		distancia.bindValueToProperty(Personaje.DISTANCIA);
	}
}
