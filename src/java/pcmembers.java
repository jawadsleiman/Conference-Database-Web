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

@WebServlet(urlPatterns = {"/pcmembers"})
public class pcmembers extends HttpServlet {
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
                String id = request.getParameter("id");
                
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAWAD_DB","root","");  
                int count=0;
                Statement stmt=con.createStatement(); 
                ResultSet rs=stmt.executeQuery("select * from member_assigning where paperId = "+id);
                    while (rs.next()) {
                        count++;
                    }
                    
                    if(count>2){
                        response.sendRedirect("http://localhost:8080/ConferenceWebsite/msgText.html");
                    }   
                    
                ResultSet r = stmt.executeQuery("select * from pc_members");
                
                out.println("<style>.btn{\n" +
                            "color: white;\n" +
                            "padding: 5px 40px;\n" +
                            "background-color: saddlebrown;\n" +
                            "border-radius: 20px; \n" +
                            "border:none;\n" +
                            "\n" +
                            "}\n" +
                            "</style>"
                        + "<form method=\"get\" action=\"insertMember\">"
                        + "<label>Paper ID#: </label><input type=\"text\" style=\"padding: 5px 10px;\" name=\"paperid\" value="+id+">"
                                + "<br><br>"
                        + "<label>Members List: </label><select name=\"mid\" style=\"padding: 5px 10px;\">\n");
                
                    while(r.next()){
                        int memberid = r.getInt(1);
                        String name = r.getString(2);
                        
                        out.println( "<option value="+memberid+">"+name+"</option>\n");
                    }
                
                out.println("</select>\n</div><br><br>"
                        + "<button type=\"submit\" class=\"btn\">Submit</button></form>\n");
                con.close();  
                    
            }catch(Exception e){ 
                out.println(e);
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
