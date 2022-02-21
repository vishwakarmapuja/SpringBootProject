$(document).ready(function(){
    $("#update_customer_form").submit(function(evt) {
        evt.preventDefault();
        try {
            var customerId = $("#customer_ID").val();
			
            // PREPARE FORM DATA
            let formData = {
                mobileno : $("#customer_mobileno").val(),
			
             
            }
            
            $.ajax({
                url: '/api/customer/updatebyid/',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let customer = response.customers[0];
                    let customerString = "{mobileno:" + customer.mobileno + "}"
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong>' + response.message + '</strong> Customer\'s Info = ' + customerString;
                                        '</div>'

                    // change the updated data for customer table record
                    $("#tr_" + customerId + " td.td_mobileno").text(customer.mobileno.toUpperCase());

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});

                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message + '</strong>' + ' ,Error: ' + message.error + 
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
        let customerId = id_of_button.split("_")[2];
  
        $.ajax({
            url: '/api/customer/findone/' + customerId,
            type: 'GET',
            success: function(response) {
                let customer = response.customers[0];                
                $("#customer_id").val(customer.id);
                $("#customer_mobileno").val(customer.mobileno);
                
                $("#div_customer_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });        
    });
});