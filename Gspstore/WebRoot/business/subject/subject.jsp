<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<style type="text/css">
   .tdLeft{
      width:15%;
      text-align: right;
   }
   .tdRight{
      width:35%;
      text-align: left;
   }
</style>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var subjectAddDialog;
	var subjectAddForm;
	var cdescAdd;
	var subjectEditDialog;
	var subjectEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'subjectAction!datagrid.action',
			title : '财务科目信息列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			singleSelect:true,
			nowrap : true,
			border : false,
			idField : 'subjectid',
			sortName : 'subjectname',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.subjectid;
						}
					},
			   {field:'subjectname',title:'科目名称',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.subjectname;
					}
				},				
			   {field:'subjecttype',title:'科目类别',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return dictGetName('006',row.subjecttype);
					}
				},				
			   {field:'meno',title:'备注',align:'center',sortable:true,width:210,
					formatter:function(value,row,index){
						return row.meno;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.modifydate);
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.createdate);
					}
				},				
			   {field:'crtusername',title:'创建人姓名',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				}				
			 ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('unselectAll');
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

		subjectAddForm = $('#subjectAddForm').form({
			url : 'subjectAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					subjectAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		subjectAddDialog = $('#subjectAddDialog').show().dialog({
			title : '添加财务科目信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						subjectAddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'subjectAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'subjectAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		subjectEditForm = $('#subjectEditForm').form({
			url : 'subjectAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					subjectEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		subjectEditDialog = $('#subjectEditDialog').show().dialog({
			title : '编辑财务科目信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						subjectEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'subjectAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'subjectAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '财务科目信息描述',
			modal : true,
			closed : true,
			maximizable : true
		});

		iframeDialog = $('#iframeDialog').show().dialog({
			title : 'POP弹出页面',
			modal : true,
			closed : true,
			maximizable : true
		});
	});

	function _search() {
		datagrid.datagrid('load', sy.serializeObject(searchForm));
	}
	function cleanSearch() {
		datagrid.datagrid('load', {});
		searchForm.find('input').val('');
	}
	function add() {
		subjectAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		subjectAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].subjectid);
					}
					$.ajax({
						url : 'subjectAction!delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(response) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : response.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.alert('提示', '请选择要删除的记录！', 'error');
		}
	}
	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$.messager.progress({
				text : '数据加载中....',
				interval : 100
			});
			$.ajax({
				url : 'subjectAction!showDesc.action',
				data : {
					subjectid : rows[0].subjectid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					subjectEditForm.find('input,textarea').val('');
					subjectEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					subjectEditDialog.dialog('open');
					$.messager.progress('close');
				}
			});
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	function showCdesc(index) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[index];
		$.messager.progress({
			text : '数据加载中....',
			interval : 100
		});
		$.ajax({
			url : 'subjectAction!showDesc.action',
			data : {
				subjectid : row.subjectid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有财务科目信息描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="过滤条件" collapsed="true"  style="height: 60px;overflow: hidden;display: none;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td>科目名称</td>
					<td><input name="subjectname" style="width:120px;" class="easyui-validatebox"/></td>
					<td>科目类别</td>
					<td>
					    <select id="subjecttype" name="subjecttype" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=006',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="科目类别"  style="width: 159px;">
		 				</select>
					</td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加</div>
		<div onclick="del();" iconCls="icon-remove">删除</div>
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>

	<div id="subjectAddDialog" style="display: none;width: 350px;height: 200px;padding:10px;" align="center">
		<form id="subjectAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">科目名称</td>
		 						<td class="tdRight">
									<input name="subjectname" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写科目名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">科目类别</td>
		 						<td class="tdRight">
		 						<select id="subjecttype" name="subjecttype" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=006',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="科目类别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">备注</td>
		 						<td class="tdRight">
									<input name="meno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写备注"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="subjectEditDialog" style="display: none;width: 350px;height: 200px;padding:10px;" align="center">
		<form id="subjectEditForm" method="post">
			<input type="hidden" name="subjectid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">科目名称</td>
		 						<td class="tdRight">
								<input name="subjectname" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写科目名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">科目类别</td>
		 						<td class="tdRight">
		 						  <select id="subjecttype" name="subjecttype" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=006',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="科目类别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">备注</td>
		 						<td class="tdRight">
								<input name="meno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写备注"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
	<div id="iframeDialog" style="display: none;overflow: auto;width: 600px;height: 400px;">
	<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
    </iframe>
</div>
</body>
</html>