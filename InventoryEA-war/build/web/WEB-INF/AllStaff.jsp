<%@page import="model.Staff"%>
<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@ page import="javax.ejb.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="includes/upperpart.jsp" />  
<title>Staff</title>
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

    <h2>Staff</h2>
    <hr>
    <a href="addStaff" class="btn btn-primary" href="#!" role="button">
        + Add Staff
    </a>
    <br><br>
    <input type="search" placeholder="Search Category..." class="form-control search-input border border-dark" data-table="table-list"/>
    <br>
    <div class="table-responsive">
        <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
            <thead class="text-light bg-info">
                <tr>
                    <th>Staff ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Password</th>
                    <th>Action</th>
                </tr>
            </thead>


            <%
                StaffDAO sdao = new StaffDAOImpl();
                for (Staff staff : sdao.selectAllStaff()) {

                    out.println("<tr>");
                    out.println("<td>" + staff.getStaffId() + "</td>");
                    out.println("<td>" + staff.getStaffFname() + " " + staff.getStaffLname() + "</td>");
                    out.println("<td>" + staff.getStaffAddress() + "</td>");
                    out.println("<td>****</td>");
                    out.println("<td>");
            %>
            <a class="btn text-white btn-sm btn-primary btn-floating" href="editStaff?id=<% out.println(staff.getStaffId()); %>" role="button">
                <i class="fas fa-edit"></i>
            </a>
            <a class="btn text-white btn-sm btn-danger btn-floating" href="deleteStaff?id=<% out.println(staff.getStaffId()); %>" role="button">
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
