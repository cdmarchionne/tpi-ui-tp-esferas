package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Mapa;
import dominio.Personaje;
import dominio.Personaje.NombrePersonaje;

@SuppressWarnings("serial")
public class CrearPersonajeServlet extends CrearPosicionableServlet {

	private Integer distancia;
	
	@Override
	protected Personaje crearPosicionable(Mapa mapa, Integer index) {
		NombrePersonaje personaje = mapa.getListaPersonajesNoCreadas().get(index);
		return new Personaje(personaje,this.getDistancia());				
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String distanciaMaxima = request.getParameter("distancia");
		
		if (isCompleted(distanciaMaxima)){
			request.setAttribute("mensajeError", "Complete los campos obligatorios");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else{
			try {
				this.setDistancia(Integer.parseInt(distanciaMaxima));
				super.doPost(request, response);
				
			} catch (Exception e) {
				request.setAttribute("mensajeError", "Complete los campos con valores numericos");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}

	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Integer getDistancia() {
		return distancia;
	}
}
