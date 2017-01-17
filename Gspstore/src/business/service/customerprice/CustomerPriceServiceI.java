package business.service.customerprice;

import business.entity.customerprice.CustomerPriceEntity;
import business.page.customerprice.CustomerPricePage;
import java.util.List;
import java.util.Map;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 客户品种价格
 * @author zhangdaihao
 * @date 2013-06-07 00:44:33
 * @version V1.0   
 *
 */
public interface CustomerPriceServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(CustomerPricePage customerPricePage);

	/**
	 * 添加
	 * 
	 * @param customerPricePage
	 */
	public void add(CustomerPricePage customerPricePage);

	/**
	 * 修改
	 * 
	 * @param customerPricePage
	 */
	public void update(CustomerPricePage customerPricePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param CustomerPrice
	 * @return
	 */
	public CustomerPriceEntity get(CustomerPricePage customerPricePage);
	
	
	/**
	 * 获得
	 * 
	 * @param flowid
	 * @return
	 */
	public CustomerPriceEntity get(java.lang.String flowid);
	
	/**
	 * 获取所有数据
	 */
	public List<CustomerPriceEntity> listAll(CustomerPricePage customerPricePage);
	
	public Map<String,Object> getCustomerPrice(CustomerPricePage customerPricePage);

}
