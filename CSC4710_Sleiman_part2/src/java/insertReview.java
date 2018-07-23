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


@WebServlet(urlPatterns = {"/insertReview"})
public class insertReview extends HttpServlet {

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
                String pid = request.getParameter("id");
                String pcid = request.getParameter("pcid");
                out.println("<style>.btn{\n" +
                            "color: white;\n" +
                            "padding: 5px 40px;\n" +
                            "background-color: saddlebrown;\n" +
                            "border-radius: 20px; \n" +
                            "border:none;\n" +
                            "\n" +
                            "}\n" +
                            "</style>"
                        + "<form method=\"post\" action=\"insert\">");        
                        out.println( "<div style='display:none;'><input name=\"pid\" style=\"padding: 5px 10px;\" value="+pid+">\n</div>"
                                + "<div style='display:none;'><input name=\"pcid\" style=\"padding: 5px 10px;\" value="+pcid+">\n</div>"
                                + "<label>Description: </label><input name=\"desc\" style=\"padding: 5px 10px;\" placeholder=\"Description\">\n");
                        out.println( "<br><br><label>Recommendation: </label>"
                                + "<select name=\"rec\" style=\"padding: 5px 10px;\">"
                                + "<option value=\"accept\">Accept</option>"
                                + "<option value=\"reject\">Reject</option></select>\n");
                       
                        out.println("<br><br><input type=\"submit\" name=\"insert\" class=\"btn\" value=\"Insert Review\"></input></form>\n");
              
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
