package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.webLab.dao.TFGDAO;
import es.upm.dit.isst.webLab.dao.TFGDAOImplementation;
import es.upm.dit.isst.webLab.model.TFG;

@WebServlet("/TFGServlet")
public class TFGServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		TFGDAO tfgdao = TFGDAOImplementation.getInstance();
		TFG tfg = tfgdao.read(email);

		req.getSession().setAttribute("tfg", tfg);

		getServletContext().getRequestDispatcher("/TFGView.jsp").forward(req, resp);
	}
}
