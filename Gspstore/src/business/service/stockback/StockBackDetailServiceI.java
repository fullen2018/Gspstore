package business.service.stockback;

import business.entity.stockback.StockBackDetailEntity;
import business.page.stockback.StockBackDetailPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 购进退货明细
 * @author zhangdaihao
 * @date 2013-05-17 17:21:09
 * @version V1.0   
 *
 */
public interface StockBackDetailServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(StockBackDetailPage stockBackDetailPage);

	/**
	 * 添加
	 * 
	 * @param stockBackDetailPage
	 */
	public void add(StockBackDetailPage stockBackDetailPage);

	/**
	 * 修改
	 * 
	 * @param stockBackDetailPage
	 */
	public void update(StockBackDetailPage stockBackDetailPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param StockBackDetail
	 * @return
	 */
	public StockBackDetailEntity get(StockBackDetailPage stockBackDetailPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockbackno
	 * @return
	 */
	public StockBackDetailEntity get(java.lang.String stockbackno);
	
	/**
	 * 获取所有数据
	 */
	public List<StockBackDetailEntity> listAll(StockBackDetailPage stockBackDetailPage);

}
