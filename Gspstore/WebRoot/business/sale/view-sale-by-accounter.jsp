<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
<style>
	.huibd {
		border: 1px solid #000000;
			}
	td {
		white-space: nowrap;
		font-size: 12px;
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
        $("#begindate").datebox("setValue",(sy.getMonthBeginDate()).format('yyyy-MM-dd'));
        $("#enddate").datebox("setValue",(sy.getMonthEndDate()).format('yyyy-MM-dd'));
    });
    function   _search() {
      if  ($('#searchForm').form('validate')){
	       var  querystring = sy.jsonToUrlParam(searchForm);
	       document.getElementById("iframe").src = "saleAction!viewSaleByAccounterData.action?"+querystring;
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
				    <td width="20px">
				        <input type="radio" id="hz" onclick="_search()" name="reportQueryForm.reporttype" style="width:20px;"  value="hz" checked="checked"/>
				    </td>
				    <td width="50px;"><label for="hz">查看汇总</label></td>
				    <td width="20px">
				        <input type="radio" id="mx" onclick="_search()" style="width:20px;"  name="reportQueryForm.reporttype"  value="mx" />
				    </td>
				    <td width="50px;"><label for="mx">查看明细</label></td>
					<td width="30px">客户</td>
					<td style="width: 130px;">
					    <select id="customerid" name="reportQueryForm.customerid" type="text"  class="easyui-combobox" data-options="
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 130px;">
		 					</select>
					</td>
					<td width="40px">业务员</td>
					<td>
					   <select id="accountid" name="reportQueryForm.accountid" type="text"  class="easyui-combobox" data-options="
		 						       url:'accounterAction!combox.action',
								      	 valueField:'accountid',
								         textField:'accountname',
						                editable:false,
						                onSelect: function(rec){ 
						                   $('#salerate').val(rec.rate);
						                }
		 						       "  missingMessage="请填写业务员"  style="width: 70px;">
		 					</select>
		 		    </td>
					<td width="30px">品种</td>
					<td>
					<input type="hidden" name="reportQueryForm.articleid" id="viewSaleDetailPagearticleid" />
					<select id="articlename" name="articlename" type="text"  class="easyui-combobox" data-options="
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
		 						       "  missingMessage="请填写品种"  style="width:150px;" >
		 					       </select>
					</td>
					<td width="50px">销售日期</td>
					<td>
					   <input id="begindate" name="reportQueryForm.begindate" style="width:90px;" class="easyui-datebox" data-options="required:true"/>
					   <input id="enddate" name="reportQueryForm.enddate" style="width:90px;" class="easyui-datebox" data-options="required:true" />
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