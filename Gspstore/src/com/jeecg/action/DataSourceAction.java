package com.jeecg.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * druid datasource manager Action
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "datasourceAction", results = { @Result(name = "druid", location = "/druid/index.html", type = "redirect") })
public class DataSourceAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(DataSourceAction.class);

	/**
	 * 跳转到连接池监控页面
	 * 
	 * @return
	 */
	public String goDruid() {
		return "druid";
	}

}
