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
	var accounterAddDialog;
	var accounterAddForm;
	var cdescAdd;
	var accounterEditDialog;
	var accounterEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'accounterAction!datagrid.action',
			title : '业务员信息列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : true,
			border : false,
			idField : 'accountid',
			singleSelect:true,
			sortName : 'accountname',
			sortOrder : 'asc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.accountid;
						}
					},
			   {field:'accountname',title:'业务员姓名',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.accountname;
					}
				},				
			   {field:'sex',title:'性别',align:'center',sortable:true,width:40,
					formatter:function(value,row,index){
						return dictGetName('001',row.sex);
					}
				},				
			   {field:'age',title:'年龄',align:'center',sortable:true,width:40,
					formatter:function(value,row,index){
						return row.age;
					}
				},				
			   {field:'birthday',title:'出生日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.birthday);
					}
				},				
			   {field:'telephone',title:'联系电话',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.telephone;
					}
				},				
			   {field:'area',title:'所属片区',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.areapage.areaname;
					}
				},				
			   {field:'enterdate',title:'入职日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.enterdate);
					}
				},				
			   {field:'address',title:'家庭地址',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.address;
					}
				},				
			   {field:'rate',title:'提成比例',align:'center',sortable:true,width:50,
					formatter:function(value,row,index){
						return row.rate;
					}
				},				
			   {field:'isactive',title:'是否在职',align:'center',sortable:true,width:70,
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

		accounterAddForm = $('#accounterAddForm').form({
			url : 'accounterAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					accounterAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		accounterAddDialog = $('#accounterAddDialog').show().dialog({
			title : '添加业务员信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
				  if (accounterAddForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						accounterAddForm.submit();
					}
				  }
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'accounterAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'accounterAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		accounterEditForm = $('#accounterEditForm').form({
			url : 'accounterAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					accounterEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		accounterEditDialog = $('#accounterEditDialog').show().dialog({
			title : '编辑业务员信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
				  if (accounterEditForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						accounterEditForm.submit();
					}
				  }
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'accounterAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'accounterAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '业务员信息描述',
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
		accounterAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		accounterAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].accountid);
					}
					$.ajax({
						url : 'accounterAction!delete.action',
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
				url : 'accounterAction!showDesc.action',
				data : {
					accountid : rows[0].accountid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					accounterEditForm.find('input,textarea').val('');
					accounterEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					accounterEditDialog.dialog('open');
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
			url : 'accounterAction!showDesc.action',
			data : {
				accountid : row.accountid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有业务员信息描述！', 'error');
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
					<td>业务员姓名</td>
					<td><input name="accountname" style="width:120px;" class="easyui-validatebox"/></td>
					<td>性别</td>
					<td><input name="sex" style="width:120px;" class="easyui-validatebox"/></td>
					<td>年龄</td>
					<td><input name="age" style="width:120px;" class="easyui-numberbox"/></td>
					<td>出生日期</td>
					<td><input name="birthday" style="width:120px;" class="easyui-datebox"/></td>
					<td>联系电话</td>
					<td><input name="telephone" style="width:120px;" class="easyui-validatebox"/></td>
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

	<div id="accounterAddDialog" style="display: none;width: 530px;height: 255px;padding:10px;" align="center">
		<form id="accounterAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">业务员姓名</td>
		 						<td class="tdRight">
									<input name="accountname" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写业务员姓名"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">性别</td>
		 						<td class="tdRight">
		 						    <select id="sexAdd" name="sex" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=001',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写性别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">年龄</td>
		 						<td class="tdRight">
									<input name="age" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:0,max:99" missingMessage="请填写年龄"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">出生日期</td>
		 						<td class="tdRight">
									<input name="birthday" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写出生日期"  style="width: 159px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系电话</td>
		 						<td class="tdRight">
									<input name="telephone" type="text" maxlength="15" class="easyui-validatebox" data-options="required:true" missingMessage="请填写联系电话"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">所属片区</td>
		 						<td class="tdRight">
		 						   <select id="areaAdd" name="area" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'areaAction!combox.action',
								      	 valueField:'areacode',
								         textField:'areaname',
						                editable:false
		 						       "  missingMessage="请填写所属片区"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">入职日期</td>
		 						<td class="tdRight">
									<input name="enterdate" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写入职日期"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">家庭地址</td>
		 						<td class="tdRight">
									<input name="address" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写家庭地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">提成比例(%)</td>
		 						<td class="tdRight">
									<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:3" missingMessage="请填写提成比例"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">是否在职</td>
		 						<td class="tdRight">
		 						    <select id="isactiveAdd" name="isactive" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否在职"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="accounterEditDialog" style="display: none;width: 530px;height: 255px;padding:10px;" align="center">
		<form id="accounterEditForm" method="post">
			<input type="hidden" name="accountid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">业务员姓名</td>
		 						<td class="tdRight">
								<input name="accountname" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写业务员姓名"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">性别</td>
		 						<td class="tdRight">
		 						   <select id="sexEdit" name="sex" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=001',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写性别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">年龄</td>
		 						<td class="tdRight">
								<input name="age" type="text" maxlength="2" class="easyui-numberbox" data-options="required:true,precision:0,max:99" missingMessage="请填写年龄"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">出生日期</td>
		 						<td class="tdRight">
								<input name="birthday" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写出生日期"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系电话</td>
		 						<td class="tdRight">
								<input name="telephone" type="text" maxlength="15" class="easyui-validatebox" data-options="required:true" missingMessage="请填写联系电话"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">所属片区</td>
		 						<td class="tdRight">
		 						   <select id="areaEdit" name="area" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'areaAction!combox.action',
								      	 valueField:'areacode',
								         textField:'areaname',
						                editable:false
		 						       "  missingMessage="请填写所属片区"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">入职日期</td>
		 						<td class="tdRight">
								<input name="enterdate" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写入职日期"  style="width: 159px;"/>
								</td>
		 						<td class="tdLeft">家庭地址</td>
		 						<td class="tdRight">
								<input name="address" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写家庭地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">提成比例(%)</td>
		 						<td class="tdRight">
								<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写提成比例"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">是否在职</td>
		 						<td class="tdRight">
		 						   <select id="isactiveEdit" name="isactive" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否在职"  style="width: 159px;">
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