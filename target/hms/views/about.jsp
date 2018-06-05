<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Us</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
</head>
<body class="white">
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="animated" style="height: 220px; position: relative; overflow-x: hidden; overflow-y: hidden">
    <img src="<c:url value="/static/images/roombanner.jpg" />" class="img-fluid banner" alt=""
         style="position: absolute">


    <h1 class="white-text container banner-text" style="position: absolute">About Us</h1>
    </div>   
<section style="margin-top: 10%">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <h2 class="h2-responsive font-weight-bold">About Hotel Management System</h2>
                <h4 class="h4-responsive">A 5-star Hotel. We provide family, executive and deluxe
                    type rooms.</h4>
                <button class="btn btn-default btn-lg" onclick="window.location='/hms/contact'">Get in Touch</button>
            </div>
            <div class="col-sm-4">
            </div>
        </div>
    </div>
    <br/>
    <div class="container-fluid bg-grey">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <h2 class="h2-responsive">Our Values</h2>
                <h4 class="h4-responsive"><strong>MISSION:</strong> Our mission is to make customers happy.</h4>
                <p><strong>VISION:</strong> Our vision is to provide best services.</p>
            </div>
        </div>
    </div>
    <div class="row-fluid">
	
	<div class="span3">
		<h3><span>R</span>ooms</h3>
		<a href="/hms/rooms"><img src="static/images/rooms.jpg" alt="" /></a>
		<p>View our range of availiable rooms and options</p>
	</div>		
	<div class="span3">
		<h3><span>S</span>ervices</h3>
		<a href="/hms/facilities"><img src="static/images/services.png" alt="" /></a>
		<p>We have a gym, swimming pool, golf course, and much more</p>
	</div>		
	<div class="span3">
		<h3><span>P</span>romotions</h3>
		<a href="/hms/tariff"><img src="static/images/promotions.png" alt="" /></a>
		<p>Stay five nights and get one night totally <b>FREE</b>!</p>
	</div>		
	<div class="span3">
		<h3><span>L</span>ocation</h3>
		<a href="#"><img src="static/images/location.jpg" alt="" /></a>
		<p>We're easily accessible - go anywhere quickly.</p>
	</div>		
</div>		
    
</section>
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
