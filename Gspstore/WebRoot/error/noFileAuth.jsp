<%@ page language="java" import="java.util.*,com.alibaba.fastjson.JSON" pageEncoding="UTF-8"%>
<%
	response.setContentType("text/html;charset=utf-8");
	Map<String, Object> m = new HashMap<String, Object>();
	m.put("err", m);
	m.put("msg", "");
	out.print(JSON.toJSONString(m));
%>