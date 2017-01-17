package com.sys.service.base;

import java.util.List;

import com.jeecg.pageModel.TreeNode;
import com.jeecg.service.BaseServiceI;
import com.sys.page.base.JeecgGroupPage;
/**   
 * @Title: Service
 * @Description: 会计科目
 * @author zhangdaihao
 * @date 2013-01-20 21:31:48
 * @version V1.0   
 *
 */
public interface JeecgGroupServiceI extends BaseServiceI {

	/**
	 * 获得菜单treegrid
	 * 
	 * @param jeecgGroupPage
	 * @return
	 */
	public List<JeecgGroupPage> treegrid(JeecgGroupPage jeecgGroupPage);

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(JeecgGroupPage jeecgGroupPage, Boolean b);

	/**
	 * 编辑菜单
	 * 
	 * @param jeecgGroupPage
	 */
	public void edit(JeecgGroupPage jeecgGroupPage);

	/**
	 * 添加菜单
	 * 
	 * @param jeecgGroupPage
	 */
	public void add(JeecgGroupPage jeecgGroupPage);

	/**
	 * 删除菜单
	 * 
	 * @param jeecgGroupPage
	 */
	public void delete(JeecgGroupPage jeecgGroupPage);

}
