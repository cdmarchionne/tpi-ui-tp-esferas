package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Esfera;

public class EsferaVentana extends CasilleroVentana {

	public EsferaVentana(WindowOwner owner) {
		super(owner, new Esfera());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		super.createFormPanel(mainPanel);

		panelDeEsfera(new Panel(mainPanel));
	}

	private void panelDeEsfera(Panel esferaPanel) {
		esferaPanel.setLayout(new ColumnLayout(2));

		Label numeroLabel = new Label(esferaPanel);
		numeroLabel.setText("Numero:");

		new Selector(esferaPanel).setContents(getMapa().listaEsferasNoCreadas(),
				Esfera.CANTIDAD_ESTRELLAS).bindValueToProperty(Esfera.NUMERO);

		// Control numero = new TextBox(esferaPanel);
		// numero.bindValueToProperty(Esfera.NUMERO);
	}

}
