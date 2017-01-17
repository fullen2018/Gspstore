package business.service.stock;

import business.entity.stock.StockEntity;
import business.page.stock.StockPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 库存信息
 * @author zhangdaihao
 * @date 2013-05-16 21:42:57
 * @version V1.0   
 *
 */
public interface StockServiceI extends BaseServiceI {

    public List<StockEntity> findStockByHql(String hql, Object... param);
	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagridDetail(StockPage stockPage);
	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagridTotal(StockPage stockPage);

	/**
	 * 添加
	 * 
	 * @param stockPage
	 */
	public void add(StockPage stockPage);

	/**
	 * 修改
	 * 
	 * @param stockPage
	 */
	public void update(StockPage stockPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Stock
	 * @return
	 */
	public StockEntity get(StockPage stockPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockkey
	 * @return
	 */
	public StockEntity get(java.lang.String stockkey);
	
	/**
	 * 获取所有数据
	 */
	public List<StockEntity> listAll(StockPage stockPage);

}
