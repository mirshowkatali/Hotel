<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>HOTEL MANAGEMENT SYSTEM</title>

    <jsp:include page="includes/head.jsp"></jsp:include>
    
 
 
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" rel="stylesheet">  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		
		
  
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
    <link rel="stylesheet" href="static/css/search.css" data-main-css="1"  />
<link rel="stylesheet" href="static/css/search1.css"  />
<link rel="stylesheet" href="static/css/search2.css" /> 
 <link rel="stylesheet" href="static/css/search3.css" />
 
    <style>
        img {
            width: auto;
            height: auto;
            max-width: 100%;
        }
    </style>
</head>
<body class="background">
<jsp:include page="includes/header.jsp"></jsp:include>

<div id="display_carousel" class="carousel slide carousel-fade display_carousel" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#display_carousel" data-slide-to="0" class=""></li>
        <li data-target="#display_carousel" data-slide-to="1" class=""></li>
        <li data-target="#display_carousel" data-slide-to="2" class="active"></li>
    </ol>

    <div class="carousel-inner" role="listbox">
        <div class="carousel-item">
            <div id="display_image1" class="flex-center animated">
                <img class="carousel-item-img img-fluid center-block" src="<c:url value='/static/images/display1.jpg' />">
             
      <div class="carousel-caption">
        <h3>Room1</h3>
         <p>Room perfect for family vacations which includes entertainment facilities
                            like plasma TV, C.D, Stereo and DVD. These rooms provides all necessities for family.</p>
      </div>   
                
                
            </div>
        </div>

        <div class="carousel-item">
            <div id="display_image2" class="flex-center animated">
                <img class="carousel-item-img img-fluid center-block" src="<c:url value='/static/images/display2.jpg' />">
               <div class="carousel-caption">
        <h3>Room2</h3>
         <p>Hotel provides the entire world class premium services like plasma TV, C.D,
                            Stereo & DVD,work desks, phone and much more.</p>
      </div>   
                
                
            </div>
        </div>

        <div class="carousel-item active">
            <div id="display_image3" class="flex-center animated">
                <img class="carousel-item-img img-fluid center-block" src="<c:url value='/static/images/display3.jpg' />">
              <div class="carousel-caption">
        <h3>Room3</h3>
         <p>This include a range of luxurious rooms suitable for
                            its discerning visitors. The different kinds of rooms and suits include the Business suits
                            and Crown Suits.</p>
      </div>   
                
                
            </div>
        </div>
    </div>

    <a class="left carousel-control" href="#display_carousel" role="button" data-slide="prev">
        <span class="icon-prev" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#display_carousel" role="button" data-slide="next">
        <span class="icon-next" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
 <section>
    <div class="center-div-horizontal" style="margin-bottom: 30px; margin-top: 30px">
        <div class="col-md-4">
            <button id="add3room_button" type="button" onclick="window.location='/hms/booking'"
                    class="btn-danger waves-button waves-light material-red"
                    style="width: 200px; height: 50px; color: white; font-weight: 400">BOOK ROOM
            </button>
        </div>
        
    </div>
 </section>
 
 <section id="home" class="helpr-section helpr-layout-1 section section-inverse-color" >
 <form id="frm" method="get" action="/hms/rooms/search1">
  <div class="container">
  
    <div class="helpr-content" data-stellar-offset-parent="true"> 
      
      <!-- helpr TEXT -->
      
      <div class="helpr-text" data-wow-duration="1s" data-wow-delay="0.5s"> 
        <!-- TAGLINE -->
        <div class="webHomeTitle">
          <h1 class="helpr-title">Search any Room</h1>
        </div>
       
          
      
        <div class="home-service clearfix" align="center">
        <div class="wrapper-demo1">
            <div id="dd1" class="wrapper-dropdown-3" tabindex="1">
                                                            	
                           <select  name="searchtext">
								<option value="Deluxe" selected="selected">DELUXE</option>
								<option value="Family">FAMILY</option>
								<option value="Executive">EXECUTIVE</option>
							</select>
                                    </div>
          </div>
       
        
          
          <div class="row">
          <div class="col-md-2">
          <div class="input-group">
									<span class="input-group-addon"><i class="fa fa-calendar fa" aria-hidden="true"></i></span>
							<input class="form-control input-lg" type="text" style="height:250px" placeholder="CheckInDate"
								name="txtFrom" id="txtFrom">
							<script type="text/javascript">
								$(document).ready(function() {

									$('#txtFrom').datepicker({
										format : "dd/mm/yyyy",
										autoclose : true,
										noOfMonths : 2
									});

								});
							</script>
						</div>
						</div>
						<div class="col-md-2">
						<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-calendar fa" aria-hidden="true"></i></span>
							<input class="form-control input-lg" type="text" style="height:250px"
								placeholder="CheckOutDate" name="txtTo" id="txtTo">
							<script type="text/javascript">
								$(document).ready(function() {

									$('#txtTo').datepicker({
										format : "dd/mm/yyyy",
										autoclose : true,
										noOfMonths : 2
									});

								});
							</script>
						</div>
						</div>
					<div class="col-md-2">	
					 <div class="wrapper-demo1">
                   <div id="dd2" class="wrapper-dropdown-3" tabindex="1">	
					<select name="group_adults"><b
								class="caret"></b>
								<option value="1">1 adult</option>
								<option value="2" selected="selected">2 adults</option>
								<option value="3">3 adults</option>
								<option value="4">4 adults</option>
								<option value="5">5 adults</option>
								<option value="6">6 adults</option>
								<option value="7">7 adults</option>
								<option value="8">8 adults</option>
								<option value="9">9 adults</option>
								<option value="10">10 adults</option>
								<option value="11">11 adults</option>
								<option value="12">12 adults</option>
								<option value="13">13 adults</option>
								<option value="14">14 adults</option>
								<option value="15">15 adults</option>
								<option value="16">16 adults</option>
								<option value="17">17 adults</option>
								<option value="18">18 adults</option>
								<option value="19">19 adults</option>
								<option value="20">20 adults</option>
								<option value="21">21 adults</option>
								<option value="22">22 adults</option>
								<option value="23">23 adults</option>
								<option value="24">24 adults</option>
								<option value="25">25 adults</option>
								<option value="26">26 adults</option>
								<option value="27">27 adults</option>
								<option value="28">28 adults</option>
								<option value="29">29 adults</option>
								<option value="30">30 adults</option>
							</select>
						</div>
						</div>	
						</div>
						
						
											<div class="col-md-2">
						<div class="wrapper-demo1">
                   <div id="dd4" class="wrapper-dropdown-3" tabindex="1">
                   
                   <select  name="no_rooms"></b>
								<option value="1" selected="selected">1 room</option>
								<option value="2">2 rooms</option>
								<option value="3">3 rooms</option>
								<option value="4">4 rooms</option>
								<option value="5">5 rooms</option>
								<option value="6">6 rooms</option>
								<option value="7">7 rooms</option>
								<option value="8">8 rooms</option>
								<option value="9">9 rooms</option>
								<option value="10">10 rooms</option>
								<option value="11">11 rooms</option>
								<option value="12">12 rooms</option>
								<option value="13">13 rooms</option>
								<option value="14">14 rooms</option>
								<option value="15">15 rooms</option>
								<option value="16">16 rooms</option>
								<option value="17">17 rooms</option>
								<option value="18">18 rooms</option>
								<option value="19">19 rooms</option>
								<option value="20">20 rooms</option>
								<option value="21">21 rooms</option>
								<option value="22">22 rooms</option>
								<option value="23">23 rooms</option>
								<option value="24">24 rooms</option>
								<option value="25">25 rooms</option>
								<option value="26">26 rooms</option>
								<option value="27">27 rooms</option>
								<option value="28">28 rooms</option>
								<option value="29">29 rooms</option>
								<option value="30">30 rooms</option>

							</select>
						</div>
						</div>
						</div>	
						
						
						
          <div class="clearfix"></div>
        </div>
      </div>
      <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
							<div class="centered"><button type="submit" style="height:50px"
								class="btn btn-primary btn-lg btn-block site-btn">
								<i class="fa fa-search"></i> Search
							</button></div>
						</div>
	<div class="col-md-4"></div>
      </div>
    </div>
  </div>
 </div>
