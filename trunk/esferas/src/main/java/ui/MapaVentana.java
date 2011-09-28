package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;

import utils.Punto;
import dominio.Casillero;
import dominio.Esfera;
import dominio.Mapa;
import dominio.Personaje;
import dominio.Posicionable;

@SuppressWarnings("serial")
public class MapaVentana extends MainWindow<Mapa> {

	static final String REALIZAR_BUSQUEDA = "realizarBusqueda";
	static final String ACTUALIZAR_GRID = "actualizarGrid";
	static final String CREAR_ESFERA = "crearEsfera";
	static final String CREAR_PERSONAJE = "crearPersonaje";

	public MapaVentana() {

		super(new Mapa(new Punto<Integer>(5, 8)));
	}

	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		this.botoneraCreacion(new Panel(mainPanel));

		Table<Casillero> table = new Table<Casillero>(mainPanel, Casillero.class);
		table.bindContentsToProperty(Mapa.CASILLEROS);
		this.describeResultsGrid(table);

		Button busquedaButton = new Button(mainPanel);
		busquedaButton.setCaption("¿ Atrapame si puedes ?");
		busquedaButton.onClick(new MessageSend(this, REALIZAR_BUSQUEDA));

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
		crearEsferaWindow.onAccept(new MessageSend(this, ACTUALIZAR_GRID));
		this.actualizarGrid();
	}

	public void realizarBusqueda() {
		Dialog<?> busquedaWindow = new BusquedaVentana(this, this.getModel());
		busquedaWindow.open();
		busquedaWindow.onAccept(new MessageSend(this, ACTUALIZAR_GRID));
		this.actualizarGrid();
	}

	public void crearPersonaje() {
		Dialog<?> crearPersonajeWindow = new PersonajeVentana(this);
		crearPersonajeWindow.open();
		crearPersonajeWindow.onAccept(new MessageSend(this, ACTUALIZAR_GRID));
		this.actualizarGrid();
	}

	public void actualizarGrid() {
		imprimirTablero();
		imprimirMapa();

		System.out.println("Esferas: \t" + this.getModel().getListaEsferas());
		System.out.println("Personajes: \t" + this.getModel().getListaPersonajes());
	}

	/**
	 * Imprimo los elementos que contiene el mapa
	 */
	private void imprimirMapa() {
		System.out.println("Mapa: \t{");
		for (Casillero casillero : this.getModel().getCasilleros()) {

			System.out.println("\t [ " + casillero.getPosicion().toString() + " ; "
					+ casillero.getObjeto().toString() + " ] ");
		}
		System.out.println("\t}");

	}

	/**
	 * Imprimo el tablero como una matriz dando detalles de su contenido.
	 */
	private void imprimirTablero() {
		Punto<Integer> dimension = this.getModel().getDimension();

		for (int i = 0; i < dimension.getX(); i++) {
			System.out.print((i + 1) + ":\t[ ");
			for (int j = 0; j < dimension.getY(); j++) {
				System.out.print(" (" + objetoDelCasillero(new Punto<Integer>(i, j)) + ") ");
			}
			System.out.print(" ]\n");
		}
		System.out.print("\n");
	}

	private String objetoDelCasillero(Punto<Integer> posicion) {
		String objetoDelCasillero;

		if (this.getModel().hayObjetoEn(posicion)) {
			Posicionable objeto = this.getModel().buscarObjeto(posicion);
			if (objeto.esEsfera())
				objetoDelCasillero = "E." + ((Esfera) objeto).getNumero().getCantidadEstrellas();
			else
				objetoDelCasillero = "P." + ((Personaje) objeto).toString().charAt(0);
		} else {
			objetoDelCasillero = "   ";
		}

		return objetoDelCasillero;
	}

	public static void main(String[] args) {
		new MapaVentana().startApplication();
	}

}
