<jsp:include page="../includes/upperpart.jsp" />
<title>Add Administrator</title>
<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">

<h2>Add Administrator</h2>
<hr>

<a class="btn btn-primary" href="Admin" role="button">< Back</a>
<br><br>

<div class="card border">
    <div class="card-body">
        <form action="addAdmin" method="post">
            <p>First name:<input class="form-control" type="text" name="adminFname" value="" /></p>
            <p>Last name:<input class="form-control" type="text" name="adminLname" value="" /></p>
            <p>Username:<input class="form-control" type="text" name="adminUsername" value="" /></p>
            <p>Password:<input class="form-control" type="text" name="adminPass" value="" /></p>
            <button type="submit" class="btn btn-success">Add</button>
        </form>
        
    </div>
</div>

</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  