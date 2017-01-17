package ${bussiPackage}.action.${entityPackage};

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;

import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
import ${bussiPackage}.service.${entityPackage}.${entityName}ServiceI;
<#include "/include/actionInclude.ftl">



/**   
 * @Title: Action
 * @Description: ${ftl_description}
 * @author zhangdaihao
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Action(value = "${entityName?uncap_first}Action", results = { @Result(name = "${entityName?uncap_first}", location = "/${bussiPackage?replace(".","/")}/${entityPackage}/${entityName?uncap_first}.jsp") })
public class ${entityName}Action extends BaseAction implements ModelDriven<${entityName}Page> {

	private static final Logger logger = Logger.getLogger(${entityName}Action.class);

	private ${entityName}ServiceI ${entityName?uncap_first}Service;

	private ${entityName}Page ${entityName?uncap_first}Page = new ${entityName}Page();

	public ${entityName}Page getModel() {
		return ${entityName?uncap_first}Page;
	}

	public ${entityName}ServiceI get${entityName}Service() {
		return ${entityName?uncap_first}Service;
	}

	@Autowired
	public void set${entityName}Service(${entityName}ServiceI ${entityName?uncap_first}Service) {
		this.${entityName?uncap_first}Service = ${entityName?uncap_first}Service;
	}

	/**
	 * 首页获得菜单树
	 */
	public void ctrlTree() {
		writeJson(${entityName?uncap_first}Service.tree(${entityName?uncap_first}Page, false));
	}

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @return
	 */
	public String go${entityName}() {
		return "${entityName?uncap_first}";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		writeJson(${entityName?uncap_first}Service.treegrid(${entityName?uncap_first}Page));
	}

	/**
	 * 获取菜单树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(${entityName?uncap_first}Service.tree(${entityName?uncap_first}Page, true));
	}

	/**
	 * 所有人都有权限的菜单
	 */
	public void ${entityName?uncap_first}TreeRecursive() {
		writeJson(${entityName?uncap_first}Service.tree(${entityName?uncap_first}Page, true));
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			${entityName?uncap_first}Service.edit(${entityName?uncap_first}Page);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
			j.setObj(${entityName?uncap_first}Page.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 添加菜单
	 */
	public void add() {
		Json j = new Json();
		try {
			${entityName?uncap_first}Service.add(${entityName?uncap_first}Page);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(${entityName?uncap_first}Page.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			${entityName?uncap_first}Service.delete(${entityName?uncap_first}Page);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

}
