<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <jsp:include page="includes/head.jsp"></jsp:include>

    <script type="text/javascript" src="<c:url value='/static/js/user-validations.js' />"></script>
</head>

<body class="background">
<jsp:include page="includes/header.jsp"></jsp:include>

<section class="form-dark" align="center">

    <!--Form without header-->
    <div class="card card-image" align="center" "background-image: url('https://mdbootstrap.com/img/Photos/Others/pricing-table7.jpg'); width: 28rem;">
        <div class="text-white rgba-stylish-strong py-5 px-5 z-depth-4">
	<form action="login" method="post" autocomplete="on">
            <!--Header-->
            <div class="text-center">
                <h3 class="white-text mb-5 mt-4 font-weight-bold"><strong>LOG</strong> <a class="green-text font-weight-bold"><strong> IN</strong></a></h3>
            </div>
            
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>

                <p>
                    <label for="username" data-icon="u"> Your username </label>
                    <input id="username" onchange="usernameChecker()" name="username" required="required" type="text"
                           placeholder="Enter username"/>
                </p>

                <p>
                    <label for="password" data-icon="p"> Your password </label>
                    <input id="password" onchange="passwordChecker()" name="password" required="required"
                           type="password" placeholder="Enter Password"/>
                </p>

                <p>
                    <label><input type="checkbox" id="rememberme" name="remember-me" class="checkbox" style="float: left; width: 15px">
                        &nbsp;&nbsp;&nbsp;Remember Me</label>
                </p>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <p class="flex-center">
                    <input class="btn btn-success btn-block btn-rounded z-depth-1"
                           style="width: 240px; height: 40px" type="submit" value="Login"/>
                </p>

                <p class="font-small white-text d-flex justify-content-end" style="text-align: center">
                    Not a member yet ? </br>
                    <a href="/hms/register" class="font-small blue-text d-flex justify-content-end">Join us</a>
                </p>
            </div>
        </div>
    </form>
</section>
<jsp:include page="includes/footer.jsp"></jsp:include>   
</body>
</html>



            






</body>
</html>