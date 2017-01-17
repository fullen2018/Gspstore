package business.service.saleback;

import business.entity.saleback.SaleBackDetailEntity;
import business.page.saleback.SaleBackDetailPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 销售退货明细
 * @author zhangdaihao
 * @date 2013-05-20 13:40:44
 * @version V1.0   
 *
 */
public interface SaleBackDetailServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SaleBackDetailPage saleBackDetailPage);

	/**
	 * 添加
	 * 
	 * @param saleBackDetailPage
	 */
	public void add(SaleBackDetailPage saleBackDetailPage);

	/**
	 * 修改
	 * 
	 * @param saleBackDetailPage
	 */
	public void update(SaleBackDetailPage saleBackDetailPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param SaleBackDetail
	 * @return
	 */
	public SaleBackDetailEntity get(SaleBackDetailPage saleBackDetailPage);
	
	
	/**
	 * 获得
	 * 
	 * @param salebackno
	 * @return
	 */
	public SaleBackDetailEntity get(java.lang.String salebackno);
	
	/**
	 * 获取所有数据
	 */
	public List<SaleBackDetailEntity> listAll(SaleBackDetailPage saleBackDetailPage);

}
