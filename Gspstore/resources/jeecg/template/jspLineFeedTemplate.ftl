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
	var ${entityName?uncap_first}AddDialog;
	var ${entityName?uncap_first}AddForm;
	var cdescAdd;
	var ${entityName?uncap_first}EditDialog;
	var ${entityName?uncap_first}EditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : '${entityName?uncap_first}Action!datagrid.action',
			title : '${ftl_description}列表',
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
			idField : '${jeecg_table_id}',
			sortName : '',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.${jeecg_table_id};
						}
					},
			<#list columns as po>
			   {field:'${po.fieldName}',title:'<#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if>',align:'center',sortable:true,
					formatter:function(value,row,index){
					<#if po.fieldType == 'date' || po.fieldType == 'datetime'>
						return dateFormatYMD(row.${po.fieldName});
				    <#else>
						return row.${po.fieldName};
					</#if>
					}
				}<#if po_has_next>,<#else></#if>
				
			</#list>
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

		${entityName?uncap_first}AddForm = $('#${entityName?uncap_first}AddForm').form({
			url : '${entityName?uncap_first}Action!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					${entityName?uncap_first}AddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		${entityName?uncap_first}AddDialog = $('#${entityName?uncap_first}AddDialog').show().dialog({
			title : '添加${ftl_description}',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						${entityName?uncap_first}AddForm.submit();
					}
					
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : '${entityName?uncap_first}Action!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : '${entityName?uncap_first}Action!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		${entityName?uncap_first}EditForm = $('#${entityName?uncap_first}EditForm').form({
			url : '${entityName?uncap_first}Action!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					${entityName?uncap_first}EditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		${entityName?uncap_first}EditDialog = $('#${entityName?uncap_first}EditDialog').show().dialog({
			title : '编辑${ftl_description}',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						${entityName?uncap_first}EditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : '${entityName?uncap_first}Action!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : '${entityName?uncap_first}Action!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '${ftl_description}描述',
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
		${entityName?uncap_first}AddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		${entityName?uncap_first}AddDialog.dialog('open');
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].${jeecg_table_id});
					}
					$.ajax({
						url : '${entityName?uncap_first}Action!delete.action',
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
				url : '${entityName?uncap_first}Action!showDesc.action',
				data : {
					${jeecg_table_id} : rows[0].${jeecg_table_id}
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					${entityName?uncap_first}EditForm.find('input,textarea').val('');
					${entityName?uncap_first}EditForm.form('load', response);
					$('div.validatebox-tip').remove();
					${entityName?uncap_first}EditDialog.dialog('open');
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
			url : '${entityName?uncap_first}Action!showDesc.action',
			data : {
				${jeecg_table_id} : row.${jeecg_table_id}
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有${ftl_description}描述！', 'error');
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
				<#list columns as po>
					<#if  po_index  lte search_field_num>
					<td>${po.filedComment}</td>
					<td><input name="${po.fieldName}" style="width:120px;" class="${po.classType}"/></td>
					</#if>
				</#list>
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

	<div id="${entityName?uncap_first}AddDialog" style="display: none;width: 500px;height: 300px;padding:10px;" align="center">
		<form id="${entityName?uncap_first}AddForm" method="post">
			<table class="tableForm">
					<#list columns as po>
							<#if po_index%field_row_num==0>
							<tr>
		 					</#if>
		 						<td class="tdLeft"><#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if></td>
		 						<td class="tdRight">
									<input name="${po.fieldName}" type="text" maxlength="${po.charmaxLength}" class="${po.classType}" data-options="${po.optionType}" missingMessage="请填写<#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if>"  style="width: 155px;"/>
								</td>
							<#if (po_index+1)%field_row_num==0>
							</tr>
							<#else>
							<#if !po_has_next>
							</tr>
							</#if>
		 					</#if>
					</#list>
			</table>
		</form>
	</div>

	<div id="${entityName?uncap_first}EditDialog" style="display: none;width: 500px;height: 300px;padding:10px;" align="center">
		<form id="${entityName?uncap_first}EditForm" method="post">
			<input type="hidden" name="${jeecg_table_id}" />
			<table class="tableForm">
					<#list columns as po>
							<#if po_index%field_row_num==0>
							<tr>
		 					</#if>
		 						<td class="tdLeft"><#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if></td>
		 						<td class="tdRight">
							<#if  po_index  lte field_required_num>
								<input name="${po.fieldName}" type="text" maxlength="${po.charmaxLength}" class="${po.classType}" data-options="${po.optionType}" missingMessage="请填写<#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if>"  style="width: 155px;"/>
							<#else>
								<input name="${po.fieldName}" type="text" maxlength="${po.charmaxLength}" class="${po.classType}" data-options="" missingMessage="请填写<#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if>"  style="width: 155px;"/>
	                    	</#if>
								</td>
							<#if (po_index+1)%field_row_num==0>
							</tr>
							<#else>
							<#if !po_has_next>
							</tr>
							</#if>
		 					</#if>
					</#list>
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