<%@page import="dao.StaffDAOImpl"%>
<%@page import="dao.StaffDAO"%>
<%@page import="model.Staff"%>
<jsp:include page="staffincludes/upperpart.jsp" />
<%@ page import="javax.ejb.*" %>

<%   if (request.getParameter("code") != null) {

        if (request.getParameter("code").equals("editSuccess")) {

%>
<jsp:include page="staffincludes/modalEditProfileSuccess.jsp" />  
<%                            }//END IF
    }//END IF
%>

<!-- Section: Content -->
<section class="animate__animated animate__fadeIn">

    <h2>Staff Profile</h2>
    <hr>
    <%
        StaffDAO sdao = new StaffDAOImpl();
        Staff staff = new Staff();
        String adminsession = (String) session.getAttribute("staffid");
        if (adminsession != null) {

            staff = sdao.selectStaff(Integer.parseInt(adminsession));
        }

    %> 





    <div class="card border border-success">
        <div class="card-body">
            <form action="editProfileStaff" method="post">
                <br>
                <div class="form-group">
                    <label style="font-size:15px;">Staff ID :</label>
                    <input required readonly type="text" value="<% out.print(staff.getStaffId()); %>" class="form-control text-danger" required name="staffId">
                </div>
                <div class="form-group">
                    <label style="font-size:15px;">First Name :</label>
                    <input required type="text" value="<% out.print(staff.getStaffFname()); %>" class="form-control" required name="staffFname">
                </div>
                <div class="form-group">
                    <label style="font-size:15px;">Last Name :</label>
                    <input required type="text" value="<% out.print(staff.getStaffLname()); %>" class="form-control" required name="staffLname">
                </div>
                <div class="form-group">
                    <label style="font-size:15px;">Address :</label>
                    <input required type="text" value="<% out.print(staff.getStaffAddress()); %>" class="form-control" required name="staffAddress">
                </div>
                <br>
                <button id="btnShowHide" onclick="myFunction()" type="button" class="btn btn-danger">Show Password</button>
                <hr>

                <div style='display: none;' id="mypassword" class="form-group">
                    <label style="font-size:15px;">Password :</label>
                    <input required type="text" value="<% out.print(staff.getStaffPass());%>" class="form-control" required name="staffPass">
                </div>






                <br>
                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>

</section>
<!-- Section: Content -->

<script>
    function myFunction() {
        var x = document.getElementById("mypassword");

        if (x.style.display === "none") {
            x.style.display = "block";
            document.getElementById("btnShowHide").innerHTML = "Hide Password";
        } else {
            x.style.display = "none";
            document.getElementById("btnShowHide").innerHTML = "Show Password";
        }
    }

</script>

<jsp:include page="staffincludes/lowerpart.jsp" />  
