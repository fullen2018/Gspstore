package ${bussiPackage}.service.${entityPackage};

import java.util.List;

import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
<#include "/include/serviceInclude.ftl">

/**   
 * @Title: Service
 * @Description: 会计科目
 * @author zhangdaihao
 * @date 2013-01-20 21:31:48
 * @version V1.0   
 *
 */
public interface ${entityName}ServiceI extends BaseServiceI {

	/**
	 * 获得菜单treegrid
	 * 
	 * @param ${entityName?uncap_first}Page
	 * @return
	 */
	public List<${entityName}Page> treegrid(${entityName}Page ${entityName?uncap_first}Page);

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(${entityName}Page ${entityName?uncap_first}Page, Boolean b);

	/**
	 * 编辑菜单
	 * 
	 * @param ${entityName?uncap_first}Page
	 */
	public void edit(${entityName}Page ${entityName?uncap_first}Page);

	/**
	 * 添加菜单
	 * 
	 * @param ${entityName?uncap_first}Page
	 */
	public void add(${entityName}Page ${entityName?uncap_first}Page);

	/**
	 * 删除菜单
	 * 
	 * @param ${entityName?uncap_first}Page
	 */
	public void delete(${entityName}Page ${entityName?uncap_first}Page);

}
