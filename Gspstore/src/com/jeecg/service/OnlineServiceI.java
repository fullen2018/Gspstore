package com.jeecg.service;

import com.jeecg.model.Tonline;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.pageModel.Online;

/**
 * 在线用户Service
 * 
 * @author zhangdaihao
 * 
 */
public interface OnlineServiceI extends BaseServiceI {

	/**
	 * 更新或插入用户在线列表
	 * 
	 * @param online
	 */
	public void updateOnline(Tonline online);

	/**
	 * 删除用户在线列表
	 * 
	 * @param loginName
	 * @param ip
	 */
	public void deleteOnline(String loginName, String ip);

	/**
	 * 获得用户在线列表datagrid
	 * 
	 * @param online
	 * @return
	 */
	public DataGrid datagrid(Online online);

}
