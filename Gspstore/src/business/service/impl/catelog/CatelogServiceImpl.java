package business.service.impl.catelog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.comparator.catelog.CatelogComparator;
import business.entity.catelog.CatelogEntity;
import business.page.catelog.CatelogPage;
import business.service.catelog.CatelogServiceI;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;
/**
 * 菜单Service
 * 
 * @author 张代浩
 * 
 */
@Service("catelogService")
public class CatelogServiceImpl extends BaseServiceImpl implements CatelogServiceI {

	private static final Logger logger = Logger.getLogger(CatelogServiceImpl.class);

	private BaseDaoI<CatelogEntity> catelogDao;

	public BaseDaoI<CatelogEntity> getCatelogDao() {
		return catelogDao;
	}

	@Autowired
	public void setCatelogDao(BaseDaoI<CatelogEntity> catelogDao) {
		this.catelogDao = catelogDao;
	}

	/**
	 * 统计当前菜单下有多少子节点
	 */
	private Long countChildren(String pid) {
		return catelogDao.count("select count(*) from CatelogEntity t where t.catelogEntity.catelogcode = ?", pid);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CatelogPage> treegrid(CatelogPage catelogPage) {
		List<CatelogEntity> tmenus;
		if (catelogPage != null && catelogPage.getId() != null) {
			tmenus = catelogDao.find("from CatelogEntity t where t.catelogEntity.catelogcode = ? order by t.cseq", catelogPage.getId());
		} else {
			tmenus = catelogDao.find("from CatelogEntity t where t.catelogEntity is null order by t.cseq");
		}
		return geCatelogsFromCatelogEntitys(tmenus);
	}

	private List<CatelogPage> geCatelogsFromCatelogEntitys(List<CatelogEntity> CatelogEntitys) {
		List<CatelogPage> menus = new ArrayList<CatelogPage>();
		if (CatelogEntitys != null && CatelogEntitys.size() > 0) {
			for (CatelogEntity t : CatelogEntitys) {
				CatelogPage m = new CatelogPage();
				BeanUtils.copyProperties(t, m);
				if (t.getCatelogEntity() != null) {
					m.setCpid(t.getCatelogEntity().getCatelogcode());
					m.setCpname(t.getCatelogEntity().getCname());
				}
				if (countChildren(t.getCatelogcode()) > 0) {
					m.setState("closed");
				}
				if (t.getCiconcls() == null) {
					m.setIconCls("");
				} else {
					m.setIconCls(t.getCiconcls());
				}
				menus.add(m);
			}
		}
		return menus;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(CatelogPage catelogPage, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from CatelogEntity t where t.catelogEntity is null order by t.cseq";
		if (catelogPage != null && catelogPage.getId() != null && !catelogPage.getId().trim().equals("")) {
			hql = "from CatelogEntity t where t.catelogEntity.catelogcode = ? order by t.cseq";
			param.add(catelogPage.getId());
		}
		List<CatelogEntity> l = catelogDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (CatelogEntity t : l) {
				tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(CatelogEntity t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getCatelogcode());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		//attributes.put("url", t.getCurl());
		node.setAttributes(attributes);
		if (t.getCiconcls() != null) {
			node.setIconCls(t.getCiconcls());
		} else {
			node.setIconCls("");
		}
		if (t.getCatelogEntitys() != null && t.getCatelogEntitys().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<CatelogEntity> l = new ArrayList<CatelogEntity>(t.getCatelogEntitys());
				Collections.sort(l, new CatelogComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (CatelogEntity r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(CatelogPage catelogPage) {
		CatelogEntity t = catelogDao.get(CatelogEntity.class, catelogPage.getCatelogcode());
		BeanUtils.copyProperties(catelogPage, t);
		if (catelogPage.getIconCls() != null) {
			t.setCiconcls(catelogPage.getIconCls());
		}
		if (catelogPage.getCpid() != null && !catelogPage.getCpid().equals(catelogPage.getCatelogcode())) {
			CatelogEntity pt = catelogDao.get(CatelogEntity.class, catelogPage.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 
					Set<CatelogEntity> tmenus = t.getCatelogEntitys();// 
					if (tmenus != null && tmenus.size() > 0) {
						for (CatelogEntity tmenu : tmenus) {
							if (tmenu != null) {
								tmenu.setCatelogEntity(null);
							}
						}
					}
				}
				t.setCatelogEntity(pt);
			}

		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点
	 * 
	 * @param t
	 * @param pt
	 * @return
	 */
	private boolean isDown(CatelogEntity t, CatelogEntity pt) {
		if (pt.getCatelogEntity() != null) {
			if (pt.getCatelogEntity().getCatelogcode().equals(t.getCatelogcode())) {
				return true;
			} else {
				return isDown(t, pt.getCatelogEntity());
			}
		}
		return false;
	}

	public void add(CatelogPage catelogPage) {
		CatelogEntity t = new CatelogEntity();
		BeanUtils.copyProperties(catelogPage, t);
		if (catelogPage.getIconCls() != null) {
			t.setCiconcls(catelogPage.getIconCls());
		}
		if (catelogPage.getCpid() != null && !catelogPage.getCpid().equals(catelogPage.getCatelogcode())) {
			t.setCatelogEntity(catelogDao.get(CatelogEntity.class, catelogPage.getCpid()));
		}
		catelogDao.save(t);
	}

	public void delete(CatelogPage catelogPage) {
		del(catelogPage.getCatelogcode());
	}
	
	private void del(String catelogcode) {
		CatelogEntity t = catelogDao.get(CatelogEntity.class, catelogcode);
		if (t != null) {
			Set<CatelogEntity> menus = t.getCatelogEntitys();
			if (menus != null && !menus.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (CatelogEntity tmenu : menus) {
					del(tmenu.getCatelogcode());
				}
			}
			catelogDao.delete(t);
		}
	}
	public boolean ifCanDelete(String key){
        String sql = "select count(1) as delflag from t_xx_article t where t.catelogid='"+key+"' ";
        Map<String, Object> map = catelogDao.findOneForJdbc(sql);
        Object obj = map.get("delflag");
        if (obj!=null&&!obj.equals("")&&Integer.valueOf(obj.toString())>0){
            return false;
        }else{
            return true;
        }
    }
}
