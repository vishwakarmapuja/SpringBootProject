$(document).ready(function(){
    let customerdetialId = 0;

    $(document).on("click", "#div_personal_details_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        customerdetialId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Do you want delete a Product with id = " +  customerdetialId + " ?");
        $("#model-delete-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: '/api/customerdetial/deletebyid/' + customerdetialId,
            type: 'DELETE',
            success: function(response) {
                $("div.modal-body")
                    .text("Delete successfully a Product with id = " +  customerdetialId + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the product row on html page
                let row_id = "tr_" +  customerdetialId;
                $("#" + row_id).remove();
                $("#div_customerdetial_updating").css({"display": "none"});
            },
            error: function(error){
                console.log(error);
                $("#div_customerdetial_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
});