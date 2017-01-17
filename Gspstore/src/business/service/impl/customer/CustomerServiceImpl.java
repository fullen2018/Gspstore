package business.service.impl.customer;

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

import business.entity.customer.CustomerEntity;
import business.page.accounter.AccounterPage;
import business.page.customer.CustomerPage;
import business.service.accounter.AccounterServiceI;
import business.service.customer.CustomerServiceI;
import business.util.FormatUtil;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 客户信息
 * @author zhangdaihao
 * @date 2013-05-13 14:23:07
 * @version V1.0   
 *
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<CustomerEntity> customerEntityDao;
	@Autowired
	private AccounterServiceI accounterService;

	public BaseDaoI<CustomerEntity> getCustomerEntityDao() {
		return customerEntityDao;
	}
	@Autowired
	public void setCustomerEntityDao(BaseDaoI<CustomerEntity> customerEntityDao) {
		this.customerEntityDao = customerEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(CustomerPage customerPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(customerPage)));
		j.setTotal(total(customerPage));
		return j;
	}

	private List<CustomerPage> getPagesFromEntitys(List<CustomerEntity> customerEntitys) {
		List<CustomerPage> customerPages = new ArrayList<CustomerPage>();
		if (customerEntitys != null && customerEntitys.size() > 0) {
			for (CustomerEntity tb : customerEntitys) {
				CustomerPage b = new CustomerPage();
				AccounterPage ap = new AccounterPage();
				BeanUtils.copyProperties(tb, b);
				BeanUtils.copyProperties(accounterService.get(b.getAccountid()),ap);
				b.setPageaccounter(ap);
				customerPages.add(b);
			}
		}
		return customerPages;
	}

	private List<CustomerEntity> find(CustomerPage customerPage) {
		String hql = "from CustomerEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPage, hql, values);

		if (customerPage.getSort() != null && customerPage.getOrder() != null) {
			hql += " order by " + customerPage.getSort() + " " + customerPage.getOrder();
		}
		return customerEntityDao.find(hql, customerPage.getPage(), customerPage.getRows(), values);
	}

	private Long total(CustomerPage customerPage) {
		String hql = "select count(*) from CustomerEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPage, hql, values);
		return customerEntityDao.count(hql, values);
	}

	private String addWhere(CustomerPage customerPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerPage, customerEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, customerEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(CustomerPage customerPage) {
		CustomerEntity t = new CustomerEntity();
		BeanUtils.copyProperties(customerPage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxCustomerNum();
        t.setOrdernum(num);
        t.setCustomerid(FormatUtil.convertIntToString(4, num));
		customerEntityDao.save(t);
	}

	public void update(CustomerPage customerPage) throws Exception {
		CustomerEntity t = customerEntityDao.get(CustomerEntity.class, customerPage.getCustomerid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(customerPage, t);
			t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from CustomerEntity where customerid = '"+id+"'";
				CustomerEntity t = customerEntityDao.get(hql);
				if (t != null) {
					customerEntityDao.delete(t);
				}
			}
		}
	}

	public CustomerEntity get(CustomerPage customerPage) {
		return customerEntityDao.get(CustomerEntity.class, customerPage.getCustomerid());
	}

	public CustomerEntity get(java.lang.String customerid) {
		return customerEntityDao.get(CustomerEntity.class, customerid);
	}
	public List<CustomerEntity> listAll(CustomerPage customerPage) {
		String hql = "from CustomerEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(customerPage, hql, values);
		List<CustomerEntity> list = customerEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxCustomerNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_customer ae ";
        Map<String, Object> map = customerEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
    public AccounterServiceI getAccounterService()
    {
        return accounterService;
    }
    public void setAccounterService(AccounterServiceI accounterService)
    {
        this.accounterService = accounterService;
    }
    public boolean ifCanDelete(String key){
        String sql = "select count(1) as delflag from t_xs_sale t where t.customerid='"+key+"' ";
        Map<String, Object> map = customerEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
