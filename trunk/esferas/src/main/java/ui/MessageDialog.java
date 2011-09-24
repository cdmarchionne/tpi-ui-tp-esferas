package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

public class MessageDialog extends Dialog {

	private String titulo;
	private String mensaje;

	public MessageDialog(WindowOwner owner, String titulo, String mensaje) {
		super(owner, null);
		this.titulo = titulo;
		this.mensaje = mensaje;
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		Button okBoton = new Button(actionsPanel).setCaption("OK");
		okBoton.onClick(new MessageSend(this, CANCEL));
		okBoton.setAsDefault();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle(this.titulo);

		mainPanel.setLayout(new VerticalLayout());

		Label mensaje = new Label(mainPanel);
		mensaje.setText(this.mensaje);
	}

	@Override
	protected void executeTask() {
	}

}
