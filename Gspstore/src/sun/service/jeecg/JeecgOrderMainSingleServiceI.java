package sun.service.jeecg;

import sun.entity.jeecg.JeecgOrderMainSingleEntity;
import com.jeecg.pageModel.DataGrid;
import sun.page.jeecg.JeecgOrderMainSinglePage;
import com.jeecg.service.BaseServiceI;
import java.util.List;
import sun.page.jeecg.JeecgOrderCustomSinglePage;
import sun.entity.jeecg.JeecgOrderCustomSingleEntity;
import sun.page.jeecg.JeecgOrderProductSinglePage;
import sun.entity.jeecg.JeecgOrderProductSingleEntity;

/**   
 * @Title: Service
 * @Description: 订单主数据
 * @author zhangdaihao
 * @date 2013-01-18 15:44:12
 * @version V1.0   
 *
 */
public interface JeecgOrderMainSingleServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOrderMainSinglePage jeecgOrderMainSinglePage);

	/**
	 * 添加
	 * 
	 * @param jeecgOrderMainSinglePage
	 */
	public void add(JeecgOrderMainSinglePage jeecgOrderMainSinglePage);

	
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(JeecgOrderMainSinglePage jeecgOrderMainSinglePage,List<JeecgOrderCustomSinglePage> jeecgOrderCustomSingleList,List<JeecgOrderProductSinglePage> jeecgOrderProductSingleList)  throws Exception ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void editMain(JeecgOrderMainSinglePage jeecgOrderMainSinglePage,List<JeecgOrderCustomSinglePage> jeecgOrderCustomSingleList,List<JeecgOrderProductSinglePage> jeecgOrderProductSingleList)  throws Exception ;
	
	
	/**
	 * 修改
	 * 
	 * @param jeecgOrderMainSinglePage
	 */
	public void update(JeecgOrderMainSinglePage jeecgOrderMainSinglePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOrderMainSingle
	 * @return
	 */
	public JeecgOrderMainSingleEntity get(JeecgOrderMainSinglePage jeecgOrderMainSinglePage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOrderMainSingleEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOrderMainSingleEntity> listAll(JeecgOrderMainSinglePage jeecgOrderMainSinglePage);
	
	/**根据主表Key,查询子表明细：订单客户明细*/
	public List<JeecgOrderCustomSinglePage> getJeecgOrderCustomSingleListByFkey(String obid,String goOrderCode);
	/**根据主表Key,查询子表明细：订单产品明细*/
	public List<JeecgOrderProductSinglePage> getJeecgOrderProductSingleListByFkey(String obid,String goOrderCode);

}
