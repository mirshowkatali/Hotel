<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>

    <jsp:include page="includes/head.jsp"></jsp:include>

    <script type="text/javascript">
	function printpage() {
		window.print();
	}
</script>
</head>
<body class="background">
<jsp:include page="includes/header1.jsp"></jsp:include>

<section id="booking" style="margin-top: 5%">

    <form action="" method="POST" modelAttribute="booking"
               class="form-horizontal">
       
        <div>
            <h1 class="heading h1-responsive material-red center-div white-text card-header">PRINT ROOM</h1>


            <div class="form-group card card-block">

                <div class="grid">

                    <div>
                        <label for="people">Number of people</label>
                        <input id="people" value="${booking.people}"  type="text" />
                    </div>
                    
                    </br>
                    <label for="room">Room Type</label>

                    <div>
                       <input id="room" type="text" value="${booking.room.type.type}" />
                        
                    </div>
                    <label for="price">Price</label>
                    <div>
                       <input name="price" value="${booking.room.price}"  type="text" />
                        
                    </div>
                    
                    </br>
                    <div>
                        <label for="arrivalTime">Arrival Time</label>
                        <input name="arrivalTime" value="${booking.arrivalTime}" type="text" />
                    </div>
                    
                    </br>
                    <div>
                        <label for="departureTime">Departure Time</label>
                        <input  name="departureTime"  value="${booking.departureTime}" type="text" />
                    </div>
                    
                    </br>
                    <p class="info-text">Comments</p>

                    <div class="controls">
                        <textarea name="comment" class="md-textarea" id="comment">
                        ${booking.comment}</textarea>
                    </div>

                    </br>
                    
                    
                    <br/>

                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="grid center-div">
                    <c:choose>
                        <c:when test="${print}">
                            <button type="submit" value="Submit" onclick="printpage()"
                                    class="btn-primary material-red waves-button waves-light"
                                    style="width: 240px;">PRINT
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" value="Submit" onclick="printpage()"
                                    class="btn-primary material-red waves-button waves-light"
                                    style="width: 240px;">PRINT
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </form>
</section>
</body>
</html>
