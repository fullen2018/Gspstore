package com.sys.service.impl.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.core.aop.cache.Ehcache;
import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.sys.entity.base.DictParamEntity;
import com.sys.page.base.DictParamPage;
import com.sys.service.base.DictParamServiceI;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 数据字典
 * @author zhangdaihao
 * @date 2011-11-26 10:46:04
 * @version V1.0   
 *
 */
@Service("dictParamService")
public class DictParamServiceImpl extends BaseServiceImpl implements DictParamServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<DictParamEntity> dictParamEntityDao;

	public BaseDaoI<DictParamEntity> getDictParamEntityDao() {
		return dictParamEntityDao;
	}
	@Autowired
	public void setDictParamEntityDao(BaseDaoI<DictParamEntity> dictParamEntityDao) {
		this.dictParamEntityDao = dictParamEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(DictParamPage dictParamPage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(dictParamPage)));
		j.setTotal(total(dictParamPage));
		return j;
	}

	private List<DictParamPage> getPagesFromEntitys(List<DictParamEntity> dictParamEntitys) {
		List<DictParamPage> dictParamPages = new ArrayList<DictParamPage>();
		if (dictParamEntitys != null && dictParamEntitys.size() > 0) {
			for (DictParamEntity tb : dictParamEntitys) {
				DictParamPage b = new DictParamPage();
				BeanUtils.copyProperties(tb, b);
				dictParamPages.add(b);
			}
		}
		return dictParamPages;
	}

	private List<DictParamEntity> find(DictParamPage dictParamPage) {
		String hql = "from DictParamEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(dictParamPage, hql, values);

		if (dictParamPage.getSort() != null && dictParamPage.getOrder() != null) {
			hql += " order by " + dictParamPage.getSort() + " " + dictParamPage.getOrder();
		}
		return dictParamEntityDao.find(hql, dictParamPage.getPage(), dictParamPage.getRows(), values);
	}

	private Long total(DictParamPage dictParamPage) {
		String hql = "select count(*) from DictParamEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(dictParamPage, hql, values);
		return dictParamEntityDao.count(hql, values);
	}

	private String addWhere(DictParamPage dictParamPage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, dictParamPage);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (dictParamPage.getCcreatedatetimeStart() != null) {
			hql += " and createDt>=? ";
			values.add(dictParamPage.getCcreatedatetimeStart());
		}
		if (dictParamPage.getCcreatedatetimeEnd() != null) {
			hql += " and createDt<=? ";
			values.add(dictParamPage.getCcreatedatetimeEnd());
		}
		if (dictParamPage.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(dictParamPage.getCmodifydatetimeStart());
		}
		if (dictParamPage.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(dictParamPage.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(DictParamPage dictParamPage) {
		if (dictParamPage.getObid() == null || dictParamPage.getObid().trim().equals("")) {
			dictParamPage.setObid(UUID.randomUUID().toString());
		}
		DictParamEntity t = new DictParamEntity();
		BeanUtils.copyProperties(dictParamPage, t);
		dictParamEntityDao.save(t);
	}

	public void update(DictParamPage dictParamPage) throws Exception {
		DictParamEntity t = dictParamEntityDao.get(DictParamEntity.class, dictParamPage.getObid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(dictParamPage, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				DictParamEntity t = dictParamEntityDao.get(DictParamEntity.class, id);
				if (t != null) {
					dictParamEntityDao.delete(t);
				}
			}
		}
	}

	public DictParamEntity get(DictParamPage dictParamPage) {
		return dictParamEntityDao.get(DictParamEntity.class, dictParamPage.getObid());
	}

	public DictParamEntity get(String obid) {
		return dictParamEntityDao.get(DictParamEntity.class, obid);
	}
	
	public List<DictParamEntity> listAll(DictParamPage dictParamPage) {
		String hql = "from DictParamEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(dictParamPage, hql, values);
		List<DictParamEntity> list = dictParamEntityDao.find(hql,values);
		return list;
	}
	
	@Ehcache
	public List<DictParamEntity> listAllByParamLevel(String paramLevel) {
		String hql = "from DictParamEntity where paramLevel = ? order by param_view_order asc";
		List<DictParamEntity> list = dictParamEntityDao.find(hql,paramLevel);
		return list;
	}
	@Ehcache
	public DictParamEntity getDictName(String paramLevel, String paramValue) {
		String hql = "from DictParamEntity where paramLevel = ? and paramValue = ?";
		DictParamEntity po = dictParamEntityDao.get(hql, paramLevel,paramValue);
		return po;
	}
	public List<DictParamEntity> listAll() {
		String hql = "from DictParamEntity";
		List<DictParamEntity> list = dictParamEntityDao.find(hql);
		return list;
	}
	public Map<String,Object> getDictionaryMap(String paramLevel){
        Map<String,Object> dicMap = new HashMap<String,Object>();
        DictParamPage dpp = new DictParamPage();
        dpp.setParamLevel(paramLevel);
        List<DictParamEntity> dics = this.listAll(dpp);
        for (DictParamEntity dpe:dics){
            dicMap.put(dpp.getParamValue(), dpp.getParamName());
        }
        return dicMap;
    }
}
