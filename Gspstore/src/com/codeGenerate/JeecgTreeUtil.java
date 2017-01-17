package com.codeGenerate;

import com.code.generate.CodeTreeGenerate;

/**
 * 自动生成Dao,Service,Action及Dao和Service测试类
 */
public class JeecgTreeUtil {

	/*
	 * 参数为Entity类名(注意第一个字符大写)
	 */
	public static void main(String[] args) {
		String entityPackage = "catelog";// 包名（小写）
		String entityName = "Catelog";// 实体名（首字母大写）
		String tableName = "t_xx_catelog"; // 表名
		String ftlDescription = "药品类别信息";// 功能描述
		new CodeTreeGenerate(entityPackage, entityName, tableName,ftlDescription).generateToFile();
		System.out.println("-- success --");
	}

}
