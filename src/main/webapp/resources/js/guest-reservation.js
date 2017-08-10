$(document).ready(function() {

	// cancel space ajax
	$("#cancel-btn").click(function() {
		var selectreservation = $('input:radio[name=selectreservation]:checked').val();
		if (selectreservation == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			var status = $('input:radio[name=selectreservation]:checked').parent().prev().html();
			if(status=="Sent"||status=="Sent Complete"){
				$("#reservationid").val(selectreservation);
				$("#cancel-modal-btn").click();
			}else{
				$(".tips2").show(500);
				setTimeout(function() {
					$(".tips2").hide(500);
				}, 1500);
			}
		}
	});
	$(".input-text").focusin(function () {
		$(this).css({
			"border": "1px solid lightgray"
		});
	});
	$("#cancelbtn").click(function (event) {
		event.preventDefault();
		if($(".input-text").val()==""){
			$(".input-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "GuestAction?action=cancel",
				data:$('#cancelform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	setTimeout(function(){window.location.href="Reservation";},1500);
		            }
		        }
			});
		}
	});
	
	// update space ajax
	$("#update-btn").click(function() {
		var selectreservation = $('input:radio[name=selectreservation]:checked').val();
		if (selectreservation == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			var status = $('input:radio[name=selectreservation]:checked').parent().prev().html();
			if(status=="Sent"||status=="Sent Complete"){
				$("#reservationid2").val(selectreservation);
				$("#update-modal-btn").click();
			}else{
				$(".tips3").show(500);
				setTimeout(function() {
					$(".tips3").hide(500);
				}, 1500);
			}
		}
	});

	$("#updatebtn").click(function (event) {
		event.preventDefault();
		var notgood = 0;
		$(".input-text").each(function(){
			if($(this).val()==""){
				notgood=notgood+1;
				$(this).css({
					"border": "1px solid red"
				});
			}
		});		
		if(notgood==1){
			var now = new Date();
            var year = now.getFullYear();
            var month =(now.getMonth() + 1).toString();
            var day = (now.getDate()).toString();
            if (month.length == 1) {
                month = "0" + month;
            }
            if (day.length == 1) {
                day = "0" + day;
            }
            var nowday = year + month +  day;
            
			var reserveday = $('input[name=reserveday]').val();
			reserveday=(reserveday.replace(/-/g,""))
			var starttime = $('input[name=starttime]').val();
			var endtime = $('input[name=endtime]').val();
			
			if((endtime>starttime)&&(reserveday>nowday)){
				
				$.ajax({
					type: "POST",
					url: "GuestAction?action=update",
					data:$('#updateform').serializeArray(),
					success: function(data) {
			            if(data=="success"){
			            	$("#update-success-btn").click();
			            	setTimeout(function(){window.location.href="Reservation";},1500);
			            }else if(data=="exist") {
			            	$(".errormsg").show(500);
			            }else{
			            	alert(data)
			            }
			        }
				});
			}else{
				$(".errormsg").show(500);
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
					window.location.href="Reservation";
				} else if (data == "exist") {
				} else {
					alert(data);
				}
			}
		});
	});
	
});
