package ar.edu.unq.tpi.esferas_wicket;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import utils.Punto;

public class CrearMapaPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private Form<Prueba> form;
	
	public CrearMapaPage() {
		this.setForm(new Form<Prueba>("nuevoMapaForm", new CompoundPropertyModel(new Prueba())));
    	this.addFields();
		this.addButtons();

	}

	/**
	 * Crea y agrega los controles para crear un mapa nuevo.
	 */
	protected void addFields() {
    	this.form.add(new TextField<String>(Prueba.MENSAJE));
    	this.form.add(new FeedbackPanel("feedbackPanel"));
	}

	/**
	 * Crea y agrega los botones: aceptar y cancelar.
	 */
	protected void addButtons() {
		this.form.add(new Button("ok") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				// invoca la logica de negocio
//				Mapa mapa = new Mapa(form.getModelObject());
				// navegacion: vuelve a la pagina de busqueda.
//				this.setResponsePage(new MapaPage(mapa));
//				System.out.println(this.form.getModelObject());
				System.out.println("Aprete Aceptar");
			}
		});

		this.form.add(new Button("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
//				this.setResponsePage(CrearMapaPage.class);
				System.out.println("Aprete Cancelar");
			}
		});
	}
	
	public Form<Prueba> getForm() {
		return form;
	}
	
	
	public void setForm(Form<Prueba> form) {
		this.form = form;
		this.add(form);
	}
	
	//inner class
    private class Prueba implements Serializable{
    	private static final long serialVersionUID = 1L;
    	
		public static final String MENSAJE ="mensaje";
        private String mensaje;
        
        public Prueba() {
        	this("NADA");
        }
        
        public Prueba(String mensaje) {
        	super();
        	this.setMensaje(mensaje);
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		@Override
		public String toString() {
			return this.getMensaje();
		}
    }

}


