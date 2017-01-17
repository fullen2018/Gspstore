package business.service.sale;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
import java.util.List;
import java.util.Map;

import business.entity.sale.SaleEntity;
import business.page.report.ReportQueryForm;
import business.page.sale.SalePage;
import business.page.sale.SaleDetailPage;
import business.page.stockin.StockinDetailPage;
import business.entity.sale.SaleDetailEntity;

/**   
 * @Title: Service
 * @Description: 销售单
 * @author zhangdaihao
 * @date 2013-05-19 10:15:01
 * @version V1.0   
 *
 */
public interface SaleServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SalePage salePage);

	/**
	 * 添加
	 * 
	 * @param salePage
	 */
	public void add(SalePage salePage);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SalePage salePage,List<SaleDetailPage> saleDetailList)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(SalePage salePage,List<SaleDetailPage> saleDetailList)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param salePage
	 */
	public void update(SalePage salePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Sale
	 * @return
	 */
	public SaleEntity get(SalePage salePage);
	
	
	/**
	 * 获得
	 * 
	 * @param salebillno
	 * @return
	 */
	public SaleEntity get(java.lang.String salebillno);
	
	/**
	 * 获取所有数据
	 */
	public List<SaleEntity> listAll(SalePage salePage);
	
	/**根据主表Key,查询子表明细：销售单明细*/
	public List<SaleDetailPage> getSaleDetailListByFkey(java.lang.String salebillno);
	
	public List<Map<String,Object>> getSaleStock();

    public List<Map<String, Object>> viewSaleTotalList(ReportQueryForm salePage);

    public List<Map<String, Object>> viewSaleDetailList(ReportQueryForm salePage);
    public List<Map<String,Object>> viewSaleByAccounterList_HuiZong(ReportQueryForm reportQueryForm);
    public List<Map<String,Object>> viewSaleByAccounterList_MingXi(ReportQueryForm reportQueryForm);

}
