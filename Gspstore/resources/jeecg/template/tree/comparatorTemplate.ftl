package ${bussiPackage}.comparator.${entityPackage};

import java.util.Comparator;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;

/**
 * 菜单排序
 * 
 * @author 张代浩
 * 
 */
public class ${entityName}Comparator implements Comparator<${entityName}Entity> {

	public int compare(${entityName}Entity o1, ${entityName}Entity o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
