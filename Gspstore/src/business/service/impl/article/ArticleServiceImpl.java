package business.service.impl.article;

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
import business.page.article.ArticlePage;
import business.service.article.ArticleServiceI;
import business.util.FormatUtil;
import business.util.MapUtil;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 品种信息
 * @author zhangdaihao
 * @date 2013-05-12 15:00:00
 * @version V1.0   
 *
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<ArticleEntity> articleEntityDao;

	public BaseDaoI<ArticleEntity> getArticleEntityDao() {
		return articleEntityDao;
	}
	@Autowired
	public void setArticleEntityDao(BaseDaoI<ArticleEntity> articleEntityDao) {
		this.articleEntityDao = articleEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(ArticlePage articlePage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(articlePage)));
		j.setTotal(total(articlePage));
		return j;
	}

	private List<ArticlePage> getPagesFromEntitys(List<ArticleEntity> articleEntitys) {
		List<ArticlePage> articlePages = new ArrayList<ArticlePage>();
		if (articleEntitys != null && articleEntitys.size() > 0) {
			for (ArticleEntity tb : articleEntitys) {
				ArticlePage b = new ArticlePage();
				BeanUtils.copyProperties(tb, b);
				articlePages.add(b);
			}
		}
		return articlePages;
	}

	private List<ArticleEntity> find(ArticlePage articlePage) {
		String hql = "from ArticleEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		
		hql = addWhere(articlePage, hql, values);
		
		if (articlePage.getSort() != null && articlePage.getOrder() != null) {
			hql += " order by " + articlePage.getSort() + " " + articlePage.getOrder();
		}
		return articleEntityDao.find(hql, articlePage.getPage(), articlePage.getRows(), values);
	}

	private Long total(ArticlePage articlePage) {
		String hql = "select count(*) from ArticleEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(articlePage, hql, values);
		return articleEntityDao.count(hql, values);
	}

	private String addWhere(ArticlePage articlePage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		ArticleEntity articleEntity = new ArticleEntity();
		if (articlePage.getArticlealias()!=null&&!articlePage.getArticlealias().equals(""))
	           articlePage.setArticlealias("*"+articlePage.getArticlealias()+"*");
	    if (articlePage.getArticlename()!=null&&!articlePage.getArticlename().equals(""))
	           articlePage.setArticlename("*"+articlePage.getArticlename()+"*");
		BeanUtils.copyProperties(articlePage, articleEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, articleEntity);
		hql = hqlbf.toString();
		return hql;
	}

	public void add(ArticlePage articlePage) {
		ArticleEntity t = new ArticleEntity();
		BeanUtils.copyProperties(articlePage, t);
		Integer num = getMaxArticleNum();
        t.setOrdernum(num);
        t.setArticleid(FormatUtil.convertIntToString(5, num));
		t.setCreatedate(new Date());
		t.setModifydate(new Date());
		articleEntityDao.save(t);
	}

	public void update(ArticlePage articlePage) throws Exception {
		ArticleEntity t = articleEntityDao.get(ArticleEntity.class, articlePage.getArticleid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(articlePage, t);
			t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from ArticleEntity where articleid = '"+id+"'";
				ArticleEntity t = articleEntityDao.get(hql);
				if (t != null) {
					articleEntityDao.delete(t);
				}
			}
		}
	}

	public ArticleEntity get(ArticlePage articlePage) {
		return articleEntityDao.get(ArticleEntity.class, articlePage.getArticleid());
	}

	public ArticleEntity get(java.lang.String articleid) {
		return articleEntityDao.get(ArticleEntity.class, articleid);
	}
	public List<ArticleEntity> listAll(ArticlePage articlePage) {
		String hql = "from ArticleEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(articlePage, hql, values);
		List<ArticleEntity> list = articleEntityDao.find(hql,values);
		return list;
	}
	
	public Integer getMaxArticleNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_xx_article ae ";
        Map<String, Object> map = articleEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
	
	public boolean ifCanDelete(String key){
	    String sql = "select count(1) as delflag from t_rk_stockin_detail t where t.articleid='"+key+"' ";
	    Map<String, Object> map = articleEntityDao.findOneForJdbc(sql);
	    Object obj = map.get("delflag");
	    if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
	        return false;
	    }else{
	        return true;
	    }
	}
	
	public List<Map<String,Object>> getArticleForStockIn(){
        String sql = " SELECT   t_xx_article.articleid,t_xx_article.articlename,t_xx_article.articlealias,p.param_name as unitname, " +
                " t_xx_article.articlespec,t_xx_article.factory,t_xx_article.barcode,t_xx_article.retailprice," +
                " t_xx_article.pfprice,t_xx_article.unit " +
                " FROM  t_xx_article " +
                " inner join jeecg_dict_param p on p.param_level='003' and p.PARAM_VALUE=t_xx_article.unit " +
                " order by t_xx_article.articlealias ";
       List<Map<String,Object>> allStock = MapUtil.changeMapListToLower(articleEntityDao.findForJdbc(sql));
       return allStock;
    }
}
