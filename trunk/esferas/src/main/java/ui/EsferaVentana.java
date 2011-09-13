package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Esfera;

@SuppressWarnings("serial")
public class EsferaVentana extends Dialog<Esfera> {

	public EsferaVentana(WindowOwner owner) {
		super(owner, new Esfera());
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

	@Override
	protected void executeTask() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button crear = new Button(actionsPanel);
		crear.setCaption("Crear");
		// convertir.onClick(new MessageSend(this.getModel(),
		// Esfera.CONVERTIR));

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		Panel esferaPanel = new Panel(mainPanel);
		// esferaPanel.bindContents(Esfera.ESFERA);
		esferaPanel.setLayout(new ColumnLayout(2));

		Label numeroLabel = new Label(esferaPanel);
		numeroLabel.setText("Numero:");

		Control numero = new TextBox(esferaPanel);
		numero.bindValueToProperty(Esfera.NUMERO);

		Panel posicionPanel = new Panel(mainPanel);
		// posicionPanel.bindContents(Punto.PUNTO);
		posicionPanel.setLayout(new ColumnLayout(3));

		Label posicionLabel = new Label(posicionPanel);
		posicionLabel.setText("Posicion:");

		Control posicionX = new TextBox(posicionPanel);
		posicionX.bindValueToProperty("x");

		Control posicionY = new TextBox(posicionPanel);
		posicionY.bindValueToProperty("y");
	}
}
