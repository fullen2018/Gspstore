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
<jsp:include page="/inc.jsp"></jsp:include>
	<style>
	.huibd {
		border: 1px solid #000000;
			}
	td {
		white-space: nowrap;
		font-size: 12px;
		}
	</style>
<link rel="stylesheet" href="<%=basePath%>/main/include/css/button-usr.css" type="text/css"></link>
<script language="javascript" type="text/javascript" src="<%=basePath%>/main/include/js/form_valid.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>/jslib/datepicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>/main/css/tablelock.css" mce_href="tablelock.css" /> 
<script type="text/javascript" language="javascript" src="<%=basePath%>/main/js/tablelock.js"></script> 
<script language="javascript" type="text/javascript" src="<%=basePath%>/jslib/LodopFuncs.js"></script>
<script type="text/javascript" charset="utf-8">
  var myForm;
var LODOP;
var print_row_height = 25;//每行的高度
var print_top = 0;//最上一行距离顶端的距离
var page_total = 0;//总计页数
var page_current = 1;//当前页码
var page_row_num = 4;//每页打印行数
var page_small_amount =0;//每页小计金额
$(document).ready(function() {
		//$.fn.TableLock({table:'stockinDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:955});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	$("#doPrint").click(function(){
		 printSaleBill();
	});
	
	$("input").attr("readOnly",true);
	$("#doReturn").attr("disabled",false);
	$(window.parent.document.getElementById("loading")).fadeOut("slow",function(){
         $(this).css("display","none");
    });
});

function getFormatValue(strType,strValue){
       return LODOP.FORMAT(strType,strValue);
   }


function printSaleBill(){
    LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
    //LODOP.PRINT_INITA(0,3,2500,700,"打印控件功能演示_Lodop功能_打印维护权限控制");
	LODOP.SET_PRINT_STYLE("FontName","黑体");
	LODOP.SET_PRINT_STYLE("FontSize",15);
	var $tbody = $("#add_stockinDetail_table");
	var rows = $tbody.find('>tr');
	if (rows.length%page_row_num==0){
	   page_total = Math.floor(rows.length/page_row_num) ;
	}else{
	   page_total = Math.floor(rows.length/page_row_num) + 1;
	}
	print_top = 15;
	LODOP.ADD_PRINT_TEXT(print_top,3,291,32,"${companyPage.companyname }采购入库单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	printTop();
	printTopSecond();
	printAddHead();
	page_small_amount = 0;
	for (var i=0;i<rows.length;i++){
	   if (i!=0&&i%page_row_num==0){
	        printBottomAmount();
	        page_small_amount = 0;
	        LODOP.NewPage();
	        page_current++;
	        print_top = 15;
			LODOP.ADD_PRINT_TEXT(print_top,3,291,32,"${companyPage.companyname }采购入库单");
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
			LODOP.SET_PRINT_STYLEA(0,"Horient",2);
			printTop();
			printTopSecond();
			printAddHead();
	   }
	   printAddRow($(rows[i]));
	   if (i==rows.length-1){
	      printBottomAmount();
	   }
	}
	//alert($("#printDiv").html());
	//LODOP.ADD_PRINT_TABLE(25,2,2000,46,$("#printDiv").html());
	//LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	//LODOP.SET_PRINT_STYLEA(0,"Horient",3);
	LODOP.SET_PRINT_PAGESIZE(1,2100,930,"");
	LODOP.PREVIEW();
}
function printTop(){
    drowtablegrid();
    print_top =print_top + 25;
    LODOP.ADD_PRINT_TEXT(print_top,10,240,print_row_height,"供货商名称："+$("#suppliername").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,243,130,print_row_height,"入库仓库："+$("#depotname").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,370,230,print_row_height,"入库单号："+$("#stockinbillno").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    
    LODOP.ADD_PRINT_TEXT(print_top,800,140,print_row_height,"第"+page_current+"页,共"+page_total+"页");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
}

function printTopSecond(){
    print_top =print_top + 25;
    LODOP.ADD_PRINT_TEXT(print_top,10,200,print_row_height,"打印日期："+getFormatValue("TIME:yyyy-mm-dd hh:mm",new Date().toLocaleString()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,300,10,print_row_height,"");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,230,200,print_row_height,"制单日期："+getFormatValue("TIME:yyyy-mm-dd hh:mm",$("#createdate").val()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,510,120,print_row_height,"");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,530,220,print_row_height,"合计金额(小写)："+$("#amount").val()+"(元)");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
}
function printBottomAmount(){
    LODOP.ADD_PRINT_TEXT(270,10,300,print_row_height,"合计金额(大写)："+getFormatValue("UpperMoney",$("#amount").val()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270,300,350,print_row_height,"本页金额(大写)："+getFormatValue("UpperMoney",page_small_amount));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270,600,100,print_row_height,"质量合格：合格");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270+print_row_height,10,140,print_row_height,"制单人："+$("#crtusername").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270+print_row_height,150,140,print_row_height,"发货人：");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270+print_row_height,290,140,print_row_height,"复核人：");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270+print_row_height,430,140,print_row_height,"收货人：");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(270+print_row_height+13,10,720,print_row_height,"地址：${companyPage.address }  电话：${companyPage.telephone }  备注：${companyPage.billmeno }");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(40,530,320,print_row_height,"本页小计(小写):"+page_small_amount+"(元)");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
}
function printAddHead(){
   print_top = print_top + 25;
   LODOP.ADD_PRINT_TEXT(print_top,10,200,print_row_height,"品种名称/规格/生产厂家");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,210,35,print_row_height,"单位");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,245,60,print_row_height,"进货数量");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
    LODOP.ADD_PRINT_TEXT(print_top,305,80,print_row_height,"统一零售价");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,385,35,print_row_height,"扣率");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
    LODOP.ADD_PRINT_TEXT(print_top,415,50,print_row_height,"进价");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,465,60,print_row_height,"金额");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,525,250,print_row_height,"批号/有效期/批准文号");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
}

