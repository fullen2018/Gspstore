package com.jeecg.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.def.ConstantsSys;
import com.jeecg.pageModel.Json;
import com.jeecg.pageModel.Menu;
import com.jeecg.service.MenuServiceI;
import com.jeecg.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "menuAction", results = { @Result(name = "menu", location = "/com/jeecg/menu.jsp") })
public class MenuAction extends BaseAction implements ModelDriven<Menu> {

	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private MenuServiceI menuService;

	private Menu menu = new Menu();

	public Menu getModel() {
		return menu;
	}

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	/**
	 * 首页获得菜单树
	 */
	public void ctrlTree() {
		writeJson(menuService.tree(menu, false));
	}

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @return
	 */
	public String goMenu() {
		return "menu";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		writeJson(menuService.treegrid(menu));
	}

	/**
	 * 获取菜单树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(menuService.tree(menu, true));
	}

	/**
	 * 所有人都有权限的菜单
	 */
	public void menuTreeRecursive() {
		writeJson(menuService.tree(menu, true));
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			menuService.edit(menu);
			j.setSuccess(true);
			j.setMsg("编辑成功!请手动刷新左侧功能菜单树！");
			j.setObj(menu.getCpid());
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
			menu.setCtype(ConstantsSys.TAUTH_CTYPE_01);
			menuService.add(menu);
			j.setSuccess(true);
			j.setMsg("添加成功!请手动刷新左侧功能菜单树！");
			j.setObj(menu.getCpid());
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
			menuService.delete(menu);
			j.setSuccess(true);
			j.setMsg("删除成功！请手动刷新左侧功能菜单树！");
			j.setObj(menu.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

}
