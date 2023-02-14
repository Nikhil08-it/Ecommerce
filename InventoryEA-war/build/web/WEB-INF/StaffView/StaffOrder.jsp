<%@page import="dao.OrdersProductDAOImpl"%>
<%@page import="dao.OrdersProductDAO"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.OrderDAO"%>
<%@page import="model.OrdersProduct"%>
<%@page import="model.Orders"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="staffincludes/upperpart.jsp" />
<jsp:include page="staffincludes/cardbg.jsp" />

<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">
        <%   String adminsession = (String) session.getAttribute("staffid");
    %>
    <h2>Order</h2>
    <hr>
    <a href="addStaffOrder?staffid=<%out.print(adminsession);%>" class="btn btn-primary ripple-surface" role="button">
        + New Order
    </a>
    <br><br>

    <%
        OrdersProductDAO opdao = new OrdersProductDAOImpl();
        OrderDAO odao = new OrderDAOImpl();
        double paidOrder = opdao.getStaffTotalOrders(Integer.parseInt(adminsession));
        double unpaidOrder = opdao.getStaffUnpaidOrders(Integer.parseInt(adminsession));
        int totalOrder = 0;
        for (Orders orders : odao.selectOrdersCreatedby(Integer.parseInt(adminsession))) {
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
                            <label style="font-size:25px;"><% out.println(totalOrder);%></label> 
                            <p class="mb-0">My Order(s)</p>
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
                            <label style="font-size:25px;">RM <% out.println(String.format("%,.2f", paidOrder));%></label> 
                            <p class="mb-0">Paid Order(s)</p>
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
                            <label style="font-size:25px;">RM <% out.println(String.format("%,.2f", unpaidOrder));%></label> 
                            <p class="mb-0">Unpaid Order(s)</p>
                        </div>
                    </div>

                </div>
            </div>
        </div> 

    </div>



    <br>

    <label class="text-muted">Viewing orders created by Staff ID#<% out.print(adminsession); %> </label>
    <br>
    <input type="search" placeholder="Search Orders..." class="form-control search-input border border-dark" data-table="table-list"/>
    <br>

    <div class="table-responsive">
        <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
            <thead class="text-light bg-info">
                <tr>
                    <th>Sales ID</th>
                    <th>Order Date</th>
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

                for (Orders orders : odao.selectOrdersCreatedby(Integer.parseInt(adminsession))) {
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
                    out.println("<td>" + totalProduct + "</td>");
                    out.println("<td><h5><label class='badge badge-primary'>x" + totalQty + "</label></td>");
                    out.println("<td>RM " + String.format("%.2f", totalPrice) + "</td>");
                    out.println("<td> <h5><label class='" + badge + "'>" + orders.getOrderStatus() + "</label></h5></td>");
                    out.println("<td>"); %>
            <a class="btn text-white btn-sm btn-primary " href="viewStaffOrder?orderid=<% out.println(orders.getOrderId()); %>" role="button">
                <i class="fa fa-eye"></i> View
            </a>
            <%
                    out.println("</td>");
                    out.println("</tr>");
                }


            %>
        </table>
    </div>


</section>
<!-- Section: Content -->
<br><br><br><br><br><br>
<jsp:include page="staffincludes/lowerpart.jsp" /> 