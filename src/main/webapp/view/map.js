jQuery(document).ready(function() {
	// 百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	map.centerAndZoom("厦门", 15); // 初始化地图,用城市名设置地图中心点
	map.enableScrollWheelZoom(true);// 开启鼠标滚轮缩放
	// map.addControl(new BMap.MapTypeControl()); // 添加地图类型控件

	var stCtrl = new BMap.PanoramaControl(); // 构造全景控件
	stCtrl.setOffset(new BMap.Size(20, 20));

	map.addControl(stCtrl);// 添加全景控件
	$('#query').bind('click', function() {
		var city = document.getElementById("cityName").value;
		if (city != "") {
			map.centerAndZoom(city, 11); // 用城市名设置地图中心点
		}
	});
});
