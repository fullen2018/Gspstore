package ${bussiPackage}.service.${entityPackage};

<#include "/include/serviceInclude.ftl">

import java.util.List;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
<#list subTab as sub>
import ${bussiPackage}.page.${sub.entityPackage}.${sub.entityName}Page;
import ${bussiPackage}.entity.${sub.entityPackage}.${sub.entityName}Entity;
</#list>

/**   
 * @Title: Service
 * @Description: ${ftl_description}
 * @author zhangdaihao
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
public interface ${entityName}ServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(${entityName}Page ${entityName?uncap_first}Page);

	/**
	 * 添加
	 * 
	 * @param ${entityName?uncap_first}Page
	 */
	public void add(${entityName}Page ${entityName?uncap_first}Page);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(${entityName}Page ${entityName?uncap_first}Page,<#list subTab as sub>List<${sub.entityName}Page> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(${entityName}Page ${entityName?uncap_first}Page,<#list subTab as sub>List<${sub.entityName}Page> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param ${entityName?uncap_first}Page
	 */
	public void update(${entityName}Page ${entityName?uncap_first}Page) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param ${entityName}
	 * @return
	 */
	public ${entityName}Entity get(${entityName}Page ${entityName?uncap_first}Page);
	
	
	/**
	 * 获得
	 * 
	 * @param ${jeecg_table_id}
	 * @return
	 */
	public ${entityName}Entity get(${primary_key_type} ${jeecg_table_id});
	
	/**
	 * 获取所有数据
	 */
	public List<${entityName}Entity> listAll(${entityName}Page ${entityName?uncap_first}Page);
	
	<#list subTab as sub>
	/**根据主表Key,查询子表明细：${sub.ftlDescription}*/
	public List<${sub.entityName}Page> get${sub.entityName}ListByFkey(<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${primary_key_type} ${jeecg_table_id}<#else>String ${key?uncap_first}</#if><#if key_has_next>,</#if></#list>);
	</#list>

}
