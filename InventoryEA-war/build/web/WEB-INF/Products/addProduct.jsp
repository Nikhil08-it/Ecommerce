<%@page import="model.Category"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@ page import="javax.ejb.*" %>
<title>Add Product</title>
<jsp:include page="../includes/upperpart.jsp" />  
<!-- Section: Content -->

<section class="animate__animated animate__fadeIn">


    <h2>Add Product</h2>
    <hr>

    <a class="btn btn-primary" href="Product" role="button">< Back</a>
    <br><br>

    <div class="card border">
        <div class="card-body">

            <form action="addProduct" method="post">
                <p>Product Name:<input required type="text" value="" class="form-control" name="prodName"></p>
                <p>Quantity:<input required type="number" min="0" value="" class="form-control" name="prodQty"></p>
                <p>Price:<input required type="number" min="0" step="0.01" value="" class="form-control" name="prodPrice"></p>
                <p>Unit:<input required type="text" value="" class="form-control" name="prodUnit"></p>
                <input hidden readonly type="text" value="1" class="form-control" name="prodAdded">
                <p>Category:
                    <select class="form-select" name="prodCategory">
                        <%
                        CategoryDAO cdao = new CategoryDAOImpl();
                        for (Category category : cdao.selectAllCategory()) { %>
                        <option value='<% out.println(category.getCatId()); %>'><% out.println(category.getCatName()); %></option>
                        <%
                            }
                        %>
                    </select>
                    <br>
                    <button type="submit" class="btn btn-success">Add</button>   
            </form>
        </div>
    </div>

</section>

<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  