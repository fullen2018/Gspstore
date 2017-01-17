<%@ page language="java" import="com.jeecg.pageModel.*,com.jeecg.model.*,com.jeecg.util.*,java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	 var userInfoForm;
	$(function() {
		userInfoForm = $('#userInfoForm').form({
			url : 'userAction!modifySelfPwd.action',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					userInfoWindow.window('close');
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		});
	});
</script>
<div class="easyui-layout" fit="true" border="false">
	<div region="center" style="" title="拥有权限" border="false">
		<%
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
			if (sessionInfo != null && sessionInfo.getAuths() != null && sessionInfo.getAuths().size() > 0) {
				List<Auth> auths = sessionInfo.getAuths();
				for (int i = 0; i < auths.size(); i++) {
					if (i > 0) {
						out.print("，");
					}
					Auth a = auths.get(i);
					out.print(a.getCname());
				}
			}
		%>
	</div>
	<div region="south" style="overflow: hidden;height:115px;" title="密码修改" border="false" align="center">
		<form id="userInfoForm" method="post">
			<table class="tableForm">
				<tr>
					<th>原密码</th>
					<td><input name="oldPwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写原密码" />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>新密码</th>
					<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写新密码" />
					</td>
					<td></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input name="rePassword" type="password" class="easyui-validatebox" required="true" missingMessage="请再次填写密码" validType="eqPassword['#userInfoForm input[name=cpwd]']" /></td>
					<td><a name="btn" class="easyui-linkbutton" onclick="userInfoForm.submit();">修改</a></td>
				</tr>
			</table>
		</form>
	</div>
</div>