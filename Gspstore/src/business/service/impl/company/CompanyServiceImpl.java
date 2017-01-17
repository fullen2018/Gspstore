package business.service.impl.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.company.CompanyEntity;
import business.page.company.CompanyPage;
import business.service.company.CompanyServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 公司信息
 * @author zhangdaihao
 * @date 2013-06-20 14:31:51
 * @version V1.0   
 *
 */
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<CompanyEntity> companyEntityDao;

	public BaseDaoI<CompanyEntity> getCompanyEntityDao() {
		return companyEntityDao;
	}
	@Autowired
	public void setCompanyEntityDao(BaseDaoI<CompanyEntity> companyEntityDao) {
		this.companyEntityDao = companyEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(CompanyPage companyPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(companyPage)));
		j.setTotal(total(companyPage));
		return j;
	}

	private List<CompanyPage> getPagesFromEntitys(List<CompanyEntity> companyEntitys) {
		List<CompanyPage> companyPages = new ArrayList<CompanyPage>();
		if (companyEntitys != null && companyEntitys.size() > 0) {
			for (CompanyEntity tb : companyEntitys) {
				CompanyPage b = new CompanyPage();
				BeanUtils.copyProperties(tb, b);
				companyPages.add(b);
			}
		}
		return companyPages;
	}

	private List<CompanyEntity> find(CompanyPage companyPage) {
		String hql = "from CompanyEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(companyPage, hql, values);

		if (companyPage.getSort() != null && companyPage.getOrder() != null) {
			hql += " order by " + companyPage.getSort() + " " + companyPage.getOrder();
		}
		return companyEntityDao.find(hql, companyPage.getPage(), companyPage.getRows(), values);
	}

	private Long total(CompanyPage companyPage) {
		String hql = "select count(*) from CompanyEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(companyPage, hql, values);
		return companyEntityDao.count(hql, values);
	}

	private String addWhere(CompanyPage companyPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		CompanyEntity companyEntity = new CompanyEntity();
		BeanUtils.copyProperties(companyPage, companyEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, companyEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(CompanyPage companyPage) {
		CompanyEntity t = new CompanyEntity();
		BeanUtils.copyProperties(companyPage, t);
		companyEntityDao.save(t);
	}

	public void update(CompanyPage companyPage) throws Exception {
		CompanyEntity t = companyEntityDao.get(CompanyEntity.class, companyPage.getCompanyno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(companyPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from CompanyEntity where companyno = '"+id+"'";
				CompanyEntity t = companyEntityDao.get(hql);
				if (t != null) {
					companyEntityDao.delete(t);
				}
			}
		}
	}

	public CompanyEntity get(CompanyPage companyPage) {
		return companyEntityDao.get(CompanyEntity.class, companyPage.getCompanyno());
	}

	public CompanyEntity get(java.lang.String companyno) {
		return companyEntityDao.get(CompanyEntity.class, companyno);
	}
	public List<CompanyEntity> listAll(CompanyPage companyPage) {
		String hql = "from CompanyEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(companyPage, hql, values);
		List<CompanyEntity> list = companyEntityDao.find(hql,values);
		return list;
	}
	public CompanyPage getOneCompany(){
	    CompanyPage cp = new CompanyPage();
	    String hql = "from CompanyEntity where 1 = 1 ";
        List<Object> values = new ArrayList<Object>();
        List<CompanyEntity> list = companyEntityDao.find(hql);
        CompanyEntity en = null;
        if (list!=null&&list.size()>0){
            en = list.get(0);
        }else{
            en = new CompanyEntity();
            en.setCompanyname("请设置公司名称");
            en.setAddress("请设置公司地址");
            companyEntityDao.save(en);
        }
        BeanUtils.copyProperties(en,cp);
        return cp;
	}
}
