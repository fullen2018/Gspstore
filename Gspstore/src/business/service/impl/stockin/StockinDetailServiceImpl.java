package business.service.impl.stockin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.stockin.StockinDetailEntity;
import business.page.stockin.StockinDetailPage;
import business.service.stockin.StockinDetailServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 入库明细表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:38
 * @version V1.0   
 *
 */
@Service("stockinDetailService")
public class StockinDetailServiceImpl extends BaseServiceImpl implements StockinDetailServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<StockinDetailEntity> stockinDetailEntityDao;

	public BaseDaoI<StockinDetailEntity> getStockinDetailEntityDao() {
		return stockinDetailEntityDao;
	}
	@Autowired
	public void setStockinDetailEntityDao(BaseDaoI<StockinDetailEntity> stockinDetailEntityDao) {
		this.stockinDetailEntityDao = stockinDetailEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(StockinDetailPage stockinDetailPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockinDetailPage)));
		j.setTotal(total(stockinDetailPage));
		return j;
	}

	private List<StockinDetailPage> getPagesFromEntitys(List<StockinDetailEntity> stockinDetailEntitys) {
		List<StockinDetailPage> stockinDetailPages = new ArrayList<StockinDetailPage>();
		if (stockinDetailEntitys != null && stockinDetailEntitys.size() > 0) {
			for (StockinDetailEntity tb : stockinDetailEntitys) {
				StockinDetailPage b = new StockinDetailPage();
				BeanUtils.copyProperties(tb, b);
				stockinDetailPages.add(b);
			}
		}
		return stockinDetailPages;
	}

	private List<StockinDetailEntity> find(StockinDetailPage stockinDetailPage) {
		String hql = "from StockinDetailEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinDetailPage, hql, values);

		if (stockinDetailPage.getSort() != null && stockinDetailPage.getOrder() != null) {
			hql += " order by " + stockinDetailPage.getSort() + " " + stockinDetailPage.getOrder();
		}
		return stockinDetailEntityDao.find(hql, stockinDetailPage.getPage(), stockinDetailPage.getRows(), values);
	}

	private Long total(StockinDetailPage stockinDetailPage) {
		String hql = "select count(*) from StockinDetailEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinDetailPage, hql, values);
		return stockinDetailEntityDao.count(hql, values);
	}

	private String addWhere(StockinDetailPage stockinDetailPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		StockinDetailEntity stockinDetailEntity = new StockinDetailEntity();
		BeanUtils.copyProperties(stockinDetailPage, stockinDetailEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockinDetailEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(StockinDetailPage stockinDetailPage) {
		StockinDetailEntity t = new StockinDetailEntity();
		BeanUtils.copyProperties(stockinDetailPage, t);
		stockinDetailEntityDao.save(t);
	}

	public void update(StockinDetailPage stockinDetailPage) throws Exception {
		StockinDetailEntity t = stockinDetailEntityDao.get(StockinDetailEntity.class, stockinDetailPage.getStockinbillno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockinDetailPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockinDetailEntity where stockinbillno = '"+id+"'";
				StockinDetailEntity t = stockinDetailEntityDao.get(hql);
				if (t != null) {
					stockinDetailEntityDao.delete(t);
				}
			}
		}
	}

	public StockinDetailEntity get(StockinDetailPage stockinDetailPage) {
		return stockinDetailEntityDao.get(StockinDetailEntity.class, stockinDetailPage.getStockinbillno());
	}

	public StockinDetailEntity get(java.lang.String stockinbillno) {
		return stockinDetailEntityDao.get(StockinDetailEntity.class, stockinbillno);
	}
	public List<StockinDetailEntity> listAll(StockinDetailPage stockinDetailPage) {
		String hql = "from StockinDetailEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinDetailPage, hql, values);
		List<StockinDetailEntity> list = stockinDetailEntityDao.find(hql,values);
		return list;
	}
}
