package com.jeecg.service;

import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.pageModel.Role;

/**
 * 角色Service
 * 
 * @author zhangdaihao
 * 
 */
public interface RoleServiceI extends BaseServiceI {

	/**
	 * 查询角色datagrid
	 * 
	 * @param role
	 * @return
	 */
	public DataGrid datagrid(Role role);

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	public void add(Role role);

	/**
	 * 修改角色
	 * 
	 * @param role
	 */
	public void update(Role role);

	/**
	 * 删除一个或多个角色
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得角色下拉列表
	 * 
	 * @return
	 */
	public List<Role> combobox();

}
