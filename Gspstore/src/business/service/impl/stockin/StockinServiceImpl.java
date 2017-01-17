package business.service.impl.stockin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.article.ArticleEntity;
import business.entity.stock.StockEntity;
import business.entity.stockin.StockinEntity;
import business.page.article.ArticlePage;
import business.page.depot.DepotPage;
import business.page.report.ReportQueryForm;
import business.page.stockin.StockinPage;
import business.service.article.ArticleServiceI;
import business.service.depot.DepotServiceI;
import business.service.stock.StockServiceI;
import business.service.stockin.StockinServiceI;
import business.service.supplier.SupplierServiceI;
import business.util.FormatUtil;
import business.util.MapUtil;
import business.page.stockin.StockinDetailPage;
import business.page.supplier.SupplierPage;
import business.entity.stockin.StockinDetailEntity;

import com.jeecg.pageModel.SessionInfo;
import com.jeecg.pageModel.TreeNode;
import com.sys.page.base.DictParamPage;
import com.sys.service.base.DictParamServiceI;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.jeecg.util.RequestUtil;
import com.jeecg.util.ResourceUtil;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;/**   
 * @Title: ServiceImpl
 * @Description: 入库主表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:40
 * @version V1.0   
 *
 */
@Service("stockinService")
public class StockinServiceImpl extends BaseServiceImpl implements StockinServiceI {

	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	private BaseDaoI<StockinEntity> stockinEntityDao;
	@Autowired
	private BaseDaoI<StockEntity> stockEntityDao;
	@Autowired
	private BaseDaoI<StockinDetailEntity> stockinDetailEntityDao;
	@Autowired
	private SupplierServiceI supplierService;
	@Autowired
	private DepotServiceI depotService;
	@Autowired
	private StockServiceI stockService;
	@Autowired
	private ArticleServiceI articleService;
	@Autowired
    private DictParamServiceI dictParamService;
	public BaseDaoI<StockinEntity> getStockinEntityDao() {
		return stockinEntityDao;
	}
	@Autowired
	public void setStockinEntityDao(BaseDaoI<StockinEntity> stockinEntityDao) {
		this.stockinEntityDao = stockinEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(StockinPage stockinPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockinPage)));
		j.setTotal(total(stockinPage));
		return j;
	}

	private List<StockinPage> getPagesFromEntitys(List<StockinEntity> stockinEntitys) {
		List<StockinPage> stockinPages = new ArrayList<StockinPage>();
		if (stockinEntitys != null && stockinEntitys.size() > 0) {
			for (StockinEntity tb : stockinEntitys) {
				StockinPage b = new StockinPage();
				BeanUtils.copyProperties(tb, b);
				//获得供应商
				SupplierPage sp = new SupplierPage();
				BeanUtils.copyProperties(supplierService.get(tb.getSupplierid()),sp );
				b.setSupplierpage(sp);
				//获得仓库
				DepotPage dp = new DepotPage();
				BeanUtils.copyProperties(depotService.get(tb.getDepotid()),dp);
				b.setDepotpage(dp);
				stockinPages.add(b);
			}
		}
		return stockinPages;
	}

	private List<StockinEntity> find(StockinPage stockinPage) {
		String hql = "from StockinEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinPage, hql, values);

		if (stockinPage.getSort() != null && stockinPage.getOrder() != null) {
			hql += " order by " + stockinPage.getSort() + " " + stockinPage.getOrder();
		}
		return stockinEntityDao.find(hql, stockinPage.getPage(), stockinPage.getRows(), values);
	}

	private Long total(StockinPage stockinPage) {
		String hql = "select count(*) from StockinEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinPage, hql, values);
		return stockinEntityDao.count(hql, values);
	}

	private String addWhere(StockinPage stockinPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		StockinEntity stockinEntity = new StockinEntity();
		BeanUtils.copyProperties(stockinPage, stockinEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockinEntity);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (stockinPage.getCcreatedatetimeStart() != null) {
			hql += " and createdate>=? ";
			values.add(stockinPage.getCcreatedatetimeStart());
		}
		if (stockinPage.getCcreatedatetimeEnd() != null) {
			hql += " and createdate<=? ";
			values.add(stockinPage.getCcreatedatetimeEnd());
		}
		if (stockinPage.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(stockinPage.getCmodifydatetimeStart());
		}
		if (stockinPage.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(stockinPage.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(StockinPage stockinPage) {
		StockinEntity t = new StockinEntity();
		BeanUtils.copyProperties(stockinPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxStockinNum();
        t.setOrdernum(num);
        t.setStockinbillno(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(4, num));
        t.setAmount(new BigDecimal(0));
        t.setIspayed(2);
		stockinEntityDao.save(t);
	}

	
	/**
	 * 保存：一对多
	 */
	public void addMain(StockinPage stockinPage,List<StockinDetailPage> stockinDetailList)  throws Exception{
		//[1].主表保存
		StockinEntity t = new StockinEntity();
		BeanUtils.copyProperties(stockinPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxStockinNum();
        t.setOrdernum(num);
        t.setStockinbillno(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(4, num));
        t.setIspayed(2);
		//[2].明细数据保存
		//入库明细表
		BigDecimal totalamount = new BigDecimal(0);
		for(StockinDetailPage page:stockinDetailList){
			StockinDetailEntity stockinDetail = new StockinDetailEntity();
			BeanUtils.copyProperties(page, stockinDetail);
			
			//外键设置
			stockinDetail.setStockinbillno(t.getStockinbillno());
			totalamount = totalamount.add((stockinDetail.getNum().multiply(stockinDetail.getPrice())));
			stockinDetail.setAmount(stockinDetail.getNum().multiply(stockinDetail.getPrice()));
			stockinDetail.setBacknum(new BigDecimal(0));
			stockinDetailEntityDao.save(stockinDetail);
			String hql = " from StockEntity where articleid=? and depotid=? and serial=? ";
			List<StockEntity> stockList = stockEntityDao.find(hql, page.getArticleid(),t.getDepotid(),page.getSerial());
			StockEntity stock = null;
			if (stockList!=null&&stockList.size()>0){
			    stock = stockList.get(0);
			    BigDecimal stockmoney = stock.getNum().multiply(stock.getCostprice());
			    BigDecimal newmoney =   page.getNum().multiply(stockinDetail.getPrice());
			    BigDecimal newtotal =   stock.getNum().add(page.getNum());
			    BigDecimal newcostprice = stockmoney.add(newmoney).divide(newtotal,2,RoundingMode.HALF_UP);
			    stock.setCostprice(newcostprice);
			    stock.setNum(stock.getNum().add(page.getNum()));
			}else{
			    stock = new StockEntity();
			    stock.setArticleid(page.getArticleid());
                stock.setDepotid(t.getDepotid());
                stock.setExpiredate(page.getExpiredate());
                stock.setNum(page.getNum());
                stock.setSerial(page.getSerial());
                stock.setCostprice(page.getPrice());
			}
			ArticleEntity ae = articleService.get(stockinDetail.getArticleid());
			ae.setLastinprice(stockinDetail.getPrice());
			stockEntityDao.save(stock);
		}
		t.setAmount(totalamount);
		t.setStockbacknoitems("");
        stockinEntityDao.save(t);
	}
	/**
	 * 修改：一对多
	 */
	public void editMain(StockinPage stockinPage,List<StockinDetailPage> stockinDetailList)  throws Exception{
		//[1].主表保存
		StockinEntity t = stockinEntityDao.get(StockinEntity.class, stockinPage.getStockinbillno());
	    BigDecimal totalamount = new BigDecimal(0);
	    
	    //获取参数
		Object stockinbillno0 = stockinPage.getStockinbillno();
	    
	    //-------------------------------------------------------------------
	    //[1].查询明细入库明细表
	    String hql0 = "from StockinDetailEntity where 1 = 1 AND stockinbillno = ? ";
	    List<StockinDetailEntity> stockinDetailOldList = stockinDetailEntityDao.find(hql0,stockinbillno0);
	    
	    //[2].删除明细入库明细表
		String delhql0 = "delete from StockinDetailEntity where 1 = 1 AND stockinbillno = ? ";
		stockinEntityDao.executeHql(delhql0,stockinbillno0);
		//-------------------------------------------------------------------
		//[3].明细数据保存
		//入库明细表
		StockinDetailEntity stockinDetailEntity = null;
		for(StockinDetailPage page:stockinDetailList){
			for(StockinDetailEntity c:stockinDetailOldList){
				if(c.getStockinbillno().equals(page.getStockinbillno())){
					stockinDetailEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(stockinDetailEntity==null){
				stockinDetailEntity = new StockinDetailEntity();
			}
			stockinDetailEntityDao.evict(stockinDetailEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, stockinDetailEntity);
			//外键设置
			stockinDetailEntity.setStockinbillno(t.getStockinbillno());
			    
			SearchSqlGenerateUtil.setUpdateMessage(stockinDetailEntity);
			totalamount = totalamount.add((stockinDetailEntity.getNum().multiply(stockinDetailEntity.getPrice())));
			stockinDetailEntityDao.save(stockinDetailEntity);
			stockinDetailEntity = null;
			//-----------------------------------------------------
			
		}
		 if(t != null) {
	            MyBeanUtils.copyBeanNotNull2Bean(stockinPage, t);
	            t.setAmount(totalamount);
	     }
	}
	
	
	public void update(StockinPage stockinPage) throws Exception {
		StockinEntity t = stockinEntityDao.get(StockinEntity.class, stockinPage.getStockinbillno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockinPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockinEntity where stockinbillno = '"+id+"'";
				StockinEntity t = stockinEntityDao.get(hql);
				if (t != null) {
					stockinEntityDao.delete(t);
						//获取参数
					    Object stockinbillno0 = t.getStockinbillno();
					    //删除明细入库明细表
						String delhql0 = "delete from StockinDetailEntity where 1 = 1 AND stockinbillno = ? ";
						stockinEntityDao.executeHql(delhql0,stockinbillno0);
				}
			}
		}
	}

	public StockinEntity get(StockinPage stockinPage) {
		return stockinEntityDao.get(StockinEntity.class, stockinPage.getStockinbillno());
	}

	public StockinEntity get(java.lang.String stockinbillno) {
		return stockinEntityDao.get(StockinEntity.class, stockinbillno);
	}
	public List<StockinEntity> listAll(StockinPage stockinPage) {
		String hql = "from StockinEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockinPage, hql, values);
		List<StockinEntity> list = stockinEntityDao.find(hql,values);
		return list;
	}
	
	/**根据主表Key,查询子表明细：入库明细表*/
	public List<StockinDetailPage> getStockinDetailListByFkey(java.lang.String stockinbillno) {
		
		List<StockinDetailPage> rs = new ArrayList<StockinDetailPage>();
		String hql = "from StockinDetailEntity where 1 = 1 AND stockinbillno = ? ";
		List<StockinDetailEntity> list = stockinDetailEntityDao.find(hql,stockinbillno);
		
		for(StockinDetailEntity po:list){
			StockinDetailPage page = new StockinDetailPage();
			BeanUtils.copyProperties(po, page);
			ArticlePage ap = new ArticlePage();
			BeanUtils.copyProperties(articleService.get(po.getArticleid()), ap);
			DictParamPage dpp = new DictParamPage();
            BeanUtils.copyProperties(dictParamService.getDictName("003",ap.getUnit()), dpp);
            ap.setUnitdic(dpp);
			page.setArticlepage(ap);
			rs.add(page);
		}
		return rs;
	}
	
	public Integer getMaxStockinNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_rk_stockin ae " +
        " where date_format(ae.createdate,'%Y-%m-%d')='"+DateUtils.FormatDate(new Date(), "yyyy-MM-dd")+"' ";
        Map<String, Object> map = stockinEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
	/**
	 * 采购汇总统计报表
	 * @param stockinPage
	 * @return
	 */
	public List<Map<String, Object>> viewStockinTotalList(
	    ReportQueryForm stockinPage){
	    List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
	    List<Object> values = new ArrayList<Object>();
	    String sql = " select t.supplierid,s.suppliername,t.depotid,d.depotname,t.stockinbillno" +
	    		" ,t.stockindate,t.createdate,t.crtuser,t.crtusername,t.amount," +
	    		"case when bk.backamount is null then 0 else bk.backamount end as backamount " +
	    		" from  t_rk_stockin t " +
	    		" inner join t_xx_depot d on d.depotid=t.depotid " +
	    		" inner join t_xx_supplier s on s.supplierid=t.supplierid " +
	    		" left join (select  t.stockinno,sum(t.amount)  as backamount from t_th_stockback t group by t.stockinno ) bk on bk.stockinno=t.stockinbillno" +
	    		" where 1=1 " +
	    		"";
	    if (stockinPage.getSupplierid()!=null&&!stockinPage.getSupplierid().equals("")){
	        sql += " and t.supplierid='"+stockinPage.getSupplierid()+"' ";
	    }
	    if (stockinPage.getDepotid()!=null&&!stockinPage.getDepotid().equals("")){
	        sql += " and t.depotid='"+stockinPage.getDepotid()+"' ";
	    }
	    if (stockinPage.getCrtuser()!=null&&!stockinPage.getCrtuser().equals("")){
	        sql += " and t.crtuser='"+stockinPage.getCrtuser()+"' ";
	    }
	    if (stockinPage.getBegindate() != null&&!stockinPage.getBegindate().equals("")) {
	        sql += " and t.createdate>='"+DateUtils.FormatDate(stockinPage.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (stockinPage.getEnddate() != null&&!stockinPage.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(stockinPage.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by t.supplierid,t.createdate desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
	    return viewData;
	}
	/**
	 * 采购汇明细计报表
	 * @param stockinPage
	 * @return
	 */
	public List<Map<String, Object>> viewStockinDetailList(ReportQueryForm stockinPage){
	    List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
	    List<Object> values = new ArrayList<Object>();
	    String sql = " select t.supplierid,s.suppliername,t.depotid,d.depotname,t.stockinbillno" +
	    " ,t.stockindate,t.createdate,t.crtuser,t.crtusername,t.amount,a.articleid,a.articlename,a.factory," +
	    " p.param_name as unitname,a.fileno,a.articlespec,a.retailprice,a.pfprice,k.num,k.price,k.serial,k.expiredate" +
	    " ,k.backnum,k.backnum*k.price as backamount,k.amount " +
	    " from  t_rk_stockin t " +
	    " inner join t_rk_stockin_detail k on k.stockinbillno=t.stockinbillno " +
	    " inner join t_xx_depot d on d.depotid=t.depotid " +
	    " inner join t_xx_supplier s on s.supplierid=t.supplierid " +
	    " inner join t_xx_article a on a.articleid=k.articleid " +
	    " left join jeecg_dict_param p on p.param_level='003' and p.param_value=a.unit" +
	    "" +
	    " where 1=1 " +
	    "";
	    if (stockinPage.getArticleid()!=null&&!stockinPage.getArticleid().equals("")){
	        sql += " and k.articleid='"+stockinPage.getArticleid()+"' ";
	    }
	    if (stockinPage.getSerial()!=null&&!stockinPage.getSerial().equals("")){
	        sql += " and k.serial='"+stockinPage.getSerial()+"' ";
	    }
	    if (stockinPage.getSupplierid()!=null&&!stockinPage.getSupplierid().equals("")){
	        sql += " and t.supplierid='"+stockinPage.getSupplierid()+"' ";
	    }
	    if (stockinPage.getDepotid()!=null&&!stockinPage.getDepotid().equals("")){
	        sql += " and t.depotid='"+stockinPage.getDepotid()+"' ";
	    }
	    if (stockinPage.getCrtuser()!=null&&!stockinPage.getCrtuser().equals("")){
	        sql += " and t.crtuser='"+stockinPage.getCrtuser()+"' ";
	    }
	    if (stockinPage.getBegindate() != null&&!stockinPage.getBegindate().equals("")) {
	        sql += " and t.createdate>='"+DateUtils.FormatDate(stockinPage.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
	    }
	    if (stockinPage.getEnddate() != null&&!stockinPage.getEnddate().equals("")) {
	        sql += " and t.createdate<='"+DateUtils.FormatDate(stockinPage.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
	    }
	    sql += " order by t.supplierid,k.articleid,k.serial,t.createdate desc";
	    viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
	    return viewData;
	}
    public SupplierServiceI getSupplierService()
    {
        return supplierService;
    }
    public void setSupplierService(SupplierServiceI supplierService)
    {
        this.supplierService = supplierService;
    }
    public DepotServiceI getDepotService()
    {
        return depotService;
    }
    public void setDepotService(DepotServiceI depotService)
    {
        this.depotService = depotService;
    }
    public StockServiceI getStockService()
    {
        return stockService;
    }
    public void setStockService(StockServiceI stockService)
    {
        this.stockService = stockService;
    }
}
