package business.service.stockin;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
import java.util.List;
import java.util.Map;

import business.entity.stockin.StockinEntity;
import business.page.report.ReportQueryForm;
import business.page.stockin.StockinPage;
import business.page.stockin.StockinDetailPage;
import business.entity.stockin.StockinDetailEntity;

/**   
 * @Title: Service
 * @Description: 入库主表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:40
 * @version V1.0   
 *
 */
public interface StockinServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(StockinPage stockinPage);

	/**
	 * 添加
	 * 
	 * @param stockinPage
	 */
	public void add(StockinPage stockinPage);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(StockinPage stockinPage,List<StockinDetailPage> stockinDetailList)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(StockinPage stockinPage,List<StockinDetailPage> stockinDetailList)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param stockinPage
	 */
	public void update(StockinPage stockinPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Stockin
	 * @return
	 */
	public StockinEntity get(StockinPage stockinPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockinbillno
	 * @return
	 */
	public StockinEntity get(java.lang.String stockinbillno);
	
	/**
	 * 获取所有数据
	 */
	public List<StockinEntity> listAll(StockinPage stockinPage);
	
	/**根据主表Key,查询子表明细：入库明细表*/
	public List<StockinDetailPage> getStockinDetailListByFkey(java.lang.String stockinbillno);

    public List<Map<String, Object>> viewStockinTotalList(
        ReportQueryForm stockinPage);

    public List<Map<String, Object>> viewStockinDetailList(
        ReportQueryForm stockinPage);

}
