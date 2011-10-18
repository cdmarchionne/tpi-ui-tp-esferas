package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AgregarEsferaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

//		Mapa mapa = (Mapa) request.getSession().getAttribute("mapa");
		request.getRequestDispatcher("esfera.jsp").forward(request, response);

	}
}
