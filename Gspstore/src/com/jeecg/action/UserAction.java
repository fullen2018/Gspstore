package com.jeecg.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.pageModel.Json;
import com.jeecg.pageModel.Menu;
import com.jeecg.pageModel.SessionInfo;
import com.jeecg.pageModel.User;
import com.jeecg.service.MenuServiceI;
import com.jeecg.service.UserServiceI;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.IpUtil;
import com.jeecg.util.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "userAction", results = { @Result(name = "user", location = "/com/jeecg/user.jsp"), 
										  @Result(name = "showUserInfo", location = "/user/userInfo.jsp"),
										  @Result(name = "login", location = "/login.jsp"),
										  @Result(name = "index", location = "/index.jsp",type="redirect") })
public class UserAction extends BaseAction implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private User user = new User();
	private UserServiceI userService;
	
	@Autowired
	private MenuServiceI menuService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}
	/**
     * 获得无分页的所有数据
     */
    public void  combox(){
        writeJson(userService.listAll(user));
    }
	/**
	 * 用户登录
	 */
	public void loginOld() {
		Json j = new Json();
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = saveSessionInfo(u);
			j.setSuccess(true);
			j.setMsg("用户登录成功！");
			j.setObj(sessionInfo);

			changeUserAuths(u);
		} else {
			j.setMsg("用户名或密码错误!");
		}
		writeJson(j);
	}
	
	/**
	 * 用户登录
	 */
	public String login() {
		//判断账号和密码是否为空
		if(StringUtils.isBlank(user.getCname())||StringUtils.isBlank(user.getCpwd())){
			getRequest().setAttribute("msg", "提示：用户名和密码不能为空!");
			return "login";
		}
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = saveSessionInfo(u);
			changeUserAuths(u);
		} else {
			getRequest().setAttribute("msg", "提示：用户名或密码错误，请确认数据库是否初始化!");
			return "login";
		}
		return "index";
	}

	/**
	 * 改变用户的权限列表
	 * 
	 * @param u
	 */
	private void changeUserAuths(User u) {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		//System.out.println(userService.getAuths(u).get(0).getCid());
		sessionInfo.setAuths(userService.getAuths(u));
		Menu menu = new Menu();
		sessionInfo.setMenus((menuService.treegrid(menu)));
	}

	/**
	 * 登录成功将用户信息保存到SESSION中
	 * 
	 * @param u
	 *            用户对象
	 * @return
	 */
	private SessionInfo saveSessionInfo(User u) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setUserId(u.getCid());
		sessionInfo.setLoginName(u.getCname());
		//update-begin author:zhangdaihao date:20121121 for:将取的账号改为用户名
		sessionInfo.setRealName(u.getRealname());
		//update-end author:zhangdaihao date:20121121 for:将取的账号改为用户名
		sessionInfo.setLoginPassword(user.getCpwd());
		sessionInfo.setUserType(u.getUsertype());
		sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
		ServletActionContext.getRequest().getSession().setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
		ServletActionContext.getRequest().getSession().setAttribute("usertype", u.getUsertype());
		return sessionInfo;
	}

	/**
	 * 退出系统
	 */
	public void logout() {
		Json j = new Json();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 表格方式用户登录
	 */
	public void loginDatagrid() {
		if (user.getQ() != null && !user.getQ().trim().equals("")) {
			user.setCname(user.getQ());
		}
		writeJson(userService.datagrid(user));
	}

	/**
	 * 补全方式用户登录
	 */
	public void loginCombobox() {
		writeJson(userService.loginCombobox(user));
	}

	/**
	 * 用户注册
	 */
	public void reg() {
		Json j = new Json();
		try {
			userService.reg(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */
	public String goUser() {
		return "user";
	}

	/**
	 * 获得用户datagrid
	 */
	public void datagrid() {
		writeJson(userService.datagrid(user));
	}

	/**
	 * 添加一个用户
	 */
	public void add() {
		Json j = new Json();
		try {
			userService.add(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("用户名已存在！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 编辑一个用户
	 */
	public void edit() {
		Json j = new Json();
		try {
			userService.update(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败，用户名已存在！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个或多个用户
	 */
	public void delete() {
		Json j = new Json();
		userService.delete(user.getIds());
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 批量修改用户密码
	 */
	public void modifyPwd() {
		Json j = new Json();
		userService.modifyPwd(user);
		j.setSuccess(true);
		j.setMsg("密码修改成功！");
		writeJson(j);
	}

	/**
	 * 跳转到当前用户信息页面
	 * 
	 * @return
	 */
	public String showUserInfo() {
		return "showUserInfo";
	}

	/**
	 * 修改自己的密码
	 */
	public void modifySelfPwd() {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		user.setCid(sessionInfo.getUserId());
		if (userService.modifySelfPwd(user)) {
			j.setSuccess(true);
			j.setMsg("密码修改成功！");
		} else {
			j.setMsg("修改密码失败！原密码错误！");
		}
		writeJson(j);
	}

	/**
	 * 批量修改用户角色
	 */
	public void modifyUserRole() {
		Json j = new Json();
		userService.modifyUserRole(user);
		j.setSuccess(true);
		j.setMsg("角色修改成功！");
		writeJson(j);
	}
}
