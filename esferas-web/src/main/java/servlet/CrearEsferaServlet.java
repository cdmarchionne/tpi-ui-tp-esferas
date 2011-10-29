package servlet;

import dominio.Esfera;
import dominio.Esfera.CantidadEstrellas;
import dominio.Mapa;

@SuppressWarnings("serial")
public class CrearEsferaServlet extends CrearPosicionableServlet {

	@Override
	protected Esfera crearPosicionable(Mapa mapa,Integer index) {
		CantidadEstrellas esfera = mapa.getListaEsferasNoCreadas().get(index);
		return new Esfera(esfera);
	}
	
}
