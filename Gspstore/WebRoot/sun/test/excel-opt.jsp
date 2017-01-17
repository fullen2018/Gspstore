<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
   <script>  
       function exportExcel(){
    	   window.location.href="demoAction!exportExcel.action";
       }
       function importExcel(){
			$('#excelForm').form({
				success : function(result) {
					try {
						var r = $.parseJSON(result);
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					} catch (e) {
						$.messager.alert('提示', result);
					}
				}
			});    	   
    		if($('#excelForm').form('validate')){
    			$('#excelForm').submit();	
     		}
       }
   </script>  
</head>

<body>  
    <div style="margin:10px 0;"></div>  
    <div class="easyui-panel" title="用户信息" style="width:400px">  
        <div style="padding:10px 0 10px 60px">  
        <form id="excelForm" method="post" enctype="multipart/form-data" action="demoAction!importExcel.action">  
            <table>  
				<tr>
					<td>文件路径</td>
					<td><input name="filedata" class="easyui-validatebox" 
					                                   required="true" type="file" missingMessage="请选择上传文件" /></td>
				</tr>            
            </table>  
        </form>  
        </div>  
        <div style="text-align:center;padding:5px">  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importExcel()">导入excel</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="exportExcel()">导出excel</a>  
        </div>  
    </div>  

</body> 

</html>