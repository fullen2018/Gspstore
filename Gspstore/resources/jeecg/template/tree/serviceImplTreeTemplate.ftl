package ${bussiPackage}.service.impl.${entityPackage};

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

import ${bussiPackage}.comparator.${entityPackage}.${entityName}Comparator;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
import ${bussiPackage}.service.${entityPackage}.${entityName}ServiceI;

<#include "/include/serviceImplInclude.ftl">

/**
 * 菜单Service
 * 
 * @author 张代浩
 * 
 */
@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseServiceImpl implements ${entityName}ServiceI {

	private static final Logger logger = Logger.getLogger(${entityName}ServiceImpl.class);

	private BaseDaoI<${entityName}Entity> ${entityName?uncap_first}Dao;

	public BaseDaoI<${entityName}Entity> get${entityName}Dao() {
		return ${entityName?uncap_first}Dao;
	}

	@Autowired
	public void set${entityName}Dao(BaseDaoI<${entityName}Entity> ${entityName?uncap_first}Dao) {
		this.${entityName?uncap_first}Dao = ${entityName?uncap_first}Dao;
	}

	/**
	 * 统计当前菜单下有多少子节点
	 */
	private Long countChildren(String pid) {
		return ${entityName?uncap_first}Dao.count("select count(*) from ${entityName}Entity t where t.${entityName?uncap_first}Entity.${jeecg_table_id} = ?", pid);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<${entityName}Page> treegrid(${entityName}Page ${entityName?uncap_first}Page) {
		List<${entityName}Entity> tmenus;
		if (${entityName?uncap_first}Page != null && ${entityName?uncap_first}Page.getId() != null) {
			tmenus = ${entityName?uncap_first}Dao.find("from ${entityName}Entity t where t.${entityName?uncap_first}Entity.${jeecg_table_id} = ? order by t.cseq", ${entityName?uncap_first}Page.getId());
		} else {
			tmenus = ${entityName?uncap_first}Dao.find("from ${entityName}Entity t where t.${entityName?uncap_first}Entity is null order by t.cseq");
		}
		return ge${entityName}sFrom${entityName}Entitys(tmenus);
	}

	private List<${entityName}Page> ge${entityName}sFrom${entityName}Entitys(List<${entityName}Entity> ${entityName}Entitys) {
		List<${entityName}Page> menus = new ArrayList<${entityName}Page>();
		if (${entityName}Entitys != null && ${entityName}Entitys.size() > 0) {
			for (${entityName}Entity t : ${entityName}Entitys) {
				${entityName}Page m = new ${entityName}Page();
				BeanUtils.copyProperties(t, m);
				if (t.get${entityName}Entity() != null) {
					m.setCpid(t.get${entityName}Entity().get${jeecg_table_id?cap_first}());
					m.setCpname(t.get${entityName}Entity().getCname());
				}
				if (countChildren(t.get${jeecg_table_id?cap_first}()) > 0) {
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
	public List<TreeNode> tree(${entityName}Page ${entityName?uncap_first}Page, Boolean b) {
		List<Object> param = new ArrayList<Object>();
		String hql = "from ${entityName}Entity t where t.${entityName?uncap_first}Entity is null order by t.cseq";
		if (${entityName?uncap_first}Page != null && ${entityName?uncap_first}Page.getId() != null && !${entityName?uncap_first}Page.getId().trim().equals("")) {
			hql = "from ${entityName}Entity t where t.${entityName?uncap_first}Entity.${jeecg_table_id} = ? order by t.cseq";
			param.add(${entityName?uncap_first}Page.getId());
		}
		List<${entityName}Entity> l = ${entityName?uncap_first}Dao.find(hql, param);
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (${entityName}Entity t : l) {
				tree.add(tree(t, b));
		}
		return tree;
	}

	private TreeNode tree(${entityName}Entity t, boolean recursive) {
		TreeNode node = new TreeNode();
		node.setId(t.get${jeecg_table_id?cap_first}());
		node.setText(t.getCname());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("url", t.getCurl());
		node.setAttributes(attributes);
		if (t.getCiconcls() != null) {
			node.setIconCls(t.getCiconcls());
		} else {
			node.setIconCls("");
		}
		if (t.get${entityName}Entitys() != null && t.get${entityName}Entitys().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<${entityName}Entity> l = new ArrayList<${entityName}Entity>(t.get${entityName}Entitys());
				Collections.sort(l, new ${entityName}Comparator());// 排序
				List<TreeNode> children = new ArrayList<TreeNode>();
				for (${entityName}Entity r : l) {
					TreeNode tn = tree(r, true);
					children.add(tn);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public void edit(${entityName}Page ${entityName?uncap_first}Page) {
		${entityName}Entity t = ${entityName?uncap_first}Dao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
		BeanUtils.copyProperties(${entityName?uncap_first}Page, t);
		if (${entityName?uncap_first}Page.getIconCls() != null) {
			t.setCiconcls(${entityName?uncap_first}Page.getIconCls());
		}
		if (${entityName?uncap_first}Page.getCpid() != null && !${entityName?uncap_first}Page.getCpid().equals(${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}())) {
			${entityName}Entity pt = ${entityName?uncap_first}Dao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.getCpid());
			if (pt != null) {
				if (isDown(t, pt)) {// 
					Set<${entityName}Entity> tmenus = t.get${entityName}Entitys();// 
					if (tmenus != null && tmenus.size() > 0) {
						for (${entityName}Entity tmenu : tmenus) {
							if (tmenu != null) {
								tmenu.set${entityName}Entity(null);
							}
						}
					}
				}
				t.set${entityName}Entity(pt);
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
	private boolean isDown(${entityName}Entity t, ${entityName}Entity pt) {
		if (pt.get${entityName}Entity() != null) {
			if (pt.get${entityName}Entity().get${jeecg_table_id?cap_first}().equals(t.get${jeecg_table_id?cap_first}())) {
				return true;
			} else {
				return isDown(t, pt.get${entityName}Entity());
			}
		}
		return false;
	}

	public void add(${entityName}Page ${entityName?uncap_first}Page) {
		${entityName}Entity t = new ${entityName}Entity();
		BeanUtils.copyProperties(${entityName?uncap_first}Page, t);
		if (${entityName?uncap_first}Page.getIconCls() != null) {
			t.setCiconcls(${entityName?uncap_first}Page.getIconCls());
		}
		if (${entityName?uncap_first}Page.getCpid() != null && !${entityName?uncap_first}Page.getCpid().equals(${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}())) {
			t.set${entityName}Entity(${entityName?uncap_first}Dao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.getCpid()));
		}
		${entityName?uncap_first}Dao.save(t);
	}

	public void delete(${entityName}Page ${entityName?uncap_first}Page) {
		del(${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
	}
	
	private void del(String ${jeecg_table_id}) {
		${entityName}Entity t = ${entityName?uncap_first}Dao.get(${entityName}Entity.class, ${jeecg_table_id});
		if (t != null) {
			Set<${entityName}Entity> menus = t.get${entityName}Entitys();
			if (menus != null && !menus.isEmpty()) {
				// 说明当前菜单下面还有子菜单
				for (${entityName}Entity tmenu : menus) {
					del(tmenu.get${jeecg_table_id?cap_first}());
				}
			}
			${entityName?uncap_first}Dao.delete(t);
		}
	}
}
