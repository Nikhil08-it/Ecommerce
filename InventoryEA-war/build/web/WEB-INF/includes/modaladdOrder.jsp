<%@page import="model.Staff"%>
<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@ page import="javax.ejb.*" %>
<%

%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script type="text/javascript">
    window.onload = function () {
        OpenBootstrapPopup();
    };
    function OpenBootstrapPopup() {
        $("#addOrderModal").modal('show');
    }
</script>

<!-- Modal -->
<div class="modal fade" id="addOrderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title text-dark" id="exampleModalLabel">Create New Order</h5>

            </div>

            <form action="addOrder" method="post">
                <div class="modal-body" >
                    
                    Assign new order to :
                    <select required class="form-select" name="orderCreatedby">
                        <%
                         StaffDAO sdao = new StaffDAOImpl();
                        for (Staff staff : sdao.selectAllStaff()) {
                            %>
                            <option value="<% out.print(staff.getStaffId()); %>"> <% out.print(staff.getStaffId() + " - " + staff.getStaffFname() + " " + staff.getStaffLname()); %></option>
                            <%
                        }
                        
                        %>
                    </select>

                    <br>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Continue</button>
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