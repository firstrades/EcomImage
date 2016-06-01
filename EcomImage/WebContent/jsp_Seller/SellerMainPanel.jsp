<%@page import="ecom.common.FrequentUse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Stockist Main Panel</title>
    
	    <!-- jQuery -->    
	    <script type="text/javascript" src="<%=FrequentUse.jQuery %>"></script>	    
	    <!-- Bootstrap Core JavaScript -->
	    <script src="js/bootstrap.min.js"></script>	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="js/metisMenu.min.js"></script>	
	    <!-- Custom Theme JavaScript -->
	    <script src="js/sb-admin-2.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="css/timeline.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="css/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>

<%
//SellerMainPanel-------------------

	String userId = (String) request.getAttribute("userId");

%>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div style="font-size: 110%;
    				font-family: monospace;
    				color: green;
   					margin-top: 4%;
    				margin-left: 17%;">SELLER MAIN PANEL (<%=userId %>)</div>
            </div>
          

            <ul class="nav navbar-top-links navbar-right">                
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="index.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="AddProduct" target="iframe"><i class="fa fa-dashboard fa-fw"></i>Add New Product</a>
                        </li>
                        <li>
                            <a href="RetrieveOrderedItemsForSeller" target="iframe"><i class="fa fa-dashboard fa-fw"></i>Status OF Booked Products</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> ELECTRONICS<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Mobile" target="iframe">Mobiles</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Laptop" target="iframe">Laptops</a></li> 
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Tablet" target="iframe">Tablet</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Camera" target="iframe">Camera</a></li> 
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Television" target="iframe">Television</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=AirCondition" target="iframe">Air Condition</a></li> 
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Refrigerator" target="iframe">Refrigerator</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=WashingMachine" target="iframe">Washing Machine</a></li> 
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=MicrowaveOven" target="iframe">Microwave Oven</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=VacuumCleaner" target="iframe">Vacuum Cleaner</a></li> 
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Speaker" target="iframe">Speaker</a></li>
                                <li><a href="ViewProductList?category=ELECTRONICS&subCategory=Geyser" target="iframe">Geyser</a></li>                                
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> WOMEN<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li><a href="ViewProductList?category=WOMEN&subCategory=WomenShoe" target="iframe">Shoes</a></li>
                                <li><a href="ViewProductList?category=WOMEN&subCategory=WomenKurta" target="iframe">Kurta</a></li>  
                                <li><a href="ViewProductList?category=WOMEN&subCategory=WomenSharee" target="iframe">Sharee</a></li>
                                <li><a href="ViewProductList?category=WOMEN&subCategory=WomenSalwar" target="iframe">Salwar</a></li>
                                <li><a href="ViewProductList?category=WOMEN&subCategory=WomenJeans" target="iframe">Jeans</a></li>                              
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> MEN<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li><a href="ViewProductList?category=MEN&subCategory=MenTshirt"  target="iframe">T-Shirt</a></li>
                                <li><a href="ViewProductList?category=MEN&subCategory=MenJeans"      target="iframe">Jeans</a></li> 
                                <li><a href="ViewProductList?category=MEN&subCategory=MenShirt"   target="iframe">Shirt</a></li>
                                <li><a href="ViewProductList?category=MEN&subCategory=MenTrouser" target="iframe">Trouser</a></li> 
                                <li><a href="ViewProductList?category=MEN&subCategory=MenShoes"   target="iframe">Shoes</a></li>
                                <!-- <li><a href="ViewProductList?category=MEN&subCategory=Jeans" target="iframe">Jeans</a></li>  -->                               
                            </ul>                            
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> KIDS<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li>
                                	<a href="#">Boys</a>
                                	<ul class="nav nav-third-level">
                                        <li><a href="ViewProductList?category=KIDS&subCategory=Boys_Shirt" target="iframe">Boy's Shirt</a></li>
                                        <li><a href="ViewProductList?category=KIDS&subCategory=Boys_Pant" target="iframe">Boy's Pants</a></li>
                                        <li><a href="ViewProductList?category=KIDS&subCategory=Baby_Diapers" target="iframe">Diapers</a></li>                                     
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Girls</a>
                                    <ul class="nav nav-third-level">
                                        <li><a href="ViewProductList?category=KIDS&subCategory=Girls_Top" target="iframe">Girl's Top</a></li>
                                        <li><a href="ViewProductList?category=KIDS&subCategory=Girls_Shorts" target="iframe">Girl's Shorts</a></li>                                                                        
                                    </ul>
                                </li>                                
                            </ul>                           
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Home&amp;Kitchen<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">                            	
                                <li><a href="ViewProductList?category=HomeAndKitchen&subCategory=Bedsheets"  target="iframe">Bedsheets</a></li>
                                <li><a href="ViewProductList?category=HomeAndKitchen&subCategory=Curtains"      target="iframe">Curtains</a></li> 
                                <li><a href="ViewProductList?category=HomeAndKitchen&subCategory=SofaCovers"   target="iframe">Sofa Covers</a></li>
                                <li><a href="ViewProductList?category=HomeAndKitchen&subCategory=PressureCookers" target="iframe">Pressure Cookers</a></li> 
                                <li><a href="ViewProductList?category=HomeAndKitchen&subCategory=GasStoves"   target="iframe">Gas Stoves</a></li>
                                                             
                            </ul>                            
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Food&amp;Grocery<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                            	<li><a href="ViewProductList?category=FoodAndGrocery&subCategory=Confectionery" target="iframe">Confectionery</a></li>
                                <li><a href="ViewProductList?category=FoodAndGrocery&subCategory=PowderProduct" target="iframe">PowderProduct</a></li>
                                <li><a href="ViewProductList?category=FoodAndGrocery&subCategory=Cakes" target="iframe">Cakes</a></li>                                     
                                <li><a href="ViewProductList?category=FoodAndGrocery&subCategory=Test" target="iframe">Test</a></li>                                  
                            </ul>                           
                        </li>
                        
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                       
                        
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Blank Page</a>
                                </li>
                                <li>
                                    <a href="#">Login Page</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

       	<div>
			<iframe src="InitialDashBoard" style="position:absolute;top:8%;left:19%;width:81%; height:800px;border:none;" name="iframe"></iframe>
		</div>	

    </div>   

</body>

</html>
