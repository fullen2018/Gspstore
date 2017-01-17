package sun.service.impl.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.page.test.DemoPage;
import sun.service.test.DemoServiceI;

import com.jeecg.dao.BaseDaoI;
import com.jeecg.dao.jdbc.JdbcDao;
import com.jeecg.model.Tuser;
import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.impl.BaseServiceImpl;
import com.jeecg.util.excel.ExcelExportUtil;
import com.jeecg.util.excel.ExcelUtil;

/**   
 * @Title: ServiceImpl
 * @Description: 用户
 * @author zhangdaihao
 * @date 2011-12-25 20:55:16
 * @version V1.0   
 *
 */
@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl implements DemoServiceI {

	@Autowired
	//SQL 使用JdbcDao
	private JdbcDao jdbcDao;
	
	/**
	 * Spring Jdbc 分页
	 */
	public DataGrid listAllByJdbc(DemoPage demoPage) {
		//从对应路径中，获取SQL [sun/sql/test/DemoService_listAllByJdbc.sql]
		String sql = com.util.JeecgSqlUtil.getMethodSql(com.util.JeecgSqlUtil.getMethodUrl());
		String countsql = "select count(*) from jeecg_dict_param";
		DataGrid j = new DataGrid();
		List<Map<String, Object>> maplist =  jdbcDao.findForJdbcParam(sql, demoPage.getPage(), demoPage.getRows());
		Long count = jdbcDao.getCountForJdbcParam(countsql);
		j.setRows(maplist);
		j.setTotal(count);
		return j;
	}
	/**
	 * 上传文件
	 */
	@Override
	public void upload(File filedata) throws IOException {
		 String destDir = "d:/upload";
		 MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper)ServletActionContext.getRequest();
		 File[] files = mpRequest.getFiles("filedata");   
		 String[] fileNames = mpRequest.getFileNames("filedata");
		 for(int i=0;i<files.length;i++){
			 File destFile = new File(destDir+"/"+fileNames[i]);
			 FileUtils.copyFile(files[i], destFile);
		 }
	}

	@Autowired
	private BaseDaoI<Tuser> userDao;
	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}
	/**
	 * 导出excel
	 */
	@Override
	public InputStream exportExcel()  throws  Exception{
		//获取数据
		String hql = "from Tuser t where 1=1 ";	
		List<Object> values = new ArrayList<Object>();
		List<Tuser> listUser = userDao.find(hql, values);
		File file = new File(new Date(0).getTime()+".xls");
		OutputStream outputStream = new FileOutputStream(file);
		ExcelExportUtil.exportExcel("导出信息", Tuser.class, listUser, outputStream);
		InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));
		file.delete();		
		return is;
	}	
	
    /** 
     * 导入excel
     *  
     * @param fileName 文件名称
     * @param excelFile 文件
     * @return 
     */  	
	public void importExcel(String fileName,File excelFile){
		List<Tuser> listUsers = (List<Tuser>)ExcelUtil.importExcel(excelFile, Tuser.class);
		for(Tuser user : listUsers){
			user.setCid(UUID.randomUUID().toString());
			userDao.saveOrUpdate(user);
		}
	}	
}
