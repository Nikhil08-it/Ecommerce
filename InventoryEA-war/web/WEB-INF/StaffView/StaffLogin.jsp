<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Login</title>
        <jsp:include page="staffincludes/head.jsp" />

    </head>
    <%
        if (request.getParameter("code") != null) {

            if (request.getParameter("code").equals("failLogin")) {

    %>
    <jsp:include page="staffincludes/modalAdminLoginFail.jsp" />  
    <%                            }//END IF
        }//END IF

    %>


    <body style="background: linear-gradient(to right, #FF5370, #ff869a);">
        <div class="container">
            <div class="row" style="margin-top:20px;">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card border-0 shadow rounded-3 my-5">
                        <div class="card-body p-4 p-sm-5">
                            <div class="text-center">
                                <img src="${pageContext.request.contextPath}/img/logo.png" height="90" alt="" loading="lazy" />
                                <h5 class="lead" style="margin-top:20px;">Inventory Management System</h5>
                            </div>
                            <hr>


                            <form action="loginStaff" method="post">
                                <h5 style="margin-top:40px; margin-bottom: 20px;" class="card-title lead text-center fw-light fs-5">Staff Login</h5>
                                <div class="mb-3">
                                    <input required type="number" value="1" class="form-control" placeholder="Staff ID" name="staffId">
                                </div>

                                <div class="mb-5">
                                    <input required type="password" value="Test1" class="form-control" placeholder="Password" name="staffPass">
                                </div>

                                <div class="mb-5 d-grid gap-2">
                                    <button type="submit" class="btn btn-dark">Sign in</button>
                                </div>
                                <hr>

                                <p class="text-center text-muted">© 2022 IMS</p>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
