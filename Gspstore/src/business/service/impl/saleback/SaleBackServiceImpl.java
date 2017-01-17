package business.service.impl.saleback;

import java.math.BigDecimal;
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

import business.entity.sale.SaleDetailEntity;
import business.entity.sale.SaleEntity;
import business.entity.saleback.SaleBackEntity;
import business.page.accounter.AccounterPage;
import business.page.article.ArticlePage;
import business.page.customer.CustomerPage;
import business.page.depot.DepotPage;
import business.page.saleback.SaleBackPage;
import business.service.accounter.AccounterServiceI;
import business.service.article.ArticleServiceI;
import business.service.customer.CustomerServiceI;
import business.service.depot.DepotServiceI;
import business.service.sale.SaleDetailServiceI;
import business.service.sale.SaleServiceI;
import business.service.saleback.SaleBackServiceI;
import business.service.stock.StockServiceI;
import business.util.FormatUtil;
import business.page.saleback.SaleBackDetailPage;
import business.entity.saleback.SaleBackDetailEntity;
import business.entity.stock.StockEntity;

import com.jeecg.pageModel.TreeNode;
import com.sys.page.base.DictParamPage;
import com.sys.service.base.DictParamServiceI;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;/**   
 * @Title: ServiceImpl
 * @Description: 销售退货单
 * @author zhangdaihao
 * @date 2013-05-20 13:40:47
 * @version V1.0   
 *
 */
