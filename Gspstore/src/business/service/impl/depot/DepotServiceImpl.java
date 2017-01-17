package business.service.impl.depot;

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

import business.entity.depot.DepotEntity;
import business.page.depot.DepotPage;
import business.service.depot.DepotServiceI;
import business.util.FormatUtil;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 仓库信息
 * @author zhangdaihao
 * @date 2013-05-13 16:54:08
 * @version V1.0   
 *
 */
@Service("depotService")
public class DepotServiceImpl extends BaseServiceImpl implements DepotServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<DepotEntity> depotEntityDao;

	public BaseDaoI<DepotEntity> getDepotEntityDao() {
		return depotEntityDao;
	}
	@Autowired
	public void setDepotEntityDao(BaseDaoI<DepotEntity> depotEntityDao) {
		this.depotEntityDao = depotEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(DepotPage depotPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(depotPage)));
		j.setTotal(total(depotPage));
		return j;
	}

	private List<DepotPage> getPagesFromEntitys(List<DepotEntity> depotEntitys) {
		List<DepotPage> depotPages = new ArrayList<DepotPage>();
		if (depotEntitys != null && depotEntitys.size() > 0) {
			for (DepotEntity tb : depotEntitys) {
				DepotPage b = new DepotPage();
				BeanUtils.copyProperties(tb, b);
				depotPages.add(b);
			}
		}
		return depotPages;
	}

	private List<DepotEntity> find(DepotPage depotPage) {
		String hql = "from DepotEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(depotPage, hql, values);

		if (depotPage.getSort() != null && depotPage.getOrder() != null) {
			hql += " order by " + depotPage.getSort() + " " + depotPage.getOrder();
		}
		return depotEntityDao.find(hql, depotPage.getPage(), depotPage.getRows(), values);
	}

	private Long total(DepotPage depotPage) {
		String hql = "select count(*) from DepotEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(depotPage, hql, values);
		return depotEntityDao.count(hql, values);
	}

	private String addWhere(DepotPage depotPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		DepotEntity depotEntity = new DepotEntity();
		BeanUtils.copyProperties(depotPage, depotEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, depotEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(DepotPage depotPage) {
		DepotEntity t = new DepotEntity();
		BeanUtils.copyProperties(depotPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxDepotNum();
        t.setOrdernum(num);
        t.setDepotid(FormatUtil.convertIntToString(3, num));
		depotEntityDao.save(t);
	}

	public void update(DepotPage depotPage) throws Exception {
		DepotEntity t = depotEntityDao.get(DepotEntity.class, depotPage.getDepotid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(depotPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from DepotEntity where depotid = '"+id+"'";
				DepotEntity t = depotEntityDao.get(hql);
				if (t != null) {
					depotEntityDao.delete(t);
				}
			}
		}
	}

	public DepotEntity get(DepotPage depotPage) {
		return depotEntityDao.get(DepotEntity.class, depotPage.getDepotid());
	}

	public DepotEntity get(java.lang.String depotid) {
		return depotEntityDao.get(DepotEntity.class, depotid);
	}
	public List<DepotEntity> listAll(DepotPage depotPage) {
		String hql = "from DepotEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(depotPage, hql, values);
		List<DepotEntity> list = depotEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxDepotNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_depot ae ";
        Map<String, Object> map = depotEntityDao.findOneForJdbc(sql, null);
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
        String sql = "select count(1) as delflag from t_rk_stockin t where t.depotid='"+key+"' ";
        Map<String, Object> map = depotEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
