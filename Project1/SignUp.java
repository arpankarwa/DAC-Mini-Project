package study;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		String gender=request.getParameter("gender");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		DAO dao=new DAO();
		PrintWriter pw = response.getWriter();
		pw.println("<html><body><form action='signin.html'>");
		try {
			boolean reg = dao.addRecord(username, name, Long.parseLong(contact), gender, password);
			if(reg) {
				pw.println("<h1 align='center'> registered successfully</h1>");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("<br/><input type='submit' value='Return to SignIn'/>");
		pw.println("</form></body></html>");
		pw.close();


		
	}

}
