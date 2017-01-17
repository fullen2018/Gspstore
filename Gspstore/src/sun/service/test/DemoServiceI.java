package sun.service.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import sun.page.test.DemoPage;

import com.jeecg.pageModel.DataGrid;
import com.jeecg.service.BaseServiceI;

/**   
 * @Title: Service
 * @Description: 用户
 * @author zhangdaihao
 * @date 2011-12-25 20:55:16
 * @version V1.0   
 *
 */
public interface DemoServiceI extends BaseServiceI {

	/**
	 * Spring Jdbc 分页
	 * @param dictParamPage
	 * @return
	 */
	public DataGrid listAllByJdbc(DemoPage demoPage);
	
	/**
	 * 文件上传
	 * @param filedata
	 */
	public void upload(File filedata)throws IOException;
	
	/**
	 * 导出excel
	 * @return
	 */
	public InputStream exportExcel() throws  Exception;	
	
    /**
     * 导入excel
     * @param fileName
     * @param excelFile
     */
	public void importExcel(String fileName,File excelFile);    	
}
