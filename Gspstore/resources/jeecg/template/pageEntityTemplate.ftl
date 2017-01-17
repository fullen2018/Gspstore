package ${bussiPackage}.page.${entityPackage};

import java.math.BigDecimal;
import java.util.Date;
<#include "/include/pageInclude.ftl">
/**   
 * @Title: Page
 * @Description: ${ftl_description}
 * @author zhangdaihao
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class ${entityName}Page  extends BasePage implements java.io.Serializable {
	<#list originalColumns as po>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#list>
	<#list originalColumns as po>
	<#if po.fieldType == 'java.util.Date'>
	/**${po.filedComment} 开始时间 */
	private ${po.fieldType} begin${po.fieldName?cap_first};
	/**${po.filedComment} 结束时间*/
	private ${po.fieldType} end${po.fieldName?cap_first};
	</#if>
	</#list>
	
	<#list originalColumns as po>
	/**
	 *方法: 取得${po.fieldType}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.fieldType}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#list>
	
	<#list originalColumns as po>
	<#if po.fieldType == 'java.util.Date'>
	public ${po.fieldType} getBegin${po.fieldName?cap_first}(){
		return this.begin${po.fieldName?cap_first};
	}
	
	/**
	 *方法: 设置${po.fieldType} 
	 *@param: ${po.fieldType}  ${po.filedComment}开始时间
	 */
	public void setBegin${po.fieldName?cap_first}(${po.fieldType} begin${po.fieldName?cap_first}){
		this.begin${po.fieldName?cap_first} = begin${po.fieldName?cap_first};
	}
	
	public ${po.fieldType} getEnd${po.fieldName?cap_first}(){
		return this.end${po.fieldName?cap_first};
	}
	
	/**
	 *方法: 设置${po.fieldType} 
	 *@param: ${po.fieldType}  ${po.filedComment}结束时间
	 */
	public void setEnd${po.fieldName?cap_first}(${po.fieldType} end${po.fieldName?cap_first}){
		this.end${po.fieldName?cap_first} = end${po.fieldName?cap_first};
	}
	</#if>
	</#list>
}