@Service("saleBackService")
public class SaleBackServiceImpl extends BaseServiceImpl implements SaleBackServiceI {

	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	@Autowired
    private SaleServiceI saleService;
	private BaseDaoI<SaleBackEntity> saleBackEntityDao;
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
    private SaleDetailServiceI saleDetailService;
	@Autowired
	private BaseDaoI<SaleBackDetailEntity> saleBackDetailEntityDao;
	@Autowired
    private DictParamServiceI dictParamService;
	
	
	public BaseDaoI<SaleBackEntity> getSaleBackEntityDao() {
		return saleBackEntityDao;
	}
	@Autowired
	public void setSaleBackEntityDao(BaseDaoI<SaleBackEntity> saleBackEntityDao) {
		this.saleBackEntityDao = saleBackEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(SaleBackPage saleBackPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(saleBackPage)));
		j.setTotal(total(saleBackPage));
		return j;
	}

	private List<SaleBackPage> getPagesFromEntitys(List<SaleBackEntity> saleBackEntitys) {
		List<SaleBackPage> saleBackPages = new ArrayList<SaleBackPage>();
		if (saleBackEntitys != null && saleBackEntitys.size() > 0) {
			for (SaleBackEntity tb : saleBackEntitys) {
				SaleBackPage b = new SaleBackPage();
				BeanUtils.copyProperties(tb, b);
				//获得业务员
                AccounterPage ap = new AccounterPage();
                BeanUtils.copyProperties(accounterService.get(tb.getAccountid()),ap);
                b.setAccounterpage(ap);
                //获得客户
                CustomerPage cp = new CustomerPage();
                BeanUtils.copyProperties(customerService.get(tb.getCustomerid()), cp);
                b.setCustomerpage(cp);
				saleBackPages.add(b);
			}
		}
		return saleBackPages;
	}

	private List<SaleBackEntity> find(SaleBackPage saleBackPage) {
		String hql = "from SaleBackEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackPage, hql, values);

		if (saleBackPage.getSort() != null && saleBackPage.getOrder() != null) {
			hql += " order by " + saleBackPage.getSort() + " " + saleBackPage.getOrder();
		}
		return saleBackEntityDao.find(hql, saleBackPage.getPage(), saleBackPage.getRows(), values);
	}

	private Long total(SaleBackPage saleBackPage) {
		String hql = "select count(*) from SaleBackEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackPage, hql, values);
		return saleBackEntityDao.count(hql, values);
	}

	private String addWhere(SaleBackPage saleBackPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		SaleBackEntity saleBackEntity = new SaleBackEntity();
		BeanUtils.copyProperties(saleBackPage, saleBackEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, saleBackEntity);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (saleBackPage.getCcreatedatetimeStart() != null) {
			hql += " and createDt>=? ";
			values.add(saleBackPage.getCcreatedatetimeStart());
		}
		if (saleBackPage.getCcreatedatetimeEnd() != null) {
			hql += " and createDt<=? ";
			values.add(saleBackPage.getCcreatedatetimeEnd());
		}
		if (saleBackPage.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(saleBackPage.getCmodifydatetimeStart());
		}
		if (saleBackPage.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(saleBackPage.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(SaleBackPage saleBackPage) {
		SaleBackEntity t = new SaleBackEntity();
		BeanUtils.copyProperties(saleBackPage, t);
		saleBackEntityDao.save(t);
	}

	
	/**
	 * 保存：一对多
	 */
	public void addMain(SaleBackPage saleBackPage,List<SaleBackDetailPage> saleBackDetailList)  throws Exception{
		//[1].主表保存
		SaleBackEntity t = new SaleBackEntity();
		BeanUtils.copyProperties(saleBackPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxSaleBackNum();
        t.setOrdernum(num);
        t.setSalebackno(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(4, num));
        BigDecimal totalamount = new BigDecimal(0);
        boolean flag = false;
		//[2].明细数据保存
		//销售退货明细
		for(SaleBackDetailPage page:saleBackDetailList){
		    if (page.getNum().intValue()<1) continue;
			SaleBackDetailEntity saleBackDetail = new SaleBackDetailEntity();
			BeanUtils.copyProperties(page, saleBackDetail);
			page.getStockkey();
			StockEntity se = stockService.get(page.getStockkey());
			SaleDetailEntity sde = saleDetailService.get(page.getSalebilldetailno());
			totalamount = totalamount.add((page.getNum().multiply(sde.getSaleprice())));
			saleBackDetail.setAmount(page.getNum().multiply(sde.getSaleprice()));
			saleBackDetail.setDepotid(sde.getDepotid());
			saleBackDetail.setArticleid(sde.getArticleid());
			saleBackDetail.setSaleprice(sde.getSaleprice());
			saleBackDetail.setPrice(sde.getPrice());
			saleBackDetail.setSerial(sde.getSerial());
			saleBackDetail.setExpiredate(sde.getExpiredate());
			saleBackDetail.setSecretno(sde.getSecretno());
			saleBackDetail.setStockkey(se.getStockkey());
			saleBackDetail.setSalebilldetailno(page.getSalebilldetailno());
			//外键设置
            saleBackDetail.setSalebackno(t.getSalebackno());
			saleBackDetailEntityDao.save(saleBackDetail);
			se.setNum(se.getNum().add(page.getNum()));
			sde.setBacknum(sde.getBacknum().add(page.getNum()));
            flag = true;
		}
		t.setIsreceive(2);
		if (flag) {
		    t.setAmount(totalamount);
		    saleBackEntityDao.save(t);
		    SaleEntity saleEn = saleService.get(saleBackPage.getSalebillno());
		    if (saleEn.getSalebackbillitems()==null||saleEn.getSalebackbillitems().equals("")){
		        saleEn.setSalebackbillitems(t.getSalebackno());
		    }else{
		        saleEn.setSalebackbillitems(saleEn.getSalebackbillitems()+","+t.getSalebackno());
		    }
		}
	}
	/**
	 * 修改：一对多
	 */
	public void editMain(SaleBackPage saleBackPage,List<SaleBackDetailPage> saleBackDetailList)  throws Exception{
		//[1].主表保存
		SaleBackEntity t = saleBackEntityDao.get(SaleBackEntity.class, saleBackPage.getSalebackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(saleBackPage, t);
		}
	    
	    
	    //获取参数
		Object salebackno0 = saleBackPage.getSalebackno();
	    
	    //-------------------------------------------------------------------
	    //[1].查询明细销售退货明细
	    String hql0 = "from SaleBackDetailEntity where 1 = 1 AND salebackno = ? ";
	    List<SaleBackDetailEntity> saleBackDetailOldList = saleBackDetailEntityDao.find(hql0,salebackno0);
	    
	    //[2].删除明细销售退货明细
		String delhql0 = "delete from SaleBackDetailEntity where 1 = 1 AND salebackno = ? ";
		saleBackEntityDao.executeHql(delhql0,salebackno0);
		//-------------------------------------------------------------------
		//[3].明细数据保存
		//销售退货明细
		SaleBackDetailEntity saleBackDetailEntity = null;
		for(SaleBackDetailPage page:saleBackDetailList){
			for(SaleBackDetailEntity c:saleBackDetailOldList){
				if(c.getSalebackno().equals(page.getSalebackno())){
					saleBackDetailEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(saleBackDetailEntity==null){
				saleBackDetailEntity = new SaleBackDetailEntity();
			}
			saleBackDetailEntityDao.evict(saleBackDetailEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, saleBackDetailEntity);
			//外键设置
			saleBackDetailEntity.setSalebackno(t.getSalebackno());
			    
			SearchSqlGenerateUtil.setUpdateMessage(saleBackDetailEntity);
			saleBackDetailEntityDao.save(saleBackDetailEntity);
			saleBackDetailEntity = null;
			//-----------------------------------------------------
		}
	}
	
	
	public void update(SaleBackPage saleBackPage) throws Exception {
		SaleBackEntity t = saleBackEntityDao.get(SaleBackEntity.class, saleBackPage.getSalebackno());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(saleBackPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from SaleBackEntity where salebackno = '"+id+"'";
				SaleBackEntity t = saleBackEntityDao.get(hql);
				if (t != null) {
					saleBackEntityDao.delete(t);
						//获取参数
					    Object salebackno0 = t.getSalebackno();
					    //删除明细销售退货明细
						String delhql0 = "delete from SaleBackDetailEntity where 1 = 1 AND salebackno = ? ";
						saleBackEntityDao.executeHql(delhql0,salebackno0);
				}
			}
		}
	}

	public SaleBackEntity get(SaleBackPage saleBackPage) {
		return saleBackEntityDao.get(SaleBackEntity.class, saleBackPage.getSalebackno());
	}

	public SaleBackEntity get(java.lang.String salebackno) {
		return saleBackEntityDao.get(SaleBackEntity.class, salebackno);
	}
	public List<SaleBackEntity> listAll(SaleBackPage saleBackPage) {
		String hql = "from SaleBackEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(saleBackPage, hql, values);
		List<SaleBackEntity> list = saleBackEntityDao.find(hql,values);
		return list;
	}
	
	/**根据主表Key,查询子表明细：销售退货明细*/
	public List<SaleBackDetailPage> getSaleBackDetailListByFkey(java.lang.String salebackno) {
		
		List<SaleBackDetailPage> rs = new ArrayList<SaleBackDetailPage>();
		String hql = "from SaleBackDetailEntity where 1 = 1 AND salebackno = ? ";
		List<SaleBackDetailEntity> list = saleBackDetailEntityDao.find(hql,salebackno);
		
		for(SaleBackDetailEntity po:list){
			SaleBackDetailPage page = new SaleBackDetailPage();
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
			rs.add(page);
		}
		return rs;
	}
	public Integer getMaxSaleBackNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xs_sale_back ae " +
        " where date_format(ae.createdate,'%Y-%m-%d')='"+DateUtils.FormatDate(new Date(), "yyyy-MM-dd")+"' ";
        Map<String, Object> map = saleBackEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
}
