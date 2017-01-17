<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<style type="text/css">
   .tdLeft{
      width:30%;
      text-align: right;
   }
   .tdRight{
      width:70%;
      text-align: left;
   }
</style>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var areaAddDialog;
	var areaAddForm;
	var cdescAdd;
	var areaEditDialog;
	var areaEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'areaAction!datagrid.action',
			title : '片区信息列表',
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
			idField : 'areacode',
			sortName : 'areaname',
			sortOrder : 'asc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.areacode;
						}
					},
			   {field:'areaname',title:'片区名称',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.areaname;
					}
				},				
			   {field:'manage',title:'片区经理',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.manage;
					}
				},				
			   {field:'rate',title:'提成比例',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.rate;
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.createdate);
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.modifydate);
					}
				},				
			   {field:'ordernum',title:'序号',align:'center',sortable:true,width:60,
					formatter:function(value,row,index){
						return row.ordernum;
					}
				},
				{field:'meno',title:'备注',align:'center',sortable:true,width:240,
					formatter:function(value,row,index){
						return row.meno;
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

		areaAddForm = $('#areaAddForm').form({
			url : 'areaAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					areaAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		areaAddDialog = $('#areaAddDialog').show().dialog({
			title : '添加片区信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
				   if(areaAddForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						areaAddForm.submit();
					}
				   }
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'areaAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'areaAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		areaEditForm = $('#areaEditForm').form({
			url : 'areaAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					areaEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		areaEditDialog = $('#areaEditDialog').show().dialog({
			title : '编辑片区信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
				   if(areaEditForm.form('validate')){
						if(formFlag){
						    formFlag = false ;
							areaEditForm.submit();
						}
				   }
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'areaAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'areaAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '片区信息描述',
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
		areaAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		areaAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].areacode);
					}
					$.ajax({
						url : 'areaAction!delete.action',
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
				url : 'areaAction!showDesc.action',
				data : {
					areacode : rows[0].areacode
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					areaEditForm.find('input,textarea').val('');
					areaEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					areaEditDialog.dialog('open');
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
			url : 'areaAction!showDesc.action',
			data : {
				areacode : row.areacode
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有片区信息描述！', 'error');
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
					<td>片区名称</td>
					<td><input name="areaname" style="width:120px;" class="easyui-validatebox"/></td>
					<td>片区经理</td>
					<td><input name="manage" style="width:120px;" class="easyui-validatebox"/></td>
					<td>提成比例</td>
					<td><input name="rate" style="width:120px;" class="easyui-numberbox"/></td>
					<td>备注</td>
					<td><input name="meno" style="width:120px;" class="easyui-validatebox"/></td>
					<td>创建日期</td>
					<td><input name="createdate" style="width:120px;" class="easyui-datetimebox"/></td>
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

	<div id="areaAddDialog" style="display: none;width: 300px;height: 225px;padding:10px;" align="center">
		<form id="areaAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">片区名称</td>
		 						<td class="tdRight">
									<input name="areaname" type="text" maxlength="50" class="easyui-validatebox" data-options="required:true" missingMessage="请填写片区名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">片区经理</td>
		 						<td class="tdRight">
									<input name="manage" type="text" maxlength="10" class="easyui-validatebox" data-options="required:true" missingMessage="请填写片区经理"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">提成比例</td>
		 						<td class="tdRight">
									<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:3,max:99" missingMessage="请填写提成比例"  style="width: 155px;"/>
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

	<div id="areaEditDialog" style="display: none;width: 300px;height: 225px;padding:10px;" align="center">
		<form id="areaEditForm" method="post">
			<input type="hidden" name="areacode" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">片区名称</td>
		 						<td class="tdRight">
								<input name="areaname" type="text" maxlength="50" class="easyui-validatebox" data-options="required:true" missingMessage="请填写片区名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">片区经理</td>
		 						<td class="tdRight">
								<input name="manage" type="text" maxlength="10" class="easyui-validatebox" data-options="required:true" missingMessage="请填写片区经理"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">提成比例</td>
		 						<td class="tdRight">
									<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:3,max:99" missingMessage="请填写提成比例"  style="width: 155px;"/>
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