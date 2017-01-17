package com.sys.action.base;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.sys.page.base.JeecgGroupPage;
import com.sys.service.base.JeecgGroupServiceI;


/**   
 * @Title: Action
 * @Description: 组织机构
 * @author zhangdaihao
 * @date 2013-01-27 18:36:15
 * @version V1.0   
 *
 */
@Action(value = "jeecgGroupAction", results = { @Result(name = "jeecgGroup", location = "/com/sys/base/jeecgGroup.jsp") })
public class JeecgGroupAction extends BaseAction implements ModelDriven<JeecgGroupPage> {

	private static final Logger logger = Logger.getLogger(JeecgGroupAction.class);

	private JeecgGroupServiceI jeecgGroupService;

	private JeecgGroupPage jeecgGroupPage = new JeecgGroupPage();

	public JeecgGroupPage getModel() {
		return jeecgGroupPage;
	}

	public JeecgGroupServiceI getJeecgGroupService() {
		return jeecgGroupService;
	}

	@Autowired
	public void setJeecgGroupService(JeecgGroupServiceI jeecgGroupService) {
		this.jeecgGroupService = jeecgGroupService;
	}

	/**
	 * 首页获得菜单树
	 */
	public void ctrlTree() {
		writeJson(jeecgGroupService.tree(jeecgGroupPage, false));
	}

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @return
	 */
	public String goJeecgGroup() {
		return "jeecgGroup";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		writeJson(jeecgGroupService.treegrid(jeecgGroupPage));
	}

	/**
	 * 获取菜单树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(jeecgGroupService.tree(jeecgGroupPage, true));
	}

	/**
	 * 所有人都有权限的菜单
	 */
	public void jeecgGroupTreeRecursive() {
		writeJson(jeecgGroupService.tree(jeecgGroupPage, true));
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			jeecgGroupService.edit(jeecgGroupPage);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
			j.setObj(jeecgGroupPage.getCpid());
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
			jeecgGroupService.add(jeecgGroupPage);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(jeecgGroupPage.getCpid());
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
			jeecgGroupService.delete(jeecgGroupPage);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(jeecgGroupPage.getObid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

}
