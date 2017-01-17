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
		//$.fn.TableLock({table:'stockBackDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:907});
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
			url : 'stockBackAction!editMain.action',
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
                    <td colspan="8"  align="center" style="font-size:14px;"><strong>购进退货单</strong></td>
                    <input type="hidden" name="stockbackno" value="${stockBackPage.stockbackno }"/>
                  </tr>
					<tr>
                   	 	<td align="left">仓库ID</td>
                    	<td align="left">
                    		<input name="depotid" type="text" class="easyui-validatebox" maxlength="20" value="${stockBackPage.depotid }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">供应商</td>
                    	<td align="left">
                    		<input name="supplierid" type="text" class="easyui-validatebox" maxlength="10" value="${stockBackPage.supplierid }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">退货人姓名</td>
                    	<td align="left">
                    		<input name="crtusername" type="text" class="easyui-validatebox" maxlength="30" value="${stockBackPage.crtusername }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">创建日期</td>
                    	<td align="left">
                    		<input name="createdate" type="text" class="easyui-datetimebox" maxlength="" value="${stockBackPage.createdate }" data-options="required:true"/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">修改日期</td>
                    	<td align="left">
                    		<input name="modifydate" type="text" class="easyui-datetimebox" maxlength="" value="${stockBackPage.modifydate }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">序号</td>
                    	<td align="left">
                    		<input name="ordernum" type="text" class="easyui-validatebox" maxlength="" value="${stockBackPage.ordernum }" data-options=""/>
                    	</td>
                   	 	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="meno" type="text" class="easyui-validatebox" maxlength="100" value="${stockBackPage.meno }" data-options=""/>
                    	</td>
                   	 	<td align="left">入库单号</td>
                    	<td align="left">
                    		<input name="stockinno" type="text" class="easyui-validatebox" maxlength="50" value="${stockBackPage.stockinno }" data-options=""/>
                    	</td>
					</tr>
				 </table>
			    </div>


   <div class="tabs">
				
				
				 <div title="购进退货明细">
				  <table>
                  <tr bgcolor="#CCE6D1">
                    <td align="left" ><strong>&nbsp;&nbsp;购进退货明细&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_stockBackDetail" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_stockBackDetail" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="stockBackDetail_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="left" bgcolor="#EEEEEE">购进退货明细主</td>
							  <td align="left" bgcolor="#EEEEEE">品种ID号</td>
							  <td align="left" bgcolor="#EEEEEE">批号</td>
							  <td align="left" bgcolor="#EEEEEE">有效期</td>
							  <td align="left" bgcolor="#EEEEEE">数量</td>
							  <td align="left" bgcolor="#EEEEEE">进价</td>
							  <td align="left" bgcolor="#EEEEEE">金额</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_stockBackDetail_table">
                        <s:if test="stockBackDetailList.size >0">
                        <s:iterator value="stockBackDetailList" var="poVal" status="stuts">
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="stockBackDetailList[${stuts.index }].stockbackno" value="${po.stockbackno }"/></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].stockbackdetailno" maxlength="50" type="text" value="${poVal.stockbackdetailno }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].articleid" maxlength="10" type="text" value="${poVal.articleid }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].serial" maxlength="20" type="text" value="${poVal.serial }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].expiredate" maxlength="" type="text" value="${poVal.expiredate }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].num" maxlength="" type="text" value="${poVal.num }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].price" maxlength="" type="text" value="${poVal.price }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockBackDetailList[${stuts.index }].amount" maxlength="" type="text" value="${poVal.amount }" class="easyui-validatebox" style="width:120px;" ></td>
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
       <tbody id="add_stockBackDetail_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left"><input name="stockBackDetailList[#index#].stockbackdetailno" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].articleid" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].serial" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].expiredate" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].num" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].price" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockBackDetailList[#index#].amount" maxlength="" type="text" style="width:120px;"></td>
           </tr>
      </tbody>
</table>
</body>
</html>