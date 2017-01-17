package sun.service.jeecg;

import sun.entity.jeecg.JeecgOrderProductSingleEntity;
import com.jeecg.pageModel.DataGrid;
import sun.page.jeecg.JeecgOrderProductSinglePage;
import com.jeecg.service.BaseServiceI;
import java.util.List;

/**   
 * @Title: Service
 * @Description: 订单产品明细
 * @author zhangdaihao
 * @date 2013-01-18 15:44:10
 * @version V1.0   
 *
 */
public interface JeecgOrderProductSingleServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOrderProductSinglePage jeecgOrderProductSinglePage);

	/**
	 * 添加
	 * 
	 * @param jeecgOrderProductSinglePage
	 */
	public void add(JeecgOrderProductSinglePage jeecgOrderProductSinglePage);

	/**
	 * 修改
	 * 
	 * @param jeecgOrderProductSinglePage
	 */
	public void update(JeecgOrderProductSinglePage jeecgOrderProductSinglePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOrderProductSingle
	 * @return
	 */
	public JeecgOrderProductSingleEntity get(JeecgOrderProductSinglePage jeecgOrderProductSinglePage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOrderProductSingleEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOrderProductSingleEntity> listAll(JeecgOrderProductSinglePage jeecgOrderProductSinglePage);

}
