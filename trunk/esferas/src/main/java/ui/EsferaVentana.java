package ui;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;

import dominio.Casillero;
import dominio.Esfera;

public class CasilleroVentana extends MainWindow<Casillero> {

	public CasilleroVentana() {
		super(new Casillero());
	}

	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		Button convertir = new Button(mainPanel);
		convertir.setCaption("Crear");
		// convertir.onClick(new MessageSend(this.getModel(),
		// Esfera.CONVERTIR));

		Label numero = new Label(mainPanel);
		numero.bindValueToProperty(Esfera.NUMERO);

		Label posicion = new Label(mainPanel);
		// posicion.bindValueToProperty(Punto.x);
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

}
