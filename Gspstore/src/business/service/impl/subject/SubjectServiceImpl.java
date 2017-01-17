package business.service.impl.subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.subject.SubjectEntity;
import business.page.subject.SubjectPage;
import business.service.subject.SubjectServiceI;
import business.util.FormatUtil;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 财务科目信息
 * @author zhangdaihao
 * @date 2013-05-21 20:45:43
 * @version V1.0   
 *
 */
@Service("subjectService")
public class SubjectServiceImpl extends BaseServiceImpl implements SubjectServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<SubjectEntity> subjectEntityDao;

	public BaseDaoI<SubjectEntity> getSubjectEntityDao() {
		return subjectEntityDao;
	}
	@Autowired
	public void setSubjectEntityDao(BaseDaoI<SubjectEntity> subjectEntityDao) {
		this.subjectEntityDao = subjectEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SubjectPage subjectPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(subjectPage)));
		j.setTotal(total(subjectPage));
		return j;
	}

	private List<SubjectPage> getPagesFromEntitys(List<SubjectEntity> subjectEntitys) {
		List<SubjectPage> subjectPages = new ArrayList<SubjectPage>();
		if (subjectEntitys != null && subjectEntitys.size() > 0) {
			for (SubjectEntity tb : subjectEntitys) {
				SubjectPage b = new SubjectPage();
				BeanUtils.copyProperties(tb, b);
				subjectPages.add(b);
			}
		}
		return subjectPages;
	}

	private List<SubjectEntity> find(SubjectPage subjectPage) {
		String hql = "from SubjectEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(subjectPage, hql, values);

		if (subjectPage.getSort() != null && subjectPage.getOrder() != null) {
			hql += " order by " + subjectPage.getSort() + " " + subjectPage.getOrder();
		}
		return subjectEntityDao.find(hql, subjectPage.getPage(), subjectPage.getRows(), values);
	}

	private Long total(SubjectPage subjectPage) {
		String hql = "select count(*) from SubjectEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(subjectPage, hql, values);
		return subjectEntityDao.count(hql, values);
	}

	private String addWhere(SubjectPage subjectPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		SubjectEntity subjectEntity = new SubjectEntity();
		BeanUtils.copyProperties(subjectPage, subjectEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, subjectEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(SubjectPage subjectPage) {
		SubjectEntity t = new SubjectEntity();
		BeanUtils.copyProperties(subjectPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxSubjectNum();
        t.setOrdernum(num);
        t.setSubjectid(FormatUtil.convertIntToString(3, num));
        
		subjectEntityDao.save(t);
	}

	public void update(SubjectPage subjectPage) throws Exception {
		SubjectEntity t = subjectEntityDao.get(SubjectEntity.class, subjectPage.getSubjectid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(subjectPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SubjectEntity where subjectid = '"+id+"'";
				SubjectEntity t = subjectEntityDao.get(hql);
				if (t != null) {
					subjectEntityDao.delete(t);
				}
			}
		}
	}

	public SubjectEntity get(SubjectPage subjectPage) {
		return subjectEntityDao.get(SubjectEntity.class, subjectPage.getSubjectid());
	}

	public SubjectEntity get(java.lang.String subjectid) {
		return subjectEntityDao.get(SubjectEntity.class, subjectid);
	}
	public List<SubjectEntity> listAll(SubjectPage subjectPage) {
		String hql = "from SubjectEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(subjectPage, hql, values);
		List<SubjectEntity> list = subjectEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxSubjectNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_subject ae ";
        Map<String, Object> map = subjectEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
    public boolean ifCanDelete(String key){
        String sql = "select count(1) as delflag from t_cw_fee t where t.subjectid='"+key+"' ";
        Map<String, Object> map = subjectEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
