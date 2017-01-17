package com.jeecg.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.jeecg.pageModel.SessionInfo;
import com.jeecg.util.ResourceUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * session拦截器
 * 
 * @author zhangdaihao
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			//update-begin author:zhangdaihao date:20120908 for:session过期直接跳转到登陆页面
			return "noLogin";
			//update-end author:zhangdaihao date:20120908 for:session过期直接跳转到登陆页面
		}
		return actionInvocation.invoke();
	}

}
