package ui;

import org.uqbar.arena.actions.MessageSend;
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

import dominio.Esfera;
import dominio.Mapa;

@SuppressWarnings("serial")
public class EsferaVentana extends Dialog<Esfera> {

	public EsferaVentana(WindowOwner owner) {
		super(owner, new Esfera());
	}

	@Override
	protected void executeTask() {
		// TODO: Aca deberiamos crear la esfera y agregarla al Mapa
		Mapa mapa = (Mapa) ((Window<?>) (this.getOwner())).getModel();
		mapa.addCasillero(this.getModel().getCasillero());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button aceptar = new Button(actionsPanel).setCaption("Crear");
		aceptar.onClick(new MessageSend(this, ACCEPT));
		aceptar.setAsDefault();

		Button cancelar = new Button(actionsPanel);
		cancelar.setCaption("Cancelar");
		cancelar.onClick(new MessageSend(this, CANCEL));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		Panel posicionPanel = new Panel(mainPanel);
		// posicionPanel.bindContents(Punto.PUNTO);
		posicionPanel.setLayout(new ColumnLayout(3));

		Label posicionLabel = new Label(posicionPanel);
		posicionLabel.setText("Posicion:");

		Control posicionX = new TextBox(posicionPanel);
		posicionX.bindValueToProperty("x");

		Control posicionY = new TextBox(posicionPanel);
		posicionY.bindValueToProperty("y");

		Panel esferaPanel = new Panel(mainPanel);
		// esferaPanel.bindContents(Esfera.ESFERA);
		esferaPanel.setLayout(new ColumnLayout(2));

		Label numeroLabel = new Label(esferaPanel);
		numeroLabel.setText("Numero:");

		Control numero = new TextBox(esferaPanel);
		numero.bindValueToProperty(Esfera.NUMERO);
	}
}
