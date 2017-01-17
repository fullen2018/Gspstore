package business.service.impl.sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.article.ArticleEntity;
import business.entity.customerprice.CustomerPriceEntity;
import business.entity.sale.SaleDetailEntity;
import business.entity.sale.SaleEntity;
import business.entity.stock.StockEntity;
import business.exception.StockLessBackNumException;
import business.page.accounter.AccounterPage;
import business.page.article.ArticlePage;
import business.page.customer.CustomerPage;
import business.page.customerprice.CustomerPricePage;
import business.page.depot.DepotPage;
import business.page.report.ReportQueryForm;
import business.page.sale.SaleDetailPage;
import business.page.sale.SalePage;
import business.page.stockin.StockinDetailPage;
import business.service.accounter.AccounterServiceI;
import business.service.article.ArticleServiceI;
import business.service.customer.CustomerServiceI;
import business.service.customerprice.CustomerPriceServiceI;
import business.service.depot.DepotServiceI;
import business.service.sale.SaleServiceI;
import business.service.stock.StockServiceI;
import business.util.FormatUtil;
import business.util.MapUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.sys.entity.base.DictParamEntity;
import com.sys.page.base.DictParamPage;
import com.sys.service.base.DictParamServiceI;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
/**   
 * @Title: ServiceImpl
 * @Description: 销售单
 * @author zhangdaihao
 * @date 2013-05-19 10:15:00
 * @version V1.0   
 *
 */
@Service("saleService")
public class SaleServiceImpl extends BaseServiceImpl implements SaleServiceI {

	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	private BaseDaoI<SaleEntity> saleEntityDao;
	@Autowired
	private BaseDaoI<SaleDetailEntity> saleDetailEntityDao;
	@Autowired
    private DepotServiceI depotService;
    @Autowired
    private StockServiceI stockService;
    @Autowired
    private ArticleServiceI articleService;
    @Autowired
    private CustomerServiceI customerService;
    @Autowired
    private AccounterServiceI  accounterService;
    @Autowired
    private BaseDaoI<StockEntity> stockEntityDao;
    @Autowired
    private DictParamServiceI dictParamService;
    @Autowired
    private CustomerPriceServiceI customerPriceService;
	
