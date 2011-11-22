package ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import dominio.AplicationModelInformacion;
import dominio.Mapa;

@SuppressWarnings("serial")
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
				.bindValueToProperty(Mapa.ESFERA_BUSCADA);

		new Selector(busquedaPanel).setContents(this.getModel().getListaPersonajes(), "nombre")
				.bindValueToProperty(Mapa.PERSONAJE_BUSCADO);

		Button buscarEsfera = new Button(busquedaPanel);
		buscarEsfera.setCaption("Llega¿?");
		buscarEsfera.onClick(new MessageSend(this, PUEDE_LLEGAR_PERSONAJE_A_ESFERA));
		buscarEsfera.bindEnabled(new ObservableProperty(Mapa.HAY_PERSONAJE_Y_ESFERA_BUCADA));

		Button capturarEsfera = new Button(busquedaPanel);
		capturarEsfera.setCaption("Capturar");
		capturarEsfera.onClick(new MessageSend(this, CAPTURA_ESFERA));
		capturarEsfera.setAsDefault();
		capturarEsfera.bindEnabled(new ObservableProperty(Mapa.HAY_PERSONAJE_Y_ESFERA_BUCADA));

		Button llamarShengLong = new Button(busquedaPanel);
		llamarShengLong.setCaption("Llamar a ShenLong");
		llamarShengLong.onClick(new MessageSend(this, LLAMAR_SHENG_LONG));
		llamarShengLong.bindEnabled(new ObservableProperty(Mapa.PUEDE_LLAMAR_A_SHENG_LONG));

	}

	public void puedeLLegar() {
		new MessageDialog(this.getOwner(), new AplicationModelInformacion("Alcanza Esfera?",
				((Mapa) this.getModel()).puedeCapturarEsferaMensaje())).open();
	}

	public void capturaEsfera() {
		new MessageDialog(this.getOwner(), new AplicationModelInformacion("Captura de Esfera",
				((Mapa) this.getModel()).personajeCapturaEsferaMensaje())).open();
		this.close();
	}

	public void llamarShenLong() {
		new MessageDialog(this.getOwner(), new AplicationModelInformacion("Llamar a ShenLong",
				((Mapa) this.getModel()).llamarShenLongMensaje())).open();
		this.close();
	}

}
