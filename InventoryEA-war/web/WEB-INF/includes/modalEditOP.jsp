<%@page import="model.OrdersProduct"%>
<%@page import="dao.OrdersProductDAOImpl"%>
<%@page import="dao.OrdersProductDAO"%>
<%@ page import="javax.ejb.*" %>
<%
    OrdersProductDAO opdao = new OrdersProductDAOImpl();
    OrdersProduct op = new OrdersProduct();
    int opid = Integer.parseInt(request.getParameter("opid"));
    op = opdao.selectOrdersProduct(opid);

%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script type="text/javascript">
    window.onload = function () {
        OpenBootstrapPopup();
    };
    function OpenBootstrapPopup() {
        $("#editOPModal").modal('show');
    }
</script>

<!-- Modal -->
<div class="modal fade" id="editOPModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title text-dark" id="exampleModalLabel">Edit Quantity</h5>

            </div>
            <form action="editOrderProduct" method="post">
                <div class="modal-body" >
                    <input hidden value="<% out.print(opid); %>" name="opid" type="text">
                    <input hidden value="<% out.print(op.getProdId()); %>" name="prodid" type="text">
                    <input hidden value="<% out.print(op.getOrderId()); %>" name="orderid" type="text">
                    <p>Quantity: <input min="0" type="number" value="<% out.print(op.getOrderQty());%>" class="form-control" name="opQty" placeholder="Quantity"></p>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Save</button>
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