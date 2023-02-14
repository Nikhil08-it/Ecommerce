<%@page import="dao.ProductDAOImpl"%>
<%@page import="dao.ProductDAO"%>
<%@page import="model.Product"%>
<%@page import="model.Category"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.CategoryDAO"%>
<%@ page import="javax.ejb.*" %>
<title>Edit Product</title>
<jsp:include page="../includes/upperpart.jsp" />  
<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">

<h2>Edit Product</h2>

<hr>
<a class="btn btn-primary" href="Product" role="button">< Back</a>
<br><br>
<div class="card border">
    <div class="card-body">
        <%
            Product product = new Product();
            ProductDAO pdao = new ProductDAOImpl();
            product = pdao.selectProduct(Integer.parseInt(request.getParameter("id")));
            double price = Double.parseDouble(product.getProdPrice());
        %>


        <form action="editProduct" method="post">
            <input hidden type="text" value="<% out.println(product.getProdId()); %>" class="form-control" name="prodId">
            <p>Product Name:<input required type="text" value="<% out.println(product.getProdName()); %>" class="form-control" name="prodName"></p>
            <p>Quantity:<input required type="number" min="0" value="<% out.print(product.getProdQty()); %>" class="form-control" name="prodQty"></p>
            <p>Price:<input required type="number" min="0" step="0.01" value="<% out.print(String.format("%.2f", price)); %>" class="form-control" name="prodPrice"></p>
            <p>Unit:<input required type="text" value="<% out.print(product.getProdUnit()); %>" class="form-control" name="prodUnit"></p>
            <input hidden readonly type="text" value="1" class="form-control" name="prodAdded">
            <p>Category:
                <select class="form-select" name="prodCategory">
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
                <button type="submit" class="btn btn-success">Save</button>   
        </form>

    </div> 
</div>
</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  