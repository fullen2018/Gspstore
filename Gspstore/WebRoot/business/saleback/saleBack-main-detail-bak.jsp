<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
var page_row_num = 2;//每页打印行数
var page_small_amount =0;//每页小计金额
$(document).ready(function() {
		//$.fn.TableLock({table:'saleBackDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:950});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	$("#doPrint").click(function(){
		 printSaleBill();
	});
    $("input").attr("readOnly",true);
	$("#doReturn").attr("disabled",false);
	$("input[name*='isreceive']").eq(0).val(dictGetName('004','${salePage.isreceive }'));
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
	var $tbody = $("#add_saleBackDetail_table");
	var rows = $tbody.find('>tr');
	if (rows.length%page_row_num==0){
	   page_total = Math.floor(rows.length/page_row_num) ;
	}else{
	   page_total = Math.floor(rows.length/page_row_num) + 1;
	}
	print_top = 8;
	LODOP.ADD_PRINT_TEXT(print_top,3,291,32,"长沙泰正医药科技有限公司销售退货单");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(0,"Horient",2);
	printTop();
	printTopSecond();
	printAddHead();
	for (var i=0;i<rows.length;i++){
	   if (i!=0&&i%page_row_num==0){
	        printBottomAmount();
	        page_small_amount = 0;
	        LODOP.NewPage();
	        page_current++;
	        print_top = 8;
			LODOP.ADD_PRINT_TEXT(print_top,3,291,32,"长沙泰正医药科技有限公司销售退货单");
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
	LODOP.SET_PRINT_PAGESIZE(1,2500,700,"");
	LODOP.PREVIEW();
}
function printTop(){
    print_top =print_top + 25;
    LODOP.ADD_PRINT_TEXT(print_top,3,200,print_row_height,"客户名称："+$("#customername").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,203,130,print_row_height,"业务员："+$("#accountname").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,330,230,print_row_height,"原销售单号："+$("#salebillno").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,560,320,print_row_height,"退货单号："+$("#salebackno").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,830,140,print_row_height,"第"+page_current+"页,共"+page_total+"页");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
}
function printTopSecond(){
    print_top =print_top + 25;
    LODOP.ADD_PRINT_TEXT(print_top,3,260,print_row_height,"打印日期："+getFormatValue("TIME:yyyy-mm-dd hh:mm:ss",new Date().toLocaleString()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,233,130,print_row_height,"制单人："+$("#crtusername").val());
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,360,230,print_row_height,"制单日期："+getFormatValue("TIME:yyyy-mm-dd hh:mm:ss",$("createdate").val()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,580,320,print_row_height,"");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(print_top,700,230,print_row_height,"合计金额："+$("#amount").val()+"(元)");
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
}
function printBottomAmount(){
    LODOP.ADD_PRINT_TEXT(235,5,340,print_row_height,"本页小计金额(大写)："+getFormatValue("UpperMoney",page_small_amount));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
    LODOP.ADD_PRINT_TEXT(235,540,340,print_row_height,"合计金额(大写)："+getFormatValue("UpperMoney",$("#amount").val()));
    LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
    LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
}
function printAddHead(){
   print_top = print_top + 25;
   LODOP.ADD_PRINT_TEXT(print_top,3,140,print_row_height,"品种名称");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,143,90,print_row_height,"厂家");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,233,80,print_row_height,"规格");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,313,60,print_row_height,"单位");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,373,70,print_row_height,"批号");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,443,80,print_row_height,"有效期");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,523,90,print_row_height,"统计零售价");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,613,70,print_row_height,"退货价");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,683,80,print_row_height,"退货数量");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,763,80,print_row_height,"金额");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,843,80,print_row_height,"防伪号");
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
}
function printAddRow(row){
   //alert(row.html());
   print_top =print_top + print_row_height;
   LODOP.ADD_PRINT_TEXT(print_top,3,140,print_row_height,row.find("input[name*='.articlename']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
   LODOP.ADD_PRINT_TEXT(print_top,143,90,print_row_height,row.find("input[name*='.factory']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
   LODOP.ADD_PRINT_TEXT(print_top,233,80,print_row_height,row.find("input[name*='.articlespec']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",1);
   LODOP.ADD_PRINT_TEXT(print_top,313,60,print_row_height,row.find("input[name*='.unitname']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,373,70,print_row_height,row.find("input[name*='.serial']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,443,80,print_row_height,row.find("input[name*='.expiredate']").eq(0).val().substr(0,10));
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,523,90,print_row_height,row.find("input[name*='.retailprice']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,613,70,print_row_height,row.find("input[name*='.saleprice']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,683,80,print_row_height,row.find("input[name*='.num']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,763,80,print_row_height,row.find("input[name*='.amount']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   LODOP.ADD_PRINT_TEXT(print_top,843,80,print_row_height,row.find("input[name*='.secretno']").eq(0).val());
   LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
   LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
   page_small_amount = page_small_amount + parseFloat(row.find("input[name*='.amount']").eq(0).val(),10);
}
</script>
</head>
<body>
         <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
		</object> 
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
              <div>
                <table width="957">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="8"  align="center" style="font-size:14px;"><strong>销售退货单</strong></td>
                    <input type="hidden" name="salebackno" value="${saleBackPage.salebackno }"/>
                  </tr>
					<tr>
                   	 	<td align="left">原销售单号</td>
                    	<td align="left">
                    		<input id="salebillno" name="salebillno" type="text" class="easyui-validatebox" maxlength="20" value="${saleBackPage.salebillno }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">退货客户</td>
                    	<td align="left">
                    		<input id="customername" name="customername" type="text" class="easyui-validatebox" maxlength="20" value="${saleBackPage.customerpage.customername }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">退货日期</td>
                    	<td align="left">
                    		<input name="backdate" type="text" class="easyui-validatebox" maxlength="" value="${saleBackPage.backdate }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">业务员</td>
                    	<td align="left">
                    		<input id="accountname" name="accountname" type="text" class="easyui-validatebox" maxlength="20" value="${saleBackPage.accounterpage.accountname }" data-options="required:true"/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">原提成比例</td>
                    	<td align="left">
                    		<input id="salerate" name="salerate" type="text" class="easyui-numberbox" maxlength="" value="${saleBackPage.salerate }" data-options="required:true,precision:2,groupSeparator:','"/>
                    	</td>
                   	 	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="meno" type="text" class="easyui-validatebox" maxlength="100" value="${saleBackPage.meno }" data-options=""/>
                    	</td>
                   	 	<td align="left">是否收款</td>
                    	<td align="left">
                    		<input name="isreceive" type="text" class="easyui-validatebox" maxlength="" value="${saleBackPage.isreceive }" data-options=""/>
                    	</td>
                   	 	<td align="left">操作人姓名</td>
                    	<td align="left">
                    		<input id="crtusername" name="crtusername" type="text" class="easyui-validatebox" maxlength="50" value="${saleBackPage.crtusername }" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">修改日期</td>
                    	<td align="left">
                    		<input name="modifydate" type="text" class="easyui-validatebox" maxlength="" value="${saleBackPage.modifydate }" data-options=""/>
                    	</td>
                   	 	<td align="left">创建日期</td>
                    	<td align="left">
                    		<input id="createdate" name="createdate" type="text" class="easyui-validatebox" maxlength="" value="${saleBackPage.createdate }" data-options=""/>
                    	</td>
                   	 	<td align="left">总金额</td>
                    	<td align="left">
                    		<input id="amount" name="amount" type="text"  maxlength="" class="easyui-validatebox" value="${saleBackPage.amount }" data-options=""/>
                    	</td>
                   	 	<td align="left">退货单号</td>
                    	<td align="left">
                    	    <input id="salebackno" name="salebackno" type="text"  maxlength="" class="easyui-validatebox" value="${saleBackPage.salebackno }" data-options=""/>
                    	</td>
					</tr>
				 </table>
			    </div>


   <div class="tabs">
				
				
				 <div title="销售退货明细">
				  <table>
                  
                 
                   <tr>
                    <td align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="saleBackDetail_table">
                         <tr bgcolor="#E6E6E6">
                              <td align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE">品种</td>
							  <td align="center" bgcolor="#EEEEEE">规格</td>
							  <td align="center" bgcolor="#EEEEEE">单位</td>
							  <td align="center" bgcolor="#EEEEEE">厂家</td>
							  <td align="center" bgcolor="#EEEEEE">仓库</td>
							  <td align="center" bgcolor="#EEEEEE">批号</td>
							  <td align="center" bgcolor="#EEEEEE">有效期</td>
							  <td align="center" bgcolor="#EEEEEE">退货价</td>
							  <td align="center" bgcolor="#EEEEEE">退货数量</td>
							  <td align="center" bgcolor="#EEEEEE">退货金额</td>
							  <td align="center" bgcolor="#EEEEEE">防伪号</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_saleBackDetail_table">
                        <s:if test="saleBackDetailList.size >0">
                        <s:iterator value="saleBackDetailList" var="poVal" status="stuts">
	                       <tr>
	                              <td align="center">${stuts.index+1 }</td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].articlename" maxlength="20" type="text" value="${poVal.articlepage.articlename }" class="easyui-validatebox" style="width:150px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].articlespec" maxlength="20" type="text" value="${poVal.articlepage.articlespec }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].unitname" maxlength="20" type="text" value="${poVal.articlepage.unitdic.paramName }" class="easyui-validatebox" style="width:40px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].factory" maxlength="20" type="text" value="${poVal.articlepage.factory }" class="easyui-validatebox" style="width:85px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].depotname" maxlength="20" type="text" value="${poVal.depotpage.depotname }" class="easyui-validatebox" style="width:60px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].serial" maxlength="20" type="text" value="${poVal.serial }" class="easyui-validatebox" style="width:70px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].expiredate" maxlength="20" type="text" value="${poVal.expiredate }" class="easyui-validatebox" style="width:65px;" ></td>
								  <td align="left">
								     <input name="saleBackDetailList[${stuts.index }].retailprice" maxlength="" type="hidden" value="${poVal.articlepage.retailprice }" class="easyui-validatebox"  >
								     <input name="saleBackDetailList[${stuts.index }].saleprice" maxlength="" type="text" value="${poVal.saleprice }" class="easyui-validatebox" style="width:60px;" >
								  </td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].num" maxlength="" type="text" value="${poVal.num }" class="easyui-validatebox" style="width:70px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].amount" maxlength="" type="text" value="${poVal.amount }" class="easyui-validatebox" style="width:70px;" ></td>
								  <td align="left"><input name="saleBackDetailList[${stuts.index }].secretno" maxlength="50" type="text" value="${poVal.secretno }" class="easyui-validatebox" style="width:80px;" ></td>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                      </table>
                    </td>
                  </tr>
                </table> 
			  </div>
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
            </form>
        </div>

</body>
</html>