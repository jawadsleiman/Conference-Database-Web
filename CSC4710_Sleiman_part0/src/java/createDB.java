/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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


@WebServlet(urlPatterns = {"/createDB"})
public class createDB extends HttpServlet {

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
                    Connection conn=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/","root","");   
                    Statement stmt=conn.createStatement();  
                    String sql = "CREATE DATABASE IF NOT EXISTS JAWAD_DB"; 
                    stmt.executeUpdate(sql);
                    
                    String query1 = "CREATE TABLE conference_papers(\n" +
                                    "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,\n" +
                                    "tittle VARCHAR(100),\n" +
                                    "abstarct VARCHAR(2000),\n" +
                                    "pdf VARCHAR(20));";
                    
                    String query2 = "CREATE TABLE authors(\n" +
                                    "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                    "name VARCHAR(20),\n" +
                                    "email VARCHAR(50),\n" +
                                    "affiliation VARCHAR(50),\n" +
                                    "paperId INT,\n" +
                                    "FOREIGN KEY (paperId) REFERENCES conference_papers(id));";
                    
                    String query3 = "CREATE TABLE pc_members(\n" +
                                    "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                    "name  VARCHAR(20));";
                    
                    String query4 = "CREATE TABLE reports(\n" +
                                    "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                    "descrption VARCHAR(100) ,\n" +
                                    "recomendation VARCHAR(500),\n" +
                                    "reviewDate DATE,\n" +
                                    "paperId INT,\n" +
                                    "pcId INT,\n" +
                                    "FOREIGN KEY (paperId) REFERENCES conference_papers(id),\n" +
                                    "FOREIGN KEY (pcId) REFERENCES pc_members(id));";  
                    
                    String query5 = "CREATE TABLE member_assigning(paperId INT,\n" +
                                    "pcId INT,\n" +
                                    "FOREIGN KEY (paperId) REFERENCES conference_papers(id),\n" +
                                    "FOREIGN KEY (pcId) REFERENCES pc_members(id));";   
                    
                    String query6 = "CREATE TABLE users(\n" +
                                    "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                    "name VARCHAR(20),\n" +
                                    "email VARCHAR(20), \n" +
                                    "password VARCHAR(50)\n" +
                                    ");";
                    
                    String query7 = "insert into conference_papers values (0,\"Regions and the World Economy\",\"One of the dramatic shifts that is occurring in the world system as \n" +
                                    "we enter the twenty-first century is the increasing openness and interpenetration of national economies and sovereign states. \",\"1.pdf\"),\n" +
                                    "(0,\"Cost‐Benefit Analysis\",\"In this comment on the conference papers, Judge Posner argues for a pragmatic construal and defense of cost‐benefit \n" +
                                    "analysis, demonstrating the benefit of such analysis; responding to specific criticisms of, and suggested changes in, the analysis; and emphasizing\n" +
                                    "that the value of such analysis as an evaluative and decision tool for social and economic policy making does not depend on the resolution of \n" +
                                    "philosophical problems.\",\"2.pdf\"),\n" +
                                    "(0,\"Visual discourse in scientific conference papers A genre-based study\",\"The purpose of this paper is to investigate the role of visual \n" +
                                    "communication in a spoken research genre, the scientific conference paper. To this end, the study analyses the 2048 visuals projected during 90\n" +
                                    "papers given at five international conferences in three fields (geology, medicine, and physics), in order to bring out the recurrent features of\n" +
                                    " the visual dimension.\",\"3.pdf\"),\n" +
                                    "(0,\"Uniqueness of ab initio shape determination in small-angle scattering\",\"Scattering patterns from geometrical bodies with different shapes and\n" +
                                    " anisometry (solid and hollow spheres, cylinders, prisms) are computed and the shapes are reconstructed ab initio using envelope function and bead\n" +
                                    " modelling methods. A procedure is described to analyze multiple solutions provided by bead modeling methods and to estimate stability and \n" +
                                    " reliability of the shape reconstruction. It is demonstrated that flat shapes are more difficult to restore than elongated ones and types of shapes\n" +
                                    " are indicated, which require additional information for reliable shape reconsrtuction from the scattering data.\",\"4.pdf\"),\n" +
                                    "(0,\"Integrating quantitative and qualitative research: how is it done?\",\"This article seeks to move beyond typologies of the ways in which quantitative\n" +
                                    " and qualitative research are integrated to an examination of the ways that they are combined in practice. The article is based on a content analysis\n" +
                                    " of 232 social science articles in which the two were combined. An examination of the research methods and research designs employed suggests that on\n" +
                                    " the quantitative side structured interview and questionnaire research within a cross-sectional design tends to predominate, while on the qualitative\n" +
                                    " side the semi-structured interview within a cross-sectional design tends to predominate. An examination of the rationales that are given for employing\n" +
                                    " a mixed-methods esearch approach and the ways it is used in practice indicates that the two do not always correspond. \",\"5.pdf\"),\n" +
                                    "(0,\"Planar Antennas for Wireless Communications\",\"Planar Antennas for Wireless Communications Wiley the advent of cellular communication systems and\n" +
                                    " wireless local area networks, planar antennas with low profile, such as microstrip and other printed antennas, have been receiving a lot of \n" +
                                    " attention. Many researchers, including the author and his graduate students, have reported their designs in journal articles and conference papers.\n" +
                                    " The intent of this book is to organize and present these advanced designs in the area of planar antennas for wireless communications. \",\"6.pdf\"),\n" +
                                    "(0,\"The pivotal role of conference papers in the network of scientific communication\",\"A fresh insight, a new discovery, a novel invention, unless\n" +
                                    " made available to others in the public domain, will remain more than a piece of private intellectual property, fated to accompany its owner to the\n" +
                                    " grave. \",\"7.pdf\"),\n" +
                                    "(0,\"Country equity and country branding: Problems and prospects\",\"This paper surveys and reviews the voluminous research on product-country images\n" +
                                    " and their effects, with the objectives of discussing the multi-faceted nature of country equity and its importance, and identifying knowledge gaps\n" +
                                    " and misconceptions that often impede the development of successful business or national strategies based on effective country branding. The paper\n" +
                                    " concludes that country-based marketing is often underused or misdirected due to inadequate understanding of the meaning of 'country branding', and \n" +
                                    " suggests approaches for strategy development as well as pointing to knowledge gaps that call for additional research.\",\"8.pdf\"),\n" +
                                    "(0,\"Towards an Inverse Constant Q Transform\",\"The Constant Q transform has found use in the analysis of musical signals due to its logarithmic frequency\n" +
                                    "resolution. Unfortunately, a considerable drawback of the Constant Q transform is that there is no inverse\n" +
                                    "transform.\",\"9.pdf\"),\n" +
                                    "(0,\"The Moving Finger\",\"The remit of Working Group 8.2 (WG 8.2) is officially identified by the International Federation for Information Processing\n" +
                                    " (IFIP) as “the interaction of information systems and the organization” and its “Scope and Aims” statement (http://www.ifipwg82.org/) talks of \n" +
                                    " “building theories about the role and impact of IT in specific organizational contexts.” Thus, while WG 8.2 may not be the only group of IS \n" +
                                    " researchers concerned with understanding the relationship between social context and the development and use of information systems, for example,\n" +
                                    " WG 8.1 (“Design and Evaluation of Information Systems”) and WG 9.1 (“Computers and Work”) may be expected to share similar interests, \n" +
                                    " the Group would seem a potentially important forum for research that seeks to address the social dimension of IS. \",\"10.pdf\");";       
                    
                    String query8 = "insert into authors values(0,\"Scott, Allen J.\",\"scot23@gmail.com\",\"Amy\",2),\n" +
                                    "(0,\"Richard A. Posner\",\"posner@gmail.com\",\"Heslop\",3),\n" +
                                    "(0,\"Elizabeth Rowley\",\"rowley@gmail.com\",\"Tim\",6),\n" +
                                    "(0,\"V. V. Volkov\",\"volkov@gmail.com\",\"Richard\",9),\n" +
                                    "(0,\"Alan Bryman\",\"alan@gmail.com\",\"jimmy\",10),\n" +
                                    "(0,\"Kin-Lu Wong\",\"kni@gmail.com\",\"Tom\",1),\n" +
                                    "(0,\"Jolivet\",\"joli232@gmail.com\",\"Scott\",2),\n" +
                                    "(0,\"N Papadopoulos\",\"papado67@gmail.com\",\"Ray\",3),\n" +
                                    "(0,\"Derry FitzGerald\",\"derry@yahoo.com\",\"Walton\",5),\n" +
                                    "(0,\"Matthew Jones\",\"jones@gmail.com\",\"Chris\",8);";  
                    
                    String query9 = "insert into pc_members values(0,\"David\"),\n" +
                                    "(0,\"Richard\"),\n" +
                                    "(0,\"Elisa\"),\n" +
                                    "(0,\"Stuart\"),\n" +
                                    "(0,\"Aberman\"),\n" +
                                    "(0,\"Petter\"),\n" +
                                    "(0,\"Jhon\"),\n" +
                                    "(0,\"Paul\"),\n" +
                                    "(0,\"Sarah\"),\n" +
                                    "(0,\"James\");";
                    
                    String query10 = "insert into reports values(0,\"I like it\",\"accept\",\"2012-02-02\",3,1),\n" +
                                    "(0,\"Not a good one\",\"accept\",\"2012-03-05\",8,5),\n" +
                                    "(0,\"Not Formatted Properly\",\"accept\",\"2013-01-18\",10,3),\n" +
                                    "(0,\"Work is Nice\",\"reject\",\"2014-04-09\",1,9),\n" +
                                    "(0,\"Neatly Formatted\",\"reject\",\"2014-08-30\",1,3),\n" +
                                    "(0,\"Irralevent\",\"accept\",\"2015-06-12\",3,4),\n" +
                                    "(0,\"Unnecessary Information\",\"accept\",\"2015-11-15\",9,2),\n" +
                                    "(0,\"Good one\",\"accept\",\"2015-12-25\",6,8),\n" +
                                    "(0,\"Data is short\",\"accept\",\"2016-05-08\",7,9),\n" +
                                    "(0,\"Irralevent\",\"accept\",\"2017-05-01\",2,1);";       
                    
                    String query11 = "insert into member_assigning values(5,4),\n" +
                                    "(5,3),\n" +
                                    "(8,8),\n" +
                                    "(3,3),\n" +
                                    "(9,5),\n" +
                                    "(1,4),\n" +
                                    "(5,1),\n" +
                                    "(7,2);";
                    
                    String query12 = "insert into users values(0,\"john\",\"john@gmail.com\",\"pass1234\");";
                    
                    Connection conn1=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/JAWAD_DB","root",""); 
                    Statement stmt1=conn1.createStatement();
                    stmt1.executeUpdate(query1);
                    stmt1.executeUpdate(query2);
                    stmt1.executeUpdate(query3);
                    stmt1.executeUpdate(query4);
                    stmt1.executeUpdate(query5);
                    stmt1.executeUpdate(query6);
                    stmt1.executeUpdate(query7);
                    stmt1.executeUpdate(query8);
                    stmt1.executeUpdate(query9);
                    stmt1.executeUpdate(query10);
                    stmt1.executeUpdate(query11);
                    stmt1.executeUpdate(query12);
                    
                    response.sendRedirect("http://localhost:8080/ConferenceWebsite/index.html");
                    conn1.close();
                    conn.close();  
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
