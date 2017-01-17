package business.service.fee;

import business.entity.fee.FeeEntity;
import business.page.fee.FeePage;
import business.page.report.ReportQueryForm;

import java.util.List;
import java.util.Map;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 财务费用信息
 * @author zhangdaihao
 * @date 2013-05-21 20:46:41
 * @version V1.0   
 *
 */
public interface FeeServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(FeePage feePage);

	/**
	 * 添加
	 * 
	 * @param feePage
	 */
	public void add(FeePage feePage);

	/**
	 * 修改
	 * 
	 * @param feePage
	 */
	public void update(FeePage feePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Fee
	 * @return
	 */
	public FeeEntity get(FeePage feePage);
	
	
	/**
	 * 获得
	 * 
	 * @param feeid
	 * @return
	 */
	public FeeEntity get(java.lang.String feeid);
	
	/**
	 * 获取所有数据
	 */
	public List<FeeEntity> listAll(FeePage feePage);

    public List<Map<String, Object>> viewFeeTotalList(
        ReportQueryForm reportQueryForm);

}
