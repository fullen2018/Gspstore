package ${bussiPackage}.entity.${entityPackage};

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
<#include "/tree/include/entityTreeHeadInclude.ftl">

/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author zhangdaihao
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Entity
@Table(name = "${tableName}", schema = "")
@SuppressWarnings("serial")
public class ${entityName}Entity implements java.io.Serializable {
	<#list originalColumns as po>
	<#if po.fieldName != 'cpid'>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#if>
	</#list>
	
	<#include "/tree/include/entityTreeFKInclude.ftl">
	<#list originalColumns as po>
	<#if po.fieldName != 'cpid'>
	/**
	 *方法: 取得${po.fieldType}
	 *@return: ${po.fieldType}  ${po.filedComment}
	 */
	<#if po.fieldName == jeecg_table_id>
	@Id
	@Column(name = "${jeecg_table_id}",  nullable = false, length = 36)
    <#else>
	@Column(name ="${po.fieldDbName}")
	</#if>
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
	</#if>
	</#list>
}
