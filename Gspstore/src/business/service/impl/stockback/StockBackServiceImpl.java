package business.service.impl.stockback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.article.ArticleEntity;
import business.entity.stock.StockEntity;
import business.entity.stockback.StockBackDetailEntity;
import business.entity.stockback.StockBackEntity;
import business.entity.stockin.StockinDetailEntity;
import business.entity.stockin.StockinEntity;
import business.exception.StockLessBackNumException;
import business.page.article.ArticlePage;
import business.page.depot.DepotPage;
import business.page.stockback.StockBackDetailPage;
import business.page.stockback.StockBackPage;
import business.page.supplier.SupplierPage;
import business.service.article.ArticleServiceI;
import business.service.depot.DepotServiceI;
import business.service.stockback.StockBackServiceI;
import business.service.stockin.StockinDetailServiceI;
import business.service.stockin.StockinServiceI;
import business.service.supplier.SupplierServiceI;
import business.util.FormatUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.sys.page.base.DictParamPage;
import com.sys.service.base.DictParamServiceI;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
/**   
 * @Title: ServiceImpl
 * @Description: 购进退货单
 * @author zhangdaihao
 * @date 2013-05-17 17:21:11
 * @version V1.0   
 *
 */
@Service("stockBackService")
public class StockBackServiceImpl extends BaseServiceImpl implements StockBackServiceI {

	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	private BaseDaoI<StockBackEntity> stockBackEntityDao;
	@Autowired
    private SupplierServiceI supplierService;
    @Autowired
    private DepotServiceI depotService;
    @Autowired
    private BaseDaoI<StockEntity> stockEntityDao;
	@Autowired
	private BaseDaoI<StockBackDetailEntity> stockBackDetailEntityDao;
	@Autowired
    private ArticleServiceI articleService;
	@Autowired
	private StockinServiceI stockinService;
	@Autowired
	private StockinDetailServiceI stockinDetailService;
	@Autowired
    private DictParamServiceI dictParamService;
	public BaseDaoI<StockBackEntity> getStockBackEntityDao() {
		return stockBackEntityDao;
	}
	@Autowired
	public void setStockBackEntityDao(BaseDaoI<StockBackEntity> stockBackEntityDao) {
		this.stockBackEntityDao = stockBackEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(StockBackPage stockBackPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(stockBackPage)));
		j.setTotal(total(stockBackPage));
		return j;
	}

	private List<StockBackPage> getPagesFromEntitys(List<StockBackEntity> stockBackEntitys) {
		List<StockBackPage> stockBackPages = new ArrayList<StockBackPage>();
		if (stockBackEntitys != null && stockBackEntitys.size() > 0) {
			for (StockBackEntity tb : stockBackEntitys) {
				StockBackPage b = new StockBackPage();
				BeanUtils.copyProperties(tb, b);
				//获得供应商
	            SupplierPage sp = new SupplierPage();
	            BeanUtils.copyProperties(supplierService.get(tb.getSupplierid()),sp );
	            b.setSupplierpage(sp);
	            //获得仓库
	            DepotPage dp = new DepotPage();
	            BeanUtils.copyProperties(depotService.get(tb.getDepotid()),dp);
	            b.setDepotpage(dp);
				stockBackPages.add(b);
			}
		}
		return stockBackPages;
	}

	private List<StockBackEntity> find(StockBackPage stockBackPage) {
		String hql = "from StockBackEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackPage, hql, values);

		if (stockBackPage.getSort() != null && stockBackPage.getOrder() != null) {
			hql += " order by " + stockBackPage.getSort() + " " + stockBackPage.getOrder();
		}
		return stockBackEntityDao.find(hql, stockBackPage.getPage(), stockBackPage.getRows(), values);
	}

	private Long total(StockBackPage stockBackPage) {
		String hql = "select count(*) from StockBackEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackPage, hql, values);
		return stockBackEntityDao.count(hql, values);
	}

	private String addWhere(StockBackPage stockBackPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		StockBackEntity stockBackEntity = new StockBackEntity();
		BeanUtils.copyProperties(stockBackPage, stockBackEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, stockBackEntity);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (stockBackPage.getCcreatedatetimeStart() != null) {
			hql += " and createDt>=? ";
			values.add(stockBackPage.getCcreatedatetimeStart());
		}
		if (stockBackPage.getCcreatedatetimeEnd() != null) {
			hql += " and createDt<=? ";
			values.add(stockBackPage.getCcreatedatetimeEnd());
		}
		if (stockBackPage.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(stockBackPage.getCmodifydatetimeStart());
		}
		if (stockBackPage.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(stockBackPage.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(StockBackPage stockBackPage) {
		StockBackEntity t = new StockBackEntity();
		BeanUtils.copyProperties(stockBackPage, t);
		stockBackEntityDao.save(t);
	}

	public Integer getMaxStockBackNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_th_stockback ae " +
        		" where date_format(ae.createdate,'%Y-%m-%d')='"+DateUtils.FormatDate(new Date(), "yyyy-MM-dd")+"' ";
        Map<String, Object> map = stockBackEntityDao.findOneForJdbc(sql, null);
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
	 * 保存：一对多
	 */
	public void addMain(StockBackPage stockBackPage,List<StockBackDetailPage> stockBackDetailList)  throws Exception{
		//[1].主表保存
		StockBackEntity t = new StockBackEntity();
		BeanUtils.copyProperties(stockBackPage, t);
		Integer num = getMaxStockBackNum();
        t.setOrdernum(num);
        t.setStockbackno(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(3, num));
		t.setCreatedate(new Date());
		t.setModifydate(new Date());
        BigDecimal totalamount = new BigDecimal(0);
		//[2].明细数据保存
		//购进退货明细
        boolean flag = false;
		for(StockBackDetailPage page:stockBackDetailList){
		    if (page.getNum().intValue()<1) continue;
		    StockEntity stock = this.getStockByID(t.getDepotid(), page.getArticleid(), page.getSerial());
		    if (stock!=null&&stock.getNum().compareTo(page.getNum())>=0){
    			StockBackDetailEntity stockBackDetail = new StockBackDetailEntity();
    			BeanUtils.copyProperties(page, stockBackDetail);
    			BigDecimal tempamount = page.getNum().multiply(page.getPrice());
    			//外键设置
    			stockBackDetail.setStockbackno(t.getStockbackno());
    			stockBackDetail.setAmount(tempamount);
    			totalamount = totalamount.add(tempamount);
    			stockBackDetailEntityDao.save(stockBackDetail);
    			BigDecimal stockmoney = stock.getNum().multiply(stock.getCostprice());
                BigDecimal newmoney =   page.getNum().multiply(page.getPrice());
                BigDecimal newtotal =   stock.getNum().subtract(page.getNum());
                if (newtotal.compareTo(new BigDecimal(0))>0){
                   BigDecimal newcostprice = stockmoney.subtract(newmoney).divide(newtotal,2,RoundingMode.HALF_UP);
                   stock.setCostprice(newcostprice);
                }else{
                    stock.setCostprice(new BigDecimal(0));
                }
                stock.setNum(stock.getNum().subtract(page.getNum()));
    			StockinDetailEntity sde = stockinDetailService.get(page.getStockindetailno());
    			sde.setBacknum(sde.getBacknum().add(page.getNum()));
    			flag = true;
		    }else{
		        ArticleEntity ae = articleService.get(page.getArticleid());
		        String messge = ae.getArticlename()+"/"+ae.getArticlespec()+"/"+ae.getFactory()+
		                       "退货数量["+page.getNum()+"]大于库存剩余量["+stock.getNum()+"]";
		        throw new StockLessBackNumException(messge);
		    }
		}
		t.setAmount(totalamount);
		if (flag) {
		    stockBackEntityDao.save(t);
		    StockinEntity ss = stockinService.get(t.getStockinno());
		    String backitems = ss.getStockbacknoitems();
		    if (backitems!=null&&!backitems.equals("")){
		        ss.setStockbacknoitems(backitems+","+t.getStockbackno());
		    }else{
		        ss.setStockbacknoitems(t.getStockbackno());
		    }
		}
	}
	
	public StockEntity getStockByID(String depotid,String articleid,String serial){
	    String hql = " from StockEntity se where se.depotid=? and se.articleid=? and se.serial=? ";
	    List<StockEntity> listStock = stockEntityDao.find(hql, depotid,articleid,serial);
	    if (listStock!=null&&listStock.size()>0){
	        return listStock.get(0);
	    }else{
	        return null;
	    }
	}
	
	/**
	 * 修改：一对多
	 */
	public void editMain(StockBackPage stockBackPage,List<StockBackDetailPage> stockBackDetailList)  throws Exception{
		//[1].主表保存
		StockBackEntity t = stockBackEntityDao.get(StockBackEntity.class, stockBackPage.getStockbackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockBackPage, t);
		}
	    
	    
	    //获取参数
		Object stockbackno0 = stockBackPage.getStockbackno();
	    
	    //-------------------------------------------------------------------
	    //[1].查询明细购进退货明细
	    String hql0 = "from StockBackDetailEntity where 1 = 1 AND stockbackno = ? ";
	    List<StockBackDetailEntity> stockBackDetailOldList = stockBackDetailEntityDao.find(hql0,stockbackno0);
	    
	    //[2].删除明细购进退货明细
		String delhql0 = "delete from StockBackDetailEntity where 1 = 1 AND stockbackno = ? ";
		stockBackEntityDao.executeHql(delhql0,stockbackno0);
		//-------------------------------------------------------------------
		//[3].明细数据保存
		//购进退货明细
		StockBackDetailEntity stockBackDetailEntity = null;
		for(StockBackDetailPage page:stockBackDetailList){
			for(StockBackDetailEntity c:stockBackDetailOldList){
				if(c.getStockbackno().equals(page.getStockbackno())){
					stockBackDetailEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(stockBackDetailEntity==null){
				stockBackDetailEntity = new StockBackDetailEntity();
			}
			stockBackDetailEntityDao.evict(stockBackDetailEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, stockBackDetailEntity);
			//外键设置
			stockBackDetailEntity.setStockbackno(t.getStockbackno());
			    
			SearchSqlGenerateUtil.setUpdateMessage(stockBackDetailEntity);
			stockBackDetailEntityDao.save(stockBackDetailEntity);
			stockBackDetailEntity = null;
			//-----------------------------------------------------
		}
	}
	
	
	public void update(StockBackPage stockBackPage) throws Exception {
		StockBackEntity t = stockBackEntityDao.get(StockBackEntity.class, stockBackPage.getStockbackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(stockBackPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from StockBackEntity where stockbackno = '"+id+"'";
				StockBackEntity t = stockBackEntityDao.get(hql);
				if (t != null) {
					stockBackEntityDao.delete(t);
						//获取参数
					    Object stockbackno0 = t.getStockbackno();
					    //删除明细购进退货明细
						String delhql0 = "delete from StockBackDetailEntity where 1 = 1 AND stockbackno = ? ";
						stockBackEntityDao.executeHql(delhql0,stockbackno0);
				}
			}
		}
	}

	public StockBackEntity get(StockBackPage stockBackPage) {
		return stockBackEntityDao.get(StockBackEntity.class, stockBackPage.getStockbackno());
	}

	public StockBackEntity get(java.lang.String stockbackno) {
		return stockBackEntityDao.get(StockBackEntity.class, stockbackno);
	}
	public List<StockBackEntity> listAll(StockBackPage stockBackPage) {
		String hql = "from StockBackEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(stockBackPage, hql, values);
		List<StockBackEntity> list = stockBackEntityDao.find(hql,values);
		return list;
	}
	
	/**根据主表Key,查询子表明细：购进退货明细*/
	public List<StockBackDetailPage> getStockBackDetailListByFkey(java.lang.String stockbackno) {
		
		List<StockBackDetailPage> rs = new ArrayList<StockBackDetailPage>();
		String hql = "from StockBackDetailEntity where 1 = 1 AND stockbackno = ? ";
		List<StockBackDetailEntity> list = stockBackDetailEntityDao.find(hql,stockbackno);
		
		for(StockBackDetailEntity po:list){
			StockBackDetailPage page = new StockBackDetailPage();
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
}
