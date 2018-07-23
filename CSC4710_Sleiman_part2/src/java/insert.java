import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/insert"})
public class insert extends HttpServlet {

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
                Statement stmt=con.createStatement(); 
                
                String submitType = request.getParameter("insert");
                
                if(submitType.equals("Insert Paper")){
                    String title = request.getParameter("title");
                    String abstarct = request.getParameter("abstract");
                    String file = request.getParameter("filename");
                    String fauthor = request.getParameter("fauthor");
                    String sauthor = request.getParameter("sauthor");


                    stmt.executeUpdate("insert into conference_papers values (0,'"+title+"','"+abstarct+"','"+file+"',"+fauthor+","+sauthor+");");
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/insertpaper.html");
                }else if(submitType.equals("Insert PC Member")){
                    String mName = request.getParameter("name");


                    stmt.executeUpdate("insert into pc_members values (0,'"+mName+"');");
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/insertmember.html");
                } else if(submitType.equals("Insert Review")){
                    String pid = request.getParameter("pid");
                    String pcid = request.getParameter("pcid");
                    String desc = request.getParameter("desc");
                    String rec = request.getParameter("rec");

                    DateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar d = Calendar.getInstance();
                    stmt.executeUpdate("insert into reports values (0,'"+desc+"','"+rec+"','"+nowDate.format(d.getTime())+"',"+pid+","+pcid+");");
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/conferencepage");
                } 
                
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
