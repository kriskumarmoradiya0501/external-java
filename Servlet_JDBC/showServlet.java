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

public class showServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "kk", "kk");

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM student");

                out.println("<h2>Student Records</h2>");
                out.println("<table border='1'><tr><th>ID</th><th>Name</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("sid") + "</td><td>" + rs.getString("sname") + "</td></tr>");
                }
                out.println("</table>");
                out.println("<a href='index.html'>main page</a>");
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