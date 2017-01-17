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
	var feeAddDialog;
	var feeAddForm;
	var cdescAdd;
	var feeEditDialog;
	var feeEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'feeAction!datagrid.action',
			title : '财务费用信息列表',
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
			idField : 'feeid',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.feeid;
						}
					},
			   {field:'feeid',title:'费用单号',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.feeid;
					}
				},				
			   {field:'subjectname',title:'费用科目',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.subjectpage.subjectname+'&nbsp;('+dictGetName('006',row.subjectpage.subjecttype)+')';
					}
				},				
			   {field:'amount',title:'费用金额',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.amount;
					}
				},				
			   {field:'feetype',title:'支付形式',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dictGetName('007',row.feetype);
					}
				},				
			   {field:'feedate',title:'费用日期',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dateFormatYMD(row.feedate);
					}
				},				
			   {field:'feeeman',title:'经手人',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.feeeman;
					}
				},				
			   {field:'meno',title:'备注',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.meno;
					}
				},				
			   {field:'modifiername',title:'修改人姓名',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.modifiername;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dateFormatYMD(row.modifydate);
					}
				},				
			   {field:'crtusername',title:'创建人姓名',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.crtusername;
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

		feeAddForm = $('#feeAddForm').form({
			url : 'feeAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					feeAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		feeAddDialog = $('#feeAddDialog').show().dialog({
			title : '添加财务费用信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						feeAddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'feeAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'feeAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		feeEditForm = $('#feeEditForm').form({
			url : 'feeAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					feeEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		feeEditDialog = $('#feeEditDialog').show().dialog({
			title : '编辑财务费用信息',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						feeEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'feeAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'feeAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '财务费用信息描述',
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
		feeAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		feeAddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].feeid);
					}
					$.ajax({
						url : 'feeAction!delete.action',
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
				url : 'feeAction!showDesc.action',
				data : {
					feeid : rows[0].feeid
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					feeEditForm.find('input,textarea').val('');
					feeEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					feeEditDialog.dialog('open');
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
			url : 'feeAction!showDesc.action',
			data : {
				feeid : row.feeid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有财务费用信息描述！', 'error');
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
					<td>费用科目</td>
					<td>
					     <select id="subjectidAdd" name="subjectid" type="text"  class="easyui-combobox" data-options="
		 						        url:'subjectAction!combox.action',
								      	 valueField:'subjectid',
								         textField:'subjectname',
								         panelWidth:300,
						                editable:false
		 						       "  missingMessage="请填写费用科目"  style="width: 130px;">
		 						     </select>
					</td>
					<td>费用金额></td>
					<td><input name="amount" style="width:80px;" class="easyui-numberbox"/></td>
					<td>支付形式</td>
					<td>
					   <select id="feetype" name="feetype" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=007',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="支付形式"  style="width: 50px;">
		 				</select>
					</td>
					<td>费用日期</td>
					<td><input name="beginFeedate" style="width:90px;" class="easyui-datebox"/>
					<input name="endFeedate" style="width:90px;" class="easyui-datebox"/>
					</td>
					<td>经手人</td>
					<td><input name="feeeman" style="width:80px;" class="easyui-validatebox"/></td>
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

	<div id="feeAddDialog" style="display: none;width: 370px;height: 240px;padding:10px;" align="center">
		<form id="feeAddForm" method="post">
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">费用科目</td>
		 						<td class="tdRight">
		 						     <select id="subjectidAdd" name="subjectid" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'subjectAction!combox.action',
								      	 valueField:'subjectid',
								         textField:'subjectname',
						                editable:false
		 						       "  missingMessage="请填写费用科目"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">费用金额</td>
		 						<td class="tdRight">
									<input name="amount" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写费用金额"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">支付方式</td>
		 						<td class="tdRight">
		 						    <select id="feetypeAdd" name="feetype" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=007',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写支付方式"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">费用日期</td>
		 						<td class="tdRight">
									<input name="feedate" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写费用日期"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">经手人</td>
		 						<td class="tdRight">
									<input name="feeeman" type="text" maxlength="30" class="easyui-validatebox" data-options="required:true" missingMessage="请填写经手人"  style="width: 155px;"/>
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

	<div id="feeEditDialog" style="display: none;width: 370px;height:240px;padding:10px;" align="center">
		<form id="feeEditForm" method="post">
			<input type="hidden" name="feeid" />
			<table class="tableForm">
							<tr>
		 						<td class="tdLeft">费用科目</td>
		 						<td class="tdRight">
		 						    <select id="subjectidAdd" name="subjectid" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'subjectAction!combox.action',
								      	 valueField:'subjectid',
								         textField:'subjectname',
						                editable:false
		 						       "  missingMessage="请填写费用科目"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">费用金额</td>
		 						<td class="tdRight">
									<input name="amount" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写费用金额"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">支付方式</td>
		 						<td class="tdRight">
		 						    <select id="feetypeAdd" name="feetype" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=007',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写支付方式"  style="width: 159px;">
		 						     </select>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">费用日期</td>
		 						<td class="tdRight">
									<input name="feedate" type="text" maxlength="" class="easyui-datebox" data-options="required:true" missingMessage="请填写费用日期"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">经手人</td>
		 						<td class="tdRight">
									<input name="feeeman" type="text" maxlength="30" class="easyui-validatebox" data-options="required:true" missingMessage="请填写经手人"  style="width: 155px;"/>
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