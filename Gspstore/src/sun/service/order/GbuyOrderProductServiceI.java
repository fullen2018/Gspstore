package sun.service.order;

import sun.entity.order.GbuyOrderProductEntity;
import com.jeecg.pageModel.DataGrid;
import sun.page.order.GbuyOrderProductPage;
import com.jeecg.service.BaseServiceI;
import java.util.List;

/**   
 * @Title: Service
 * @Description: 订单产品明细
 * @author zhangdaihao
 * @date 2011-12-19 13:09:31
 * @version V1.0   
 *
 */
public interface GbuyOrderProductServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(GbuyOrderProductPage gbuyOrderProductPage);

	/**
	 * 添加
	 * 
	 * @param gbuyOrderProductPage
	 */
	public void add(GbuyOrderProductPage gbuyOrderProductPage);

	/**
	 * 修改
	 * 
	 * @param gbuyOrderProductPage
	 */
	public void update(GbuyOrderProductPage gbuyOrderProductPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param GbuyOrderProduct
	 * @return
	 */
	public GbuyOrderProductEntity get(GbuyOrderProductPage gbuyOrderProductPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public GbuyOrderProductEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<GbuyOrderProductEntity> listAll(GbuyOrderProductPage gbuyOrderProductPage);

}
