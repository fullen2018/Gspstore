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
	var stockCheckAddDialog;
	var stockCheckAddForm;
	var cdescAdd;
	var stockCheckEditDialog;
	var stockCheckEditForm;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var formFlag = true;
	var datagridStock;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
	    datagridStock = $('#datagridStock').datagrid({
			url : 'stockAction!datagridDetail.action',
			title : '库存信息列表',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 3,
			pageList : [ 3,10, 20, 30, 40,100,500 ],
			fit : true,
			fitColumns : true,
			singleSelect :true,
			nowrap : true,
			border : false,
			idField : 'stockkey',
			sortName : 'articleid',
			sortOrder : 'desc',
			columns : [ [ 
			
			   {field:'depotid',title:'仓库名称',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'articlename',title:'品种编码',align:'center',sortable:false,width:80,
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
				text : '增加盘点单',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-',{
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
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
		datagrid = $('#datagrid').datagrid({
			url : 'stockCheckAction!datagrid.action',
			title : '库存盘点列表',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 5,
			pageList : [5, 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : true,
			border : false,
			singleSelect:true,
			idField : 'stockcheckcode',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			   {field:'stockcheckcode',title:'盘点单号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.stockcheckcode;
					}
				},				
			   {field:'depotid',title:'仓库名称',align:'center',sortable:false,width:80,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'articlename',title:'品种编码',align:'center',sortable:false,width:70,
					formatter:function(value,row,index){
						return row.articleid;
					}
				},				
			   {field:'articleid',title:'品种名称',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.articlepage.articlename;
					}
				},				
			   {field:'articlespec',title:'品种规格',align:'center',sortable:false,width:70,
					formatter:function(value,row,index){
						return row.articlepage.articlespec;
					}
				},				
			   {field:'factory',title:'厂家',align:'center',sortable:false,width:90,
					formatter:function(value,row,index){
						return row.articlepage.factory;
					}
				},				
			   {field:'serial',title:'批号',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.serial;
					}
				},				
			   {field:'computernum',title:'电脑数量',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.computernum;
					}
				},				
			   {field:'realnum',title:'实际数量',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.realnum;
					}
				},				
			   {field:'costprice',title:'成本价',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.costprice;
					}
				},				
			   {field:'lostamount',title:'损益金额',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return "<font color='blue'>"+row.costprice*(row.realnum - row.computernum)+"</font>";
					}
				},				
			   {field:'expiredate',title:'有效期',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dateFormatYMD(row.expiredate);
					}
				},				
			   {field:'crtusername',title:'盘点人姓名',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},
				{field:'modifydate',title:'修改日期',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dateFormatYMD(row.modifydate);
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return dateFormatYMD(row.createdate);
					}
				}			
			 ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
			}
		});

		stockCheckAddForm = $('#stockCheckAddForm').form({
			url : 'stockCheckAction!add.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					datagridStock.datagrid('reload');
					stockCheckAddDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		stockCheckAddDialog = $('#stockCheckAddDialog').show().dialog({
			title : '添加库存盘点',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '添加',
				handler : function() {
				  if(stockCheckAddForm.form('validate')){
				     var real = $("#realnumAdd").numberbox('getValue');
					 $.messager.confirm('请确认', '您要把该库存修改为'+real+'？', function(r){
					     if (r){
					        if  (formFlag){
								formFlag = false ;
								stockCheckAddForm.submit();
							}
					     }
					 });
				  }
				}
			} ]
		});

		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockCheckAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockCheckAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		stockCheckEditForm = $('#stockCheckEditForm').form({
			url : 'stockCheckAction!edit.action',
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					datagrid.datagrid('reload');
					stockCheckEditDialog.dialog('close');
				} else {
					$.messager.show({
						title : '失败',
						msg : '操作失败！'
					});
				}
				formFlag = true ;
			}
		});

		stockCheckEditDialog = $('#stockCheckEditDialog').show().dialog({
			title : '编辑库存盘点',
			modal : true,
			closed : true,
			maximizable : true,
			buttons : [ {
				text : '编辑',
				handler : function() {
					if(formFlag){
						formFlag = false ;
						stockCheckEditForm.submit();
					}
				}
			} ]
		});

		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockCheckAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockCheckAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '库存盘点描述',
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
		datagridStock.datagrid('load', sy.serializeObject(searchForm));
	}
	function cleanSearch() {
		datagridStock.datagrid('load', {});
		searchForm.find('input').val('');
	}
	function add() {
		stockCheckAddForm.find('input,textarea').val('');
		$('div.validatebox-tip').remove();
		if (initForm()){
		   stockCheckAddDialog.dialog('open');
		}else{
		   $.messager.alert('提示', '请选择要要盘点的库存！', 'error');
		}
	}
	function initForm(){
	    var rows = datagridStock.datagrid('getSelections');
	    if (rows.length>0){
	       var row = rows[0];
	       stockCheckAddForm.find("input[name*='stockkey']").eq(0).val(row.stockkey);
	       stockCheckAddForm.find("input[name*='depotname']").eq(0).val(row.depotpage.depotname);
	       stockCheckAddForm.find("input[name*='articlename']").eq(0).val(row.articlepage.articlename);
	       stockCheckAddForm.find("input[name*='serial']").eq(0).val(row.serial);
	       stockCheckAddForm.find("input[name*='computernum']").eq(0).val(row.num);
	       stockCheckAddForm.find("input[name*='costprice']").eq(0).val(row.costprice);
	       stockCheckAddForm.find("input[name*='expiredate']").eq(0).val(row.expiredate);
	       stockCheckAddForm.find(".easyui-validatebox").css("background-color","#cfcfcf");
	       stockCheckAddForm.find(".easyui-validatebox").click(function(){
	          this.blur();
	          $("#realnumAdd").focus();
	       });
	       return true;
	    }else{
	       return false;
	    }
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].stockcheckcode);
					}
					$.ajax({
						url : 'stockCheckAction!delete.action',
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
				url : 'stockCheckAction!showDesc.action',
				data : {
					stockcheckcode : rows[0].stockcheckcode
				},
				dataType : 'json',
				cache : false,
				success : function(response) {
					stockCheckEditForm.find('input,textarea').val('');
					stockCheckEditForm.form('load', response);
					$('div.validatebox-tip').remove();
					stockCheckEditDialog.dialog('open');
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
			url : 'stockCheckAction!showDesc.action',
			data : {
				stockcheckcode : row.stockcheckcode
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有库存盘点描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height:240px;">
	    <div  id="layoutTop" class="easyui-layout" data-options="fit:true">
	        <div region="north"  style="height:36px;overflow: hidden;">
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
	        <div region="center">
	            <table id="datagridStock"></table>
	        </div>
	    </div>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加</div>
	</div>

	<div id="stockCheckAddDialog" style="display: none;width: 500px;height: 350px;padding:10px;" align="center">
		<form id="stockCheckAddForm" method="post">
			<table class="tableForm">
			                <tr>
							    <td class="tdLeft">库存序号</td>
							    <td class="tdRight">
							       <input type="text" name="stockkey" class="easyui-validatebox" style="width: 155px;"/>
							    </td>
							</tr>
							<tr>
		 						<td class="tdLeft">仓库名称</td>
		 						<td class="tdRight">
		 						    <input type="hidden" name="stockid" />
									<input name="depotname" type="text" maxlength="5" class="easyui-validatebox" data-options="required:true" missingMessage="请填写仓库ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">品种名称</td>
		 						<td class="tdRight">
		 						    <input type="hidden" name="articleid" />
									<input name="articlename" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品种ID"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">批号</td>
		 						<td class="tdRight">
									<input name="serial" type="text" maxlength="20" class="easyui-validatebox" data-options="required:true" missingMessage="请填写批号"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">有效期</td>
		 						<td class="tdRight">
									<input name="expiredate" type="text" maxlength="" class="easyui-validatebox" data-options="required:true" missingMessage="请填写有效期"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">电脑数量</td>
		 						<td class="tdRight">
									<input name="computernum" type="text" maxlength="" class="easyui-validatebox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写电脑数量"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">成本价</td>
		 						<td class="tdRight">
									<input name="costprice" type="text" maxlength="" class="easyui-validatebox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写成本价"  style="width: 155px;"/>
								</td>
							</tr>
							<tr>
		 						<td class="tdLeft">实际数量</td>
		 						<td class="tdRight">
									<input id="realnumAdd" name="realnum" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:0,groupSeparator:','" missingMessage="请填写实际数量"  style="width: 155px;"/>
								</td>
							</tr>
			</table>
		</form>
	</div>

	<div id="stockCheckEditDialog" style="display: none;width: 500px;height: 300px;padding:10px;" align="center">
		<form id="stockCheckEditForm" method="post">
			<input type="text" name="stockcheckcode" />
			<table class="tableForm">
							
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