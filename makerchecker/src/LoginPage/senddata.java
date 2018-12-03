package LoginPage;

import java.io.IOException;
import static java.lang.System.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class senddata
 */
@WebServlet("/senddata")
public class senddata extends HttpServlet {
	static Connection connection = null;
	static {
		try

		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a connection
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
		} catch (ClassNotFoundException exception) {
			System.out.println(exception);
		} catch (SQLException exception) {
			System.out.println(exception);

		}

	}

	public void connmethod(String customerId, String CustomerCode, String CustomerName, String CustomerAddress1,
			String CustomerAddress2, String CustomerPinCode, String Emailaddress, String ContactNumber,
			String PrimaryContactPerson, String RecordStatus, String ActiveInactiveFlag, String CreateDate,
			String CreatedBy, String ModifiedDate, String ModifiedBy, String AuthorizedDate, String AuthorizedBy)throws SQLException,IOException {
		try {

			String teString = "insert into TEMP_MAKER values('"+customerId+"','" + CustomerCode + "','" + CustomerName +"','"+CustomerAddress1+ "','" +CustomerAddress2+"','" +CustomerPinCode +"','"+Emailaddress +"','"+ContactNumber+ "','" +PrimaryContactPerson+ "','" +RecordStatus+ "','" +ActiveInactiveFlag+ "','" +CreateDate+ "','" +CreatedBy+ "','" +ModifiedDate+ "','" +ModifiedBy+ "','"+AuthorizedDate+ "','"+AuthorizedBy+ "')";


			
			Statement stmt = connection.createStatement();
			System.out.println(teString);
			int i = stmt.executeUpdate(teString);
			System.out.println("datainserted successfully");
			System.out.println(i);
			connection.commit();
			out.print("record level completed successfully");
			//System.err.println(stmt.toString());
			//stmt.executeUpdate(teString);

		} catch (Exception e) {
			System.out.println("connmethod : " + e);
		}
	}
	public void filelevel() throws SQLException{
		
		Statement stmt =connection.createStatement();
//		String str="truncate table TEMP_MAKER";
//		int i=stmt.executeUpdate(str);
		
		connection.rollback();
		System.out.println("data deleted");
		//System.exit(0);
		out.print("file level completed successfully");
	}

}
