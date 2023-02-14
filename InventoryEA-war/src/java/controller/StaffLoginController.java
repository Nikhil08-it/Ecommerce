package controller;

import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import model.Staff;

@WebServlet(name = "StaffLogin", urlPatterns = {"/StaffLogin", "/loginStaff", "/logoutStaff"})
public class StaffLoginController extends HttpServlet {

    @EJB
    private StaffDAO sdao;

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

        if (userPath.equals("/StaffLogin")) {

            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/StaffView/StaffLogin.jsp");
                dispatcher.forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/logoutStaff")) {

            try {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("StaffLogin");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF
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

        Staff staff = new Staff();
        String userPath = request.getServletPath();
        if (userPath.equals("/loginStaff")) {

            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String staffPass = request.getParameter("staffPass");
            Boolean login = false;
            staff.setStaffId(staffId);
            staff.setStaffPass(staffPass);

            try {
                login = sdao.loginStaff(staff);
                System.out.println(login);

                if (login) {
                    //LOGIN SUCCESS
                    HttpSession session = request.getSession();
                    session.setAttribute("staffid", Integer.toString(staffId));

                    response.sendRedirect("StaffDashboard");
                } else {
                    //LOGIN FAILED
                    request.getRequestDispatcher("/WEB-INF/StaffView/StaffLogin.jsp?code=failLogin").forward(request, response);
                }//END IF

            } catch (SQLException e) {

            }//END TRY-CATCH

        }//END IF

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
