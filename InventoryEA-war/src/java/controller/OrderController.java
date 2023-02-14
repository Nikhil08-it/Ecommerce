/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;
import dao.OrderDAO;
import dao.OrdersProductDAO;
import dao.ProductDAO;
import javax.ejb.*;
import model.Orders;
import model.OrdersProduct;
import model.Product;

/**
 *
 * @author arnab
 */
@WebServlet(name = "Order",
        urlPatterns = {"/Order",
            "/viewOrder",
            "/deleteOrderProduct",
            "/editOrderProduct",
            "/addOrderProduct",
            "/updatePayOrder",
            "/addOrder",
            "/StaffOrder",
            "/viewStaffOrder",
            "/addStaffOrderProduct",
            "/deleteStaffOrderProduct",
            "/updateStaffPayOrder",
            "/editStaffOrderProduct", 
            "/addStaffOrder"})
public class OrderController extends HttpServlet {

    @EJB
    private OrdersProductDAO opdao;

    @EJB
    private OrderDAO odao;

    @EJB
    private ProductDAO pdao;

    OrdersProduct op = new OrdersProduct();
    Orders orders = new Orders();
    Product product = new Product();

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
        if (userPath.equals("/viewOrder")) {

            try {
                request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/Order")) {

            try {
                request.getRequestDispatcher("WEB-INF/AllOrder.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/deleteOrderProduct")) {
            int opid = Integer.parseInt(request.getParameter("opid"));
            int orderid = Integer.parseInt(request.getParameter("orderid"));

            try {
                op = opdao.selectOrdersProduct(opid);
                product = pdao.selectProduct(op.getProdId());
                int currentprodQty = Integer.parseInt(product.getProdQty());
                int newprodQty = op.getOrderQty() + currentprodQty;
                product.setProdQty(String.valueOf(newprodQty));

                //UPDATE PRODUCT QTY
                pdao.updateProductQty(product);
                //DELETE OP
                op.setOrderprodId(opid);
                opdao.deleteOrdersProduct(op);
                try {
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/deleteStaffOrderProduct")) {
            int opid = Integer.parseInt(request.getParameter("opid"));
            int orderid = Integer.parseInt(request.getParameter("orderid"));

            try {
                op = opdao.selectOrdersProduct(opid);
                product = pdao.selectProduct(op.getProdId());
                int currentprodQty = Integer.parseInt(product.getProdQty());
                int newprodQty = op.getOrderQty() + currentprodQty;
                product.setProdQty(String.valueOf(newprodQty));

                //UPDATE PRODUCT QTY
                pdao.updateProductQty(product);
                //DELETE OP
                op.setOrderprodId(opid);
                opdao.deleteOrdersProduct(op);
                try {
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/updatePayOrder")) {
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            String orderstatus = request.getParameter("status");

            orders.setOrderId(orderid);
            orders.setOrderStatus(orderstatus);

            try {
                odao.updatePayOrders(orders);

                try {
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/updateStaffPayOrder")) {
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            String orderstatus = request.getParameter("status");

            orders.setOrderId(orderid);
            orders.setOrderStatus(orderstatus);

            try {
                odao.updatePayOrders(orders);

                try {
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/StaffOrder")) {

            try {
                request.getRequestDispatcher("/WEB-INF/StaffView/StaffOrder.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF

        if (userPath.equals("/viewStaffOrder")) {

            try {
                request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }//END IF
        
        if (userPath.equals("/addStaffOrder")) {

            int orderCreatedby = Integer.parseInt(request.getParameter("staffid"));

            orders.setOrderCreatedby(orderCreatedby);

            try {
                odao.insertOrders(orders);

                try {
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + odao.getlastOrderID()).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
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
        String userPath = request.getServletPath();
        if (userPath.equals("/editOrderProduct")) {

            int opid = Integer.parseInt(request.getParameter("opid"));
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            int opQty = Integer.parseInt(request.getParameter("opQty"));

            try {

                //GET PRODUCT QTY
                product = pdao.selectProduct(prodid);
                op = opdao.selectOrdersProduct(opid);
                int currentProdQty = Integer.parseInt(product.getProdQty());
                int currentOPQty = op.getOrderQty();
                int newOPQty = currentOPQty - opQty;
                int newProdQty = currentProdQty + newOPQty;

                int calcProdQty = opQty - currentOPQty;
                System.out.println("newOPQty: " + (opQty - currentOPQty));
                System.out.println("currentProdQty: " + currentProdQty);

                if (calcProdQty > currentProdQty) {
                    //PRODUCT QTY EXCEED
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid + "&action=exceed").forward(request, response);
                } else {
                    System.out.println("Ok");
                    //UPDATE OP QTY
                    op.setOrderprodId(opid);
                    op.setOrderQty(opQty);
                    opdao.updateOrdersProductQty(op);

                    //UPDATE PRODUCT QTY
                    product.setProdId(String.valueOf(prodid));
                    product.setProdQty(String.valueOf(newProdQty));
                    pdao.updateProductQty(product);

                }

                try {
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (userPath.equals("/addOrderProduct")) {

            int orderid = Integer.parseInt(request.getParameter("orderid"));
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            int opQty = Integer.parseInt(request.getParameter("opQty"));

            op.setOrderId(orderid);
            op.setProdId(prodId);
            op.setOrderQty(opQty);

            try {

                if (opdao.selectexistOrdersProduct(op)) {
                    //Existing Orders
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid + "&action=exist").forward(request, response);
                } else {

                    //GET PRODUCT QTY
                    product = pdao.selectProduct(prodId);
                    int currentQty = Integer.parseInt(product.getProdQty());

                    if (opQty > currentQty) {
                        //PRODUCT QTY EXCEED
                        request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid + "&action=exceed").forward(request, response);
                    } else {
                        //CALC NEW PRODUCT QTY
                        int newQty = currentQty - opQty;

                        //UPDATE QTY
                        product.setProdQty(Integer.toString(newQty));
                        pdao.updateProductQty(product);

                        //INSERT ORDERS PRODUCT
                        opdao.insertOrdersProduct(op);
                    }

                }

                try {
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (userPath.equals("/addOrder")) {

            int orderCreatedby = Integer.parseInt(request.getParameter("orderCreatedby"));

            orders.setOrderCreatedby(orderCreatedby);

            try {
                odao.insertOrders(orders);

                try {
                    request.getRequestDispatcher("/WEB-INF/Orders/viewOrders.jsp?orderid=" + odao.getlastOrderID()).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (userPath.equals("/addStaffOrderProduct")) {

            int orderid = Integer.parseInt(request.getParameter("orderid"));
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            int opQty = Integer.parseInt(request.getParameter("opQty"));

            op.setOrderId(orderid);
            op.setProdId(prodId);
            op.setOrderQty(opQty);

            try {

                if (opdao.selectexistOrdersProduct(op)) {
                    //Existing Orders
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid + "&action=exist").forward(request, response);
                } else {

                    //GET PRODUCT QTY
                    product = pdao.selectProduct(prodId);
                    int currentQty = Integer.parseInt(product.getProdQty());

                    if (opQty > currentQty) {
                        //PRODUCT QTY EXCEED
                        request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid + "&action=exceed").forward(request, response);
                    } else {
                        //CALC NEW PRODUCT QTY
                        int newQty = currentQty - opQty;

                        //UPDATE QTY
                        product.setProdQty(Integer.toString(newQty));
                        pdao.updateProductQty(product);

                        //INSERT ORDERS PRODUCT
                        opdao.insertOrdersProduct(op);
                    }

                }

                try {
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (userPath.equals("/editStaffOrderProduct")) {

            int opid = Integer.parseInt(request.getParameter("opid"));
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            int opQty = Integer.parseInt(request.getParameter("opQty"));

            try {

                //GET PRODUCT QTY
                product = pdao.selectProduct(prodid);
                op = opdao.selectOrdersProduct(opid);
                int currentProdQty = Integer.parseInt(product.getProdQty());
                int currentOPQty = op.getOrderQty();
                int newOPQty = currentOPQty - opQty;
                int newProdQty = currentProdQty + newOPQty;

                int calcProdQty = opQty - currentOPQty;
                System.out.println("newOPQty: " + (opQty - currentOPQty));
                System.out.println("currentProdQty: " + currentProdQty);

                if (calcProdQty > currentProdQty) {
                    //PRODUCT QTY EXCEED
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid + "&action=exceed").forward(request, response);
                } else {
                    System.out.println("Ok");
                    //UPDATE OP QTY
                    op.setOrderprodId(opid);
                    op.setOrderQty(opQty);
                    opdao.updateOrdersProductQty(op);

                    //UPDATE PRODUCT QTY
                    product.setProdId(String.valueOf(prodid));
                    product.setProdQty(String.valueOf(newProdQty));
                    pdao.updateProductQty(product);

                }

                try {
                    request.getRequestDispatcher("/WEB-INF/StaffView/viewStaffOrder.jsp?orderid=" + orderid).forward(request, response);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

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
