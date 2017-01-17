package business.service.catelog;

import java.util.List;

import business.page.catelog.CatelogPage;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;
import com.jeecg.pageModel.TreeNode;
/**   
 * @Title: Service
 * @Description: 会计科目
 * @author zhangdaihao
 * @date 2013-01-20 21:31:48
 * @version V1.0   
 *
 */
public interface CatelogServiceI extends BaseServiceI {

	/**
	 * 获得菜单treegrid
	 * 
	 * @param catelogPage
	 * @return
	 */
	public List<CatelogPage> treegrid(CatelogPage catelogPage);

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(CatelogPage catelogPage, Boolean b);

	/**
	 * 编辑菜单
	 * 
	 * @param catelogPage
	 */
	public void edit(CatelogPage catelogPage);

	/**
	 * 添加菜单
	 * 
	 * @param catelogPage
	 */
	public void add(CatelogPage catelogPage);

	/**
	 * 删除菜单
	 * 
	 * @param catelogPage
	 */
	public void delete(CatelogPage catelogPage);
	public boolean ifCanDelete(String key);
}
