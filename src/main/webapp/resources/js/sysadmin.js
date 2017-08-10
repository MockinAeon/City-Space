$(document).ready(function () {


	$(".left-part a").mouseover(function () {
		$(this).css({
			"background-color": " #413F34",
			"transition": "background-color 2s ease"
		});
		$(this).css({
			"color": "white",
			"transition": "color 1s ease"
		});
	});
	$(".left-part a").mouseout(function () {
		$(this).css({
			"background-color": "#D3CFC0",
			"transition": "background-color 2s ease"
		});
		$(this).css({
			"color": "#5D5850",
			"transition": "color 1s ease"
		});
	});
	
	//add city ajax
	$("#cityname-text").focusin(function () {
		$("#cityname-text").css({
			"border": "1px solid lightgray"
		});
	});
	$("#addcitybtn").click(function (event) {
		event.preventDefault();
		var cityname = $("#cityname-text").val();
		if(cityname==""){
			$("#cityname-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "managespace?action=addcity",
				data:$('#addcityform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managecity";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }
		        }
			});
		}
		

	});
	
	
	//add enterprise ajax
	$("#enterprisename-text").focusin(function () {
		$("#enterprisename-text").css({
			"border": "1px solid lightgray"
		});
	});
	$("#addenterprisebtn").click(function (event) {
		event.preventDefault();
		var enterprisename = $("#enterprisename-text").val();
		if(enterprisename==""){
			$("#enterprisename-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "managespace?action=addenterprise",
				data:$('#addenterpriseform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="manageenterprise";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	
	//add building ajax
	$("#buildingname-text").focusin(function () {
		$("#buildingname-text").css({
			"border": "1px solid lightgray"
		});
	});
	$("#addbuildingbtn").click(function (event) {
		event.preventDefault();
		var buildingname = $("#buildingname-text").val();
		if(buildingname==""){
			$("#buildingname-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "managespace?action=addbuilding",
				data:$('#addbuildingform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managebuilding";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	
	//add floor ajax
	$("#floorname-text").focusin(function () {
		$("#floorname-text").css({
			"border": "1px solid lightgray"
		});
	});
	$("#addfloorbtn").click(function (event) {
		event.preventDefault();
		var floorname = $("#floorname-text").val();
		if(floorname==""){
			$("#floorname-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "managespace?action=addfloor",
				data:$('#addfloorform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managefloor";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	
	//add Space ajax
	$("#spacename-text").focusin(function () {
		$("#spacename-text").css({
			"border": "1px solid lightgray"
		});
	});
	$("#addspacebtn").click(function (event) {
		event.preventDefault();
		var spacename = $("#spacename-text").val();
		if(spacename==""){
			$("#spacename-text").css({
				"border": "1px solid red"
			});
		}else{
			$.ajax({
				type: "POST",
				url: "managespace?action=addspace",
				data:$('#addspaceform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managespace";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	
	//add ReserveRole ajax
	$(".input-text").focusin(function () {
		$(this).css({
			"border": "1px solid lightgray"
		});
	});
	$("#addreservebtn").click(function (event) {
		event.preventDefault();;
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
			$.ajax({
				type: "POST",
				url: "manageaccount?action=addreserve",
				data:$('#addreserveform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managereserve";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	//add Credit Role ajax
	$("#addcreditbtn").click(function (event) {
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
			$.ajax({
				type: "POST",
				url: "manageaccount?action=addcredit",
				data:$('#addcreditform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	setTimeout(function(){window.location.href="managecredit";},1500);
		            }else if(data=="exist") {
		            	$(".errormsg").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}
	});
	
});
