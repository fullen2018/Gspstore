package sun.service.jeecg;

import sun.entity.jeecg.JeecgOrderCustomSingleEntity;
import com.jeecg.pageModel.DataGrid;
import sun.page.jeecg.JeecgOrderCustomSinglePage;
import com.jeecg.service.BaseServiceI;
import java.util.List;

/**   
 * @Title: Service
 * @Description: 订单客户明细
 * @author zhangdaihao
 * @date 2013-01-18 15:44:09
 * @version V1.0   
 *
 */
public interface JeecgOrderCustomSingleServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOrderCustomSinglePage jeecgOrderCustomSinglePage);

	/**
	 * 添加
	 * 
	 * @param jeecgOrderCustomSinglePage
	 */
	public void add(JeecgOrderCustomSinglePage jeecgOrderCustomSinglePage);

	/**
	 * 修改
	 * 
	 * @param jeecgOrderCustomSinglePage
	 */
	public void update(JeecgOrderCustomSinglePage jeecgOrderCustomSinglePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOrderCustomSingle
	 * @return
	 */
	public JeecgOrderCustomSingleEntity get(JeecgOrderCustomSinglePage jeecgOrderCustomSinglePage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOrderCustomSingleEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOrderCustomSingleEntity> listAll(JeecgOrderCustomSinglePage jeecgOrderCustomSinglePage);

}
