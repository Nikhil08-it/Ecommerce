<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Category"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Product"%>
<%@page import="model.OrdersProduct"%>
<%@page import="dao.OrdersProductDAOImpl"%>
<%@page import="dao.OrdersProductDAO"%>
<%@page import="model.Orders"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.OrderDAO"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<jsp:include page="staffincludes/upperpart.jsp" />
<jsp:include page="staffincludes/cardbg.jsp" />
<%@ page import="javax.ejb.*" %>

<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">

    <h2 class=" animate__animated animate__fadeInDown">Dashboard</h2>
    <hr>

    <%
        ProductDAO pdao = new ProductDAOImpl();
        double totalInventory = pdao.getTotalInventory();
        OrdersProductDAO opdao = new OrdersProductDAOImpl();
        double TotalOrders = opdao.getTotalOrders();
        CategoryDAO cdao = new CategoryDAOImpl();
        OrderDAO odao = new OrderDAOImpl();

        int totalProducts = 0;
        int totalCategory = 0;
        int totalOrder = 0;
        for (Product product : pdao.selectAllProduct()) {
            totalProducts += 1;
        }

        for (Category category : cdao.selectAllCategory()) {
            totalCategory += 1;
        }

        for (Orders orders : odao.selectAllOrders()) {
            totalOrder += 1;
        }
    %>

    <div class="row text-light  animate__animated animate__fadeInDown">
        <div class="col">
            <div class="card border text-center bg-c-blue">
                <div class="card-body">
                    <div class="d-flex justify-content-between px-md-1">
                        <div class="align-self-center">
                            <i class="fas fa-box fa-3x"></i>
                        </div>
                        <div class="text-end">
                            <label style="font-size:25px;">RM <% out.print(String.format("%,.2f", totalInventory));%></label> 
                            <p class="mb-0">Inventory Value</p>
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
                            <label style="font-size:25px;">RM <% out.print(String.format("%,.2f", TotalOrders));%></label> 
                            <p class="mb-0">Orders Value</p>
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
                            <i class="fas fa-boxes fa-3x"></i>
                        </div>
                        <div class="text-end">
                            <label style="font-size:25px;"><% out.print(totalProducts);%></label> 
                            <p class="mb-0">Total Product(s)</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>


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
    </div>
    <br>





    <hr>

    <div class="row">
        <%

            List<String> myDate = new ArrayList<String>();
            List<String> myOrders = new ArrayList<String>();
            LocalDate date = LocalDate.now();
            for (int i = 0; i <= 5; i++) {
                LocalDate newDate = date.minusDays(i);
                myDate.add(newDate.toString());
                //out.println(myDate.get(i));

            }//END FOR

        %>


        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <canvas class=" animate__animated animate__fadeInUp" id="bar-chart" style="width:300px; height:100px;" ></canvas>
        <script>
            // Bar chart
            new Chart(document.getElementById("bar-chart"), {
                type: 'bar',
                data: {
                            labels: ['<%out.print(myDate.get(5));%>', '<%out.print(myDate.get(4));%>', '<%out.print(myDate.get(3));%>', '<%out.print(myDate.get(2));%>', '<%out.print(myDate.get(1));%>'],
                    datasets: [
                        {
                            label: "Orders by Date (RM)",
                            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
                                    data: [<% out.print(String.format("%.2f", opdao.getTotalOrdersbyDate(myDate.get(5)))); %>, <% out.print(String.format("%.2f", opdao.getTotalOrdersbyDate(myDate.get(4)))); %>, <% out.print(String.format("%.2f", opdao.getTotalOrdersbyDate(myDate.get(3)))); %>, <% out.print(String.format("%.2f", opdao.getTotalOrdersbyDate(myDate.get(2)))); %>, <% out.print(String.format("%.2f", opdao.getTotalOrdersbyDate(myDate.get(1))));%>]
                        }
                    ]
                },
                options: {
                    legend: {display: true},
                    title: {
                        display: true,
                        text: 'Total Sales for last 5 Days (RM)'
                    }
                }
            });
        </script>

    </div>
    <br>

    </div>



</section>
<!-- Section: Content -->

<jsp:include page="staffincludes/lowerpart.jsp" />  

