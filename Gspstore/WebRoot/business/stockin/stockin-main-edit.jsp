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
<script type="text/javascript" charset="utf-8">
var myForm;
var article_combox_data = ${article_combox_data };
$(document).ready(function() {
		//$.fn.TableLock({table:'stockinDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:885});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //入库明细表追加
	 $("#button_add_stockinDetail").bind("click", function(){
	  	  var tr =  $("#add_stockinDetail_table_template tr").clone();
	  	  var keyid = sy.UUID();
	  	     tr.attr("id",keyid);
	 	     $("#add_stockinDetail_table").append(tr);
	 	  var articleid = tr.find("select[name*='articleid']").eq(0);
	 	      articleid.attr("class","easyui-combobox");
	 	  var expiredate = tr.find("input[name*='expiredate']").eq(0);
	 	     expiredate.attr("class","easyui-datebox");
	 	  var num = tr.find("input[name*='num']").eq(0);
	 	     num.attr("class","easyui-numberbox");
	 	  var price = tr.find("input[name*='price']").eq(0);
	 	     price.attr("class","easyui-numberbox");
	 	  var amount = tr.find("input[name*='amount']").eq(0);
	 	     amount.attr("class","easyui-numberbox");
		  $.parser.parse("#"+keyid);
	 	  resetTrNumnew('add_stockinDetail_table');
	 });
	 
	 //入库明细表删除
	 $("#button_del_stockinDetail").bind("click", function(){
	  	$("#add_stockinDetail_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNumnew('add_stockinDetail_table');
	 });
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'stockinAction!editMain.action',
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
		
	$("#supplierid").combobox("setValue","${stockinPage.supplierid }");
	$("#depotid").combobox("setValue","${stockinPage.depotid }");
	initSelectValue("add_stockinDetail_table");
	$("input").attr("disabled",true);
	$("#doReturn").attr("disabled",false);
});

//初始化下标
	function initSelectValue(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$('select', this).each(function(){
				var $this = $(this), defval = $this.attr('defaultvalue');
				if(defval!=null){
					$this.combobox("setValue",defval);
				}
			});
		});
	}
