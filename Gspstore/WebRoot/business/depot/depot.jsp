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
	var depotAddDialog;
	var depotAddForm;
	var cdescAdd;
	var depotEditDialog;
	var depotEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'depotAction!datagrid.action',
			title : '仓库信息列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : true,
			singleSelect:true,
			border : false,
			idField : 'depotid',
			sortName : '',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.depotid;
						}
					},
			   {field:'depotname',title:'仓库名称',align:'center',sortable:true,width:180,
					formatter:function(value,row,index){
						return row.depotname;
					}
				},				
			   {field:'depotman',title:'仓管人',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.depotman;
					}
				},				
			   {field:'depotaddress',title:'仓库地址',align:'center',sortable:true,width:220,
					formatter:function(value,row,index){
						return row.depotaddress;
					}
				},				
			   {field:'phone',title:'联系电话',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.phone;
					}
				},				
			   {field:'isactive',title:'是否启用',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dictGetName('004',row.isactive);
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

		depotAddForm = $('#depotAddForm').form({
			url : 'depotAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					depotAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		depotAddDialog = $('#depotAddDialog').show().dialog({
			title : '添加仓库信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
				  if (depotAddForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						depotAddForm.submit();
					}
				 }
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'depotAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'depotAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		depotEditForm = $('#depotEditForm').form({
			url : 'depotAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					depotEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		depotEditDialog = $('#depotEditDialog').show().dialog({
			title : '编辑仓库信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
				  if (depotEditForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						depotEditForm.submit();
					}
				  }
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'depotAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'depotAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '仓库信息描述',
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
		depotAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		depotAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].depotid);
					}
					$.ajax({
						url : 'depotAction!delete.action',
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
				url : 'depotAction!showDesc.action',
				data : {
					depotid : rows[0].depotid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					depotEditForm.find('input,textarea').val('');
					depotEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					depotEditDialog.dialog('open');
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
			url : 'depotAction!showDesc.action',
			data : {
				depotid : row.depotid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有仓库信息描述！', 'error');
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
					<td>仓库名称</td>
					<td><input name="depotname" style="width:120px;" class="easyui-validatebox"/></td>
					<td>仓管人</td>
					<td><input name="depotman" style="width:120px;" class="easyui-validatebox"/></td>
					<td>仓库地址</td>
					<td><input name="depotaddress" style="width:120px;" class="easyui-validatebox"/></td>
					<td>联系电话</td>
					<td><input name="phone" style="width:120px;" class="easyui-validatebox"/></td>
					<td>是否启用</td>
					<td><input name="isactive" style="width:120px;" class="easyui-validatebox"/></td>
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

	<div id="depotAddDialog" style="display: none;width: 350px;height: 250px;padding:10px;" align="center">
		<form id="depotAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">仓库名称</td>
		 						<td class="tdRight">
									<input name="depotname" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">仓管人</td>
		 						<td class="tdRight">
									<input name="depotman" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓管人"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">仓库地址</td>
		 						<td class="tdRight">
									<input name="depotaddress" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系电话</td>
		 						<td class="tdRight">
									<input name="phone" type="text" maxlength="15" class="easyui-validatebox" data-options="required:true" missingMessage="请填写联系电话"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">是否启用</td>
		 						<td class="tdRight">
		 						    <select id="isactiveAdd" name="isactive" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否启用"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="depotEditDialog" style="display: none;width: 350px;height: 250px;padding:10px;" align="center">
		<form id="depotEditForm" method="post">
			<input type="hidden" name="depotid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">仓库名称</td>
		 						<td class="tdRight">
								<input name="depotname" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">仓管人</td>
		 						<td class="tdRight">
								<input name="depotman" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓管人"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">仓库地址</td>
		 						<td class="tdRight">
								<input name="depotaddress" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系电话</td>
		 						<td class="tdRight">
								<input name="phone" type="text" maxlength="15" class="easyui-validatebox" data-options="required:true" missingMessage="请填写联系电话"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">是否启用</td>
		 						<td class="tdRight">
		 						   <select id="isactiveEdit" name="isactive" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否启用"  style="width: 159px;">
		 						     </select>
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