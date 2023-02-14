/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.*;
import javax.servlet.http.HttpSession;
import model.Admin;

@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin", "/loginAdmin", "/logoutAdmin"})
public class AdminLoginController extends HttpServlet {

    @EJB
    private AdminDAO adao;

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

        String userPath = request.getServletPath();

        if (userPath.equals("/AdminLogin")) {

            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AdminLogin.jsp");
                dispatcher.forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF
        
        if (userPath.equals("/logoutAdmin")) {

            try {
                HttpSession session = request.getSession();
                session.invalidate(); 
                response.sendRedirect("AdminLogin");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

    }//END METHOD

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

        Admin admin = new Admin();

        String userPath = request.getServletPath();
        if (userPath.equals("/loginAdmin")) {
            String adminUsername = request.getParameter("adminUsername");
            String adminPass = request.getParameter("adminPass");
            Boolean login = false;
            admin.setAdminUsername(adminUsername);
            admin.setAdminPass(adminPass);

            try {
                login = adao.loginAdmin(admin);

                if (login) {
                    //LOGIN SUCCESS
                    admin = adao.getAdminID(admin);
                    HttpSession session = request.getSession();
                    session.setAttribute("adminid", admin.getAdminId().toString());
                    response.sendRedirect("Dashboard");
                } else {
                    //LOGIN FAILED
                    request.getRequestDispatcher("/WEB-INF/AdminLogin.jsp?code=failLogin").forward(request, response);

                }

            } catch (SQLException e) //CATCH EXCEPTION
            {

            }//END TRY-CATCH

        }//END IF

    }//END METHOD

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
