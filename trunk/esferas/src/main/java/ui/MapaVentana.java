package ui;

import java.util.Arrays;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;

import utils.Punto;
import dominio.Casillero;
import dominio.Mapa;

@SuppressWarnings("serial")
public class MapaVentana extends MainWindow<Mapa> {

	static final String CREAR_ESFERA = "crearEsfera";
	static final String CREAR_PERSONAJE = "crearPersonaje";
	static final String BUSCAR_ESTRELLA = "llegaAlaEstrella";

	public MapaVentana() {

		super(new Mapa(new Punto<Integer>(5, 8)));
	}

	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		botoneraCreacion(new Panel(mainPanel));

		Table<Casillero> table = new Table<Casillero>(mainPanel, Casillero.class);
		table.bindContentsToProperty(Mapa.CASILLAS);
		this.describeResultsGrid(table);

		botoneraBusqueda(new Panel(mainPanel));

	}

	private void botoneraBusqueda(Panel busquedaPanel) {
		busquedaPanel.setLayout(new ColumnLayout(3));

		// new
		// Selector(creacionPanel).setContents(Arrays.asList(Esfera.CantidadEstrellas.values()),
		// "cantidadEstrellas");//.bindValueToProperty("estado");
		new Selector(busquedaPanel)
				.setContents(Arrays.asList(this.getModel().listaEsferas()), null);// .bindValueToProperty("estado");

		// new
		// Selector(creacionPanel).setContents(Arrays.asList(Personaje.NombrePersonaje.values()),
		// "nombrePersonaje");
		new Selector(busquedaPanel).setContents(Arrays.asList(this.getModel().listaPersonajes()),
				null);

		Button buscarEsfera = new Button(busquedaPanel);
		buscarEsfera.setCaption("Llega¿?");
		buscarEsfera.onClick(new MessageSend(this.getModel(), Mapa.PUEDE_CAPTURAR));
	}

	private void botoneraCreacion(Panel creacionPanel) {
		creacionPanel.setLayout(new ColumnLayout(2));

		Button crearEsfera = new Button(creacionPanel);
		crearEsfera.setCaption("Crear Esfera");
		crearEsfera.onClick(new MessageSend(this, CREAR_ESFERA));

		Button crearPersonaje = new Button(creacionPanel);
		crearPersonaje.setCaption("Crear Personaje");
		crearPersonaje.onClick(new MessageSend(this, CREAR_PERSONAJE));
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

	public void crearEsfera() {
		Dialog<?> crearEsferaWindow = new EsferaVentana(this);
		crearEsferaWindow.open();
		// crearEsferaWindow.onAccept(new MessageSend(this, "actualizarGrid"));
	}

	public void crearPersonaje() {
		Dialog<?> crearPersonajeWindow = new PersonajeVentana(this);
		crearPersonajeWindow.open();

	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

}
