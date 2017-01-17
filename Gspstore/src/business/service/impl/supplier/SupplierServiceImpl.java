package business.service.impl.supplier;

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

import business.entity.supplier.SupplierEntity;
import business.page.supplier.SupplierPage;
import business.service.supplier.SupplierServiceI;
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
 * @Description: 供应商信息
 * @author zhangdaihao
 * @date 2013-05-13 09:23:16
 * @version V1.0   
 *
 */
@Service("supplierService")
public class SupplierServiceImpl extends BaseServiceImpl implements SupplierServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<SupplierEntity> supplierEntityDao;

	public BaseDaoI<SupplierEntity> getSupplierEntityDao() {
		return supplierEntityDao;
	}
	@Autowired
	public void setSupplierEntityDao(BaseDaoI<SupplierEntity> supplierEntityDao) {
		this.supplierEntityDao = supplierEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SupplierPage supplierPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(supplierPage)));
		j.setTotal(total(supplierPage));
		return j;
	}

	private List<SupplierPage> getPagesFromEntitys(List<SupplierEntity> supplierEntitys) {
		List<SupplierPage> supplierPages = new ArrayList<SupplierPage>();
		if (supplierEntitys != null && supplierEntitys.size() > 0) {
			for (SupplierEntity tb : supplierEntitys) {
				SupplierPage b = new SupplierPage();
				BeanUtils.copyProperties(tb, b);
				supplierPages.add(b);
			}
		}
		return supplierPages;
	}

	private List<SupplierEntity> find(SupplierPage supplierPage) {
		String hql = "from SupplierEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(supplierPage, hql, values);

		if (supplierPage.getSort() != null && supplierPage.getOrder() != null) {
			hql += " order by " + supplierPage.getSort() + " " + supplierPage.getOrder();
		}
		return supplierEntityDao.find(hql, supplierPage.getPage(), supplierPage.getRows(), values);
	}

	private Long total(SupplierPage supplierPage) {
		String hql = "select count(*) from SupplierEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(supplierPage, hql, values);
		return supplierEntityDao.count(hql, values);
	}

	private String addWhere(SupplierPage supplierPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		SupplierEntity supplierEntity = new SupplierEntity();
		BeanUtils.copyProperties(supplierPage, supplierEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, supplierEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(SupplierPage supplierPage) {
		SupplierEntity t = new SupplierEntity();
		BeanUtils.copyProperties(supplierPage, t);
		Integer num = getMaxSupplierNum();
        t.setOrdernum(num);
        t.setSupplierid(FormatUtil.convertIntToString(4, num));
        t.setCreatedate(new Date());
        t.setModifydate(new Date());
		supplierEntityDao.save(t);
	}

	public void update(SupplierPage supplierPage) throws Exception {
		SupplierEntity t = supplierEntityDao.get(SupplierEntity.class, supplierPage.getSupplierid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(supplierPage, t);
            t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SupplierEntity where supplierid = '"+id+"'";
				SupplierEntity t = supplierEntityDao.get(hql);
				if (t != null) {
					supplierEntityDao.delete(t);
				}
			}
		}
	}

	public SupplierEntity get(SupplierPage supplierPage) {
		return supplierEntityDao.get(SupplierEntity.class, supplierPage.getSupplierid());
	}

	public SupplierEntity get(java.lang.String supplierid) {
		return supplierEntityDao.get(SupplierEntity.class, supplierid);
	}
	public List<SupplierEntity> listAll(SupplierPage supplierPage) {
		String hql = "from SupplierEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(supplierPage, hql, values);
		List<SupplierEntity> list = supplierEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxSupplierNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_supplier ae ";
        Map<String, Object> map = supplierEntityDao.findOneForJdbc(sql, null);
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
        String sql = "select count(1) as delflag from t_rk_stockin t where t.supplierid='"+key+"' ";
        Map<String, Object> map = supplierEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
