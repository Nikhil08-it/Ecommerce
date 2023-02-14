<%@page import="model.Admin"%>
<%@page import="dao.AdminDAOImpl"%>
<%@page import="dao.AdminDAO"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="includes/upperpart.jsp" />  
<!-- Section: Content -->

<%

    
    if (request.getParameter("code") != null) {

            if (request.getParameter("code").equals("editSuccess")) {

                    %>
                    <jsp:include page="includes/modalEditSuccess.jsp" />  
                    <%
                
            }//END IF


            if (request.getParameter("code").equals("deleteSuccess")) {
                    %>
                    <jsp:include page="includes/modalDeleteSuccess.jsp" />  
                    <%
            }//END IF


            if (request.getParameter("code").equals("addSuccess")){
                    %>
                    <jsp:include page="includes/modalAddSuccess.jsp" />  
                    <%
            }//END IF

    }//END IF

%>



<section class="animate__animated animate__fadeIn">

    <h2>Administrator</h2>
    <hr>
    <a href="addAdmin" class="btn btn-primary" href="#!" role="button">
        + Add Administrator
    </a>

    <br><br>
    <input type="search" placeholder="Search Administrator..." class="form-control search-input border border-dark" data-table="table-list"/>
    <br>
    <div class="table-responsive">
        <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
            <thead class="text-light bg-info">
                <tr>
                    <th>Admin ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Action</th>
                </tr>
            </thead>

            <%
                AdminDAO adao = new AdminDAOImpl();
                for (Admin admin : adao.selectAllAdmin()) {
                    out.println("<tr>");
                    out.println("<td>" + admin.getAdminId() + "</td>");
                    out.println("<td>" + admin.getAdminFname() + "</td>");
                    out.println("<td>" + admin.getAdminLname() + "</td>");
                    out.println("<td>" + admin.getAdminUsername() + "</td>");
                    out.println("<td>****</td>");
                out.println("<td>"); %> 
            <a class="btn text-white btn-sm btn-primary btn-floating" href="editAdmin?id=<% out.println(admin.getAdminId()); %>" role="button">
                <i class="fas fa-edit"></i>
            </a>
            <a class="btn text-white btn-sm btn-danger btn-floating" href="deleteAdmin?id=<% out.println(admin.getAdminId()); %>" role="button">
                <i class="fas fa-trash-alt"></i>
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
<jsp:include page="includes/lowerpart.jsp" />  