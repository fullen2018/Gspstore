<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    String widthtable = "857";
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
<script type="text/javascript" charset="utf-8">
var myForm;

$(document).ready(function() {
		//$.fn.TableLock({table:'stockBackDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	checkStockAgain();
	$(".tabs").tabs({width:950});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //购进退货明细追加
	 $("#button_add_stockBackDetail").bind("click", function(){
	  	  var tr =  $("#add_stockBackDetail_table_template tr").clone();
	 	 $("#add_stockBackDetail_table").append(tr);
	 	  resetTrNum('add_stockBackDetail_table');
	 });
	 //购进退货明细删除
	 $("#button_del_stockBackDetail").bind("click", function(){
	  	$("#add_stockBackDetail_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNum('add_stockBackDetail_table');
	 });
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'stockBackAction!addMain.action',
			success : function(data) {
				var json = $.parseJSON(data);
				$.messager.progress('close');
				 window.parent.window.closePayoutDialog();
				if (json && json.success) {
					//父窗口刷新datagrid,提示操作消息
					window.parent.window.datagrid.datagrid('reload');
					window.parent.window.datagridStockIn.datagrid('reload');
					window.parent.window.messageshow('成功',json.msg);
				} else {
				    window.parent.window.messageshow('失败','操作失败!'+json.msg);
				}
			}
		});
	$(window.parent.document.getElementById("loading")).fadeOut("slow",function(){
         $(this).css("display","none");
      });
});

	 //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
		});
	}

	//form提交
 	function dosubmit(){
		if($('#myForm').form('validate')){
		    if (checkStockAgain()){
		         if (totalamount<=0){
		            $.messager.alert('提示', "总计退货金额为零，请核查是否输入退货数量！", 'warn');
	                return;
		         }
			     $.messager.confirm('请确认','总计退货金额：'+totalamount+'，您确定提交吗？',function(r){   
				    if (r){
				       $.messager.progress({
							text : '数据保存中....',
							interval : 100
						});
			           myForm.submit();
			        }
			      });
			}
		}
   	}
   	 //检查主键重复
   	 var totalamount = 0.0;
	function checkStockAgain() {
	    totalamount = 0.0;
		$tbody = $("#add_stockinDetail_table");
		var stockkeyinfo = "";
		var flag = true;
		var rows = $tbody.find('>tr');
		for (var i=0;i<rows.length;i++){
		    var num = $("input[id*='_num']", rows[i]).numberbox("getValue");
		    var price = $("input[id*='_price']", rows[i]).val();
		    var amount = $("input[id*='_amount']", rows[i]).val(parseInt(num,10)*parseFloat(price,10));
		    totalamount += parseInt(num,10)*parseFloat(price,10);
		}
		$("#totalamount").html(totalamount.toFixed(2));
		return flag;
	}
	
	function computeAmount(obj){
	   var price = $(obj).parent().parent().find("input[id*='_price']").eq(0).val();
	   var num = $(obj).parent().parent().find("input[id*='_num']").eq(0).numberbox("getValue");
	   if (num!=null&&num!=""&&price!=null&&price!="")
	      $(obj).val(parseInt(num,10)*parseFloat(price,10));
	}
   	
