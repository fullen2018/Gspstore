package sun.action.test;

import java.io.File;
import java.io.InputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sun.page.test.DemoPage;
import sun.service.test.DemoServiceI;

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Title: Action
 * @Description: 用户
 * @author zhangdaihao
 * @date 2011-12-25 20:55:16
 * @version V1.0   
 *
 */
@Action(value = "demoAction", results = { @Result(name = "demo-jdbc", location = "/sun/test/demo-jdbc.jsp"),
							              @Result(name = "upload-input", location = "/sun/test/upload-input.jsp"),
							              @Result(name = "menuButton", location = "/sun/test/menuButton.jsp"),
							              @Result(name = "combobx2", location = "/sun/test/combobx-2level.jsp"),
							              @Result(name = "combobx3", location = "/sun/test/combobx-3level.jsp"),
							              @Result(name = "dic-display", location = "/sun/test/dic-display.jsp"),
							              @Result(name = "excel-opt", location = "/sun/test/excel-opt.jsp"),
							              @Result(name = "export", type="stream",params={"bufferSize","4096","contentDisposition","attachment;filename=${fileName}","inputName","inputStream"})
				})
public class DemoAction extends BaseAction implements ModelDriven<DemoPage> {

	private static final Logger logger = Logger.getLogger(DemoAction.class);

	@Autowired
	private DemoServiceI demoService;

	private DemoPage demoPage = new DemoPage();

	public DemoPage getModel() {
		return demoPage;
	}


	/**
	 * 获得pageHotel数据表格
	 */
	public void datagridByJdbc() {
		writeJson(demoService.listAllByJdbc(demoPage));
	}
	
	/**
	 * jdbc 分页
	 * @return
	 */
	public String goDemoJdbc() {
		return "demo-jdbc";
	}


	
	public DemoPage getDemoPage() {
		return demoPage;
	}


	public void setDemoPage(DemoPage demoPage) {
		this.demoPage = demoPage;
	}
	
	/**
	 * 跳转到上传页面
	 * @return
	 */
	public String goUploadInput(){
		return "upload-input";
	}
	//浏览器传递过来的信息
	private File filedata;
	
	public File getFiledata() {
		return filedata;
	}
	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	/**
	 * 上传文件
	 */
	public void upload(){
		Json j = new Json();
		try {
			demoService.upload(filedata);
			j.setSuccess(true);
			j.setMsg("文件上传成功！");
		} catch (Exception e) {
			j.setMsg("文件上传失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);		 
	}
	
	/**
	 * 跳转到下拉菜单
	 * @return
	 */
	public String goMenuButton(){
		return "menuButton";
	}	
	
	/**
	 * 跳转到二级联动
	 * @return
	 */
	public String goMenuButton2(){
		return "combobx2";
	}
	
	/**
	 * 得到省级数据
	 */
	public void getProvinceJson(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("provinceId", "bj");
		jsonObj.put("provinceName", "北京");
		jsonObj.put("selected", true);
		jsonArray.add(jsonObj);	
		JSONObject jsonObj2 = new JSONObject();		
		jsonObj2.put("provinceId", "hb");
		jsonObj2.put("provinceName", "河北");
		jsonArray.add(jsonObj2);
		writeJson(jsonArray);
	}
	//浏览器传递过来的信息
	private String provinceId;
	
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 得到市级数据
	 */
	public void getCityJsonByProvinceId(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		if("bj".equals(provinceId)){
			jsonObj.put("cityId", "ft");
			jsonObj.put("cityName", "丰台区");
			jsonObj.put("selected", true);
			JSONObject jsonObj2 = new JSONObject();			
			jsonArray.add(jsonObj);					
			jsonObj2.put("cityId", "hd");
			jsonObj2.put("cityName", "海淀区");
			jsonArray.add(jsonObj2);					
		}
		if("hb".equals(provinceId)){
			jsonObj.put("cityId", "lf");
			jsonObj.put("cityName", "廊坊");
			jsonObj.put("selected", true);
			jsonArray.add(jsonObj);	
			JSONObject jsonObj2 = new JSONObject();				
			jsonObj2.put("cityId", "cz");
			jsonObj2.put("cityName", "石家庄");
			jsonArray.add(jsonObj2);					
		}
		writeJson(jsonArray);
	}
	
	/**
	 * 跳转到三级联动
	 * @return
	 */
	public String goMenuButton3(){
		return "combobx3";
	}	
	//浏览器传递过来的信息	
	private String cityId;
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * 得到区级数据
	 */
	public void getAreaJsonByCityId(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		if("ft".equals(cityId)){
			jsonObj.put("areaId", "xwy");
			jsonObj.put("areaName", "小瓦窑");
			jsonObj.put("selected", true);
			JSONObject jsonObj2 = new JSONObject();			
			jsonArray.add(jsonObj);					
			jsonObj2.put("areaId", "yql");
			jsonObj2.put("areaName", "玉泉路");
			jsonArray.add(jsonObj2);					
		}
		if("hd".equals(cityId)){
			jsonObj.put("areaId", "zgc");
			jsonObj.put("areaName", "中关村");
			jsonObj.put("selected", true);
			JSONObject jsonObj2 = new JSONObject();			
			jsonArray.add(jsonObj);					
			jsonObj2.put("areaId", "sd");
			jsonObj2.put("areaName", "上地");
			jsonArray.add(jsonObj2);					
		}
		writeJson(jsonArray);		
	}	
	
	/**
	 * 跳转到数据字典
	 * @return
	 * 
	 */
	public String goDicDisplay(){
		return "dic-display";
	}	
	
	/**
	 * 跳转到excel导入导出页面
	 * @return
	 * 
	 */	
	public String goExcelOpt(){
		return "excel-opt";
	}
	//供浏览器读取的信息
	private String fileName;
	private InputStream inputStream;
	
	private String filedataFileName;

	public String getFiledataFileName() {
		return filedataFileName;
	}


	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}


	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 导出Excel
	 * @return
	 * 
	 */	
	public String exportExcel(){
		fileName = "导出信息.xls";
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
			inputStream = demoService.exportExcel();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));			
		}
		return "export";
	}
	/**
	 * 导入Excel
	 * @return
	 * 
	 */	
	public void importExcel(){
		Json j = new Json();
		try {
			demoService.importExcel(filedataFileName, filedata);
			j.setSuccess(true);
			j.setMsg("文件导入成功！");		
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("文件导入失败！");			
			logger.error(ExceptionUtil.getExceptionMessage(e));			
		}
		writeJson(j);
	}	
}
