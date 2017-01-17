package business.service.depot;

import business.entity.depot.DepotEntity;
import business.page.depot.DepotPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 仓库信息
 * @author zhangdaihao
 * @date 2013-05-13 16:54:08
 * @version V1.0   
 *
 */
public interface DepotServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(DepotPage depotPage);

	/**
	 * 添加
	 * 
	 * @param depotPage
	 */
	public void add(DepotPage depotPage);

	/**
	 * 修改
	 * 
	 * @param depotPage
	 */
	public void update(DepotPage depotPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Depot
	 * @return
	 */
	public DepotEntity get(DepotPage depotPage);
	
	
	/**
	 * 获得
	 * 
	 * @param depotid
	 * @return
	 */
	public DepotEntity get(java.lang.String depotid);
	
	/**
	 * 获取所有数据
	 */
	public List<DepotEntity> listAll(DepotPage depotPage);
	
	public Integer getMaxDepotNum();
	public boolean ifCanDelete(String key);
}
