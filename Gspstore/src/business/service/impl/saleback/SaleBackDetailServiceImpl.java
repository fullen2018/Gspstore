package business.service.impl.saleback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.saleback.SaleBackDetailEntity;
import business.page.saleback.SaleBackDetailPage;
import business.service.saleback.SaleBackDetailServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 销售退货明细
 * @author zhangdaihao
 * @date 2013-05-20 13:40:42
 * @version V1.0   
 *
 */
@Service("saleBackDetailService")
public class SaleBackDetailServiceImpl extends BaseServiceImpl implements SaleBackDetailServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<SaleBackDetailEntity> saleBackDetailEntityDao;

	public BaseDaoI<SaleBackDetailEntity> getSaleBackDetailEntityDao() {
		return saleBackDetailEntityDao;
	}
	@Autowired
	public void setSaleBackDetailEntityDao(BaseDaoI<SaleBackDetailEntity> saleBackDetailEntityDao) {
		this.saleBackDetailEntityDao = saleBackDetailEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SaleBackDetailPage saleBackDetailPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(saleBackDetailPage)));
		j.setTotal(total(saleBackDetailPage));
		return j;
	}

	private List<SaleBackDetailPage> getPagesFromEntitys(List<SaleBackDetailEntity> saleBackDetailEntitys) {
		List<SaleBackDetailPage> saleBackDetailPages = new ArrayList<SaleBackDetailPage>();
		if (saleBackDetailEntitys != null && saleBackDetailEntitys.size() > 0) {
			for (SaleBackDetailEntity tb : saleBackDetailEntitys) {
				SaleBackDetailPage b = new SaleBackDetailPage();
				BeanUtils.copyProperties(tb, b);
				saleBackDetailPages.add(b);
			}
		}
		return saleBackDetailPages;
	}

	private List<SaleBackDetailEntity> find(SaleBackDetailPage saleBackDetailPage) {
		String hql = "from SaleBackDetailEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackDetailPage, hql, values);

		if (saleBackDetailPage.getSort() != null && saleBackDetailPage.getOrder() != null) {
			hql += " order by " + saleBackDetailPage.getSort() + " " + saleBackDetailPage.getOrder();
		}
		return saleBackDetailEntityDao.find(hql, saleBackDetailPage.getPage(), saleBackDetailPage.getRows(), values);
	}

	private Long total(SaleBackDetailPage saleBackDetailPage) {
		String hql = "select count(*) from SaleBackDetailEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackDetailPage, hql, values);
		return saleBackDetailEntityDao.count(hql, values);
	}

	private String addWhere(SaleBackDetailPage saleBackDetailPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		SaleBackDetailEntity saleBackDetailEntity = new SaleBackDetailEntity();
		BeanUtils.copyProperties(saleBackDetailPage, saleBackDetailEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, saleBackDetailEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(SaleBackDetailPage saleBackDetailPage) {
		SaleBackDetailEntity t = new SaleBackDetailEntity();
		BeanUtils.copyProperties(saleBackDetailPage, t);
		saleBackDetailEntityDao.save(t);
	}

	public void update(SaleBackDetailPage saleBackDetailPage) throws Exception {
		SaleBackDetailEntity t = saleBackDetailEntityDao.get(SaleBackDetailEntity.class, saleBackDetailPage.getSalebackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(saleBackDetailPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SaleBackDetailEntity where salebackno = '"+id+"'";
				SaleBackDetailEntity t = saleBackDetailEntityDao.get(hql);
				if (t != null) {
					saleBackDetailEntityDao.delete(t);
				}
			}
		}
	}

	public SaleBackDetailEntity get(SaleBackDetailPage saleBackDetailPage) {
		return saleBackDetailEntityDao.get(SaleBackDetailEntity.class, saleBackDetailPage.getSalebackno());
	}

	public SaleBackDetailEntity get(java.lang.String salebackno) {
		return saleBackDetailEntityDao.get(SaleBackDetailEntity.class, salebackno);
	}
	public List<SaleBackDetailEntity> listAll(SaleBackDetailPage saleBackDetailPage) {
		String hql = "from SaleBackDetailEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackDetailPage, hql, values);
		List<SaleBackDetailEntity> list = saleBackDetailEntityDao.find(hql,values);
		return list;
	}
}
