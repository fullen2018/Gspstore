package com.jeecg.tagjsp;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;

import  com.jeecg.pageModel.Auth;
import com.jeecg.pageModel.SessionInfo;
import com.jeecg.util.ResourceUtil;

/**
 * <P>Title: 页面按钮显示标签</P>
 * <P>Description: 此标签用来控制jsp页面按钮是否显示</P>
 * @author zhangdaihao
 * @Date：2012-9-9 下午9:07:29
 */
public class AnchorTag extends TagSupport {
	private static final long serialVersionUID = 3174234039143531070L;
	// 标签使用的时候，传入的值
	private String privilege;

	@Override
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		List<Auth> auths = sessionInfo.getAuths();
		// admin用户不需要验证权限
		if (sessionInfo.getLoginName().equals("admin")||checkAuthExit(auths, privilege)) {
			// 用户权限列表中包含访问所需权限则返回EVAL_BODY_INCLUDE，即输出标签体的内容
			return EVAL_BODY_INCLUDE;
		} else {
			// 否则跳过标签体，即不显示标签包含的html内容
			try {
				pageContext.getOut().write("alert('您没有访问此功能的权限！权限路径为[" + privilege + "]请联系管理员给你赋予相应权限。');");
			} catch (IOException e) {
				e.printStackTrace();
			}//标签的返回值  
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	/**
	 * 判断用户权限是否存在
	 * @param auths
	 * @param url
	 * @return
	 */
	public boolean checkAuthExit(List<Auth> auths,String url){
		for(Auth t:auths){
			if(url.equals(t.getCurl())){
				return true;
			}
		}
		return false;
	}
} 