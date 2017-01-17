package sun.service.jeecg;

import java.util.List;

import sun.entity.jeecg.JeecgOneRowEntity;
import sun.page.jeecg.JeecgOneRowPage;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;

/**   
 * @Title: Service
 * @Description: 行编辑
 * @author zhangdaihao
 * @date 2011-12-31 14:29:05
 * @version V1.0   
 *
 */
public interface JeecgOneRowServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOneRowPage jeecgOneRowPage);

	/**
	 * 添加
	 * 
	 * @param jeecgOneRowPage
	 */
	public void add(JeecgOneRowPage jeecgOneRowPage);

	/**
	 * 修改
	 * 
	 * @param jeecgOneRowPage
	 */
	public void update(JeecgOneRowPage jeecgOneRowPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOneRow
	 * @return
	 */
	public JeecgOneRowEntity get(JeecgOneRowPage jeecgOneRowPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOneRowEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOneRowEntity> listAll(JeecgOneRowPage jeecgOneRowPage);

}
