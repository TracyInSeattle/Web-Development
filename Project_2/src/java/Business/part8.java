/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author fengcu
 */
public class part8 extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "12345678";
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>HOMEWORRK2_PART8_addBooks</title>");            
        out.println("</head>");
        out.println("<body style=\"background-color:rgba(230, 123, 185, 0.5);\">");
        
        String bookNumber = request.getParameter("bookNumber");
        int count = Integer.valueOf(bookNumber);
        ArrayList<String> isbn = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            isbn.add(request.getParameter("isbn" + String.valueOf(i)));
            title.add(request.getParameter("title" + String.valueOf(i)));
            authors.add(request.getParameter("authors" + String.valueOf(i)));
            prices.add(request.getParameter("price" + String.valueOf(i)));
        }
        // database operation
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, user, password);
            statement = connection.createStatement();
            for (int i = 0; i < count; i++) {
                String query = "insert into books (isbn, title, authors, price) value ('" + 
                        isbn.get(i) + "', '" + title.get(i) + "', '" + authors.get(i) + "', " + prices.get(i) + ")"; 
                int result = statement.executeUpdate(query);
                if (result < 0) {
                    throw new Exception("Add Failed!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.println("<p>" + bookNumber + " books added successfully</p>");
        out.println("<a href=\"part8_inputBookNumber.jsp\">Click here to Go Gack to Homepage</a>");
        out.println("</body>");
        out.println("</html>");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}