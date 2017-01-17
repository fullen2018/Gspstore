package business.service.customer;

import business.entity.customer.CustomerEntity;
import business.page.customer.CustomerPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 客户信息
 * @author zhangdaihao
 * @date 2013-05-13 14:23:08
 * @version V1.0   
 *
 */
public interface CustomerServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(CustomerPage customerPage);

	/**
	 * 添加
	 * 
	 * @param customerPage
	 */
	public void add(CustomerPage customerPage);

	/**
	 * 修改
	 * 
	 * @param customerPage
	 */
	public void update(CustomerPage customerPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Customer
	 * @return
	 */
	public CustomerEntity get(CustomerPage customerPage);
	
	
	/**
	 * 获得
	 * 
	 * @param customerid
	 * @return
	 */
	public CustomerEntity get(java.lang.String customerid);
	
	/**
	 * 获取所有数据
	 */
	public List<CustomerEntity> listAll(CustomerPage customerPage);
	
	public Integer getMaxCustomerNum();
	public boolean ifCanDelete(String key);
}
