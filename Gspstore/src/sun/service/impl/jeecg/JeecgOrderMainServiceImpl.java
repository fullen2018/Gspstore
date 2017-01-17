package sun.service.impl.jeecg;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.entity.jeecg.JeecgOrderCustomEntity;
import sun.entity.jeecg.JeecgOrderMainEntity;
import sun.entity.jeecg.JeecgOrderProductEntity;
import sun.page.jeecg.JeecgOrderCustomPage;
import sun.page.jeecg.JeecgOrderMainPage;
import sun.page.jeecg.JeecgOrderProductPage;
import sun.service.jeecg.JeecgOrderMainServiceI;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.JpaUtil;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
/**   
 * @Title: ServiceImpl
 * @Description: 订单主数据
 * @author zhangdaihao
 * @date 2011-12-31 16:23:01
 * @version V1.0   
 *
 */
@Service("jeecgOrderMainService")
public class JeecgOrderMainServiceImpl extends BaseServiceImpl implements JeecgOrderMainServiceI {

	private static final String JOIN = "join";
	private static final String WHERE_1_1 = "where 1=1";
	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	private BaseDaoI<JeecgOrderMainEntity> jeecgOrderMainEntityDao;
	@Autowired
	private BaseDaoI<JeecgOrderCustomEntity> jeecgOrderCustomEntityDao;
	@Autowired
	private BaseDaoI<JeecgOrderProductEntity> jeecgOrderProductEntityDao;
	public BaseDaoI<JeecgOrderMainEntity> getJeecgOrderMainEntityDao() {
		return jeecgOrderMainEntityDao;
	}
	@Autowired
	public void setJeecgOrderMainEntityDao(BaseDaoI<JeecgOrderMainEntity> jeecgOrderMainEntityDao) {
		this.jeecgOrderMainEntityDao = jeecgOrderMainEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(JeecgOrderMainPage jeecgOrderMainPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(jeecgOrderMainPage)));
		j.setTotal(total(jeecgOrderMainPage));
		return j;
	}

	private List<JeecgOrderMainPage> getPagesFromEntitys(List<JeecgOrderMainEntity> jeecgOrderMainEntitys) {
		List<JeecgOrderMainPage> jeecgOrderMainPages = new ArrayList<JeecgOrderMainPage>();
		if (jeecgOrderMainEntitys != null && jeecgOrderMainEntitys.size() > 0) {
			for (JeecgOrderMainEntity tb : jeecgOrderMainEntitys) {
				JeecgOrderMainPage b = new JeecgOrderMainPage();
				BeanUtils.copyProperties(tb, b);
				jeecgOrderMainPages.add(b);
			}
		}
		return jeecgOrderMainPages;
	}
	//update-begin author:gaoxingang for:修改find方法  date：20130315
	private List<JeecgOrderMainEntity> find(JeecgOrderMainPage jeecgOrderMainPage) {
		
		String	sql = "select * from jeecg_order_main jeecgOrderMainEntity " + WHERE_1_1;
		List<Object> values = new ArrayList<Object>();
		sql = addWhere(jeecgOrderMainPage, sql, values);

		if (StringUtils.isNotEmpty(jeecgOrderMainPage.getSort())  && StringUtils.isNotEmpty(jeecgOrderMainPage.getOrder())) {
			sql += " order by " + "jeecgOrderMainEntity." +JpaUtil.getColumnName(new JeecgOrderMainEntity(), jeecgOrderMainPage.getSort()) + " " + jeecgOrderMainPage.getOrder();
		}
		String _sql = checkSql(sql);
		return jeecgOrderMainEntityDao.find(_sql, jeecgOrderMainPage.getPage(), jeecgOrderMainPage.getRows(), values, JeecgOrderMainEntity.class);
	}
	//update-end author:gaoxingang for:修改find方法  date：20130315
	
	/**
	 * 判断当前sql 中是否存在 join语句
	 * @param sql
	 * @return
	 */
	private String checkSql(String sql) {
		String _sql;
		if (sql.contains(JOIN)) {
			_sql = sql.replace(WHERE_1_1, "");
		} else {
			_sql = sql;
		}
		return _sql;
	}
	//update-begin author:gaoxingang for:修改total方法  date：20130315
	private Long total(JeecgOrderMainPage jeecgOrderMainPage) {
		String sql = "select count(*) from jeecg_order_main jeecgOrderMainEntity " + WHERE_1_1;
		List<Object> values = new ArrayList<Object>();
		sql = addWhere(jeecgOrderMainPage, sql, values);
		String _sql = checkSql(sql);
		return jeecgOrderMainEntityDao.countBySql(_sql, values);
	}
	//update-begin author:gaoxingang for:修改total方法  date：20130315

	private String addWhere(JeecgOrderMainPage jeecgOrderMainPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer bf = new StringBuffer(hql);
		JeecgOrderMainEntity jeecgOrderMainEntity = new JeecgOrderMainEntity();
		BeanUtils.copyProperties(jeecgOrderMainPage, jeecgOrderMainEntity);
		SearchSqlGenerateUtil.addJoin(bf, values, jeecgOrderMainEntity);
		SearchSqlGenerateUtil.createSearchParamsSql(bf, values, jeecgOrderMainEntity);
		hql = bf.toString();
		return hql;
	}

	public void add(JeecgOrderMainPage jeecgOrderMainPage) {
		if (jeecgOrderMainPage.getObid() == null || jeecgOrderMainPage.getObid().trim().equals("")) {
			jeecgOrderMainPage.setObid(UUID.randomUUID().toString());
		}
		JeecgOrderMainEntity t = new JeecgOrderMainEntity();
		BeanUtils.copyProperties(jeecgOrderMainPage, t);
		jeecgOrderMainEntityDao.save(t);
	}

	
	/**
	 * 保存：一对多
	 */
	public void addMain(JeecgOrderMainPage jeecgOrderMainPage,List<JeecgOrderCustomPage> jeecgOrderCustomList,List<JeecgOrderProductPage> jeecgOrderProductList)  throws Exception{
		//[1].主表保存
		if (jeecgOrderMainPage.getObid() == null || jeecgOrderMainPage.getObid().trim().equals("")) {
			jeecgOrderMainPage.setObid(UUID.randomUUID().toString());
		}
		JeecgOrderMainEntity t = new JeecgOrderMainEntity();
		BeanUtils.copyProperties(jeecgOrderMainPage, t);
		jeecgOrderMainEntityDao.save(t);
		//[2].明细数据保存
		//订单客户明细
		for(JeecgOrderCustomPage page:jeecgOrderCustomList){
			JeecgOrderCustomEntity jeecgOrderCustom = new JeecgOrderCustomEntity();
			BeanUtils.copyProperties(page, jeecgOrderCustom);
			
			//外键设置
			jeecgOrderCustom.setGorderObid(t.getObid());
			//外键设置
			jeecgOrderCustom.setGoOrderCode(t.getGoOrderCode());
			
			jeecgOrderCustom.setObid(UUID.randomUUID().toString());
			jeecgOrderCustomEntityDao.save(jeecgOrderCustom);
		}
		//订单产品明细
		for(JeecgOrderProductPage page:jeecgOrderProductList){
			JeecgOrderProductEntity jeecgOrderProduct = new JeecgOrderProductEntity();
			BeanUtils.copyProperties(page, jeecgOrderProduct);
			
			//外键设置
			jeecgOrderProduct.setGorderObid(t.getObid());
			//外键设置
			jeecgOrderProduct.setGoOrderCode(t.getGoOrderCode());
			
			jeecgOrderProduct.setObid(UUID.randomUUID().toString());
			jeecgOrderProductEntityDao.save(jeecgOrderProduct);
		}
	}
	/**
	 * 修改：一对多
	 */
	public void editMain(JeecgOrderMainPage jeecgOrderMainPage,List<JeecgOrderCustomPage> jeecgOrderCustomList,List<JeecgOrderProductPage> jeecgOrderProductList)  throws Exception{
		//[1].主表保存
		JeecgOrderMainEntity t = jeecgOrderMainEntityDao.get(JeecgOrderMainEntity.class, jeecgOrderMainPage.getObid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(jeecgOrderMainPage, t);
		}
	    
	    
	    //获取参数
		String obid0 = jeecgOrderMainPage.getObid();
		//String goOrderCode0 = jeecgOrderMainPage.getGoOrderCode();
		String obid1 = jeecgOrderMainPage.getObid();
		//String goOrderCode1 = jeecgOrderMainPage.getGoOrderCode();
	    
	    //-------------------------------------------------------------------
	    //[1].查询明细订单客户明细
	    String hql0 = "from JeecgOrderCustomEntity where 1 = 1 AND gorderObid = ? ";
	    List<JeecgOrderCustomEntity> jeecgOrderCustomOldList = jeecgOrderCustomEntityDao.find(hql0,obid0);
	    
	    //[2].删除明细订单客户明细
		String delhql0 = "delete from JeecgOrderCustomEntity where 1 = 1 AND gorderObid = ?  ";
		jeecgOrderMainEntityDao.executeHql(delhql0,obid0);
	    //-------------------------------------------------------------------
	    //[1].查询明细订单产品明细
	    String hql1 = "from JeecgOrderProductEntity where 1 = 1 AND gorderObid = ?  ";
	    List<JeecgOrderProductEntity> jeecgOrderProductOldList = jeecgOrderProductEntityDao.find(hql1,obid1);
	    
	    //[2].删除明细订单产品明细
		String delhql1 = "delete from JeecgOrderProductEntity where 1 = 1 AND gorderObid = ?  ";
		jeecgOrderMainEntityDao.executeHql(delhql1,obid1);
		//-------------------------------------------------------------------
		//[3].明细数据保存
		//订单客户明细
		JeecgOrderCustomEntity jeecgOrderCustomEntity = null;
		for(JeecgOrderCustomPage page:jeecgOrderCustomList){
			for(JeecgOrderCustomEntity c:jeecgOrderCustomOldList){
				if(c.getObid().equals(page.getObid())){
					jeecgOrderCustomEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(jeecgOrderCustomEntity==null){
				jeecgOrderCustomEntity = new JeecgOrderCustomEntity();
			}
			jeecgOrderCustomEntityDao.evict(jeecgOrderCustomEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, jeecgOrderCustomEntity);
			jeecgOrderCustomEntity.setObid(UUID.randomUUID().toString());
			//外键设置
			jeecgOrderCustomEntity.setGorderObid(t.getObid());
			jeecgOrderCustomEntity.setGoOrderCode(t.getGoOrderCode());
			    
			SearchSqlGenerateUtil.setUpdateMessage(jeecgOrderCustomEntity);
			jeecgOrderCustomEntityDao.save(jeecgOrderCustomEntity);
			jeecgOrderCustomEntity = null;
			//-----------------------------------------------------
		}
		//订单产品明细
		JeecgOrderProductEntity jeecgOrderProductEntity = null;
		for(JeecgOrderProductPage page:jeecgOrderProductList){
			for(JeecgOrderProductEntity c:jeecgOrderProductOldList){
				if(c.getObid().equals(page.getObid())){
					jeecgOrderProductEntity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(jeecgOrderProductEntity==null){
				jeecgOrderProductEntity = new JeecgOrderProductEntity();
			}
			jeecgOrderProductEntityDao.evict(jeecgOrderProductEntity);
			MyBeanUtils.copyBeanNotNull2Bean(page, jeecgOrderProductEntity);
			jeecgOrderProductEntity.setObid(UUID.randomUUID().toString());
			//外键设置
			jeecgOrderProductEntity.setGorderObid(t.getObid());
			jeecgOrderProductEntity.setGoOrderCode(t.getGoOrderCode());
			    
			SearchSqlGenerateUtil.setUpdateMessage(jeecgOrderProductEntity);
			jeecgOrderProductEntityDao.save(jeecgOrderProductEntity);
			jeecgOrderProductEntity = null;
			//-----------------------------------------------------
		}
	}
	
	
	public void update(JeecgOrderMainPage jeecgOrderMainPage) throws Exception {
		JeecgOrderMainEntity t = jeecgOrderMainEntityDao.get(JeecgOrderMainEntity.class, jeecgOrderMainPage.getObid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(jeecgOrderMainPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				JeecgOrderMainEntity t = jeecgOrderMainEntityDao.get(JeecgOrderMainEntity.class, id);
				if (t != null) {
					jeecgOrderMainEntityDao.delete(t);
						//获取参数
					    String obid0 = t.getObid();
					    String goOrderCode0 = t.getGoOrderCode();
					    String obid1 = t.getObid();
					    String goOrderCode1 = t.getGoOrderCode();
					    //删除明细订单客户明细
						String delhql0 = "delete from JeecgOrderCustomEntity where 1 = 1 AND gorderObid = ?  AND goOrderCode = ? ";
						jeecgOrderMainEntityDao.executeHql(delhql0,obid0,goOrderCode0);
					    //删除明细订单产品明细
						String delhql1 = "delete from JeecgOrderProductEntity where 1 = 1 AND gorderObid = ?  AND goOrderCode = ? ";
						jeecgOrderMainEntityDao.executeHql(delhql1,obid1,goOrderCode1);
				}
			}
		}
	}

	public JeecgOrderMainEntity get(JeecgOrderMainPage jeecgOrderMainPage) {
		return jeecgOrderMainEntityDao.get(JeecgOrderMainEntity.class, jeecgOrderMainPage.getObid());
	}

	public JeecgOrderMainEntity get(String obid) {
		return jeecgOrderMainEntityDao.get(JeecgOrderMainEntity.class, obid);
	}
	public List<JeecgOrderMainEntity> listAll(JeecgOrderMainPage jeecgOrderMainPage) {
		String hql = "from JeecgOrderMainEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(jeecgOrderMainPage, hql, values);
		List<JeecgOrderMainEntity> list = jeecgOrderMainEntityDao.find(hql,values);
		return list;
	}
	
	/**根据主表Key,查询子表明细：订单客户明细*/
	public List<JeecgOrderCustomPage> getJeecgOrderCustomListByFkey(String obid,String goOrderCode) {
		
		List<JeecgOrderCustomPage> rs = new ArrayList<JeecgOrderCustomPage>();
		String hql = "from JeecgOrderCustomEntity where 1 = 1 AND gorderObid = ?  AND goOrderCode = ? ";
		List<JeecgOrderCustomEntity> list = jeecgOrderCustomEntityDao.find(hql,obid,goOrderCode);
		
		for(JeecgOrderCustomEntity po:list){
			JeecgOrderCustomPage page = new JeecgOrderCustomPage();
			BeanUtils.copyProperties(po, page);
			rs.add(page);
		}
		return rs;
	}
	/**根据主表Key,查询子表明细：订单产品明细*/
	public List<JeecgOrderProductPage> getJeecgOrderProductListByFkey(String obid,String goOrderCode) {
		
		List<JeecgOrderProductPage> rs = new ArrayList<JeecgOrderProductPage>();
		String hql = "from JeecgOrderProductEntity where 1 = 1 AND gorderObid = ?  AND goOrderCode = ? ";
		List<JeecgOrderProductEntity> list = jeecgOrderProductEntityDao.find(hql,obid,goOrderCode);
		
		for(JeecgOrderProductEntity po:list){
			JeecgOrderProductPage page = new JeecgOrderProductPage();
			BeanUtils.copyProperties(po, page);
			rs.add(page);
		}
		return rs;
	}
}
