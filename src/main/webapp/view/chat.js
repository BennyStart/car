$(function() {

	var host = window.location.host;
	var webSocket = new WebSocket("ws://" + "localhost:8333/car" + "/ws.shtml");

	webSocket.onopen = function(evnt) {
		console.log("链接服务器成功!");
	};
	webSocket.onmessage = function(evnt) {
		$("#context").html($("#context").html() + "<br/>" + evnt.data);
	};
	webSocket.onerror = function(evnt) {
		console.log("链接错误");
	};
	webSocket.onclose = function(evnt) {
		console.log("与服务器断开了链接!");
	}

	$('#send').bind('click', function() {
		var message = $("#message").val();
		webSocket.send(message);
	});
});
