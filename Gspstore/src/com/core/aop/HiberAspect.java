package com.core.aop;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.core.def.ConstantsDefs;
import com.jeecg.pageModel.SessionInfo;
import com.jeecg.util.ResourceUtil;
/**
 * Hiberate拦截器：实现创建人，创建时间，创建人名称自动注入;
 *                修改人,修改时间,修改人名自动注入;
 */
@Component
public class HiberAspect extends EmptyInterceptor {
	private static final Logger logger = Logger.getLogger(HiberAspect.class);
	private static final long serialVersionUID = 1L;


@Override
public boolean onSave(Object entity, Serializable id, Object[] state,
		String[] propertyNames, Type[] types) {
	
	//logger.info("--------------HiberAspect-----------------------onSave-------------------");
	SessionInfo sessionInfo;
	try{
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		
		//初始化数据的时候，未登录情况下session为空
		if(sessionInfo==null){
			return true;
		}
	}catch(Exception e){
		return true;
	}
	try {
		//添加数据
		 for (int index=0;index<propertyNames.length;index++)
		 {
		     /*找到名为"创建时间"的属性*/
		     if ("createdate".equals(propertyNames[index]))
		     {
		         /*使用拦截器将对象的"创建时间"属性赋上值*/
		    	 if(state[index]==null){
		    		 state[index] = new Date();
		    	 }
		         continue;
		     }
		     /*找到名为"删除标示"的属性*/
		     //update-begin author:zhangdaihao date:20121115 for:新数据插入的时候，逻辑删除字段自动赋值为0:未删除
		     if ("delflag".equals(propertyNames[index]))
		     {
		         /*使用拦截器将对象的"删除标示"属性赋上值*/
		    	 if(state[index]==null){
		    		//state[index] = ConstantsDefs.DELETE_FLG_0;
		    	 }
		         continue;
		     }
		     //update-end author:zhangdaihao date:20121115 for:新数据插入的时候，逻辑删除字段自动赋值为0:未删除
		     /*找到名为"创建人"的属性*/
		     else if ("crtuser".equals(propertyNames[index]))
		     {
		         /*使用拦截器将对象的"创建人"属性赋上值*/
		    	 if(state[index]==null){
		    		  state[index] = sessionInfo.getUserId();
		    	 }
		         continue;
		     }
		     /*找到名为"创建人名称"的属性*/
		     else if ("crtusername".equals(propertyNames[index]))
		     {
		         /*使用拦截器将对象的"创建人名称"属性赋上值*/
		    	 if(state[index]==null){
		    		 state[index] = sessionInfo.getRealName(); 
		    	 }
		         continue;
		     }
		 }
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 return true;
}


@Override
public boolean onFlushDirty(Object entity, Serializable id,
		Object[] currentState, Object[] previousState,
		String[] propertyNames, Type[] types) {
//	logger.info("--------------HiberAspect-----------------------onFlushDirty-------------------");
	SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
	//update-begin author:jeecg_心風輕雲淡 date:20130123 for:bug
	//添加数据
     for (int index=0;index<propertyNames.length;index++)
     {
         /*找到名为"修改时间"的属性*/
         if ("modifydate".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改时间"属性赋上值*/
        	 currentState[index] = new Date();
             continue;
         }
         /*找到名为"修改人"的属性*/
         else if ("modifier".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改人"属性赋上值*/
        	 currentState[index] = sessionInfo.getUserId();
        	 continue;
         }
         /*找到名为"修改人名称"的属性*/
         else if ("modifiername".equals(propertyNames[index]))
         {
             /*使用拦截器将对象的"修改人名称"属性赋上值*/
        	 currentState[index] = sessionInfo.getRealName();
        	 continue;
         }
       //update-end author:jeecg_心風輕雲淡 date:20130123 for:bug
     }

	 return true;
}
}
