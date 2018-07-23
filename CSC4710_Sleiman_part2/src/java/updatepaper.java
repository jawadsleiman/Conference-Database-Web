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


@WebServlet(urlPatterns = {"/updatepaper"})
public class updatepaper extends HttpServlet {

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
                String id = request.getParameter("pId");
                
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");  
                
                Statement stmt=con.createStatement(); 
                ResultSet rs=stmt.executeQuery("select * from conference_papers where id = "+id);
                
                Statement stmt1=con.createStatement();  
                ResultSet rs1=stmt1.executeQuery("select * from authors");
                               
                out.println("<style>.btn{\n" +
                            "color: white;\n" +
                            "padding: 5px 40px;\n" +
                            "background-color: saddlebrown;\n" +
                            "border-radius: 20px; \n" +
                            "border:none;\n" +
                            "\n" +
                            "}\n"
                            + "textarea{"
                        + " width:20%;"
                        + " padding:10px;}" +
                            "</style>"
                        + "<form method=\"get\" action=\"update\">"
                        + "<div style='display:none;'><label>Member ID#: </label><input type=\"text\" style=\"padding: 5px 10px;\" name=\"paperId\" value="+id+">"
                                + "<br><br></div>");
                
                    while(rs.next()){
                        String title = rs.getString(2);
                        String abstarct = rs.getString(3);
                        String filename = rs.getString(4);
                        int faid = rs.getInt(5);
                        int said = rs.getInt(6);
                        
                        out.println( "<br><label>Title: </label><br><textarea name=\"title\" style=\"padding: 5px 10px;\" value="+title+">"+title+"</textarea>\n");
                        out.println( "<br><br><label>Abstract: </label><br><textarea name=\"abstarct\" value="+abstarct+">"+abstarct+"</textarea>\n");
                        out.println( "<br><br><label>File Name: </label><br><input name=\"filename\" style=\"padding: 5px 10px;\" value="+filename+">\n");
                        
                        out.println("<br><br><select name=\"fauthor\">"
                                    + "<option value=\"null\">Select First Author</option>");
                    
                        while(rs1.next()){
                                int fid = rs1.getInt(1);
                                String fname = rs1.getString(2);

                                out.println("<option value="+fid+"");
                                    if(fid == faid){
                                        out.println("selected=selected");
                                    }
                                    out.println(">"+fname+"</option>");
                        }
                    
                    out.println("</select><br><br><select name=\"sauthor\">"
                                    + "<option value=\"null\">Select Second Author</option>");
                        rs1.first();
                        while(rs1.next()){
                                int sid = rs1.getInt(1);
                                String sname = rs1.getString(2);

                                out.println("<option value="+sid+"");
                                    if(sid == said){
                                        out.println("selected=selected");
                                    }
                                    out.println(">"+sname+"</option>");
                        }
                    
                    out.println("</select>");
                        
                        
                    }
                        out.println("<br><br><input type=\"submit\" name=\"update\" class=\"btn\" value=\"Update Paper\"></input></form>\n");
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
