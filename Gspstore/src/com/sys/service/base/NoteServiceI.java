package com.sys.service.base;

import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.sys.entity.base.NoteEntity;
import com.sys.page.base.NotePage;

/**   
 * @Title: Service
 * @Description: 公告
 * @author zhangdaihao
 * @date 2013-01-18 14:55:34
 * @version V1.0   
 *
 */
public interface NoteServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(NotePage notePage);

	/**
	 * 添加
	 * 
	 * @param notePage
	 */
	public void add(NotePage notePage);

	/**
	 * 修改
	 * 
	 * @param notePage
	 */
	public void update(NotePage notePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Note
	 * @return
	 */
	public NoteEntity get(NotePage notePage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public NoteEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<NoteEntity> listAll(NotePage notePage);

}
