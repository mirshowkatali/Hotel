<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="row">

<div class="col-xs-6 col-md-6"><img src="<c:url value="/static/images/rooms/${room.images[0].name}"  />"
                                                     class="img-fluid" alt=""
                                                     style="height: 150px; max-width: 100%; object-fit: cover; -o-object-fit: cover;"></div>

</div>






<br><jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>