package com.jeecg.converter;

import java.sql.Clob;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.jeecg.util.ClobUtil;

/**
 * java.sql.Clob转换器
 * 
 * @author zhangdaihao
 * 
 */
public class ClobConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null || values.length == 0) {
			return null;
		}
		return ClobUtil.getClob(values[0]);
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Clob) {
			return ClobUtil.getString((Clob) o);
		}
		return "";
	}

}
