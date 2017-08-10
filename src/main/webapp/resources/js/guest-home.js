$(document).ready(function() {

	// use space ajax
	$("#usespace-btn").click(function() {
		var selectspace = $('input:radio[name=selectspace]:checked').val();
		if (selectspace == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			
			if($("h4").html()=="You are not using any space now."){
				
				$.ajax({
					type : "POST",
					url : "GuestAction?action=usespace&spaceid=" + selectspace,
					success : function(data) {
						if (data == "success") {
							window.location.href="Home";
						} else if (data == "exist") {
						} else {
							alert(data);
						}
					}
				});
			}else{
				$(".using-error").show(500);
				setTimeout(function() {
					$(".using-error").hide(500);
				}, 1500);
			}
		}
	});
	
	
	// leave space ajax
	$("#leavespace-btn").click(function() {
		$.ajax({
			type : "POST",
			url : "GuestAction?action=leavespace",
			success : function(data) {
				if (data == "success") {
					window.location.href="Home";
				} else if (data == "exist") {
				} else {
					alert(data);
				}
			}
		});
	});
	
});