//初始化下标等待研究
	function initSelectValue2(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
		    var trKey = this.id;
		    var defval = $('#'+trKey+'_article').attr("defaultvalue");
		    $('#'+trKey+'_article').combobox({   
			     url:'articleAction!combox.action',
				 valueField:'articleid',
				 textField:'articlename',
				 panelHeight:70,
				 editable:false 
			}); 
			$('#'+trKey+'_article').combobox("setValue",defval);
			
		});
	}
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
    function resetTrNumnew(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				var comboname = $this.attr('comboname');
				var numberboxname = $this.attr('numberboxname');
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
				if(comboname!=null){
					if (comboname.indexOf("#index#") >= 0){
						$this.attr("comboname",comboname.replace('#index#',i));
					}else{
						var s = comboname.indexOf("[");
						var e = comboname.indexOf("]");
						var new_comboname = comboname.substring(s+1,e);
						$this.attr("comboname",comboname.replace(new_comboname,i));
					}
				}
				if(numberboxname!=null){
					if (numberboxname.indexOf("#index#") >= 0){
						$this.attr("numberboxname",numberboxname.replace('#index#',i));
					}else{
						var s = numberboxname.indexOf("[");
						var e = numberboxname.indexOf("]");
						var new_numberboxname = numberboxname.substring(s+1,e);
						$this.attr("numberboxname",numberboxname.replace(new_numberboxname,i));
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
<body >
        <div id="chk_idAll" >
            <form name="myForm" id="myForm" method="post">
              <div >
                <table width="885px;">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="8"  align="center" style="font-size:14px;"><strong>采购入库单详情</strong></td>
                    <input type="hidden" name="stockinbillno" value="${stockinPage.stockinbillno }"/>
                  </tr>
					<tr>
					    <td align="left">供应商</td>
                    	<td align="left">
                    	     <select id="supplierid" name="supplierid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'supplierAction!combox.action',
								      	 valueField:'supplierid',
								         textField:'suppliername',
						                editable:false
		 						       "  missingMessage="请填写供应商"  style="width: 159px;">
		 					 </select>
                    	</td>
                    	<td align="left">仓库</td>
                    	<td align="left">
                    	    <select id="depotid" name="depotid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'depotAction!combox.action',
								      	 valueField:'depotid',
								         textField:'depotname',
						                editable:false
		 						       "  missingMessage="请填写供仓库"  style="width: 159px;">
		 					</select>
                    	</td>
                   	 	<td align="left">入库日期</td>
                    	<td align="left">
                    		<input name="stockindate" type="text" class="easyui-datetimebox" maxlength="" value="${stockinPage.stockindate }" data-options="required:true" style="width:150px;"/>
                    	</td>
					</tr>
					
				 </table>
			    </div>


   <div class="tabs">
				
				
				 <div title="采购入库单明细">
				  <table>
                  <tr bgcolor="#CCE6D1" style="display:none;">
                    <td align="left" ><strong>&nbsp;&nbsp;采购入库单明细&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_stockinDetail" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_stockinDetail" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="stockinDetail_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="left" bgcolor="#EEEEEE">品种</td>
							  <td align="left" bgcolor="#EEEEEE">批号</td>
							  <td align="left" bgcolor="#EEEEEE">有效期</td>
							  <td align="left" bgcolor="#EEEEEE">数量</td>
							  <td align="left" bgcolor="#EEEEEE">进价</td>
							  <td align="left" bgcolor="#EEEEEE">金额</td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_stockinDetail_table">
                        <s:if test="stockinDetailList.size >0">
                        <s:iterator value="stockinDetailList" var="poVal" status="stuts">
	                        <tr id="${poVal.stockindetailno }">
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="stockinDetailList[${stuts.index }].stockinbillno" value="${po.stockinbillno }"/></td>
								  <td align="left">
								    <select id="${poVal.stockindetailno }_article" defaultvalue="${poVal.articleid }" name="stockinDetailList[${stuts.index }].articleid"  class="easyui-combobox"  data-options="required:true,
		 						        //url:'articleAction!combox.action',
		 						        data:article_combox_data,
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:200,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return row.articlename+'&nbsp;/&nbsp;'+row.articlespec+'&nbsp;/&nbsp;'+row.factory;
										}
		 						       "   missingMessage="请填写品种"  style="width:200px;">
		 						     </select>
								  </td>
								  <td align="left"><input name="stockinDetailList[${stuts.index }].serial" maxlength="20" data-options="required:true" type="text" type="text" value="${poVal.serial }"  class="easyui-validatebox" style="width:120px;" ></td>
								  <td align="left">
								  <input name="stockinDetailList[${stuts.index }].expiredate" 
								  maxlength="" type="text" value="${fn:substring(poVal.expiredate,0,10) }" data-options="required:true"
								  class="easyui-datebox" style="width:120px;" ></td>
								  <td align="left"><input name="stockinDetailList[${stuts.index }].num" maxlength="" type="text" value="${poVal.num }" data-options="required:true,precision:0" type="text" class="easyui-numberbox" style="width:120px;" ></td>
								  <td align="left"><input name="stockinDetailList[${stuts.index }].price" maxlength="" data-options="required:true,precision:2" type="text" value="${poVal.price }" class="easyui-numberbox" style="width:120px;" ></td>
								  <td align="left">
								  <input name="stockinDetailList[${stuts.index }].amount" maxlength="" data-options="required:true,precision:2,groupSeparator:','" type="text" value="${poVal.amount }" class="easyui-numberbox" style="width:120px;" ></td>
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
                      	<input style="display: none" value=" 提 交 " type="button" onClick="dosubmit();"  />
                    </td>
                  </tr>
                 </table>
		    </div>
            </form>
        </div>
       

<table style="display:none">
       <tbody id="add_stockinDetail_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left">
				       <select  name="stockinDetailList[#index#].articleid"    data-options="required:true,
		 						        //url:'articleAction!combox.action',
		 						        data:article_combox_data,
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:200,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return row.articlename+'&nbsp;/&nbsp;'+row.articlespec+'&nbsp;/&nbsp;'+row.factory;
										}
		 				 "   missingMessage="请填写品种"  style="width:200px;">
		 			</select>
				  </td>
				  <td align="left"><input name="stockinDetailList[#index#].serial" maxlength="20" data-options="required:true" class="easyui-validatebox" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].expiredate" maxlength="" data-options="required:true" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].num" maxlength="" data-options="required:true,precision:0" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].price" maxlength="" data-options="required:true,precision:2,groupSeparator:','" type="text" style="width:120px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].amount" maxlength="" data-options="required:true,precision:2,groupSeparator:','" type="text" style="width:120px;"></td>
           </tr>
      </tbody>
</table>
</body>
</html>