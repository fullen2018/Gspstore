package com.util;

public class TestCode {

	//举例：
	//A.当前CODE = 01
	
	//B.同级增加节点,CODE = (当前级最大值+1 范围：从01->99)
	
	//C.子节点，CODE=  (当前子级最大值+1 范围：从01->99)
	
	//D.获得的节点CODE = A.CODE + B.CODE
	//               = A.CODE + C.CODE
	
	
	
	
	/**
	 * 根据传入的父节点CODE，获取子节点CODE
	 */
	
	public static String getNextNodeCode(String pcode){
		String begin = pcode+"01";
		String end   = pcode+"99";
		String sql = "select max(obid) from jeecg_group where obid>="+begin+" and obid<="+end;
		return "";
	}
}
