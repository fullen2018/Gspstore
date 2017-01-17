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
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_打印表格");
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
		//var height = $("#printDivStockin").height()*1.22;
		LODOP.ADD_PRINT_HTM(65,10,780,920,$("#printDivStockin").html()+$("#printDivSale").html()+$("#printDivStockCheck").html());
		//LODOP.ADD_PRINT_HTM(height+65,10,780,920,$("#printDivSale").html());
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
                 长沙泰正医药科技有限公司商品跟踪
             </div>
             <div id="divTime" style="font-size:14px;text-align:center;font-weight:bold;padding:1px;">
                 (<s:date name="reportQueryForm.begindate" format="yyyy-MM-dd HH:mm"/>
                 -
                 <s:date name="reportQueryForm.enddate" format="yyyy-MM-dd 23:59"/>)
             </div>
             <s:set id="nettotal" name="nettotal" value="0"></s:set>
             <div id="printDivStockin">
                <table    border="1"  style="font-size:12px;border-collapse: collapse;width:1500px;">
                        <thead>
                            <tr class="trHeader">
                                 <td colspan="16">
                                             采购入库及采购退货信息
                                 </td>
                            </tr>
	                        <tr class="trHeader">
	                              <td  style="text-align: center;width:40px;">序号</td>
								  <td style="text-align: center;width:120px;">供货商名称</td>
								  <td style="text-align: center;width:80px;">入库单号</td>
								  <td style="text-align: center;width:80px;">入库日期</td>
								  <td style="text-align: center;width:60px;">仓库</td>
								  <td style="text-align: center;width:120px;">品名</td>
								  <td style="text-align: center;width:180px;">厂家</td>
								  <td style="text-align: center;width:100px;">规格</td>
								  <td style="text-align: center;width:40px;">单位</td>
								  <td style="text-align: center;width:80px;">批号</td>
								  <td style="text-align: center;width:80px;">有效期</td>
								  <td style="text-align: center;width:50px;">进价</td>
								  <td style="text-align: center;width:70px;">入库数量</td>
								  <td style="text-align: center;width:90px;">入库金额</td>
								   <td style="text-align: center;width:70px;">退货数量</td>
								  <td style="text-align: center;width:90px;">退货金额</td>
	                        </tr>
                        </thead>
                        <tbody id="view_data_tbody">
                        <s:set id="totalamount" name="totalamount" value="0"></s:set>
                        <s:set id="smallamount" name="smallamount" value="0"></s:set>
                        <s:set id="totalnum" name="totalnum" value="0"></s:set>
                        <s:set id="smallnum" name="smallnum" value="0"></s:set>
                        <s:set id="totalamountback" name="totalamountback" value="0"></s:set>
                        <s:set id="smallamountback" name="smallamountback" value="0"></s:set>
                        <s:set id="totalnumback" name="totalnumback" value="0"></s:set>
                        <s:set id="smallnumback" name="smallnumback" value="0"></s:set>
                        <s:set id="p_supplierid" name="p_supplierid" value=""></s:set>
                        <s:set id="c_supplierid" name="c_supplierid" value=""></s:set>
                        <s:if test="viewStockinDetailList.size >0">
                        <s:iterator value="viewStockinDetailList" var="poMap" status="stuts">
                          <s:set id="c_supplierid" value="#poMap.suppliername"></s:set>
                          <s:if test="#stuts.index!=0&&#p_supplierid!=#c_supplierid">
                               <tr>
                                   <td colspan="12"></td>
		                           <td style="text-align:right;font-weight:bold;">入库小计:<s:property value="#smallnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">入库小计:&nbsp;&nbsp;<s:property value="#smallamount" /></td>
		                           <td style="text-align:right;font-weight:bold;">退货小计:<s:property value="#smallnumback" /></td>
		                           <td  style="text-align:right;font-weight:bold;">退货小计:&nbsp;&nbsp;<s:property value="#smallamountback" /></td>
		                       </tr> 
		                       <s:set id="smallamount" value="0"></s:set>
		                       <s:set id="smallnum" value="0"></s:set>
		                       <s:set id="smallamountback" value="0"></s:set>
		                       <s:set id="smallnumback" value="0"></s:set>
                          </s:if>
                          <s:set id="smallamount" value="#smallamount+#poMap.amount"></s:set>
                          <s:set id="totalamount" value="#totalamount+#poMap.amount"></s:set>
                          <s:set id="smallnum" value="#smallnum+#poMap.num"></s:set>
                          <s:set id="totalnum" value="#totalnum+#poMap.num"></s:set>
                          <s:set id="smallamountback" value="#smallamountback+#poMap.backamount"></s:set>
                          <s:set id="totalamountback" value="#totalamountback+#poMap.backamount"></s:set>
                          <s:set id="smallnumback" value="#smallnumback+#poMap.backnum"></s:set>
                          <s:set id="totalnumback" value="#totalnumback+#poMap.backnum"></s:set>
	                        <tr class="trTBody">
	                          <td  align="center" >${stuts.index+1 }</td>
							  <td align="left" >${poMap.suppliername }</td>
							  <td align="center" >${poMap.stockinbillno }</td>
							  <td align="center" >${poMap.createdate }</td>
							  <td align="center" >${poMap.depotname }</td>
							  <td align="center" >${poMap.articlename }</td>
							  <td align="center" >${poMap.factory }</td>
							  <td align="center" >${poMap.articlespec }</td>
							  <td align="center" >${poMap.unitname }</td>
							  <td align="center" >${poMap.serial }</td>
							  <td align="center" >${poMap.expiredate }</td>
							  <td align="center" >${poMap.price }</td>
							  <td align="right" > ${poMap.num }</td>
							  <td align="right" > ${poMap.amount }</td>
							  <td align="right" > ${poMap.backnum }</td>
							  <td align="right" > ${poMap.backamount }</td>
	                        </tr>
	                        <s:if test="#stuts.index==viewStockinDetailList.size-1">
	                            <tr>
	                              <td colspan="12"></td>
		                           <td style="text-align:right;font-weight:bold;">入库小计:<s:property value="#smallnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">入库小计:&nbsp;&nbsp;<s:property value="#smallamount" /></td>
		                           <td style="text-align:right;font-weight:bold;">退货小计:<s:property value="#smallnumback" /></td>
		                           <td  style="text-align:right;font-weight:bold;">退货小计:&nbsp;&nbsp;<s:property value="#smallamountback" /></td>
		                       </tr> 
	                        </s:if>
	                        <s:set id="p_supplierid" value="#c_supplierid"></s:set>
                        </s:iterator>
                         <tr>
                              <td colspan="12"></td>
		                      <td style="text-align:right;font-weight:bold;">入库总计:<s:property value="#smallnum" /></td>
		                      <td style="text-align:right;font-weight:bold;">入库总计:&nbsp;&nbsp;<s:property value="#totalamount" /></td>
		                      <td style="text-align:right;font-weight:bold;">退货总计:<s:property value="#smallnumback" /></td>
		                      <td style="text-align:right;font-weight:bold;">退货总计:&nbsp;&nbsp;<s:property value="#totalamountback" /></td>
		                 </tr> 
                         <tr>
		                      <td colspan="16" style="text-align:left;font-weight:bold;">
		                              净入库数量:&nbsp;&nbsp;<s:property value="#totalnum - #totalnumback" />&nbsp;&nbsp;&nbsp;&nbsp;
		                        &nbsp;&nbsp;&nbsp;&nbsp;净入库金额:&nbsp;&nbsp;<s:property value="#totalamount - #totalamountback" />
		                      </td>
		                      <s:set id="nettotal" name="nettotal" value="#totalnum - #totalnumback"></s:set>
		                 </tr> 
                      	</s:if> 
                      	<s:else>
                      	      <tr>
                      	         <td colspan="16">无入库记录！</td>
                      	      </tr>
                      	</s:else>
                       </tbody>
                      </table>
                      </div>
                      <div id="printDivSale">
                      <table    border="1"  style="font-size:12px;border-collapse: collapse;width:1500px;">
                        <thead>
                            <tr class="trHeader">
                                 <td colspan="18">
                                             销售出库及销售退货信息
                                 </td>
                            </tr>
	                        <tr class="trHeader">
	                              <td  style="text-align: center;width:40px;">序号</td>
								  <td style="text-align: center;width:120px;">客户名称</td>
								  <td style="text-align: center;width:80px;">销售单号</td>
								  <td style="text-align: center;width:80px;">销售日期</td>
								  <td style="text-align: center;width:60px;">业务员</td>
								  <td style="text-align: center;width:120px;">品名</td>
								  <td style="text-align: center;width:180px;">厂家</td>
								  <td style="text-align: center;width:110px;">规格</td>
								  <td style="text-align: center;width:40px;">单位</td>
								  <td style="text-align: center;width:80px;">批号</td>
								  <td style="text-align: center;width:80px;">有效期</td>
								  <td style="text-align: center;width:50px;">销售价</td>
								  <td style="text-align: center;width:70px;">销售数量</td>
								  <td style="text-align: center;width:90px;">销售金额</td>
								  <td style="text-align: center;width:50px;">成本价</td>
								  <td style="text-align: center;width:90px;">利润额</td>
								  <td style="text-align: center;width:70px;">退货数量</td>
								  <td style="text-align: center;width:90px;">退货金额</td>
	                        </tr>
                        </thead>
                        <tbody id="view_data_tbody">
                        <s:set id="totalamount" name="totalamount" value="0"></s:set>
                        <s:set id="smallamount" name="smallamount" value="0"></s:set>
                        <s:set id="totalnum" name="totalnum" value="0"></s:set>
                        <s:set id="smallnum" name="smallnum" value="0"></s:set>
                        <s:set id="totalamountback" name="totalamountback" value="0"></s:set>
                        <s:set id="smallamountback" name="smallamountback" value="0"></s:set>
                        <s:set id="totalnumback" name="totalnumback" value="0"></s:set>
                        <s:set id="smallnumback" name="smallnumback" value="0"></s:set>
                        <s:set id="totalbenift" name="totalbenift" value="0"></s:set>
                        <s:set id="smallbenift" name="smallbenift" value="0"></s:set>
                        <s:set id="p_customerid" name="p_customerid" value=""></s:set>
                        <s:set id="c_customerid" name="c_customerid" value=""></s:set>
                        <s:if test="viewSaleDetailList.size >0">
                        <s:iterator value="viewSaleDetailList" var="poMap" status="stuts">
                          <s:set id="c_customerid" value="#poMap.suppliername"></s:set>
                          <s:if test="#stuts.index!=0&&#p_customerid!=#c_customerid">
                               <tr>
                                   <td colspan="12"></td>
		                           <td style="text-align:right;font-weight:bold;">销售小计:<s:property value="#smallnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">销售小计:&nbsp;&nbsp;<s:property value="#smallamount" /></td>
		                           <td></td>
		                           <td style="text-align:right;font-weight:bold;">利润小计:&nbsp;&nbsp;<s:property value="#smallbenift" /></td>
		                            <td style="text-align:right;font-weight:bold;">退货小计:<s:property value="#smallnumback" /></td>
		                           <td  style="text-align:right;font-weight:bold;">退货小计:&nbsp;&nbsp;<s:property value="#smallamountback" /></td>
		                       </tr> 
		                       <s:set id="smallamount" value="0"></s:set>
		                       <s:set id="smallnum" value="0"></s:set>
		                       <s:set id="smallamountback" value="0"></s:set>
		                       <s:set id="smallnumback" value="0"></s:set>
		                       <s:set id="smallbenift" value="0"></s:set>
                          </s:if>
                          <s:set id="smallamount" value="#smallamount+#poMap.amount"></s:set>
                          <s:set id="totalamount" value="#totalamount+#poMap.amount"></s:set>
                          <s:set id="smallnum" value="#smallnum+#poMap.num"></s:set>
                          <s:set id="totalnum" value="#totalnum+#poMap.num"></s:set>
                          <s:set id="smallamountback" value="#smallamountback+#poMap.backamount"></s:set>
                          <s:set id="totalamountback" value="#totalamountback+#poMap.backamount"></s:set>
                          <s:set id="smallnumback" value="#smallnumback+#poMap.backnum"></s:set>
                          <s:set id="totalnumback" value="#totalnumback+#poMap.backnum"></s:set>
                          <s:set id="smallbenift" value="#smallbenift+#poMap.benifitamount"></s:set>
                          <s:set id="totalbenift" value="#totalbenift+#poMap.benifitamount"></s:set>
	                        <tr class="trTBody">
	                          <td  align="center" >${stuts.index+1 }</td>
							  <td align="left" >${poMap.customername }</td>
							  <td align="center" >${poMap.salebillno }</td>
							  <td align="center" >${poMap.createdate }</td>
							  <td align="center" >${poMap.accountname }</td>
							  <td align="center" >${poMap.articlename }</td>
							  <td align="center" >${poMap.factory }</td>
							  <td align="center" >${poMap.articlespec }</td>
							  <td align="center" >${poMap.unitname }</td>
							  <td align="center" >${poMap.serial }</td>
							  <td align="center" >${poMap.expiredate }</td>
							  <td align="center" >${poMap.saleprice }</td>
							  <td align="right" > ${poMap.num }</td>
							  <td align="right" > ${poMap.amount }</td>
							  <td align="right" > ${poMap.price }</td>
							  <td align="right" > ${poMap.benifitamount }</td>
							  <td align="right" > ${poMap.backnum }</td>
							  <td align="right" > ${poMap.backamount }</td>
	                        </tr>
	                        <s:if test="#stuts.index==viewSaleDetailList.size-1">
	                            <tr>
	                              <td colspan="12"></td>
		                           <td style="text-align:right;font-weight:bold;">销售小计:<s:property value="#smallnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">销售小计:&nbsp;&nbsp;<s:property value="#smallamount" /></td>
		                           <td></td>
		                           <td style="text-align:right;font-weight:bold;">利润小计:&nbsp;&nbsp;<s:property value="#smallbenift" /></td>
		                            <td style="text-align:right;font-weight:bold;">退货小计:<s:property value="#smallnumback" /></td>
		                           <td  style="text-align:right;font-weight:bold;">退货小计:&nbsp;&nbsp;<s:property value="#smallamountback" /></td>
		                       </tr> 
	                        </s:if>
	                        <s:set id="p_customerid" value="#c_customerid"></s:set>
                        </s:iterator>
                         <tr>
                              <td colspan="12"></td>
		                      <td style="text-align:right;font-weight:bold;">销售总计:<s:property value="#smallnum" /></td>
		                      <td style="text-align:right;font-weight:bold;">销售总计:&nbsp;&nbsp;<s:property value="#totalamount" /></td>
		                      <td></td>
		                      <td style="text-align:right;font-weight:bold;">利润总计:&nbsp;&nbsp;<s:property value="#totalbenift" /></td>
		                      <td style="text-align:right;font-weight:bold;">退货总计:<s:property value="#smallnumback" /></td>
		                      <td style="text-align:right;font-weight:bold;">退货总计:&nbsp;&nbsp;<s:property value="#totalamountback" /></td>
		                 </tr> 
                         <tr>
		                      <td colspan="18" style="text-align:left;font-weight:bold;">
		                              净销售数量:&nbsp;&nbsp;<s:property value="#totalnum - #totalnumback" />&nbsp;&nbsp;&nbsp;&nbsp;
		                        &nbsp;&nbsp;&nbsp;&nbsp;净销售金额:&nbsp;&nbsp;<s:property value="#totalamount - #totalamountback" />
		                        &nbsp;&nbsp;&nbsp;&nbsp;净利润金额:&nbsp;&nbsp;<s:property value="#totalbenift" />
		                      </td>
		                      <s:set id="nettotal" name="nettotal" value="#nettotal - (#totalnum - #totalnumback)"></s:set>
		                 </tr> 
                      	</s:if>  
                      	<s:else>
                      	      <tr>
                      	         <td colspan="18">无销售记录！</td>
                      	      </tr>
                      	</s:else>
                       </tbody>
                      </table>
             </div>
            <div id="printDivStockCheck">
                <table    border="1"  style="font-size:12px;border-collapse: collapse;width:1500px;">
                        <thead>
                            <tr class="trHeader">
                                 <td colspan="16">
                                             库存盘点信息
                                 </td>
                            </tr>
	                        <tr class="trHeader">
	                              <td  style="text-align: center;width:40px;">序号</td>
								  <td style="text-align: center;width:80px;">盘点单号</td>
								  <td style="text-align: center;width:80px;">盘点日期</td>
								  <td style="text-align: center;width:60px;">仓库</td>
								  <td style="text-align: center;width:150px;">品名</td>
								  <td style="text-align: center;width:180px;">厂家</td>
								  <td style="text-align: center;width:110px;">规格</td>
								  <td style="text-align: center;width:40px;">单位</td>
								  <td style="text-align: center;width:80px;">批号</td>
								  <td style="text-align: center;width:80px;">有效期</td>
								  <td style="text-align: center;width:50px;">成本价</td>
								  <td style="text-align: center;width:70px;">电脑数量</td>
								  <td style="text-align: center;width:90px;">实际数量</td>
								  <td style="text-align: center;width:70px;">损益数量</td>
								  <td style="text-align: center;width:90px;">损益金额</td>
	                        </tr>
                        </thead>
                        <tbody id="view_data_tbody">
                        <s:set id="totallostamount" name="totallostamount" value="0"></s:set>
                        <s:set id="smalllostamount" name="smalllostamount" value="0"></s:set>
                        <s:set id="totallostnum" name="totallostnum" value="0"></s:set>
                        <s:set id="smalllostnum" name="smalllostnum" value="0"></s:set>
                        <s:set id="totalrealnum" name="totalrealnum" value="0"></s:set>
                        <s:set id="smallrealnum" name="smallrealnum" value="0"></s:set>
                        <s:set id="totalcomputenum" name="totalcomputenum" value="0"></s:set>
                        <s:set id="smallcomputenum" name="smallcomputenum" value="0"></s:set>
                        <s:set id="p_articleid" name="p_articleid" value=""></s:set>
                        <s:set id="c_articleid" name="c_articleid" value=""></s:set>
                        <s:if test="viewStockCheckList.size >0">
                        <s:iterator value="viewStockCheckList" var="poMap" status="stuts">
                          <s:set id="c_articleid" value="#poMap.suppliername"></s:set>
                          <s:if test="#stuts.index!=0&&#p_articleid!=#c_articleid">
                               <tr>
                                   <td colspan="12"></td>
                                   <td style="text-align:right;font-weight:bold;">电脑数小计:<s:property value="#smallcomputenum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">实际数小计:&nbsp;&nbsp;<s:property value="#smallrealnum" /></td>
		                           <td style="text-align:right;font-weight:bold;">损益数小计:<s:property value="#smalllostnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">损益小计:&nbsp;&nbsp;<s:property value="#smalllostamount" /></td>
		                       </tr> 
		                       <s:set id="smallamount" value="0"></s:set>
		                       <s:set id="smalllostnum" value="0"></s:set>
		                       <s:set id="smallrealnum" value="0"></s:set>
		                       <s:set id="smallcomputenum" value="0"></s:set>
                          </s:if>
                          <s:set id="smallcomputenum" value="#smallcomputenum+#poMap.computernum"></s:set>
                          <s:set id="totalcomputenum" value="#totalcomputenum+#poMap.computernum"></s:set>
                          <s:set id="smallrealnum" value="#smallrealnum+#poMap.realnum"></s:set>
                          <s:set id="totalrealnum" value="#totalrealnum+#poMap.realnum"></s:set>
                          <s:set id="smalllostnum" value="#smalllostnum+#poMap.lostnum"></s:set>
                          <s:set id="totallostnum" value="#totallostnum+#poMap.lostnum"></s:set>
                          <s:set id="smalllostamount" value="#smalllostamount+#poMap.lostamount"></s:set>
                          <s:set id="totallostamount" value="#totallostamount+#poMap.lostamount"></s:set>
	                        <tr class="trTBody">
	                          <td  align="center" >${stuts.index+1 }</td>
							  <td align="center" >${poMap.stockcheckcode }</td>
							  <td align="center" >${poMap.createdate }</td>
							  <td align="center" >${poMap.depotname }</td>
							  <td align="center" >${poMap.articlename }</td>
							  <td align="center" >${poMap.factory }</td>
							  <td align="center" >${poMap.articlespec }</td>
							  <td align="center" >${poMap.unitname }</td>
							  <td align="center" >${poMap.serial }</td>
							  <td align="center" >${poMap.expiredate }</td>
							  <td align="center" >${poMap.costprice }</td>
							  <td align="right" > ${poMap.computernum }</td>
							  <td align="right" > ${poMap.realnum }</td>
							  <td align="right" > ${poMap.lostnum }</td>
							  <td align="right" > ${poMap.lostamount }</td>
	                        </tr>
	                        <s:if test="#stuts.index==viewStockCheckList.size-1">
	                            <tr>
	                              <td colspan="11"></td>
                                   <td style="text-align:right;font-weight:bold;">电脑数小计:<s:property value="#smallcomputenum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">实际数小计:&nbsp;&nbsp;<s:property value="#smallrealnum" /></td>
		                           <td style="text-align:right;font-weight:bold;">损益数小计:<s:property value="#smalllostnum" /></td>
		                           <td  style="text-align:right;font-weight:bold;">损益小计:&nbsp;&nbsp;<s:property value="#smalllostamount" /></td>
		                       </tr> 
	                        </s:if>
	                        <s:set id="p_articleid" value="#c_articleid"></s:set>
                        </s:iterator>
                         <tr>
                              <td colspan="11"></td>
                               <td style="text-align:right;font-weight:bold;">电脑数总计:<s:property value="#totalcomputenum" /></td>
		                      <td style="text-align:right;font-weight:bold;">实际数总计:&nbsp;&nbsp;<s:property value="#totalrealnum" /></td>
		                      <td style="text-align:right;font-weight:bold;">损益数总计:<s:property value="#totallostnum" /></td>
		                      <td style="text-align:right;font-weight:bold;">损益总计:&nbsp;&nbsp;<s:property value="#totallostamount" /></td>
		                 </tr> 
                         <tr>
		                      <td colspan="15" style="text-align:left;font-weight:bold;">
		                              净损益数量:&nbsp;&nbsp;<s:property value="#totallostnum" />&nbsp;&nbsp;&nbsp;&nbsp;
		                        &nbsp;&nbsp;&nbsp;&nbsp;净损益金额:&nbsp;&nbsp;<s:property value="#totallostamount" />
		                      </td>
		                      <s:set id="nettotal" name="nettotal" value="#nettotal + #totallostnum"></s:set>
		                 </tr> 
                         <tr>
		                      <td colspan="15" style="text-align:center;font-weight:bold;color:blue;font-size:15px;">
		                              【应余库存:&nbsp;&nbsp;<s:property value="#nettotal" />】&nbsp;&nbsp;&nbsp;&nbsp;
		                      </td>
		                 </tr> 
                      	</s:if> 
                      	<s:else>
                      	      <tr>
                      	         <td colspan="15">无盘点记录！</td>
                      	      </tr>
                      	      <td colspan="15" style="text-align:center;font-weight:bold;color:blue;font-size:15px;">
		                              【应余库存:&nbsp;&nbsp;<s:property value="#nettotal" />】&nbsp;&nbsp;&nbsp;&nbsp;
		                      </td>
                      	</s:else> 
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