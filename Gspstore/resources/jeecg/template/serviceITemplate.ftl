package ${bussiPackage}.service.${entityPackage};

import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
import java.util.List;

<#include "/include/serviceInclude.ftl">

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

}
