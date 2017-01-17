<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		
	});
</script>

<div align="center" style="padding: 5px;">
	<form id="jeecgOneDemoAddForm" method="post">
		<table class="tableForm">
				<tr>
						<td>用户名</td>
						<td>
					<input name="name" type="text" maxlength="32" class="easyui-validatebox" data-options="required:true" missingMessage="请填写用户名"  style="width: 155px;"/>
					</td>
				</tr>
				<tr>
						<td>年龄</td>
						<td>
					<input name="age" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写年龄"  style="width: 155px;"/>
					</td>
				</tr>
				<tr>
						<td>工资</td>
						<td>
					<input name="salary" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写工资"  style="width: 155px;"/>
					</td>
				</tr>
				<tr>
						<td>生日</td>
						<td>
					<input name="birthday" type="text" class="Wdate" onClick="WdatePicker()"  style="width: 155px;"/>
					</td>
				</tr>
				<tr>
						<td>注册时间</td>
						<td>
					<input name="registerDt" type="text" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 155px;"/>
					</td>
				</tr>
		</table>
	</form>
</div>