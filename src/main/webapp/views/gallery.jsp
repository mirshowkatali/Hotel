<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Gallery</title>
<link rel="stylesheet" href="static/css/gallery-grid.css">   
    <jsp:include page="includes/head.jsp"></jsp:include>

<link rel="stylesheet" href="//blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<link rel="stylesheet" href="css/bootstrap-image-gallery.min.css">
    
</head>
<body class="white">
<jsp:include page="includes/header1.jsp"></jsp:include>
<div class="animated" style="height: 220px; position: relative; overflow-x: hidden; overflow-y: hidden">
    <img src="<c:url value="/static/images/roombanner.jpg" />" class="img-fluid banner" alt=""
         style="position: absolute">


    <h1 class="white-text container banner-text" style="position: absolute">Gallery</h1>


    
</div>

<div id="links" align="center">
	<a href="static/images/room1.jpg" title="Your Text is responsive and centered" data-gallery>
    
     
       
        <img src="static/images/room1_tn.jpg" alt="Room1">
  
                                  
        </a>
        
    <a href="static/images/room2.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/room2_tn.jpg" alt="Room2">
    </a>
    <a href="static/images/room3.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/room3_tn.jpg" alt="Room3">
    </a><br>
     <a href="static/images/service1.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/service1_tn.jpg" alt="Service1">
    </a>
    <a href="static/images/service2.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/service2_tn.jpg" alt="Service2">
    </a>
    <a href="static/images/service3.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/service3_tn.jpg" alt="Service3">
    </a><br>
     <a href="static/images/display1.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/display1_tn.jpg" alt="Display1">
    </a>
    <a href="static/images/display2.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/display2_tn.jpg" alt="Display2">
    </a>
    <a href="static/images/display3.jpg" title="Your Text is responsive and centered" data-gallery>
        <img src="static/images/display3_tn.jpg" alt="Display3">
    </a>
</div>    
<div id="blueimp-gallery" class="blueimp-gallery">
    <!-- The container for the modal slides -->
    <div class="slides"></div>
    <!-- Controls for the borderless lightbox -->
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
    <!-- The modal dialog, which will be used to wrap the lightbox content -->
    <div class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body next"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left prev">
                        <i class="glyphicon glyphicon-chevron-left"></i>
                        Previous
                    </button>
                    <button type="button" class="btn btn-primary next">
                        Next
                        <i class="glyphicon glyphicon-chevron-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<script src="js/bootstrap-image-gallery.min.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>   
</body>
</html>