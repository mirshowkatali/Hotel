<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="style.css">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
    <script>
        function verifyPeople() {
            var check = document.getElementById('people').value;
            if (Number(check) < 0) {
                document.getElementById("people").style.borderColor = '#FF0013';
                document.getElementById("people").value = null;
            } else if (Number(check) > 6) {
                document.getElementById("people").value = 6;
            } else {
                document.getElementById("people").style.borderColor = '#A7FF00';
            }
        }
    </script>
</head>
<body class="background">
<jsp:include page="includes/header.jsp"></jsp:include>

<section id="booking" style="margin-top: 5%">

    <sec:authorize access="hasRole('UNVERIFIED')">
        <div class="flex-center">
            <div class="alert alert-info waves-effect" style="width: 500px">
                <strong>Kindly check your email for confirmation. Otherwise you will not be able to book rooms.</strong>
                <br/><br/>
                <a class="center-div-horizontal waves-button theme-black white-text"
                   href="/user/profile-${pageContext.request.userPrincipal.name}/resend">
                    Resend Confirmation</a>
            </div>
        </div>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('ADMIN', 'MANAGER', 'USER')">
     <div class="container-fluid" style="background-image:url('static/images/rooms/Room20.jpg'); height: 800px; width: 1500px; border: 1px solid black;">
			<div class="row">
    
        <form:form action="payU_Home" method="GET" modelAttribute="booking"
                   class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
            <form:hidden path="status"/>
           

                <h1 class="heading h1-responsive material-red center-div white-text card-header">BOOK A ROOM</h1>

                <div class="form-group card card-block">

                    <div class="grid">

                        <div class="form-group">
							<label for="people" class="cols-md-2 control-label">Number of People</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                           
                            <form:input id="people" path="people" onchange="verifyPeople()" type="number" style="width: 200px;"
                                        required="required"/>
                        </div>
                        </div>
                        </div>
                        <div class="has-error">
                            <form:errors path="people" class="help-inline material-red-text "/>
                        </div>
                        </br>
                        <div class="form-group">
							<label for="room" class="cols-md-2 control-label">Select Room Type</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-caret-down" aria-hidden="true"></i></span>
                             <div>
                            <form:select id="room" required="required" path="room"
                                         cssClass="input-block-level rgba-black-slight waves-input-wrapper"
                                         items="${rooms}"
                                         itemValue="id" style="width: 200px; font:black"
                                         itemLabel="name">
                            </form:select>
                            </div>
                            </div>
                            </div>
                            <div class="has-error">
                                <form:errors path="room" class="help-inline material-red-text "/>
                            </div>
                        </div>
                        </br>
                        <div class="form-group">
							<label for="arrivalTime" class="cols-md-2 control-label">Arrival Time</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-calendar fa" aria-hidden="true"></i></span>
                            <form:input path="arrivalTime" id="arrivalTime" style="width: 200px;" type="date" required="required"/>
                        </div>
                        </div>
                        </div>
                        
                        <div class="has-error">
                            <form:errors path="arrivalTime" class="help-inline material-red-text "/>
                        </div>
                        </br>
                        <div class="form-group">
							<label for="departureTime" class="cols-md-2 control-label">Departure Time</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-calendar fa" aria-hidden="true"></i></span>
                            <form:input path="departureTime" id="departureTime" style="width: 200px;" type="date" required="required"/>
                        </div>
                        </div>
                        </div>
                        <div class="has-error">
                            <form:errors path="departureTime" class="help-inline material-red-text "/>
                        </div>
                        </br>
                        <div class="form-group">
							<label for="comments" class="cols-md-2 control-label">Comments</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-pencil prefix" aria-hidden="true"></i></span>
                            <form:textarea path="comment" name="comment" style="width: 200px;" class="md-textarea" id="comment"/>
                            <label for="comment">Please describe your needs e.g. Extra mattress</label>
                        </div>
						</div>
						</div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="grid center-div">
                        <button type="submit" value="Submit"
                                class="btn-primary material-red waves-button waves-light"
                                style="width: 240px;">BOOK
                        </button>
                    </div>
                </div>
            
        </form:form>
        </div>
        </div>
        
    </sec:authorize>
</section>

</body>
</html>
