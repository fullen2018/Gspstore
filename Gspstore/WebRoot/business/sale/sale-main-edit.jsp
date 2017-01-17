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
		//$.fn.TableLock({table:'saleDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:907});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //销售单明细追加
	 $("#button_add_saleDetail").bind("click", function(){
	  	  var tr =  $("#add_saleDetail_table_template tr").clone();
	 	 $("#add_saleDetail_table").append(tr);
	 	  resetTrNum('add_saleDetail_table');
	 });
	 //销售单明细删除
	 $("#button_del_saleDetail").bind("click", function(){
	  	$("#add_saleDetail_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNum('add_saleDetail_table');
	 });
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'saleAction!editMain.action',
			success : function(data) {
				var json = $.parseJSON(data);
				 window.parent.window.closePayoutDialog();
				if (json && json.success) {
					//父窗口刷新datagrid,提示操作消息
					window.parent.window.datagrid.datagrid('reload');
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
			myForm.submit();
			window.parent.window.closePayoutDialog();
		}
   	}
</script>
</head>
<body>
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
              <div>
                <table>
                  <tr bgcolor="#CCE6D1">
                    <td colspan="8"  align="center" style="font-size:14px;"><strong>销售单</strong></td>
                    <input type="hidden" name="salebillno" value="${salePage.salebillno }"/>
                  </tr>
					<tr>
                   	 	<td align="left">销售人</td>
                    	<td align="left">
                    		<input name="saleman" type="text" class="easyui-validatebox" maxlength="20" value="${salePage.saleman }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">销售客户</td>
                    	<td align="left">
                    		<input name="customerid" type="text" class="easyui-validatebox" maxlength="20" value="${salePage.customerid }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">销售日期</td>
                    	<td align="left">
                    		<input name="saledate" type="text" class="easyui-datetimebox" maxlength="" value="${salePage.saledate }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">业务员</td>
                    	<td align="left">
                    		<input name="accountid" type="text" class="easyui-validatebox" maxlength="20" value="${salePage.accountid }" data-options="required:true"/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">提成比例</td>
                    	<td align="left">
                    		<input name="salerate" type="text" class="easyui-numberbox" maxlength="" value="${salePage.salerate }" data-options="required:true,precision:2,groupSeparator:','"/>
                    	</td>
                   	 	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="meno" type="text" class="easyui-validatebox" maxlength="100" value="${salePage.meno }" data-options=""/>
                    	</td>
                   	 	<td align="left">是否收款</td>
                    	<td align="left">
                    		<input name="isreceive" type="text" class="easyui-validatebox" maxlength="" value="${salePage.isreceive }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">操作人姓名</td>
                    	<td align="left">
                    		<input name="crtusername" type="text" class="easyui-validatebox" maxlength="50" value="${salePage.crtusername }" data-options="required:true"/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">修改日期</td>
                    	<td align="left">
                    		<input name="modifydate" type="text" class="easyui-datetimebox" maxlength="" value="${salePage.modifydate }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">创建日期</td>
                    	<td align="left">
                    		<input name="createdate" type="text" class="easyui-datetimebox" maxlength="" value="${salePage.createdate }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">销售退货单列表</td>
                    	<td align="left">
                    		<input name="salebackbillitems" type="text" class="easyui-validatebox" maxlength="100" value="${salePage.salebackbillitems }" data-options=""/>
                    	</td>
					</tr>
				 </table>
			    </div>


   <div class="tabs">
				
				
				 <div title="销售单明细">
				  <table>
                  <tr bgcolor="#CCE6D1">
                    <td align="left" ><strong>&nbsp;&nbsp;销售单明细&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_saleDetail" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_saleDetail" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="saleDetail_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="left" bgcolor="#EEEEEE">销售明细主键</td>
							  <td align="left" bgcolor="#EEEEEE">品种</td>
							  <td align="left" bgcolor="#EEEEEE">批号</td>
							  <td align="left" bgcolor="#EEEEEE">数量</td>
							  <td align="left" bgcolor="#EEEEEE">销售价</td>
							  <td align="left" bgcolor="#EEEEEE">成本价</td>
							  <td align="left" bgcolor="#EEEEEE">金额</td>
							  <td align="left" bgcolor="#EEEEEE">仓库</td>
							  <td align="left" bgcolor="#EEEEEE">防伪号</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_saleDetail_table">
                        <s:if test="saleDetailList.size >0">
                        <s:iterator value="saleDetailList" var="poVal" status="stuts">
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="saleDetailList[${stuts.index }].salebillno" value="${po.salebillno }"/></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].salebilldetailno" maxlength="20" type="text" value="${poVal.salebilldetailno }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].articleid" maxlength="20" type="text" value="${poVal.articleid }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].serial" maxlength="20" type="text" value="${poVal.serial }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].num" maxlength="" type="text" value="${poVal.num }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].saleprice" maxlength="" type="text" value="${poVal.saleprice }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].price" maxlength="" type="text" value="${poVal.price }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].amount" maxlength="" type="text" value="${poVal.amount }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].depotid" maxlength="20" type="text" value="${poVal.depotid }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="saleDetailList[${stuts.index }].secretno" maxlength="50" type="text" value="${poVal.secretno }" class="easyui-validatebox" style="width:120px;" ></td>
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
       

<table style="display:none">
       <tbody id="add_saleDetail_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left"><input name="saleDetailList[#index#].salebilldetailno" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].articleid" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].serial" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].num" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].saleprice" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].price" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].amount" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].depotid" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="saleDetailList[#index#].secretno" maxlength="50" type="text" style="width:120px;"></td>
           </tr>
      </tbody>
</table>
</body>
</html>