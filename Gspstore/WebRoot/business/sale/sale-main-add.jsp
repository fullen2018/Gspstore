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
	   width:610px;
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
		//$.fn.TableLock({table:'saleDetail_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	
	$(".tabs").tabs({width:1000});
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	 //销售单明细追加
	 $("#button_add_saleDetail").bind("click", function(){
	 	  var tr =  $("#add_saleDetail_table_template tr").clone();
	  	  var keyid = sy.UUID();
	  	     tr.attr("id",keyid);
	 	     $("#add_saleDetail_table").append(tr);
	 	  var articlename = tr.find("select[name*='.articlename']").eq(0);
	 	      articlename.attr("class","easyui-combobox");
	 	  var saleprice = tr.find("input[name*='.saleprice']").eq(0);
	 	     saleprice.attr("class","easyui-numberbox");
	 	  var num = tr.find("input[name*='.num']").eq(0);
	 	     num.attr("class","easyui-numberbox");
	 	  //var amount = tr.find("input[name*='.amount']").eq(0);
	 	     //amount.attr("class","easyui-numberbox");
		  $.parser.parse("#"+keyid);
	 	  resetTrNumnew('add_saleDetail_table');
	 });
	 //销售单明细删除
	 $("#button_del_saleDetail").bind("click", function(){
	  	$("#add_saleDetail_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNumnew('add_saleDetail_table');
	 });
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : 'saleAction!addMain.action',
			success : function(data) {
				var json = $.parseJSON(data);
				$.messager.progress('close');
				 window.parent.window.closePayoutDialog();
				if (json && json.success) {
					//父窗口刷新datagrid,提示操作消息
					window.parent.window.datagrid.datagrid('reload');
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
		$tbody = $("#add_saleDetail_table");
		var stockkeyinfo = "";
		var flag = true;
		var rows = $tbody.find('>tr');
		for (var i=0;i<rows.length;i++){
		    var corrent = $("input[name*='.stockkey']", rows[i]).val();
		    var num = $("input[id*='.num']", rows[i]).numberbox("getValue");
		    var saleprice = $("input[id*='.saleprice']", rows[i]).numberbox("getValue");
		    totalamount += parseInt(num,10)*parseFloat(saleprice,10);
		    var articlename = $("select[id*='.articlename']", rows[i]).combobox("getText");
		    if (stockkeyinfo.indexOf(corrent)!=-1){
		        var againinf = "第"+(i+1)+"行["+articlename+"]与前面的存在重复!";
			    $.messager.alert('提示', againinf, 'error');
			    return false;
			}else{
			    stockkeyinfo = stockkeyinfo + "," + corrent;
			}
		}
		return flag;
	}
	
	function computeAmount(obj){
	   var num = $(obj).parent().prev().find("input[id*='.num']").eq(0).numberbox("getValue");;
	   var saleprice = $(obj).parent().prev().prev().find("input[id*='.saleprice']").eq(0).numberbox("getValue");
	   if (num!=null&&num!=""&&saleprice!=null&&saleprice!="")
	      $(obj).val(parseInt(num,10)*parseFloat(saleprice,10));
	}
	
	function findLastInprice(obj){
	  var articleid = $(obj).parent().parent().find("input[name*='.articleid']").eq(0).val();
	  var salepriceinput  = $(obj).parent().parent().find("input[id*='.saleprice']").eq(0);
	  var customerid = $("#customerid").combobox("getValue");
	  if (customerid==null||customerid==""){
	     $.messager.alert('提示', "请先选择客户！", 'warn');
	     return;
	  }
	  if (articleid==null||articleid==""){
	     $.messager.alert('提示', "请先选择本行的品种！", 'warn');
	     return;
	  }
      $.ajax({
			url : 'customerPriceAction!getCustomerPrice.action',
			data : {
				articleid : articleid,
				customerid : customerid
			},
			dataType : 'json',
			cache : false,
			async:false,
			success : function(response) {
				if(response.flag==1){
					$.messager.alert('提示', "该客户上次购买["+response.articlename+"]<br>价格是：["+response.saleprice+"]元！", 'info');
					salepriceinput.numberbox('setValue',response.saleprice);
				}else{
				    $.messager.alert('提示', "该客户还没有购买过此品种！", 'info');
				}
			}
		});
	}
</script>
</head>
<body>
        <div id="chk_idAll">
            <form name="myForm" id="myForm" method="post">
             <div>
              <table width="998px">
                  <tr bgcolor="#CCE6D1">
                    <td colspan="10"  align="center" style="font-size:14px;"><strong>销售单</strong></td>
                  </tr>
					<tr>
                   	 	<td align="left">销售客户</td>
                    	<td align="left">
                    	    <select id="customerid" name="customerid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'customerAction!combox.action',
								      	 valueField:'customerid',
								         textField:'customername',
						                editable:false
		 						       "  missingMessage="请填写销售客户"  style="width: 163px;">
		 					</select>
                    	</td>
                   	 	<td align="left">销售日期</td>
                    	<td align="left">
                    		<input name="saledate" type="text" style="width: 135px;" class="easyui-datetimebox" maxlength="" data-options="required:true"/>
                    	</td>
                   	 	<td align="left">业务员</td>
                    	<td align="left">
                    	    <select id="accountid" name="accountid" type="text"  class="easyui-combobox" data-options="required:true,
		 						       url:'accounterAction!combox.action',
								      	 valueField:'accountid',
								         textField:'accountname',
						                editable:false,
						                onSelect: function(rec){ 
						                   $('#salerate').val(rec.rate);
						                }
		 						       "  missingMessage="请填写业务员"  style="width: 100px;">
		 					</select>
                    	</td>
                    	<td align="left">提成比例</td>
                    	<td align="left">
                    		<input id="salerate" readonly="readonly" style="width: 70px;" name="salerate" type="text" class="easyui-validatebox" maxlength="" data-options="required:true"/>
                    	</td>
                    	<td align="left">备注</td>
                    	<td align="left">
                    		<input style="width: 230px;" name="meno" type="text" class="easyui-validatebox" maxlength="100" data-options=""/>
                    	</td>
					</tr>
					<tr>
                   	 	
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
                    <td  align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="saleDetail_table">
                        <tr bgcolor="#E6E6E6">
                          <td align="center" bgcolor="#EEEEEE">序号</td>
							  <td align="center" bgcolor="#EEEEEE">品种</td>
							  <td align="center" bgcolor="#EEEEEE">厂家</td>
							  <td align="center" bgcolor="#EEEEEE">规格</td>
							  <td align="center" bgcolor="#EEEEEE">单位</td>
							  <td align="center" bgcolor="#EEEEEE">批号</td>
							  <td align="center" bgcolor="#EEEEEE">有效期</td>
							  <td align="center" bgcolor="#EEEEEE">统一零售价</td>
							   <td align="center" bgcolor="#EEEEEE">仓库</td>
							  <td align="center" bgcolor="#EEEEEE">剩余库存</td>
							  <td align="center" bgcolor="#EEEEEE">销售价</td>
							  <td align="center" bgcolor="#EEEEEE">销售数量</td>
							  <td align="center" bgcolor="#EEEEEE">销售金额</td>
							  <td align="center" bgcolor="#EEEEEE">防伪号</td>
							  <td width=16px;></td>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_saleDetail_table">
	                        <tr>
	                            <td align="center">
	                                <input type="checkbox" name="ck"/>
	                                <input type="hidden" name="saleDetailList[0].stockkey" />
	                                <input type="hidden" name="saleDetailList[0].articleid" />
	                            </td>
								<td align="left">
								  <select id="stockinDetailList[0].articlename" name="stockinDetailList[0].articlename" type="text"  class="easyui-combobox" data-options="required:true,
		 						        url:'saleAction!saleStockCombox.action',
								      	valueField:'stockkey',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:650,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   var factoryname = row.factory;
										   if (row.factory.length>7) factoryname = row.factory.substring(0,7);
										   var articlespec = row.articlespec;
										   if (row.articlespec.length>12) articlespec = row.articlespec.substring(0,12);
										   return '<table  class=comboxstock><tr><td >'+row.articlename+'</td><td width=110px;>'+row.articlespec+'</td><td width=100px;>'+row.factory+'</td><td width=50px;>'+row.num+'('+row.unitname+')</td><td width=80px>'+row.serial+'</td><td width=70px;>'+row.expiredate.substr(0,10)+'</td></tr></table>';
										},
										onSelect: function(rec){ 
										     var stockkey = $(this).parent().prev().find('input[name*=\'stockkey\']').eq(0).val(rec.stockkey);
										     var articleid = $(this).parent().prev().find('input[name*=\'articleid\']').eq(0).val(rec.articleid);
										     var factory = $(this).parent().next().children('input').eq(0).val(rec.factory);
										     var articlespec = $(this).parent().next().next().children('input').eq(0).val(rec.articlespec);
										     var unitname = $(this).parent().next().next().next().children('input').eq(0).val(rec.unitname);
										     var serial = $(this).parent().next().next().next().next().children('input').eq(0).val(rec.serial);
										     var expiredate = $(this).parent().next().next().next().next().next().children('input').eq(0).val(rec.expiredate);
										     var retailprice = $(this).parent().next().next().next().next().next().next().children('input').eq(0).val(rec.retailprice);
										     var depotname = $(this).parent().next().next().next().next().next().next().next().children('input').eq(0).val(rec.depotname);
										     var num = $(this).parent().next().next().next().next().next().next().next().next().children('input').eq(0).val(rec.num);
										     //var saleprice = $(this).parent().next().next().next().next().next().next().next().next().next().children('input').eq(0).val(rec.pfprice);
										     var salenum = $(this).parent().next().next().next().next().next().next().next().next().next().next().children('input').eq(0).numberbox('options').max = rec.num;
										     //var salenum = $(this).parent().next().next().next().next().next().next().next().children('input').eq(0).focus();
										     //alert($(this).parent().prev().find('input[name*=\'stockkey\']').eq(0).val());
										}
		 						       "  missingMessage="请填写品种"  style="width:165px;" >
		 					       </select>
								</td>
								<td align="left">
								  <input name="factory" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:70px;" >
								</td>
								<td align="left">
								  <input name="articlespec" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input name="unitname" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:30px;" >
								</td>
								<td align="left">
								  <input name="serial" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:70px;" >
								</td>
								<td align="left">
								  <input name="expiredate" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:65px;" >
								</td>
								<td align="left">
								  <input name="retailprice" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:60px;" >
								</td>
								<td align="left">
								  <input name="depotname" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input name="stocknum" readonly="readonly" maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input id="saleDetailList[0].saleprice" name="saleDetailList[0].saleprice"  maxlength="" type="text" data-options="required:true,precision:2" class="easyui-numberbox" style="width:50px;" >
								</td>
								<td align="left">
								   <input id="saleDetailList[0].num" name="saleDetailList[0].num" maxlength="" type="text" class="easyui-numberbox" data-options="required:true,precision:0,min:0"  style="width:50px;" >
								</td>
								<td align="left">
								   <input name="saleDetailList[0].amount" onclick="computeAmount(this)" readonly="readonly" maxlength="" type="text"  data-options="precision:2" style="width:50px;" >
								</td>
								<td align="left">
								   <input name="saleDetailList[0].secretno" maxlength="50" type="text" class="easyui-validatebox" data-options="required:true" style="width:50px;" >
								</td>
								<td align="left">
								   <span class="calendar-menu-next" onclick="findLastInprice(this)" style="width:16px;" ></span>
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
				<table width="100%;">
                  <tr>
                    <td height="50" colspan="8"  align="center">
                        <input value=" 关 闭 " type="button" id="doReturn"/>
                      	<input value=" 提 交 " type="button" onClick="dosubmit();"  />
                    </td>
                  </tr>
                  <tr>
                     <td style="font-size:12px;color: blue;text-align:left;">
                       注:行中的黑色箭头作用是根据当前的客户和当前行的品种查找客户最近一次的销售价格;
                     </td>
                  </tr>
              </table>
		    </div>	
 		 </form>
        </div>

<table style="display:none">
       <tbody id="add_saleDetail_table_template">
          <tr>
	                            <td align="center">
	                                <input type="checkbox" name="ck"/>
	                                <input type="hidden" name="saleDetailList[#index#].stockkey" />
                                    <input type="hidden" name="saleDetailList[0].articleid" />
	                            </td>
								<td align="left">
								  <select id="stockinDetailList[#index#].articlename" name="stockinDetailList[#index#].articlename" type="text"   data-options="required:true,
		 						         url:'saleAction!saleStockCombox.action',
								      	valueField:'stockkey',
								        textField:'articlename',
						                panelHeight:90,
						                panelWidth:600,
						                filter: function(q, row){
											var opts = $(this).combobox('options');
											return row[opts.textField].indexOf(q)!=-1||row['articlealias'].indexOf(q)!=-1;
										},
										formatter:function(row){
										   return '<table  class=comboxstock><tr><td >'+row.articlename+'</td><td width=60px;>'+row.articlespec+'</td><td width=90px;>'+row.factory+'</td><td width=50px;>'+row.num+'('+row.unitname+')</td><td width=80px>'+row.serial+'</td><td width=70px;>'+row.expiredate.substr(0,10)+'</td></tr></table>';
										},
										onSelect: function(rec){ 
										     var stockkey = $(this).parent().prev().find('input[name*=\'stockkey\']').eq(0).val(rec.stockkey);
                                             var articleid = $(this).parent().prev().find('input[name*=\'articleid\']').eq(0).val(rec.articleid);
										     var factory = $(this).parent().next().children('input').eq(0).val(rec.factory);
										     var articlespec = $(this).parent().next().next().children('input').eq(0).val(rec.articlespec);
										     var unitname = $(this).parent().next().next().next().children('input').eq(0).val(rec.unitname);
										     var serial = $(this).parent().next().next().next().next().children('input').eq(0).val(rec.serial);
										     var expiredate = $(this).parent().next().next().next().next().next().children('input').eq(0).val(rec.expiredate);
										     var retailprice = $(this).parent().next().next().next().next().next().next().children('input').eq(0).val(rec.retailprice);
										     var depotname = $(this).parent().next().next().next().next().next().next().next().children('input').eq(0).val(rec.depotname);
										     var num = $(this).parent().next().next().next().next().next().next().next().next().children('input').eq(0).val(rec.num);
										     //var saleprice = $(this).parent().next().next().next().next().next().next().next().next().next().children('input').eq(0).val(rec.pfprice);
										     var salenum = $(this).parent().next().next().next().next().next().next().next().next().next().next().children('input').eq(0).numberbox('options').max = rec.num;
										     //var salenum = $(this).parent().next().next().next().next().next().next().next().children('input').eq(0).focus();
										     //alert($(this).parent().prev().find('input[name*=\'stockkey\']').eq(0).val());
										}
		 						       "  missingMessage="请填写品种"  style="width:165px;" >
		 					       </select>
								</td>
								<td align="left">
								  <input name="factory" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:70px;" >
								</td>
								<td align="left">
								  <input name="articlespec" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input name="unitname" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:30px;" >
								</td>
								<td align="left">
								  <input name="serial" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:70px;" >
								</td>
								<td align="left">
								  <input name="expiredate" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:65px;" >
								</td>
								<td align="left">
								  <input name="retailprice" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:60px;" >
								</td>
								<td align="left">
								  <input name="depotname" readonly="readonly"  maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input name="stocknum" readonly="readonly" maxlength="20" type="text" class="easyui-validatebox" style="width:50px;" >
								</td>
								<td align="left">
								  <input id="saleDetailList[#index#].saleprice" name="saleDetailList[#index#].saleprice"  maxlength="" type="text" data-options="required:true,precision:2"  style="width:50px;" >
								</td>
								<td align="left">
								   <input id="saleDetailList[#index#].num" name="saleDetailList[#index#].num" maxlength="" type="text"  data-options="required:true,precision:0,min:0"  style="width:50px;" >
								</td>
								<td align="left">
								   <input name="saleDetailList[#index#].amount" onclick="computeAmount(this)" readonly="readonly" maxlength="" type="text"  data-options="precision:2" style="width:50px;" >
								</td>
								<td align="left">
								   <input name="saleDetailList[#index#].secretno" maxlength="50" type="text" class="easyui-validatebox" data-options="required:true" style="width:50px;" >
								</td>
                                <td>
								   <span class="calendar-menu-next" onclick="findLastInprice(this)" style="width:16px;"></span>
								</td>
	                        </tr>
      </tbody>
</table>
</body>
</html>