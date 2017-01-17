package com.util.dbcommon;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.jeecg.pageModel.SessionInfo;
import com.jeecg.util.JpaUtil;
import com.jeecg.util.ResourceUtil;
import com.jeecg.util.StringUtil;
import com.util.ClassReflectUtil;
import com.util.MyBeanUtils;
import com.util.MyStringUtils;

public class SearchSqlGenerateUtil {
	
	private static final String SUFFIX_COMMA = ",";
	private static final String SUFFIX_KG = " ";
	/**模糊查询符号*/
	private static final String SUFFIX_ASTERISK = "*";
	private static final String SUFFIX_ASTERISK_VAGUE = "%%";
	/**不等于查询符号*/
	private static final String SUFFIX_NOT_EQUAL = "!";
	private static final String SUFFIX_NOT_EQUAL_NULL = "!NULL";
	
	//add-begin author:gaoxingang for:添加时间查询标识  date：20130310
	/**时间查询标识*/
	private static final String END = "end";
	private static final String BEGIN = "begin";
	//add-end author:gaoxingang for:添加时间查询标识  date：20130310
	
	private static final Logger logger = Logger.getLogger(SearchSqlGenerateUtil.class);
	
	/**
  	 * 给数据库实体赋值
  	 * 创建时间
  	 * 创建人
  	 * 创建人名称
  	 * @param bean
  	 */
	public static void setInsertMessage(Object bean){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		try {
			//逻辑删除
			MyBeanUtils.setProperty(bean, "delflag",0);
			MyBeanUtils.setProperty(bean, "createDt",new Date());
			MyBeanUtils.setProperty(bean, "crtuser", sessionInfo.getUserId());
			MyBeanUtils.setProperty(bean, "crtuserName", sessionInfo.getRealName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
;		}
	}
	
	/**
  	 * 给数据库实体赋值
  	 * 创建时间
  	 * 创建人
  	 * 创建人名称
  	 * @param bean
  	 */
	public static void setUpdateMessage(Object bean){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		try {
			MyBeanUtils.setProperty(bean, "modifyDt",new Date());
			MyBeanUtils.setProperty(bean, "modifier", sessionInfo.getUserId());
			MyBeanUtils.setProperty(bean, "modifierName", sessionInfo.getRealName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		} 
	}
	
	/**
  	 * 给数据库实体赋值
  	 * 创建时间
  	 * 创建人
  	 * 创建人名称
  	 * @param bean
  	 */
	public static void setDelMessage(Object bean){
		try {
			MyBeanUtils.setProperty(bean, "delflag",1);
			MyBeanUtils.setProperty(bean, "delDt", new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	
	/**
  	 * 自动生成查询条件HQL
  	 * 模糊查询
  	 * 【只对Integer类型和String类型的字段自动生成查询条件】
  	 * @param hql
  	 * @param values
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	public static void createSearchParamsHql(StringBuffer hqlbf,List<Object> values,Object searchObj){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		String descriptorsNames = getDescriptorsNames(origDescriptors);
  		
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            String type = origDescriptors[i].getPropertyType().toString();
            
            if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
            		||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            try {
            if (PropertyUtils.isReadable(searchObj, name)) {
               String _name = JpaUtil.getColumnName(searchObj, name);
            	
               if("class java.lang.String".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   String searchValue = null;
            	   if(value!=null){
            		    searchValue = value.toString().trim();
            	   }else{
            		   continue;
            	   }
            	   if(searchValue!=null&&!"".equals(searchValue)){
            		   //[1].In 多个条件查询{逗号隔开参数}
            		   if(searchValue.indexOf(SUFFIX_COMMA)>=0){
            			   //页面输入查询条件，情况（取消字段的默认条件）
            			   if(searchValue.indexOf(SUFFIX_KG)>=0){
            				   String val = searchValue.substring(searchValue.indexOf(SUFFIX_KG));
            				   hqlbf.append(" and  "+_name+" = ? ");
                   			   values.add(val.trim());
            			   }else{
            				   String[] vs = searchValue.split(SUFFIX_COMMA);
                			   hqlbf.append(" and  "+_name+" in ("+MyStringUtils.getStringSplit(vs)+") ");
            			   }
            		   }
            		   //[2].模糊查询{带有* 星号的参数}
            		   else if(searchValue.indexOf(SUFFIX_ASTERISK)>=0){
            			   //searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE);
            			   hqlbf.append(" and  "+_name+" like ? ");
               			   values.add(searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE));
            		   }
            		   //[3].不匹配查询{等于！叹号}
            		   //(1).不为空字符串
            		   else if(searchValue.equals(SUFFIX_NOT_EQUAL)){
            			   hqlbf.append(" and  (LENGTH("+_name+")>0 or "+_name+" is null) ");
            		   }
            		   //(2).不为NULL
            		   else if(searchValue.toUpperCase().equals(SUFFIX_NOT_EQUAL_NULL)){
            			   hqlbf.append(" and  "+_name+" is not null ");
            		   }
            		   //(3).正常不匹配
            		   else if(searchValue.indexOf(SUFFIX_NOT_EQUAL)>=0){
            			   hqlbf.append("and ("+_name+" != ? or "+_name+" is null)");
               			   values.add(searchValue.replace(SUFFIX_NOT_EQUAL, ""));
            		   }
            		   //[4].全匹配查询{没有特殊符号的参数}
            		   else{
            			   hqlbf.append(" and  "+_name+" = ? ");
               			   values.add(searchValue);
            		   }
            	   }
               }else if("class java.lang.Integer".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   hqlbf.append(" and  "+_name+" = ? ");
           			   values.add(value);
            	   }
               } else if ("class java.util.Date".equals(type)) {
            	    //add-begin author:gaoxingang for:判断时间条件拼装查询hql  date：20130310
					Date value = (Date) PropertyUtils.getSimpleProperty(searchObj, name);
					if (null != value) {
						// 判断开始时间
						if (_name.contains(BEGIN)) {
							String realName = StringUtil.firstLowerCase(_name.substring(5, _name.length()));
							if (!BEGIN.equals(_name.substring(0, 5)) || !descriptorsNames.contains(realName)) {
								logger.error("该查询属性 [" + _name + "] 命名不规则");
							} else {
								hqlbf.append(" and " + realName + " >= ? ");
								values.add(value);
							}
						}
						// 判断结束时间
						else if (_name.contains(END)) {
							String realName = StringUtil.firstLowerCase(_name.substring(3, _name.length()));
							if (!END.equals(_name.substring(0, 3)) || !descriptorsNames.contains(realName)) {
								logger.error("该查询属性 [" + _name + "] 命名不规则");
							} else {
								hqlbf.append(" and " + realName + " <= ? ");
								values.add(value);
							}
						}
						else {
							if (!_name.equals("")){
							    hqlbf.append(" and " + _name + " = ? ");
	                            values.add(value);
							}
						}
					 }
					//end-begin author:gaoxingang for:判断时间条件拼装查询hql  date：20130310
                  }
               }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
  	}
  	
  	//add-begin author:gaoxingang for:新增createSearchParamsSql方法  date：20130315
  	/**
  	 * 自动生成查询条件SQL
  	 * 模糊查询
  	 * @param sql
  	 * @param values
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	public static void createSearchParamsSql(StringBuffer hqlbf,List<Object> values,Object searchObj){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		String descriptorsNames = getDescriptorsNames(origDescriptors);
  		
  		for (int i = 0; i < origDescriptors.length; i++) {
  			String name = origDescriptors[i].getName();
  			String type = origDescriptors[i].getPropertyType().toString();
  			
  			if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
  					||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
  				continue; 
  			}
  			try {
  				if (PropertyUtils.isReadable(searchObj, name)) {
  					String _name = StringUtil.firstLowerCase(searchObj.getClass().getSimpleName()) + "." + JpaUtil.getColumnName(searchObj, name);
  					
  					if("class java.lang.String".equals(type)){
  						Object value = PropertyUtils.getSimpleProperty(searchObj, name);
  						String searchValue = null;
  						if(value!=null){
  							searchValue = value.toString().trim();
  						}else{
  							continue;
  						}
  						if(searchValue!=null&&!"".equals(searchValue)){
  							//[1].In 多个条件查询{逗号隔开参数}
  							if(searchValue.indexOf(SUFFIX_COMMA)>=0){
  								//页面输入查询条件，情况（取消字段的默认条件）
  								if(searchValue.indexOf(SUFFIX_KG)>=0){
  									String val = searchValue.substring(searchValue.indexOf(SUFFIX_KG));
  									hqlbf.append(" and  "+_name+" = ? ");
  									values.add(val.trim());
  								}else{
  									String[] vs = searchValue.split(SUFFIX_COMMA);
  									hqlbf.append(" and  "+_name+" in ("+MyStringUtils.getStringSplit(vs)+") ");
  								}
  							}
  							//[2].模糊查询{带有* 星号的参数}
  							else if(searchValue.indexOf(SUFFIX_ASTERISK)>=0){
  								//searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE);
  								hqlbf.append(" and  "+_name+" like ? ");
  								values.add(searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE));
  							}
  							//[3].不匹配查询{等于！叹号}
  							//(1).不为空字符串
  							else if(searchValue.equals(SUFFIX_NOT_EQUAL)){
  								hqlbf.append(" and  (LENGTH("+_name+")>0 or "+_name+" is null) ");
  							}
  							//(2).不为NULL
  							else if(searchValue.toUpperCase().equals(SUFFIX_NOT_EQUAL_NULL)){
  								hqlbf.append(" and  "+_name+" is not null ");
  							}
  							//(3).正常不匹配
  							else if(searchValue.indexOf(SUFFIX_NOT_EQUAL)>=0){
  								hqlbf.append("and ("+_name+" != ? or "+_name+" is null)");
  								values.add(searchValue.replace(SUFFIX_NOT_EQUAL, ""));
  							}
  							//[4].全匹配查询{没有特殊符号的参数}
  							else{
  								hqlbf.append(" and  "+_name+" = ? ");
  								values.add(searchValue);
  							}
  						}
  					}else if("class java.lang.Integer".equals(type)){
  						Object value = PropertyUtils.getSimpleProperty(searchObj, name);
  						if(value!=null&&!"".equals(value)){
  							hqlbf.append(" and  "+_name+" = ? ");
  							values.add(value);
  						}
  					} else if ("class java.util.Date".equals(type)) {
  						Date value = (Date) PropertyUtils.getSimpleProperty(searchObj, name);
  						if (null != value) {
  							// 判断开始时间
  							if (_name.contains(BEGIN)) {
  								String realName = StringUtil.firstLowerCase(_name.substring(5, _name.length()));
  								if (!BEGIN.equals(_name.substring(0, 5)) || !descriptorsNames.contains(realName)) {
  									logger.error("该查询属性 [" + _name + "] 命名不规则");
  								} else {
  									hqlbf.append(" and " + realName + " >= ? ");
  									values.add(value);
  								}
  							}
  							// 判断结束时间
  							else if (_name.contains(END)) {
  								String realName = StringUtil.firstLowerCase(_name.substring(3, _name.length()));
  								if (!END.equals(_name.substring(0, 3)) || !descriptorsNames.contains(realName)) {
  									logger.error("该查询属性 [" + _name + "] 命名不规则");
  								} else {
  									hqlbf.append(" and " + realName + " <= ? ");
  									values.add(value);
  								}
  							}
  							else {
  								hqlbf.append(" and " + _name + " = ? ");
  								values.add(value);
  							}
  						}
  					}
  				}
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  	}
  	//add-end author:gaoxingang for:新增createSearchParamsSql方法  date：20130315
  	
  	//add-begin author:gaoxingang for:新增拼接Join语句方法  date：20130315
  	/**
  	 * 拼接Join语句
  	 * @param hqlbf
  	 * @param values
  	 * @param searchObj
  	 */
  	public static void addJoin(StringBuffer hqlbf, List<Object> values,
			Object searchObj)  {
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		for (int i = 0; i < origDescriptors.length; i++) {
  			String name = origDescriptors[i].getName();
  			String type = origDescriptors[i].getPropertyType().toString();
  			
  			if (type.contains("class java") || searchObj.getClass().getSimpleName().equalsIgnoreCase(name)) 
  				continue;
  			
			try {
				Object value; value = PropertyUtils.getSimpleProperty(searchObj, name);
				if (null != value && !isEmptyProperty(value)) {
					String mainClassName = searchObj.getClass().getSimpleName();
					String subClassName = StringUtil.firstUpperCase(name);
					String mainTableName = JpaUtil.getTableName(searchObj);
					String subTableName = "";
					try {
						subTableName = JpaUtil.getTableName(ClassReflectUtil.getInstance(type.replace("class ", "")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					String foreignKeyName = JpaUtil.getForeignKeyName(subTableName);
					String majorKeyName = JpaUtil.getMajorKeyName(mainTableName);
					hqlbf.append("join " + subTableName + " " + StringUtil.firstLowerCase(subClassName) + " on " + StringUtil.firstLowerCase(mainClassName) + "." + majorKeyName + " = " + name + "." + foreignKeyName);
					createSearchParamsSql(hqlbf,values,value);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
  			
  		}
  	}
  	//add-end author:gaoxingang for:新增拼接Join语句方法  date：20130315
  	
  	//add-begin author:gaoxingang for:新增isEmptyProperty方法  date：20130315
  	/**
  	 * 判断关联表的属性值是否为空
  	 * @param searchObj
  	 * @return
  	 */
  	private static boolean isEmptyProperty(Object searchObj) {
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		for (int i = 0; i < origDescriptors.length; i++) {
  			String name = origDescriptors[i].getName();
            if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
            		||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
                continue; // No point in trying to set an object's class
            }
  			String type = origDescriptors[i].getPropertyType().toString();
  			try {
				Object value = PropertyUtils.getSimpleProperty(searchObj, name);
				if (null != value && !StringUtils.isEmpty(value.toString())) {
					return false; 
				}
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
  		}
  		return true;
  	}
  	//add-end author:gaoxingang for:新增isEmptyProperty方法  date：20130315
  	
  	//add-begin author:gaoxingang for:得到对象属性中所有name方法  date：20130310
	/**
	 * 得到对象属性中所有name
	 * @param origDescriptors
	 * @return
	 */
  	private static String getDescriptorsNames(PropertyDescriptor origDescriptors[]) {
  		StringBuilder sb = new StringBuilder();
  		for (int i = 0; i < origDescriptors.length; i++) {
  			sb.append(origDescriptors[i].getName() + ",");
  		}
  		return sb.toString();
  	}
    //add-end author:gaoxingang for:得到对象属性中所有name方法  date：20130310
  	
  	public static void main(String[] args) {
  		try {
			Object instance = ClassReflectUtil.getInstance("sun.entity.jeecg.JeecgOrderCustomEntity");
			System.out.println(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
}


