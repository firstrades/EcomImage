$(function() {
	
	var availableTags = [];
	
	$("#tags").keyup(function() {
		
		  var keyword = $(this).val();  
		  
		  //alert(keyword);
		  
		  $.getJSON('searchByKeyWord', { keyWord: keyword }, function(array) {				
			  
			  var availableTags = array;	
			  
			  $( "#tags" ).autocomplete({
			      source: availableTags
			   });
		
		  });  	
	});    
    
    
});