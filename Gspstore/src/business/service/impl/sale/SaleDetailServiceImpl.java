package business.service.impl.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.sale.SaleDetailEntity;
import business.page.sale.SaleDetailPage;
import business.service.sale.SaleDetailServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 销售单明细
 * @author zhangdaihao
 * @date 2013-05-19 10:14:58
 * @version V1.0   
 *
 */
@Service("saleDetailService")
public class SaleDetailServiceImpl extends BaseServiceImpl implements SaleDetailServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<SaleDetailEntity> saleDetailEntityDao;

	public BaseDaoI<SaleDetailEntity> getSaleDetailEntityDao() {
		return saleDetailEntityDao;
	}
	@Autowired
	public void setSaleDetailEntityDao(BaseDaoI<SaleDetailEntity> saleDetailEntityDao) {
		this.saleDetailEntityDao = saleDetailEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SaleDetailPage saleDetailPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(saleDetailPage)));
		j.setTotal(total(saleDetailPage));
		return j;
	}

	private List<SaleDetailPage> getPagesFromEntitys(List<SaleDetailEntity> saleDetailEntitys) {
		List<SaleDetailPage> saleDetailPages = new ArrayList<SaleDetailPage>();
		if (saleDetailEntitys != null && saleDetailEntitys.size() > 0) {
			for (SaleDetailEntity tb : saleDetailEntitys) {
				SaleDetailPage b = new SaleDetailPage();
				BeanUtils.copyProperties(tb, b);
				saleDetailPages.add(b);
			}
		}
		return saleDetailPages;
	}

	private List<SaleDetailEntity> find(SaleDetailPage saleDetailPage) {
		String hql = "from SaleDetailEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleDetailPage, hql, values);

		if (saleDetailPage.getSort() != null && saleDetailPage.getOrder() != null) {
			hql += " order by " + saleDetailPage.getSort() + " " + saleDetailPage.getOrder();
		}
		return saleDetailEntityDao.find(hql, saleDetailPage.getPage(), saleDetailPage.getRows(), values);
	}

	private Long total(SaleDetailPage saleDetailPage) {
		String hql = "select count(*) from SaleDetailEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleDetailPage, hql, values);
		return saleDetailEntityDao.count(hql, values);
	}

	private String addWhere(SaleDetailPage saleDetailPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		SaleDetailEntity saleDetailEntity = new SaleDetailEntity();
		BeanUtils.copyProperties(saleDetailPage, saleDetailEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, saleDetailEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(SaleDetailPage saleDetailPage) {
		SaleDetailEntity t = new SaleDetailEntity();
		BeanUtils.copyProperties(saleDetailPage, t);
		saleDetailEntityDao.save(t);
	}

	public void update(SaleDetailPage saleDetailPage) throws Exception {
		SaleDetailEntity t = saleDetailEntityDao.get(SaleDetailEntity.class, saleDetailPage.getSalebillno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(saleDetailPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SaleDetailEntity where salebillno = '"+id+"'";
				SaleDetailEntity t = saleDetailEntityDao.get(hql);
				if (t != null) {
					saleDetailEntityDao.delete(t);
				}
			}
		}
	}

	public SaleDetailEntity get(SaleDetailPage saleDetailPage) {
		return saleDetailEntityDao.get(SaleDetailEntity.class, saleDetailPage.getSalebillno());
	}

	public SaleDetailEntity get(java.lang.String salebillno) {
		return saleDetailEntityDao.get(SaleDetailEntity.class, salebillno);
	}
	public List<SaleDetailEntity> listAll(SaleDetailPage saleDetailPage) {
		String hql = "from SaleDetailEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleDetailPage, hql, values);
		List<SaleDetailEntity> list = saleDetailEntityDao.find(hql,values);
		return list;
	}
}