</script>
</head>
<body>
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
             <div>
              <table border=0 width="950px;" style="border-collapse: collapse;">
                  <input type="hidden" name="stockBackPage.depotid" value="${stockinPage.depotid }" />
                  <input type="hidden" name="stockBackPage.supplierid" value="${stockinPage.supplierid }" />
                  <input type="hidden" name="stockBackPage.stockinno" value="${stockinPage.stockinbillno }" />
                  <tr bgcolor="#CCE6D1">
                     <td colspan="8"  align="center" style="font-size:14px;"><strong>购进退货单</strong></td>
                  </tr>
					<tr>
                   	 	<td align="left">退货仓库</td>
                    	<td align="left">
                    		<input name="depotid" disabled="disabled" type="text" value="${stockinPage.depotpage.depotname }" class="easyui-validatebox" maxlength="20" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">供应商</td>
                    	<td align="left">
                    		<input name="supplierid" disabled="disabled" type="text" value="${stockinPage.supplierpage.suppliername }" class="easyui-validatebox" maxlength="10" data-options="required:true"/>
                    	</td>
                    	<td align="left">退货入库单号</td>
                    	<td align="left">
                    		<input name="stockinno" type="text" disabled="disabled" value="${stockinPage.stockinbillno }" class="easyui-validatebox" maxlength="50" data-options=""/>
                    	</td>
                    	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="stockBackPage.meno" name="stockBackPage.stockinno" type="text" class="easyui-validatebox" maxlength="100" data-options=""/>
                    	</td>
					</tr>
				   </table>
			</div>
				
				
		<div class="tabs">
				<div title="购进退货明细">
				 <table >
                  
                 
                   <tr>
                    <td  align="left">
                       <table border="1" style="border-collapse: collapse;" cellpadding="2" cellspacing="0" id="stockBackDetail_table">
                         <tr bgcolor="#E6E6E6">
                              <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:140px;overflow: hidden;">品种名称</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:60px;">规格</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:80px;">厂家</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:40px;">单位</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:70px;">批号</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:70px;">有效期</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:60px;">入库数量</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:50px;">已退数量</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:90px;">退货价(原进价)</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:70px;">退货数量</td>
							  <td align="center" bgcolor="#EEEEEE" style="width:80px;">退货金额</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_stockinDetail_table">
                        <s:if test="stockinDetailList.size >0">
                        <s:iterator value="stockinDetailList" var="poVal" status="stuts">
	                        <tr id="${poVal.stockindetailno }">
	                              <td align="center">
	                                ${stuts.index+1 }
	                                <input type="hidden"  name="stockBackDetailList[${stuts.index }].stockindetailno" value="${poVal.stockindetailno }" />
	                                <input type="hidden"  name="stockBackDetailList[${stuts.index }].articleid" value="${poVal.articleid }" />
	                                <input type="hidden"  name="stockBackDetailList[${stuts.index }].serial" value="${poVal.serial }" />
	                                <input type="hidden"  name="stockBackDetailList[${stuts.index }].expiredate" value="${fn:substring(poVal.expiredate,0,10) }" />
	                                <input type="hidden"  name="stockBackDetailList[${stuts.index }].price" id="${poVal.stockindetailno }_price"  value="${poVal.price }"   />
	                              </td>
								  <td align="left">
								    ${poVal.articlepage.articlename }
								  </td>
								  <td align="left">
								    ${poVal.articlepage.articlespec }
								  </td>
								  <td align="left">
								    ${poVal.articlepage.factory }
								  </td>
								  <td align="left">
								    ${poVal.articlepage.unitdic.paramName }
								  </td>
								  <td align="center">
								    ${poVal.serial }
								  </td>
								  <td align="center">
								      ${fn:substring(poVal.expiredate,0,10) }
								  </td>
								  <td align="right">
								      ${poVal.num }
								  </td>
								  <td align="right">
								      ${poVal.backnum }
								  </td>
								  <td align="right">
								      ${poVal.price }
								  </td>
								  <td align="left">
								      <input name="stockBackDetailList[${stuts.index }].num" id="${poVal.stockindetailno }_num"
								         value="${poVal.num }" data-options="required:true,
								         precision:0,
								         min:0,max:${poVal.num - poVal.backnum }
								         " 
								         type="text" class="easyui-numberbox" style="width:70px;text-align: right;" />
								  </td>
								  <td align="left">
								      <input id="${poVal.stockindetailno }_amount" onclick="computeAmount(this)" readonly="readonly" name="stockBackDetailList[${stuts.index }].amount" 
								         value="${poVal.amount }" style="width:80px;text-align: right;" />
								  </td>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                       <tfoot>
                          <tr style="display: none;">
                              <td colspan="11" style="text-align: left;">
                                         合计退款金额
                              </td>
                              <td  style="text-align: right;font-weight:bold;" id="totalamount">
                                 ${stockinPage.amount }
                              </td>
                          </tr>
                       </tfoot>
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
                      	<input value=" 提 交 " type="button" onClick="dosubmit();"  />
                    </td>
                  </tr>
              </table>
		    </div>	
 		 </form>
        </div>
</body>
</html>