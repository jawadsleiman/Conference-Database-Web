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


@WebServlet(urlPatterns = {"/reviews"})
public class reviews extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            try{ 
                
                String id = request.getParameter("id");
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                   
                Statement stmt=con.createStatement();  
                Statement stmt1=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from reports where paperId="+id);
                ResultSet rs1=stmt1.executeQuery("select * from conference_papers where id="+id);
             
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>Reviews</title>\n" +
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
                        + "a{"
                        + "color:saddlebrown;"
                        + "text-decoration:none;"
                        + "padding:5px;"
                        + "}"
                        + "table,th,td{"
                        + " border:1px solid black;"
                        + "border-spacing:0px;"
                        + "}"
                        + "textarea{"
                        + "width:20%;"
                        + "padding:10px;"
                        + "}\n" +
                        "</style></head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<center><div class=\"container\"><a href='http://localhost:8080/ConferenceWebsite/conferencepage'><button class='btn' style='float:left'>Conference Papers</button></a>"
                        + "<a href='http://localhost:8080/ConferenceWebsite/loginpage.html'><button class='btn' style='float:right'>Logout</button></a>" +
                        "  <br><h2>Paper Reviews</h2>");
                
                while(rs1.next()){
                    String abs = rs1.getString(2);        
                    out.println("<label>Abstract</label><br><textarea disabled>"+abs+"</textarea>");
                }
                rs1.close();
                        out.println( "  \n<br><br>" +
                        "  <table>\n" +
                        "    <thead>\n" +
                        "      <tr>\n" +
                        "        <th>Description</th>\n" +
                        "        <th>Recommendation</th>\n" +
                        "        <th>Action</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                while(rs.next()){
                    int rid = rs.getInt(1);
                    String desc = rs.getString(2);
                    String recommend = rs.getString(3);
                    
                    out.println("<tr>\n" +
                        "<td>"+desc+"</td>\n" +
                        "<td>"+recommend+"</td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/updatereview?id="+rid+"'>Update</a>/"
                                + "<a href='http://localhost:8080/ConferenceWebsite/delete?id="+rid+"&delete=r_yes'>Delete</a></td>"+
                        "</tr>\n");
                }
                        out.println("</tbody>\n" +
                        "  </table><br><br><a href='http://localhost:8080/ConferenceWebsite/insertReview?id="+id+"&pcid=2'>"
                                + "<button name=\"addReview\" value=\"Add Review\" class=\"btn\">Add Review</button>"
                                + "</a>\n" +
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
