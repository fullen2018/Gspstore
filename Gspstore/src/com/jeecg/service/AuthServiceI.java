package com.jeecg.service;

import java.util.List;

import com.jeecg.pageModel.Auth;
import com.jeecg.pageModel.TreeNode;

public interface AuthServiceI extends BaseServiceI {

	/**
	 * 根据请求参数,去权限表查询看是否存在配置
	 * @param auth
	 * @return
	 */
	public boolean findAuthExist(String curl);
	
	/**
	 * 获得权限treegrid
	 * 
	 * @param auth
	 * @return
	 */
	public List<Auth> treegrid(Auth auth);

	/**
	 * 删除权限
	 * 
	 * @param auth
	 */
	public void delete(Auth auth);

	/**
	 * 获取权限树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Auth auth, boolean b);
	
	
	/**
	 * 获取权限树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> treeByCtype(Auth auth, boolean b);

	/**
	 * 添加权限
	 * 
	 * @param auth
	 */
	public void add(Auth auth);

	/**
	 * 编辑权限
	 * 
	 * @param auth
	 */
	public void edit(Auth auth);

}