</form>
  <script>
  
  $(document).ready(function(){
	    $(".wrapper-dropdown-3").on("click",function(){
	        $(this).toggleClass("active")
	    })
	    $(".wrapper-dropdown-2").on("click",function(){
	        $(this).toggleClass("active")
	    })
	})
  
  </script>
</section>
 
 
 <section>

<<div class="container">
<h1 class="h1-responsive" style="margin-bottom: 20px">Special Offers
            
        </h1>
  <div class="row text-center">
    <div class="col-xs-3">
      <figure>
        <img src="static/images/offer.jpg" alt="">
        <figcaption>Check out our brand new Summer Wedding Package and take the first steps towards your dream summer wedding. For 50 Day Guests and 70 Evening Guests this fantastic package includes everything you need for a magical day</figcaption>
      </figure>
    </div>
    <div class="col-xs-3">
      <figure>
        <img src="static/images/offer1.jpg" alt="">
        <figcaption>Head along for the ultimate night out that’s sure to Spice Up Your Life. Enjoy a delicious three course meal in the surroundings of the Cathedral Ballroom and then prepare to dance the night away and Reach for the Stars as our live entertainment will be sure to get the party started!</figcaption>
      </figure>
    </div>
    <div class="col-xs-3">
      <figure>
        <img src="static/images/offer6.jpg" alt="">
        <figcaption>Our Wine, Dine &amp; Stay Offer has everything you need to relax and indulge. With an overnight stay, three course meal with full choice from our menu with wine and full cooked Scottish breakfast, you will leave feeling truly rested!</figcaption>
      </figure>
    </div>
    <div class="col-xs-3">
      <figure>
        <img src="static/images/offer3.jpg" alt="">
        <figcaption>Our fantastic Afternoon Tea Packages have everything you need for a truly indulgent afternoon treat. Whether its a traditional Afternoon Tea or something special with fizz we have various different options to suit your needs.</figcaption>
      </figure>
    </div>
   
  </div>
