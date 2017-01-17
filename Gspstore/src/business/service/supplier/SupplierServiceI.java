package business.service.supplier;

import business.entity.supplier.SupplierEntity;
import business.page.supplier.SupplierPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 供应商信息
 * @author zhangdaihao
 * @date 2013-05-13 09:23:16
 * @version V1.0   
 *
 */
public interface SupplierServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SupplierPage supplierPage);

	/**
	 * 添加
	 * 
	 * @param supplierPage
	 */
	public void add(SupplierPage supplierPage);

	/**
	 * 修改
	 * 
	 * @param supplierPage
	 */
	public void update(SupplierPage supplierPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Supplier
	 * @return
	 */
	public SupplierEntity get(SupplierPage supplierPage);
	
	
	/**
	 * 获得
	 * 
	 * @param supplierid
	 * @return
	 */
	public SupplierEntity get(java.lang.String supplierid);
	
	/**
	 * 获取所有数据
	 */
	public List<SupplierEntity> listAll(SupplierPage supplierPage);
	
	public Integer getMaxSupplierNum();
	public boolean ifCanDelete(String key);
}
