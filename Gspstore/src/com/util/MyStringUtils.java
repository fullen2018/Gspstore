package com.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecg.dao.jdbc.JdbcDao;

public class MyStringUtils {
	
	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	
	/**
	 * 封装查询条件
	 * @param val
	 * @return
	 */
	public static String getStringSplit(String[] val){
		StringBuffer sqlStr = new StringBuffer();
		for(String s:val){
			if(StringUtils.isNotBlank(s)){
				sqlStr.append(",");
				sqlStr.append("'");
				sqlStr.append(s.trim());
				sqlStr.append("'");
			}
		}
		return sqlStr.toString().substring(1);
	}
	/**
	 * 字符串首字母变小写
	 */
	public static String getInitialSmall(String str) {
		if(StringUtils.isNotBlank(str)){
			str = str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		return str;
	}
	
	/**
	 * 判断如果字段为空，则返回0
	 */
	public static Integer getIntegerNotNull(Integer t){
		if(t==null){
			return 0;
		}
		return t;
	}
}
