$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "customers/list",
		success: function(result){
			$.each(result, function(i, customers){
				
				var customerRow = '<tr>' +
									'<td>' + customers.id + '</td>' +
									'<td>' + customers.firstName + '</td>' +
									'<td>' + customers.lastName + '</td>' +
									'<td>' + customers.email + '</td>' +
								  '</tr>';
				
				$('#customerTable tbody').append(customerRow);
				
	        });
			
			$( "#customerTable tbody tr:odd" ).addClass("info");
			$( "#customerTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			//alert("ERROR: ", e);
			//console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#customerTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
})