package business.service.accounter;

import business.entity.accounter.AccounterEntity;
import business.page.accounter.AccounterPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 业务员信息
 * @author zhangdaihao
 * @date 2013-05-13 09:26:48
 * @version V1.0   
 *
 */
public interface AccounterServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(AccounterPage accounterPage);

	/**
	 * 添加
	 * 
	 * @param accounterPage
	 */
	public void add(AccounterPage accounterPage);

	/**
	 * 修改
	 * 
	 * @param accounterPage
	 */
	public void update(AccounterPage accounterPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Accounter
	 * @return
	 */
	public AccounterEntity get(AccounterPage accounterPage);
	
	
	/**
	 * 获得
	 * 
	 * @param accountid
	 * @return
	 */
	public AccounterEntity get(java.lang.String accountid);
	
	/**
	 * 获取所有数据
	 */
	public List<AccounterEntity> listAll(AccounterPage accounterPage);
	
	public Integer getMaxAccounterNum();
	public boolean ifCanDelete(String key);
}
