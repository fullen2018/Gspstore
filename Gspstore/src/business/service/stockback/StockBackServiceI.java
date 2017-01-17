package business.service.stockback;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
import java.util.List;
import business.entity.stockback.StockBackEntity;
import business.page.stockback.StockBackPage;
import business.page.stockback.StockBackDetailPage;
import business.entity.stockback.StockBackDetailEntity;

/**   
 * @Title: Service
 * @Description: 购进退货单
 * @author zhangdaihao
 * @date 2013-05-17 17:21:12
 * @version V1.0   
 *
 */
public interface StockBackServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(StockBackPage stockBackPage);

	/**
	 * 添加
	 * 
	 * @param stockBackPage
	 */
	public void add(StockBackPage stockBackPage);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(StockBackPage stockBackPage,List<StockBackDetailPage> stockBackDetailList)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(StockBackPage stockBackPage,List<StockBackDetailPage> stockBackDetailList)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param stockBackPage
	 */
	public void update(StockBackPage stockBackPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param StockBack
	 * @return
	 */
	public StockBackEntity get(StockBackPage stockBackPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockbackno
	 * @return
	 */
	public StockBackEntity get(java.lang.String stockbackno);
	
	/**
	 * 获取所有数据
	 */
	public List<StockBackEntity> listAll(StockBackPage stockBackPage);
	
	/**根据主表Key,查询子表明细：购进退货明细*/
	public List<StockBackDetailPage> getStockBackDetailListByFkey(java.lang.String stockbackno);

}
