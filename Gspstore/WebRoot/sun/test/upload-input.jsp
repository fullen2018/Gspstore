<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
   <script>  
       function submitForm(){
      	 //提交Form
       	 var myForm = $('#uploadForm').form({
       			url : 'demoAction!upload.action',
       			success : function(data) {
       				var json = $.parseJSON(data);
       				if (json && json.success) {
       					window.parent.window.messageshow('成功',json.msg);
       				} else {
       				    window.parent.window.messageshow('失败','操作失败!');
       				}
       			}
       		});  
 		if($('#uploadForm').form('validate')){
 			myForm.submit();	
 		}
       }  
       function clearForm(){ 
    	   $('#uploadForm')[0].reset() 
           
       }  
   </script>  
</head>

<body>  
    <div style="margin:10px 0;"></div>  
    <div class="easyui-panel" title="信息" style="width:400px">  
        <div style="padding:10px 0 10px 60px">  
        <form id="uploadForm" method="post" enctype="multipart/form-data" action="demoAction!upload.action">  
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
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>  
        </div>  
    </div>  

</body> 

</html>