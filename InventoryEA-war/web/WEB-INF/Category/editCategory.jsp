<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Category"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="../includes/upperpart.jsp" />
<title>Edit Category</title>
<!-- Section: Content -->

<section class="animate__animated animate__fadeIn">

<h2>Edit Category</h2>
<hr>
<a class="btn btn-primary" href="Category" role="button">< Back</a>
<br><br>
<%
Category category = new Category();
CategoryDAO cdao = new CategoryDAOImpl();
category = cdao.selectCategory(Integer.parseInt(request.getParameter("id")));
%>

        <div class="card border">
            <div class="card-body">
                <form action="editCategory" method="post">
                    <div class="form-group">
                        <label style="font-size:15px;">Category ID :</label>
                        <input readonly type="text" value="<% out.println(category.getCatId()); %>" class="form-control" required name="catId">
                    </div>
                    <br>
                    <div class="form-group">
                        <label style="font-size:15px;">Category Name :</label>
                        <input type="text" value="<% out.println(category.getCatName()); %>" class="form-control" required name="catName">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>


</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  