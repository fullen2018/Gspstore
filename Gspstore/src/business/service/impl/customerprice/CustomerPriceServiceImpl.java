package business.service.impl.customerprice;

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

import business.entity.customerprice.CustomerPriceEntity;
import business.page.article.ArticlePage;
import business.page.customer.CustomerPage;
import business.page.customerprice.CustomerPricePage;
import business.service.article.ArticleServiceI;
import business.service.customer.CustomerServiceI;
import business.service.customerprice.CustomerPriceServiceI;
import business.util.MapUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 客户品种价格
 * @author zhangdaihao
 * @date 2013-06-07 00:44:33
 * @version V1.0   
 *
 */
@Service("customerPriceService")
public class CustomerPriceServiceImpl extends BaseServiceImpl implements CustomerPriceServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<CustomerPriceEntity> customerPriceEntityDao;
	@Autowired
	private ArticleServiceI articleService;
	@Autowired
	private CustomerServiceI customerService;
	public BaseDaoI<CustomerPriceEntity> getCustomerPriceEntityDao() {
		return customerPriceEntityDao;
	}
	@Autowired
	public void setCustomerPriceEntityDao(BaseDaoI<CustomerPriceEntity> customerPriceEntityDao) {
		this.customerPriceEntityDao = customerPriceEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(CustomerPricePage customerPricePage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(customerPricePage)));
		j.setTotal(total(customerPricePage));
		return j;
	}

	private List<CustomerPricePage> getPagesFromEntitys(List<CustomerPriceEntity> customerPriceEntitys) {
		List<CustomerPricePage> customerPricePages = new ArrayList<CustomerPricePage>();
		if (customerPriceEntitys != null && customerPriceEntitys.size() > 0) {
			for (CustomerPriceEntity tb : customerPriceEntitys) {
				CustomerPricePage b = new CustomerPricePage();
				BeanUtils.copyProperties(tb, b);
				//获得客户
                CustomerPage cp = new CustomerPage();
                BeanUtils.copyProperties(customerService.get(tb.getCustomerid()),cp);
                b.setCustomerpage(cp);
                ArticlePage ap = new ArticlePage();
                BeanUtils.copyProperties(articleService.get(tb.getArticleid()),ap);
                b.setArticlepage(ap);
				customerPricePages.add(b);
			}
		}
		return customerPricePages;
	}

	private List<CustomerPriceEntity> find(CustomerPricePage customerPricePage) {
		String hql = "from CustomerPriceEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPricePage, hql, values);

		if (customerPricePage.getSort() != null && customerPricePage.getOrder() != null) {
			hql += " order by " + customerPricePage.getSort() + " " + customerPricePage.getOrder();
		}
		return customerPriceEntityDao.find(hql, customerPricePage.getPage(), customerPricePage.getRows(), values);
	}

	private Long total(CustomerPricePage customerPricePage) {
		String hql = "select count(*) from CustomerPriceEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPricePage, hql, values);
		return customerPriceEntityDao.count(hql, values);
	}

	private String addWhere(CustomerPricePage customerPricePage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		CustomerPriceEntity customerPriceEntity = new CustomerPriceEntity();
		BeanUtils.copyProperties(customerPricePage, customerPriceEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, customerPriceEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(CustomerPricePage customerPricePage) {
		CustomerPriceEntity t = new CustomerPriceEntity();
		String key = customerPricePage.getCustomerid()+"-"+customerPricePage.getArticleid();
        t =  this.get(key);
        if (t!=null){
            t.setSaleprice(customerPricePage.getSaleprice());
            t.setModifydate(new Date());
        }else{
            t = new CustomerPriceEntity();
            customerPricePage.setFlowid(key);
            BeanUtils.copyProperties(customerPricePage, t);
            t.setCreatedate(new Date());
            t.setModifydate(new Date());
            customerPriceEntityDao.save(t);
        }
	}

	public void update(CustomerPricePage customerPricePage) throws Exception {
		CustomerPriceEntity t = customerPriceEntityDao.get(CustomerPriceEntity.class, customerPricePage.getFlowid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(customerPricePage, t);
			t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from CustomerPriceEntity where flowid = '"+id+"'";
				CustomerPriceEntity t = customerPriceEntityDao.get(hql);
				if (t != null) {
					customerPriceEntityDao.delete(t);
				}
			}
		}
	}

	public CustomerPriceEntity get(CustomerPricePage customerPricePage) {
		return customerPriceEntityDao.get(CustomerPriceEntity.class, customerPricePage.getFlowid());
	}

	public CustomerPriceEntity get(java.lang.String flowid) {
		return customerPriceEntityDao.get(CustomerPriceEntity.class, flowid);
	}
	public List<CustomerPriceEntity> listAll(CustomerPricePage customerPricePage) {
		String hql = "from CustomerPriceEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPricePage, hql, values);
		List<CustomerPriceEntity> list = customerPriceEntityDao.find(hql,values);
		return list;
	}
	public Map<String,Object> getCustomerPrice(CustomerPricePage customerPricePage){
	    String key = customerPricePage.getCustomerid()+"-"+customerPricePage.getArticleid();
	    String sql = "select c.customername,a.articlename,p.saleprice,1 as flag " +
	    		" from t_xs_customer_price p ," +
	    		" t_xx_customer c,t_xx_article a " +
	    		" where p.customerid=c.customerid and p.articleid=a.articleid " +
	    		" and p.flowid='"+key+"' " +
	    		"";
	    Map<String,Object> temp =jdbcDao.findOneForJdbc(sql);
	    if (temp==null){
	        temp = new HashMap<String,Object>();
	        temp.put("flag","0");
	    }
	    Map<String,Object> result = MapUtil.changeMapToLower(temp);
	    return result;
	}
}
