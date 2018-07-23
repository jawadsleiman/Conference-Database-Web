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


@WebServlet(urlPatterns = {"/update"})
public class update extends HttpServlet {

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
                String mId = request.getParameter("memberId");    
                String mname = request.getParameter("memberName");
                String memail = request.getParameter("memberEmail");
                String pId = request.getParameter("paperId");
                String title = request.getParameter("title");
                String faId = request.getParameter("fauthor");
                String saId = request.getParameter("sauthor");
                String abstarct = request.getParameter("abstarct");
                String filename = request.getParameter("filename");
                String rId = request.getParameter("reviewId");
                String desc = request.getParameter("desc");
                String rec = request.getParameter("rec");
                String submitType = request.getParameter("update");    
            
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                Statement stmt=con.createStatement();  
                if(submitType.equals("Update Members")){
                    stmt.executeUpdate("update pc_members set name = '"+mname+"', email = '"+memail+"' where id = "+mId);
                    con.close();
                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/members");
                }else if(submitType.equals("Update Paper")){
                    stmt.executeUpdate("update conference_papers set tittle = '"+title+"', abstarct = '"+abstarct+"', pdf = '"+filename+"',fauthor = '"+faId+"',sauthor = '"+saId+"' where id = "+pId);
                    con.close();
                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/conferencepage");
                }else if(submitType.equals("Update Review")){
                    DateFormat nowDate = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar d = Calendar.getInstance();
                    
                    stmt.executeUpdate("update reports set descrption = '"+desc+"', recomendation = '"+rec+"', reviewDate ='"+nowDate.format(d.getTime())+"' where id = "+rId);
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
