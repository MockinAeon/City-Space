$("body").hide().fadeIn(1000);
var video = document.getElementById('video');
video.playbackRate = 0.8;

var login = $("#login");
var video = $("video");
var txtbox = $("input:text");
var username = $("#username");
var password = $("#password");


login.mouseenter(function () {
	video.css({
		"-webkit-filter": "blur(20px)",
		"-moz-filter": "blur(20px)",
		"-ms-filter": "blur(20px)",
		"filter": "blur(20px)",
		"transition": "-webkit-filter 2s ease"

	})
	login.css({
		"opacity": 1,
		"background-color": "rgba(0, 0, 0, .3)",
		"transition": "opacity 2s ease"
	})

	$(".user input").css({
		"background-color": "rgba(255, 255,255, .1)",
		"transition": "opacity 2s ease"
	})
})

login.mouseleave(function () {
	if ($("input:focus").length > 0) {
		return;
	} else {
		video.css({
			"-webkit-filter": "blur(2px)",
			"-moz-filter": "blur(2px)",
			"-ms-filter": "blur(2px)",
			"filter": "blur(2px)",
			"transition": "-webkit-filter 2s ease"
		})
		login.css({
			"opacity": 0.3,
			"background": "none",
			"transition": "opacity 2s ease",
			"transition": "background 2s ease"
		})
	}

})




$("input").focusin(function () {
	video.css({
		"-webkit-filter": "blur(20px)",
		"-moz-filter": "blur(20px)",
		"-ms-filter": "blur(20px)",
		"filter": "blur(20px)"

	})
	login.css({
		"opacity": 1,
		"background-color": "rgba(0, 0, 0, .3)"
	})
});
$("input").focusout(function () {
	video.css({
		"-webkit-filter": "blur(2px)",
		"-moz-filter": "blur(2px)",
		"-ms-filter": "blur(2px)",
		"filter": "blur(2px)",
		"transition": "-webkit-filter 2s linear"
	})
	login.css({
		"opacity": 0.3,
		"background-color": "rgba(0, 0, 0, .1)",
		"transition": "opacity 2s ease",
	})
});


$("#loginbtn").mousemove(function () {
	$(this).css({
		"background-color": "rgba(255,255,255,.5)",
		"transition": "background-color 1s ease"
	})
	$(this).css({
		"color": "black",
		"transition": "color 1s ease"
	})

});

$("#loginbtn").mouseout(function () {
	$(this).css({
		"background-color": "rgba(0,0,0,0)",
		"transition": "background-color 1s ease"
	})
	$(this).css({
		"color": "white",
		"transition": "color 1s ease"
	})

});




$("#loginbtn").click(function (event) {
	event.preventDefault();
	$.ajax({
		type: "POST",
		url: "ajaxlogin",
		data:$('#loginform').serializeArray(),
		success: function(data) {
            if(data=="error"){
            	$(".errormsg").show(300);
            	username.css({
        			"border": "1px solid red"
        		});
            	password.css({
        			"border": "1px solid red"
        		});
            	setTimeout(function() {
    				$(".errormsg").hide(300);
    			}, 1500);
            }else{
            	$('#loginform').submit();
            }
        }
	});

});
//create account ajax

$(".input-text").focusin(function () {
	$(this).css({
		"border": "1px solid lightgray"
	});
});
$("#createguestform input[name='password'],#createguestform input[name='password2']").focusin(function () {
	$(".errormsg2").hide(500);
});
$("#addguestbtn").click(function (event) {
	event.preventDefault();
	var username= $("#createguestform input[name='username']").val();
	var password=$("#createguestform input[name='password']").val();
	var password2=$("#createguestform input[name='password2']").val();
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
		if(password==password2){
			$.ajax({
				type: "POST",
				url: "manageaccount?action=addguest",
				data:$('#createguestform').serializeArray(),
				success: function(data) {
		            if(data=="success"){
		            	$("#add-success-btn").click();
		            	$("#username").val(username);
		            	$("#password").val(password);
		            	setTimeout(function(){$("#loginbtn").click();},1500);
		            }else if(data=="exist") {
		            	$(".errormsg1").show(500);
		            }else{
		            	alert(data)
		            }
		        }
			});
		}else{
			$("#createguestform input[name='password'],#createguestform input[name='password2']").css({
				"border": "1px solid red"
			});
			$(".errormsg2").show(500);
			
		}
	}
});


username.focusin(function () {
	username.css({
		"border": "none"
	})
})
username.focusout(function () {
	var usernameval = $("#username").val();
	if (usernameval == "") {
		username.css({
			"border": "1px solid red"
		})
	}
})

password.focusin(function () {
	password.css({
		"border": "none"
	})
})
password.focusout(function () {
		var passwordval = $("#password").val();
		if (passwordval == "") {
			password.css({
				"border": "1px solid red"
			})
		}
	})
