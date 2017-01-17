<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
 	var searchForm;
	var datagrid;
	$(function() {
	    //查询列表	
		datagrid = $('#datagrid').datagrid({
			url : 'demoAction!datagridByJdbc.action',
			title : 'Spring JDBC分页',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : true,
			nowrap : true,
			border : false,
			idField : 'obid',
			sortName : 'obid',
			sortOrder : 'desc',
			rownumbers: true,
			columns : [ [ 
			{field:'PARAM_LEVEL_DEC',title:'描述',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.PARAM_LEVEL_DEC;
					}
				},				
			   {field:'PARAM_LEVEL',title:'类型',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.PARAM_LEVEL;
					}
				},				
			   {field:'PARAM_VALUE',title:'参数值',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.PARAM_VALUE;
					}
				},				
			   {field:'PARAM_NAME',title:'参数名',align:'center',sortable:true,
					formatter:function(value,row,index){
						return row.PARAM_NAME;
					}
				}			
			 ] ]
		});


	});
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="过滤条件" collapsed="true"  style="height: 110px;overflow: hidden;display: none;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>查询字段需要手工修改</th>
					<td><input name="hotelid" style="width:155px;" /></td>
				</tr>
				<tr>
					<th>创建时间</th>
					<td><input name="ccreatedatetimeStart" class="easyui-datebox" editable="false" style="width: 155px;" />至<input name="ccreatedatetimeEnd" class="easyui-datebox" editable="false" style="width: 155px;" /></td>
				</tr>
				<tr>
					<th>最后修改时间</th>
					<td><input name="cmodifydatetimeStart" class="easyui-datebox" editable="false" style="width: 155px;" />至<input name="cmodifydatetimeEnd" class="easyui-datebox" editable="false" style="width: 155px;" /><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div region="center" border="false">
		<table id="datagrid"></table>
	</div>
	
	<div id="iframeDialog" style="display: none;overflow: auto;width: 600px;height: 400px;">
	<iframe name="iframe" id="iframe" src=""  scrolling="auto" frameborder="0" style="width:100%;height:100%;">
    </iframe>
</div>
</body>
</html>