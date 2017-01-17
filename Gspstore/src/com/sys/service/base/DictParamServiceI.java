package com.sys.service.base;

import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.sys.entity.base.DictParamEntity;
import com.sys.page.base.DictParamPage;

/**   
 * @Title: Service
 * @Description: 数据字典
 * @author zhangdaihao
 * @date 2011-11-26 10:46:05
 * @version V1.0   
 *
 */
public interface DictParamServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(DictParamPage dictParamPage);

	/**
	 * 添加
	 * 
	 * @param dictParamPage
	 */
	public void add(DictParamPage dictParamPage);

	/**
	 * 修改
	 * 
	 * @param dictParamPage
	 */
	public void update(DictParamPage dictParamPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param DictParam
	 * @return
	 */
	public DictParamEntity get(DictParamPage dictParamPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public DictParamEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<DictParamEntity> listAll(DictParamPage dictParamPage);
	
	
	/**
	 * 根据类型/参数值获取参数名称
	 * 
	 * @param DictParam
	 * @return
	 */
	public DictParamEntity getDictName(String paramLevel,String paramValue);
	
	/**
	 * 获取所有数据
	 */
	public List<DictParamEntity> listAll();
	
	/**
	 * 获取所有数据
	 */
	public List<DictParamEntity> listAllByParamLevel(String paramLevel);

	
}
