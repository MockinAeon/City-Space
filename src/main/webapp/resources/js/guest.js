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
							window.location.href="CheckSpace";
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
					window.location.href="CheckSpace";
				} else if (data == "exist") {
				} else {
					alert(data);
				}
			}
		});
	});
	
	// subscribe ajax
	$("#subscribe-btn").click(function() {
		var selectspace = $('input:radio[name=selectspace]:checked').val();
		if (selectspace == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			$.ajax({
				type : "POST",
				url : "GuestAction?action=subscribe&spaceid=" + selectspace,
				success : function(data) {
					if (data == "success") {
						$("#subscribe-click").click();
					} else if (data == "exist") {
						$(".tips3").show(500);
						setTimeout(function() {
							$(".tips3").hide(500);
						}, 1500);
					} else {
						alert(data);
					}
				}
			});
		}
	});
	
	// reserve ajax
	$("#reserve-btn").click(function() {
		var selectspace = $('input:radio[name=selectspace]:checked').val();
		if (selectspace == null) {
			$(".tips1").show(500);
			setTimeout(function() {
				$(".tips1").hide(500);
			}, 1500);
		} else {
			var status = $('input:radio[name=selectspace]:checked').parent().prev().html();
			
			if(status=="false"){
				$(".tips2").show(500);
				setTimeout(function() {
					$(".tips2").hide(500);
				}, 1500);
			}else{
				var table=$('input:radio[name=selectspace]:checked').parent().parent();
				var city = table.children().html()
				var enterprise=table.children().next().html()
				var building =table.children().next().next().html() ;
				var floor=table.children().next().next().next().html();
				var space=table.children().next().next().next().next().html();
				$(".show-reserve-info").html("You want to reserve: " +city+" > "+enterprise+" > "+building+" > "+floor+" > "+space);
				$("#spaceid").val(selectspace);
				$("#reserve-modal-btn").click();
			}
		}
	});
	
	$(".input-text").focusin(function () {
		$(this).css({
			"border": "1px solid lightgray"
		});
	});
	$("#addreservebtn").click(function (event) {
		event.preventDefault();
		var notgood = 0;
		$(".input-text").each(function(){
			if($(this).val()==""){
				notgood=notgood+1;
				$(this).css({
					"border": "1px solid red"
				});
			}else{
				
			}
			
		});
		if(notgood==0){
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
					url: "GuestAction?action=reserve",
					data:$('#reservespaceform').serializeArray(),
					success: function(data) {
			            if(data=="success"){
			            	$("#add-success-btn").click();
// setTimeout(function(){window.location.href="manageaccount?action=credit";},1500);
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
	
});
