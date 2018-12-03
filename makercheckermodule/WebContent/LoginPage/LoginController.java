package LoginPage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.CommonDialogs.Type;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {


	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("UserName");
		String password = request.getParameter("password");
		String type = request.getParameter("list");

		try {
			if (validateLogin(username, password, type)) {
				if (type.equals("m")) {
					response.sendRedirect("maker.html");
				} else {
					response.sendRedirect("checker.html");
				}
			} else {
				response.sendRedirect("errorpage.html");
				System.out.println("invalid login");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validateLogin(String username, String password, String type) throws SQLException, ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Get a connection
	Connection	connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
			

			PreparedStatement stmt = connection
					.prepareStatement("select count(*) from verify_details where username=? and password=? and type=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, type);
			stmt.execute();
			ResultSet rs=stmt.getResultSet();
			rs.next();
			System.out.println(rs.getInt(1));
			if(rs.getInt(1)>0)
			{
				return true;
				
		
	}else {
			return false;
	}
	}
}

	

	

