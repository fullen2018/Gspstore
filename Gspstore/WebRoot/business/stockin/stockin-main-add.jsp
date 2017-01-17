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
    .comboxstock{
	   width:470px;
	   border:0px solid #cfcfcf;
	   border-collapse: collapse;
	   margin:0px;
	   table-layout:fixed;
	}
	.comboxstock td{
	   overflow:hidden;
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
		//$.fn.TableLock({table:'stockinDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	$(".tabs").tabs({width:960});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //入库明细表追加
	 $("#button_add_stockinDetail").bind("click", function(){
	  	   var tr =  $("#add_stockinDetail_table_template tr").clone();
	  	  var keyid = sy.UUID();
	  	     tr.attr("id",keyid);
	 	     $("#add_stockinDetail_table").append(tr);
	 	  var articlename = tr.find("select[name*='articlename']").eq(0);
	 	      articlename.attr("class","easyui-combobox");
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
			url : 'stockinAction!addMain.action',
			success : function(data) {
				var json = $.parseJSON(data);
				$.messager.progress('close');
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
		            $.messager.alert('提示', "总计金额为零，请核查！", 'warn');
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
		$tbody = $("#add_stockinDetail_table");
		var stockkeyinfo = "";
		var flag = true;
		var rows = $tbody.find('>tr');
		for (var i=0;i<rows.length;i++){
		    var articleid = $("input[name*='.articleid']", rows[i]).val();
		    var serial = $("input[name*='.serial']", rows[i]).val();
		    var num = $("input[name*='.num']", rows[i]).val();
		    var price = $("input[name*='.price']", rows[i]).val();
		    totalamount += parseInt(num,10)*parseFloat(price,10);
		    var key = articleid+"|"+serial;
		    if (stockkeyinfo.indexOf(key)!=-1){
		        var articlename = $("select[id*='.articlename']", rows[i]).combobox("getText");
		        var againinf = "第"+(i+1)+"行["+articlename+"]批号["+serial+"]与前面的存在重复!";
			    $.messager.alert('提示', againinf, 'warn');
			    return false;
			}else{
			    stockkeyinfo = stockkeyinfo + "," + key;
			}
		}
		return flag;
	}
	
	function computeAmount(obj){
	   var price = $(obj).parent().prev().find("input[name*='.price']").eq(0).val();
	   var num = $(obj).parent().prev().prev().find("input[name*='.num']").eq(0).val();
	   if (num!=null&&num!=""&&price!=null&&price!="")
	      $(obj).val(parseInt(num,10)*parseFloat(price,10));
	}
</script>
</head>
<body>
        
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
             <div>
              <table width="968px">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="8"   style="font-size:14px;text-align:center;"><strong>采购入库单</strong></td>
                  </tr>
					<tr>
					    <td align="left">供应商</td>
                    	<td align="left">
                    	    <select id="supplierid" name="supplierid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'supplierAction!combox.action',
								      	 valueField:'supplierid',
								         textField:'suppliername',
						                editable:false
		 						       "  missingMessage="请填写供应商"  style="width: 180px;">
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
                    		<input name="stockindate" type="text" class="easyui-datetimebox" maxlength="" data-options="required:true" style="width:150px;"/>
                    	</td>
                   	 	<td align="left">备注</td>
                    	<td align="left">
                    		<input name="meno" type="text" class="easyui-validatebox" maxlength="" data-options="" style="width:285px;"/>
                    	</td>
					</tr>
				   </table>
			</div>
				
				
		<div class="tabs">
				
				
				 <div title="入库明细表">
				  <table >
                  <tr bgcolor="#CCE6D1">
                    <td align="left" ><strong>&nbsp;&nbsp;入库明细表&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_stockinDetail" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_stockinDetail" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td  align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="stockinDetail_table">
                        <tr bgcolor="#E6E6E6">
                              <td align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE">品种</td>
							  <td align="center" bgcolor="#EEEEEE">编码</td>
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
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/></td>
								<td align="left">
								<select id="stockinDetailList[0].articlename" name="stockinDetailList[0].articlename" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:500,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   var factoryname = row.factory;
										   if (row.factory.length>7) factoryname = row.factory.substring(0,7);
										   var articlespec = row.articlespec;
										   if (row.articlespec.length>12) articlespec = row.articlespec.substring(0,12);
										   return '<table  class=comboxstock ><tr><td >'+row.articlename+'</td><td width=110px;>'+articlespec+'</td><td width=100px;>'+factoryname+'</td><td width=40px;>'+row.unitname+'</td></tr></table>';
										},
										onSelect: function(rec){ 
										     var articleid = $(this).parent().next().children('input').eq(0).val(rec.articleid);
										     var spec = $(this).parent().next().next().children('input').eq(0).val(rec.articlespec);
										     var factory = $(this).parent().next().next().next().children('input').eq(0).val(rec.factory);
										     var unitname = $(this).parent().next().next().next().next().children('input').eq(0).val(rec.unitname);
										     //var serial = $(this).parent().next().next().next().next().children('input').eq(0).focus();
										}
		 						       "  missingMessage="请填写品种"  style="width:140px;" >
		 					       </select>
								</td>
								 <td align="left"  >
								   <input type="text" name="stockinDetailList[0].articleid" style="width:80px;" class="easyui-validatebox"/>
								  </td>
								 <td align="left"  >
								   <input type="text" disabled="disabled" style="width:80px;" class="easyui-validatebox"/>
								  </td>
								  <td align="left" >
								    <input type="text" disabled="disabled" style="width:90px;" class="easyui-validatebox"/>
								  </td>
								  <td align="left" >
								    <input type="text" disabled="disabled" style="width:50px;" class="easyui-validatebox"/>
								  </td>
								<td align="left">
									<input name="stockinDetailList[0].serial" maxlength="20" type="text" 
									  class="easyui-validatebox" data-options="required:true" style="width:75px;" >
							    </td>
								<td align="left">
									<input name="stockinDetailList[0].expiredate" maxlength="" type="text" class="easyui-datebox" 
									 data-options="required:true" style="width:90px;" >
								 </td>
								<td align="left"><input name="stockinDetailList[0].num" maxlength="" data-options="required:true,precision:0" type="text" 
								class="easyui-numberbox" style="width:70px;" >
								</td>
								<td align="left">
								<input name="stockinDetailList[0].price" maxlength="" data-options="required:true,precision:2"
								type="text" class="easyui-numberbox" style="width:80px;" >
								</td>
								<td align="left"><input name="stockinDetailList[0].amount" maxlength="" type="text" onclick="computeAmount(this)" readonly="readonly"
								  data-options="precision:2" style="width:80px;" >
								</td>
	                        </tr>
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
       <tbody id="add_stockinDetail_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
				  <td align="left">
				       <select id="stockinDetailList[#index#].articlename" name="stockinDetailList[#index#].articlename"    data-options="required:true,
		 						        //data:article_combox_data,
		 						        url:'articleAction!getArticleForStockIn.action',
								      	valueField:'articleid',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:500,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   var factoryname = row.factory;
										   if (row.factory.length>7) factoryname = row.factory.substring(0,7);
										   var articlespec = row.articlespec;
										   if (row.articlespec.length>12) articlespec = row.articlespec.substring(0,12);
										   return '<table  class=comboxstock ><tr><td >'+row.articlename+'</td><td width=110px;>'+articlespec+'</td><td width=100px;>'+factoryname+'</td><td width=40px;>'+row.unitname+'</td></tr></table>';
										},
										onSelect: function(rec){ 
										     var articleid = $(this).parent().next().children('input').eq(0).val(rec.articleid);
										     var spec = $(this).parent().next().next().children('input').eq(0).val(rec.articlespec);
										     var factory = $(this).parent().next().next().next().children('input').eq(0).val(rec.factory);
										     var unitname = $(this).parent().next().next().next().next().children('input').eq(0).val(rec.unitname);
										     //var serial = $(this).parent().next().next().next().next().children('input').eq(0).focus();
										}
		 				 "   missingMessage="请填写品种"  style="width:140px;">
		 			</select>
				  </td>
				  <td align="left"  >
				   <input type="text" name="stockinDetailList[#index#].articleid" style="width:80px;" class="easyui-validatebox"/>
				  </td>
				 <td align="left"  >
				   <input type="text" disabled="disabled" style="width:80px;" class="easyui-validatebox"/>
				  </td>
				  <td align="left" >
				    <input type="text" disabled="disabled" style="width:90px;" class="easyui-validatebox"/>
				  </td>
				  <td align="left" >
				    <input type="text" disabled="disabled" style="width:50px;" class="easyui-validatebox"/>
				  </td>
				  <td align="left"><input name="stockinDetailList[#index#].serial" maxlength="20" data-options="required:true" class="easyui-validatebox" type="text" style="width:75px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].expiredate" maxlength="" data-options="required:true" type="text" style="width:90px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].num" maxlength="" data-options="required:true,precision:0" type="text" style="width:70px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].price" maxlength="" data-options="required:true,precision:2" type="text" style="width:80px;"></td>
				  <td align="left"><input name="stockinDetailList[#index#].amount" maxlength="" data-options="required:true"  onclick="computeAmount(this)" readonly="readonly" type="text" style="width:80px;"></td>
           </tr>
      </tbody>
</table>
</body>
</html>