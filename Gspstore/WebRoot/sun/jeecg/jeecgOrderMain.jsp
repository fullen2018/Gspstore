<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath%>/jslib/artDialog4.1.7/jquery.artDialog.js?skin=default" charset="utf-8"></script>
<script src="<%=basePath%>/jslib/artDialog4.1.7/plugins/iframeTools.js"  charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    var searchForm;
	var datagrid;
	var cdescAdd;
	var cdescEdit;
	var showCdescDialog;
	var iframeDialog;
	var addDialog;
	var editDialog;
	$(function() {
	    //查询列表	
	    searchForm = $('#searchForm').form();
		datagrid = $('#datagrid').datagrid({
			url : 'jeecgOrderMainAction!datagrid.action',
			title : '一对多数据模型（明细采用TAB方式切换）',
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
			   {field:'goOrderCode',title:'订单号',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goOrderCode;
					}
				},				
			   {field:'goderType',title:'订单类型 0',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goderType;
					}
				},				
			   {field:'goStatus',title:'订单团购状态',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goStatus;
					}
				},				
			   {field:'touristid',title:'旅行社ID',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.touristid;
					}
				},				
			   {field:'agencyId',title:'旅行社门店I',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.agencyId;
					}
				},				
			   {field:'agencyName',title:'旅行社名称',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.agencyName;
					}
				},				
			   {field:'linkmenid',title:'联系人ID',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.linkmenid;
					}
				},				
			   {field:'usertype',title:'顾客类型 :',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.usertype;
					}
				},				
			   {field:'goCusName',title:'客户',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goCusName;
					}
				},				
			   {field:'goSaleName',title:'销售人',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goSaleName;
					}
				},				
			   {field:'goSaleId',title:'销售人ID',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goSaleId;
					}
				},				
			   {field:'goContactName',title:'联系人',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goContactName;
					}
				},				
			   {field:'goZoneNo',title:'区号',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goZoneNo;
					}
				},				
			   {field:'goPhone',title:'电话',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goPhone;
					}
				},				
			   {field:'goTelphone',title:'手机',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goTelphone;
					}
				},				
			   {field:'goFacsimile',title:'传真',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goFacsimile;
					}
				},				
			   {field:'goMail',title:'邮箱',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goMail;
					}
				},				
			   {field:'goOrderCount',title:'订单人数',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goOrderCount;
					}
				},				
			   {field:'goAllPrice',title:'总价(不含返',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goAllPrice;
					}
				},				
			   {field:'goReturnPrice',title:'返款',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goReturnPrice;
					}
				},				
			   {field:'goContent',title:'备注',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.goContent;
					}
				},				
			   {field:'auditorStatus',title:'审核状态 0',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.auditorStatus;
					}
				},				
			   {field:'auditorObid',title:'审核人ID',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.auditorObid;
					}
				},				
			   {field:'auditorName',title:'审核人',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.auditorName;
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


		cdescAdd = $('#cdescAdd').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'jeecgOrderMainAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'jeecgOrderMainAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});


		cdescEdit = $('#cdescEdit').xheditor({
			tools : 'mini',
			html5Upload : true,
			upMultiple : 4,
			upLinkUrl : 'jeecgOrderMainAction!upload.action',
			upLinkExt : 'zip,rar,txt,doc,docx,xls,xlsx',
			upImgUrl : 'jeecgOrderMainAction!upload.action',
			upImgExt : 'jpg,jpeg,gif,png'
		});

		showCdescDialog = $('#showCdescDialog').show().dialog({
			title : '订单主数据描述',
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
		addDialog = $.dialog.open('jeecgOrderMainAction!toJeecgOrderMainMainAdd.action', 
				{
					title: '新建订单主数据',
					ok: function(topWin){
			    		//$.dialog('hello world');
						this.iframe.contentWindow.dosubmit();
						return false;
			   		},
			   		width:"88%",  
        			height:"80%",
			   		cancelVal: '关闭',
			   	    cancel: true //为true等价于function(){}
				});
	}
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
						url : 'jeecgOrderMainAction!delete.action',
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
			editDialog = $.dialog.open('jeecgOrderMainAction!toJeecgOrderMainMainEdit.action?obid='+rows[0].obid, 
					{
						title: '编辑订单主数据',
						ok: function(topWin){
							this.iframe.contentWindow.dosubmit();
							return false;
				   		},
				   		width:"88%",  
	        			height:"80%",
				   		cancelVal: '关闭',
				   	    cancel: true //为true等价于function(){}
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
			url : 'jeecgOrderMainAction!showDesc.action',
			data : {
				obid : row.obid
			},
			dataType : 'json',
			cache : false,
			success : function(response) {
				if (response && response.cdesc) {
					showCdescDialog.find('div[name=cdesc]').html(response.cdesc);
					showCdescDialog.dialog('open');
				} else {
					$.messager.alert('提示', '没有订单主数据描述！', 'error');
				}
				$.messager.progress('close');
			}
		});
		datagrid.datagrid('unselectAll');
	}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="搜索条件" style="height: 60px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table>
				<tr>
					<!-- add-begin author:gaoxingang for:添加生日开始结束时间条件  date：20130310-->
					<td>订单<input name="goOrderCode" style="width:100px;" /></td>
					<td>客户英文名<input name="jeecgOrderCustomEntity.gocCusNameEn" style="width:100px;" /></td>
					<td>姓名<input name="jeecgOrderCustomEntity.gocCusName" style="width:100px;" /></td>
					<td>产品名称<input name="jeecgOrderProductEntity.gopProductName" style="width:100px;" /></td>
					<!-- add-end author:gaoxingang for:添加生日开始结束时间条件  date：20130310-->
					<!-- update-begin author:gaoxingang for:更新创建时间name  date：20130310-->
					<td>创建时间<input name="beginCreateDt" class="easyui-datetimebox" editable="false" style="width: 100px;" />至<input name="endCreateDt" class="easyui-datetimebox" editable="false" style="width: 100px;" /></td>
					<!-- update-end author:gaoxingang for:更新创建时间name  date：20130310-->
					<td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">搜索</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
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
	
	<div id="iframeDialog" style="display: none;overflow: auto;width: 600px;height: 400px;">
	<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
    </iframe>
</div>
</body>
</html>