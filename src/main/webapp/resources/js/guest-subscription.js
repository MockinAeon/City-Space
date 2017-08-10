$(document).ready(function() {

	// delete space ajax
	$("#delete-btn").click(function() {
		var selectsubscription = $('input:radio[name=selectsubscription]:checked').val();
		if (selectsubscription == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$.ajax({
				type : "POST",
				url : "GuestAction?action=deletesubscription&subscriptionid=" + selectsubscription,
				success : function(data) {
					if (data == "success") {
						window.location.href="Subscription";
					} else if (data == "exist") {
					} else {
						alert(data);
					}
				}
			});
		}
	});
	
	
	// leave space ajax
	$("#leavespace-btn").click(function() {
		$.ajax({
			type : "POST",
			url : "GuestAction?action=leavespace",
			success : function(data) {
				if (data == "success") {
					window.location.href="Subscription";
				} else if (data == "exist") {
				} else {
					alert(data);
				}
			}
		});
	});
	
});
