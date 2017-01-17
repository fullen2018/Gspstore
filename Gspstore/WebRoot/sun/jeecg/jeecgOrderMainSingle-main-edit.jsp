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
		$.fn.TableLock({table:'jeecgOrderCustomSingle_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$.fn.TableLock({table:'jeecgOrderProductSingle_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //订单客户明细追加
	 $("#button_add_jeecgOrderCustomSingle").bind("click", function(){
	  	  var tr =  $("#add_jeecgOrderCustomSingle_table_template tr").clone();
	 	 $("#add_jeecgOrderCustomSingle_table").append(tr);
	 	  resetTrNum('add_jeecgOrderCustomSingle_table');
	 });
	 //订单客户明细删除
	 $("#button_del_jeecgOrderCustomSingle").bind("click", function(){
	  	$("#add_jeecgOrderCustomSingle_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNum('add_jeecgOrderCustomSingle_table');
	 });
	 //订单产品明细追加
	 $("#button_add_jeecgOrderProductSingle").bind("click", function(){
	  	  var tr =  $("#add_jeecgOrderProductSingle_table_template tr").clone();
	 	 $("#add_jeecgOrderProductSingle_table").append(tr);
	 	  resetTrNum('add_jeecgOrderProductSingle_table');
	 });
	 //订单产品明细删除
	 $("#button_del_jeecgOrderProductSingle").bind("click", function(){
	  	$("#add_jeecgOrderProductSingle_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNum('add_jeecgOrderProductSingle_table');
	 });
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'jeecgOrderMainSingleAction!editMain.action',
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
<table cellpadding="0" cellspacing="0" id="TableBody">
  <tbody>
    <tr>
      <td><div align="center"></div>
        <div align="left"></div>
        <div id="chk_idAll">
          <FIELDSET style="BORDER:#82913C 1px solid; BACKGROUND-COLOR:#F4F9F5;padding:5px; margin:0 5px 5px 5px;">
            <LEGEND class="f14px">订单主数据</LEGEND>
            <form name="myForm" id="myForm" method="post">
              <div>
              </div>
              <table border="0" cellpadding="1" cellspacing="0"  style="border-collapse:collapse;margin-left:10px;margin-top:10px; border:1px solid #999;">
                <tbody>
                  <tr bgcolor="#CCE6D1">
                    <td height="30" colspan="8"  align="center" style="font-size:14px;"><strong>订单主数据</strong></td>
                    <input type="hidden" name="obid" value="${jeecgOrderMainSinglePage.obid }"/>
                  </tr>
					<tr>
                   	 	<td align="left">订单号</td>
                    	<td align="left">
                    	<input name="goOrderCode" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.goOrderCode }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">订单类型 0</td>
                    	<td align="left">
                    	<input name="goderType" type="text" class="easyui-validatebox" maxlength="2" value="${jeecgOrderMainSinglePage.goderType }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">订单团购状态</td>
                    	<td align="left">
                    	<input name="goStatus" type="text" class="easyui-validatebox" maxlength="2" value="${jeecgOrderMainSinglePage.goStatus }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">旅行社ID</td>
                    	<td align="left">
                    	<input name="touristid" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.touristid }" data-options="required:true"/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">旅行社门店I</td>
                    	<td align="left">
                    	<input name="agencyId" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.agencyId }" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">旅行社名称</td>
                    	<td align="left">
                    	<input name="agencyName" type="text" class="easyui-validatebox" maxlength="50" value="${jeecgOrderMainSinglePage.agencyName }" data-options=""/>
                    	</td>
                   	 	<td align="left">联系人ID</td>
                    	<td align="left">
                    	<input name="linkmenid" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.linkmenid }" data-options=""/>
                    	</td>
                   	 	<td align="left">顾客类型 :</td>
                    	<td align="left">
                    	<input name="usertype" type="text" class="easyui-validatebox" maxlength="1" value="${jeecgOrderMainSinglePage.usertype }" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">客户</td>
                    	<td align="left">
                    	<input name="goCusName" type="text" class="easyui-validatebox" maxlength="50" value="${jeecgOrderMainSinglePage.goCusName }" data-options=""/>
                    	</td>
                   	 	<td align="left">销售人</td>
                    	<td align="left">
                    	<input name="goSaleName" type="text" class="easyui-validatebox" maxlength="100" value="${jeecgOrderMainSinglePage.goSaleName }" data-options=""/>
                    	</td>
                   	 	<td align="left">销售人ID</td>
                    	<td align="left">
                    	<input name="goSaleId" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.goSaleId }" data-options=""/>
                    	</td>
                   	 	<td align="left">联系人</td>
                    	<td align="left">
                    	<input name="goContactName" type="text" class="easyui-validatebox" maxlength="50" value="${jeecgOrderMainSinglePage.goContactName }" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">区号</td>
                    	<td align="left">
                    	<input name="goZoneNo" type="text" class="easyui-validatebox" maxlength="6" value="${jeecgOrderMainSinglePage.goZoneNo }" data-options=""/>
                    	</td>
                   	 	<td align="left">电话</td>
                    	<td align="left">
                    	<input name="goPhone" type="text" class="easyui-validatebox" maxlength="20" value="${jeecgOrderMainSinglePage.goPhone }" data-options=""/>
                    	</td>
                   	 	<td align="left">手机</td>
                    	<td align="left">
                    	<input name="goTelphone" type="text" class="easyui-validatebox" maxlength="20" value="${jeecgOrderMainSinglePage.goTelphone }" data-options=""/>
                    	</td>
                   	 	<td align="left">传真</td>
                    	<td align="left">
                    	<input name="goFacsimile" type="text" class="easyui-validatebox" maxlength="32" value="${jeecgOrderMainSinglePage.goFacsimile }" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">邮箱</td>
                    	<td align="left">
                    	<input name="goMail" type="text" class="easyui-validatebox" maxlength="32" value="${jeecgOrderMainSinglePage.goMail }" data-options=""/>
                    	</td>
                   	 	<td align="left">订单人数</td>
                    	<td align="left">
                    	<input name="goOrderCount" type="text" class="easyui-numberbox" maxlength="" value="${jeecgOrderMainSinglePage.goOrderCount }" data-options=""/>
                    	</td>
                   	 	<td align="left">总价(不含返</td>
                    	<td align="left">
                    	<input name="goAllPrice" type="text" class="easyui-numberbox" maxlength="" value="${jeecgOrderMainSinglePage.goAllPrice }" data-options=""/>
                    	</td>
                   	 	<td align="left">返款</td>
                    	<td align="left">
                    	<input name="goReturnPrice" type="text" class="easyui-numberbox" maxlength="" value="${jeecgOrderMainSinglePage.goReturnPrice }" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	<td align="left">备注</td>
                    	<td align="left">
                    	<input name="goContent" type="text" class="easyui-validatebox" maxlength="200" value="${jeecgOrderMainSinglePage.goContent }" data-options=""/>
                    	</td>
                   	 	<td align="left">审核状态 0</td>
                    	<td align="left">
                    	<input name="auditorStatus" type="text" class="easyui-validatebox" maxlength="2" value="${jeecgOrderMainSinglePage.auditorStatus }" data-options=""/>
                    	</td>
                   	 	<td align="left">审核人ID</td>
                    	<td align="left">
                    	<input name="auditorObid" type="text" class="easyui-validatebox" maxlength="36" value="${jeecgOrderMainSinglePage.auditorObid }" data-options=""/>
                    	</td>
                   	 	<td align="left">审核人</td>
                    	<td align="left">
                    	<input name="auditorName" type="text" class="easyui-validatebox" maxlength="50" value="${jeecgOrderMainSinglePage.auditorName }" data-options=""/>
                    	</td>
					</tr>
				  

				  <tr>
                    <td align="center" colspan="22" height="1" bgcolor="#D8D8D8"></td>
                  </tr>
                  <tr>
                    <td colspan="8"  align="right" height="3"></td>
                  </tr>
                  <tr>
                    <td colspan="8" align="left" ><strong>&nbsp;&nbsp;订单客户明细&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_jeecgOrderCustomSingle" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_jeecgOrderCustomSingle" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td colspan="8"  align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderCustomSingle_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="left" bgcolor="#EEEEEE">客户英文名</td>
							  <td align="left" bgcolor="#EEEEEE">姓名</td>
							  <td align="left" bgcolor="#EEEEEE">人员类型</td>
							  <td align="left" bgcolor="#EEEEEE">性别</td>
							  <td align="left" bgcolor="#EEEEEE">身份证号</td>
							  <td align="left" bgcolor="#EEEEEE">出生日期</td>
							  <td align="left" bgcolor="#EEEEEE">护照号</td>
							  <td align="left" bgcolor="#EEEEEE">护照有效期</td>
							  <td align="left" bgcolor="#EEEEEE">业务</td>
							  <td align="left" bgcolor="#EEEEEE">同住</td>
							  <td align="left" bgcolor="#EEEEEE">备注</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_jeecgOrderCustomSingle_table">
                        <s:if test="jeecgOrderCustomSingleList.size >0">
                        <s:iterator value="jeecgOrderCustomSingleList" var="poVal" status="stuts">
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="jeecgOrderCustomSingleList[${stuts.index }].obid" value="${po.obid }"/></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocCusNameEn" maxlength="50" type="text" value="${poVal.gocCusNameEn }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocCusName" maxlength="50" type="text" value="${poVal.gocCusName }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocCusType" maxlength="3" type="text" value="${poVal.gocCusType }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocSex" maxlength="2" type="text" value="${poVal.gocSex }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocIdcard" maxlength="32" type="text" value="${poVal.gocIdcard }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocBirthday" maxlength="" type="text" value="${poVal.gocBirthday }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocPassportCode" maxlength="32" type="text" value="${poVal.gocPassportCode }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocPassportEndDate" maxlength="" type="text" value="${poVal.gocPassportEndDate }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocBussContent" maxlength="100" type="text" value="${poVal.gocBussContent }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocRoomNum" maxlength="10" type="text" value="${poVal.gocRoomNum }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderCustomSingleList[${stuts.index }].gocContent" maxlength="200" type="text" value="${poVal.gocContent }" class="easyui-validatebox" style="width:120px;" ></td>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                      </table>
                    </td>
                  </tr>
                  
                  
				  <tr>
                    <td align="center" colspan="22" height="1" bgcolor="#D8D8D8"></td>
                  </tr>
                  <tr>
                    <td colspan="8"  align="right" height="3"></td>
                  </tr>
                  <tr>
                    <td colspan="8" align="left" ><strong>&nbsp;&nbsp;订单产品明细&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_jeecgOrderProductSingle" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_jeecgOrderProductSingle" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td colspan="8"  align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProductSingle_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="left" bgcolor="#EEEEEE">服务项目类型</td>
							  <td align="left" bgcolor="#EEEEEE">产品名称</td>
							  <td align="left" bgcolor="#EEEEEE">个数</td>
							  <td align="left" bgcolor="#EEEEEE">单位</td>
							  <td align="left" bgcolor="#EEEEEE">单价</td>
							  <td align="left" bgcolor="#EEEEEE">小计</td>
							  <td align="left" bgcolor="#EEEEEE">备注</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_jeecgOrderProductSingle_table">
                        <s:if test="jeecgOrderProductSingleList.size >0">
                        <s:iterator value="jeecgOrderProductSingleList" var="poVal" status="stuts">
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="jeecgOrderProductSingleList[${stuts.index }].obid" value="${po.obid }"/></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopProductType" maxlength="3" type="text" value="${poVal.gopProductType }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopProductName" maxlength="100" type="text" value="${poVal.gopProductName }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopCount" maxlength="" type="text" value="${poVal.gopCount }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopUnit" maxlength="2" type="text" value="${poVal.gopUnit }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopOnePrice" maxlength="" type="text" value="${poVal.gopOnePrice }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopSumPrice" maxlength="" type="text" value="${poVal.gopSumPrice }" class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left"><input name="jeecgOrderProductSingleList[${stuts.index }].gopContent" maxlength="200" type="text" value="${poVal.gopContent }" class="easyui-validatebox" style="width:120px;" ></td>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                      </table>
                    </td>
                  </tr>
                  
                  
                  <tr>
                    <td height="50" colspan="8"  align="center">
                        <input value=" 关 闭 " type="button" id="doReturn"/>
                      	<input value=" 提 交 " type="button" onClick="dosubmit();"  />
                    </td>
                  </tr>
                </tbody>
              </table>
            </form>
          </FIELDSET>
        </div>
       </td>
    </tr>
    </tbody>
</table>

<table style="display:none">
       <tbody id="add_jeecgOrderCustomSingle_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocCusNameEn" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocCusName" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocCusType" maxlength="3" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocSex" maxlength="2" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocIdcard" maxlength="32" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocBirthday" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocPassportCode" maxlength="32" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocPassportEndDate" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocBussContent" maxlength="100" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocRoomNum" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderCustomSingleList[#index#].gocContent" maxlength="200" type="text" style="width:120px;"></td>
           </tr>
      </tbody>
       <tbody id="add_jeecgOrderProductSingle_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopProductType" maxlength="3" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopProductName" maxlength="100" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopCount" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopUnit" maxlength="2" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopOnePrice" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopSumPrice" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="jeecgOrderProductSingleList[#index#].gopContent" maxlength="200" type="text" style="width:120px;"></td>
           </tr>
      </tbody>
</table>
</body>
</html>