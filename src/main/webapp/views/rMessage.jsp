<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receive Message</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
</head>
<body class="background">
<jsp:include page="includes/header.jsp"></jsp:include>


<section id="contact">
    <div class="main-wrapper">
        <div>
            <div id="wrapper" class="center-div">
                <div id="register_div" class="card card-block span7 animate form"
                     style="padding-top: 20px; padding-bottom: 20px; min-height: 100%">
                    <form action="" method="post" autocomplete="on" style="min-width: 400px;">
                        

                        <p>
                            <textarea class="md-textarea" name="message" placeholder="Your message">The message is : ${msg}</textarea>
                        </p>

                       
                    </form>
                </div>

            </div>
        </div>
    </div>
</section>
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
