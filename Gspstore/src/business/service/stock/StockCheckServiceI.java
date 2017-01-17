package business.service.stock;

import business.entity.stock.StockCheckEntity;
import business.page.report.ReportQueryForm;
import business.page.stock.StockCheckPage;
import java.util.List;
import java.util.Map;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 库存盘点
 * @author zhangdaihao
 * @date 2013-05-16 23:47:32
 * @version V1.0   
 *
 */
public interface StockCheckServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(StockCheckPage stockCheckPage);

	/**
	 * 添加
	 * 
	 * @param stockCheckPage
	 */
	public void add(StockCheckPage stockCheckPage);

	/**
	 * 修改
	 * 
	 * @param stockCheckPage
	 */
	public void update(StockCheckPage stockCheckPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param StockCheck
	 * @return
	 */
	public StockCheckEntity get(StockCheckPage stockCheckPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockcheckcode
	 * @return
	 */
	public StockCheckEntity get(java.lang.String stockcheckcode);
	
	/**
	 * 获取所有数据
	 */
	public List<StockCheckEntity> listAll(StockCheckPage stockCheckPage);

    public List<Map<String, Object>> viewStockCheckData(
        ReportQueryForm stockCheckPage);

}
