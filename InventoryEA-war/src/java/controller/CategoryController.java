package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.CategoryDAO;
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
import model.Category;


@WebServlet(urlPatterns = {"/Category", "/addCategory", "/editCategory", "/deleteCategory"})
public class CategoryController extends HttpServlet {

    @EJB
    private CategoryDAO cdao;

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

        if (userPath.equals("/addCategory")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Category/addCategory.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (userPath.equals("/editCategory")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Category/editCategory.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (userPath.equals("/deleteCategory")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Category/deleteCategory.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AllCategory.jsp");
            dispatcher.forward(request, response);

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
        Category category = new Category();

        String userPath = request.getServletPath();
        if (userPath.equals("/addCategory")) {

            String catName = request.getParameter("catName");
            category.setCatName(catName);

            try {
                cdao.insertCategory(category);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                
                request.getRequestDispatcher("/WEB-INF/AllCategory.jsp?code=addSuccess&type=Category&id=" + category.getCatName()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/editCategory")) {
            int catId = Integer.parseInt(request.getParameter("catId"));
            String catName = request.getParameter("catName");

            category.setCatId(catId);
            category.setCatName(catName);

            try {
                cdao.updateCategory(category);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                request.getRequestDispatcher("/WEB-INF/AllCategory.jsp?code=editSuccess&type=Category&id=" + category.getCatId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/deleteCategory")) {
            int catId = Integer.parseInt(request.getParameter("catId"));

            category.setCatId(catId);

            try {
                cdao.deleteCategory(category);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                request.getRequestDispatcher("/WEB-INF/AllCategory.jsp?code=deleteSuccess&type=Category&id=" + category.getCatId()).forward(request, response);
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
