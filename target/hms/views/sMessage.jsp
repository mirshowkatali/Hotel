<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Message</title>

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
                    <form action="send" method="post" autocomplete="on" style="min-width: 400px;">
                        

                        <p>
                            <textarea class="md-textarea" name="message" placeholder="Your message"></textarea>
                        </p>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <p>
                            <input class="btn btn-primary waves-button waves-light center-div-horizontal"
                                   style="width: 240px; height: 40px"
                                   type="submit" value="Send"/>
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
