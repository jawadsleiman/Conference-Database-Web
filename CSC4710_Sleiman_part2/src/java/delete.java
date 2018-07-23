
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/delete"})
public class delete extends HttpServlet {

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
               String mId = request.getParameter("id");
               String submitType = request.getParameter("delete");
            
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                Statement stmt=con.createStatement();  
                if(submitType.equals("m_yes")){
                    stmt.executeUpdate("delete from pc_members where id = "+mId);
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/members");
                }else if(submitType.equals("p_yes")){
                    stmt.executeUpdate("delete from reports where paperId = "+mId);
                    stmt.executeUpdate("delete from member_assigning where paperId = "+mId);
                    stmt.executeUpdate("delete from conference_papers where id = "+mId);
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/conferencepage");
                }else if(submitType.equals("r_yes")){
                    stmt.executeUpdate("delete from reports where id = "+mId);
                    con.close();

                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/conferencepage");
                }
                
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
