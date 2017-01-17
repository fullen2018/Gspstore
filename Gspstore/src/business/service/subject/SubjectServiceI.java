package business.service.subject;

import business.entity.subject.SubjectEntity;
import business.page.subject.SubjectPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 财务科目信息
 * @author zhangdaihao
 * @date 2013-05-21 20:45:44
 * @version V1.0   
 *
 */
public interface SubjectServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(SubjectPage subjectPage);

	/**
	 * 添加
	 * 
	 * @param subjectPage
	 */
	public void add(SubjectPage subjectPage);

	/**
	 * 修改
	 * 
	 * @param subjectPage
	 */
	public void update(SubjectPage subjectPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Subject
	 * @return
	 */
	public SubjectEntity get(SubjectPage subjectPage);
	
	
	/**
	 * 获得
	 * 
	 * @param subjectid
	 * @return
	 */
	public SubjectEntity get(java.lang.String subjectid);
	
	/**
	 * 获取所有数据
	 */
	public List<SubjectEntity> listAll(SubjectPage subjectPage);
	
	public boolean ifCanDelete(String key);

}
