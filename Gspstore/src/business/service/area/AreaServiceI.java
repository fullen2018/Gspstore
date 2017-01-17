package business.service.area;

import business.entity.area.AreaEntity;
import business.page.area.AreaPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 片区信息
 * @author zhangdaihao
 * @date 2013-05-12 18:15:54
 * @version V1.0   
 *
 */
public interface AreaServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(AreaPage areaPage);

	/**
	 * 添加
	 * 
	 * @param areaPage
	 */
	public void add(AreaPage areaPage);

	/**
	 * 修改
	 * 
	 * @param areaPage
	 */
	public void update(AreaPage areaPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Area
	 * @return
	 */
	public AreaEntity get(AreaPage areaPage);
	
	
	/**
	 * 获得
	 * 
	 * @param areacode
	 * @return
	 */
	public AreaEntity get(java.lang.String areacode);
	
	/**
	 * 获取所有数据
	 */
	public List<AreaEntity> listAll(AreaPage areaPage);
	
	public Integer getMaxAreaNum();
	public boolean ifCanDelete(String key);
}
