package sun.service.jeecg;

import java.io.InputStream;
import java.util.List;

import sun.entity.jeecg.JeecgOneTestEntity;
import sun.page.jeecg.JeecgOneTestPage;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;

/**   
 * @Title: Service
 * @Description: 单表模型Test
 * @author zhangdaihao
 * @date 2011-12-31 14:18:16
 * @version V1.0   
 *
 */
public interface JeecgOneTestServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(JeecgOneTestPage jeecgOneTestPage);

	/**
	 * 添加
	 * 
	 * @param jeecgOneTestPage
	 */
	public void add(JeecgOneTestPage jeecgOneTestPage);

	/**
	 * 修改
	 * 
	 * @param jeecgOneTestPage
	 */
	public void update(JeecgOneTestPage jeecgOneTestPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param JeecgOneTest
	 * @return
	 */
	public JeecgOneTestEntity get(JeecgOneTestPage jeecgOneTestPage);
	
	
	/**
	 * 获得
	 * 
	 * @param obid
	 * @return
	 */
	public JeecgOneTestEntity get(String obid);
	
	/**
	 * 获取所有数据
	 */
	public List<JeecgOneTestEntity> listAll(JeecgOneTestPage jeecgOneTestPage);
	
	
	/**
	 * 导出excel
	 * @return
	 * @throws Exception
	 */
	public InputStream exportXls( JeecgOneTestPage jeecgOneTestPage)  throws  Exception;

}
