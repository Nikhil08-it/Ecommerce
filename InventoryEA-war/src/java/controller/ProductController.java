package controller;

import dao.ProductDAO;
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
import model.Product;


@WebServlet(name = "Product", urlPatterns = {"/Product", "/addProduct", "/editProduct", "/editProduct", "/deleteProduct", "/StaffProduct", "/restockProduct"})
public class ProductController extends HttpServlet {

    @EJB
    private ProductDAO pdao;

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

        String userPath = request.getServletPath();

        if (userPath.equals("/addProduct")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Products/addProduct.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (userPath.equals("/editProduct")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Products/editProduct.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (userPath.equals("/deleteProduct")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Products/deleteProduct.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        if (userPath.equals("/Product")) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AllProduct.jsp");
            dispatcher.forward(request, response);
        }

        if (userPath.equals("/StaffProduct")) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/StaffView/StaffProduct.jsp");
            dispatcher.forward(request, response);
        }

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
        Product product = new Product();

        String userPath = request.getServletPath();
        if (userPath.equals("/addProduct")) {

            String prodName = request.getParameter("prodName");
            String prodQty = request.getParameter("prodQty");
            String prodPrice = request.getParameter("prodPrice");
            String prodUnit = request.getParameter("prodUnit");
            String prodAdded = request.getParameter("prodAdded");
            String prodCategory = request.getParameter("prodCategory");

            product.setProdName(prodName);
            product.setProdQty(prodQty);
            product.setProdPrice(prodPrice);
            product.setProdUnit(prodUnit);
            product.setProdAdded(prodAdded);
            product.setProdCategory(prodCategory);

            try {
                pdao.insertProduct(product);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllProduct.jsp?code=addSuccess&type=Product&id=" + product.getProdName()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/editProduct")) {

            String prodId = request.getParameter("prodId");
            String prodName = request.getParameter("prodName");
            String prodQty = request.getParameter("prodQty");
            String prodPrice = request.getParameter("prodPrice");
            String prodUnit = request.getParameter("prodUnit");
            String prodAdded = request.getParameter("prodAdded");
            String prodCategory = request.getParameter("prodCategory");

            product.setProdId(prodId);
            product.setProdName(prodName);
            product.setProdQty(prodQty);
            product.setProdPrice(prodPrice);
            product.setProdUnit(prodUnit);
            product.setProdAdded(prodAdded);
            product.setProdCategory(prodCategory);

            try {
                pdao.updateProduct(product);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllProduct.jsp?code=editSuccess&type=Product&id=" + product.getProdId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/deleteProduct")) {

            String prodId = request.getParameter("prodId");
            product.setProdId(prodId);

            try {
                pdao.deleteProduct(product);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {

                request.getRequestDispatcher("/WEB-INF/AllProduct.jsp?code=deleteSuccess&type=Product&id=" + product.getProdId()).forward(request, response);
            }//END TRY-CATCH

        } else if (userPath.equals("/restockProduct")) {

            String prodId = request.getParameter("prodid");
            String prodQty = request.getParameter("prodQty");

            product.setProdId(prodId);
            product.setProdQty(prodQty);

            try {
               pdao.updateProductQty(product);
            } catch (SQLException e) //CATCH EXCEPTION
            {
            } finally {
                response.sendRedirect("StaffProduct?code=restockSuccess");
                
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
