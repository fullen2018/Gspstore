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
<script type="text/javascript" charset="utf-8">
var myForm;
$(document).ready(function() {
		//$.fn.TableLock({table:'saleBackDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	checkStockAgain();
	$(".tabs").tabs({width:950});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'saleBackAction!addMain.action',
			success : function(data) {
				var json = $.parseJSON(data);
				$.messager.progress('close');
				 window.parent.window.closePayoutDialog();
				if (json && json.success) {
					//父窗口刷新datagrid,提示操作消息
					window.parent.window.datagrid.datagrid('reload');
					window.parent.window.datagridSale.datagrid('reload');
					window.parent.window.messageshow('成功',json.msg);
				} else {
				    window.parent.window.messageshow('失败','操作失败!');
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
		            $.messager.alert('提示', "总计退货金额为零，请核查！", 'warn');
	                return;
		          }
		          $.messager.confirm('请确认','总计金额：'+totalamount+'，您确定提交吗？',function(r){   
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
		$tbody = $("#add_saleDetail_table");
		var stockkeyinfo = "";
		var flag = true;
		var rows = $tbody.find('>tr');
		for (var i=0;i<rows.length;i++){
		    var num = $("input[id*='num']", rows[i]).numberbox("getValue");
		    var price = $("input[id*='saleprice']", rows[i]).val();
		    var amount = $("input[id*='amount']", rows[i]).val(parseInt(num,10)*parseFloat(price,10));
		    totalamount += parseInt(num,10)*parseFloat(price,10);
		}
		return flag;
	}
   	function computeAmount(obj){
	   var price = $(obj).parent().parent().find("input[id*='saleprice']").eq(0).val();
	   var num = $(obj).parent().parent().find("input[id*='num']").eq(0).val();
	   if (num!=null&&num!=""&&price!=null&&price!="")
	      $(obj).val(parseInt(num,10)*parseFloat(price,10));
	}
</script>
</head>
<body>
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
             <div>
             <table width="958">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="8"  align="center" style="font-size:14px;"><strong>销售退货单</strong></td>
                    
                    </tr>
					<tr>
                   	 	<td align="left">退货客户</td>
                    	<td align="left">
                    	    <input name="customerid" type="hidden" value="${salePage.customerid }" />
                    		<input  type="text" class="easyui-validatebox" maxlength="20" value="${salePage.customerpage.customername }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">退货日期</td>
                    	<td align="left">
                    		<input name="backdate" type="text" style="width:140px;" class="easyui-datetimebox" maxlength=""  data-options="required:true"/>
                    	</td>
                   	 	<td align="left">业务员</td>
                    	<td align="left">
                    	    <input name="accountid" type="hidden" value="${salePage.accountid }" />
                    		<input  type="text" class="easyui-validatebox" maxlength="20" value="${salePage.accounterpage.accountname }" data-options="required:true"/>
                    	</td>
                    	<td align="left">提成比例</td>
                    	<td align="left">
                    		<input name="salerate" type="text" class="easyui-validatebox" maxlength="" value="${salePage.salerate }" data-options="required:true,precision:2,groupSeparator:','"/>
                    	</td>
					</tr>
					<tr>
					    <td align="left">原销售单号</td>
                    	<td align="left">
                    		<input name="salebillno" type="text" class="easyui-validatebox" maxlength="" value="${salePage.salebillno }" data-options="required:true"/>
                    	</td>
                    	<td align="left">退货金额</td>
                    	<td align="left">
                    		<input name="amount" type="text" class="easyui-validatebox" maxlength="100" value="${salePage.amount }" data-options=""/>
                    	</td>
                    	<td align="left">销售退货单列表</td>
                    	<td align="left">
                    		<input  type="text" class="easyui-validatebox" maxlength="100" value="${salePage.salebackbillitems }" data-options=""/>
                    	</td>
                    	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="meno" type="text" class="easyui-validatebox" maxlength="100" value="${salePage.meno }" data-options=""/>
                    	</td>
					</tr>
				 </table>
			</div>
				
				
		<div class="tabs">
				
				
				 <div title="销售退货单明细">
				  <table>
                   <tr>
                    <td align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="saleDetail_table">
                        <tr bgcolor="#E6E6E6">
                              <td align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE">品种</td>
							  <td align="center" bgcolor="#EEEEEE">编码</td>
							  <td align="center" bgcolor="#EEEEEE">规格</td>
							  <td align="center" bgcolor="#EEEEEE">厂家</td>
							  <td align="center" bgcolor="#EEEEEE">单位</td>
							  <td align="center" bgcolor="#EEEEEE">仓库</td>
							  <td align="center" bgcolor="#EEEEEE">批号</td>
							  <td align="center" bgcolor="#EEEEEE">销售价</td>
							  <td align="center" bgcolor="#EEEEEE">销售数量</td>
							  <td align="center" bgcolor="#EEEEEE">已退货数</td>
							  <td align="center" bgcolor="#EEEEEE">退货数</td>
							  <td align="center" bgcolor="#EEEEEE">退货金额</td>
							  <td align="center" bgcolor="#EEEEEE">防伪号</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_saleDetail_table">
                        <s:if test="saleDetailList.size >0">
                        <s:iterator value="saleDetailList" var="poVal" status="stuts">
	                        <tr>  
	                              <td align="center">${stuts.index+1 }</td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.articlepage.articlename }" class="easyui-validatebox" style="width:100px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.articleid }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.articlepage.articlespec }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.articlepage.factory }" class="easyui-validatebox" style="width:80px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.articlepage.unitdic.paramName }" class="easyui-validatebox" style="width:40px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.depotpage.depotname }" class="easyui-validatebox" style="width:60px;" ></td>
								  <td align="left"><input  maxlength="20" type="text" value="${poVal.serial }" class="easyui-validatebox" style="width:70px;" ></td>
								  <td align="left"><input  maxlength="" id="saleprice" type="text" value="${poVal.saleprice }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left"><input  maxlength="" id= type="text" value="${poVal.num }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left"><input  maxlength="" type="text" value="${poVal.backnum }" class="easyui-validatebox" style="width:50px;" ></td>
								  <td align="left">
	                               <input type="hidden" name="saleBackDetailList[${stuts.index }].salebilldetailno" value="${poVal.salebilldetailno }" />
	                               <input type="hidden" name="saleBackDetailList[${stuts.index }].stockkey" value="${poVal.stockkey }" />
								   <input id="saleBackDetailList[${stuts.index }].num" name="saleBackDetailList[${stuts.index }].num" maxlength="" 
								   type="text" class="easyui-numberbox" value="${poVal.num - poVal.backnum }"
								   data-options="required:true,precision:0,min:0,max:${poVal.num - poVal.backnum }"  
								   style="width:50px;" >
								  </td>
								  <td align="left"><input  maxlength="" type="text" id="amount" value="${poVal.amount }" 
								  readonly="readonly" style="width:60px;" onclick="computeAmount(this)"></td>
								  <td align="left"><input  maxlength="50" type="text" value="${poVal.secretno }" class="easyui-validatebox" style="width:70px;" ></td>
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
                      	<input value=" 提 交 " type="button" onClick="dosubmit();"  />
                    </td>
                  </tr>
              </table>
		    </div>	
 		 </form>
        </div>
</body>
</html>