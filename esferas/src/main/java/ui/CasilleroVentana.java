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

import dominio.Mapa;
import dominio.Posicionable;

public class CasilleroVentana extends Dialog<Posicionable> {

	public CasilleroVentana(WindowOwner owner, Posicionable model) {
		super(owner, model);
	}

	@Override
	protected void executeTask() {
		getMapa().addCasillero(this.getModel().getCasillero());
	}

	protected Mapa getMapa() {
		return (Mapa) ((Window<?>) (this.getOwner())).getModel();
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

		panelDePosicion(new Panel(mainPanel));

	}

	private void panelDePosicion(Panel posicionPanel) {
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
