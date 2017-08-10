$(document).ready(function() {

//reject
	$("#reject-btn").click(function() {
		var selectreservation = $('input:radio[name=selectreservation]:checked').val();
		if (selectreservation == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$("#reservationid").val(selectreservation);
			$("#reserve-modal-btn").click();
		}
	});
	$(".input-text").focusin(function () {
		$(this).css({
			"border": "1px solid lightgray"
		});
	});
	$("#rejectbtn").click(function (event) {
		event.preventDefault();
		if($(".input-text").val()==""){
			$(".input-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "ReserveAction?action=reject",
				data:$('#rejectform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="reservecheckrequest";},1500);
		            }
		        }
			});
		}
	});
	//approve
	$("#approve-btn").click(function() {
		var selectreservation = $('input:radio[name=selectreservation]:checked').val();
		if (selectreservation == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$.ajax({
				type : "POST",
				url : "ReserveAction?action=approve&reservationid=" + selectreservation,
				success : function(data) {
					if (data == "success") {
						window.location.href="reservecheckrequest";
					} else if (data == "exist") {
					} else {
						alert(data);
					}
				}
			});
		}
	});
	
	
});
