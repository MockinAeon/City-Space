$(document).ready(function() {


	$("#addadvice-btn").click(function() {
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
	$("#addadvicebtn").click(function (event) {
		event.preventDefault();
		if($(".input-text").val()==""){
			$(".input-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "CreditAction?action=addadvice",
				data:$('#addadviceform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="checkrequest";},1500);
		            }
		        }
			});
		}
	});
	
	
	// edit credit ajax
	$("#addcredit-btn").click(function() {
		var selectguest = $('input:radio[name=selectguest]:checked').val();
		if (selectguest == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$.ajax({
				type : "POST",
				url : "CreditAction?action=addcredit&guestid=" + selectguest,
				success : function(data) {
					if (data == "success") {
						window.location.href="creditRolemanagecredit";
					} 
				}
			});
		}
	});
	$("#reducecredit-btn").click(function() {
		var selectguest = $('input:radio[name=selectguest]:checked').val();
		if (selectguest == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$.ajax({
				type : "POST",
				url : "CreditAction?action=reducecredit&guestid=" + selectguest,
				success : function(data) {
					if (data == "success") {
						window.location.href="creditRolemanagecredit";
					} 
				}
			});
		}
	});
	
	
	
});
