package com.jeecg.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.def.ConstantsSys;
import com.jeecg.pageModel.Auth;
import com.jeecg.pageModel.Json;
import com.jeecg.service.AuthServiceI;
import com.jeecg.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 权限ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "authAction", results = { @Result(name = "auth", location = "/com/jeecg/auth.jsp") })
public class AuthAction extends BaseAction implements ModelDriven<Auth> {

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private Auth auth = new Auth();
	private AuthServiceI authService;

	public AuthServiceI getAuthService() {
		return authService;
	}

	@Autowired
	public void setAuthService(AuthServiceI authService) {
		this.authService = authService;
	}

	public Auth getModel() {
		return auth;
	}

	public String goAuth() {
		return "auth";
	}

	/**
	 * 获得权限treegrid
	 */
	public void treegrid() {
		writeJson(authService.treegrid(auth));
	}

	/**
	 * 删除一个权限
	 */
	public void delete() {
		Json j = new Json();
		try {
			authService.delete(auth);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(auth.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

	/**
	 * 获取权限树
	 */
	public void tree() {
		writeJson(authService.tree(auth, false));
	}

	/**
	 * 获取权限树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(authService.tree(auth, true));
	}

	/**
	 * 权限树,所有人都有权限访问这个
	 */
	public void authTreeRecursive() {
		//根据参数，判断是查询菜单权限还是按钮权限
		if(StringUtils.isNotBlank(auth.getCtype())){
			writeJson(authService.treeByCtype(auth, true));
		}else{
			writeJson(authService.tree(auth, true));
		}
	}
	
	/**
	 * 编辑权限
	 */
	public void edit() {
		Json j = new Json();
		try {
			authService.edit(auth);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
			j.setObj(auth.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 添加权限
	 */
	public void add() {
		Json j = new Json();
		try {
			auth.setCtype(ConstantsSys.TAUTH_CTYPE_02);
			authService.add(auth);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(auth.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		writeJson(j);
	}

}
