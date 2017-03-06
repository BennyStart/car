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
<title>文件上传下载</title>
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/ajaxFileUpload.js"></script>
<script src="../view/upload.js"></script>
</head>
<body>
	<h3>使用uploadForm上传文件</h3>
	<form id="uploadForm" action="/car/file/upload.shtml" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="上传"/>
	</form>
	<h3>使用ajax上传文件</h3>
	<div style="padding-top:30px">
		<input id="ajaxFile" type="file" name="file">
		<button type="button" id="upload" >上传</button>
	</div>
	<h3>下载文件</h3>
	<button type="button" id="download" >下载</button>
</body>
</html>