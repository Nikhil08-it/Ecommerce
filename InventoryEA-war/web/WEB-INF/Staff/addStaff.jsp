<jsp:include page="../includes/upperpart.jsp" />  
<title>Add Staff</title>
<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">
<h2>Add Staff</h2>
<hr>
<a class="btn btn-primary" href="Staff" role="button">< Back</a>
<br><br>
<div class="card border border-success">
    <div class="card-body">
        <form action="addStaff" method="post">
            <br>
            <div class="form-group">
                <label style="font-size:15px;">First Name :</label>
                <input required type="text" value="" class="form-control" required name="staffFname">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Last Name :</label>
                <input required type="text" value="" class="form-control" required name="staffLname">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Address :</label>
                <input required type="text" value="" class="form-control" required name="staffAddress">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Password :</label>
                <input required type="text" value="" class="form-control" required name="staffPass">
            </div>
            <br>
            <button type="submit" class="btn btn-success">Add</button>
        </form>
    </div>
</div>


</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  