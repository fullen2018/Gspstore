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
	$(function() {
	    $('#iframeDialog').css('width',(window.screen.width - 200)>1010?1010:(window.screen.width - 200));
	    $('#iframeDialog').attr('height',window.screen.height - 60);
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'stockinAction!datagrid.action',
			title : '入库主表列表',
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
			idField : 'stockinbillno',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.stockinbillno;
						}
					},
				{field:'stockinbillno',title:'入库单号',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.stockinbillno;
					}
				},	
				{field:'supplierid',title:'供应商',align:'center',sortable:true,width:120,
					formatter:function(value,row,index){
						return row.supplierpage.suppliername;
					}
				},	
			   {field:'stockindate',title:'入库日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.stockindate;
					}
				},				
			   {field:'optman',title:'入库人',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},				
			   {field:'depotid',title:'仓库',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.depotpage.depotname;
					}
				},				
			   {field:'ispayed',title:'是否付款',align:'center',hidden:true,sortable:true,width:80,
					formatter:function(value,row,index){
						return dictGetName('004',row.ispayed);
					}
				},				
			   {field:'paybillno',title:'付款单号',align:'center',hidden:true,sortable:true,width:90,
					formatter:function(value,row,index){
						return row.paybillno;
					}
				},				
			   {field:'amount',title:'总金额',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.amount;
					}
				},				
			   {field:'meno',title:'备注',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.meno;
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
			toolbar : [ {
				text : '增加采购入库单',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			},'-', {
				text : '采购入库单详情',
				iconCls : 'icon-newd',
				handler : function() {
					detail();
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-relo',
				handler : function() {
					datagrid.datagrid('reload');
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


		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockinAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockinAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});


		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'stockinAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'stockinAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '入库主表描述',
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
		
		$("#begindate").datebox("setValue",(new Date()).format('yyyy-MM-dd 00:00:00'));
        $("#enddate").datebox("setValue",(new Date()).format('yyyy-MM-dd 23:59:59'));
	});

	function _search() {
		datagrid.datagrid('load', sy.serializeObject(searchForm));
	}
	function cleanSearch() {
		datagrid.datagrid('load', {});
		searchForm.find('input').val('');
	}
	function add() {
		$('#iframeDialog').dialog('setTitle', '新建采购入库单'); 
		iframeDialog.dialog('open');
		$("#loading").css("display","block");
		document.getElementById("iframe").src = "stockinAction!toStockinMainAdd.action";
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].stockinbillno);
					}
					$.ajax({
						url : 'stockinAction!delete.action',
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
			$('#iframeDialog').dialog('setTitle', '【'+rows[0].stockinbillno+'】采购入库单详情'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "stockinAction!toStockinMainEdit.action?stockinbillno="+rows[0].stockinbillno;
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	function detail() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$('#iframeDialog').dialog('setTitle', '【'+rows[0].stockinbillno+'】采购入库单详情'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "stockinAction!toStockinMainDetail.action?stockinbillno="+rows[0].stockinbillno;
		} else {
			$.messager.alert('提示', '请选择一项要查看的记录！', 'error');
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
			url : 'stockinAction!showDesc.action',
			data : {
				stockinbillno : row.stockinbillno
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有入库主表描述！', 'error');
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
					<td width="70px">入库操作人</td>
					<td>
					<select id="crtuser" name="crtuser" type="text"  class="easyui-combobox" data-options="
		 						       url:'userAction!combox.action',
								      	 valueField:'cid',
								         textField:'realname',
						                editable:false
		 						       "  missingMessage="请填写入库操作人"  style="width: 80px;">
		 						     </select>
		 		    </td>
					<td width="30px">仓库</td>
					<td>
					   <select id="depotid" name="depotid" type="text"  class="easyui-combobox" data-options="
		 						       url:'depotAction!combox.action',
								      	 valueField:'depotid',
								         textField:'depotname',
						                editable:false
		 						       "    style="width: 100px;">
		 				</select>
		 		    </td>
					<td width="45px">供应商</td>
					<td>
					   <select id="supplierid" name="supplierid" type="text"  class="easyui-combobox" data-options="
		 						       url:'supplierAction!combox.action',
								      	 valueField:'supplierid',
								         textField:'suppliername',
						                editable:false
		 						       "  missingMessage="请填写供应商"  style="width: 120px;">
		 						     </select>
					</td>
					<td width="60px">是否付款</td>
					<td>
					  <select id="ispayed" name="ispayed" class="easyui-combobox" data-options="
		 						        panelHeight:45,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       " missingMessage="请填写是否付款"  style="width: 50px;">
		 						    </select>
					</td>
					<td width="60px">入库日期</td>
					<td>
					   <input id="begindate" name="ccreatedatetimeStart" style="width:117px;" class="easyui-datebox"/>
					   <input id="enddate" name="ccreatedatetimeEnd" style="width:117px;" class="easyui-datebox"/>
					</td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>
	<div region="south" border="false">
		<table id="datagridStockinDetail"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加采购入库单</div>
		<div onclick="detail();" iconCls="icon-newd">采购入库单详情</div>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
	<div id="iframeDialog" style="display: none;overflow: hidden;width: 1010px;height: 430px;">
	    <div id="loading"  style="position: absolute;z-index: 10000;
          top:30;left:0;width:100%;height:95%;background: #DDDDDB  url('<%=request.getContextPath()%>/css/images/loading.gif') no-repeat center ;text-align:center;">
        </div>
		<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
	    </iframe>
    </div>
</body>
</html>