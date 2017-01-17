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
	var datagridSale;
	$(function() {
	    $('#iframeDialog').css('width',(window.screen.width - 200)>1000?1000:(window.screen.width - 200));
	    $('#iframeDialog').attr('height',window.screen.height - 60);
	    //查询列表	
	    searchForm = $('#searchForm').form();
	    datagridSale = $('#datagridSale').datagrid({
			url : 'saleAction!datagrid.action',
			title : '销售单列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 3,
			pageList : [ 3,10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			singleSelect:true,
			nowrap : true,
			border : false,
			idField : 'salebillno',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.salebillno;
						}
					},
			   {field:'salebillno',title:'销售单号',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return '<a href=\'#\' onclick=\'detailSaleBillDetail('+row.salebillno+')\' >'+row.salebillno+'</a>';
					}
				},				
			   {field:'amount',title:'销售金额',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.amount;
					}
				},				
			   {field:'customerid',title:'销售客户',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.customerpage.customername;
					}
				},				
			   {field:'saledate',title:'销售日期',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return dateFormatYMD(row.saledate);
					}
				},				
			   {field:'accountid',title:'业务员',align:'center',sortable:true,width:60,
					formatter:function(value,row,index){
						return row.accounterpage.accountname;
					}
				},				
			   {field:'salerate',title:'提成比例',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.salerate;
					}
				},				
			   {field:'isreceive',title:'是否收款',align:'center',hidden:true,sortable:true,width:60,
					formatter:function(value,row,index){
						return dictGetName('004',row.isreceive);
					}
				},				
			   {field:'crtusername',title:'操作人姓名',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.modifydate;
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.createdate;
					}
				},	
				{field:'salebackbillitems',title:'销售退货单列表',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
					    if (row.salebackbillitems!=null&&row.salebackbillitems!="")
						    return '<span title='+row.salebackbillitems+'>'+row.salebackbillitems+'</span>';
						else return row.salebackbillitems;
					}
				},
				{field:'meno',title:'备注',align:'center',sortable:true,width:100,
					formatter:function(value,row,index){
						return row.meno;
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
			url : 'saleBackAction!datagrid.action',
			title : '销售退货单列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			pageSize : 5,
			pageList : [5, 10, 20, 30, 40 ],
			fit : true,
			singleSelect:true,
			fitColumns : false,
			nowrap : true,
			border : false,
			idField : 'salebackno',
			sortName : 'createdate',
			sortOrder : 'desc',
			columns : [ [ 
			{field:'ck',checkbox:true,
						formatter:function(value,row,index){
							return row.salebackno;
						}
					},
			   {field:'salebackno',title:'销售退货单号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.salebackno;
					}
				},				
			   {field:'salebillno',title:'原销售单号',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.salebillno;
					}
				},				
			   {field:'customerid',title:'退货客户',align:'center',sortable:true,width:90,
					formatter:function(value,row,index){
						return row.customerpage.customername;
					}
				},				
			   {field:'backdate',title:'退货日期',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return dateFormatYMD(row.backdate);
					}
				},				
			   {field:'accountid',title:'业务员',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.accounterpage.accountname;
					}
				},				
			   {field:'salerate',title:'原提成比例',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.salerate;
					}
				},				
			   				
			   {field:'isreceive',title:'是否收款',align:'center',hidden:true,sortable:true,width:80,
					formatter:function(value,row,index){
						return dictGetName('004',row.isreceive);
					}
				},				
			   {field:'crtusername',title:'操作人姓名',align:'center',sortable:true,width:80,
					formatter:function(value,row,index){
						return row.crtusername;
					}
				},				
			   {field:'modifydate',title:'修改日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.modifydate;
					}
				},				
			   {field:'createdate',title:'创建日期',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.createdate;
					}
				},				
			   {field:'amount',title:'退货金额',align:'center',sortable:true,width:70,
					formatter:function(value,row,index){
						return row.amount;
					}
				},
				{field:'meno',title:'备注',align:'center',sortable:true,width:110,
					formatter:function(value,row,index){
						return row.meno;
					}
				}			
			 ] ],
			toolbar : [ {
				text : '增加销售退货单',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			},  '-', {
				text : '查看销售退货单',
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
			upLinkUrl : 'saleBackAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'saleBackAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});


		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'saleBackAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'saleBackAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '销售退货单描述',
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
		datagridSale.datagrid('load', sy.serializeObject(searchForm));
	}
	function cleanSearch() {
		datagridSale.datagrid('load', {});
		searchForm.find('input').val('');
	}
	
	function add() {
		var rows = datagridSale.datagrid('getSelections');
		if (rows.length == 1) {
		    $('#iframeDialog').dialog('setTitle', '新建销售退货单'); 
		    iframeDialog.dialog('open');
		    $("#loading").css("display","block");
		    document.getElementById("iframe").src = "saleBackAction!toSaleBackMainAdd.action?salebillno="+rows[0].salebillno;
		} else {
			$.messager.alert('提示', '请选择一项要退货的销售单！', 'error');
		}
	}
	function del() {
		var rows = datagrid.datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '您要删除当前所选项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].salebackno);
					}
					$.ajax({
						url : 'saleBackAction!delete.action',
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
			$('#iframeDialog').dialog('setTitle', '编辑销售退货单'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "saleBackAction!toSaleBackMainEdit.action?salebackno="+rows[0].salebackno;
		} else {
			$.messager.alert('提示', '请选择一项要编辑的记录！', 'error');
		}
	}
	function detail() {
		var rows = datagrid.datagrid('getSelections');
		if (rows.length == 1) {
			$('#iframeDialog').dialog('setTitle', '【'+rows[0].salebackno+'】销售退货单详情'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "saleBackAction!toSaleBackMainDetail.action?salebackno="+rows[0].salebackno;
		} else {
			$.messager.alert('提示', '请选择一项要查看的销售退货单记录！', 'error');
		}
	}
	function detailSaleBillDetail(salebillno) {
		$('#iframeDialog').dialog('setTitle', '【'+salebillno+'】销售单详情'); 
			iframeDialog.dialog('open');
			$("#loading").css("display","block");
			document.getElementById("iframe").src = "saleAction!toSaleMainDetail.action?salebillno="+salebillno;
	}
	function showCdesc(index) {
		var rows = datagrid.datagrid('getRows');
		var row = rows[index];
		$.messager.progress({
			text : '数据加载中....',
			interval : 100
		});
		$.ajax({
			url : 'saleBackAction!showDesc.action',
			data : {
				salebackno : row.salebackno
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有销售退货单描述！', 'error');
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
			<table class="tableForm datagrid-toolbar" style="width: 100%;height:100%;">
				<tr>
				<td>销售客户</td>
					<td>
					  <select id="customerid" name="customerid" type="text"  class="easyui-combobox" data-options="
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 100px;">
		 					</select>
					</td>
					<td>业务员</td>
					<td>
					   <select id="accountid" name="accountid" type="text"  class="easyui-combobox" data-options="
		 						       url:'accounterAction!combox.action',
								      	 valueField:'accountid',
								         textField:'accountname',
						                editable:false,
						                onSelect: function(rec){ 
						                   $('#salerate').val(rec.rate);
						                }
		 						       "  missingMessage="请填写业务员"  style="width: 80px;">
		 					</select>
					</td>
					<td>销售操作人</td>
					<td>
					   <select id="crtuser" name="crtuser" type="text"  class="easyui-combobox" data-options="
		 						       url:'userAction!combox.action',
								      	 valueField:'cid',
								         textField:'realname',
						                editable:false
		 						       "  missingMessage="请填写入库操作人"  style="width: 80px;">
		 						     </select>
					</td>
					<td>提成比例></td>
					<td>
					   <input name="salerate" style="width:50px;" class="easyui-numberbox"/>
					</td>
					<td>销售日期</td>
					<td>
					  <input id="begindate" name="ccreatedatetimeStart" style="width:117px;" class="easyui-datetimebox"/>
					  <input id="enddate" name="ccreatedatetimeEnd" style="width:117px;" class="easyui-datetimebox"/>
					  </td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	        </div>
	        <div region="center">
	            <table id="datagridSale"></table>
	        </div>
	    </div>
	</div>
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>

	<div id="menuTop" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="add();" iconCls="icon-add">增加销售退货单</div>
	</div>
	<div id="menuBottom" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="detail();" iconCls="icon-remove">查看销售退货</div>
	</div>

	<div id="showCdescDialog" style="display: none;overflow: auto;width: 500px;height: 400px;">
		<div name="cdesc"></div>
	</div>
	
	<div id="iframeDialog" style="display: none;overflow: hidden;width: 1000px;height: 420px;">
		<div id="loading"  style="position: absolute;z-index: 10000;
          top:30;left:0;width:100%;height:95%;background: #DDDDDB  url('<%=request.getContextPath()%>/css/images/loading.gif') no-repeat center ;text-align:center;">
        </div>
		<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
	    </iframe>
	</div>
</body>
</html>