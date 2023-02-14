<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Category"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="../includes/upperpart.jsp" />  
<title>Delete Category</title>
<!-- Section: Content -->

<section class="animate__animated animate__fadeIn">


<h2>Delete Category</h2>
<hr>
<a class="btn btn-primary" href="Category" role="button">< Back</a>
<br><br>
<%
    Category category = new Category();
    CategoryDAO cdao = new CategoryDAOImpl();
    category = cdao.selectCategory(Integer.parseInt(request.getParameter("id")));
%>

<div class="card border border border-danger">
    <div class="card-body">
        <div class="alert alert-danger animate__animated animate__shakeX" role="alert">
            <i class="fas fa-exclamation-triangle"></i>&nbsp;
            Are you sure to delete the following category ?
        </div>
        <form action="deleteCategory" method="post">
            <div class="form-group">
                <label style="font-size:15px;">Category ID :</label>
                <input readonly type="text" value="<% out.println(category.getCatId()); %>" class="form-control text-danger" required name="catId">
            </div>
            <br>
            <div class="form-group">
                <label style="font-size:15px;">Category Name :</label>
                <input readonly type="text" value="<% out.println(category.getCatName());%>" class="form-control" required name="catName">
            </div>
            <br>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>

</section>

<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  