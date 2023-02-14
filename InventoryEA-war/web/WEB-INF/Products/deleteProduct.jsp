<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@page import="model.Category"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@ page import="javax.ejb.*" %>
<title>Delete Product</title>
<jsp:include page="../includes/upperpart.jsp" />  
<!-- Section: Content -->

<section class="animate__animated animate__fadeIn">

<h2>Delete Product</h2>

<hr>
<a class="btn btn-primary" href="Product" role="button">< Back</a>
<br><br>
<div class="card border border-danger">
    <div class="card-body">
        <div class="alert alert-danger animate__animated animate__shakeX" role="alert">
            <i class="fas fa-exclamation-triangle"></i>&nbsp;
            Are you sure to delete the following product ?
        </div>

        <%
            Product product = new Product();
            ProductDAO pdao = new ProductDAOImpl();
            product = pdao.selectProduct(Integer.parseInt(request.getParameter("id")));
            double price = Double.parseDouble(product.getProdPrice());
        %>


        <form action="deleteProduct" method="post">
            <p>Product ID:<input readonly type="text" value="<% out.println(product.getProdId()); %>" class="form-control text-danger" name="prodId"></p>
            <p>Product Name:<input readonly required type="text" value="<% out.println(product.getProdName()); %>" class="form-control" name="prodName"></p>
            <p>Quantity:<input readonly required type="number" min="0" value="<% out.print(product.getProdQty()); %>" class="form-control" name="prodQty"></p>
            <p>Price:<input readonly required type="number" min="0" step="0.01" value="<% out.print(String.format("%.2f", price)); %>" class="form-control" name="prodPrice"></p>
            <p>Unit:<input readonly required type="text" value="<% out.print(product.getProdUnit()); %>" class="form-control" name="prodUnit"></p>
            <input hidden readonly readonly type="text" value="1" class="form-control" name="prodAdded">
            <p>Category:
                <select disabled class="form-select" name="prodCategory">
                    <%
                        CategoryDAO cdao = new CategoryDAOImpl();
                        for (Category category : cdao.selectAllCategory()) {
                            String selected = "";
                            String categoryId = Integer.toString(category.getCatId());
                            String productCategory = product.getProdCategory();
                            if (productCategory.equals(categoryId)) {
                                selected = "selected";

                            } else {
                                selected = "";

                            }

                    %>
                    <option <% out.print(selected); %> value='<% out.println(category.getCatId()); %>'><% out.println(category.getCatName()); %></option>
                    <%
                        }
                    %>
                </select>
                <br>
                <button type="submit" class="btn btn-danger">Delete</button>   
        </form>

    </div>
</div>
</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  