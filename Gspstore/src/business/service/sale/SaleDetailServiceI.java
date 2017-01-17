package business.service.sale;

import business.entity.sale.SaleDetailEntity;
import business.page.sale.SaleDetailPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 销售单明细
 * @author zhangdaihao
 * @date 2013-05-19 10:14:58
 * @version V1.0   
 *
 */
public interface SaleDetailServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SaleDetailPage saleDetailPage);

	/**
	 * 添加
	 * 
	 * @param saleDetailPage
	 */
	public void add(SaleDetailPage saleDetailPage);

	/**
	 * 修改
	 * 
	 * @param saleDetailPage
	 */
	public void update(SaleDetailPage saleDetailPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param SaleDetail
	 * @return
	 */
	public SaleDetailEntity get(SaleDetailPage saleDetailPage);
	
	
	/**
	 * 获得
	 * 
	 * @param salebillno
	 * @return
	 */
	public SaleDetailEntity get(java.lang.String salebillno);
	
	/**
	 * 获取所有数据
	 */
	public List<SaleDetailEntity> listAll(SaleDetailPage saleDetailPage);

}
