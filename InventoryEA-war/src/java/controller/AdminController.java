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
import model.Admin;

@WebServlet(name = "Admin", urlPatterns = {"/Admin", "/addAdmin", "/editAdmin", "/deleteAdmin"})
public class AdminController extends HttpServlet {

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

        if (userPath.equals("/addAdmin")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Admin/addAdmin.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (userPath.equals("/editAdmin")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Admin/editAdmin.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (userPath.equals("/deleteAdmin")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Admin/deleteAdmin.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AllAdmin.jsp");
        dispatcher.forward(request, response);
        //END IF

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
        if (userPath.equals("/addAdmin")) {

            String adminFname = request.getParameter("adminFname");
            String adminLname = request.getParameter("adminLname");
            String adminUsername = request.getParameter("adminUsername");
            String adminPass = request.getParameter("adminPass");

            admin.setAdminFname(adminFname);
            admin.setAdminLname(adminLname);
            admin.setAdminUsername(adminUsername);
            admin.setAdminPass(adminPass);

            try {
                adao.insertAdmin(admin);

            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllAdmin.jsp?code=addSuccess&type=Admin&id=" + admin.getAdminUsername()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/editAdmin")) {

            String adminFname = request.getParameter("adminFname");
            String adminLname = request.getParameter("adminLname");
            String adminUsername = request.getParameter("adminUsername");
            int adminId = Integer.parseInt(request.getParameter("adminId"));

            admin.setAdminFname(adminFname);
            admin.setAdminLname(adminLname);
            admin.setAdminUsername(adminUsername);
            admin.setAdminId(adminId);

            try {
                adao.updateAdmin(admin);

            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllAdmin.jsp?code=editSuccess&type=Admin&id=" + admin.getAdminId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/deleteAdmin")) {

            int adminId = Integer.parseInt(request.getParameter("adminId"));

            admin.setAdminId(adminId);

            try {
                adao.deleteAdmin(admin);

            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllAdmin.jsp?code=deleteSuccess&type=Admin&id=" + admin.getAdminId()).forward(request, response);
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
