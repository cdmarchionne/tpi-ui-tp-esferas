package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Mapa;
import dominio.Personaje;

@SuppressWarnings("serial")
public class PersonajeVentana extends Dialog<Personaje> {

	public PersonajeVentana(WindowOwner owner) {
		super(owner, new Personaje());
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

	@Override
	protected void executeTask() {
		// TODO: Aca deberiamos crear el personaje y agregarlo al Mapa
		Mapa mapa = (Mapa) ((Window<?>) (this.getOwner())).getModel();
		mapa.addCasillero(this.getModel().getCasillero());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button crear = new Button(actionsPanel);
		crear.setCaption("Crear");
		// crear.onClick(new MessageSend(this.getModel(), Personaje.CREAR));

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		Panel posicionPanel = new Panel(mainPanel);
		posicionPanel.setLayout(new ColumnLayout(3));

		Label posicionLabel = new Label(posicionPanel);
		posicionLabel.setText("Posicion:");

		Control posicionX = new TextBox(posicionPanel);
		posicionX.bindValueToProperty("x");

		Control posicionY = new TextBox(posicionPanel);
		posicionY.bindValueToProperty("y");

		Panel personajePanel = new Panel(mainPanel);
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
