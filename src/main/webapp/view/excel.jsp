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
<title>excel导入下载</title>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/ajaxFileUpload.js"></script>
<script src="../view/excel.js"></script>
</head>
<body>
	<div style="padding-top:30px">
		<input id="ajaxFile" type="file" name="file">
		<button type="button" id="upload" >上传</button>
	</div>
	<h3>下载文件</h3>
	<button type="button" id="download" >下载</button>
</body>
</html>