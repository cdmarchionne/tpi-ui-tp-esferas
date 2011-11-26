package controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;

import org.zkoss.zul.Textbox;

import domain.formulario.Persona;

public class FormularioController extends GenericForwardComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Textbox nombre;
	Textbox contrasenia;
	Intbox edad;
	Intbox telefono;
	Datebox cumpleanios;
	Textbox direccion;
	Intbox codigoPostal;
	Textbox email;
	Textbox opinion;
	
	
	
	public void onClick$aceptar(Event event){
		new Persona(nombre.getValue(),contrasenia.getValue(),edad.getValue(),
				telefono.getValue(),cumpleanios.getValue(),direccion.getValue(),
				codigoPostal.getValue(),email.getValue(),opinion.getValue());
		
		this.limpiarCampos();
	}
	
	public void limpiarCampos(){
		this.nombre.setValue("");
		this.contrasenia.setValue("");
		this.edad.setRawValue("");
		this.telefono.setRawValue("");
		this.cumpleanios.setRawValue("");
		this.direccion.setValue("");
		this.codigoPostal.setRawValue("");
		this.email.setRawValue("");
		this.opinion.setRawValue("");
		
		
	}
}