</div>



             





</section>   
    



<section>

    
    <div id="room_types" class="container">
        <h1 class="h1-responsive" style="margin-bottom: 20px">Room Types
            
        </h1>
        <hr>

        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="view overlay hm-white-slight">
                        <img src="<c:url value='/static/images/room1.jpg' />" class="img-fluid" alt="">
                    </div>

                    <div class="card-block">
                        <h4 class="card-title">Family Rooms</h4>

                        <p class="card-text">Room perfect for family vacations which includes entertainment facilities
                            like plasma TV, C.D, Stereo and DVD. These rooms provides all necessities for family.</p>

                        <div class="read-more">
                            <table>
                                <tr>
                                    <form action="/hms/rooms/search" method="get" class="btn btn-primary">
                                        <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button id="searchtext" name="searchtext" type="submit" value="Family"
                                                    class="btn btn-primary waves-button waves-light">Available Rooms
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </table>

                            <span class="text-lg-left" style="float: right; margin-top: -40px">
                                <p id="display_room1_price"
                                   style="font-size: 30px; color: dodgerblue">Rs${familybase} +</p>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card">
                    <div class="view overlay hm-white-slight">
                        <img src="<c:url value='/static/images/room2.jpg' />" class="img-fluid" alt="">
                    </div>

                    <div class="card-block">
                        <h4 class="card-title">Deluxe Rooms</h4>

                        <p class="card-text">Hotel provides the entire world class premium services like plasma TV, C.D,
                            Stereo & DVD,work desks, phone and much more.</p>

                        <div class="read-more">
                            <table>
                                <tr>
                                    <form action="/hms/rooms/search" method="get" class="btn btn-primary">
                                        <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button id="searchtext" name="searchtext" type="submit" value="Deluxe"
                                                    class="btn btn-primary waves-button waves-light">Available Rooms
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </table>

                            <span class="text-lg-left" style="float: right; margin-top: -40px">
                                <p id="display_room2_price"
                                   style="font-size: 30px; color: dodgerblue">Rs${deluxebase} +</p>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card">
                    <div class="view overlay hm-white-slight">
                        <img src="<c:url value='/static/images/room3.jpg' />" class="img-fluid" alt="">
                    </div>

                    <div class="card-block">
                        <h4 class="card-title">Executive Rooms</h4>

                        <p class="card-text"> This include a range of luxurious rooms suitable for
                            its discerning visitors. The different kinds of rooms and suits include the Business suits
                            and Crown Suits.</p>

                        <div class="read-more">
                            <table>
                                <tr>
                                    <form action="/hms/rooms/search" method="get" class="btn btn-primary">
                                        <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <button id="searchtext" name="searchtext" type="submit" value="Executive"
                                                    class="btn btn-primary waves-button waves-light">Available Rooms
                                            </button>
                                        </td>
                                    </form>
                                </tr>
                            </table>

                            <span class="text-lg-left" style="float: right; margin-top: -40px">
                                <p id="display_room3_price" style="font-size: 30px; color: dodgerblue">Rs${executivebase} +</p>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    </section>
    
    
    
    
<section>
    <hr class="container">
    <jsp:include page="services.jsp"></jsp:include>

</section>



<br><jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
