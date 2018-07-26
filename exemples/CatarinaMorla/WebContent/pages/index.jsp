<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<title>Catarina Morla</title>

    <!-- Bootstrap Core CSS -->
    <link href="/CatarinaMorla/css/bootstrap.min.css"	rel="stylesheet">
	<link href="/CatarinaMorla/css/bootstrap.css" 		rel="stylesheet">
	<link href="/CatarinaMorla/css/bodyStyle.css" 		rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/CatarinaMorla/css/full-slider.css" 	rel="stylesheet">
    
</head>
<body>


 <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
					<span class="icon-bar"></span>
                </button>
                <a onclick="bodySlide(0);" class="navbar-brand" href="#role0">Catarina Morla</a>
            </div>
            
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="menu_head">
                   <li>
                        <a onclick="bodySlide(1);" href="#role1">Coreografia</a>
                    </li>
                    <li>
                        <a onclick="bodySlide(2);" href="#role2">Figurinos</a>
                    </li>
                    <li>
                        <a onclick="bodySlide(3);"href="#role3">Vestuário</a>
                    </li>
					 <li>
                        <a onclick="bodySlide(4);" href="#role4">S.C.A.R.F.S</a>
                    </li>
					<li id="loader">
                        
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
     </nav>  
       
       
  <!-- Full Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide">
    
        <!-- Indicators -->
        <ol class="carousel-indicators" id="indx">
            <li id="ind0" data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li id="ind1" data-target="#myCarousel" data-slide-to="1"></li>
            <li id="ind2" data-target="#myCarousel" data-slide-to="2"></li>
			<li id="ind3" data-target="#myCarousel" data-slide-to="3"></li>
			<li id="ind4" data-target="#myCarousel" data-slide-to="4"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
	<!-- ************************************************************************************************* -->		
             <div class="item active"  id="0">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url(recursos/bg.jpg);"></div>
                <div class="carousel-caption">
                   <h2><a  href="#role0"> Catarina Morla </a> </h2>
                </div>
            </div>
            
	<!-- ************************************************************************************************* -->			
           <div class="item" id="1">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url(recursos/1.jpg);"></div>
                <div class="carousel-caption">
					<h2><a  href="#role1">Coreografia</a> </h2>
                </div>
            </div>
	<!-- ************************************************************************************************* -->			
             <div class="item" id="2">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url(recursos/4.jpg);"></div>
                <div class="carousel-caption">
					<h2><a  href="#role2">Figurinos</a> </h2>					
                </div>				 
            </div>
	<!-- ************************************************************************************************* -->				
            <div class="item" id="3">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url(recursos/2.jpg);"></div>
                <div class="carousel-caption">
					<h2><a  href="#role3">Vestuário</a> </h2>
                </div>
            </div>
	<!-- ************************************************************************************************* -->				
			 <div class="item" id="4">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url(recursos/3.jpg);"></div>
                <div class="carousel-caption">
					<h2><a  href="#role4">S.C.A.R.F.S</a> </h2>                   
                </div>
            </div>
		<!-- ************************************************************************************************* -->				
        </div>

        <!-- Controls -->
        <a onclick="bodySlide(10)" class="left carousel-control" href="#myCarousel" data-slide="prev" >
            <span class="icon-prev"></span>
        </a>
        <a onclick="bodySlide(10)" class="right carousel-control" href="#myCarousel" data-slide="next" >
            <span class="icon-next"></span>
        </a>

    </header>
    
    
    


	 <!-- Page Content -->
    <div class="container">
    
		<!-- ********************** Page Content catarina morla ******************************************-->
	    <div class="hidden" id="role0">
	    	  <h2>Page Content catarina morla -</h2>
	    </div>
			
		<!-- ******************************* Page Content Coreográfia **************************************-->
		 <div class="hidden" id="role1"   >		 	
			${coreografia}
		</div>
		
		
		<!-- ****************************** Page Content Figurinos ******************************************-->
		 <div class="hidden" id="role2">
				 <h2>Page Content catarina morla -</h2>		
	    </div>
		
		<!-- ******************************** Page Content Vestuário ****************************************-->
		 <div class="hidden" id="role3">
			 <h2>Page Content catarina morla -</h2>			
	    </div>
		
		<!-- ******************************** Page Content S.C.A.R.F.S ***************************************-->
		 <div class="hidden" id="role4">
				 <h2>Page Content catarina morla -</h2>	
	    </div>
	    
    </div>  

    <!-- /.container -->
    
    
    
    
    
    
    
    
    
    
     
            <!-- jQuery -->
    <script src="/CatarinaMorla/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/CatarinaMorla/js/bootstrap.min.js"></script>
	
		<!-- Custom JavaScript -->
	<script src="/CatarinaMorla/js/scripts.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 0//5000 //changes the speed
    })
    </script>
        
</body>
</html>