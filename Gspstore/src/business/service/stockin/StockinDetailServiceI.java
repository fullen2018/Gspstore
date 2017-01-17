package business.service.stockin;

import business.entity.stockin.StockinDetailEntity;
import business.page.stockin.StockinDetailPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 入库明细表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:38
 * @version V1.0   
 *
 */
public interface StockinDetailServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(StockinDetailPage stockinDetailPage);

	/**
	 * 添加
	 * 
	 * @param stockinDetailPage
	 */
	public void add(StockinDetailPage stockinDetailPage);

	/**
	 * 修改
	 * 
	 * @param stockinDetailPage
	 */
	public void update(StockinDetailPage stockinDetailPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param StockinDetail
	 * @return
	 */
	public StockinDetailEntity get(StockinDetailPage stockinDetailPage);
	
	
	/**
	 * 获得
	 * 
	 * @param stockinbillno
	 * @return
	 */
	public StockinDetailEntity get(java.lang.String stockinbillno);
	
	/**
	 * 获取所有数据
	 */
	public List<StockinDetailEntity> listAll(StockinDetailPage stockinDetailPage);

}
