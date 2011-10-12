package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Punto;
import dominio.Mapa;

public class MapaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String dimensionX = request.getParameter("x");
		String dimensionY = request.getParameter("y");
		
		if (isCompleted(dimensionX) && isCompleted(dimensionY)){
			request.setAttribute("mensajeError", "Complete los campos obligatorios");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else{
			try {
				Integer x = Integer.parseInt(dimensionX);
				Integer y = Integer.parseInt(dimensionY);
				
				request.getSession().setAttribute("mapa", new Mapa(new Punto<Integer>(x,y)));
				
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
