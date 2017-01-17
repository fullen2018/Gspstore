package sun.service.jeecg;

import sun.entity.jeecg.JeecgOneDemoEntity;
import sun.page.jeecg.JeecgOneDemoPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 单表模型（DEMO）
 * @author zhangdaihao
 * @date 2013-01-23 17:12:39
 * @version V1.0   
 *
 */
public interface JeecgOneDemoServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOneDemoPage jeecgOneDemoPage);

	/**
	 * 添加
	 * 
	 * @param jeecgOneDemoPage
	 */
	public JeecgOneDemoEntity add(JeecgOneDemoPage jeecgOneDemoPage);

	/**
	 * 修改
	 * 
	 * @param jeecgOneDemoPage
	 */
	public void update(JeecgOneDemoPage jeecgOneDemoPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOneDemo
	 * @return
	 */
	public JeecgOneDemoEntity get(JeecgOneDemoPage jeecgOneDemoPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOneDemoEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOneDemoEntity> listAll(JeecgOneDemoPage jeecgOneDemoPage);

}
