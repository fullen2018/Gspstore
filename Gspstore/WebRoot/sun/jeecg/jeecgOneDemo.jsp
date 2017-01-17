<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script language="javascript" type="text/javascript" src="<%=basePath%>/jslib/datepicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>/main/js/tool/dataformat.js"></script>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'jeecgOneDemoAction!datagrid.action',
			title : '添加修改页面拆开（弹出详细页面编辑数据）',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			nowrap : true,
			border : false,
			idField : 'obid',
			sortName : 'createDt',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.obid;
						}
					},
			   {field:'name',title:'用户名',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.name;
					}
				},				
			   {field:'age',title:'年龄',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.age;
					}
				},				
			   {field:'salary',title:'工资',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.salary;
					}
				},				
			   {field:'birthday',title:'生日',align:'center',sortable:true,
					formatter:function(value,row,index){
						return new Date().format("yyyy-MM-dd",row.birthday);
					}
				},				
			   {field:'registerDt',title:'注册时间',align:'center',sortable:true,
					formatter:function(value,row,index){
						return new Date().format("yyyy-MM-dd hh:mm:ss",row.registerDt);
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
	});
	function add() {
		datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/sun/jeecg/jeecgOneDemo-add.jsp',
			width : 500,
			height : 400,
			modal : true,
			title : '添加单表模型',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#jeecgOneDemoAddForm').form({
						url : '${pageContext.request.contextPath}/jeecgOneDemoAction!add.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									datagrid.datagrid('reload');
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
					$('#jeecgOneDemoAddForm').submit();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	
	function edit() {
			var rows = datagrid.datagrid('getSelections');
			if (rows.length == 1) {
				$('<div/>').dialog({
					href : '${pageContext.request.contextPath}/jeecgOneDemoAction!toEdit.action?obid=' + rows[0].obid,
					width : 500,
					height : 400,
					modal : true,
					title : '编辑单表模型',
					buttons : [ {
						text : '编辑',
						iconCls : 'icon-edit',
						handler : function() {
							var d = $(this).closest('.window-body');
							$('#jeecgOneDemoEditForm').form({
								url : '${pageContext.request.contextPath}/jeecgOneDemoAction!edit.action',
								success : function(result) {
									try {
										var r = $.parseJSON(result);
										if (r.success) {
											datagrid.datagrid('load');
											d.dialog('destroy');
										}
										$.messager.show({
											title : '提示',
											msg : r.msg
										});
									} catch (e) {
										$.messager.alert('提示', result);
									}
								}
							});
							$('#jeecgOneDemoEditForm').submit();
						}
					} ],
					onClose : function() {
						$(this).dialog('destroy');
					},
					onLoad : function() {
					}
				});
			} else if (rows.length > 1) {
				$.messager.alert('提示', '同一时间只能编辑一条记录！', 'error');
			} else {
				$.messager.alert('提示', '请选择要编辑的记录！', 'error');
			}
		}
		
		function del() {
			var rows = datagrid.datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].obid);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/jeecgOneDemoAction!delete.action',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(result) {
								if (result.success) {
									datagrid.datagrid('load');
									datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
								}
								$.messager.show({
									title : '提示',
									msg : result.msg
								});
							}
						});
					}
				});
			} else {
				$.messager.show({
					title : '提示',
					msg : '请勾选要删除的记录！'
				});
			}
		}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="过滤条件" collapsed="true"  style="height: 60px;overflow: hidden;display: none;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td>用户名</td>
					<td><input name="name" style="width:120px;" class="easyui-validatebox"/></td>
					<td>年龄</td>
					<td><input name="age" style="width:120px;" class="easyui-numberbox"/></td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu"
			style="width: 120px; display: none;">
			<div onclick="add();" data-options="iconCls:'icon-add'">
				增加
			</div>
			<div onclick="del();" data-options="iconCls:'icon-remove'">
				删除
			</div>
			<div onclick="edit();" data-options="iconCls:'icon-edit'">
				编辑
			</div>
	</div>
</body>
</html>