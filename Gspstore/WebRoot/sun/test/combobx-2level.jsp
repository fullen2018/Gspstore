<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<script type="text/javascript">

$(function(){  
	//初始化省份、城市下拉列表
 	 var provinceId = $('#provinceId');
		provinceId.combobox({
	 	    url:'demoAction!getProvinceJson.action',
		    valueField:'provinceId',
		    textField:'provinceName',
		    onChange:function(){
		     	 var provinceId = $('#provinceId').combobox('getValue');
		     	 $('#cityId').combobox({
			     	 url:'demoAction!getCityJsonByProvinceId.action?provinceId='+provinceId,
			      	 valueField:'cityId',
			         textField:'cityName'
		         });
		    }
	   });
});

</script>
<body>  
    <select id="provinceId" class="easyui-combobox" style="width:200px;">  </select>  
    <select id="cityId"     class="easyui-combobox"  style="width:200px;">  </select>  
</body>  

</html>