function printAddRow(row){
   print_top =print_top + print_row_height;
   LODOP.ADD_PRINT_TEXT(print_top,10,200,print_row_height,row.find("input[name*='.articlename']").eq(0).val()+
   "/"+row.find("input[name*='.articlespec']").eq(0).val()+"/"+row.find("input[name*='.factory']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
   LODOP.ADD_PRINT_TEXT(print_top,210,35,print_row_height,row.find("input[name*='.unitname']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,235,60,print_row_height,row.find("input[name*='.num']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,295,80,print_row_height,row.find("input[name*='.retailprice']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,375,35,print_row_height,"");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,410,50,print_row_height,row.find("input[name*='.price']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,460,60,print_row_height,row.find("input[name*='.amount']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   
   LODOP.ADD_PRINT_TEXT(print_top,520,300,print_row_height,row.find("input[name*='.serial']").eq(0).val()+"/"+
   row.find("input[name*='.expiredate']").eq(0).val().substr(0,10)+"/"+
   row.find("input[name*='.fileno']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
   print_top =print_top + 10;
   page_small_amount = page_small_amount + parseFloat(row.find("input[name*='.amount']").eq(0).val(),10);
}

  function drowtablegrid(){
   LODOP.ADD_PRINT_SHAPE(2,3,3,765,320,0,1,"#800000");//矩形框
   LODOP.ADD_PRINT_SHAPE(1,35,255,280,1,0,1,"#800000");//横线
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_SHAPE(1,85,3,765,1,0,1,"#800000");//横线
   LODOP.ADD_PRINT_SHAPE(1,108,3,765,1,0,1,"#800000");//横线 线类型,距离顶部,距离左边,宽度,,,粗细
   LODOP.ADD_PRINT_SHAPE(1,265,3,765,1,0,1,"#800000");//横线 线类型,距离顶部,距离左边,宽度,,,粗细
   LODOP.ADD_PRINT_SHAPE(1,292,3,765,1,0,1,"#800000");//横线 线类型,距离顶部,距离左边,宽度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,210,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,245,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,305,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,380,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,415,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,465,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
   LODOP.ADD_PRINT_SHAPE(0,85,519,1,180,0,1,"#800000");//竖线 线类型,距离顶部,距离左边,1,高度,,,粗细
}
</script>
</head>
<body >
        <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
		</object> 
		 <div >
                <table width="960px;">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="10"  align="center" style="font-size:14px;"><strong>采购入库单详情</strong></td>
                  </tr>
					<tr>
					    <td align="left">供应商</td>
                    	<td align="left">
                    	     <input type="text" readonly="readonly" name="suppliername" id="suppliername" value="${stockinPage.supplierpage.suppliername }"/>
                    	</td>
                    	<td align="left">仓库</td>
                    	<td align="left">
                    	   <input type="text" readonly="readonly" name="depotname" id="depotname" value="${stockinPage.depotpage.depotname }"/>
                    	</td>
                   	 	<td align="left">入库日期</td>
                    	<td align="left">
                    		<input name="stockindate" id="stockindate" readonly="readonly" type="text"  maxlength="" value="${stockinPage.stockindate }" data-options="required:true" style="width:135px;"/>
                    	</td>
                   	 	<td align="left">入库单号</td>
                    	<td align="left">
                    		<input name="stockinbillno" id="stockinbillno" readonly="readonly" type="text"  maxlength="" value="${stockinPage.stockinbillno }" data-options="required:true" style="width:90px;"/>
                    	</td>
                   	 	<td align="left">总金额</td>
                    	<td align="left">
                    		<input name="amount" id="amount" readonly="readonly" type="text"  maxlength="" value="${stockinPage.amount }" data-options="required:true" style="width:80px;"/>
                    	</td>
					</tr>
					<tr>
					    <td align="left">操作人姓名</td>
                    	<td align="left">
                    		<input id="crtusername" name="crtusername" type="text" class="easyui-validatebox" maxlength="50" value="${stockinPage.crtusername }" data-options="required:true"/>
                    	</td>
                    	<td align="left">创建日期</td>
                    	<td align="left">
                    		<input name="createdate" id="createdate" readonly="readonly" type="text"  maxlength="" value="${stockinPage.stockindate }" data-options="required:true" style="width:150px;"/>
                    	</td>
					</tr>
				 </table>
			    </div>
	<div class="tabs">
        <div title="采购入库单明细" style="width:100%;text-align:center;">
             <div id="datatable">
                <table border="0" cellpadding="2" cellspacing="0" >
                        <tr bgcolor="#E6E6E6">
                              <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE">编码</td>
							  <td align="center" bgcolor="#EEEEEE">品种</td>
							  <td align="center" bgcolor="#EEEEEE">规格</td>
							  <td align="center" bgcolor="#EEEEEE">厂家</td>
							   <td align="center" bgcolor="#EEEEEE">单位</td>
							  <td align="center" bgcolor="#EEEEEE">批号</td>
							  <td align="center" bgcolor="#EEEEEE">有效期</td>
							  <td align="center" bgcolor="#EEEEEE">数量</td>
							  <td align="center" bgcolor="#EEEEEE">进价</td>
							  <td align="center" bgcolor="#EEEEEE">金额</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_stockinDetail_table">
                        <s:if test="stockinDetailList.size >0">
                        <s:iterator value="stockinDetailList" var="poVal" status="stuts">
	                        <tr id="${poVal.stockindetailno }">
	                              <td align="center">
	                              <input type="checkbox" name="ck"/>
	                              </td>
								  <td align="left">
								    <input type="text" name=".articleid" value="${poVal.articlepage.articleid}" class="easyui-validatebox"  style="width: 70px;" />
								  </td>
								  <td align="left">
								    <input type="text" name=".articlename" value="${poVal.articlepage.articlename }" class="easyui-validatebox"  style="width: 160px;" />
								  </td>
								  <td align="left">
								    <input type="text" name=".articlespec" value="${poVal.articlepage.articlespec }" class="easyui-validatebox"  style="width: 60px;" />
								  </td>
								  <td align="left">
								    <input type="hidden" name=".retailprice" value="${poVal.articlepage.retailprice }" />
								    <input type="hidden" name=".fileno" value="${poVal.articlepage.fileno }" />
								    <input type="text" name=".factory" value="${poVal.articlepage.factory }" class="easyui-validatebox"  style="width: 90px;" />
								  </td>
								  <td align="left">
								    <input type="text" name=".unitname" value="${poVal.articlepage.unitdic.paramName }" class="easyui-validatebox"  style="width: 80px;" />
								  </td>
								  <td align="left">
								  <input  type="text" name=".serial" value="${poVal.serial }"  class="easyui-validatebox" style="width:80px;" ></td>
								  <td align="left">
								  <input  type="text" name=".expiredate" value="${fn:substring(poVal.expiredate,0,10) }" class="easyui-validatebox" style="width:70px;" >
								  </td>
								  <td align="left">
								   <input type="text" name=".num" value="${poVal.num }" class="easyui-validatebox"  style="width:60px;" >
								   </td>
								  <td align="left">
								   <input name=".price" class="easyui-validatebox" type="text" value="${poVal.price }"  style="width:60px;" >
								  </td>
								  <td align="left">
								   <input name=".amount" class="easyui-validatebox" type="text" value="${poVal.amount }"
								   style="width:80px;" >
								  </td>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                      </table>
             </div>
             <div align="center">
				<table>
                  <tr>
                    <td height="50" colspan="8"  align="center">
                        <input value=" 关 闭 " type="button" id="doReturn"/>
                         <input value=" 打 印 " type="button" id="doPrint"/>
                    </td>
                  </tr>
                 </table>
		    </div>
         </div>
        </div>
       

</body>
</html>