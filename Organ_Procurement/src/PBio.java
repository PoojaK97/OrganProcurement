

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class PBio
 */
@WebServlet("/PBio")
public class PBio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PBio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root","");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String height = request.getParameter("h");
			String weight = request.getParameter("w");
			String bloodp = request.getParameter("bp");
			PrintWriter pw = response.getWriter();
			String query="insert into bio values(?,?,?,?, ?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, "hjsacvhj"); //ID to be created
			pstmt.setString(2, height);
			pstmt.setString(3, weight);
			pstmt.setString(4, bloodp);
			pstmt.setInt(5, 2131214); //Timestamp: Current Time to be taken
			pstmt.execute();
		}catch(ClassNotFoundException e) {e.printStackTrace();}
		catch(SQLException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
			con.close();
			}catch(Exception e) {}
		}
	}

}
