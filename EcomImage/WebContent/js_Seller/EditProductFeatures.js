$(function() {	
	
	/*********** Basic Features ************/
	
	$('form#form1').submit(function(event) {
		
		event.preventDefault();
		
		$('#msg1').empty();
		
		var r = confirm("Alert: Do You Really Want To Edit This Basic Product!");
		
		if (r == true) {  
		
				var formData = new FormData($(this)[0]);
				
				$.ajax({
					url: 'EditBasicProduct',
					type: 'POST',
					data: formData,
					async: false,
				    cache: false,
				    contentType: false,
				    processData: false,
				    dataType: 'json',
				    success: function (data) {
				    	
				    	$('#msg1').empty();
				    	
				    	if (data.success)				    	
				    		$('#msg1').append(data.success);
				    	
				    	if (data.failed)				    	
				    		$('#msg1').append(data.failed);
				    	
				    },
				    error: function() {
				  		$('#msg1').empty();
				  		$('#msg1').append('Basic Features Not Updated');
				  	}
				});
		
		} // if close
		
		
		return false;
	});
	
	
	
	
});