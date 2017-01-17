package business.action.catelog;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;

import business.page.catelog.CatelogPage;
import business.service.catelog.CatelogServiceI;
import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import com.util.MyBeanUtils;


/**   
 * @Title: Action
 * @Description: 药品类别信息
 * @author zhangdaihao
 * @date 2013-05-13 16:40:33
 * @version V1.0   
 *
 */
@Action(value = "catelogAction", results = { @Result(name = "catelog", location = "/business/catelog/catelog.jsp") })
public class CatelogAction extends BaseAction implements ModelDriven<CatelogPage> {

	private static final Logger logger = Logger.getLogger(CatelogAction.class);

	private CatelogServiceI catelogService;

	private CatelogPage catelogPage = new CatelogPage();

	public CatelogPage getModel() {
		return catelogPage;
	}

	public CatelogServiceI getCatelogService() {
		return catelogService;
	}

	@Autowired
	public void setCatelogService(CatelogServiceI catelogService) {
		this.catelogService = catelogService;
	}

	/**
	 * 首页获得菜单树
	 */
	public void ctrlTree() {
		writeJson(catelogService.tree(catelogPage, false));
	}

	/**
	 * 跳转到菜单管理页面
	 * 
	 * @return
	 */
	public String goCatelog() {
		return "catelog";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		writeJson(catelogService.treegrid(catelogPage));
	}

	/**
	 * 获取菜单树,递归子节点
	 */
	public void treeRecursive() {
		writeJson(catelogService.tree(catelogPage, true));
	}

	/**
	 * 所有人都有权限的菜单
	 */
	public void catelogTreeRecursive() {
		writeJson(catelogService.tree(catelogPage, true));
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			catelogService.edit(catelogPage);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
			j.setObj(catelogPage.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

	/**
	 * 添加菜单
	 */
	public void add() {
		Json j = new Json();
		try {
			catelogService.add(catelogPage);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			j.setObj(catelogPage.getCpid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		writeJson(j);
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			if (catelogService.ifCanDelete(catelogPage.getIds())){
			    catelogService.delete(catelogPage);
	            j.setSuccess(true);
	            j.setMsg("删除成功！");
	            j.setObj(catelogPage.getCatelogcode());
	        }else{
	            j.setSuccess(false);
	            j.setMsg("存在关联，不能删除！");
	        }
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		writeJson(j);
	}

}
