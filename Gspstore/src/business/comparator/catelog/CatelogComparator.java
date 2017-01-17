package business.comparator.catelog;

import java.util.Comparator;
import business.entity.catelog.CatelogEntity;

/**
 * 菜单排序
 * 
 * @author 张代浩
 * 
 */
public class CatelogComparator implements Comparator<CatelogEntity> {

	public int compare(CatelogEntity o1, CatelogEntity o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
