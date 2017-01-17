package business.service.impl.area;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.area.AreaEntity;
import business.page.area.AreaPage;
import business.service.area.AreaServiceI;
import business.util.FormatUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 片区信息
 * @author zhangdaihao
 * @date 2013-05-12 18:15:54
 * @version V1.0   
 *
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl implements AreaServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<AreaEntity> areaEntityDao;

	public BaseDaoI<AreaEntity> getAreaEntityDao() {
		return areaEntityDao;
	}
	@Autowired
	public void setAreaEntityDao(BaseDaoI<AreaEntity> areaEntityDao) {
		this.areaEntityDao = areaEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(AreaPage areaPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(areaPage)));
		j.setTotal(total(areaPage));
		return j;
	}

	private List<AreaPage> getPagesFromEntitys(List<AreaEntity> areaEntitys) {
		List<AreaPage> areaPages = new ArrayList<AreaPage>();
		if (areaEntitys != null && areaEntitys.size() > 0) {
			for (AreaEntity tb : areaEntitys) {
				AreaPage b = new AreaPage();
				BeanUtils.copyProperties(tb, b);
				areaPages.add(b);
			}
		}
		return areaPages;
	}

	private List<AreaEntity> find(AreaPage areaPage) {
		String hql = "from AreaEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(areaPage, hql, values);

		if (areaPage.getSort() != null && areaPage.getOrder() != null) {
			hql += " order by " + areaPage.getSort() + " " + areaPage.getOrder();
		}
		return areaEntityDao.find(hql, areaPage.getPage(), areaPage.getRows(), values);
	}

	private Long total(AreaPage areaPage) {
		String hql = "select count(*) from AreaEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(areaPage, hql, values);
		return areaEntityDao.count(hql, values);
	}

	private String addWhere(AreaPage areaPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		AreaEntity areaEntity = new AreaEntity();
		BeanUtils.copyProperties(areaPage, areaEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, areaEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(AreaPage areaPage) {
		AreaEntity t = new AreaEntity();
		BeanUtils.copyProperties(areaPage, t);
		t.setCreatedate(new Date());
		t.setModifydate(new Date());
		Integer num = getMaxAreaNum();
		t.setOrdernum(num);
		t.setAreacode(FormatUtil.convertIntToString(3, num));
		areaEntityDao.save(t);
	}

	public void update(AreaPage areaPage) throws Exception {
		AreaEntity t = areaEntityDao.get(AreaEntity.class, areaPage.getAreacode());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(areaPage, t);
			t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from AreaEntity where areacode = '"+id+"'";
				AreaEntity t = areaEntityDao.get(hql);
				if (t != null) {
					areaEntityDao.delete(t);
				}
			}
		}
	}

	public AreaEntity get(AreaPage areaPage) {
		return areaEntityDao.get(AreaEntity.class, areaPage.getAreacode());
	}

	public AreaEntity get(java.lang.String areacode) {
		return areaEntityDao.get(AreaEntity.class, areacode);
	}
	public List<AreaEntity> listAll(AreaPage areaPage) {
		String hql = "from AreaEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(areaPage, hql, values);
		List<AreaEntity> list = areaEntityDao.find(hql,values);
		return list;
	}
	
	public Integer getMaxAreaNum(){
	    Integer maxNum = 0;
	    String sql = "select max(ae.ordernum) as ordernum  from t_xx_area ae ";
	    Map<String, Object> map = areaEntityDao.findOneForJdbc(sql, null);
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
        String sql = "select count(1) as delflag from t_xx_accounter t where t.area='"+key+"' ";
        Map<String, Object> map = areaEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
