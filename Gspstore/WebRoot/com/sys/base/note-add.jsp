<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		var cdescAdd;
		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'noteAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'noteAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png',
			width:300,
			height:200
		});
	});
</script>

<div align="center" style="padding: 5px;">
	<form id="noteAddForm" method="post">
		<table class="tableForm">
			<tr>
				<td>标题</td>
				<td>
				<input name="title" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写标题"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
				<td>内容</td>
				<td>
					<textarea name="content" id="cdescAdd"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>