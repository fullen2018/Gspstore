package com.jeecg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.jeecg.pageModel.Json;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 异常拦截器
 * 
 * @author xiewenyu
 * 
 */
public class ExceptionInterceptor extends AbstractInterceptor {  
     
    /**
	 * 
	 */
	private static final long serialVersionUID = -5471880815125291343L;
	private static final Logger log = Logger  .getLogger(ExceptionInterceptor.class);  
  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        String actionName = invocation.getInvocationContext().getName();  
        try {  
            String result = invocation.invoke();  
            return result;  
        } catch (Exception e) {  
            log.error("-------------"+actionName, e);  
            ActionContext context = ActionContext.getContext();
            HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            if(isAjaxRequest(request)){
            	Json j=new Json();
            	j.setMsg(String.format("错误信息%s",e.getMessage()));
            	j.setSuccess(false);
            	return j.toString();
            }
            //HttpServletResponse response=(HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
            //request.getRequestDispatcher("/error/err.jsp").forward(request, response);
            return "globalError"; 
         
        }  
    }  
    private boolean isAjaxRequest(HttpServletRequest request) {  
        String header = request.getHeader("X-Requested-With");  
        if (header != null && "XMLHttpRequest".equals(header))  
            return true;  
        else  
            return false;  
    }  
}  

