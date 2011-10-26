package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Personaje;

@SuppressWarnings("serial")
public class PersonajeVentana extends CasilleroVentana {

	public PersonajeVentana(WindowOwner owner) {
		super(owner, new Personaje());
	}

	@Override
	protected void executeTask() {
		super.executeTask();
		this.getMapa().actualizarFaltanCrearPersonajes();
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

		new Selector(personajePanel).setContents(getMapa().getListaPersonajesNoCreadas(),
				Personaje.NOMBRE_PERSONAJE).bindValueToProperty(Personaje.NOMBRE);

		Label distanciaLabel = new Label(personajePanel);
		distanciaLabel.setText("Distancia: ");

		Control distancia = new TextBox(personajePanel);
		distancia.bindValueToProperty(Personaje.DISTANCIA);
	}
}
