<%@page import="dao.AdminDAO"%>
<%@page import="dao.AdminDAOImpl"%>
<%@page import="model.Admin"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="../includes/upperpart.jsp" />
<title>Edit Administrator</title>
<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">
    <h2>Edit Administrator</h2>
    <hr>

    <a class="btn btn-primary" href="Admin" role="button">< Back</a>
    <br><br>

    <div class="card border">
        <div class="card-body">
            <%
                Admin admin = new Admin();
                AdminDAO adao = new AdminDAOImpl();
                admin = adao.selectAdmin(Integer.parseInt(request.getParameter("id")));

            %>

            <form action="editAdmin" method="post">
                <p>ID:<input readonly  class="form-control text-secondary" type="text" name="adminId" value="<% out.print(admin.getAdminId()); %>" /></p>
                <p>First name:<input required class="form-control" type="text" name="adminFname" value="<% out.print(admin.getAdminFname()); %>" /></p>
                <p>Last name:<input required class="form-control" type="text" name="adminLname" value="<% out.print(admin.getAdminLname()); %>" /></p>
                <p>Username:<input required class="form-control" type="text" name="adminUsername" value="<% out.print(admin.getAdminUsername()); %>" /></p>
                <p>Password:<input required disabled class="form-control" type="password" name="adminPass" value="<% out.print(admin.getAdminPass());%>" /></p>
                <button type="submit" class="btn btn-success">Save</button>
            </form>

        </div>
    </div>

</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  