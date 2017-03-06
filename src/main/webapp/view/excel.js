$(document).ready(function() {
	$('#upload').bind('click', function() {
		$.ajaxFileUpload({
			url : '/car/excel/upload.shtml', // 需要链接到服务器地址
			secureuri : false,
			fileElementId : 'ajaxFile', // 文件选择框的id属性
			data : {
				'_excel' : 'testXml'
			},
			dataType : 'text', // 服务器返回的格式
			success : function(data, status) {
				var data = data;
			},
			error : function(data, status, e) {
			}
		});
	});

	$('#download').bind('click', function() {
		window.location = '/car/excel/download.shtml?name=' + 'download';
	});

});
