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


@WebServlet(urlPatterns = {"/reviewqueries"})
public class reviewqueries extends HttpServlet {

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
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
             
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>PC Members</title>\n" +
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
                        + "}\n" +
                        "</style></head>\n" +
                        "<body>\n" +
                        "\n");
                
                        
                        out.println("<center><div class=\"container\"><a href='http://localhost:8080/ConferenceWebsite/conferencepage'><button class='btn' style='float:left'>Conference Papers</button></a>"
                        + "<a href='http://localhost:8080/ConferenceWebsite/loginpage.html'>"
                        + "<button class='btn' style='float:right'>Logout</button></a>"
                        + "<center><a href='http://localhost:8080/ConferenceWebsite/reviewqueries?reviewer=m_review'>"
                        + "<button class='btn' >Most Conference Paper Reviewer </button></a>"
                        + "<a href='http://localhost:8080/ConferenceWebsite/reviewqueries?reviewer=n_review'>"
                        + "<button class='btn'>PC Member neither reviewed any paper</button></a></center>" +
                        "  <br><h2>PC Members</h2>"+
                        "  \n<br>" +
                        "  <table>\n" +
                        "    <thead>\n" +
                        "      <tr>\n" +
                        "        <th>ID</th>\n" +
                        "        <th>Member Name</th>\n" +
                        "        <th>Action</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                        
                        String query = request.getParameter("reviewer");
                        Statement stmt=con.createStatement();
                        ResultSet rs = null;
                        if(query.equals("m_review")){
                            Statement stmt1=con.createStatement();
                            ResultSet rs1=stmt1.executeQuery("select pcId,MAX(reviews) FROM (SELECT pcId,COUNT(*) "
                                    + "AS reviews FROM reports group by pcId) AS member_review;");
                            
                            while(rs1.next()){
                                rs=stmt.executeQuery("select * from pc_members where id="+rs1.getInt(1));
                            }
                        }else{
                            rs=stmt.executeQuery("select * from pc_members p left join reports r on p.id = r.id where r.id is null");
                        }             
                while(rs.next()){
                    int id = rs.getInt(1);
                    String mname = rs.getString(2);
                    
                    out.println("<tr>\n" +
                        "<td>"+id+"</td>\n" +
                        "<td>"+mname+"</td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/updatepcmember?id="+id+"'>Update</a>/"
                                + "<a href='http://localhost:8080/ConferenceWebsite/delete?id="+id+"&delete=m_yes'>Delete</a></td>"+
                        "</tr>\n");
                }
                        out.println("</tbody>\n" +
                        "  </table>\n" +
                        "<br><br><a href='http://localhost:8080/ConferenceWebsite/insertmember.html'>"
                                + "<button name=\"addMember\" value=\"Add PC Member\" class=\"btn\">Add PC Member</button>"
                                + "</a><br><br></div>\n" +
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
