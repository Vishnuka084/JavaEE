package lk.ijse.pos.servlet.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

//http://localhost:8080/pos_one/customer
//http://localhost:8080/pos_one/pages/customer? 404
//http://localhost:8080/customer? 404

//http://localhost:8080/pos_one/pages/customer//
//http:://localhost:8080/pos_one/pages/customer
//http:://localhost:8080/pos_one/pages/customer

@WebServlet(urlPatterns = "/pages/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu1234");
            PreparedStatement pstm = connection.prepareStatement("select * from Customer");
            ResultSet rst = pstm.executeQuery();
            PrintWriter writer = resp.getWriter();
            writer.println("<h1 style='color:red'>All Customers</h1>");
            writer.println("<link href='//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' rel='stylesheet' id='bootstrap-css'>");
            writer.println("<br>");
            writer.println("<table class='table table-bordered'>");
            writer.println("<thead><tr><th>Customer ID</th><th>Customer Name</th><th>Customer Address</th></tr></thead>");
            writer.println("<tbody>");

            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                writer.println("<tr>");
                writer.println("<td>" + id + "</td>");
                writer.println("<td>" + name + "</td>");
                writer.println("<td>" + address + "</td>");
                writer.println("</tr>");
            }
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("<br>");
            writer.println("<form action='customer.html'>");
            writer.println("<button class='btn btn-danger' type='submit'>Go Back</button>");
            writer.println("</form>");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
        String cusSalary = req.getParameter("cusSalary");
        String option = req.getParameter("option");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "sanu1234");


        switch (option) {
            case "add":
                PreparedStatement pstm = connection.prepareStatement("insert into Customer values(?,?,?)");
                pstm.setObject(1, cusID);
                pstm.setObject(2, cusName);
                pstm.setObject(3, cusAddress);
                if (pstm.executeUpdate() > 0) {
                    resp.getWriter().println("Customer Added..!");
                    resp.sendRedirect("customer");
                }
                break;
            case "delete":
                PreparedStatement pstm2 = connection.prepareStatement("delete from Customer where id=?");
                pstm2.setObject(1, cusID);
                if (pstm2.executeUpdate() > 0) {
                    resp.getWriter().println("Customer Deleted..!");
                    resp.sendRedirect("/pos_one/pages/customer.html");
                }
                break;
            case "update":
                PreparedStatement pstm3 = connection.prepareStatement("update Customer set name=?,address=? where id=?");
                pstm3.setObject(3, cusID);
                pstm3.setObject(1, cusName);
                pstm3.setObject(2, cusAddress);
                if (pstm3.executeUpdate() > 0) {
                    resp.getWriter().println("Customer Updated..!");
                    resp.sendRedirect("/pos_one/pages/customer.html");
                }
                break;
        }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
