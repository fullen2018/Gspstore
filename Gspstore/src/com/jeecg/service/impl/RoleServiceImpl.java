package com.jeecg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.model.Tauth;
import com.jeecg.model.Trole;
import com.jeecg.model.Troletauth;
import com.jeecg.model.Tusertrole;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.pageModel.Role;
import com.jeecg.service.RoleServiceI;

/**
 * 角色Service
 * 
 * @author zhangdaihao
 * 
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleServiceI {

	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<Tauth> authDao;
	private BaseDaoI<Troletauth> roleauthDao;
	private BaseDaoI<Tusertrole> userroleDao;

	public BaseDaoI<Tusertrole> getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<Tusertrole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	public BaseDaoI<Troletauth> getRoleauthDao() {
		return roleauthDao;
	}

	@Autowired
	public void setRoleauthDao(BaseDaoI<Troletauth> roleauthDao) {
		this.roleauthDao = roleauthDao;
	}

	public BaseDaoI<Tauth> getAuthDao() {
		return authDao;
	}

	@Autowired
	public void setAuthDao(BaseDaoI<Tauth> authDao) {
		this.authDao = authDao;
	}

	public BaseDaoI<Trole> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(Role role) {
		DataGrid j = new DataGrid();
		j.setRows(getRolesFromTroles(find(role)));
		j.setTotal(total(role));
		return j;
	}

	private List<Role> getRolesFromTroles(List<Trole> troles) {
		List<Role> roles = new ArrayList<Role>();
		if (troles != null && troles.size() > 0) {
			for (Trole tu : troles) {
				Role u = new Role();
				BeanUtils.copyProperties(tu, u);

				Set<Troletauth> troletauths = tu.getTroletauths();
				String authIds = "";
				String authNames = "";
				if (troletauths != null && troletauths.size() > 0) {
					for (Troletauth troletauth : troletauths) {
						if (troletauth.getTauth() != null) {
							authIds += "," + troletauth.getTauth().getCid();
							authNames += "," + troletauth.getTauth().getCname();
						}
					}
				}
				if (authIds.equals("")) {
					u.setAuthIds("");
					u.setAuthNames("");
				} else {
					u.setAuthIds(authIds.substring(1));
					u.setAuthNames(authNames.substring(1));
				}

				roles.add(u);
			}
		}
		return roles;
	}

	private List<Trole> find(Role role) {
		String hql = "from Trole t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(role, hql, values);

		if (role.getSort() != null && role.getOrder() != null) {
			hql += " order by " + role.getSort() + " " + role.getOrder();
		}
		return roleDao.find(hql, role.getPage(), role.getRows(), values);
	}

	private Long total(Role role) {
		String hql = "select count(*) from Trole t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(role, hql, values);
		return roleDao.count(hql, values);
	}

	private String addWhere(Role role, String hql, List<Object> values) {
		return hql;
	}

	public void add(Role role) {
		Trole r = new Trole();
		BeanUtils.copyProperties(role, r);
		roleDao.save(r);

		saveRoleAuth(role, r);

	}

	/**
	 * 保存Trole和Tauth的关系
	 * 
	 * @param role
	 * @param r
	 */
	private void saveRoleAuth(Role role, Trole r) {
		if (role.getAuthIds() != null) {
			for (String id : role.getAuthIds().split(",")) {
				Troletauth troletauth = new Troletauth();
				troletauth.setCid(UUID.randomUUID().toString());
				troletauth.setTauth(authDao.get(Tauth.class, id.trim()));
				troletauth.setTrole(r);
				roleauthDao.save(troletauth);
			}
		}
	}

	public void update(Role role) {
		Trole r = roleDao.get(Trole.class, role.getCid());
		BeanUtils.copyProperties(role, r, new String[] { "cid" });

		roleauthDao.executeHql("delete Troletauth t where t.trole = ?", r);
		saveRoleAuth(role, r);

	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Trole r = roleDao.get(Trole.class, id.trim());
				if (r != null) {
					roleauthDao.executeHql("delete Troletauth t where t.trole = ?", r);
					userroleDao.executeHql("delete Tusertrole t where t.trole = ?", r);
					roleDao.delete(r);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Role> combobox() {
		return getRolesFromTroles(findAllTrole());
	}

	private List<Trole> findAllTrole() {
		String hql = "from Trole ";
		return roleDao.find(hql);
	}

}
