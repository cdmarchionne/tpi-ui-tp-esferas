package ui;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

import utils.Punto;
import dominio.Mapa;

@SuppressWarnings("serial")
public class MapaVentana extends MainWindow<Mapa> {

	public MapaVentana() {
		super(new Mapa(new Punto<Integer>(5, 8)));
	}

	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		// Button convertir = new Button(mainPanel);
		// convertir.setCaption("Convertir");
		// convertir.onClick(new MessageSend(this.getModel(),
		// Conversor.CONVERTIR));

		// Label kilometros = new Label(mainPanel);
		// kilometros.bindValueToProperty(Conversor.KILOMETROS);
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

}
