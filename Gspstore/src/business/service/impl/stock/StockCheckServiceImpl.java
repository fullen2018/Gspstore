package business.service.impl.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.stock.StockCheckEntity;
import business.entity.stock.StockEntity;
import business.page.article.ArticlePage;
import business.page.depot.DepotPage;
import business.page.report.ReportQueryForm;
import business.page.stock.StockCheckPage;
import business.service.article.ArticleServiceI;
import business.service.depot.DepotServiceI;
import business.service.stock.StockCheckServiceI;
import business.service.stock.StockServiceI;
import business.util.FormatUtil;
import business.util.MapUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 库存盘点
 * @author zhangdaihao
 * @date 2013-05-16 23:47:31
 * @version V1.0   
 *
 */
@Service("stockCheckService")
public class StockCheckServiceImpl extends BaseServiceImpl implements StockCheckServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<StockCheckEntity> stockCheckEntityDao;
	@Autowired
	private StockServiceI stockService;
	@Autowired
    private ArticleServiceI articleService;
    @Autowired
    private DepotServiceI depotService;
	public BaseDaoI<StockCheckEntity> getStockCheckEntityDao() {
		return stockCheckEntityDao;
	}
	@Autowired
	public void setStockCheckEntityDao(BaseDaoI<StockCheckEntity> stockCheckEntityDao) {
		this.stockCheckEntityDao = stockCheckEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(StockCheckPage stockCheckPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockCheckPage)));
		j.setTotal(total(stockCheckPage));
		return j;
	}

	private List<StockCheckPage> getPagesFromEntitys(List<StockCheckEntity> stockCheckEntitys) {
		List<StockCheckPage> stockCheckPages = new ArrayList<StockCheckPage>();
		if (stockCheckEntitys != null && stockCheckEntitys.size() > 0) {
			for (StockCheckEntity tb : stockCheckEntitys) {
				StockCheckPage b = new StockCheckPage();
				ArticlePage ap = new ArticlePage();
                BeanUtils.copyProperties(articleService.get(tb.getArticleid()),ap);
                b.setArticlepage(ap);
                DepotPage dp = new DepotPage();
                BeanUtils.copyProperties(depotService.get(tb.getDepotid()), dp);
                b.setDepotpage(dp);
				BeanUtils.copyProperties(tb, b);
				stockCheckPages.add(b);
			}
		}
		return stockCheckPages;
	}

	private List<StockCheckEntity> find(StockCheckPage stockCheckPage) {
		String hql = "from StockCheckEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockCheckPage, hql, values);

		if (stockCheckPage.getSort() != null && stockCheckPage.getOrder() != null) {
			hql += " order by " + stockCheckPage.getSort() + " " + stockCheckPage.getOrder();
		}
		return stockCheckEntityDao.find(hql, stockCheckPage.getPage(), stockCheckPage.getRows(), values);
	}

	private Long total(StockCheckPage stockCheckPage) {
		String hql = "select count(*) from StockCheckEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockCheckPage, hql, values);
		return stockCheckEntityDao.count(hql, values);
	}

	private String addWhere(StockCheckPage stockCheckPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		StockCheckEntity stockCheckEntity = new StockCheckEntity();
		BeanUtils.copyProperties(stockCheckPage, stockCheckEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockCheckEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(StockCheckPage stockCheckPage) {
		StockCheckEntity t = new StockCheckEntity();
		StockEntity se = stockService.get(stockCheckPage.getStockkey());
		t.setArticleid(se.getArticleid());
		t.setComputernum(se.getNum());
		t.setCostprice(se.getCostprice());
		t.setSerial(se.getSerial());
		t.setExpiredate(se.getExpiredate());
		t.setDepotid(se.getDepotid());
		t.setRealnum(stockCheckPage.getRealnum());
		t.setCreatedate(new Date());
		t.setModifydate(new Date());
		Integer num = getMaxStockChecktNum();
        t.setOrdernum(num);
        t.setStockcheckcode(DateUtils.FormatDate(new Date(),"yyyyMMdd")+FormatUtil.convertIntToString(4, num));
		stockCheckEntityDao.save(t);
		se.setNum(stockCheckPage.getRealnum());
	}

	public void update(StockCheckPage stockCheckPage) throws Exception {
		StockCheckEntity t = stockCheckEntityDao.get(StockCheckEntity.class, stockCheckPage.getStockcheckcode());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockCheckPage, t);
		}
	}
	public Integer getMaxStockChecktNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_kc_stock_check ae ";
        Map<String, Object> map = stockCheckEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockCheckEntity where stockcheckcode = '"+id+"'";
				StockCheckEntity t = stockCheckEntityDao.get(hql);
				if (t != null) {
					stockCheckEntityDao.delete(t);
				}
			}
		}
	}

	public StockCheckEntity get(StockCheckPage stockCheckPage) {
		return stockCheckEntityDao.get(StockCheckEntity.class, stockCheckPage.getStockcheckcode());
	}

	public StockCheckEntity get(java.lang.String stockcheckcode) {
		return stockCheckEntityDao.get(StockCheckEntity.class, stockcheckcode);
	}
	public List<StockCheckEntity> listAll(StockCheckPage stockCheckPage) {
		String hql = "from StockCheckEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockCheckPage, hql, values);
		List<StockCheckEntity> list = stockCheckEntityDao.find(hql,values);
		return list;
	}
    public StockServiceI getStockService()
    {
        return stockService;
    }
    public void setStockService(StockServiceI stockService)
    {
        this.stockService = stockService;
    }
    public List<Map<String, Object>> viewStockCheckData(
        ReportQueryForm stockCheckPage){
        List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        List<Object> values = new ArrayList<Object>();
        String sql = " select k.depotid,d.depotname,k.stockcheckcode" +
        " ,k.createdate,k.crtuser,k.crtusername,a.articleid,a.articlename,a.factory," +
        " p.param_name as unitname,a.fileno,a.articlespec,a.retailprice,a.pfprice," +
        "k.computernum,k.realnum,k.costprice,k.serial,k.expiredate,(k.realnum-k.computernum) as lostnum" +
        " ,(k.realnum-k.computernum)*k.costprice as lostamount " +
        " from  t_kc_stock_check k " +
        " inner join t_xx_depot d on d.depotid=k.depotid " +
        " inner join t_xx_article a on a.articleid=k.articleid " +
        " left join jeecg_dict_param p on p.param_level='003' and p.param_value=a.unit" +
        "" +
        " where 1=1 " +
        "";
        if (stockCheckPage.getArticleid()!=null&&!stockCheckPage.getArticleid().equals("")){
            sql += " and k.articleid='"+stockCheckPage.getArticleid()+"' ";
        }
        if (stockCheckPage.getSerial()!=null&&!stockCheckPage.getSerial().equals("")){
            sql += " and k.serial='"+stockCheckPage.getSerial()+"' ";
        }
        if (stockCheckPage.getDepotid()!=null&&!stockCheckPage.getDepotid().equals("")){
            sql += " and k.depotid='"+stockCheckPage.getDepotid()+"' ";
        }
        if (stockCheckPage.getCrtuser()!=null&&!stockCheckPage.getCrtuser().equals("")){
            sql += " and k.crtuser='"+stockCheckPage.getCrtuser()+"' ";
        }
        if (stockCheckPage.getBegindate() != null&&!stockCheckPage.getBegindate().equals("")) {
            sql += " and k.createdate>='"+DateUtils.FormatDate(stockCheckPage.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (stockCheckPage.getEnddate() != null&&!stockCheckPage.getEnddate().equals("")) {
            sql += " and k.createdate<='"+DateUtils.FormatDate(stockCheckPage.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by k.articleid,k.serial,k.createdate desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
    }
}
