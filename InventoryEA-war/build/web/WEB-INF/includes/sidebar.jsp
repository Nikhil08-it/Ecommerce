<%@page import="dao.AdminDAOImpl"%>
<%@page import="dao.AdminDAO"%>
<%@page import="model.Admin"%>
<%@ page import="javax.ejb.*" %>
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-dark text-light">
    <div class="position-sticky">
        <div class="list-group list-group-flush mx-3 mt-4">
            <a id="dashboard" href="Dashboard" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light" aria-current="true">
                <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Dashboard</span>
            </a>
            <a id="category" href="Category" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light">
                <i class="fas fa-list fa-fw me-3"></i><span>Category </span>
            </a>
            <a id="product" href="Product" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light"><i
                    class="fas fa-box fa-fw me-3"></i><span>Product</span></a>
            <a id="orders" href="Order" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light"><i
                    class="fas fa-chart-line fa-fw me-3"></i><span>Orders</span></a>
            <a id="staff" href="Staff" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light">
                <i class="fas fa-user fa-fw me-3"></i><span>Staff</span>
            </a>
            <a id="admin" href="Admin" class="list-group-item list-group-item-action py-2 ripple bg-dark text-light">
                <i class="fas fa-user-shield fa-fw me-3"></i><span>Administrator</span>
            </a>

        </div>
    </div>
    
    <%
    String adminsession = (String) session.getAttribute("adminid");
    Admin admin = new Admin();
    AdminDAO adao = new AdminDAOImpl();
    admin = adao.selectAdmin(Integer.parseInt(adminsession));
    
    if (adminsession != null) {   
    %>
    <div class="fixed-bottom mx-3 mt-4">
        
        <label>Holla, <% out.print(admin.getAdminFname() + " " + admin.getAdminLname() + " (#ID" + admin.getAdminId() +")"); %></label>
        <br>
        <small>Logged in as : Administrator</small>
    </div>
    
    <% } %>
    
</nav>


<script>
    var path = window.location.pathname;
    var page = path.split("/").pop();
    console.log(page);

    if (page == "Category" || page == "addCategory" || page == "editCategory" || page == "deleteCategory") {
        document.getElementById("category").classList.add("bg-danger");
        document.getElementById("category").classList.remove("bg-dark");
    }

    if (page == "Dashboard") {
        document.getElementById("dashboard").classList.add("bg-danger");
        document.getElementById("dashboard").classList.remove("bg-dark");
    }

    if (page == "Product" || page == "addProduct" || page == "editProduct" || page == "deleteProduct") {
        document.getElementById("product").classList.add("bg-danger");
        document.getElementById("product").classList.remove("bg-dark");
    }

    if (page == "Admin" || page == "addAdmin" || page == "editAdmin" || page == "deleteAdmin") {
        document.getElementById("admin").classList.add("bg-danger");
        document.getElementById("admin").classList.remove("bg-dark");
    }

    if (page == "Staff" || page == "addStaff" || page == "editStaff" || page == "deleteStaff") {
        document.getElementById("staff").classList.add("bg-danger");
        document.getElementById("staff").classList.remove("bg-dark");
    }

    if (page == "Order" || page == "viewOrder" || page == "editOrderProduct" || page == "updatePayOrder" || page == "deleteOrderProduct" ) {
        document.getElementById("orders").classList.add("bg-danger");
        document.getElementById("orders").classList.remove("bg-dark");
    }
</script>