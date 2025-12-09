/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class insertServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int sid = Integer.parseInt(request.getParameter("sid"));
            String sname = request.getParameter("sname");
            String gender = request.getParameter("gender");
            String qualification = request.getParameter("qualification");
            
            String[] hobbiesArray = request.getParameterValues("hobbies");
            String hobbies = (hobbiesArray != null) ? String.join(",", hobbiesArray) : "";

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "kk", "kk");

                PreparedStatement ps = con.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, sid);
                ps.setString(2, sname);
                ps.setString(3, gender);
                ps.setString(4, qualification);
                ps.setString(5, hobbies);

                int rows = ps.executeUpdate();
                out.println("<h2>" + rows + " record inserted successfully!</h2>");

                con.close();
                } catch (Exception e) {
                out.println("<h2 style='color:red;'>Error: " + e.getMessage() + "</h2>");
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}