package controllers;

import org.zkoss.xel.XelException;

import domain.formulario.ListaPersonas;
import domain.formulario.Persona;

public  class PersonaResolver implements org.zkoss.xel.VariableResolver{

	
	public Object resolveVariable(String name) {
		
		ListaPersonas lp = new ListaPersonas();
		return "personas".equals(name) ? ListaPersonas.getListaPersonas() : null;
	}

}
