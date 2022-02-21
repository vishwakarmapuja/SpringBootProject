$(document).ready(function(){
    $("#update_customerdetial_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let productId = $("#customerdetial").val()

            // PREPARE FORM DATA
          let formData = {				
			pincode : $("#customerdetial_pincode").val(),
			employment_type: $("#customerdetial_employment_type").val(),
			income : $("#customerdetial_income").val(),
			emi : $("#customerdetial_emi").val(),
			pancard : $("#customerdetial_pancard").val(),
			dob : $("#customerdetial_dob").val()
        }   
            $.ajax({
                url: '/api/customerdetial/updatebyid/' + productId + "/",
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let customerdetial = response.products[0];
                    let productString =  "{pincode: " + customerdetial.pincode + ",employment_type: " + customerdetial.employment_type + ",income : " + customerdetial.income  + ",emi : " + customerdetial.emi  + ",pancard: " + customerdetial.pancard + ",dob: " + customerdetial.dob + "}"
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong>' + response.message + '</strong> Product\'s Info = ' + productString;
                                        '</div>'

                    // change the updated data for personal_details  table record
                    $("#tr_" + productId + " td.td_pincode").text(customerdetial.pincode.toUpperCase());                                                                                                                                                                                                                                                                                                                      
  					 $("#tr_" + productId + " td.td_employment_type").text(customerdetial.employment_type.toUpperCase());
		 			$("#tr_" +productId  + " td.td_income").text(customerdetial.income.toUpperCase());   
					$("#tr_" + productId + " td.td_emi").text(customerdetial.emi.toUpperCase());    
    			 	 $("#tr_" + productId + " td.td_pancard").text(customerdetial.pancard.toUpperCase());
                	 $("#tr_" + productId + " td.td_dob").text(customerdetial.dob.toUpperCase());

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message1 + '</strong>' + ' ,Error: ' + message1.error + 
                                    '</div>';

                    $("#response").empty();                                    
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch(error){
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function(){
        let id_of_button = (event.srcElement.id);
        let personal_detailsId = id_of_button.split("_")[2];
  
        $.ajax({
            url: '/api/customerdetial/findone/' +personal_detailsId,
            type: 'GET',
            success: function(response) {
                let customerdetial= response.personal_details[0];                
                $("#customerdetial_id").val(customerdetial.id);
                $("#customerdetial_pincode").val(customerdetial.pincode);
				 $("#customerdetial_employment_type").val(customerdetial.employment_type);
				 $("#customerdetial_income").val(customerdetial.income);
				 $("#customerdetial_emi").val(customerdetial.emi);
				 $("#customerdetial_pancard").val(customerdetial.pancard);
                
                $("#div_customerdetial_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });        
    });
});