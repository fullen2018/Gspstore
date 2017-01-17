package com.jeecg.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.pageModel.Online;
import com.jeecg.service.OnlineServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 在线列表ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "onlineAction")
public class OnlineAction extends BaseAction implements ModelDriven<Online> {

	private static final Logger logger = Logger.getLogger(OnlineAction.class);

	private Online online = new Online();

	public Online getModel() {
		return online;
	}

	private OnlineServiceI onlineService;

	public OnlineServiceI getOnlineService() {
		return onlineService;
	}

	@Autowired
	public void setOnlineService(OnlineServiceI onlineService) {
		this.onlineService = onlineService;
	}

	/**
	 * 首页获得在线列表datagrid
	 */
	public void onlineDatagrid() {
		writeJson(onlineService.datagrid(online));
	}

}
