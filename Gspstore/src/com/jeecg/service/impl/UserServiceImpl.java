package com.jeecg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.model.Tauth;
import com.jeecg.model.Trole;
import com.jeecg.model.Troletauth;
import com.jeecg.model.Tuser;
import com.jeecg.model.Tusertrole;
import com.jeecg.pageModel.Auth;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.pageModel.User;
import com.jeecg.service.UserServiceI;
import com.jeecg.util.Encrypt;
import com.sys.entity.base.JeecgGroupEntity;

/**
 * 用户Service
 * 
 * @author zhangdaihao
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserServiceI {

	private BaseDaoI<Tuser> userDao;
	private BaseDaoI<Trole> roleDao;
	private BaseDaoI<Tusertrole> userroleDao;
	@Autowired
	private BaseDaoI<Tauth> authDao;
	@Autowired
	private BaseDaoI<JeecgGroupEntity> jeecgGroupEntityDao;

	public BaseDaoI<Trole> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(BaseDaoI<Trole> roleDao) {
		this.roleDao = roleDao;
	}

	public BaseDaoI<Tusertrole> getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(BaseDaoI<Tusertrole> userroleDao) {
		this.userroleDao = userroleDao;
	}

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}
    
	public List<User> listAll(User user){
	    String hql = "from Tuser t ";
	    List<User> result = new ArrayList<User>();
	    List<Tuser> users = userDao.find(hql);
	    for (Tuser t:users){
	        User temp = new User();
	        BeanUtils.copyProperties(t, temp);
	        result.add(temp);
	    }
	    return result;
	}
	
	/**
	 * 用户登录
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public User login(User user) {
		Tuser u = userDao.get("from Tuser t where t.cname=? and t.cpwd=? and status='1' ", user.getCname(), Encrypt.e(user.getCpwd()));
		if (u != null) {
			User t = new User();
			BeanUtils.copyProperties(u, t);
			return t;
		}
		return null;
	}

	/**
	 * 获得用户datagrid
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(User user) {
		DataGrid j = new DataGrid();
		j.setRows(getUsersFromTusers(find(user)));
		j.setTotal(total(user));
		return j;
	}

	private List<User> getUsersFromTusers(List<Tuser> tusers) {
		List<User> users = new ArrayList<User>();
		if (tusers != null && tusers.size() > 0) {
			for (Tuser tu : tusers) {
				User u = new User();
				BeanUtils.copyProperties(tu, u);

				Set<Tusertrole> tusertroles = tu.getTusertroles();
				String roleIds = "";
				String roleNames = "";
				if (tusertroles != null && tusertroles.size() > 0) {
					for (Tusertrole tusertrole : tusertroles) {
						if (tusertrole.getTrole() != null) {
							roleIds += "," + tusertrole.getTrole().getCid();
							roleNames += "," + tusertrole.getTrole().getCname();
						}
					}
				}
				if (roleIds.equals("")) {
					u.setRoleIds("");
					u.setRoleNames("");
				} else {
					u.setRoleIds(roleIds.substring(1));
					u.setRoleNames(roleNames.substring(1));
				}

				
				//设置隶属部门名称
				if(StringUtils.isNotEmpty(u.getOrg())){
					JeecgGroupEntity jeecgGroupEntity = jeecgGroupEntityDao.get(JeecgGroupEntity.class, u.getOrg());
					if(jeecgGroupEntity!=null){
						u.setOrgName(jeecgGroupEntity.getCname());
					}
				}
				users.add(u);
			}
		}
		return users;
	}

	private List<Tuser> find(User user) {
		String hql = "from Tuser t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);

		if (user.getSort() != null && user.getOrder() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return userDao.find(hql, user.getPage(), user.getRows(), values);
	}

	private Long total(User user) {
		String hql = "select count(*) from Tuser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(user, hql, values);
		return userDao.count(hql, values);
	}

	private String addWhere(User user, String hql, List<Object> values) {
		if (user.getCname() != null && !user.getCname().trim().equals("")) {
			hql += " and t.cname like ? ";
			values.add("%%" + user.getCname().trim() + "%%");
		}
		if (user.getRealname() != null && !user.getRealname().trim().equals("")) {
			hql += " and t.realname like ? ";
			values.add("%%" + user.getRealname().trim() + "%%");
		}
		//update-begin---Author:jeecg_爱因   Date:20130203 for:增加组织机构查询条件----------
		if(StringUtils.isNotBlank(user.getOrg())){
			hql += " and t.org like ? ";
			values.add(user.getOrg()+"%");
		}
		//update-end---Author:jeecg_爱因   Date:20130203 for:增加组织机构查询条件----------
		if (user.getCcreatedatetimeStart() != null) {
			hql += " and t.ccreatedatetime>=? ";
			values.add(user.getCcreatedatetimeStart());
		}
		if (user.getCcreatedatetimeEnd() != null) {
			hql += " and t.ccreatedatetime<=? ";
			values.add(user.getCcreatedatetimeEnd());
		}
		if (user.getCmodifydatetimeStart() != null) {
			hql += " and t.cmodifydatetime>=? ";
			values.add(user.getCmodifydatetimeStart());
		}
		if (user.getCmodifydatetimeEnd() != null) {
			hql += " and t.cmodifydatetime<=? ";
			values.add(user.getCmodifydatetimeEnd());
		}
		return hql;
	}

	/**
	 * 获得用户下拉列表
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> loginCombobox(User user) {
		String q = "";
		if (user != null && user.getQ() != null) {
			q = user.getQ().trim();
		}
		return getUsersFromTusers(userDao.find("from Tuser t where t.cname like ?", 1, 10, "%%" + q.trim() + "%%"));
	}

	/**
	 * 用户注册
	 */
	public void reg(User user) {
		Tuser u = new Tuser();
		u.setCid(UUID.randomUUID().toString());
		u.setCname(user.getCname());
		u.setCpwd(Encrypt.e(user.getCpwd()));
		u.setCcreatedatetime(new Date());
		userDao.save(u);
	}

	/**
	 * 添加用户
	 */
	public void add(User user) {
		Tuser u = new Tuser();
		u.setCid(user.getCid());
		u.setCname(user.getCname());
		u.setRealname(user.getRealname());
		u.setMail(user.getMail());
		u.setMobile(user.getMobile());
		u.setOrg(user.getOrg());
		u.setStatus(user.getStatus());
		u.setUsertype(user.getUsertype());
		u.setCpwd(Encrypt.e(user.getCpwd()));
		u.setCcreatedatetime(new Date());
		userDao.save(u);

		saveUserRole(user, u);
	}

	/**
	 * 保存用户和角色的关系
	 * 
	 * @param user
	 * @param u
	 */
	private void saveUserRole(User user, Tuser u) {
		if (user.getRoleIds() != null) {
			for (String id : user.getRoleIds().split(",")) {
				Tusertrole tusertrole = new Tusertrole();
				tusertrole.setCid(UUID.randomUUID().toString());
				tusertrole.setTrole(roleDao.get(Trole.class, id.trim()));
				tusertrole.setTuser(u);
				userroleDao.save(tusertrole);
			}
		}
	}

	/**
	 * 更新用户
	 */
	public void update(User user) {
		Tuser u = userDao.get(Tuser.class, user.getCid());
		BeanUtils.copyProperties(user, u, new String[] { "cid", "cpwd" });
		if (user.getCcreatedatetime() == null) {
			u.setCcreatedatetime(new Date());
		}
		if (user.getCmodifydatetime() == null) {
			u.setCmodifydatetime(new Date());
		}
		if (user.getMail() == null) {
			u.setMail(u.getMail());
		}

		userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", u);
		saveUserRole(user, u);
	}

	/**
	 * 删除一个或多个用户
	 */
	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				if (!id.trim().equals("0")) {
					Tuser user = userDao.get(Tuser.class, id.trim());
					userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", user);
					userDao.delete(user);
				}
			}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Auth> getAuths(User user) {
		List<Auth> auths = new ArrayList<Auth>();
		Tuser u = userDao.get(Tuser.class, user.getCid());
		//----------------------------------------------------------------
		//update-begin--Author:zhangdaihao  Date:20130205 for：树机构调整
		if (u.getCname().equals("admin")) {// admin用户加载所有的根菜单
			List<Tauth> tauths = authDao.find("from Tauth order by cseq asc");
			for(Tauth tauth:tauths){
				Auth a = wrapAuth(tauth);
				auths.add(a);
			}
			return auths;
		}
		//update-end--Author:zhangdaihao  Date:20130205 for：树机构调整
		//----------------------------------------------------------------
		if (u != null) {
			Set<Tusertrole> tusertroles = u.getTusertroles();
			if (tusertroles != null && tusertroles.size() > 0) {
				for (Tusertrole tusertrole : tusertroles) {
					Trole trole = tusertrole.getTrole();
					if (trole != null) {
						Set<Troletauth> troletauths = trole.getTroletauths();
						if (troletauths != null && troletauths.size() > 0) {
							for (Troletauth troletauth : troletauths) {
								Tauth tauth = troletauth.getTauth();
								if (tauth != null) {
									Auth a = wrapAuth(tauth);
									this.addSuperiorAuth(auths, a);
									if (!isAuthContains(auths, a))
										auths.add(a);
								}
							}
						}
					}
				}
			}
		}
		return auths;
	}

	/**
	 * 组装权限 add Author:gaoxingang  Date:20130308 
	 * @param tauth
	 * @return
	 */
	private Auth wrapAuth(Tauth tauth) {
		Auth a = new Auth();
		a.setCid(tauth.getCid());
		a.setCname(tauth.getCname());
		a.setCurl(tauth.getCurl());
		a.setCiconCls(tauth.getCiconcls());
		if (tauth.getTauth() != null) {
			a.setCpid(tauth.getTauth().getCid());
			a.setCpname(tauth.getTauth().getCname());
		}
		return a;
	}
	
	/**
	 * 增加当前权限上级权限 add Author:gaoxingang  Date:20130308 
	 * @param auths
	 * @param auth
	 */
	private void addSuperiorAuth(List<Auth> auths, Auth auth) {
		String cpid = auth.getCpid();
		if (String.valueOf("0").equals(cpid))
			return;
		if (StringUtils.isNotBlank(cpid)) {
			Tauth tauth = authDao.get(Tauth.class, cpid);
			Auth a = wrapAuth(tauth);
			if (!isAuthContains(auths, a))
				auths.add(a);
		}
	}
	
	/**
	 * 判断当前权限集合是否包含某个权限 add Author:gaoxingang  Date:20130308 
	 * @param auths
	 * @param a
	 * @return
	 */
	private static boolean isAuthContains(List<Auth> auths, Auth a) {
		boolean exist = false;
		for (Auth _auth : auths) {
			if (_auth.getCid().equals(a.getCid())) {
				exist = true;
				break;
			}
		    else {
		    	continue;
		    }
		}
		return exist;
	}


	public void modifyPwd(User user) {
		if (user != null && user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id.trim());
				u.setCpwd(Encrypt.e(user.getCpwd()));
				u.setCmodifydatetime(new Date());
			}
		}
	}

	public void modifyUserRole(User user) {
		if (user != null && user.getIds() != null) {
			for (String id : user.getIds().split(",")) {
				Tuser u = userDao.get(Tuser.class, id.trim());
				u.setCmodifydatetime(new Date());

				userroleDao.executeHql("delete Tusertrole t where t.tuser = ?", u);
				saveUserRole(user, u);
			}
		}
	}

	public boolean modifySelfPwd(User user) {
		boolean b = false;
		if (user != null && user.getCid() != null && user.getOldPwd() != null && user.getCpwd() != null) {
			Tuser u = userDao.get(Tuser.class, user.getCid().trim());

			if (u != null && u.getCpwd().equals(Encrypt.e(user.getOldPwd()))) {// 如果找到了这个用户，并且原密码正确，那么就修改新密码
				u.setCpwd(Encrypt.e(user.getCpwd()));
				u.setCmodifydatetime(new Date());

				b = true;
			}
		}
		return b;
	}

}
