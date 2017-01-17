package com.jeecg.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.service.RepairServiceI;

/**
 * 修复数据库ACTION
 * 
 * @author zhangdaihao
 * 
 */
@Action(value = "repairAction", results = { @Result(name = "success", location = "/index.jsp") })
public class RepairAction extends BaseAction {

	private RepairServiceI repairService;

	public RepairServiceI getRepairService() {
		return repairService;
	}

	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	/**
	 * 修复数据库
	 * 
	 * @return
	 */
	public String repair() {
		repairService.repair();
		return "success";
	}

	/**
	 * 先清空数据库，然后再修复数据库
	 * 
	 * @return
	 */
	public String deleteAndRepair() {
		repairService.deleteAndRepair();
		return "success";
	}

}
