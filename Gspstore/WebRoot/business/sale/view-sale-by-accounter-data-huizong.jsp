<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/css/report.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath%>/jslib/jquery-easyui-1.3/jquery-1.8.0.min.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>/jslib/LodopFuncs.js"></script>
<style type="text/css">
    
</style>
<script language="javascript" type="text/javascript"> 
	var LODOP; //声明为全局变量
	function printTotal(){
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT("业务员销售统计");
		LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");
		LODOP.ADD_PRINT_TEXT(12,100,760,30,$("#divTitle").html());
		LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		LODOP.ADD_PRINT_TEXT(40,100,760,25,$("#divTime").html());
		LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
		LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		LODOP.ADD_PRINT_TABLE(65,10,780,920,$("#printDiv").html());
		LODOP.PREVIEW();			
	};	
	$(document).ready(function() {
	    $("#doPrint").click(function(){
		    printTotal();
	    });
	});	
</script>
</head>
<body style="margin: 0px;padding:5px;vertical-align: top;">
        <div style="width:99%;text-align:center;">
             <div id="divTitle" style="font-size:16px;text-align:center;font-weight:bold;padding:5px;">
                 长沙泰正医药科技有限公司业务员销售统计表
             </div>
             <div id="divTime" style="font-size:14px;text-align:center;font-weight:bold;padding:1px;">
                 (<s:date name="reportQueryForm.begindate" format="yyyy-MM-dd HH:mm"/>
                 -
                 <s:date name="reportQueryForm.enddate" format="yyyy-MM-dd 23:59"/>)
             </div>
             <div id="printDiv">
                <table id="datatable"   border="1"  style="font-size:12px;border-collapse: collapse;">
                        <tr class="trHeader">
                              <td  style="text-align: center;width:40px;">排名</td>
							  <td style="text-align: center;width:120px;">业务员</td>
							  <td style="text-align: center;width:220px;">总销售金额</td>
							  <td style="text-align: center;width:220px;">提成金额</td>
							  <td style="text-align: center;width:220px;">利润金额</td>
                        </tr>
                        <tbody id="view_data_tbody">
                        <s:set id="totalamount" name="totalamount" value="0"></s:set>
                        <s:set id="totalrateamount" name="totalrateamount" value="0"></s:set>
                        <s:set id="totalbenifitamount" name="totalbenifitamount" value="0"></s:set>
                        <s:if test="viewSaleByAccounterList.size >0">
                        <s:iterator value="viewSaleByAccounterList" var="poMap" status="stuts">
                          <s:set id="totalamount" value="#totalamount+#poMap.totalsaleamount"></s:set>
                          <s:set id="totalrateamount" value="#totalrateamount+#poMap.rateamount"></s:set>
                          <s:set id="totalbenifitamount" value="#totalbenifitamount+#poMap.benifitamount"></s:set>
	                        <tr class="trTBody">
	                          <td  align="center" >${stuts.index+1 }</td>
							  <td align="center" >${poMap.accountname }</td>
							  <td align="right" > ${poMap.totalsaleamount }</td>
							  <td align="right" > ${poMap.rateamount }</td>
							  <td align="right" > ${poMap.benifitamount }</td>
	                        </tr>
                        </s:iterator>
                         <tr>
                              <td colspan="2"></td>
		                      <td style="text-align:right;font-weight:bold;">总销售金额总计:&nbsp;&nbsp;<s:property value="#totalamount" /></td>
		                      <td style="text-align:right;font-weight:bold;">提成金额总计:<s:property value="#totalrateamount" /></td>
		                      <td style="text-align:right;font-weight:bold;">利润总计:&nbsp;&nbsp;<s:property value="#totalbenifitamount" /></td>
		                 </tr> 
                      	</s:if>  
                       </tbody>
                      </table>
             </div>

			<div align="center">
				<table>
                  <tr>
                    <td height="50" colspan="8"  align="center">
                         <input value=" 打 印 " type="button" id="doPrint"/>
                    </td>
                  </tr>
                 </table>
		    </div>
        </div>
     <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
		</object>

</body>
</html>