<%@page import="ecom.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Advance Features</title>
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">

.row {
    margin-left:0px !important;
    margin-right:0px !important;
    margin-bottom: 16px;
}
label{
font-weight: normal !important; 
color:#337AB7;
}
hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
}
</style>
</head>
<body>

<%
	Product product = (Product) request.getAttribute("product");
%>

<hr>

	<!-- -------------------------------- Product Description --------------------------------------- -->			
			
			<h3 style="font-size: 25px; color:#337AB7;">
				Advance Features 
				<span style="margin-left: 62%;font-size: 68%;color: blue;">
					Product Id: <%=product.getProductId() %>
				</span>
			</h3>  			
			<hr>
			<!-- -------------------------------------------------- -->
			<div class="row">					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Category </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="category" readonly class="form-control" value="<%=product.getCategory() %>" />
					</div>						
					<div class="col-md-3 col-sm-6 col-xs-12">
						<label>	Sub Category </label>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="text" name="subCategory" class="form-control" readonly value="<%=product.getSubCategory() %>" />
					</div>
				</div>
			<!-- -------------------------------------------------- -->
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label> Company Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="companyOfTheProduct" class="form-control" readonly="readonly"  value="<%=product.getCompanyName() %>" />
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>	 Product Name </label>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<input type="text" name="productName" class="form-control" readonly="readonly" value="<%=product.getProductName() %>" />
				</div>
			</div>	
			
	<!-- -------------------------------- End Product Description --------------------------------------- -->
			
	<hr>
	
	<!-- -------------------------------- Wholesale Offer -------------------------------------------- -->
	
	<form method="post" id="form1"> 
		
			<input type="hidden" name="productId" value="<%=product.getProductId() %>" />
			
			<h3 style="font-size: 25px; color:#337AB7;text-align:center;">Wholesale Offer </h3>  
			
			<div id="msg1" style="color:red;"></div>
			<hr>
					<!-- -------------------------------------------------- -->
					<div class="row">					
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	<span style="color:red;">*</span> Quantity </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="category" required class="form-control" value="<%=product.getCategory() %>" />
						</div>						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<label>	<span style="color:red;">*</span> Discount% </label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<input type="text" name="subCategory" class="form-control" required value="<%=product.getSubCategory() %>" />
						</div>
					</div>
			
			
					<!-- ---------------------- Submit ---------------------------- -->
			
			
					<div class="col-md-3 col-sm-6 col-xs-12">
						<input type="submit" value="Submit" 
							style="width: 50% !important;  padding: 7px 1px;
							background:linear-gradient(#54b4eb, #2fa4e7 60%, #1d9ce5);
							border: 1px solid #0098fe;color:#ffffff;margin-top:18px;
							margin-left: 385%; " />
					</div>
					
			<div class="col-md-12">
			<hr>
			</div>	
					<!-- ---------------------- End Submit ---------------------------- -->
	</form>
	
	<!-- -------------------------------- End Wholesale Offer -------------------------------------------- -->
	
	
	

</body>
</html>