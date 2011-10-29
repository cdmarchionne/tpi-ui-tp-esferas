package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uqbar.commons.model.UserException;

import utils.Punto;
import dominio.Casillero;
import dominio.Mapa;
import dominio.Posicionable;

@SuppressWarnings("serial")
public abstract class CrearPosicionableServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String posicionX = request.getParameter("x");
		String posicionY = request.getParameter("y");
		String selectorIndex = request.getParameter("selector");
		
		if (isCompleted(posicionX) && isCompleted(posicionY)){
			request.setAttribute("mensajeError", "Complete los campos obligatorios");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else{
			try {
				Integer x = Integer.parseInt(posicionX);
				Integer y = Integer.parseInt(posicionY);
				Integer index = Integer.parseInt(selectorIndex);
				Punto<Integer> posicion = new Punto<Integer>(x,y);
				
				Mapa mapa = (Mapa) request.getSession().getAttribute("mapa");
				
				try {
					mapa.addCasillero(new Casillero(posicion, this.crearPosicionable(mapa,index)));
					request.getRequestDispatcher("mapa.jsp").forward(request, response);
				} catch (UserException e) {
					// Cuando intento agregar un casillero en una posicion ya ocupada se genera esta excepsion
					request.setAttribute("mensajeError", e.getMessage());
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				request.setAttribute("mensajeError", "Complete los campos con valores numericos");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}

	}

	protected abstract Posicionable crearPosicionable(Mapa mapa, Integer index);
//	{	return new PosicionableNulo();	}

	protected final boolean isCompleted(String valor){
		return (valor==null) || (valor.isEmpty());
	}
}
