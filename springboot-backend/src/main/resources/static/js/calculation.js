
function show_value(val){
       var loan_amount=val;
        $("#loan_amount").html(loan_amount);
        var tenure = $('#tenure').html(); 
        var rate_interest = $('#name').val();


				 $.ajax({
				            type : "POST",
				            url : "/api/customerdetial/cal/",
				            data:{"cal":loan_amount,"Tenure":tenure,"Interest":rate_interest},
				            success: function(data)
								{		
													
						$('#EMI_value').html(Math.round(data[0]));
		
                                    }
                           });
			

}
function update_value(val) {
 var loan_amount=$("#loan_amount").html();
		var tenure=val;
	 $("#tenure").html(tenure);
 var rate_interest = $('#name').val();
	 $.ajax({
				            type : "POST",
				            url : "/api/customerdetial/cal",
				            data:{"cal":loan_amount,"Tenure":tenure,"Interest":rate_interest},
				            success: function(data)
								{
									$('#EMI_value').html(Math.round(data[0]));
                                    }
                           });
			
}	
