package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import dominio.AplicationModelInformacion;

@SuppressWarnings("serial")
public class MessageDialog extends Dialog<AplicationModelInformacion> {

	public MessageDialog(WindowOwner owner, AplicationModelInformacion model) {
		super(owner, model);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button okBoton = new Button(actionsPanel).setCaption("OK");
		okBoton.onClick(new MessageSend(this, CANCEL));
		okBoton.setAsDefault();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle(this.getModel().getTitulo());

		mainPanel.setLayout(new VerticalLayout());

		Label mensaje = new Label(mainPanel);
		mensaje.bindValueToProperty(AplicationModelInformacion.MENSAJE);
	}

	@Override
	protected void executeTask() {
	}

}
