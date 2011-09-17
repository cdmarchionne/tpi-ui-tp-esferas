package ui;

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

	static final String ACTUALIZAR_GRID = "actualizarGrid";
	static final String CREAR_ESFERA = "crearEsfera";
	static final String CREAR_PERSONAJE = "crearPersonaje";
	static final String BUSCAR_ESTRELLA = "llegaAlaEstrella";

	public MapaVentana() {

		super(new Mapa(new Punto<Integer>(5, 8)));
	}

	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		this.botoneraCreacion(new Panel(mainPanel));

		Table<Casillero> table = new Table<Casillero>(mainPanel, Casillero.class);
		table.bindContentsToProperty(Mapa.CASILLAS);
		this.describeResultsGrid(table);

		this.botoneraBusqueda(new Panel(mainPanel));

	}

	private void botoneraBusqueda(Panel busquedaPanel) {
		busquedaPanel.setLayout(new ColumnLayout(3));

		new Selector(busquedaPanel).setContents(this.getModel().getListaEsferas(), "numero")
				.bindValueToProperty(this.getModel().ESFERA_BUSCADA);

		new Selector(busquedaPanel).setContents(this.getModel().getListaPersonajes(), "nombre")
				.bindValueToProperty(this.getModel().PERSONAJE_BUSCADO);

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
		crearEsferaWindow.onAccept(new MessageSend(this, this.ACTUALIZAR_GRID));
	}

	public void crearPersonaje() {
		Dialog<?> crearPersonajeWindow = new PersonajeVentana(this);
		crearPersonajeWindow.open();
		crearPersonajeWindow.onAccept(new MessageSend(this, this.ACTUALIZAR_GRID));
	}

	public void actualizarGrid() {
		System.out.println("Se creo la Esfera." + this.getModel().getCasillas().toString());

	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

}
