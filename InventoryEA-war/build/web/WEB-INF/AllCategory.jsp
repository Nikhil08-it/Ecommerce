<%@page import="dao.CategoryDAOImpl"%>
<%@ page import="javax.ejb.*" %>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Category"%>


<jsp:include page="includes/upperpart.jsp" />
<title>Category</title>
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

<h2>Category</h2>
<hr>
<a href="addCategory" class="btn btn-primary" href="#!" role="button">
    + Add Category
</a>
<br><br>
<input type="search" placeholder="Search Category..." class="form-control search-input border border-dark" data-table="table-list"/>
<br>
<div class="table-responsive">
    <table class="table table-sm table-striped table-hover text-center table-list " style="font-size:15px;">
        <thead class="text-light bg-info">
            <tr>
                <th>ID</th>
                <th>Category Name</th>
                <th>Action</th>
            </tr>
        </thead>

        <%            CategoryDAO cdao = new CategoryDAOImpl();
            for (Category category : cdao.selectAllCategory()) {

                String editHyperlink = "/admin/editCategory";
                String editHref = "<a href=" + request.getContextPath() + editHyperlink + "?id=" + category.getCatId() + ">Edit</a>";

                out.println("<tr>");
                out.println("<td>" + category.getCatId() + "</td>");
                out.println("<td>" + category.getCatName() + "</td>");
                out.println("<td>");
        %> 
        <a class="btn text-white btn-sm btn-primary btn-floating" href="editCategory?id=<% out.println(category.getCatId()); %>" role="button">
            <i class="fas fa-edit"></i>
        </a>
        <a class="btn text-white btn-sm btn-danger btn-floating" href="deleteCategory?id=<% out.println(category.getCatId()); %>" role="button">
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


