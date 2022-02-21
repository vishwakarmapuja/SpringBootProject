$(document).ready(function(){
    (function(){
	var customer_id=sessionStorage.getItem("customer_id");
	  var cal=0;
        $.ajax({
            type : "POST",
            url : "/api/customerdetial/retrieveinfos",
            data:{"customer_id":customer_id},
            success: function(response){
				//var obj =JSON.parse(response);
				console.log(response.customers);
				
              $.each(response.customers, (i, product) => 
				{  

              /*  <button type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#myModal">
                Open modal
              </button>*/

               let deleteButton = '<button ' +
                                        'id=' +
                                        '\"' + 'btn_delete_' +product.id + '\"'+
                                        ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal"' +
                                        '>&times</button>';

                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + product.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            product.id +
                                            '</button>';
                
                let tr_id = 'tr_' + product.id;
                let customerRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' + 
                          '<td class=\"td_pincode\">' + product.pincode.toUpperCase() + '</td>' +
						  '<td class=\"td_pancard\">' + product.pancard.toUpperCase() + '</td>' +
						  '<td class=\"td_income\">' + product.income.toUpperCase() + '</td>' +
						  '<td class=\"td_emi\">' + product.emi.toUpperCase() + '</td>' +
						  '<td class=\"td_employment_type\">' + product.employment_type.toUpperCase() + '</td>' +
						  '<td class=\"td_dob\">' + product.dob.toUpperCase() + '</td>' +		  
						'<td class=\"td_cal_eligibility\">' + product.cal_eligibility.toUpperCase() + '</td>' +	  
					
                          '<td>' + deleteButton + '</td>' +
                          '</tr>';                
                $('#loan').html(product.cal_eligibility);
                  $('#principle_amount').html(product.cal_eligibility);
               cal=product.cal_eligibility ;
                  $('#loan_amount').html(product.cal_eligibility);     
              });
		
			
			 $.ajax({
				            type : "POST",
				            url : "/api/customerdetial/cal",
				            data:{"cal":cal,"Tenure":3,"Interest":13},
				            success: function(data)
								{
						 $('#interest_amount').html(Math.round(data[1]));
					 		var total_payable=(+cal)+(+data[1]);
							 $('#total').html(Math.round(total_payable));
							
                                    }
                           });
			
			/*	function commaSeparateNumber(val){
    		return val.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    			
  				}*/

            },
		

            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
		
    })();        
 
});