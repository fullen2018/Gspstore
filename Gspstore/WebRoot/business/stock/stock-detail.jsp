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
	   width:470px;
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
	var stockAddDialog;
	var stockAddForm;
	var cdescAdd;
	var stockEditDialog;
	var stockEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'stockAction!datagridDetail.action',
			title : '库存明细列表',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			singleSelect:true,
			nowrap : true,
			border : false,
			idField : 'stockkey',
			sortName : '',
			sortOrder : 'desc',
			columns : [ [ 
			
			   {field:'depotid',title:'仓库名称',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'articlename',title:'品种编码',align:'center',width:80,
					formatter:function(value,row,index){
						return row.articleid;
					}
				},				
			   {field:'articleid',title:'品种名称',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.articlepage.articlename;
					}
				},				
			   {field:'articlespec',title:'品种规格',align:'center',sortable:false,width:80,
					formatter:function(value,row,index){
						return row.articlepage.articlespec;
					}
				},				
			   {field:'factory',title:'厂家',align:'center',sortable:false,width:90,
					formatter:function(value,row,index){
						return row.articlepage.factory;
					}
				},				
			   {field:'serial',title:'批号',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.serial;
					}
				},				
			   {field:'num',title:'数量',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.num;
					}
				},				
			   {field:'costprice',title:'成本价',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.costprice;
					}
				},				
			   {field:'expiredate',title:'有效期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.expiredate);
					}
				}				
			 ] ],
			toolbar : [ {
				text : '刷新',
				iconCls : 'icon-undo',
				handler : function() {
					datagrid.datagrid('reload');
				}
			}, '-' ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
			}
		});

		stockAddForm = $('#stockAddForm').form({
			url : 'stockAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					stockAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		stockAddDialog = $('#stockAddDialog').show().dialog({
			title : '添加库存信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						stockAddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		stockEditForm = $('#stockEditForm').form({
			url : 'stockAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					stockEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		stockEditDialog = $('#stockEditDialog').show().dialog({
			title : '编辑库存信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						stockEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '库存信息描述',
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
		stockAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		stockAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].stockkey);
					}
					$.ajax({
						url : 'stockAction!delete.action',
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
				url : 'stockAction!showDesc.action',
				data : {
					stockkey : rows[0].stockkey
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					stockEditForm.find('input,textarea').val('');
					stockEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					stockEditDialog.dialog('open');
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
			url : 'stockAction!showDesc.action',
			data : {
				stockkey : row.stockkey
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有库存信息描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="过滤条件" collapsed="false"  style="height: 60px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td>仓库</td>
					<td>
					 <select id="depotid" name="depotid" type="text"  class="easyui-combobox" data-options="
		 						       url:'depotAction!combox.action',
								      	 valueField:'depotid',
								         textField:'depotname',
						                editable:false
		 						       "  missingMessage="请填写供仓库"  style="width: 125px;">
		 		    </select>
					</td>
					<td>品种</td>
					<td>
					   <select  name="articleid" type="text"  class="easyui-combobox" data-options="
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:500,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   var factoryname = row.factory;
										   if (row.factory.length>7) factoryname = row.factory.substring(0,7);
										   var articlespec = row.articlespec;
										   if (row.articlespec.length>12) articlespec = row.articlespec.substring(0,12);
										   return '<table  class=comboxstock ><tr><td >'+row.articlename+'</td><td width=110px;>'+articlespec+'</td><td width=100px;>'+factoryname+'</td><td width=40px;>'+row.unitname+'</td></tr></table>';
										}
		 						       "  missingMessage="请填写品种"  style="width:130px;" >
		 				</select>
					</td>
					<td>批号</td>
					<td><input name="serial" style="width:120px;" class="easyui-validatebox"/></td>
					<td>库存数量<=</td>
					<td><input name="num" style="width:120px;" class="easyui-numberbox"/></td>
					<td>有效期限<=</td>
					<td>
					   <input id="begindate" name="ccreatedatetimeStart" style="width:90px;" class="easyui-datebox"/>
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

	<div id="stockAddDialog" style="display: none;width: 500px;height: 300px;padding:10px;" align="center">
		<form id="stockAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">仓库</td>
		 						<td class="tdRight">
									<input name="depotid" type="text" maxlength="5" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">品种</td>
		 						<td class="tdRight">
									<input name="articleid" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">批号</td>
		 						<td class="tdRight">
									<input name="serial" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写批号"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">数量</td>
		 						<td class="tdRight">
									<input name="num" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写数量"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">成本价</td>
		 						<td class="tdRight">
									<input name="costprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写成本价"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">有效期</td>
		 						<td class="tdRight">
									<input name="expiredate" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写有效期"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="stockEditDialog" style="display: none;width: 500px;height: 300px;padding:10px;" align="center">
		<form id="stockEditForm" method="post">
			<input type="hidden" name="stockkey" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">仓库</td>
		 						<td class="tdRight">
								<input name="depotid" type="text" maxlength="5" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">品种</td>
		 						<td class="tdRight">
								<input name="articleid" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">批号</td>
		 						<td class="tdRight">
								<input name="serial" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写批号"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">数量</td>
		 						<td class="tdRight">
								<input name="num" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写数量"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">成本价</td>
		 						<td class="tdRight">
								<input name="costprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写成本价"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">有效期</td>
		 						<td class="tdRight">
								<input name="expiredate" type="text" maxlength="" class="easyui-datebox" data-options="" missingMessage="请填写有效期"  style="width: 155px;"/>
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