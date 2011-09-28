package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import dominio.Mapa;

public class BusquedaVentana extends Dialog<Mapa> {

	static final String CAPTURA_ESFERA = "capturaEsfera";
	static final String PUEDE_LLEGAR_PERSONAJE_A_ESFERA = "puedeLLegar";
	static final String LLAMAR_SHENG_LONG = "llamarShenLong";

	public BusquedaVentana(WindowOwner owner, Mapa model) {
		super(owner, model);
	}

	@Override
	protected void executeTask() {
	}

	@Override
	protected void addActions(Panel actionsPanel) {
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());

		this.botoneraBusqueda(new Panel(mainPanel));
	}

	private void botoneraBusqueda(Panel busquedaPanel) {
		busquedaPanel.setLayout(new ColumnLayout(2));

		new Selector(busquedaPanel).setContents(this.getModel().getListaEsferas(), "numero")
				.bindValueToProperty(this.getModel().ESFERA_BUSCADA);

		new Selector(busquedaPanel).setContents(this.getModel().getListaPersonajes(), "nombre")
				.bindValueToProperty(this.getModel().PERSONAJE_BUSCADO);

		Button buscarEsfera = new Button(busquedaPanel).setCaption("Llega¿?").onClick(
				new MessageSend(this, PUEDE_LLEGAR_PERSONAJE_A_ESFERA));

		Button capturarEsfera = new Button(busquedaPanel).setCaption("Capturar")
				.onClick(new MessageSend(this, CAPTURA_ESFERA)).setAsDefault();

		Button llamarShengLong = new Button(busquedaPanel);
		llamarShengLong.setCaption("Llamar a ShenLong");
		llamarShengLong.onClick(new MessageSend(this, LLAMAR_SHENG_LONG));

	}

	public void puedeLLegar() {
		String accion;

		if (this.getModel().puedeCapturarEsfera()) {
			accion = "llega";
		} else {
			accion = "no llega";
		}

		String mensaje = "El personaje " + this.getModel().getPersonajeBuscado().getNombre() + " "
				+ accion + " a capturar la " + this.getModel().getEsferaBuscada().toString();

		new MessageDialog(this.getOwner(), "Alcanza Esfera?", mensaje).open();
	}

	public void capturaEsfera() {
		String accion;

		if (this.getModel().personajeCapturaEsfera()) {
			accion = "capturo";
		} else {
			accion = "no puede capturar";
		}

		String mensaje = "El personaje " + this.getModel().getPersonajeBuscado().getNombre() + " "
				+ accion + " la " + this.getModel().getEsferaBuscada().toString();

		new MessageDialog(this.getOwner(), "Captura de Esfera", mensaje).open();
		this.close();
	}

	public void llamarShenLong() {
		String accion;
		Boolean valor = this.getModel().getPersonajeBuscado().puedeInvocarShengLong();

		if (valor) {
			accion = "puede";
		} else {
			accion = "no puede";
		}
		String mensaje = "El personaje " + this.getModel().getPersonajeBuscado().getNombre() + " "
				+ accion + " llamar a Sheng Long";

		new MessageDialog(this.getOwner(), "Llamar a ShenLong", mensaje).open();
		this.close();
	}

}
