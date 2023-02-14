<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@ page import="javax.ejb.*" %>
<%

%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script type="text/javascript">
    window.onload = function () {
        OpenBootstrapPopup();
    };
    function OpenBootstrapPopup() {
        $("#addOPModal").modal('show');
    }
</script>

<!-- Modal -->
<div class="modal fade" id="addOPModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title text-dark" id="exampleModalLabel">Add Product to Order# SA-<% out.println(request.getParameter("orderid")); %></h5>

            </div>

            <%
                ProductDAO pdao = new ProductDAOImpl();
            %>
            <form action="addStaffOrderProduct" method="post">
                <div class="modal-body" >

                    <input required hidden value="<% out.print(request.getParameter("orderid")); %>" name="orderid" type="text">
                    Select Product:
                    <select required class="form-select" name="prodId">
                        <%
                            for (Product product : pdao.selectAllProduct()) {
                                 %>
                                 <option value="<% out.print(product.getProdId()); %>"><% out.print(product.getProdName() + " (RM" + String.format("%.2f", Double.parseDouble(product.getProdPrice())) + " / " + product.getProdUnit()+ ")"); %></option>
                                <% 
                            }
                        %>
                    </select>
                    <br>
                    <p>Quantity: <input required min="0" type="number" value="<% %>" class="form-control" name="opQty" placeholder="Quantity"></p>


                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Add</button>
                    <button type="button" class="btn btn-danger" data-mdb-dismiss="modal">Cancel</button>

                </div>
            </form>
        </div>
    </div>
</div>

<style>
    .center-lottie{
        position: absolute;
        top:35%;
        left: 50%;
        transform: translate(-50% , -50%)
    }
</style>