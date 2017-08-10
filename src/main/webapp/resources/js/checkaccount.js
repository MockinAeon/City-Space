$(".input-text").focusin(function () {
	$(this).css({
		"border": "1px solid lightgray"
	});
});
$("#changepasswordform input[name='password'],#changepasswordform input[name='password2']").focusin(function () {
	$(".errormsg").hide(500);
});

$("#changebtn").click(function (event) {
	event.preventDefault();
	var password=$("#changepasswordform input[name='password']").val();
	var password2=$("#changepasswordform input[name='password2']").val();
	var notgood = 0;
	$(".input-text").each(function(){
		if($(this).val()==""){
			notgood=notgood+1;
			$(this).css({
				"border": "1px solid red"
			});
		}
	});
	if(notgood==0){
		if(password==password2){
			$.ajax({
				type: "POST",
				url: "changepassword",
				data:$('#changepasswordform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            }else if(data=="exist") {
		            	$(".errormsg1").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}else{
			$("#changepasswordform input[name='password'],#changepasswordform input[name='password2']").css({
				"border": "1px solid red"
			});
			$(".errormsg").show(500);
			
		}
	}
});