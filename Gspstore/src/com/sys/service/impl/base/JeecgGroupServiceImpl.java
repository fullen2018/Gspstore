package com.sys.service.impl.base;

import java.util.ArrayList;
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

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.TreeNode;
import com.jeecg.service.impl.BaseServiceImpl;
import com.sys.entity.base.JeecgGroupEntity;
import com.sys.page.base.JeecgGroupPage;
import com.sys.service.base.JeecgGroupServiceI;
/**
 * 菜单Service
 * 
 * @author 张代浩
 * 
 */
@Service("jeecgGroupService")
public class JeecgGroupServiceImpl extends BaseServiceImpl implements JeecgGroupServiceI {

	private static final Logger logger = Logger.getLogger(JeecgGroupServiceImpl.class);
	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<JeecgGroupEntity> jeecgGroupDao;

	public BaseDaoI<JeecgGroupEntity> getJeecgGroupDao() {
		return jeecgGroupDao;
	}

	@Autowired
	public void setJeecgGroupDao(BaseDaoI<JeecgGroupEntity> jeecgGroupDao) {
		this.jeecgGroupDao = jeecgGroupDao;
	}

	/**
	 * 统计当前菜单下有多少子节点
	 */
	private Long countChildren(String pid) {
		return jeecgGroupDao.count("select count(*) from JeecgGroupEntity t where t.jeecgGroupEntity.obid = ?", pid);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<JeecgGroupPage> treegrid(JeecgGroupPage jeecgGroupPage) {
		List<JeecgGroupEntity> tmenus;
		if (jeecgGroupPage != null && jeecgGroupPage.getId() != null) {
			tmenus = jeecgGroupDao.find("from JeecgGroupEntity t where t.jeecgGroupEntity.obid = ? ", jeecgGroupPage.getId());
		} else {
			tmenus = jeecgGroupDao.find("from JeecgGroupEntity t where t.jeecgGroupEntity is null ");
		}
		return geJeecgGroupsFromJeecgGroupEntitys(tmenus);
	}

	private List<JeecgGroupPage> geJeecgGroupsFromJeecgGroupEntitys(List<JeecgGroupEntity> JeecgGroupEntitys) {
		List<JeecgGroupPage> menus = new ArrayList<JeecgGroupPage>();
		if (JeecgGroupEntitys != null && JeecgGroupEntitys.size() > 0) {
			for (JeecgGroupEntity t : JeecgGroupEntitys) {
				JeecgGroupPage m = new JeecgGroupPage();
				BeanUtils.copyProperties(t, m);
				if (t.getJeecgGroupEntity() != null) {
					m.setCpid(t.getJeecgGroupEntity().getObid());
					m.setCpname(t.getJeecgGroupEntity().getCname());
				}
				if (countChildren(t.getObid()) > 0) {
					m.setState("closed");
				}
				menus.add(m);
			}
		}
		return menus;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TreeNode> tree(JeecgGroupPage jeecgGroupPage, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from JeecgGroupEntity t where t.jeecgGroupEntity is null ";
		if (jeecgGroupPage != null && jeecgGroupPage.getId() != null && !jeecgGroupPage.getId().trim().equals("")) {
			hql = "from JeecgGroupEntity t where t.jeecgGroupEntity.obid = ? ";
			param.add(jeecgGroupPage.getId());
		}
		List<JeecgGroupEntity> l = jeecgGroupDao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (JeecgGroupEntity t : l) {
				tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(JeecgGroupEntity t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.getObid());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		node.setAttributes(attributes);
		if (t.getJeecgGroupEntitys() != null && t.getJeecgGroupEntitys().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<JeecgGroupEntity> l = new ArrayList<JeecgGroupEntity>(t.getJeecgGroupEntitys());
				//Collections.sort(l, new JeecgGroupComparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (JeecgGroupEntity r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(JeecgGroupPage jeecgGroupPage) {
		JeecgGroupEntity t = jeecgGroupDao.get(JeecgGroupEntity.class, jeecgGroupPage.getObid());
		BeanUtils.copyProperties(jeecgGroupPage, t);
		if (jeecgGroupPage.getCpid() != null && !jeecgGroupPage.getCpid().equals(jeecgGroupPage.getObid())) {
			JeecgGroupEntity pt = jeecgGroupDao.get(JeecgGroupEntity.class, jeecgGroupPage.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 
					Set<JeecgGroupEntity> tmenus = t.getJeecgGroupEntitys();// 
					if (tmenus != null && tmenus.size() > 0) {
						for (JeecgGroupEntity tmenu : tmenus) {
							if (tmenu != null) {
								tmenu.setJeecgGroupEntity(null);
							}
						}
					}
				}
				t.setJeecgGroupEntity(pt);
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
	private boolean isDown(JeecgGroupEntity t, JeecgGroupEntity pt) {
		if (pt.getJeecgGroupEntity() != null) {
			if (pt.getJeecgGroupEntity().getObid().equals(t.getObid())) {
				return true;
			} else {
				return isDown(t, pt.getJeecgGroupEntity());
			}
		}
		return false;
	}

	public void add(JeecgGroupPage jeecgGroupPage) {
		JeecgGroupEntity t = new JeecgGroupEntity();
		BeanUtils.copyProperties(jeecgGroupPage, t);
		t.setObid(getNextNodeCode(jeecgGroupPage.getCpid()));
		if (jeecgGroupPage.getCpid() != null && !jeecgGroupPage.getCpid().equals(jeecgGroupPage.getObid())) {
			t.setJeecgGroupEntity(jeecgGroupDao.get(JeecgGroupEntity.class, jeecgGroupPage.getCpid()));
		}
		jeecgGroupDao.save(t);
	}

	public void delete(JeecgGroupPage jeecgGroupPage) {
		del(jeecgGroupPage.getObid());
	}
	
	private void del(String obid) {
		JeecgGroupEntity t = jeecgGroupDao.get(JeecgGroupEntity.class, obid);
		if (t != null) {
			Set<JeecgGroupEntity> menus = t.getJeecgGroupEntitys();
			if (menus != null && !menus.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (JeecgGroupEntity tmenu : menus) {
					del(tmenu.getObid());
				}
			}
			jeecgGroupDao.delete(t);
		}
	}
	

	/**
	 * 根据传入的父节点CODE，获取子节点CODE
	 */
	
	public synchronized String getNextNodeCode(String pcode){
		String nextCode = "";
		if(pcode==null)pcode="";
		String sql = "select max(obid) obid from jeecg_group where length(obid) = "+(pcode.length()+2) +" and obid like '"+pcode+"%'";
		Map mp = jdbcDao.findForMap(sql,null);
		System.out.println(mp.get("obid"));
		if(mp.get("obid")!=null&&!"".equals(mp.get("obid"))){
			nextCode = mp.get("obid").toString().trim();
			Integer nextNum = Integer.parseInt(nextCode.substring(nextCode.length()-2))+1;
			if(nextNum.toString().trim().length()==1){
				nextCode = pcode + "0"+nextNum;
			}else{
				nextCode = pcode +nextNum;
			}
		}else{
			nextCode = pcode+"01";
		}
		return nextCode;
	}
}
