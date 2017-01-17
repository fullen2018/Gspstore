package sun.service.order;

import sun.entity.order.GbuyOrderCustomEntity;
import com.jeecg.pageModel.DataGrid;
import sun.page.order.GbuyOrderCustomPage;
import com.jeecg.service.BaseServiceI;
import java.util.List;

/**   
 * @Title: Service
 * @Description: 订单客户明细
 * @author zhangdaihao
 * @date 2011-12-19 13:09:30
 * @version V1.0   
 *
 */
public interface GbuyOrderCustomServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(GbuyOrderCustomPage gbuyOrderCustomPage);

	/**
	 * 添加
	 * 
	 * @param gbuyOrderCustomPage
	 */
	public void add(GbuyOrderCustomPage gbuyOrderCustomPage);

	/**
	 * 修改
	 * 
	 * @param gbuyOrderCustomPage
	 */
	public void update(GbuyOrderCustomPage gbuyOrderCustomPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param GbuyOrderCustom
	 * @return
	 */
	public GbuyOrderCustomEntity get(GbuyOrderCustomPage gbuyOrderCustomPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public GbuyOrderCustomEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<GbuyOrderCustomEntity> listAll(GbuyOrderCustomPage gbuyOrderCustomPage);

}
