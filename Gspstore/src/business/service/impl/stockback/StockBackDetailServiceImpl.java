package business.service.impl.stockback;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.stockback.StockBackDetailEntity;
import business.page.stockback.StockBackDetailPage;
import business.service.stockback.StockBackDetailServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 购进退货明细
 * @author zhangdaihao
 * @date 2013-05-17 17:21:07
 * @version V1.0   
 *
 */
@Service("stockBackDetailService")
public class StockBackDetailServiceImpl extends BaseServiceImpl implements StockBackDetailServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<StockBackDetailEntity> stockBackDetailEntityDao;

	public BaseDaoI<StockBackDetailEntity> getStockBackDetailEntityDao() {
		return stockBackDetailEntityDao;
	}
	@Autowired
	public void setStockBackDetailEntityDao(BaseDaoI<StockBackDetailEntity> stockBackDetailEntityDao) {
		this.stockBackDetailEntityDao = stockBackDetailEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(StockBackDetailPage stockBackDetailPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockBackDetailPage)));
		j.setTotal(total(stockBackDetailPage));
		return j;
	}

	private List<StockBackDetailPage> getPagesFromEntitys(List<StockBackDetailEntity> stockBackDetailEntitys) {
		List<StockBackDetailPage> stockBackDetailPages = new ArrayList<StockBackDetailPage>();
		if (stockBackDetailEntitys != null && stockBackDetailEntitys.size() > 0) {
			for (StockBackDetailEntity tb : stockBackDetailEntitys) {
				StockBackDetailPage b = new StockBackDetailPage();
				BeanUtils.copyProperties(tb, b);
				stockBackDetailPages.add(b);
			}
		}
		return stockBackDetailPages;
	}

	private List<StockBackDetailEntity> find(StockBackDetailPage stockBackDetailPage) {
		String hql = "from StockBackDetailEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackDetailPage, hql, values);

		if (stockBackDetailPage.getSort() != null && stockBackDetailPage.getOrder() != null) {
			hql += " order by " + stockBackDetailPage.getSort() + " " + stockBackDetailPage.getOrder();
		}
		return stockBackDetailEntityDao.find(hql, stockBackDetailPage.getPage(), stockBackDetailPage.getRows(), values);
	}

	private Long total(StockBackDetailPage stockBackDetailPage) {
		String hql = "select count(*) from StockBackDetailEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackDetailPage, hql, values);
		return stockBackDetailEntityDao.count(hql, values);
	}

	private String addWhere(StockBackDetailPage stockBackDetailPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		StockBackDetailEntity stockBackDetailEntity = new StockBackDetailEntity();
		BeanUtils.copyProperties(stockBackDetailPage, stockBackDetailEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockBackDetailEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(StockBackDetailPage stockBackDetailPage) {
		StockBackDetailEntity t = new StockBackDetailEntity();
		BeanUtils.copyProperties(stockBackDetailPage, t);
		stockBackDetailEntityDao.save(t);
	}

	public void update(StockBackDetailPage stockBackDetailPage) throws Exception {
		StockBackDetailEntity t = stockBackDetailEntityDao.get(StockBackDetailEntity.class, stockBackDetailPage.getStockbackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockBackDetailPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockBackDetailEntity where stockbackno = '"+id+"'";
				StockBackDetailEntity t = stockBackDetailEntityDao.get(hql);
				if (t != null) {
					stockBackDetailEntityDao.delete(t);
				}
			}
		}
	}

	public StockBackDetailEntity get(StockBackDetailPage stockBackDetailPage) {
		return stockBackDetailEntityDao.get(StockBackDetailEntity.class, stockBackDetailPage.getStockbackno());
	}

	public StockBackDetailEntity get(java.lang.String stockbackno) {
		return stockBackDetailEntityDao.get(StockBackDetailEntity.class, stockbackno);
	}
	public List<StockBackDetailEntity> listAll(StockBackDetailPage stockBackDetailPage) {
		String hql = "from StockBackDetailEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackDetailPage, hql, values);
		List<StockBackDetailEntity> list = stockBackDetailEntityDao.find(hql,values);
		return list;
	}
}
