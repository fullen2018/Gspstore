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
   .comboxstock{
	   width:380px;
	   border:0px solid #cfcfcf;
	   border-collapse: collapse;
	   margin:0px;
	   table-layout:fixed;
	}
	.comboxstock td{
	   overflow:hidden;
	}
</style>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var customerPriceAddDialog;
	var customerPriceAddForm;
	var cdescAdd;
	var customerPriceEditDialog;
	var customerPriceEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'customerPriceAction!datagrid.action',
			title : '客户品种价格列表',
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
			idField : 'flowid',
			sortName : '',
			sortOrder : 'desc',
			columns : [ [ 
			   {field:'articlename',title:'品种ID',align:'center',sortable:false,width:80,
					formatter:function(value,row,index){
						return row.articleid;
					}
				},				
			   {field:'articleid',title:'品种名称',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.articlepage.articlename;
					}
				},				
			   {field:'customername',title:'客户ID',align:'center',sortable:false,width:80,
					formatter:function(value,row,index){
						return row.customerid;
					}
				},				
			   {field:'customerid',title:'客户名称',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.customerpage.customername;
					}
				},				
			   {field:'saleprice',title:'最近销售价',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.saleprice;
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

		customerPriceAddForm = $('#customerPriceAddForm').form({
			url : 'customerPriceAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					customerPriceAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		customerPriceAddDialog = $('#customerPriceAddDialog').show().dialog({
			title : '添加客户品种价格',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						customerPriceAddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'customerPriceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'customerPriceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		customerPriceEditForm = $('#customerPriceEditForm').form({
			url : 'customerPriceAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					customerPriceEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		customerPriceEditDialog = $('#customerPriceEditDialog').show().dialog({
			title : '编辑客户品种价格',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						customerPriceEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'customerPriceAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'customerPriceAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '客户品种价格描述',
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
		customerPriceAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		customerPriceAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].flowid);
					}
					$.ajax({
						url : 'customerPriceAction!delete.action',
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
				url : 'customerPriceAction!showDesc.action',
				data : {
					flowid : rows[0].flowid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					customerPriceEditForm.find('input,textarea').val('');
					customerPriceEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					customerPriceEditDialog.dialog('open');
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
			url : 'customerPriceAction!showDesc.action',
			data : {
				flowid : row.flowid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有客户品种价格描述！', 'error');
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
					<td>品种名称</td>
					<td>
					    <select id="articleid" name="articleid" type="text"  class="easyui-combobox" data-options="
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:420,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return '<table  class=comboxstock><tr><td >'+row.articlename+'</td><td width=60px;>'+row.articlespec+'</td><td width=160px;>'+row.factory+'</td><td width=50px;>'+row.unitname+'</td></tr></table>';
										}
		 						       "  missingMessage="请填写品种"  style="width:140px;" >
		 					       </select>
					</td>
					<td>客户名称</td>
					<td>
					    <select id="customerid" name="customerid" type="text"  class="easyui-combobox" data-options="
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 163px;">
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

	<div id="customerPriceAddDialog" style="display: none;width: 400px;height: 200px;padding:10px;" align="center">
		<form id="customerPriceAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">品种名称</td>
		 						<td class="tdRight">
		 						    <select id="articleid" name="articleid" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:420,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return '<table  class=comboxstock><tr><td >'+row.articlename+'</td><td width=60px;>'+row.articlespec+'</td><td width=160px;>'+row.factory+'</td><td width=50px;>'+row.unitname+'</td></tr></table>';
										}
		 						       "  missingMessage="请填写品种"  style="width:160px;" >
		 					       </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">客户名称</td>
		 						<td class="tdRight">
		 						   <select id="customerid" name="customerid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 160px;">
		 					        </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">销售价</td>
		 						<td class="tdRight">
									<input name="saleprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写销售价"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="customerPriceEditDialog" style="display: none;width: 400px;height: 200px;padding:10px;" align="center">
		<form id="customerPriceEditForm" method="post">
			<input type="hidden" name="flowid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">品种名称</td>
		 						<td class="tdRight">
		 						    <select id="articleid" name="articleid" type="text" disabled="disabled"  class="easyui-combobox" data-options="required:true,
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:420,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return '<table  class=comboxstock><tr><td >'+row.articlename+'</td><td width=60px;>'+row.articlespec+'</td><td width=160px;>'+row.factory+'</td><td width=50px;>'+row.unitname+'</td></tr></table>';
										}
		 						       "  missingMessage="请填写品种"  style="width:160px;" >
		 					       </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">客户名称</td>
		 						<td class="tdRight">
		 						   <select id="customerid" name="customerid" type="text" disabled="disabled"  class="easyui-combobox" data-options="required:true,
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 160px;">
		 					        </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">销售价</td>
		 						<td class="tdRight">
								<input name="saleprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2" missingMessage="请填写销售价"  style="width: 155px;"/>
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