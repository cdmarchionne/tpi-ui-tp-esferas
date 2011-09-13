package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;

import utils.Punto;
import dominio.Casillero;
import dominio.Mapa;

@SuppressWarnings("serial")
public class MapaVentana extends MainWindow<Mapa> {

	public MapaVentana() {
		super(new Mapa(new Punto<Integer>(5, 8)));
	}

	@Override
	public void createContents(Panel mainPanel) {
		// ESTO DE ENTITY TPE NO M CIERRA!
		// Table<Casillero> table = new Table<Casillero>(mainPanel,
		// this.getModel().getEntityType());
		// O SEA HABRIA QUE VER COMO MIERDA ES ESO DE PASARLE EL MODELO O DE
		// DONDE CARAJO SACARLO.
		// table.bindContentsToProperty(Mapa.CASILLEROS); // bindeo los
		// casilleros del mapa con la tabla

		// this.describeResultsGrid(table);

		mainPanel.setLayout(new VerticalLayout());

		Button crearEsfera = new Button(mainPanel);
		crearEsfera.setCaption("Crear Esfera");
		crearEsfera.onClick(new MessageSend(this, "crearEsfera"));
	}

	public void describeResultsGrid(Table<Casillero> table) {
		Column<Casillero> posicionColumn = new Column<Casillero>(table);
		posicionColumn.setTitle("Posicion");
		posicionColumn.setPreferredSize(100);
		posicionColumn.bindContentsToProperty(Casillero.POSICION);

		Column<Casillero> objetoColumn = new Column<Casillero>(table);
		objetoColumn.setTitle("Contenido");
		objetoColumn.setPreferredSize(100);
		objetoColumn.bindContentsToProperty(Casillero.OBJETO);
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

	public void crearEsfera() {
		Dialog<?> crearEsferaWindow = new EsferaVentana(this);
		crearEsferaWindow.open();
	}
}
