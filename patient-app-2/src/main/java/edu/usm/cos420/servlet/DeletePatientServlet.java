package edu.usm.cos420.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usm.cos420.dao.CloudSqlDao;
import edu.usm.cos420.dao.PatientDao;

@WebServlet(urlPatterns = {"/delete"})
public class DeletePatientServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.decode(req.getParameter("id"));
		String dbUrl = this.getServletContext().getInitParameter("sql.urlRemote");
		PatientDao dao = null;
		
		try {
			dao = new CloudSqlDao(dbUrl);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			dao.deletePatient(id);
			resp.sendRedirect("/list");
		} catch (Exception e) {
			throw new ServletException("Error deleting patient", e);
		}
	}
}
