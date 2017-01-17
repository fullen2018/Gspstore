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
	var companyAddDialog;
	var companyAddForm;
	var cdescAdd;
	var companyEditDialog;
	var companyEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'companyAction!datagrid.action',
			title : '公司信息列表',
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
			idField : 'companyno',
			singleSelect:true,
			sortName : '',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.companyno;
						}
					},
			   {field:'companyname',title:'公司名称',align:'center',sortable:true,width:150,
					formatter:function(value,row,index){
						return row.companyname;
					}
				},				
			   {field:'telephone',title:'电话',align:'center',sortable:false,width:150,
					formatter:function(value,row,index){
						return row.telephone;
					}
				},				
			   {field:'fax',title:'传真',align:'center',sortable:false,width:100,
					formatter:function(value,row,index){
						return row.fax;
					}
				},				
			   {field:'email',title:'邮箱',align:'center',sortable:false,width:100,
					formatter:function(value,row,index){
						return row.email;
					}
				},				
			   {field:'contactman',title:'联系人',align:'center',sortable:false,width:90,
					formatter:function(value,row,index){
						return row.contactman;
					}
				},				
			   {field:'billmeno',title:'票据备注',align:'center',sortable:false,width:190,
					formatter:function(value,row,index){
						return row.billmeno;
					}
				},				
			   {field:'address',title:'地址',align:'center',sortable:false,width:250,
					formatter:function(value,row,index){
						return row.address;
					}
				}				
			 ] ],
			toolbar : [ {
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

		companyAddForm = $('#companyAddForm').form({
			url : 'companyAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					companyAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		companyAddDialog = $('#companyAddDialog').show().dialog({
			title : '添加公司信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						companyAddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'companyAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'companyAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		companyEditForm = $('#companyEditForm').form({
			url : 'companyAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					companyEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		companyEditDialog = $('#companyEditDialog').show().dialog({
			title : '编辑公司信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						companyEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'companyAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'companyAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '公司信息描述',
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
		companyAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		companyAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].companyno);
					}
					$.ajax({
						url : 'companyAction!delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(response) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
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
				url : 'companyAction!showDesc.action',
				data : {
					companyno : rows[0].companyno
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					companyEditForm.find('input,textarea').val('');
					companyEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					companyEditDialog.dialog('open');
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
			url : 'companyAction!showDesc.action',
			data : {
				companyno : row.companyno
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有公司信息描述！', 'error');
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
					<td>公司名称</td>
					<td><input name="companyname" style="width:120px;" class="easyui-validatebox"/></td>
					<td>电话</td>
					<td><input name="telephone" style="width:120px;" class="easyui-validatebox"/></td>
					<td>传真</td>
					<td><input name="fax" style="width:120px;" class="easyui-validatebox"/></td>
					<td>邮箱</td>
					<td><input name="email" style="width:120px;" class="easyui-validatebox"/></td>
					<td>地址</td>
					<td><input name="address" style="width:120px;" class="easyui-validatebox"/></td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="edit();" iconCls="icon-edit">编辑</div>
	</div>

	<div id="companyAddDialog" style="display: none;width: 400px;height: 270px;padding:10px;" align="center">
		<form id="companyAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">公司名称</td>
		 						<td class="tdRight">
									<input name="companyname" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写公司名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">电话</td>
		 						<td class="tdRight">
									<input name="telephone" type="text" maxlength="30" class="easyui-validatebox" data-options="" missingMessage="请填写电话"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">传真</td>
		 						<td class="tdRight">
									<input name="fax" type="text" maxlength="20" class="easyui-validatebox" data-options="" missingMessage="请填写传真"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">邮箱</td>
		 						<td class="tdRight">
									<input name="email" type="text" maxlength="40" class="easyui-validatebox" data-options="" missingMessage="请填写邮箱"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">地址</td>
		 						<td class="tdRight">
									<input name="address" type="text" maxlength="150" class="easyui-validatebox" data-options="" missingMessage="请填写地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系人</td>
		 						<td class="tdRight">
									<input name="contactman" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写联系人"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">发票备注</td>
		 						<td class="tdRight">
									<input name="billmeno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写发票备注"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="companyEditDialog" style="display: none;width: 400px;height: 270px;padding:10px;" align="center">
		<form id="companyEditForm" method="post">
			<input type="hidden" name="companyno" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">公司名称</td>
		 						<td class="tdRight">
								<input name="companyname" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写公司名称"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">电话</td>
		 						<td class="tdRight">
								<input name="telephone" type="text" maxlength="30" class="easyui-validatebox" data-options="" missingMessage="请填写电话"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">传真</td>
		 						<td class="tdRight">
								<input name="fax" type="text" maxlength="20" class="easyui-validatebox" data-options="" missingMessage="请填写传真"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">邮箱</td>
		 						<td class="tdRight">
								<input name="email" type="text" maxlength="40" class="easyui-validatebox" data-options="" missingMessage="请填写邮箱"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">地址</td>
		 						<td class="tdRight">
								<input name="address" type="text" maxlength="150" class="easyui-validatebox" data-options="" missingMessage="请填写地址"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">联系人</td>
		 						<td class="tdRight">
								<input name="contactman" type="text" maxlength="50" class="easyui-validatebox" data-options="" missingMessage="请填写联系人"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">发票备注</td>
		 						<td class="tdRight">
									<input name="billmeno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写发票备注"  style="width: 155px;"/>
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