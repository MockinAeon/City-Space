$("#login").click(function () {
	$("#logout-part").toggle(500);
});


$("#logout-btn").click(function (event) {

	$("#logout-part").hide(500)

});

$(".icon-part-icon").mousemove(function () {
	$(this).children().children().css({
		"color": "#b30000",
		"transition": "color 0.5s ease"
	});


});

$(".icon-part-icon").mouseout(function () {
	$(this).children().children().css({
		"color": "black",
		"transition": "color 0.5s ease"
	});

});