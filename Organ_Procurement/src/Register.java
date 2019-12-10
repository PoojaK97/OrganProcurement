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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms","root","");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String address = request.getParameter("add");
			String blood = request.getParameter("bloodgrp");
			String emer = request.getParameter("emer");
			PrintWriter pw = response.getWriter();
			String query="insert into patient values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1, "shnxjj"); //Generate random guid
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, dob);
			pstmt.setString(5, gender);
			pstmt.setString(6, contact);
			pstmt.setString(7, email);
			pstmt.setString(8, address);
			pstmt.setString(9, blood);
			pstmt.setString(10, emer);
			pstmt.execute();
			response.sendRedirect("PatientBio.html");  
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
