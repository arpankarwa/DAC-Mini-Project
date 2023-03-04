package study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signin")
public class SignIn extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");

		String password = request.getParameter("password");
		//		int pass = Integer.parseInt(pwd);
		DAO dao = new DAO();
		PrintWriter pw = response.getWriter();
		boolean validity = dao.validate(username, password);

		pw.println("<html><body>");


		if(validity) {
			pw.println("<h1 align='center'>Login Successful!!!</h1>");
		}
		else
		{
			pw.println("Invalid Credentials");
			pw.println("<a href='signin.html'> Enter login details again</a>");
		}
		pw.println("</body></html>");
		pw.close();




	}

	//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		
	//	}

}
