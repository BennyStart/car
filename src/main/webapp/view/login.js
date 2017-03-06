jQuery(document).ready(
		function() {

			$('.page-container form').submit(function() {
				var username = $(this).find('.username').val();
				var password = $(this).find('.password').val();
				if (username == '') {
					$(this).find('.error').fadeOut('fast', function() {
						$(this).css('top', '27px');
					});
					$(this).find('.error').fadeIn('fast', function() {
						$(this).parent().find('.username').focus();
					});
					return false;
				}
				if (password == '') {
					$(this).find('.error').fadeOut('fast', function() {
						$(this).css('top', '96px');
					});
					$(this).find('.error').fadeIn('fast', function() {
						$(this).parent().find('.password').focus();
					});
					return false;
				}
			});

			// $('#login').bind('click', function() {
			// var username = $("#username").val();
			// var password = $("#password").val();
			// login(username, password);
			// });
			//
			// login = function(username, password) {
			// var params = [ {
			// name : 'username',
			// value : username
			// }, {
			// name : 'password',
			// value : password
			// } ];
			// $.ajax({
			// url : '/car/main/login.shtml',
			// type : "get",
			// data : params,
			// dataType : 'json',
			// contentType : 'application/json; charset=UTF-8',
			// success : function(result) {
			//
			// }
			// });
			// }

			$('.page-container form .username, .page-container form .password')
					.keyup(function() {
						$(this).parent().find('.error').fadeOut('fast');
					});

		});
