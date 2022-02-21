$(document).ready(function() {	
    $("#add_new_customer").submit(function(evt) {
        evt.preventDefault();
	
	    var number=Math.floor(1000 + Math.random() * 9000);
		var customer_ID="FNLP"+number;
		
			function getUrlVars()
			{
			    var vars = [], hash;
			    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			    for(var i = 0; i < hashes.length; i++)
			    {
			        hash = hashes[i].split('=');
			        vars.push(hash[1]);
			        vars[hash[0]] = hash[1];
			    }
			    return vars['ref'];
			}
		var url_path= getUrlVars();
		//alert(url_path);
	
      // PREPARE FORM DATA
        let formData = {
			mobileno : $("#mobileno").val(),
			customer_ID :customer_ID,
			url_path:url_path,
         }
		
      $.ajax({
            url: '/api/customer/create',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType :'json',
            success: function (response) {
                let customer = response.customers[0];
                let customerString = "{mobileno: " + customer.mobileno +"}"
                let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong>' + response.message + '</strong> Customer\'s Info = ' + customerString;
                                    '</div>'
				
				
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});
                resetUploadForm();
				

            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                    '<strong>' + response.message + '</strong>' + ' ,Error: ' + message.error + 
                                '</div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});
                resetUploadForm();
            }
        });

});
});

