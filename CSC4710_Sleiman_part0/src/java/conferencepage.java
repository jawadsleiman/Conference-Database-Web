/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/conferencepage"})
public class conferencepage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{ 
                
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Jawad_DB","root","");  
                   
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from conference_papers");
             
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>Conference Papers</title>\n" +
                        "<style>\n" +
                        "\n" +
                        ".container{\n" +
                        "   margin-top: 2%;\n" +
                        "}\n" +
                        "h2,label{\n" +
                        "   margin: 20px;\n" +
                        "   color: saddlebrown;\n" +
                        "}\n" +
                        ".btn{\n" +
                        "   color: white;\n" +
                        "   padding: 5px 40px;\n" +
                        "   background-color: saddlebrown;\n" +
                        "   border-radius: 20px; \n" +
                        "   border:none;\n" +
                        "\n" +
                        "}"
                        + "table,th,td{"
                        + " border:1px solid black;"
                        + "border-spacing:0px;"
                        + "}\n" +
                        "</style></head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<center><div class=\"container\">\n" +
                        "  <br><br><br><h2>Conference Papers</h2>"+ 
                        "  <p>Select any one to assign Upto 3 PC Members To Conference Paper</p>\n<br>" +
                        "  <table>\n" +
                        "    <thead>\n" +
                        "      <tr>\n" +
                        "        <th>ID</th>\n" +
                        "        <th>Paper Title</th>\n" +
                        "        <th>Abstract</th>\n" +
                        "        <th>Conference Paper Files</th>\n" +
                        "        <th>Assign PC Members</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                while(rs.next()){
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String ab = rs.getString(3);
                    String file = rs.getString(4);
                    
                    out.println("<tr>\n" +
                        "<td>"+id+"</td>\n" +
                        "<td>"+title+"</td>\n" +
                        "<td>"+ab+"</td>\n" +
                        "<td>"+file+"</td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/pcmembers?id="+id+"'><button class='btn'>Select</button></a></td>\n" +
                        "</tr>\n");
                }
                        out.println("</tbody>\n" +
                        "  </table>\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n" +
                        "\n");
                
                con.close();  
                    
            }catch(Exception ex){ 
                out.println(ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
