<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<style type="text/css">
   .tdLeft{
      width:16%;
      text-align: right;
   }
   .tdRight{
      width:34%;
      text-align: left;
   }
</style>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var articleAddDialog;
	var articleAddForm;
	var cdescAdd;
	var articleEditDialog;
	var articleEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'articleAction!datagrid.action',
			title : '品种信息列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			fitColumns : false,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : true,
			border : false,
			idField : 'articleid',
			sortName : 'articlealias',
			singleSelect:true,
			sortOrder : 'asc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.articleid;
						}
					},
			   {field:'articlename',title:'品种名称',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.articlename;
					}
				},				
			   {field:'articlealias',title:'品种别名',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.articlealias;
					}
				},				
			   {field:'articlespec',title:'品种规格',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.articlespec;
					}
				},				
			   {field:'factory',title:'生产厂家',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.factory;
					}
				},				
			   {field:'barcode',title:'条码',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.barcode;
					}
				},				
			   {field:'fileno',title:'批准文号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.fileno;
					}
				},				
			   {field:'retailprice',title:'统一零售价',align:'center',sortable:true,width:50,
					formatter:function(value,row,index){
						return row.retailprice;
					}
				},				
			   {field:'lastinprice',title:'最新进价',align:'center',sortable:true,width:50,
					formatter:function(value,row,index){
						return row.lastinprice;
					}
				},				
			   {field:'unit',title:'单位',align:'center',sortable:true,width:40,
					formatter:function(value,row,index){
						return dictGetName('003',row.unit);
					}
				},				
			   {field:'catelog',title:'产品类别',align:'center',sortable:true,width:40,
					formatter:function(value,row,index){
						return dictGetName('005',row.catelog);
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
				{field:'meno',title:'备注',align:'center',sortable:true,width:140,
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

		articleAddForm = $('#articleAddForm').form({
			url : 'articleAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					articleAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		articleAddDialog = $('#articleAddDialog').show().dialog({
			title : '添加品种信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
				  if (articleAddForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						articleAddForm.submit();
					}
				   }
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'articleAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'articleAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		articleEditForm = $('#articleEditForm').form({
			url : 'articleAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					articleEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		articleEditDialog = $('#articleEditDialog').show().dialog({
			title : '编辑品种信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
				  if (articleEditForm.form('validate')){
					if(formFlag){
						formFlag = false ;
						articleEditForm.submit();
					}
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'articleAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'articleAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '品种信息描述',
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
		articleAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		articleAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].articleid);
					}
					$.ajax({
						url : 'articleAction!delete.action',
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
				url : 'articleAction!showDesc.action',
				data : {
					articleid : rows[0].articleid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					articleEditForm.find('input,textarea').val('');
					articleEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					articleEditDialog.dialog('open');
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
			url : 'articleAction!showDesc.action',
			data : {
				articleid : row.articleid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有品种信息描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
	
	function changePinYingAdd(){
	    var pingying = makePy($("#articlenameAdd").val());
	    $("#articlealiasAdd").val(pingying);
	}
	function changePinYingEdit(){
	    var pingying = makePy($("#articlenameEdit").val());
	    $("#articlealiasEdit").val(pingying);
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="过滤条件" collapsed="true"  style="height: 60px;overflow: hidden;display: none;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td>品种名称</td>
					<td><input name="articlename" style="width:120px;" class="easyui-validatebox"/></td>
					<td>品种别名</td>
					<td><input name="articlealias" style="width:120px;" class="easyui-validatebox"/></td>
					<td>品种规格</td>
					<td><input name="articlespec" style="width:120px;" class="easyui-validatebox"/></td>
					<td>生产厂家</td>
					<td><input name="factory" style="width:120px;" class="easyui-validatebox"/></td>
					<td>条码</td>
					<td><input name="barcode" style="width:120px;" class="easyui-validatebox"/></td>
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

	<div id="articleAddDialog" style="display: none;width: 530px;height: 260px;padding:10px;" align="center">
		<form id="articleAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">品种名称</td>
		 						<td class="tdRight">
									<input id="articlenameAdd" name="articlename" type="text" maxlength="100" onblur="changePinYingAdd()" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种名称"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">品种别名</td>
		 						<td class="tdRight">
									<input id="articlealiasAdd" name="articlealias" type="text" maxlength="50" readonly="readonly" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种别名"  style="width: 155px;background-color: #cfcfcf;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">品种规格</td>
		 						<td class="tdRight">
									<input name="articlespec" type="text" maxlength="50" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种规格"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">生产厂家</td>
		 						<td class="tdRight">
									<input name="factory" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写生产厂家"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">条码</td>
		 						<td class="tdRight">
									<input name="barcode" type="text" maxlength="40" class="easyui-validatebox" data-options="" missingMessage="请填写条码"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">单位</td>
		 						<td class="tdRight">
		 						    <select id="unitAdd" name="unit" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=003&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写单位"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">统一零售价</td>
		 						<td class="tdRight">
								<input name="retailprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写统一零售价"  style="width: 155px;"/>
								</td>
								<td class="tdLeft">产品类别</td>
		 						<td class="tdRight">
		 						    <select id="catelogAdd" name="catelog" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=005&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写产品类别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
							    <td class="tdLeft">批准文号</td>
		 						<td class="tdRight">
								<input name="fileno" type="text" maxlength="80" class="easyui-validatebox" data-options="" missingMessage="请填写批准文号"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">备注</td>
		 						<td class="tdRight">
								<input name="meno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写备注"  style="width: 155px;"/>
								</td>
							</tr>
							<tr style="display: none;">
		 						<td class="tdLeft">是否提成</td>
		 						<td class="tdRight">
		 						    <select id="israteAdd" name="israte" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否提成"  style="width: 159px;">
		 						     </select>
								</td>
		 						<td class="tdLeft">提成比例</td>
		 						<td class="tdRight">
									<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="precision:3,max:99" missingMessage="请填写提成比例"  style="width: 155px;"/>
								</td>
							</tr>
							<tr style="display: none;">
							    <td class="tdLeft">批发价</td>
		 						<td class="tdRight">
									<input name="pfprice" type="text" maxlength="" class="easyui-numberbox" value="0.0" data-options="precision:2,groupSeparator:','" missingMessage="请填写批发价"  style="width: 155px;"/>
								</td>
							    <td class="tdLeft">批发价</td>
		 						<td class="tdRight">
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="articleEditDialog" style="display: none;width: 530px;height: 260px;padding:10px;" align="center">
		<form id="articleEditForm" method="post">
			<input type="hidden" name="articleid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">品种名称</td>
		 						<td class="tdRight">
									<input id="articlenameEdit" name="articlename" type="text" maxlength="100" onblur="changePinYingEdit()" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种名称"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">品种别名</td>
		 						<td class="tdRight">
									<input id="articlealiasEdit" name="articlealias" type="text" maxlength="50" readonly="readonly" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种别名"  style="width: 155px;background-color: #cfcfcf;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">品种规格</td>
		 						<td class="tdRight">
								<input name="articlespec" type="text" maxlength="50" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种规格"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">生产厂家</td>
		 						<td class="tdRight">
								<input name="factory" type="text" maxlength="80" class="easyui-validatebox" data-options="required:true" missingMessage="请填写生产厂家"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">条码</td>
		 						<td class="tdRight">
								<input name="barcode" type="text" maxlength="40" class="easyui-validatebox" data-options="" missingMessage="请填写条码"  style="width: 155px;"/>
								</td>
								<td class="tdLeft">单位</td>
		 						<td class="tdRight">
		 						    <select id="unitEdit" name="unit" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=003&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写单位"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">统一零售价</td>
		 						<td class="tdRight">
								<input name="retailprice" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写统一零售价"  style="width: 155px;"/>
								</td>
								<td class="tdLeft">产品类别</td>
		 						<td class="tdRight">
		 						    <select id="catelogEdit" name="catelog" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=005&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写产品类别"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							
							<tr>
							    <td class="tdLeft">批准文号</td>
		 						<td class="tdRight">
								<input name="fileno" type="text" maxlength="80" class="easyui-validatebox" data-options="" missingMessage="请填写批准文号"  style="width: 155px;"/>
								</td>
		 						<td class="tdLeft">备注</td>
		 						<td class="tdRight">
								<input name="meno" type="text" maxlength="100" class="easyui-validatebox" data-options="" missingMessage="请填写备注"  style="width: 155px;"/>
								</td>
							</tr>
							<tr style="display: none;">
		 						<td class="tdLeft">是否提成</td>
		 						<td class="tdRight">
		 						    <select id="israteAdd" name="israte" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004&timestamp=<%=new Date().getTime() %>',
						                valueField : 'paramValue',
						                panelHeight:45,
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写是否提成"  style="width: 159px;">
		 						     </select>
								</td>
		 						<td class="tdLeft">提成比例</td>
		 						<td class="tdRight">
									<input name="rate" type="text" maxlength="" class="easyui-numberbox" data-options="precision:3,max:99" missingMessage="请填写提成比例"  style="width: 155px;"/>
								</td>
							</tr>
							<tr style="display: none;">
							    <td class="tdLeft">批发价</td>
		 						<td class="tdRight">
									<input name="pfprice" type="text" maxlength="" class="easyui-numberbox" value="0.0" data-options="precision:2,groupSeparator:','" missingMessage="请填写批发价"  style="width: 155px;"/>
								</td>
							    <td class="tdLeft">批发价</td>
		 						<td class="tdRight">
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