package ${bussiPackage}.service.impl.${entityPackage};

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import ${bussiPackage}.page.${entityPackage}.${entityName}Page;
import ${bussiPackage}.service.${entityPackage}.${entityName}ServiceI;
<#list subTab as sub>
import ${bussiPackage}.page.${sub.entityPackage}.${sub.entityName}Page;
import ${bussiPackage}.entity.${sub.entityPackage}.${sub.entityName}Entity;

<#include "/include/serviceImplInclude.ftl">
</#list>
/**   
 * @Title: ServiceImpl
 * @Description: ${ftl_description}
 * @author zhangdaihao
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl extends BaseServiceImpl implements ${entityName}ServiceI {

	//SQL 使用JdbcDao
	@Autowired
	private JdbcDao jdbcDao;
	private BaseDaoI<${entityName}Entity> ${entityName?uncap_first}EntityDao;
	<#list subTab as sub>
	@Autowired
	private BaseDaoI<${sub.entityName}Entity> ${sub.entityName?uncap_first}EntityDao;
	</#list>
	public BaseDaoI<${entityName}Entity> get${entityName}EntityDao() {
		return ${entityName?uncap_first}EntityDao;
	}
	@Autowired
	public void set${entityName}EntityDao(BaseDaoI<${entityName}Entity> ${entityName?uncap_first}EntityDao) {
		this.${entityName?uncap_first}EntityDao = ${entityName?uncap_first}EntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(${entityName}Page ${entityName?uncap_first}Page) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(${entityName?uncap_first}Page)));
		j.setTotal(total(${entityName?uncap_first}Page));
		return j;
	}

	private List<${entityName}Page> getPagesFromEntitys(List<${entityName}Entity> ${entityName?uncap_first}Entitys) {
		List<${entityName}Page> ${entityName?uncap_first}Pages = new ArrayList<${entityName}Page>();
		if (${entityName?uncap_first}Entitys != null && ${entityName?uncap_first}Entitys.size() > 0) {
			for (${entityName}Entity tb : ${entityName?uncap_first}Entitys) {
				${entityName}Page b = new ${entityName}Page();
				BeanUtils.copyProperties(tb, b);
				${entityName?uncap_first}Pages.add(b);
			}
		}
		return ${entityName?uncap_first}Pages;
	}

	private List<${entityName}Entity> find(${entityName}Page ${entityName?uncap_first}Page) {
		String hql = "from ${entityName}Entity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(${entityName?uncap_first}Page, hql, values);

		if (${entityName?uncap_first}Page.getSort() != null && ${entityName?uncap_first}Page.getOrder() != null) {
			hql += " order by " + ${entityName?uncap_first}Page.getSort() + " " + ${entityName?uncap_first}Page.getOrder();
		}
		return ${entityName?uncap_first}EntityDao.find(hql, ${entityName?uncap_first}Page.getPage(), ${entityName?uncap_first}Page.getRows(), values);
	}

	private Long total(${entityName}Page ${entityName?uncap_first}Page) {
		String hql = "select count(*) from ${entityName}Entity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(${entityName?uncap_first}Page, hql, values);
		return ${entityName?uncap_first}EntityDao.count(hql, values);
	}

	private String addWhere(${entityName}Page ${entityName?uncap_first}Page, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		${entityName}Entity ${entityName?uncap_first}Entity = new ${entityName}Entity();
		BeanUtils.copyProperties(${entityName?uncap_first}Page, ${entityName?uncap_first}Entity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, ${entityName?uncap_first}Entity);
		hql = hqlbf.toString();
		//-----------------------------------------------------
		if (${entityName?uncap_first}Page.getCcreatedatetimeStart() != null) {
			hql += " and createDt>=? ";
			values.add(${entityName?uncap_first}Page.getCcreatedatetimeStart());
		}
		if (${entityName?uncap_first}Page.getCcreatedatetimeEnd() != null) {
			hql += " and createDt<=? ";
			values.add(${entityName?uncap_first}Page.getCcreatedatetimeEnd());
		}
		if (${entityName?uncap_first}Page.getCmodifydatetimeStart() != null) {
			hql += " and modifyDt>=? ";
			values.add(${entityName?uncap_first}Page.getCmodifydatetimeStart());
		}
		if (${entityName?uncap_first}Page.getCmodifydatetimeEnd() != null) {
			hql += " and modifyDt<=? ";
			values.add(${entityName?uncap_first}Page.getCmodifydatetimeEnd());
		}
		return hql;
	}

	public void add(${entityName}Page ${entityName?uncap_first}Page) {
		${entityName}Entity t = new ${entityName}Entity();
		BeanUtils.copyProperties(${entityName?uncap_first}Page, t);
		${entityName?uncap_first}EntityDao.save(t);
	}

	
	/**
	 * 保存：一对多
	 */
	public void addMain(${entityName}Page ${entityName?uncap_first}Page,<#list subTab as sub>List<${sub.entityName}Page> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>)  throws Exception{
		//[1].主表保存
		${entityName}Entity t = new ${entityName}Entity();
		BeanUtils.copyProperties(${entityName?uncap_first}Page, t);
		${entityName?uncap_first}EntityDao.save(t);
		//[2].明细数据保存
		<#list subTab as sub>
		//${sub.ftlDescription}
		for(${sub.entityName}Page page:${sub.entityName?uncap_first}List){
			${sub.entityName}Entity ${sub.entityName?uncap_first} = new ${sub.entityName}Entity();
			BeanUtils.copyProperties(page, ${sub.entityName?uncap_first});
			
			<#list sub.foreignKeys as key>
			//外键设置
			<#if key?lower_case?index_of("${jeecg_table_id}")!=-1>
			${sub.entityName?uncap_first}.set${key}(t.get${jeecg_table_id?cap_first}());
			<#else>
			${sub.entityName?uncap_first}.set${key}(t.get${key}());
			</#if>
			</#list>
			
			${sub.entityName?uncap_first}EntityDao.save(${sub.entityName?uncap_first});
		}
		</#list>
	}
	/**
	 * 修改：一对多
	 */
	public void editMain(${entityName}Page ${entityName?uncap_first}Page,<#list subTab as sub>List<${sub.entityName}Page> ${sub.entityName?uncap_first}List<#if sub_has_next>,</#if></#list>)  throws Exception{
		//[1].主表保存
		${entityName}Entity t = ${entityName?uncap_first}EntityDao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(${entityName?uncap_first}Page, t);
		}
	    
	    
	    //获取参数
	    <#list subTab as sub>
		    <#list sub.foreignKeys as key>
		    <#if key?lower_case?index_of("${jeecg_table_id}")!=-1>
		Object ${jeecg_table_id}${sub_index} = ${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}();
		    <#else>
		Object ${key?uncap_first}${sub_index} = ${entityName?uncap_first}Page.get${key}();
		    </#if>
		    </#list>
	    </#list>
	    
	    <#list subTab as sub>
	    //-------------------------------------------------------------------
	    //[1].查询明细${sub.ftlDescription}
	    String hql${sub_index} = "from ${sub.entityName}Entity where 1 = 1<#list sub.foreignKeys as key> AND ${key?uncap_first} = ? </#list>";
	    List<${sub.entityName}Entity> ${sub.entityName?uncap_first}OldList = ${sub.entityName?uncap_first}EntityDao.find(hql${sub_index},<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${jeecg_table_id}${sub_index}<#else>${key?uncap_first}${sub_index}</#if><#if key_has_next>,</#if></#list>);
	    
	    //[2].删除明细${sub.ftlDescription}
		String delhql${sub_index} = "delete from ${sub.entityName}Entity where 1 = 1<#list sub.foreignKeys as key> AND ${key?uncap_first} = ? </#list>";
		${entityName?uncap_first}EntityDao.executeHql(delhql${sub_index},<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${jeecg_table_id}${sub_index}<#else>${key?uncap_first}${sub_index}</#if><#if key_has_next>,</#if></#list>);
		</#list>
		//-------------------------------------------------------------------
		//[3].明细数据保存
		<#list subTab as sub>
		//${sub.ftlDescription}
		${sub.entityName}Entity ${sub.entityName?uncap_first}Entity = null;
		for(${sub.entityName}Page page:${sub.entityName?uncap_first}List){
			for(${sub.entityName}Entity c:${sub.entityName?uncap_first}OldList){
				if(c.get${jeecg_table_id?cap_first}().equals(page.get${jeecg_table_id?cap_first}())){
					${sub.entityName?uncap_first}Entity = c;
					break;
				}
			}
			//-----------------------------------------------------
			if(${sub.entityName?uncap_first}Entity==null){
				${sub.entityName?uncap_first}Entity = new ${sub.entityName}Entity();
			}
			${sub.entityName?uncap_first}EntityDao.evict(${sub.entityName?uncap_first}Entity);
			MyBeanUtils.copyBeanNotNull2Bean(page, ${sub.entityName?uncap_first}Entity);
			//外键设置
			 <#list sub.foreignKeys as key>
			    <#if key?lower_case?index_of("${jeecg_table_id}")!=-1>
			${sub.entityName?uncap_first}Entity.set${key}(t.get${jeecg_table_id?cap_first}());
			    <#else>
			${sub.entityName?uncap_first}Entity.set${key}(t.get${key}());
			    </#if>
			 </#list>
			    
			SearchSqlGenerateUtil.setUpdateMessage(${sub.entityName?uncap_first}Entity);
			${sub.entityName?uncap_first}EntityDao.save(${sub.entityName?uncap_first}Entity);
			${sub.entityName?uncap_first}Entity = null;
			//-----------------------------------------------------
		}
		</#list>
	}
	
	
	public void update(${entityName}Page ${entityName?uncap_first}Page) throws Exception {
		${entityName}Entity t = ${entityName?uncap_first}EntityDao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(${entityName?uncap_first}Page, t);
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from ${entityName}Entity where ${jeecg_table_id} = '"+id+"'";
				${entityName}Entity t = ${entityName?uncap_first}EntityDao.get(hql);
				if (t != null) {
					${entityName?uncap_first}EntityDao.delete(t);
						//获取参数
					    <#list subTab as sub>
					    <#list sub.foreignKeys as key>
					    <#if key?lower_case?index_of("${jeecg_table_id}")!=-1>
					    Object ${jeecg_table_id}${sub_index} = t.get${jeecg_table_id?cap_first}();
					    <#else>
					    Object ${key?uncap_first}${sub_index} = t.get${key}();
					    </#if>
					    </#list>
					    </#list>
					   <#list subTab as sub>
					    //删除明细${sub.ftlDescription}
						String delhql${sub_index} = "delete from ${sub.entityName}Entity where 1 = 1<#list sub.foreignKeys as key> AND ${key?uncap_first} = ? </#list>";
						${entityName?uncap_first}EntityDao.executeHql(delhql${sub_index},<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${jeecg_table_id}${sub_index}<#else>${key?uncap_first}${sub_index}</#if><#if key_has_next>,</#if></#list>);
						</#list>
				}
			}
		}
	}

	public ${entityName}Entity get(${entityName}Page ${entityName?uncap_first}Page) {
		return ${entityName?uncap_first}EntityDao.get(${entityName}Entity.class, ${entityName?uncap_first}Page.get${jeecg_table_id?cap_first}());
	}

	public ${entityName}Entity get(${primary_key_type} ${jeecg_table_id}) {
		return ${entityName?uncap_first}EntityDao.get(${entityName}Entity.class, ${jeecg_table_id});
	}
	public List<${entityName}Entity> listAll(${entityName}Page ${entityName?uncap_first}Page) {
		String hql = "from ${entityName}Entity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(${entityName?uncap_first}Page, hql, values);
		List<${entityName}Entity> list = ${entityName?uncap_first}EntityDao.find(hql,values);
		return list;
	}
	
	<#list subTab as sub>
	/**根据主表Key,查询子表明细：${sub.ftlDescription}*/
	public List<${sub.entityName}Page> get${sub.entityName}ListByFkey(<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${primary_key_type} ${jeecg_table_id}<#else>String ${key?uncap_first}</#if><#if key_has_next>,</#if></#list>) {
		
		List<${sub.entityName}Page> rs = new ArrayList<${sub.entityName}Page>();
		String hql = "from ${sub.entityName}Entity where 1 = 1<#list sub.foreignKeys as key> AND ${key?uncap_first} = ? </#list>";
		List<${sub.entityName}Entity> list = ${sub.entityName?uncap_first}EntityDao.find(hql,<#list sub.foreignKeys as key><#if key?lower_case?index_of("${jeecg_table_id}")!=-1>${jeecg_table_id}<#else>${key?uncap_first}</#if><#if key_has_next>,</#if></#list>);
		
		for(${sub.entityName}Entity po:list){
			${sub.entityName}Page page = new ${sub.entityName}Page();
			BeanUtils.copyProperties(po, page);
			rs.add(page);
		}
		return rs;
	}
	</#list>
}
