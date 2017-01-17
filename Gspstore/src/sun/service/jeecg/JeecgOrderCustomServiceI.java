package sun.service.jeecg;

import java.util.List;

import sun.entity.jeecg.JeecgOrderCustomEntity;
import sun.page.jeecg.JeecgOrderCustomPage;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;

/**   
 * @Title: Service
 * @Description: 订单客户明细
 * @author zhangdaihao
 * @date 2011-12-31 16:22:58
 * @version V1.0   
 *
 */
public interface JeecgOrderCustomServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOrderCustomPage jeecgOrderCustomPage);

	/**
	 * 添加
	 * 
	 * @param jeecgOrderCustomPage
	 */
	public void add(JeecgOrderCustomPage jeecgOrderCustomPage);

	/**
	 * 修改
	 * 
	 * @param jeecgOrderCustomPage
	 */
	public void update(JeecgOrderCustomPage jeecgOrderCustomPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOrderCustom
	 * @return
	 */
	public JeecgOrderCustomEntity get(JeecgOrderCustomPage jeecgOrderCustomPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOrderCustomEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOrderCustomEntity> listAll(JeecgOrderCustomPage jeecgOrderCustomPage);

}
