<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
  </head>
  
  <body>
    <table>
       <tr>
          <td class="tdLeft">所属片区</td>
		 						<td class="tdRight">
		 						   <select id="areaEdit" name="area" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'areaAction!combox.action',
						                valueField : 'areacode',
						                textField : 'areaname',
						                editable:false
		 						       "  missingMessage="请填写所属片区"  style="width: 159px;">
		 						     </select>
								</td>
			<td>
			  <select id="provinceId" class="easyui-combobox" style="width:200px;">  </select> 
			</td>
       </tr>
    </table>
  </body>
</html>
