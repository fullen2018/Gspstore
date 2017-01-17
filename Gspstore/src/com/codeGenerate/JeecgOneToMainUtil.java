package com.codeGenerate;

import java.util.ArrayList;
import java.util.List;

import com.code.CodeParamEntity;
import com.code.SubTableEntity;
import com.code.generate.CodeGenerateOneToMany;
import com.util.def.JeecgKey;


/**
 * 代码生成器入口【一对多】
 * @author zhangdaihao
 *
 */
public class JeecgOneToMainUtil {

	/**
	 * 一对多(父子表)数据模型，生成方法
	 * @param args
	 */
	public static void main(String[] args) {
		//--------------------------------------------------------
		//第一步：设置主表
		CodeParamEntity codeParamEntityIn = new CodeParamEntity();
		codeParamEntityIn.setTableName("jeecg_order_main");//主表[表名]
		codeParamEntityIn.setEntityName("Demo4ManyKey");	 //主表[实体名]
		codeParamEntityIn.setEntityPackage("jeecg");	 //主表[包名]
		codeParamEntityIn.setFtlDescription("订单主数据");	 //主表[描述]
		codeParamEntityIn.setPrimaryKeyPolicy(JeecgKey.SEQUENCE);
		codeParamEntityIn.setSequenceCode("JEECGONEDEMO_SEQ");
		codeParamEntityIn.setFtl_mode(CodeGenerateOneToMany.FTL_MODE_B);//主表[模板 A:明细单页布局显示 B:明细采用Tab布局展现]
		
		//第二步：设置子表集合
		List<SubTableEntity> subTabParamIn = new ArrayList<SubTableEntity>();
		//[1].子表一
		SubTableEntity po = new SubTableEntity();
		po.setTableName("jeecg_order_custom");//子表[表名]
		po.setEntityName("DemoMany4CustomKey");	 //子表[实体名]
		po.setEntityPackage("jeecg");			 //子表[包]
		po.setFtlDescription("订单客户明细");		 //子表[描述]
		po.setPrimaryKeyPolicy(JeecgKey.SEQUENCE);
		po.setSequenceCode("JEECGONEDEMO_SEQ");
		po.setForeignKeys(new String[]{"GORDER_OBID","GO_ORDER_CODE"});//子表[外键:与主表关联外键]
		subTabParamIn.add(po);
		//[2].子表二
		SubTableEntity po2 = new SubTableEntity();
		po2.setTableName("jeecg_order_product");		//子表[表名]
		po2.setEntityName("DemoMany4ProductKey");			//子表[实体名]
		po2.setEntityPackage("jeecg"); 					//子表[包]
		po2.setFtlDescription("订单产品明细");				//子表[描述]
		po2.setPrimaryKeyPolicy(JeecgKey.SEQUENCE);
		po2.setSequenceCode("JEECGONEDEMO_SEQ");
		po2.setForeignKeys(new String[]{"GORDER_OBID","GO_ORDER_CODE"});//子表[外键:与主表关联外键]
		subTabParamIn.add(po2);
		codeParamEntityIn.setSubTabParam(subTabParamIn);
		
		//第三步：一对多(父子表)数据模型,代码生成
		CodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn);
	}
}
