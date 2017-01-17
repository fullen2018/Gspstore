<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%
		double d = Math.random() * 10 + 1;
		int i = (int) d;
		String n = String.valueOf(i);
		if (i < 10) {
			n = "0" + n;
		}
	%>
	<img alt="" src="<%=basePath%>/css/images/dogs/puppy_dogs_<%=n%>.png">
</body>
</html>
