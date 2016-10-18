/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import database.DB_Conn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FixOperation extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changeProductStatus</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeProductStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {String ChangeStatus = request.getParameter ("ChangeStatus");
        String order [] = request.getParameterValues("order");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        if (order !=  null){
            for (int i=0; i<order.length; i++){
                out.println (" <br/>"+order[i]);
            }
            
            if (ChangeStatus.equals("regler")){
                try {StringBuffer sqlBuffer = new StringBuffer();
                    
                    
                    Connection c = new DB_Conn().getConnection();
                    Statement st = c.createStatement();
                    for (int i=0; i<order.length; i++){
                    String sqlUpdatePending = "UPDATE  `order` SET " +
    " `operation_exceptionnelle` =  NULL WHERE  `order`.`order_id` ='"+order[i]+"' ; ";
                        //sqlBuffer.append(sqlUpdatePending);
                        st.addBatch(sqlUpdatePending);
                    }
                    
                    out.print(" "+sqlBuffer.toString());
                    int[] executeBatch = st.executeBatch();                   
                    
                } catch (SQLException ex) {
                    out.print(" " +ex);
                    Logger.getLogger(changeProductStatus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    out.print(" " +ex);
                    Logger.getLogger(changeProductStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                response.sendRedirect(request.getContextPath()+"/userinfo.jsp");
        
            
    }
        
        }
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
