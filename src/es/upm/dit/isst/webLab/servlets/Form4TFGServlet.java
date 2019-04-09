package es.upm.dit.isst.webLab.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.webLab.dao.TFGDAO;
import es.upm.dit.isst.webLab.dao.TFGDAOImplementation;
import es.upm.dit.isst.webLab.model.TFG;

@WebServlet("/Form4TFGServlet")
@MultipartConfig
public class Form4TFGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");

		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		for (int length = 0; (length = fileContent.read(buffer)) > 0;)
			output.write(buffer, 0, length);

		TFGDAO tfgdao = TFGDAOImplementation.getInstance();
		TFG tfg = tfgdao.read(email);

		tfg.setStatus(tfg.getStatus() + 1);
		tfg.setDocument(output.toByteArray());

		tfgdao.update(tfg);

		resp.sendRedirect(req.getContextPath() + "/TFGServlet?email=" + email);
	}

}
