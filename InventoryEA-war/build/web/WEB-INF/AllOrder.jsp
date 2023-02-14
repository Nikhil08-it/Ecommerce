<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@page import="model.Product"%>
<%@page import="dao.OrdersProductDAOImpl"%>
<%@page import="dao.OrdersProductDAO"%>
<%@page import="model.OrdersProduct"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<%@page import="model.Orders"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.OrderDAO"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="includes/cardbg.jsp" />
<jsp:include page="includes/upperpart.jsp" />
<title>Order</title>
<!-- Section: Content -->

<%
       
    
    
    
    if (request.getParameter("action") != null) {

        if (request.getParameter("action").equals("add")) {
%>
<jsp:include page="includes/modaladdOrder.jsp" />  
<%
        }

    }

%>

<section class="animate__animated animate__fadeIn">

    <h2>Order</h2>
    <hr>


    <a href="Order?action=add" class="btn btn-primary" href="#!" role="button">
        + New Order
    </a>
    <br><br>
    <%       
        OrdersProductDAO opdao = new OrdersProductDAOImpl();
        double TotalOrders = opdao.getTotalOrders();
        double UnpaidOrders = opdao.getUnpdaidOrders();

        OrderDAO odao = new OrderDAOImpl();
        int totalOrder = 0;
        for (Orders orders : odao.selectAllOrders()) {
            totalOrder += 1;
        }
    %>
    <div class="row text-light">
        <div class="col">
            <div class="card border text-center bg-c-green">
                <div class="card-body">

                    <div class="d-flex justify-content-between px-md-1">
                        <div class="align-self-center">
                            <i class="fas fa-file-invoice-dollar fa-3x"></i>
                        </div>
                        <div class="text-end">
                            <label style="font-size:25px;"><% out.print(totalOrder);%></label> 
                            <p class="mb-0">Total Order(s)</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="col">
            <div class="card border text-center bg-c-blue">
                <div class="card-body">


                    <div class="d-flex justify-content-between px-md-1">
                        <div class="align-self-center">
                            <i class="fas fa-chart-line fa-3x"></i>
                        </div>
                        <div class="text-end">
                            <label style="font-size:25px;">RM <% out.print(String.format("%,.2f", TotalOrders));%></label> 
                            <p class="mb-0">Confirmed Orders</p>
                        </div>
                    </div>

                </div>
            </div>
        </div> 

        <div class="col">
            <div class="card border text-center bg-c-pink">
                <div class="card-body">


                    <div class="d-flex justify-content-between px-md-1">
                        <div class="align-self-center">
                            <i class="fas fa-chart-line fa-3x"></i>
                        </div>
                        <div class="text-end">
                            <label style="font-size:25px;">RM <% out.print(String.format("%,.2f", UnpaidOrders));%></label> 
                            <p class="mb-0">Unpaid Orders</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>  
                            
    </div>

    <br>

    <input type="search" placeholder="Search Orders..." class="form-control search-input border border-dark" data-table="table-list"/>
    <br>
    <div class="table-responsive">
        <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
            <thead class="text-light bg-info">
                <tr>
                    <th>Sales ID</th>
                    <th>Order Date</th>
                    <th>Staff#</th>
                    <th>Total Product</th>
                    <th>Total Quantity</th>
                    <th>Total Price</th>
                    <th>Status</th>
                    <th>Action</th>

                </tr>
            </thead>
            <%
                Staff staff = new Staff();
                StaffDAO sdao = new StaffDAOImpl();
                Product product = new Product();
                ProductDAO pdao = new ProductDAOImpl();

                for (Orders orders : odao.selectAllOrders()) {
                    staff = sdao.selectStaff(orders.getOrderCreatedby());
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(orders.getOrderDate());
                    String getDate = new SimpleDateFormat("dd MMM yyyy").format(date);
                    String badge = "";
                    if (orders.getOrderStatus().equals("Paid")) {
                        badge = "badge badge-success";
                    } else {
                        badge = "badge badge-danger";
                    }
                    double totalPrice = 0;
                    int totalQty = 0;
                    int totalProduct = 0;
                    for (OrdersProduct op : opdao.selectAllOrdersProduct(orders.getOrderId())) {

                        product = pdao.selectProduct(op.getProdId());
                        double prodPrice = Double.parseDouble(product.getProdPrice());
                        double prodTotal = prodPrice * op.getOrderQty();
                        totalPrice += prodTotal;
                        totalQty += op.getOrderQty();
                        totalProduct += 1;
                    }

                    out.println("<tr>");
                    out.println("<td class='text-success'><b>SA-" + orders.getOrderId() + "</b></td>");
                    out.println("<td>" + getDate + "</td>");
                    out.println("<td>" +orders.getOrderCreatedby() + "</td>");
                    out.println("<td>" + totalProduct + "</td>");
                    out.println("<td><h5><label class='badge badge-primary'>x" + totalQty + "</label></td>");
                    out.println("<td>RM " + String.format("%.2f", totalPrice) + "</td>");
                    out.println("<td> <h5><label class='" + badge + "'>" + orders.getOrderStatus() + "</label></h5></td>");
                    out.println("<td>"); %>
            <a class="btn text-white btn-sm btn-primary " href="viewOrder?orderid=<% out.println(orders.getOrderId()); %>" role="button">
                <i class="fa fa-eye"></i> View
            </a>
            <%
                    out.println("</td>");
                    out.println("</tr>");
                }


            %>
        </table>
    </div>
        
        <br><br><br><br><br>
</section>
<!-- Section: Content -->
<jsp:include page="includes/lowerpart.jsp" />  