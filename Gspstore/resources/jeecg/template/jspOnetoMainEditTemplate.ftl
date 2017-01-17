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
	<#list subTab as sub>
	$.fn.TableLock({table:'${sub.entityName?uncap_first}_table',lockRow:0,lockColumn:0,width:'960px',height:'100%'});
	</#list>
	$("#doReturn").click(function(){
		 window.parent.window.closePayoutDialog();
		});
	
	<#list subTab as sub>
	 //${sub.ftlDescription}追加
	 $("#button_add_${sub.entityName?uncap_first}").bind("click", function(){
	  	  var tr =  $("#add_${sub.entityName?uncap_first}_table_template tr").clone();
	 	 $("#add_${sub.entityName?uncap_first}_table").append(tr);
	 	  resetTrNum('add_${sub.entityName?uncap_first}_table');
	 });
	 //${sub.ftlDescription}删除
	 $("#button_del_${sub.entityName?uncap_first}").bind("click", function(){
	  	$("#add_${sub.entityName?uncap_first}_table").find("input:checked").parent().parent().remove(); 
	 	 resetTrNum('add_${sub.entityName?uncap_first}_table');
	 });
	</#list>
	 
	 //提交Form
	 myForm = $('#myForm').form({
			url : '${entityName?uncap_first}Action!editMain.action',
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
            <LEGEND class="f14px">${ftl_description}</LEGEND>
            <form name="myForm" id="myForm" method="post">
              <div>
              </div>
              <table border="0" cellpadding="1" cellspacing="0"  style="border-collapse:collapse;margin-left:10px;margin-top:10px; border:1px solid #999;">
                <tbody>
                  <tr bgcolor="#CCE6D1">
                    <td height="30" colspan="8"  align="center" style="font-size:14px;"><strong>${ftl_description}</strong></td>
                    <input type="hidden" name="${jeecg_table_id}" value="${'$'}{${entityName?uncap_first}Page.${jeecg_table_id} }"/>
                  </tr>
					<#list mainColums as po>
					<#if po_index%4==0>
					<tr>
 					</#if>
                   	 	<td align="left"><#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if></td>
                    	<td align="left">
                    		<input name="${po.fieldName}" type="text" class="${po.classType}" maxlength="${po.charmaxLength}" value="${'$'}{${entityName?uncap_first}Page.${po.fieldName} }" data-options="${po.optionType}"/>
                    	</td>
					<#if (po_index+1)%4==0>
					</tr>
					<#else>
					<#if !po_has_next>
					</tr>
					</#if>
		 			</#if>
					</#list>
				  

				<#list subTab as sub>
				  <tr>
                    <td align="center" colspan="22" height="1" bgcolor="#D8D8D8"></td>
                  </tr>
                  <tr>
                    <td colspan="8"  align="right" height="3"></td>
                  </tr>
                  <tr>
                    <td colspan="8" align="left" ><strong>&nbsp;&nbsp;${sub.ftlDescription}&nbsp;&nbsp;</strong>
                    <input type="button" id="button_add_${sub.entityName?uncap_first}" class=" wd1 btn" value="添加"/>
                    <input type="button" id="button_del_${sub.entityName?uncap_first}" class=" wd1 btn" value="删除"/>
                    </td>
                  </tr>
                 
                   <tr>
                    <td colspan="8"  align="left">
                    <table border="0" cellpadding="2" cellspacing="0" id="${sub.entityName?uncap_first}_table">
                        <tr bgcolor="#E6E6E6">
                          <td height="18" align="center" bgcolor="#EEEEEE">序号</td>
						  <#list sub.subColums as po>
	                          <#assign check = 0 >
							  <#list sub.foreignKeys as key>
							  <#if po.fieldName==key?uncap_first>
							  <#assign check = 1 >
							  <#break>
							  </#if>
							  </#list>
							  <#if check==0>
							  <td align="left" bgcolor="#EEEEEE"><#if po.filedComment?length lt 7 >${po.filedComment}<#else>${po.filedComment[0..6]}</#if></td>
							  </#if>
                          </#list>
                        </tr>
                        <tr>
                          <td align="center" colspan="20" height="1" bgcolor="#D8D8D8"></td>
                        </tr>
                        <tbody id="add_${sub.entityName?uncap_first}_table">
                        <s:if test="${sub.entityName?uncap_first}List.size >0">
                        <s:iterator value="${sub.entityName?uncap_first}List" var="poVal" status="stuts">
	                        <tr>
	                          <td align="center"><input type="checkbox" name="ck"/><input type="hidden" name="${sub.entityName?uncap_first}List[${'$'}{stuts.index }].${jeecg_table_id}" value="${'$'}{po.${jeecg_table_id} }"/></td>
							  <#list sub.subColums as po>
								  <#assign check = 0 >
								  <#list sub.foreignKeys as key>
								  <#if po.fieldName==key?uncap_first>
								  <#assign check = 1 >
								  <#break>
								  </#if>
								  </#list>
								  <#if check==0>
								  <td align="left"><input name="${sub.entityName?uncap_first}List[${'$'}{stuts.index }].${po.fieldName}" maxlength="${po.charmaxLength}" type="text" value="${'$'}{poVal.${po.fieldName} }" class="easyui-validatebox" style="width:120px;" ></td>
								  </#if>
                              </#list>
	                        </tr>
                        </s:iterator>
                      	</s:if>  
                       </tbody>
                      </table>
                    </td>
                  </tr>
                  
                  
			</#list>
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
<#list subTab as sub>
       <tbody id="add_${sub.entityName?uncap_first}_table_template">
          <tr>
             <td align="center"><input type="checkbox" name="ck"/></td>
             <#list sub.subColums as po>
	             <#assign check = 0 >
				  <#list sub.foreignKeys as key>
				  <#if po.fieldName==key?uncap_first>
				  <#assign check = 1 >
				  <#break>
				  </#if>
				  </#list>
				  <#if check==0>
				  <td align="left"><input name="${sub.entityName?uncap_first}List[#index#].${po.fieldName}" maxlength="${po.charmaxLength}" type="text" style="width:120px;"></td>
				  </#if>
          	 </#list>
           </tr>
      </tbody>
</#list>     
</table>
</body>
</html>