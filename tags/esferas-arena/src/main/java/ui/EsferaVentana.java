package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Esfera;

@SuppressWarnings("serial")
public class EsferaVentana extends CasilleroVentana {

	public EsferaVentana(WindowOwner owner) {
		super(owner, new Esfera());
	}

	@Override
	protected void executeTask() {
		super.executeTask();
		this.getMapa().actualizarFaltanCrearEsferas();
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

		Selector selectorEsfera = new Selector(esferaPanel);
		selectorEsfera.setContents(getMapa().getListaEsferasNoCreadas(), Esfera.CANTIDAD_ESTRELLAS);
		selectorEsfera.bindValueToProperty(Esfera.NUMERO);
		// selectorEsfera
		// .bindEnabled(new ObservableProperty(this.getMapa(),
		// Mapa.FALTAN_CREAR_ESFERAS));
	}

}
