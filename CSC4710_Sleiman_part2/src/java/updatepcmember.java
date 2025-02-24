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


@WebServlet(urlPatterns = {"/updatepcmember"})
public class updatepcmember extends HttpServlet {

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
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                
                Statement stmt=con.createStatement(); 
                ResultSet rs=stmt.executeQuery("select * from pc_members where id = "+id);
                               
                out.println("<style>.btn{\n" +
                            "color: white;\n" +
                            "padding: 5px 40px;\n" +
                            "background-color: saddlebrown;\n" +
                            "border-radius: 20px; \n" +
                            "border:none;\n" +
                            "\n" +
                            "}\n" +
                            "</style>"
                        + "<form method=\"get\" action=\"update\">"
                        + "<div style='display:none;'><label>Member ID#: </label><input type=\"text\" style=\"padding: 5px 10px;\" name=\"memberId\" value="+id+"></div>"
                                + "<br><br>"
                        + "<label>Members Name: </label>"
                        );
                
                    while(rs.next()){
                        String name = rs.getString(2);
                        String email = rs.getString(3);
                        
                        out.println( "<input name=\"memberName\" style=\"padding: 5px 10px;\" value="+name+">\n");
                        out.println( "<br><br><label>Members Email: </label>"
                                + "<input name=\"memberEmail\" style=\"padding: 5px 10px;\" value="+email+">\n");
                        
                    }
                        out.println("<br><br><input type=\"submit\" name=\"update\" class=\"btn\" value=\"Update Members\"></input></form>\n");
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
