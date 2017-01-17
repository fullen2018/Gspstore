package business.service.impl.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.stock.StockEntity;
import business.page.article.ArticlePage;
import business.page.depot.DepotPage;
import business.page.stock.StockPage;
import business.service.article.ArticleServiceI;
import business.service.depot.DepotServiceI;
import business.service.stock.StockServiceI;
import business.util.MapUtil;

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
 * @Description: 库存信息
 * @author zhangdaihao
 * @date 2013-05-16 21:42:56
 * @version V1.0   
 *
 */
@Service("stockService")
public class StockServiceImpl extends BaseServiceImpl implements StockServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<StockEntity> stockEntityDao;
	@Autowired
	private ArticleServiceI articleService;
	@Autowired
	private DepotServiceI depotService;

	public BaseDaoI<StockEntity> getStockEntityDao() {
		return stockEntityDao;
	}
	@Autowired
	public void setStockEntityDao(BaseDaoI<StockEntity> stockEntityDao) {
		this.stockEntityDao = stockEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagridDetail(StockPage stockPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockPage)));
		j.setTotal(total(stockPage));
		return j;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagridTotal(StockPage stockPage) {
	    DataGrid j = new DataGrid();
	    j.setRows(this.findTotal(stockPage));
	    j.setTotal(totalForTotal(stockPage));
	    return j;
	}

	private List<StockPage> getPagesFromEntitys(List<StockEntity> stockEntitys) {
		List<StockPage> stockPages = new ArrayList<StockPage>();
		if (stockEntitys != null && stockEntitys.size() > 0) {
			for (StockEntity tb : stockEntitys) {
				StockPage b = new StockPage();
				BeanUtils.copyProperties(tb, b);
				ArticlePage ap = new ArticlePage();
				BeanUtils.copyProperties(articleService.get(tb.getArticleid()),ap);
				b.setArticlepage(ap);
				DepotPage dp = new DepotPage();
				BeanUtils.copyProperties(depotService.get(tb.getDepotid()), dp);
				b.setDepotpage(dp);
				stockPages.add(b);
			}
		}
		return stockPages;
	}

	private List<StockEntity> find(StockPage stockPage) {
		String hql = "from StockEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhereForDetail(stockPage, hql, values);
        
		if (stockPage.getSort() != null && stockPage.getOrder() != null) {
			hql += " order by " + stockPage.getSort() + " " + stockPage.getOrder();
		}
		return stockEntityDao.find(hql, stockPage.getPage(), stockPage.getRows(), values);
	}

	private List<Map<String,Object>> findTotal(StockPage stockPage){
	    String sql = "select a.*,t.num,d.depotname,t.amount,t.depotid " +
	    		" from (" +
	    		"  select s.depotid,s.articleid,sum(s.num) as num ,sum(s.num*s.costprice) amount" +
	    		"    from t_kc_stock s " +
	    		"    group by s.depotid,s.articleid " +
	    		" ) t " +
	    		" inner join t_xx_article a on a.articleid=t.articleid " +
	    		" inner join t_xx_depot d on d.depotid=t.depotid  " +
	    		" where 1=1 ";
	    sql += this.addWhereForTotal(stockPage);
	    if (stockPage.getSort() != null && stockPage.getOrder() != null) {
            sql += " order by " + stockPage.getSort() + " " + stockPage.getOrder();
        }
	    List<Map<String,Object>> rows = 
	        MapUtil.changeMapListToLower(jdbcDao.findForJdbcParam(sql, stockPage.getPage(), stockPage.getRows()));
	    return rows;
	}
	
	public List<StockEntity> findStockByHql(String hql, Object... param){
	     return stockEntityDao.find(hql, param);
	}
	
	private Long total(StockPage stockPage) {
		String hql = "select count(*) from StockEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhereForDetail(stockPage, hql, values);
		return stockEntityDao.count(hql, values);
	}
	private Long totalForTotal(StockPage stockPage) {
	    String sql = "select count(*) as totalnum " +
        " from (" +
        "  select s.depotid,s.articleid,sum(s.num) as num " +
        "    from t_kc_stock s " +
        "    group by s.depotid,s.articleid " +
        " ) t " +
        //" inner join t_xx_article a on a.articleid=t.articleid " +
        //" inner join t_xx_depot d on d.depotid=t.depotid  " +
        " where 1=1 ";
	    sql += this.addWhereForTotal(stockPage);
       long totalnum = 0;
       Map<String, Object> map = stockEntityDao.findOneForJdbc(sql, null);
       Object obj = map.get("totalnum");
       if (obj!=null&&!obj.equals("")){
           totalnum =Long.valueOf(obj.toString());
       }
       return totalnum;
	}

	private String addWhereForDetail(StockPage stockPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		StockEntity stockEntity = new StockEntity();
		BeanUtils.copyProperties(stockPage, stockEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockEntity);
		if (stockPage.getNum()!=null&&stockPage.getNum().compareTo(new BigDecimal(0))>0){
		    hqlbf.append(" and num <= ? ");
            values.add(stockPage.getNum());
        }
        if (stockPage.getCostprice()!=null&&stockPage.getCostprice().compareTo(new BigDecimal(0))>0){
            hqlbf.append(" and costprice >= ? ");
            values.add(stockPage.getCostprice());
        }
		if (stockPage.getCcreatedatetimeStart()!=null){
            hqlbf.append(" and expiredate <= ? ");
            values.add(stockPage.getCcreatedatetimeStart());
        }
		hql = hqlbf.toString();
		 
		return hql;
	}
	private String addWhereForTotal(StockPage stockPage) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
	    //-----------------------------------------------------
	    StringBuffer hqlbf = new StringBuffer();
	    if (stockPage.getArticleid()!=null&&!stockPage.getArticleid().equals("")){
	        hqlbf.append(" and t.articleid='"+stockPage.getArticleid()+"' ");
	    }
	    if (stockPage.getDepotid()!=null&&!stockPage.getDepotid().equals("")){
	        hqlbf.append(" and t.depotid='"+stockPage.getDepotid()+"' ");
	    }
	    if (stockPage.getNum()!=null&&!stockPage.getNum().equals("")){
	        hqlbf.append(" and t.num<="+stockPage.getNum()+" ");
	    }else{
	        hqlbf.append(" and t.num>0 ");
	    }
	   
	    return hqlbf.toString();
	}

	public void add(StockPage stockPage) {
		StockEntity t = new StockEntity();
		BeanUtils.copyProperties(stockPage, t);
		stockEntityDao.save(t);
	}

	public void update(StockPage stockPage) throws Exception {
		StockEntity t = stockEntityDao.get(StockEntity.class, stockPage.getStockkey());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockEntity where stockkey = '"+id+"'";
				StockEntity t = stockEntityDao.get(hql);
				if (t != null) {
					stockEntityDao.delete(t);
				}
			}
		}
	}

	public StockEntity get(StockPage stockPage) {
		return stockEntityDao.get(StockEntity.class, stockPage.getStockkey());
	}

	public StockEntity get(java.lang.String stockkey) {
		return stockEntityDao.get(StockEntity.class, stockkey);
	}
	public List<StockEntity> listAll(StockPage stockPage) {
		String hql = "from StockEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhereForDetail(stockPage, hql, values);
		List<StockEntity> list = stockEntityDao.find(hql,values);
		return list;
	}
    public ArticleServiceI getArticleService()
    {
        return articleService;
    }
    public void setArticleService(ArticleServiceI articleService)
    {
        this.articleService = articleService;
    }
    public DepotServiceI getDepotService()
    {
        return depotService;
    }
    public void setDepotService(DepotServiceI depotService)
    {
        this.depotService = depotService;
    }
}
