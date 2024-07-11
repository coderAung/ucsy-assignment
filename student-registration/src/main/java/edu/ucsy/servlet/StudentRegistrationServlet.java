package edu.ucsy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student-regis")
public class StudentRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String rollNo = req.getParameter("rollNo");
		String phone = req.getParameter("phone");
		String subject = req.getParameter("subject");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String infoHtml = """
				<table>
					<tr>
						<td>Name</td>
						<td>:</td>
						<td>%s</td>
					</tr>
					<tr>
						<td>Roll No.</td>
						<td>:</td>
						<td>%s</td>
					</tr>
					<tr>
						<td>Phone</td>
						<td>:</td>
						<td>%s</td>
					</tr>
					<tr>
						<td>Subject</td>
						<td>:</td>
						<td>%s</td>
					</tr>
				</table>
				""";
		out.append(infoHtml.formatted(name, rollNo, phone, subject));
		out.flush();
	}
}
