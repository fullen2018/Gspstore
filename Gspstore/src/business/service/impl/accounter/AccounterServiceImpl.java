package business.service.impl.accounter;

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

import business.entity.accounter.AccounterEntity;
import business.page.accounter.AccounterPage;
import business.page.area.AreaPage;
import business.service.accounter.AccounterServiceI;
import business.service.area.AreaServiceI;
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
 * @Description: 业务员信息
 * @author zhangdaihao
 * @date 2013-05-13 09:26:48
 * @version V1.0   
 *
 */
@Service("accounterService")
public class AccounterServiceImpl extends BaseServiceImpl implements AccounterServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<AccounterEntity> accounterEntityDao;
	@Autowired
    private AreaServiceI areaService;
	public BaseDaoI<AccounterEntity> getAccounterEntityDao() {
		return accounterEntityDao;
	}
	@Autowired
	public void setAccounterEntityDao(BaseDaoI<AccounterEntity> accounterEntityDao) {
		this.accounterEntityDao = accounterEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(AccounterPage accounterPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(accounterPage)));
		j.setTotal(total(accounterPage));
		return j;
	}

	private List<AccounterPage> getPagesFromEntitys(List<AccounterEntity> accounterEntitys) {
		List<AccounterPage> accounterPages = new ArrayList<AccounterPage>();
		if (accounterEntitys != null && accounterEntitys.size() > 0) {
			for (AccounterEntity tb : accounterEntitys) {
				AccounterPage b = new AccounterPage();
				BeanUtils.copyProperties(tb, b);
				AreaPage ap = new AreaPage();
				BeanUtils.copyProperties(areaService.get(tb.getArea()), ap);
				b.setAreapage(ap);
				accounterPages.add(b);
			}
		}
		return accounterPages;
	}

	private List<AccounterEntity> find(AccounterPage accounterPage) {
		String hql = "from AccounterEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(accounterPage, hql, values);

		if (accounterPage.getSort() != null && accounterPage.getOrder() != null) {
			hql += " order by " + accounterPage.getSort() + " " + accounterPage.getOrder();
		}
		return accounterEntityDao.find(hql, accounterPage.getPage(), accounterPage.getRows(), values);
	}

	private Long total(AccounterPage accounterPage) {
		String hql = "select count(*) from AccounterEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(accounterPage, hql, values);
		return accounterEntityDao.count(hql, values);
	}

	private String addWhere(AccounterPage accounterPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		AccounterEntity accounterEntity = new AccounterEntity();
		BeanUtils.copyProperties(accounterPage, accounterEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, accounterEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(AccounterPage accounterPage) {
		AccounterEntity t = new AccounterEntity();
		BeanUtils.copyProperties(accounterPage, t);
		Integer num = getMaxAccounterNum();
        t.setOrdernum(num);
        t.setAccountid(FormatUtil.convertIntToString(4, num));
        t.setCreatedate(new Date());
        t.setModifydate(new Date());
		accounterEntityDao.save(t);
	}

	public void update(AccounterPage accounterPage) throws Exception {
		AccounterEntity t = accounterEntityDao.get(AccounterEntity.class, accounterPage.getAccountid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(accounterPage, t);
	        t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from AccounterEntity where accountid = '"+id+"'";
				AccounterEntity t = accounterEntityDao.get(hql);
				if (t != null) {
					accounterEntityDao.delete(t);
				}
			}
		}
	}

	public AccounterEntity get(AccounterPage accounterPage) {
		return accounterEntityDao.get(AccounterEntity.class, accounterPage.getAccountid());
	}

	public AccounterEntity get(java.lang.String accountid) {
		return accounterEntityDao.get(AccounterEntity.class, accountid);
	}
	public List<AccounterEntity> listAll(AccounterPage accounterPage) {
		String hql = "from AccounterEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(accounterPage, hql, values);
		List<AccounterEntity> list = accounterEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxAccounterNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_accounter ae ";
        Map<String, Object> map = accounterEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
    public AreaServiceI getAreaService()
    {
        return areaService;
    }
    public void setAreaService(AreaServiceI areaService)
    {
        this.areaService = areaService;
    }
    public boolean ifCanDelete(String key){
        String sql = "select count(1) as delflag from t_xs_sale t where t.accountid='"+key+"' ";
        Map<String, Object> map = accounterEntityDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
