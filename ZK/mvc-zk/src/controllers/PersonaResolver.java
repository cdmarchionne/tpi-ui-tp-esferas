package controllers;

import domain.formulario.ListaPersonas;

public class PersonaResolver implements org.zkoss.xel.VariableResolver {

	public Object resolveVariable(String name) {

		ListaPersonas lp = ListaPersonas.getInstance();
		return "personas".equals(name) ? ListaPersonas.getListaPersonas() : null;
	}

}
