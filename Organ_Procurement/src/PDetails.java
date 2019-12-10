import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PDetails")
public class PDetails extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ResultSet rs;
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root","");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String id = request.getParameter("id");
			PrintWriter pw = response.getWriter();
			String sql="select * from Patient where Id="+id;
			rs = stmt.executeQuery(sql);
			rs.last();
			int size = rs.getRow();
			if (size>0) {
				//pw.println("ID Found: "+id);
				response.sendRedirect("registration.html");  
			}
			else {
				//Print the message below using include.
				pw.println("No such ID exists, Kindly Retry or Register.");
				pw.println("<html><body><br><br><input type='submit' value='submit'></body></html>");
			}
		}catch(ClassNotFoundException e) {e.printStackTrace();}
		catch(SQLException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
			con.close();
			}catch(Exception e) {}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		PrintWriter pw = response.getWriter();
		pw.println("Your id is: "+id);
	}

}
