<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success!</title>

    <jsp:include page="includes/head.jsp"></jsp:include>

    
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>

<main>
    <div class="container">
        <div class="alert alert-success lead">
            ${success}
        </div>
        </br>
        

		<span class="well floatRight">
			Go to <a href="<c:url value='/hms' />">Home</a>
		</span>
    </div>
</main>
</body>
</html>
