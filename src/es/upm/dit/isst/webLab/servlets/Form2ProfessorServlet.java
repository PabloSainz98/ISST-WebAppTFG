package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.webLab.dao.TFGDAO;
import es.upm.dit.isst.webLab.dao.TFGDAOImplementation;
import es.upm.dit.isst.webLab.model.TFG;

@WebServlet("/Form2ProfessorServlet")
public class Form2ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		TFGDAO tfgdao = TFGDAOImplementation.getInstance();
		TFG tfg = tfgdao.read(email);
		tfg.setStatus(tfg.getStatus()+1);
		tfgdao.update(tfg);

		resp.sendRedirect(req.getContextPath() + "/ProfessorServlet?email=" + email);
	}

}
