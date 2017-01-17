<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var cdescAdd;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var datagridStockIn;
	$(function() {
	    $('#iframeDialog').css('width',(window.screen.width - 200)>1000?1000:(window.screen.width - 200));
	    $('#iframeDialog').attr('height',window.screen.height - 60);
	    //查询列表	
	    searchForm = $('#searchForm').form();
	    datagridStockIn = $('#datagridStockIn').datagrid({
			url : 'stockinAction!datagrid.action',
			title : '入库单列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 3,
			pageList : [3, 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			singleSelect:true,
			nowrap : true,
			border : false,
			idField : 'stockinbillno',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.stockinbillno;
						}
					},
				{field:'stockinbillno',title:'入库单号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.stockinbillno;
					}
				},	
				{field:'supplierid',title:'供应商',align:'center',sortable:true,width:160,
					formatter:function(value,row,index){
						return row.supplierpage.suppliername;
					}
				},	
			   {field:'stockindate',title:'入库日期',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.stockindate;
					}
				},				
			   {field:'crtusername',title:'入库人',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},				
			   {field:'depotid',title:'仓库',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'ispayed',title:'是否付款',align:'center',sortable:true,hidden:true,width:80,
					formatter:function(value,row,index){
						return  dictGetName('004',row.ispayed);
					}
				},				
			   {field:'paybillno',title:'付款单号',align:'center',sortable:true,hidden:true,width:90,
					formatter:function(value,row,index){
						return row.paybillno;
					}
				},				
			   {field:'stockbacknoitems',title:'退货单列表',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return '<span title='+row.stockbacknoitems+'>'+row.stockbacknoitems+'</span>';
					}
				},				
			   {field:'amount',title:'总金额',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.amount;
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.createdate;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.modifydate;
					}
				}				
			 ] ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menuTop').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
		datagrid = $('#datagrid').datagrid({
			url : 'stockBackAction!datagrid.action',
			title : '购进退货单列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			singleSelect:true,
			pageSize : 5,
			pageList : [ 5,10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : true,
			border : false,
			idField : 'stockbackno',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.stockbackno;
						}
					},
				{field:'stockbackno',title:'购进退货单号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.stockbackno;
					}
				},
			   {field:'depotid',title:'退货仓库',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'supplierid',title:'退货供应商',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.supplierpage.suppliername;
					}
				},				
			   {field:'crtusername',title:'退货人姓名',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},				
			   {field:'amount',title:'退货总金额',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.amount;
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.createdate;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.modifydate;
					}
				},				
			   {field:'stockinno',title:'原入库单号',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.stockinno;
					}
				},
				{field:'meno',title:'备注',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.meno;
					}
				}				
			 ] ],
			toolbar : [ {
				text : '添加购进退货单',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '购进退货单详情',
				iconCls : 'icon-edit',
				handler : function() {
					detail();
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
				$('#menuBottom').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});


		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockBackAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockBackAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});


		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockBackAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockBackAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '购进退货单描述',
			modal : true,
			closed : true,
			maximizable : true
		});

		iframeDialog = $('#iframeDialog').show().dialog({
			title : 'POP弹出页面',
			modal : true,
			closed : true,
			maximizable : true,
			onClose : function(){
			    document.getElementById("iframe").src = "about:blank";
			}
		});
	});

	function _search() {
		datagridStockIn.datagrid('load', sy.serializeObject(searchForm));
	}
	function cleanSearch() {
		datagridStockIn.datagrid('load', {});
		searchForm.find('input').val('');
	}
	function add() {
	    var rows = datagridStockIn.datagrid('getSelections');
	    if (rows.length == 1) {
		   $('#iframeDialog').dialog('setTitle', '新建购进退货单'); 
		   iframeDialog.dialog('open');
		   $("#loading").css("display","block");
		   document.getElementById("iframe").src = "stockBackAction!toStockBackMainAdd.action?stockinbillno="+rows[0].stockinbillno;
		}else{
		   $.messager.alert('提示', '请选择要退货的入库单！', 'error');
		}
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].stockbackno);
					}
					$.ajax({
						url : 'stockBackAction!delete.action',
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
			$('#iframeDialog').dialog('setTitle', '编辑购进退货单'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "stockBackAction!toStockBackMainEdit.action?stockinbillno="+rows[0].stockinbillno;
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	function detail() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$('#iframeDialog').dialog('setTitle', '【'+rows[0].stockbackno+'】购进退货单'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "stockBackAction!toStockBackMainDetail.action?stockbackno="+rows[0].stockbackno;
		} else {
			$.messager.alert('提示', '请选择一项要查看的退货单！', 'error');
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
			url : 'stockBackAction!showDesc.action',
			data : {
				stockbackno : row.stockbackno
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有购进退货单描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height:220px;">
	    <div  id="layoutTop" class="easyui-layout" data-options="fit:true">
	        <div region="north"  style="height:36px;overflow: hidden;">
	            <form id="searchForm">
			<table class="datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td>入库操作人</td>
					<td>
					<select id="crtuser" name="crtuser" type="text"  class="easyui-combobox" data-options="
		 						       url:'userAction!combox.action',
								      	 valueField:'cid',
								         textField:'realname',
						                editable:false
		 						       "  missingMessage="请填写入库操作人"  style="width: 120px;">
		 						     </select>
		 		    </td>
					<td>仓库</td>
					<td>
					   <select id="depotid" name="depotid" type="text"  class="easyui-combobox" data-options="
		 						       url:'depotAction!combox.action',
								      	 valueField:'depotid',
								         textField:'depotname',
						                editable:false
		 						       "    style="width: 120px;">
		 				</select>
		 		    </td>
					<td>供应商</td>
					<td>
					   <select id="supplierid" name="supplierid" type="text"  class="easyui-combobox" data-options="
		 						       url:'supplierAction!combox.action',
								      	 valueField:'supplierid',
								         textField:'suppliername',
						                editable:false
		 						       "  missingMessage="请填写供应商"  style="width: 150px;">
		 						     </select>
					</td>
					<td>是否付款</td>
					<td>
					<select id="ispayed" name="ispayed" class="easyui-combobox" data-options="
		 						        panelHeight:45,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       " missingMessage="请填写是否付款"  style="width: 60px;">
		 						    </select>
					</td>
			        <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	        </div>
	        <div region="center">
	            <table id="datagridStockIn"></table>
	        </div>
	    </div>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menuTop" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加购进退货单</div>
	</div>
	<div id="menuBottom" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="detail();" iconCls="icon-edit">购进退货单详情</div>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
	<div id="iframeDialog" style="display: none;overflow: hidden;width: 1000px;height: 400px;">
	    <div id="loading"  style="position: absolute;z-index: 10000;
          top:30;left:0;width:100%;height:95%;background: #DDDDDB  url('<%=request.getContextPath()%>/css/images/loading.gif') no-repeat center ;text-align:center;">
        </div>
		<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
	    </iframe>
	</div>
</body>
</html>