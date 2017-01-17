<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$(function() {
		var cdescEdit;
		cdescEdit = $('#cdescEdit').xheditor({
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
		cdescEdit.setSource("${note.content}");
	});
</script>
<div align="center">
	<form id="noteEditForm" method="post">
		<input type="hidden" name="obid" value="${note.obid }"/>
		<table class="tableForm">
			<tr>
				<td>标题</td>
				<td>
				<input name="title" type="text" maxlength="100" value="${note.title}" class="easyui-validatebox" data-options="required:true" missingMessage="请填写标题"  style="width: 155px;"/>
				</td>
			</tr>
			<tr>
				<td>内容</td>
				<td>
					<textarea name="content" id="cdescEdit" ></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>