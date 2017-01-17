package business.service.article;

import business.entity.article.ArticleEntity;
import business.page.article.ArticlePage;
import java.util.List;
import java.util.Map;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 品种信息
 * @author zhangdaihao
 * @date 2013-05-12 15:00:00
 * @version V1.0   
 *
 */
public interface ArticleServiceI extends BaseServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(ArticlePage articlePage);

	/**
	 * 添加
	 * 
	 * @param articlePage
	 */
	public void add(ArticlePage articlePage);

	/**
	 * 修改
	 * 
	 * @param articlePage
	 */
	public void update(ArticlePage articlePage) throws Exception;

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 获得
	 * 
	 * @param Article
	 * @return
	 */
	public ArticleEntity get(ArticlePage articlePage);
	
	
	/**
	 * 获得
	 * 
	 * @param articleid
	 * @return
	 */
	public ArticleEntity get(java.lang.String articleid);
	
	/**
	 * 获取所有数据
	 */
	public List<ArticleEntity> listAll(ArticlePage articlePage);
	
	public Integer getMaxArticleNum();
    
	public boolean ifCanDelete(String key);
	
	public List<Map<String,Object>> getArticleForStockIn();
}
