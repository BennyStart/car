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
<title>登录</title>
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/supersized.css">
<link rel="stylesheet" href="../css/style.css">
<script src="../js/jquery-1.8.2.min.js"></script>
<script src="../js/supersized.3.2.7.min.js"></script>
<script src="../js/supersized-init.js"></script>
<script src="../view/login.js"></script>
</head>
<body>
	<div class="page-container">
        <h1>Login</h1>
        <form action="/car/main/login.shtml" method="post">
	        <input id="username" type="text" name="username" class="username" placeholder="Username">
	        <input id="password" type="password" name="password" class="password" placeholder="Password">
	        <button id="login" type="submit">登 录</button>
	        <div class="error"><span>+</span></div>
        </form>
    </div>
</body>
</html>