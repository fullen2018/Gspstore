<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var showCdescDialog;
	//update-begin author:anchao for:防止form重复提交  date：20130310
	var formFlag = true;
	//update-end author:anchao for:防止form重复提交  date：20130310
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'noteAction!datagrid.action',
			title : '公告管理列表',
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
			   {field:'title',title:'标题',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.title;
					}
				},				
			   {field:'content',title:'内容',align:'center',sortable:true,
					formatter:function(value,row,index){
						return '<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="showCdesc(' + index + ');">查看详细</a>';
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

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '公告管理描述',
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
	
	//update-begin author:gaoxingang for:优化将添加和修改页面分开  date：20130309
	function add() {
		datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({     
			href : '${pageContext.request.contextPath}/com/sys/base/note-add.jsp',
			width : 500,
			height : 400,
			modal : true,
			title : '添加公告管理',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#noteAddForm').form({
						url : '${pageContext.request.contextPath}/noteAction!add.action',
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
							//update-begin author:anchao for:防止form重复提交  date：20130310
							formFlag =  true;
							//update-end author:anchao for:防止form重复提交  date：20130310
						}
					});
					//update-begin author:anchao for:防止form重复提交  date：20130310
					if(formFlag){
						formFlag =  false;
					//update-end author:anchao for:防止form重复提交  date：20130310
						$('#noteAddForm').submit();
					//update-begin author:anchao for:防止form重复提交  date：20130310
					}
					//update-end author:anchao for:防止form重复提交  date：20130310
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	//update-end author:gaoxingang for:优化将添加和修改页面分开  date：20130309
	
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].obid);
					}
					$.ajax({
						url : 'noteAction!delete.action',
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
	//update-begin author:gaoxingang for:优化将添加和修改页面分开  date：20130309
	function edit() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$('<div/>').dialog({
				href : '${pageContext.request.contextPath}/noteAction!toEdit.action?obid=' + rows[0].obid,
				width : 500,
				height : 400,
				modal : true,
				title : '编辑公告管理',
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var d = $(this).closest('.window-body');
						$('#noteEditForm').form({
							url : '${pageContext.request.contextPath}/noteAction!edit.action',
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
								//update-begin author:anchao for:防止form重复提交  date：20130310
								formFlag =  true;
								//update-end author:anchao for:防止form重复提交  date：20130310
							}
						});
						//update-begin author:anchao for:防止form重复提交  date：20130310
						if(formFlag){
							formFlag =  false;
						//update-end author:anchao for:防止form重复提交  date：20130310
							$('#noteEditForm').submit();
						//update-begin author:anchao for:防止form重复提交  date：20130310
						}
						//update-end author:anchao for:防止form重复提交  date：20130310
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
	//update-end author:gaoxingang for:优化将添加和修改页面分开  date：20130309
	
	function showCdesc(index) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[index];
		$.messager.progress({
			text : '数据加载中....',
			interval : 100
		});
		$.ajax({
			url : 'noteAction!showDesc.action',
			data : {
				obid : row.obid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				$.messager.progress('close');
				if (response && response.content) {
					showCdescDialog.find('div[name=cdesc]').html(response.content);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有公告管理描述！', 'error');
				}
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
					<td>标题</td>
					<td><input name="title" style="width:120px;" /></td>
					<td>内容</td>
					<td><input name="content" style="width:120px;" /><td>
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

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
</body>
</html>