<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>聊天网页</title>
<style type="text/css">
    body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:500px;width:800px;}
		#r-result{width:100%; font-size:14px;}
  </style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=aPClY21MD9NbmOEKgXAgSlZMmU82unwr"></script>
<script src="../js/jquery-1.8.2.min.js"></script>
<script src="../view/map.js"></script>
</head>
<body>
	<div id="allmap"></div>
	<div id="r-result">
		城市名: <input id="cityName" type="text" style="width:100px; margin-right:10px;" />
		<button id="query" type="submit">查询</button>
	</div>
</body>
</html>