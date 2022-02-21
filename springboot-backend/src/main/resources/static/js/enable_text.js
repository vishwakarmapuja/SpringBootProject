$(document).ready(function(){
              
	$("#send_otp" ).click(function() {
	/*	  var mob = /^[1-9]{1}[0-9]{9}$/;
            
             if (mob.test($('#mobileno').val()) == true) {
                swal("success!", "moblieno is valid", "success");
			$('#otp').removeAttr('disabled');      
			$('#verify_otp').removeAttr('disabled');
                return;
              }
              else{
				swal("warning!", "mobileno is invalid", "warning");
					$('#otp').addAttr('disabled');
			}
            */
	$('#otp').removeAttr('disabled');
	$('#verify_otp').removeAttr('disabled');
	});
	
	$( "#send_otp" ).click(function() 
	{
		 var mobileno = $('#mobileno').val();
             
		 if(mobileno=='')
		 {
			 swal("warning!", "Please enter Mobile Number", "warning");
			 exit;
		 }
			if( $(this).hasClass('disabled') )
			{
			e.preventDefault();	
			}
		
			else{
				$.ajax({
				type:'POST',
				url:'/api/customer/send',
				data:{'mobileno':mobileno},
				success:function(data){
				var obj =JSON.parse(data);
				if(obj.status=='1')
				{
				swal("success!", "OTP Send Sucessfully", "success");
				$('#send_otp').hide();
				$('#resend_otp').show();
				}
				else
				{
				swal("warning!", "Something get Wrong", "warning");
				}
			}
		});
	}
	});
	
		$("#verify_otp" ).click(function() 
		{
		 var send_otp = $('#otp').val();
	
		 	if( $(this).hasClass('disabled') )
				{
				e.preventDefault();	
				}
			else
			{
				$.ajax({
				type:'POST',
				url:'/api/customer/verify_otp',
				data:{'send_otp':send_otp},
				success:function(data){
				
			
				if(data=='success')
				{
				swal("success!", "OTP Verified Sucessfully", "success");
				$('#btnSubmit').removeClass('disabled');
				
				}
				
				else
				{
				swal("warning!", "Wrong OTP Enter", "warning");
				$('#btnSubmit').addClass('disabled');
					
				}
			}
		});
	}	
	});
	
	});
           