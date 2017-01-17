package com.jeecg.service;

/**
 * 修复数据库Service
 * 
 * @author zhangdaihao
 * 
 */
public interface RepairServiceI extends BaseServiceI {

	/**
	 * 修复数据库
	 */
	public void repair();

	/**
	 * 先清空数据库，然后再修复数据库
	 */
	public void deleteAndRepair();

}
