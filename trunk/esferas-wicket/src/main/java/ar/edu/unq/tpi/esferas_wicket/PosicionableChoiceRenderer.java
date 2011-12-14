package ar.edu.unq.tpi.esferas_wicket;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import dominio.Posicionable;

public class PosicionableChoiceRenderer<T extends Posicionable> implements IChoiceRenderer<T>,Serializable {

	// Gets the display value that is visible to the end user.
	public String getDisplayValue(T posicionable) {
		return posicionable.getName();
	}

	// Gets the value that is invisble to the end user, and that is used as the selection id.
	public String getIdValue(T posicionable, int index) {
		return ((Integer) index).toString();
	}
}
