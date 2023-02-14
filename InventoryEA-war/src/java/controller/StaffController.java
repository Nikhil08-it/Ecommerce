package controller;

import dao.StaffDAO;
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
import model.Staff;


@WebServlet(name = "Staff", urlPatterns = {"/Staff", "/addStaff", "/editStaff", "/deleteStaff", "/StaffProfile", "/editProfileStaff"})
public class StaffController extends HttpServlet {

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

        if (userPath.equals("/addStaff")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Staff/addStaff.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/editStaff")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Staff/editStaff.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/deleteStaff")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Staff/deleteStaff.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/StaffProfile")) {

            try {
                request.getRequestDispatcher("/WEB-INF/StaffView/StaffProfile.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AllStaff.jsp");
        dispatcher.forward(request, response);

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

        Staff staff = new Staff();

        String userPath = request.getServletPath();
        if (userPath.equals("/addStaff")) {

            String staffFname = request.getParameter("staffFname");
            String staffLname = request.getParameter("staffLname");
            String staffAddress = request.getParameter("staffAddress");
            String staffPass = request.getParameter("staffPass");

            staff.setStaffFname(staffFname);
            staff.setStaffLname(staffLname);
            staff.setStaffAddress(staffAddress);
            staff.setStaffPass(staffPass);

            try {
                sdao.insertStaff(staff);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllStaff.jsp?code=addSuccess&type=Staff&id=" + staff.getStaffFname() + " " + staff.getStaffLname()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/editStaff")) {

            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String staffFname = request.getParameter("staffFname");
            String staffLname = request.getParameter("staffLname");
            String staffAddress = request.getParameter("staffAddress");
            String staffPass = request.getParameter("staffPass");

            staff.setStaffId(staffId);
            staff.setStaffFname(staffFname);
            staff.setStaffLname(staffLname);
            staff.setStaffAddress(staffAddress);
            staff.setStaffPass(staffPass);

            try {
                sdao.updateStaff(staff);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                request.getRequestDispatcher("/WEB-INF/AllStaff.jsp?code=editSuccess&type=Staff&id=" + staff.getStaffId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/deleteStaff")) {

            int staffId = Integer.parseInt(request.getParameter("staffId"));
            System.out.println(staffId);

            staff.setStaffId(staffId);

            try {
                sdao.deleteStaff(staff);
            } catch (SQLException e) //CATCH EXCEPTION
            {
                
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllStaff.jsp?code=deleteSuccess&type=Staff&id=" + staff.getStaffId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/editProfileStaff")) {
            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String staffFname = request.getParameter("staffFname");
            String staffLname = request.getParameter("staffLname");
            String staffAddress = request.getParameter("staffAddress");
            String staffPass = request.getParameter("staffPass");
            System.out.println(staffPass);

            staff.setStaffId(staffId);
            staff.setStaffFname(staffFname);
            staff.setStaffLname(staffLname);
            staff.setStaffAddress(staffAddress);
            staff.setStaffPass(staffPass);

            try {
                sdao.updateStaff(staff);
                sdao.updateStaffPassword(staff);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                response.sendRedirect("StaffProfile?code=editSuccess");
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
