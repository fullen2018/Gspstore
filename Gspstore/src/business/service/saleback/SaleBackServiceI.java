package business.service.saleback;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
import java.util.List;
import business.entity.saleback.SaleBackEntity;
import business.page.saleback.SaleBackPage;
import business.page.saleback.SaleBackDetailPage;
import business.entity.saleback.SaleBackDetailEntity;

/**   
 * @Title: Service
 * @Description: 销售退货单
 * @author zhangdaihao
 * @date 2013-05-20 13:40:47
 * @version V1.0   
 *
 */
public interface SaleBackServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SaleBackPage saleBackPage);

	/**
	 * 添加
	 * 
	 * @param saleBackPage
	 */
	public void add(SaleBackPage saleBackPage);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(SaleBackPage saleBackPage,List<SaleBackDetailPage> saleBackDetailList)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(SaleBackPage saleBackPage,List<SaleBackDetailPage> saleBackDetailList)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param saleBackPage
	 */
	public void update(SaleBackPage saleBackPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param SaleBack
	 * @return
	 */
	public SaleBackEntity get(SaleBackPage saleBackPage);
	
	
	/**
	 * 获得
	 * 
	 * @param salebackno
	 * @return
	 */
	public SaleBackEntity get(java.lang.String salebackno);
	
	/**
	 * 获取所有数据
	 */
	public List<SaleBackEntity> listAll(SaleBackPage saleBackPage);
	
	/**根据主表Key,查询子表明细：销售退货明细*/
	public List<SaleBackDetailPage> getSaleBackDetailListByFkey(java.lang.String salebackno);

}
