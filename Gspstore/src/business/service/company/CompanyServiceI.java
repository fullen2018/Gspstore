package business.service.company;

import business.entity.company.CompanyEntity;
import business.page.company.CompanyPage;
import java.util.List;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 公司信息
 * @author zhangdaihao
 * @date 2013-06-20 14:31:51
 * @version V1.0   
 *
 */
public interface CompanyServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(CompanyPage companyPage);

	/**
	 * 添加
	 * 
	 * @param companyPage
	 */
	public void add(CompanyPage companyPage);

	/**
	 * 修改
	 * 
	 * @param companyPage
	 */
	public void update(CompanyPage companyPage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Company
	 * @return
	 */
	public CompanyEntity get(CompanyPage companyPage);
	
	
	/**
	 * 获得
	 * 
	 * @param companyno
	 * @return
	 */
	public CompanyEntity get(java.lang.String companyno);
	
	/**
	 * 获取所有数据
	 */
	public List<CompanyEntity> listAll(CompanyPage companyPage);
	
	public CompanyPage getOneCompany();

}
