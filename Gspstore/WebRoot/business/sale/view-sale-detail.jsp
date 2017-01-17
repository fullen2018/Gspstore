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
    $(function() {
        searchForm = $('#searchForm').form();
         $("#begindate").datebox("setValue",(sy.getMonthBeginDate()).format('yyyy-MM-dd 00:00:00'));
        $("#enddate").datebox("setValue",(sy.getMonthEndDate()).format('yyyy-MM-dd 23:59:59'));
    });
    function   _search() {
      if  ($('#searchForm').form('validate')){
	       var  querystring = sy.jsonToUrlParam(searchForm);
	       document.getElementById("iframe").src = "saleAction!viewSaleDetailData.action?"+querystring;
	    }
	}
	
	function cleanSearch() {
		searchForm.find('input').val('');
	}
	
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" title="查询条件"  style="height: 60px;overflow: hidden;" align="left">
		<form id="searchForm" method="post" target="iframe">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<td width="30px">客户</td>
					<td>
					    <select id="customerid" name="reportQueryForm.customerid" type="text"  class="easyui-combobox" data-options="
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 100px;">
		 					</select>
					</td>
					<td width="45px">业务员</td>
					<td>
					   <select id="accountid" name="reportQueryForm.accountid" type="text"  class="easyui-combobox" data-options="
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
					<td width="30px">品种</td>
					<td>
					<input type="hidden" name="reportQueryForm.articleid" id="viewSaleDetailPagearticleid" />
					<select id="viewStockinDetailPage.articlename" name="viewStockinDetailPage.articlename" type="text"  class="easyui-combobox" data-options="
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
										},
										onSelect: function(rec){ 
										   $('#viewSaleDetailPagearticleid').val(rec.articleid);
										}
		 						       "  missingMessage="请填写品种"  style="width:120px;" >
		 					       </select>
					</td>
					<td width="30px">批号</td>
					<td>
					<input type="text" name="reportQueryForm.serial" style="width:70px;" />
		 		    </td>
					<td width="60px">销售日期</td>
					<td>
					  <input id="begindate" name="reportQueryForm.begindate" style="width:117px;" data-options="required:true" class="easyui-datetimebox"/>
					  <input id="enddate" name="reportQueryForm.enddate" style="width:117px;" data-options="required:true" class="easyui-datetimebox"/>
					</td>
					<td width="40px">操作人</td>
					<td>
					<select id="crtuser" name="reportQueryForm.crtuser" type="text"  class="easyui-combobox" data-options="
		 						       url:'userAction!combox.action',
								      	 valueField:'cid',
								         textField:'realname',
						                editable:false
		 						       "  missingMessage="请填写入库操作人"  style="width: 50px;">
		 			</select>
		 		    </td>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div region="center" border="false" >
		<iframe name="iframe" id="iframe"  src=""  scrolling="auto" frameborder="0" style="width:100%;height:99%;">
        </iframe>
	</div>
</body>
</html>