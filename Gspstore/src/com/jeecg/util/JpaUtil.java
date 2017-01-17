package com.jeecg.util;

import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import sun.entity.jeecg.JeecgOrderCustomEntity;
import sun.entity.jeecg.JeecgOrderMainEntity;

public class JpaUtil {

	/**
	 * 通过字段属性返回对应数据库字段名
	 * @param o
	 * @param name
	 * @return
	 */
	public static String getColumnName(Object o,String name) {
		Method[] methods = o.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName().replace("get", "");
			if (methodName.toLowerCase().equals(name.toLowerCase())) {
				Column excel = method.getAnnotation(Column.class);
				if (null != excel) 
					return excel.name();
			}
		}
		return "";
	}

	/**
	 * 通过字段属性返回对应数据库表名
	 * @param o
	 * @return
	 */
	public static String getTableName(Object o) {
		Table table;
		table = o.getClass().getAnnotation(Table.class);
		if (null != table) {
			return table.name();
		}
		return "";
	}

	/**
	 * 通过字段属性返回外键名字
	 * @param name
	 * @return
	 */
	public static String getForeignKeyName(String name) {
		Method[] methods = JeecgOrderCustomEntity.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			JoinColumn foreignKeyName = method.getAnnotation(JoinColumn.class);
			if (null != foreignKeyName) 
				return foreignKeyName.name();
		}
		return "";
	}

	/**
	 * 通过字段属性返回主键名字
	 * @param name
	 * @return
	 */
	public static String getMajorKeyName(String name) {
		Method[] methods = JeecgOrderMainEntity.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			Id majorKeyName = method.getAnnotation(Id.class);
			if (null != majorKeyName) 
				return method.getAnnotation(Column.class).name();
		}
		return "";
	}

}
