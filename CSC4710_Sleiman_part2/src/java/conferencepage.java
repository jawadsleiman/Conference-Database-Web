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


@WebServlet(urlPatterns = {"/conferencepage"})
public class conferencepage extends HttpServlet {

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
                ResultSet rs=null;
                String singleAuthor = request.getParameter("search");
                String submitType = request.getParameter("firstauthor");
                String coAuthor = request.getParameter("coauthor");
                String reject = request.getParameter("rejected");
                String accept = request.getParameter("accepted");
                
                String firstAuthor = null;
                if(submitType != null){
                    if(submitType.equals("Search First Author")){
                        singleAuthor = null;
                        firstAuthor = request.getParameter("search");
                    }
                }
                if(singleAuthor != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select * from authors where lname='"+singleAuthor+"'");
                    if(rs1.next()){
                        int fauthorId = rs1.getInt(1);
                        rs=stmt.executeQuery("select * from conference_papers where fauthor="+fauthorId+" and sauthor IS NULL");
                    }
                }else if(firstAuthor != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select * from authors where fname='"+firstAuthor+"'");
                    if(rs1.next()){
                        int fauthorId = rs1.getInt(1);
                        rs=stmt.executeQuery("select * from conference_papers where fauthor="+fauthorId);
                    }
                }else if(firstAuthor != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select * from authors where fname='"+firstAuthor+"'");
                    if(rs1.next()){
                        int fauthorId = rs1.getInt(1);
                        rs=stmt.executeQuery("select * from conference_papers where fauthor="+fauthorId);
                    }
                }else if(coAuthor != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select * from authors");
                    int zid = 0;
                    int lid = 0;
                    while(rs1.next()){
                        if(rs1.getString(2).equals("Zhang")){
                            zid = rs1.getInt(1);
                        }else if(rs1.getString(2).equals("Lu")){
                            lid = rs1.getInt(1);    
                        }
                    }
                    if(zid != 0 && lid != 0){
                        rs=stmt.executeQuery("select * from conference_papers ");
                    }else{
                        out.println("No Record Found");
                    }
                }else if(reject != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select * from pc_members");
                    int mid = 0;
                    int jid = 0;
                    while(rs1.next()){
                        if(rs1.getString(2).equals("Richard")){
                            mid = rs1.getInt(1);
                        }else if(rs1.getString(2).equals("Sarah")){
                            jid = rs1.getInt(1);    
                        }
                    }
                    if(mid != 0 && jid != 0){
                        int id[] = {mid,jid};
                        for(int j=0;j<2;j++){
                            Statement stmt2=con.createStatement();
                            ResultSet rs2=stmt2.executeQuery("select * from reports where pcId="+id[j]+" and "
                                    + "recomendation='reject'");
                            if(rs2.next()){
                                rs=stmt.executeQuery("select * from conference_papers where id="+rs2.getInt(5));
                            }
                        }
                    }else{
                        out.println("No Record Found");
                    }
                }else if(accept != null){
                    Statement stmt1=con.createStatement();
                    ResultSet rs1=stmt1.executeQuery("select paperId, count(paperId) from reports where "
                            + "recomendation = 'accept' group by paperId");
                    
                    while(rs1.next()){
                        if(rs1.getInt(2)>=2){
                            rs=stmt.executeQuery("select * from conference_papers where id="+rs1.getInt(1));
                        }
                    }
                }else{
                    rs=stmt.executeQuery("select * from conference_papers");
                }
             
                
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>Conference Papers</title>\n" +
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
                        "\n" +
                        "<center><div class=\"container\"><div style='float:left'><a href='http://localhost:8080/ConferenceWebsite/conferencepage?accepted=yes'>"+
						"<button class='btn'>Search Accepted Papers</button></a><a href='http://localhost:8080/ConferenceWebsite/conferencepage?rejected=yes'>"+
						"<button class='btn'>Search Rejected Papers By Matt and John</button></a><a href='http://localhost:8080/ConferenceWebsite/members'>"+
						"<button class='btn'>PC Members</button></a>"
                        + "<a href='http://localhost:8080/ConferenceWebsite/loginpage.html'><button class='btn' style='float:right'>Logout</button></a>" +
                        "  <br><h2>Conference Papers</h2>"+ 
                        "  <p>Select any one to assign Upto 3 PC Members To Conference Paper</p>\n<br>"
                        + "<form method=\"post\" action=\"conferencepage\">\n" +
                        "      <input type=\"text\" placeholder=\"Search..\" name=\"search\" required>\n" +
                        "      <input type=\"submit\" name=\"singleauthor\" value=\"Search Single Author\"></input>\n" +
                        "      <input type=\"submit\" name=\"firstauthor\" value=\"Search First Author\"></input>\n" +
                        "    </form><br><br>"+
                        "<form method=\"get\" action=\"conferencepage\">\n" +
                        "      <input type=\"submit\" name=\"coauthor\" value=\"Search Coauthorized By Zhang and Lu\"></input>\n" +
                        "    </form><br><br>"+
                        "  <table>\n" +
                        "    <thead>\n" +
                        "      <tr>\n" +
                        "        <th>ID</th>\n" +
                        "        <th>Paper Title</th>\n" +
                        "        <th>Abstract</th>\n" +
                        "        <th>Conference Paper Files</th>\n" +
                        "        <th>Assign PC Members</th>\n" +
                        "        <th>Action</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                while(rs.next()){
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    String ab = rs.getString(3);
                    String file = rs.getString(4);
                    
                    out.println("<tr>\n" +
                        "<td>"+id+"</td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/reviews?id="+id+"'>"+title+"</a></td>\n" +
                        "<td>"+ab+"</td>\n" +
                        "<td>"+file+"</td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/pcmembers?id="+id+"'><button class='btn'>Select</button></a></td>\n" +
                        "<td><a href='http://localhost:8080/ConferenceWebsite/updatepaper?pId="+id+"'>Update</a>/"
                                    + "<a href='http://localhost:8080/ConferenceWebsite/delete?id="+id+"&delete=p_yes''>Delete</a></td>"+
                        "</tr>\n");
                }
                        out.println("</tbody>\n" +
                        "  </table>\n" +
                        "<br><br><a href='http://localhost:8080/ConferenceWebsite/insertpaper.html'>"
                                + "<button name=\"addPaper\" value=\"Add New Paper\" class=\"btn\">Add New Paper</button>"
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
