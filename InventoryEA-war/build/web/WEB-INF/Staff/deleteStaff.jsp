<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<%@ page import="javax.ejb.*" %>
<jsp:include page="../includes/upperpart.jsp" />
<title>Delete Staff</title>
<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">
<h2>Delete Staff</h2>
<hr>
<a class="btn btn-primary" href="Staff" role="button">< Back</a>
<br><br>
<%
    Staff staff = new Staff();
    StaffDAO sdao = new StaffDAOImpl();
    staff = sdao.selectStaff(Integer.parseInt(request.getParameter("id")));
%>
<div class="card border border-danger ">
    <div class="card-body">
        <div class="alert alert-danger animate__animated animate__shakeX" role="alert">
            <i class="fas fa-exclamation-triangle"></i>&nbsp;
            Are you sure to delete the following staff ?
        </div>
        <form action="deleteStaff" method="post">
            <br>
            <div class="form-group">
                <label style="font-size:15px;">Staff ID :</label>
                <input required readonly type="text" value="<% out.println(staff.getStaffId()); %>" class="form-control text-danger" required name="staffId">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">First Name :</label>
                <input disabled required type="text" value="<% out.println(staff.getStaffFname()); %>" class="form-control" required name="staffFname">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Last Name :</label>
                <input disabled required type="text" value="<% out.println(staff.getStaffLname()); %>" class="form-control" required name="staffLname">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Address :</label>
                <input disabled  required type="text" value="<% out.println(staff.getStaffAddress()); %>" class="form-control" required name="staffAddress">
            </div>
            <div class="form-group">
                <label style="font-size:15px;">Password :</label>
                <input disabled  required type="text" value="<% out.println(staff.getStaffPass());%>" class="form-control" required name="staffPass">
            </div>
            <br>
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>

</section>
<!-- Section: Content -->
<jsp:include page="../includes/lowerpart.jsp" />  