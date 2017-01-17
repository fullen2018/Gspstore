<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
	
	});
</script>
<div align="center">
	<form id="jeecgOneDemoEditForm" method="post">
		<input type="hidden" name="obid" value="${jeecgOneDemo.obid }"/>
		<table class="tableForm">
			<tr>
					<td>用户名</td>
					<td>
					<input name="name" type="text" maxlength="32" value="${jeecgOneDemo.name }" class="easyui-validatebox" data-options="required:true" missingMessage="请填写用户名"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
					<td>年龄</td>
					<td>
					<input name="age" type="text" maxlength="" value="${jeecgOneDemo.age }" class="easyui-numberbox" data-options="required:true" missingMessage="请填写年龄"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
					<td>工资</td>
					<td>
					<input name="salary" type="text" maxlength="" value="${jeecgOneDemo.salary }" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写工资"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
					<td>生日</td>
					<td>
					<input name="birthday" type="text" maxlength=""  value="<fmt:formatDate value='${jeecgOneDemo.birthday }' type='date'/>" class="Wdate" onClick="WdatePicker()" missingMessage="请填写生日"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
					<td>注册时间</td>
					<td>
					<input name="registerDt" type="text" maxlength="" value="<fmt:formatDate value='${jeecgOneDemo.registerDt }' type='date'  pattern="yyyy-MM-dd hh:mm:ss"/>"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" missingMessage="请填写注册时间"  style="width: 155px;"/>
				</td>
			</tr>
		</table>
	</form>
</div>