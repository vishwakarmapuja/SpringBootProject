$(document).ready(function() {
    $("#add_new_customer_detail").submit(function(evt) {
        evt.preventDefault();
        var income = parseFloat($("#income").val());
        var emi = parseFloat($("#emi").val());
       var Monthly_net=income-emi;
			
			
		var foir=0;
		if(Monthly_net>=1 && Monthly_net<=30000)
		{
		  	 foir= 40;
			 
		}
		else if(Monthly_net>=30001 && Monthly_net<=75000)
		{
		 	 foir= 50;
			
		}
		else if(Monthly_net>=75001 && Monthly_net<=150000)
		{
		 foir= 55;
			
		}		
		else if(Monthly_net>=150000 && Monthly_net<=800000)
		{
			  foir= 60;
			
		}
		var Less=Monthly_net*foir/100;
		var disposable_income=Monthly_net-Less;
		// get p from customer
		var p=1000000;
		var r=13;
		var t=3;
		var final_loan_Amount;
        var EMI_value= emi_calculator(p, r, t);
        var final_loan_Amount = disposable_income/EMI_value*100000;
	
        if(final_loan_Amount<p)
      	{
      //	 alert(final_loan_Amount);
       	}
		else if(final_loan_Amount,p)
		{
		//alert(p);
		}
		 var cal_eligibility=(final_loan_Amount*10)/10;
		document.getElementById('cal_eligibility').value=cal_eligibility.toFixed(0);	
//  		alert($("#cal_eligibility").val()); 	


    // PREPARE FORM DATA
        let formData = {
			pincode : $("#pincode").val(),
			employment_type: $("#employment_type").val(),
			income : $("#income").val(),
			emi : $("#emi").val(),
			pancard : $("#pancard").val(),
			dob : $("#dob").val(),
			cal_eligibility :$("#cal_eligibility").val()
        }

        $.ajax({
            url: '/api/customerdetial/create',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
              let customerdetial = response.products[0];
                let customerdetialString = "{pincode: " + customerdetial.pincode + ",employment_type: " + customerdetial.employment_type + ",income: " + customerdetial.income  + ",emi : " + customerdetial.emi  + ",pancard: " + customerdetial.pancard +",dob: " + customerdetial.dob+ "}"
                let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message1 + '</strong> Product\'s Info = ' + customerdetialString;
                                    '</div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong>' + response.message1 + '</strong>' + ' ,Error: ' + message1.error + 
                                '</div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            }
        });

});


	function emi_calculator(p, r, t)
    {
        let emi;  
        r = r / (12*100); // one month interest
        t = t * 12; // one month period
        emi = (p * r * Math.pow(1 + r, t)) / (Math.pow(1 + r, t) - 1);
     	var emi_float=(emi*10)/100;
	  	 return emi_float;
    }
});