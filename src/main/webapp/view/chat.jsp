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
<script src="../js/jquery-1.8.2.min.js"></script>
<script src="../view/chat.js"></script>
</head>
<body>
	<div id="context" style="width:800px;height:300px;border:1px solid #F00"></div>
	<div style="padding-top:10px">
		<input id="message" type="text" placeholder="发送信息...">
		<button type="button" id="send" >发送</button>
	</div>
</body>
</html>