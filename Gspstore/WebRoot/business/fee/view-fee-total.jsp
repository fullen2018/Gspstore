<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
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
	       document.getElementById("iframe").src = "feeAction!viewFeeTotalData.action?"+querystring;
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
					<td width="30px">科目</td>
					<td>
					    <select id="subjectidAdd" name="reportQueryForm.subjectid" type="text"  class="easyui-combobox" data-options="
		 						        url:'subjectAction!combox.action',
								      	 valueField:'subjectid',
								         textField:'subjectname',
						                editable:false
		 						       "  missingMessage="请填写费用科目"  style="width: 100px;">
		 			   </select>
					</td>
					<td width="50px">支付方式</td>
					<td>
					   <select id="feetypeAdd" name="reportQueryForm.feetype" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=007',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="请填写支付方式"  style="width: 80px;">
		 						     </select>
		 		    </td>
					<td width="50px">科目类别</td>
					<td>
					   <select id="subjecttype" name="reportQueryForm.subjecttype" type="text"  class="easyui-combobox" data-options="
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=006',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       "  missingMessage="科目类别"  style="width: 80px;">
		 						     </select>
		 		    </td>
		 		    <td width="40px">操作人</td>
					<td>
					<select id="crtuser" name="reportQueryForm.crtuser" type="text"  class="easyui-combobox" data-options="
		 						       url:'userAction!combox.action',
								      	 valueField:'cid',
								         textField:'realname',
						                editable:false
		 						       "  missingMessage="请填写出库操作人"  style="width: 80px;">
		 						     </select>
		 		    </td>
					<td width="50px" style="display: none;">是否付款</td>
					<td style="display: none;">
					  <select id="ispayed" name="reportQueryForm.ispayed" class="easyui-combobox" data-options="
		 						        panelHeight:45,
		 						        url : 'dictParamAction!dictCombobox.action?paramLevel=004',
						                valueField : 'paramValue',
						                textField : 'paramName',
						                editable:false
		 						       " missingMessage="请填写是否付款"  style="width: 50px;">
		 			  </select>
					</td>
					<td width="50px">费用日期</td>
					<td>
					  <input id="begindate" name="reportQueryForm.begindate" style="width:117px;" data-options="required:true" class="easyui-datetimebox"/>
					  <input id="enddate" name="reportQueryForm.enddate" style="width:117px;" data-options="required:true" class="easyui-datetimebox"/>
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