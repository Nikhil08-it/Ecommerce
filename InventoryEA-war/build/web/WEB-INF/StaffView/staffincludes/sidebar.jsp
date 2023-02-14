<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<%@ page import="javax.ejb.*" %>
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-dark text-light">
    <div class="position-sticky">
        <div class="list-group list-group-flush mx-3 mt-4">
            <a id="dashboard" href="StaffDashboard" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light" aria-current="true">
                <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Dashboard</span>
            </a>

            <a id="product" href="StaffProduct" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light"><i
                    class="fas fa-box fa-fw me-3"></i><span>Product</span></a>
            <a id="orders" href="StaffOrder" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light"><i
                    class="fas fa-chart-line fa-fw me-3"></i><span>Orders</span></a>
            <a id="staff" href="StaffProfile" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light">
                <i class="fas fa-user fa-fw me-3"></i><span>Staff Profile</span>
            </a>

        </div>
    </div>
    
        <%
    String adminsession = (String) session.getAttribute("staffid");
    Staff staff = new Staff();
    StaffDAO sdao = new StaffDAOImpl();
    staff = sdao.selectStaff(Integer.parseInt(adminsession));
    
    if (adminsession != null) {   
    %>
    <div class="fixed-bottom mx-3 mt-4">
        
        <small>Holla, <% out.print(staff.getStaffFname() + " " + staff.getStaffLname() + " (#ID" + staff.getStaffId() +")"); %></small>
        <br>
        <small>Logged in as : Staff</small>
    </div>
    
    <% } %>
    
    
    
</nav>


<script>
    var path = window.location.pathname;
    var page = path.split("/").pop();
    console.log(page);

    if (page == "StaffDashboard") {
        document.getElementById("dashboard").classList.add("bg-danger");
        document.getElementById("dashboard").classList.remove("bg-dark");
    }

    if (page == "StaffProduct") {
        document.getElementById("product").classList.add("bg-danger");
        document.getElementById("product").classList.remove("bg-dark");
    }

    if (page == "StaffProfile") {
        document.getElementById("staff").classList.add("bg-danger");
        document.getElementById("staff").classList.remove("bg-dark");
    }

    if (page == "StaffOrder" || page == "viewStaffOrder" || page == "editOrderProduct" || page == "updatePayOrder" || page == "deleteOrderProduct" ) {
        document.getElementById("orders").classList.add("bg-danger");
        document.getElementById("orders").classList.remove("bg-dark");
    }
</script>