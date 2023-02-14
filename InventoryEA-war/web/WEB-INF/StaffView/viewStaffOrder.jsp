<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.OrderDAO"%>
<%@page import="model.Orders"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Category"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@page import="model.OrdersProduct"%>
<%@page import="dao.OrdersProductDAOImpl"%>
<%@page import="dao.OrdersProductDAO"%>

<jsp:include page="staffincludes/upperpart.jsp" />
<%@ page import="javax.ejb.*" %>

<%
    if (request.getParameter("action") != null) {

        if (request.getParameter("action").equals("add")) {

%>
<jsp:include page="staffincludes/modaladdProduct.jsp" />  
<%      }//END IF 

    if (request.getParameter("action").equals("exist")) {
%>
<jsp:include page="staffincludes/modalProductExist.jsp" />  
<%
    }

    if (request.getParameter("action").equals("exceed")) {
%>
<jsp:include page="staffincludes/modalQtyExceed.jsp" />  
<%
        }

        if (request.getParameter("action").equals("edit")) {

%>
<jsp:include page="staffincludes/modalEditOP.jsp" />  
<%                            }//END IF

    }//END IF

%>


<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">

    <h2>View Order</h2>
    <hr>
    <a class="btn btn-primary" href="StaffOrder" role="button">< Back</a>
    <br><br>



    <div class="card border border-success">
        <div class="card-body">

            <div class="row">
                <div class="col">

                    <%                        OrdersProductDAO opdao = new OrdersProductDAOImpl();
                        Product product = new Product();
                        ProductDAO pdao = new ProductDAOImpl();
                        Category category = new Category();
                        CategoryDAO cdao = new CategoryDAOImpl();
                        Orders orders = new Orders();
                        OrderDAO odao = new OrderDAOImpl();
                        orders = odao.selectOrders(Integer.parseInt(request.getParameter("orderid")));
                        Staff staff = new Staff();
                        StaffDAO sdao = new StaffDAOImpl();
                        staff = sdao.selectStaff(orders.getOrderCreatedby());
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(orders.getOrderDate());
                        String getDate = new SimpleDateFormat("dd MMMM yyyy").format(date);
                        String badge = "";
                        if (orders.getOrderStatus().equals("Paid")) {
                            badge = "badge badge-success";
                        } else {
                            badge = "badge badge-danger";
                        }
                        double totalPrice = 0; %> 


                    <label class="lead">Order# : SA-<% out.println(request.getParameter("orderid")); %></label><br>
                    <label>Date : <% out.println(getDate); %></label><br>
                    <label>Created by : <% out.println(staff.getStaffFname() + " " + staff.getStaffLname()); %></label><br>
                    <label>Status :</label>&nbsp;  <label style="font-size:15px;" class="<% out.println(badge); %>"><% out.println(orders.getOrderStatus()); %></label><br><br>



                </div>


                <%
                    if (orders.getOrderStatus().equals("Unpaid")) {
                %> 

                <div class="col">
                    <div style="margin-right:60px;" class="text-end">
                        <a class="btn btn-success" href="viewStaffOrder?orderid=<% out.println(request.getParameter("orderid")); %>&action=add" role="button">+ Add Order Product</a><br><br>
                        <a class="btn btn-success" href="updateStaffPayOrder?orderid=<% out.print(request.getParameter("orderid")); %>&status=Paid" role="button">Mark as Paid</a>

                    </div>
                </div>

                <%
                    }
                %>    


            </div>

            <div class="table-responsive">
                <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
                    <thead class="text-light bg-info">
                        <tr>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Unit</th>
                            <th>Quantity</th>
                            <th>Total</th>
                                <% if (orders.getOrderStatus().equals("Unpaid")) { %>
                            <th>Action</th>
                                <% }%>

                        </tr>
                    </thead>


                    <%                        for (OrdersProduct op : opdao.selectAllOrdersProduct(Integer.parseInt(request.getParameter("orderid")))) {
                            product = pdao.selectProduct(op.getProdId());
                            category = cdao.selectCategory(Integer.parseInt(product.getProdCategory()));
                            double prodPrice = Double.parseDouble(product.getProdPrice());
                            double prodTotal = prodPrice * op.getOrderQty();
                            totalPrice += prodTotal;

                            out.println("<tr>");
                            out.println("<td><b>" + product.getProdName() + "</b></td>");
                            out.println("<td>" + category.getCatName() + "</td>");
                            out.println("<td>RM " + String.format("%.2f", prodPrice) + "</td>");
                            out.println("<td>" + product.getProdUnit() + "</td>");
                            out.println("<td><h5><label class='badge badge-primary'>x" + op.getOrderQty() + "</label></h5></td>");
                            out.println("<td>RM " + String.format("%.2f", prodTotal) + "</td>");
                            if (orders.getOrderStatus().equals("Unpaid")) {
                                out.println("<td>");

                    %>


                    <a class="btn text-white btn-sm btn-primary btn-floating" href="viewStaffOrder?&orderid=<% out.println(request.getParameter("orderid")); %>&opid=<% out.println(op.getOrderprodId()); %>&action=edit" role="button">
                        <i class="fas fa-edit"></i>
                    </a>

                    <a class="btn text-white btn-sm btn-danger btn-floating" href="deleteStaffOrderProduct?opid=<% out.println(op.getOrderprodId()); %>&orderid=<% out.println(request.getParameter("orderid")); %>" role="button">
                        <i class="fas fa-trash-alt"></i>
                    </a>

                    <%
                                out.println("</td>");
                            }//END IF
                            out.println("</tr>");
                        }

                        out.println("<tr class='bg-info'>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td></td>");
                        out.println("<td class='text-light'>Grand Total</td>");
                        out.println("<td class='text-light'><h5><label class='badge badge-pill badge-success'>RM" + String.format("%.2f", totalPrice) + "</label></h5></td>");
                        if (orders.getOrderStatus().equals("Unpaid")) {
                            out.println("<td></td>");
                        }
                        out.println("</tr>");
                    %>



            </div>
        </div>
    </div>




</section>
<!-- Section: Content -->   
<jsp:include page="staffincludes/lowerpart.jsp" />  