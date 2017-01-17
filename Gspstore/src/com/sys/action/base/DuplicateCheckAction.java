package com.sys.action.base;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.action.BaseAction;
import com.jeecg.dao.BaseDaoI;
import com.jeecg.pageModel.Json;
import com.opensymphony.xwork2.ModelDriven;
import com.sys.page.base.DuplicateCheckPage;

/**   
 * @Title: Action
 * @Description: 校验工具Action
 * @author zhangdaihao
 * @date 2012-11-15 22:27:30
 * @version V1.0   
 *
 */
@Action(value = "duplicateCheckAction", results = {})
public class DuplicateCheckAction extends BaseAction implements ModelDriven<DuplicateCheckPage> {

	private static final Logger logger = Logger.getLogger(DuplicateCheckAction.class);

	@Autowired
	private BaseDaoI<?> duplicateCheckDao;
	private DuplicateCheckPage duplicateCheckPage = new DuplicateCheckPage();
	public DuplicateCheckPage getModel() {
		return duplicateCheckPage;
	}

	
	public DuplicateCheckPage getDuplicateCheckPage() {
		return duplicateCheckPage;
	}


	public void setDuplicateCheckPage(DuplicateCheckPage duplicateCheckPage) {
		this.duplicateCheckPage = duplicateCheckPage;
	}


	/**
	 * 校验数据是否在系统中是否存在
	 * @return
	 */
	public void doDuplicateCheck(){
		Json j = new Json();
		Long num = null;
		
		if(StringUtils.isNotBlank(duplicateCheckPage.getRowObid())){
			//[2].编辑页面校验
			String hql = "SELECT count(*) FROM "+duplicateCheckPage.getTableName()
						+" WHERE "+duplicateCheckPage.getFieldName() +" =? and obid != ?";
			num = duplicateCheckDao.count(hql, duplicateCheckPage.getFieldVlaue(),duplicateCheckPage.getRowObid());
		}else{
			//[1].添加页面校验
			String hql = "SELECT count(*) FROM "+duplicateCheckPage.getTableName()
						+" WHERE "+duplicateCheckPage.getFieldName() +" =?";
			num = duplicateCheckDao.count(hql, duplicateCheckPage.getFieldVlaue());
		}
		
		if(num==null||num==0){
			//该值可用
			j.setSuccess(true);
			j.setMsg("该值可用！");
		}else{
			//该值不可用
			j.setSuccess(false);
			j.setMsg("该值不可用，系统中已存在！");
		}
		writeJson(j);
	}
}