	public BaseDaoI<SaleEntity> getSaleEntityDao() {
		return saleEntityDao;
	}
	@Autowired
	public void setSaleEntityDao(BaseDaoI<SaleEntity> saleEntityDao) {
		this.saleEntityDao = saleEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SalePage salePage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(salePage)));
		j.setTotal(total(salePage));
		return j;
	}

	private List<SalePage> getPagesFromEntitys(List<SaleEntity> saleEntitys) {
		List<SalePage> salePages = new ArrayList<SalePage>();
		if (saleEntitys != null && saleEntitys.size() > 0) {
			for (SaleEntity tb : saleEntitys) {
				SalePage b = new SalePage();
				BeanUtils.copyProperties(tb, b);
				//获得业务员
                AccounterPage ap = new AccounterPage();
                BeanUtils.copyProperties(accounterService.get(tb.getAccountid()),ap);
                b.setAccounterpage(ap);
                //获得客户
                CustomerPage cp = new CustomerPage();
                BeanUtils.copyProperties(customerService.get(tb.getCustomerid()), cp);
                b.setCustomerpage(cp);
				salePages.add(b);
			}
		}
		return salePages;
	}

	private List<SaleEntity> find(SalePage salePage) {
		String hql = "from SaleEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(salePage, hql, values);

		if (salePage.getSort() != null && salePage.getOrder() != null) {
			hql += " order by " + salePage.getSort() + " " + salePage.getOrder();
		}
		return saleEntityDao.find(hql, salePage.getPage(), salePage.getRows(), values);
	}

	private Long total(SalePage salePage) {
		String hql = "select count(*) from SaleEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(salePage, hql, values);
		return saleEntityDao.count(hql, values);
	}

	private String addWhere(SalePage salePage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		SaleEntity saleEntity = new SaleEntity();
		BeanUtils.copyProperties(salePage, saleEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, saleEntity);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (salePage.getSalerate()!=null&&!salePage.getSalerate().equals("")){
		    hql += " and salerate>=? ";
            values.add(salePage.getSalerate());
		}
		if (salePage.getCcreatedatetimeStart() != null) {
			hql += " and createdate>=? ";
			values.add(salePage.getCcreatedatetimeStart());
		}
		if (salePage.getCcreatedatetimeEnd() != null) {
			hql += " and createdate<=? ";
			values.add(salePage.getCcreatedatetimeEnd());
		}
		if (salePage.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(salePage.getCmodifydatetimeStart());
		}
		if (salePage.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(salePage.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(SalePage salePage) {
		SaleEntity t = new SaleEntity();
		BeanUtils.copyProperties(salePage, t);
		saleEntityDao.save(t);
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
	 * 保存：一对多
	 */
	public void addMain(SalePage salePage,List<SaleDetailPage> saleDetailList)  throws Exception{
		//[1].主表保存
		SaleEntity t = new SaleEntity();
		BeanUtils.copyProperties(salePage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxSaleNum();
        t.setOrdernum(num);
        t.setSalebillno(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(4, num));
        BigDecimal totalamount = new BigDecimal(0);
        boolean flag = false;
		//[2].明细数据保存
		//销售单明细
		for(SaleDetailPage page:saleDetailList){
		    if (page.getNum().intValue()<1) continue;
            //StockEntity se = this.getStockByID(page.getDepotid(), page.getArticleid(), page.getSerial());
            StockEntity se = stockService.get(page.getStockkey());
            
            if (se!=null&&se.getNum().compareTo(page.getNum())>=0){
                ArticleEntity ae = articleService.get(se.getArticleid());
    			SaleDetailEntity saleDetail = new SaleDetailEntity();
    			BeanUtils.copyProperties(page, saleDetail);
    			totalamount = totalamount.add((page.getNum().multiply(page.getSaleprice())));
    			saleDetail.setAmount(page.getNum().multiply(page.getSaleprice()));
    			saleDetail.setArticleid(se.getArticleid());
    			saleDetail.setDepotid(se.getDepotid());
    			saleDetail.setSaleprice(page.getSaleprice());
    			saleDetail.setPrice(se.getCostprice());
    			saleDetail.setSerial(se.getSerial());
    			saleDetail.setExpiredate(se.getExpiredate());
    			saleDetail.setStockkey(se.getStockkey());
    			saleDetail.setBacknum(new BigDecimal(0));
    			//外键设置
    			saleDetail.setSalebillno(t.getSalebillno());
    			saleDetailEntityDao.save(saleDetail);
    			//修改库存
    			se.setNum(se.getNum().subtract(page.getNum()));
    			//修改客户的品种销售价
    			CustomerPricePage cus = new CustomerPricePage();
                cus.setArticleid(se.getArticleid());
                cus.setCustomerid(salePage.getCustomerid());
                cus.setSaleprice(page.getSaleprice());
                customerPriceService.add(cus);
    			flag = true;
            }else{
                ArticleEntity ae = articleService.get(se.getArticleid());
                String messge = ae.getArticlename()+"/"+ae.getArticlespec()+"/"+ae.getFactory()+
                               "销售数量["+page.getNum()+"]大于库存剩余量["+se.getNum()+"]";
                throw new StockLessBackNumException(messge);
            }
		}
		t.setAmount(totalamount);
		t.setIsreceive(2);
		t.setSalebackbillitems("");
		if (flag) saleEntityDao.save(t);
	}
	/**
	 * 修改：一对多
	 */
	public void editMain(SalePage salePage,List<SaleDetailPage> saleDetailList)  throws Exception{
		//[1].主表保存
		SaleEntity t = saleEntityDao.get(SaleEntity.class, salePage.getSalebillno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(salePage, t);
		}
	    
	    
	    //获取参数
		Object salebillno0 = salePage.getSalebillno();
	    
	    //-------------------------------------------------------------------
	    //[1].查询明细销售单明细
	    String hql0 = "from SaleDetailEntity where 1 = 1 AND salebillno = ? ";
	    List<SaleDetailEntity> saleDetailOldList = saleDetailEntityDao.find(hql0,salebillno0);
	    
	    //[2].删除明细销售单明细
		String delhql0 = "delete from SaleDetailEntity where 1 = 1 AND salebillno = ? ";
		saleEntityDao.executeHql(delhql0,salebillno0);
		//-------------------------------------------------------------------
		//[3].明细数据保存
		//销售单明细
		SaleDetailEntity saleDetailEntity = null;
		for(SaleDetailPage page:saleDetailList){
			for(SaleDetailEntity c:saleDetailOldList){
				if(c.getSalebillno().equals(page.getSalebillno())){
					saleDetailEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(saleDetailEntity==null){
				saleDetailEntity = new SaleDetailEntity();
			}
			saleDetailEntityDao.evict(saleDetailEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, saleDetailEntity);
			//外键设置
			saleDetailEntity.setSalebillno(t.getSalebillno());
			    
			SearchSqlGenerateUtil.setUpdateMessage(saleDetailEntity);
			saleDetailEntityDao.save(saleDetailEntity);
			saleDetailEntity = null;
			//-----------------------------------------------------
		}
	}
	
	
	public void update(SalePage salePage) throws Exception {
		SaleEntity t = saleEntityDao.get(SaleEntity.class, salePage.getSalebillno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(salePage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SaleEntity where salebillno = '"+id+"'";
				SaleEntity t = saleEntityDao.get(hql);
				if (t != null) {
					saleEntityDao.delete(t);
						//获取参数
					    Object salebillno0 = t.getSalebillno();
					    //删除明细销售单明细
						String delhql0 = "delete from SaleDetailEntity where 1 = 1 AND salebillno = ? ";
						saleEntityDao.executeHql(delhql0,salebillno0);
				}
			}
		}
	}

	public SaleEntity get(SalePage salePage) {
		return saleEntityDao.get(SaleEntity.class, salePage.getSalebillno());
	}

	public SaleEntity get(java.lang.String salebillno) {
		return saleEntityDao.get(SaleEntity.class, salebillno);
	}
	public List<SaleEntity> listAll(SalePage salePage) {
		String hql = "from SaleEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(salePage, hql, values);
		List<SaleEntity> list = saleEntityDao.find(hql,values);
		return list;
	}
	
	/**根据主表Key,查询子表明细：销售单明细*/
	public List<SaleDetailPage> getSaleDetailListByFkey(java.lang.String salebillno) {
		
		List<SaleDetailPage> rs = new ArrayList<SaleDetailPage>();
		String hql = "from SaleDetailEntity where 1 = 1 AND salebillno = ? ";
		List<SaleDetailEntity> list = saleDetailEntityDao.find(hql,salebillno);
		
		for(SaleDetailEntity po:list){
			SaleDetailPage page = new SaleDetailPage();
			BeanUtils.copyProperties(po, page);
			ArticlePage ap = new ArticlePage();
            BeanUtils.copyProperties(articleService.get(po.getArticleid()), ap);
            DictParamPage dpp = new DictParamPage();
            BeanUtils.copyProperties(dictParamService.getDictName("003",ap.getUnit()), dpp);
            ap.setUnitdic(dpp);
            page.setArticlepage(ap);
            DepotPage dp = new DepotPage();
            BeanUtils.copyProperties(depotService.get(po.getDepotid()), dp);
            page.setDepotpage(dp);
            BeanUtils.copyProperties(dictParamService.getDictName("003",ap.getUnit()), dpp);
            ap.setUnitdic(dpp);
			rs.add(page);
		}
		return rs;
	}
	
	public Integer getMaxSaleNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xs_sale ae " +
        " where date_format(ae.createdate,'%Y-%m-%d')='"+DateUtils.FormatDate(new Date(), "yyyy-MM-dd")+"' ";
        Map<String, Object> map = saleEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
	public List<Map<String,Object>> getSaleStock(){
	    String sql = " SELECT  t_kc_stock.stockkey,t_kc_stock.articleid,t_kc_stock.depotid,t_kc_stock.serial,t_kc_stock.num," +
	    		" t_kc_stock.costprice,t_kc_stock.expiredate,t_xx_article.articlename,t_xx_article.articlealias,p.param_name as unitname, " +
	    		" t_xx_article.articlespec,t_xx_article.factory,t_xx_article.barcode,t_xx_article.retailprice," +
	    		" t_xx_article.pfprice,t_xx_article.unit,t_xx_depot.depotname " +
	    		" FROM  t_kc_stock " +
	    		" Inner Join t_xx_article ON t_kc_stock.articleid = t_xx_article.articleid " +
	    		" Inner Join t_xx_depot ON t_kc_stock.depotid = t_xx_depot.depotid " +
	    		" inner join jeecg_dict_param p on p.param_level='003' and p.PARAM_VALUE=t_xx_article.unit " +
	    		" where t_kc_stock.num>0 " +
	    		" order by t_xx_article.articlealias,t_kc_stock.expiredate";
	   List<Map<String,Object>> allStock = MapUtil.changeMapListToLower(saleEntityDao.findForJdbc(sql));
	   return allStock;
	   
	}
    @Override
    public List<Map<String, Object>> viewSaleTotalList(ReportQueryForm salePage)
    {
        List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        List<Object> values = new ArrayList<Object>();
        String sql = " select t.customerid,s.customername,t.accountid,d.accountname,t.salebillno" +
                " ,t.saledate,t.createdate,t.crtuser,t.crtusername,t.amount,d.accountname," +
                "case when bk.backamount is null then 0 else bk.backamount end as backamount " +
                " from  t_xs_sale t " +
                " inner join t_xx_accounter d on d.accountid=t.accountid " +
                " inner join t_xx_customer s on s.customerid=t.customerid " +
                " left join (select  t.salebillno,sum(t.amount)  as backamount " +
                " from t_xs_sale_back t group by t.salebillno ) bk on bk.salebillno=t.salebillno" +
                " where 1=1 " +
                "";
        if (salePage.getCustomerid()!=null&&!salePage.getCustomerid().equals("")){
            sql += " and t.customerid='"+salePage.getCustomerid()+"' ";
        }
        if (salePage.getAccountid()!=null&&!salePage.getAccountid().equals("")){
            sql += " and t.accountid='"+salePage.getAccountid()+"' ";
        }
        if (salePage.getCrtuser()!=null&&!salePage.getCrtuser().equals("")){
            sql += " and t.crtuser='"+salePage.getCrtuser()+"' ";
        }
        if (salePage.getBegindate() != null&&!salePage.getBegindate().equals("")) {
            sql += " and t.createdate>='"+DateUtils.FormatDate(salePage.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (salePage.getEnddate() != null&&!salePage.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(salePage.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by t.customerid,t.createdate desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
    }
    
    @Override
    public List<Map<String, Object>> viewSaleDetailList(ReportQueryForm salePage){
        List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        List<Object> values = new ArrayList<Object>();
        String sql = " select t.customerid,s.customername,t.accountid,d.accountname,t.salebillno" +
                " ,t.saledate,t.createdate,t.crtuser,t.crtusername,t.amount," +
                " a.articleid,a.articlename,a.factory," +
                " p.param_name as unitname,a.fileno,a.articlespec,a.retailprice,a.pfprice," +
                " k.num,k.price,k.serial,k.expiredate " +
                " ,k.backnum,k.backnum*k.saleprice as backamount,k.amount,k.saleprice," +
                " (k.num-k.backnum)*(k.saleprice-k.price) as benifitamount " +
                " from  t_xs_sale t " +
                " inner join t_xs_sale_detail k on k.salebillno=t.salebillno " +
                " inner join t_xx_accounter d on d.accountid=t.accountid " +
                " inner join t_xx_customer s on s.customerid=t.customerid " +
                " inner join t_xx_article a on a.articleid=k.articleid " +
                " left join jeecg_dict_param p on p.param_level='003' and p.param_value=a.unit" +
                " where 1=1 " +
                "";
        if (salePage.getArticleid()!=null&&!salePage.getArticleid().equals("")){
            sql += " and k.articleid='"+salePage.getArticleid()+"' ";
        }
        if (salePage.getSerial()!=null&&!salePage.getSerial().equals("")){
            sql += " and k.serial='"+salePage.getSerial()+"' ";
        }
        if (salePage.getCustomerid()!=null&&!salePage.getCustomerid().equals("")){
            sql += " and t.customerid='"+salePage.getCustomerid()+"' ";
        }
        if (salePage.getAccountid()!=null&&!salePage.getAccountid().equals("")){
            sql += " and t.accountid='"+salePage.getAccountid()+"' ";
        }
        if (salePage.getCrtuser()!=null&&!salePage.getCrtuser().equals("")){
            sql += " and t.crtuser='"+salePage.getCrtuser()+"' ";
        }
        if (salePage.getBegindate() != null&&!salePage.getBegindate().equals("")) {
            sql += " and t.createdate>='"+DateUtils.FormatDate(salePage.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (salePage.getEnddate() != null&&!salePage.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(salePage.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by t.customerid,t.createdate desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
    }
    public List<Map<String,Object>> viewSaleByAccounterList_HuiZong(ReportQueryForm reportQueryForm){
        List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        String sql = " select tt.accountid,tt.accountname,sum(tt.amount) totalsaleamount," +
        " sum(tt.rateamount) rateamount,sum(tt.benifitamount) benifitamount " +
        " from ( " +
        " select t.customerid,s.customername,t.accountid,d.accountname,t.salebillno" +
        " ,t.saledate,t.createdate,t.crtuser,t.crtusername," +
        " a.articleid,a.articlename,a.factory," +
        " p.param_name as unitname,a.fileno,a.articlespec,a.retailprice,a.pfprice," +
        " k.num,k.price,k.serial,k.expiredate,t.salerate,(k.num-k.backnum)*k.saleprice*t.salerate*0.01 as rateamount " +
        " ,k.backnum,k.backnum*k.saleprice as backamount,k.amount,k.saleprice," +
        " (k.num-k.backnum)*(k.saleprice-k.price) as benifitamount " +
        " from  t_xs_sale t " +
        " inner join t_xs_sale_detail k on k.salebillno=t.salebillno " +
        " inner join t_xx_accounter d on d.accountid=t.accountid " +
        " inner join t_xx_customer s on s.customerid=t.customerid " +
        " inner join t_xx_article a on a.articleid=k.articleid " +
        " left join jeecg_dict_param p on p.param_level='003' and p.param_value=a.unit" +
        " where 1=1 " +
        "";
        
        if (reportQueryForm.getArticleid()!=null&&!reportQueryForm.getArticleid().equals("")){
            sql += " and t.articleid='"+reportQueryForm.getArticleid()+"' ";
        }
        if (reportQueryForm.getCustomerid()!=null&&!reportQueryForm.getCustomerid().equals("")){
            sql += " and t.customerid='"+reportQueryForm.getCustomerid()+"' ";
        }
        if (reportQueryForm.getAccountid()!=null&&!reportQueryForm.getAccountid().equals("")){
            sql += " and t.accountid='"+reportQueryForm.getAccountid()+"' ";
        }
        
        if (reportQueryForm.getBegindate() != null&&!reportQueryForm.getBegindate().equals("")) {
            sql += " and t.createdate>='"+DateUtils.FormatDate(reportQueryForm.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (reportQueryForm.getEnddate() != null&&!reportQueryForm.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(reportQueryForm.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += ") tt group by tt.accountid,tt.accountname order by sum(tt.rateamount) desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
    }
    public List<Map<String,Object>> viewSaleByAccounterList_MingXi(ReportQueryForm reportQueryForm){
        List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        String sql = " select t.customerid,s.customername,t.accountid,d.accountname,t.salebillno" +
        " ,t.saledate,t.createdate,t.crtuser,t.crtusername," +
        " a.articleid,a.articlename,a.factory," +
        " p.param_name as unitname,a.fileno,a.articlespec,a.retailprice,a.pfprice," +
        " k.num,k.price,k.serial,k.expiredate,t.salerate,(k.num-k.backnum)*k.saleprice*t.salerate*0.01 as rateamount " +
        " ,k.backnum,k.backnum*k.saleprice as backamount,k.amount,k.saleprice," +
        " (k.num-k.backnum)*(k.saleprice-k.price) as benifitamount " +
        " from  t_xs_sale t " +
        " inner join t_xs_sale_detail k on k.salebillno=t.salebillno " +
        " inner join t_xx_accounter d on d.accountid=t.accountid " +
        " inner join t_xx_customer s on s.customerid=t.customerid " +
        " inner join t_xx_article a on a.articleid=k.articleid " +
        " left join jeecg_dict_param p on p.param_level='003' and p.param_value=a.unit" +
        " where 1=1 " +
        "";
        
        if (reportQueryForm.getArticleid()!=null&&!reportQueryForm.getArticleid().equals("")){
            sql += " and k.articleid='"+reportQueryForm.getArticleid()+"' ";
        }
        if (reportQueryForm.getCustomerid()!=null&&!reportQueryForm.getCustomerid().equals("")){
            sql += " and t.customerid='"+reportQueryForm.getCustomerid()+"' ";
        }
        if (reportQueryForm.getAccountid()!=null&&!reportQueryForm.getAccountid().equals("")){
            sql += " and t.accountid='"+reportQueryForm.getAccountid()+"' ";
        }
        
        if (reportQueryForm.getBegindate() != null&&!reportQueryForm.getBegindate().equals("")) {
            sql += " and t.createdate>='"+DateUtils.FormatDate(reportQueryForm.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (reportQueryForm.getEnddate() != null&&!reportQueryForm.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(reportQueryForm.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by t.accountid,t.createdate desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
    }
    public CustomerPriceServiceI getCustomerPriceService()
    {
        return customerPriceService;
    }
    public void setCustomerPriceService(CustomerPriceServiceI customerPriceService)
    {
        this.customerPriceService = customerPriceService;
    }
	
}
