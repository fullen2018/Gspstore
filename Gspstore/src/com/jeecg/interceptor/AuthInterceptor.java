package com.jeecg.interceptor;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.pageModel.Auth;
import com.jeecg.pageModel.SessionInfo;
import com.jeecg.service.AuthServiceI;
import com.jeecg.util.RequestUtil;
import com.jeecg.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author zhangdaihao
 * 
 */
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private AuthServiceI authService;
	
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getLoginName().equals("admin")) {// admin用户不需要验证权限
			return actionInvocation.invoke();
		}
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest()).substring(1);
		//-----------------------------------------
//		菜单权限修改
//		if(requestPath.indexOf("!go")==-1){
//			return actionInvocation.invoke();
//		}
		
		//判断如果按钮权限或者菜单权限在系统中没有配置，则所有人都可以访问
		if(!authService.findAuthExist(requestPath)){
			return actionInvocation.invoke();
		}
		//-----------------------------------------
		logger.debug(actionInvocation.getAction().getClass());
		logger.debug(requestPath);
		List<Auth> auths = sessionInfo.getAuths();
		if (auths != null && auths.size() > 0) {
			boolean b = false;
			for (Auth a : auths) {
				if (requestPath.equals(a.getCurl())) {
					b = true;
					break;
				}
			}
			if (b) {
				return actionInvocation.invoke();
			}
		}
		ServletActionContext.getRequest().setAttribute("msg", "您没有访问此功能的权限！权限路径为[" + requestPath + "]请联系管理员给你赋予相应权限。");
		return "noAuth";
	}

}
