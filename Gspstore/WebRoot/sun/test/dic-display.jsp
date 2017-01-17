<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	$(function() {
		dictCombox('001','lineType');
	});
</script>
</head>

<body>  
	<input name="lineType" style="width:100px;"/>
</body>  

</html>