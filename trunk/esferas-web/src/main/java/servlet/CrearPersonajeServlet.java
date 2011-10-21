package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Punto;
import dominio.Casillero;
import dominio.Esfera;
import dominio.Esfera.CantidadEstrellas;
import dominio.Mapa;
import dominio.Personaje;
import dominio.Personaje.NombrePersonaje;

@SuppressWarnings("serial")
public class CrearPersonajeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String posicionX = request.getParameter("x");
		String posicionY = request.getParameter("y");
		String personajeIndex = request.getParameter("personaje");
		String distanciaMaxima = request.getParameter("distancia");
		
		if (isCompleted(posicionX) && isCompleted(posicionY)){
			request.setAttribute("mensajeError", "Complete los campos obligatorios");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else{
			try {
				Integer x = Integer.parseInt(posicionX);
				Integer y = Integer.parseInt(posicionY);
				Integer index = Integer.parseInt(personajeIndex);
				Integer distancia = Integer.parseInt(distanciaMaxima);
				Mapa mapa = (Mapa) request.getSession().getAttribute("mapa");
				
				Punto<Integer> posicion = new Punto<Integer>(x,y);
				NombrePersonaje personaje = mapa.getListaPersonajesNoCreadas().get(index);
				Casillero casillero = new Casillero(posicion, new Personaje(personaje,distancia));				
				mapa.addCasillero(casillero);
				
				request.getRequestDispatcher("mapa.jsp").forward(request, response);
				
			} catch (Exception e) {
				request.setAttribute("mensajeError", "Complete los campos con valores numericos");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}

	}

	private boolean isCompleted(String valor){
		return (valor==null) || (valor.isEmpty());
	}
}
