package com.jeecg.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.pageModel.Json;
import com.jeecg.pageModel.Role;
import com.jeecg.service.RoleServiceI;
import com.jeecg.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 角色ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "roleAction", results = { @Result(name = "role", location = "/com/jeecg/role.jsp") })
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private Role role = new Role();
	private RoleServiceI roleService;

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public Role getModel() {
		return role;
	}

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	public String goRole() {
		return "role";
	}

	/**
	 * 获得角色datagrid
	 */
	public void datagrid() {
		writeJson(roleService.datagrid(role));
	}

	/**
	 * 添加一个角色
	 */
	public void add() {
		Json j = new Json();
		try {
			roleService.add(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 编辑一个角色
	 */
	public void edit() {
		Json j = new Json();
		try {
			roleService.update(role);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个或多个角色
	 */
	public void delete() {
		Json j = new Json();
		roleService.delete(role.getIds());
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 获得角色下拉列表
	 */
	public void roleCombobox() {
		writeJson(roleService.combobox());
	}

}
