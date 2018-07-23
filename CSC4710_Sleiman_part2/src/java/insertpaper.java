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


@WebServlet(urlPatterns = {"/insertpaper"})
public class insertpaper extends HttpServlet {

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
                
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                   
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from authors");
            
            
            
            out.println("<head>\n" +
            "        <title>Conference Paper</title>\n" +
            "        \n" +
            "        <style>\n" +
            "            \n" +
            "            .container{\n" +
            "                margin-top: 5%;\n" +
            "            }\n" +
            "            textarea{\n" +
            "                width: 300px;\n" +
            "                height: 100px;\n" +
            "            }\n" +
            "            h2,label{\n" +
            "                margin: 20px;\n" +
            "                color: saddlebrown;\n" +
            "            }\n" +
            "            .btn{\n" +
            "                color: white;\n" +
            "                padding: 5px 40px;\n" +
            "                background-color: saddlebrown;\n" +
            "                border-radius: 20px; \n" +
            "                border:none;\n" +
            "                \n" +
            "            }\n" +
            "        </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        \n" +
            "        <a href='http://localhost:8080/ConferenceWebsite/conferencepage'>\n" +
            "            <button class=\"btn\">Back</button>\n" +
            "        </a>\n" +
            "        \n" +
            "        <center>\n" +
            "            \n" +
            "            <div class=\"container\">\n" +
            "                <h2>Add New Conference Paper</h2>\n" +
            "                <form method=\"post\" action=\"insert\">\n" +
            "                    <label>Title:</label><br>\n" +
            "                    <textarea name=\"title\" placeholder=\"Paper Title\" required></textarea><br><br>\n" +
            "\n" +
            "                    <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Abstract:</label><br>\n" +
            "                    <textarea name=\"abstract\" placeholder=\"Abstract\" required></textarea><br><br>\n" +
            "\n" +
            "                    <label>FileName:</label><br>\n" +
            "                    <input type=\"text\" name=\"filename\" placeholder=\"File Name\" required><br><br>\n" +
            "                    <select name=\"fauthor\">"
                                    + "<option value=\"null\">Select First Author</option>");
                    
                        while(rs.next()){
                                int id = rs.getInt(1);
                                String aname = rs.getString(2);

                                out.println("<option value="+id+">"+aname+"</option>");
                        }
                    
                    out.println("</select><br><br><select name=\"sauthor\">"
                                    + "<option value=\"null\">Select Second Author</option>");
                        rs.first();
                        while(rs.next()){
                                int sid = rs.getInt(1);
                                String sname = rs.getString(2);

                                out.println("<option value="+sid+">"+sname+"</option>");
                        }
                    
                    out.println("</select><br><br>\n" +
            "\n" +
            "                    <button type=\"submit\" name=\"insert\" class=\"btn\" value=\"Insert Paper\">Insert Paper</button>\n" +
            "\n" +
            "                </form>\n" +
            "            </div>\n" +
            "        </center>\n" +
            "    </body>");
            
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
