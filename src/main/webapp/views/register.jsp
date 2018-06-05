<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="style.css">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="<c:url value='/static/js/user-validations.js' />"></script>
</head>

<body class="background">

<jsp:include page="includes/header.jsp"></jsp:include>

<section id="register" >
<div class="container-fluid">
			<div class="row">
    <form:form id="regForm" action="regis" method="post" modelAttribute="user">
        <form:input type="hidden" path="id" id="id"/>
        <div>
           	<h1 class="heading h1-responsive material-red center-div white-text card-header">SIGN UP</h1>

            
               	<div class="form-group">
							<label for="firstName" class="cols-md-2 control-label">First Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                    <form:input type="text" onchange="firstNameChecker()" path="firstName" id="firstName" class="form-control input-lg" 
                               style="width: 200px;" placeholder="John" required="required"/>
                                </div>
                                </div>
                                
                </div>
                <div class="has-error">
                    <form:errors path="firstName" class="help-inline material-red-text "/>
                </div>
                </br>
                	<div class="form-group">
							<label for="lastName" class="cols-md-2 control-label">Last Name</label>
							<div class="cols-md-20">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                    <form:input type="text" onchange="lastNameChecker()" path="lastName" id="lastName" placeholder="Doe"
                       class="form-control input-lg"   style="width: 200px;"      required="required"/>
                                </div>
                                </div>
                </div>
                <div class="has-error">
                    <form:errors path="lastName" class="help-inline material-red-text "/>
                </div>
                </br>
                	<div class="form-group">
							<label for="username" class="cols-md-2 control-label">Your unique customer name</label>
							<div class="cols-md-20">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                             <form:input type="text" onchange="usernameChecker()" path="username" id="username" class="form-control input-lg"
                                placeholder="johndoe_199x" style="width: 200px;"
                                required="required"/>
                                </div>
                                </div>
                </div>
                <div class="has-error">
                    <form:errors path="username" class="help-inline material-red-text "/>
                    <span class="animated help-inline material-red-text " id="usernameerror"></span>
                </div>
                </br>
                <div class="form-group">
							<label for="email" class="cols-md-2 control-label">Your email</label>
							<div class="cols-md-20">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                       <form:input type="email" onchange="emailChecker()" path="email" id="email"
                      class="form-control input-lg"          placeholder="johndoe_199x@mail.com"
                      style="width: 200px;"          required="required"/>
                                </div>
                                </div>
                </div>
                <div class="has-error">
                    <form:errors path="email" class="help-inline material-red-text "/>
                    <span class="animated help-inline material-red-text " id="emailerror"></span>
                </div>
                </br>
                 <div class="form-group">
							<label for="password" class="cols-md-2 control-label">Your password</label>
							<div class="cols-md-20">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                       <form:input type="password" onchange="passwordChecker()" path="password" id="password"
                         class="form-control input-lg"  style="width: 200px;"     placeholder="Password"/>
                </div>
                </div>
                </div>
                <div class="has-error">
                    <form:errors path="password" class="help-inline material-red-text "/>
                </div>
                </br>
                 <div class="form-group">
							<label for="confirmPassword" class="cols-sm-2 control-label">Please confirm your password</label>
							<div class="cols-md-20">
								<div class="input-group">
									<span class="input-group-addon"><i class="ffa fa-lock fa-lg" aria-hidden="true"></i></span>
                     <input id="confirmPassword" onchange="checkFields()" required="required" type="password"
                    class="form-control input-lg"  style="width: 200px;"     placeholder="Confirm Password"/>
                </div>
                </div>
                </div>
                </br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </br>
                <div style="text-align: center">
                    <input class="btn btn-primary waves-button waves-light" style="width: 240px; height: 40px"
                           type="submit" value="Sign up"/>
                </div>
                </br>
                <div class="change_link" style="text-align: center">
                    Already a member ? </br>
                    <a href="/hms/login" class="to_register"> Login Instead </a>
                </div>
            
        </div>
            </form:form>
            
            </div>
            </div>
            
</section>
</body>
</html>