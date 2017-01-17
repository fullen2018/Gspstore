package business.service.impl.fee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import business.entity.fee.FeeEntity;
import business.page.fee.FeePage;
import business.page.report.ReportQueryForm;
import business.page.subject.SubjectPage;
import business.service.fee.FeeServiceI;
import business.service.subject.SubjectServiceI;
import business.util.FormatUtil;
import business.util.MapUtil;

import com.jeecg.pageModel.TreeNode;
import com.util.MyBeanUtils;
import com.util.dbcommon.SearchSqlGenerateUtil;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.DateUtils;
import com.jeecg.util.StringUtil;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.dao.BaseDaoI;

/**   
 * @Title: ServiceImpl
 * @Description: 财务费用信息
 * @author zhangdaihao
 * @date 2013-05-21 20:46:40
 * @version V1.0   
 *
 */
@Service("feeService")
public class FeeServiceImpl extends BaseServiceImpl implements FeeServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	private BaseDaoI<FeeEntity> feeEntityDao;
	@Autowired
	private SubjectServiceI subjectService;

	public BaseDaoI<FeeEntity> getFeeEntityDao() {
		return feeEntityDao;
	}
	@Autowired
	public void setFeeEntityDao(BaseDaoI<FeeEntity> feeEntityDao) {
		this.feeEntityDao = feeEntityDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public DataGrid datagrid(FeePage feePage) {
		DataGrid j = new DataGrid();
		j.setRows(getPagesFromEntitys(find(feePage)));
		j.setTotal(total(feePage));
		return j;
	}

	private List<FeePage> getPagesFromEntitys(List<FeeEntity> feeEntitys) {
		List<FeePage> feePages = new ArrayList<FeePage>();
		if (feeEntitys != null && feeEntitys.size() > 0) {
			for (FeeEntity tb : feeEntitys) {
				FeePage b = new FeePage();
				BeanUtils.copyProperties(tb, b);
				SubjectPage sp = new SubjectPage();
				BeanUtils.copyProperties(subjectService.get(tb.getSubjectid()),sp);
				b.setSubjectpage(sp);
				feePages.add(b);
			}
		}
		return feePages;
	}

	private List<FeeEntity> find(FeePage feePage) {
		String hql = "from FeeEntity t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(feePage, hql, values);

		if (feePage.getSort() != null && feePage.getOrder() != null) {
			hql += " order by " + feePage.getSort() + " " + feePage.getOrder();
		}
		return feeEntityDao.find(hql, feePage.getPage(), feePage.getRows(), values);
	}

	private Long total(FeePage feePage) {
		String hql = "select count(*) from FeeEntity t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(feePage, hql, values);
		return feeEntityDao.count(hql, values);
	}

	private String addWhere(FeePage feePage, String hql, List<Object> values) {
	    //循环查询条件Page的所有[Integer][String]类型的字段，如果字段不为空则动态加上查询条件
		//-----------------------------------------------------
		StringBuffer hqlbf = new StringBuffer(hql);
		
		FeeEntity feeEntity = new FeeEntity();
		if (feePage.getFeeeman()!=null&&!feePage.getFeeeman().equals("")){
		   feePage.setFeeeman("*"+feePage.getFeeeman()+"*");
		}
		BeanUtils.copyProperties(feePage, feeEntity);
		SearchSqlGenerateUtil.createSearchParamsHql(hqlbf, values, feeEntity);
		if (feePage.getBeginFeedate()!=null){
		    hqlbf.append(" and feedate >=? ");
		    values.add(feePage.getBeginFeedate());
		}
		if (feePage.getEndFeedate()!=null){
		    hqlbf.append(" and feedate <=? ");
		    values.add(feePage.getEndFeedate());
		}
		hql = hqlbf.toString();
		return hql;
	}

	public void add(FeePage feePage) {
		FeeEntity t = new FeeEntity();
		BeanUtils.copyProperties(feePage, t);
		t.setCreatedate(new Date());
        t.setModifydate(new Date());
        Integer num = getMaxFeeNum();
        t.setOrdernum(num);
        t.setFeeid(DateUtils.FormatDate(new Date(), "yyyyMMdd")+FormatUtil.convertIntToString(4, num));
        
		feeEntityDao.save(t);
	}

	public void update(FeePage feePage) throws Exception {
		FeeEntity t = feeEntityDao.get(FeeEntity.class, feePage.getFeeid());
	    if(t != null) {
			MyBeanUtils.copyBeanNotNull2Bean(feePage, t);
			t.setModifydate(new Date());
		}
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				String hql = "from FeeEntity where feeid = '"+id+"'";
				FeeEntity t = feeEntityDao.get(hql);
				if (t != null) {
					feeEntityDao.delete(t);
				}
			}
		}
	}

	public FeeEntity get(FeePage feePage) {
		return feeEntityDao.get(FeeEntity.class, feePage.getFeeid());
	}

	public FeeEntity get(java.lang.String feeid) {
		return feeEntityDao.get(FeeEntity.class, feeid);
	}
	public List<FeeEntity> listAll(FeePage feePage) {
		String hql = "from FeeEntity where 1 = 1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(feePage, hql, values);
		List<FeeEntity> list = feeEntityDao.find(hql,values);
		return list;
	}
	public Integer getMaxFeeNum(){
        Integer maxNum = 0;
        String sql = "select max(ae.ordernum) as ordernum  from t_cw_fee ae " +
        " where date_format(ae.createdate,'%Y-%m-%d')='"+DateUtils.FormatDate(new Date(), "yyyy-MM-dd")+"' ";
        Map<String, Object> map = feeEntityDao.findOneForJdbc(sql, null);
        Object obj = map.get("ordernum");
        System.out.println(obj);
        if (obj!=null&&!obj.equals("")){
            maxNum =Integer.valueOf(obj.toString())+1;
        }else{
            maxNum =1;          
        }
        return maxNum;
    }
	public List<Map<String, Object>> viewFeeTotalList(
        ReportQueryForm reportQueryForm){
	    List<Map<String, Object>> viewData = new ArrayList<Map<String,Object>>();
        List<Object> values = new ArrayList<Object>();
        String sql = " select t.*,p.param_name as feetypename,s.subjectname,w.param_name as subjecttypename,s.subjecttype  " +
        		" from t_cw_fee t " +
        		" inner join t_xx_subject s on s.subjectid=t.subjectid " +
        		" left join jeecg_dict_param p on p.param_level='007' and p.param_value=t.feetype " +
        		" left join jeecg_dict_param w on w.param_level='006' and w.param_value=s.subjecttype " +
                " where 1=1 " +
                "";

        if (reportQueryForm.getSubjectid()!=null&&!reportQueryForm.getSubjectid().equals("")){
            sql += " and t.subjectid='"+reportQueryForm.getSubjectid()+"' ";
        }
        if (reportQueryForm.getSubjecttype()!=null&&!reportQueryForm.getSubjecttype().equals("")){
            sql += " and s.subjecttype='"+reportQueryForm.getSubjecttype()+"' ";
        }
        if (reportQueryForm.getFeetype()!=null&&!reportQueryForm.getFeetype().equals("")){
            sql += " and t.feetype='"+reportQueryForm.getFeetype()+"' ";
        }
        if (reportQueryForm.getCrtuser()!=null&&!reportQueryForm.getCrtuser().equals("")){
            sql += " and t.crtuser='"+reportQueryForm.getCrtuser()+"' ";
        }
        if (reportQueryForm.getBegindate() != null&&!reportQueryForm.getBegindate().equals("")) {
            sql += " and t.createdate>='"+DateUtils.FormatDate(reportQueryForm.getBegindate(),"yyyy-MM-dd")+" 00:00:00"+"' ";
        }
        if (reportQueryForm.getEnddate() != null&&!reportQueryForm.getEnddate().equals("")) {
            sql += " and t.createdate<='"+DateUtils.FormatDate(reportQueryForm.getEnddate(),"yyyy-MM-dd")+" 23:59:59"+"' ";
        }
        sql += " order by s.subjecttype desc";
        viewData = MapUtil.changeMapListToLower(jdbcDao.findForJdbc(sql));
        return viewData;
	}
}
