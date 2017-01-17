package sun.action.jeecg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import sun.page.jeecg.JeecgOrderMainSinglePage;
import sun.entity.jeecg.JeecgOrderMainSingleEntity;
import sun.service.jeecg.JeecgOrderMainSingleServiceI;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import com.util.MyBeanUtils;
import sun.page.jeecg.JeecgOrderCustomSinglePage;
import sun.entity.jeecg.JeecgOrderCustomSingleEntity;
import sun.page.jeecg.JeecgOrderProductSinglePage;
import sun.entity.jeecg.JeecgOrderProductSingleEntity;

import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Title: Action
 * @Description: 订单主数据
 * @author zhangdaihao
 * @date 2013-01-18 15:44:12
 * @version V1.0   
 *
 */
@Action(value = "jeecgOrderMainSingleAction", results = { @Result(name = "jeecgOrderMainSingle", location = "/sun/jeecg/jeecgOrderMainSingle.jsp"),
													   @Result(name = "jeecgOrderMainSingle-main-add", location = "/sun/jeecg/jeecgOrderMainSingle-main-add.jsp"),
													   @Result(name = "jeecgOrderMainSingle-main-edit", location = "/sun/jeecg/jeecgOrderMainSingle-main-edit.jsp")})
public class JeecgOrderMainSingleAction extends BaseAction implements ModelDriven<JeecgOrderMainSinglePage> {

	private static final Logger logger = Logger.getLogger(JeecgOrderMainSingleAction.class);

	private JeecgOrderMainSingleServiceI jeecgOrderMainSingleService;

	private JeecgOrderMainSinglePage jeecgOrderMainSinglePage = new JeecgOrderMainSinglePage();

	/**订单客户明细*/
	private List<JeecgOrderCustomSinglePage> jeecgOrderCustomSingleList = new ArrayList<JeecgOrderCustomSinglePage>();
	public List<JeecgOrderCustomSinglePage> getJeecgOrderCustomSingleList() {
		return jeecgOrderCustomSingleList;
	}
	public void setJeecgOrderCustomSingleList(List<JeecgOrderCustomSinglePage> jeecgOrderCustomSingleList) {
		this.jeecgOrderCustomSingleList = jeecgOrderCustomSingleList;
	}
	/**订单产品明细*/
	private List<JeecgOrderProductSinglePage> jeecgOrderProductSingleList = new ArrayList<JeecgOrderProductSinglePage>();
	public List<JeecgOrderProductSinglePage> getJeecgOrderProductSingleList() {
		return jeecgOrderProductSingleList;
	}
	public void setJeecgOrderProductSingleList(List<JeecgOrderProductSinglePage> jeecgOrderProductSingleList) {
		this.jeecgOrderProductSingleList = jeecgOrderProductSingleList;
	}
	
	public JeecgOrderMainSinglePage getModel() {
		return jeecgOrderMainSinglePage;
	}


	public JeecgOrderMainSingleServiceI getJeecgOrderMainSingleService() {
		return jeecgOrderMainSingleService;
	}

	@Autowired
	public void setJeecgOrderMainSingleService(JeecgOrderMainSingleServiceI jeecgOrderMainSingleService) {
		this.jeecgOrderMainSingleService = jeecgOrderMainSingleService;
	}


	/**
	 * 跳转到订单主数据管理页面
	 * 
	 * @return
	 */
	public String goJeecgOrderMainSingle() {
		return "jeecgOrderMainSingle";
	}

	
	/**
	 * 跳转一对多添加页面
	 * 
	 * @return
	 */
	public String toJeecgOrderMainSingleMainAdd() {
		return "jeecgOrderMainSingle-main-add";
	}
	
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toJeecgOrderMainSingleMainEdit() {
		//主表KEY参数
		String obid = this.jeecgOrderMainSinglePage.getObid();
		//[1].根据订单ID，查询订单抬头信息
		JeecgOrderMainSingleEntity jeecgOrderMainSingleEntity  = this.jeecgOrderMainSingleService.get(obid);
		try {
			MyBeanUtils.copyBean2Bean(this.jeecgOrderMainSinglePage, jeecgOrderMainSingleEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//[2].根据主表ID,查询子表明细
		//订单客户明细
		jeecgOrderCustomSingleList = jeecgOrderMainSingleService.getJeecgOrderCustomSingleListByFkey(jeecgOrderMainSingleEntity.getObid(),jeecgOrderMainSingleEntity.getGoOrderCode());
		//订单产品明细
		jeecgOrderProductSingleList = jeecgOrderMainSingleService.getJeecgOrderProductSingleListByFkey(jeecgOrderMainSingleEntity.getObid(),jeecgOrderMainSingleEntity.getGoOrderCode());
		return "jeecgOrderMainSingle-main-edit";
	}
	
	
	/**
	 * 跳转到查看desc页面
	 * 
	 * @return
	 */
	public void showDesc() {
		writeJson(jeecgOrderMainSingleService.get(jeecgOrderMainSinglePage));
	}

	/**
	 * 获得pageHotel数据表格
	 */
	public void datagrid() {
		writeJson(jeecgOrderMainSingleService.datagrid(jeecgOrderMainSinglePage));
	}
	
	
	/**
	 * 获得无分页的所有数据
	 */
	public void  combox(){
		writeJson(jeecgOrderMainSingleService.listAll(jeecgOrderMainSinglePage));
	}

	/**
	 * 添加一对多
	 */
	public void addMain() {
		Json j = new Json();
		try {
			jeecgOrderMainSingleService.addMain(jeecgOrderMainSinglePage, jeecgOrderCustomSingleList,jeecgOrderProductSingleList);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}
	
	/**
	 * 编辑订单主数据
	 */
	public void editMain() {
		Json j = new Json();
		try {
			jeecgOrderMainSingleService.editMain(jeecgOrderMainSinglePage, jeecgOrderCustomSingleList,jeecgOrderProductSingleList);
			j.setSuccess(true);
			j.setMsg("修改订单主数据成功！");
		} catch (Exception e) {
			j.setMsg("修改失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 删除订单主数据
	 */
	public void delete() {
		Json j = new Json();
		jeecgOrderMainSingleService.delete(jeecgOrderMainSinglePage.getIds());
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 文件上传
	 */
	public void upload() {
		String savePath = ServletActionContext.getServletContext().getRealPath("/") + ResourceUtil.getUploadDirectory() + "/";// 文件保存目录路径
		String saveUrl = "/" + ResourceUtil.getUploadDirectory() + "/";// 文件保存目录URL

		String contentDisposition = ServletActionContext.getRequest().getHeader("Content-Disposition");// 如果是HTML5上传文件，那么这里有相应头的

		if (contentDisposition != null) {// HTML5拖拽上传文件
			Long fileSize = Long.valueOf(ServletActionContext.getRequest().getHeader("Content-Length"));// 上传的文件大小
			String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("filename=\""));// 文件名称
			fileName = fileName.substring(fileName.indexOf("\"") + 1);
			fileName = fileName.substring(0, fileName.indexOf("\""));

			ServletInputStream inputStream;
			try {
				inputStream = ServletActionContext.getRequest().getInputStream();
			} catch (IOException e) {
				uploadError("上传文件出错！");
				ExceptionUtil.getExceptionMessage(e);
				return;
			}

			if (inputStream == null) {
				uploadError("您没有上传任何文件！");
				return;
			}

			if (fileSize > ResourceUtil.getUploadFileMaxSize()) {
				uploadError("上传文件超出限制大小！", fileName);
				return;
			}

			// 检查文件扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(ResourceUtil.getUploadFileExts().split(",")).contains(fileExt)) {
				uploadError("上传文件扩展名是不允许的扩展名。\n只允许" + ResourceUtil.getUploadFileExts() + "格式！");
				return;
			}

			savePath += fileExt + "/";
			saveUrl += fileExt + "/";

			SimpleDateFormat yearDf = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthDf = new SimpleDateFormat("MM");
			SimpleDateFormat dateDf = new SimpleDateFormat("dd");
			Date date = new Date();
			String ymd = yearDf.format(date) + "/" + monthDf.format(date) + "/" + dateDf.format(date) + "/";
			savePath += ymd;
			saveUrl += ymd;

			File uploadDir = new File(savePath);// 创建要上传文件到指定的目录
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;// 新的文件名称
			File uploadedFile = new File(savePath, newFileName);

			try {
				FileCopyUtils.copy(inputStream, new FileOutputStream(uploadedFile));
			} catch (FileNotFoundException e) {
				uploadError("上传文件出错！");
				ExceptionUtil.getExceptionMessage(e);
				return;
			} catch (IOException e) {
				uploadError("上传文件出错！");
				ExceptionUtil.getExceptionMessage(e);
				return;
			}

			uploadSuccess(ServletActionContext.getRequest().getContextPath() + saveUrl + newFileName, fileName, 0);// 文件上传成功

			return;
		}

		MultiPartRequestWrapper multiPartRequest = (MultiPartRequestWrapper) ServletActionContext.getRequest();// 由于struts2上传文件时自动使用了request封装
		File[] files = multiPartRequest.getFiles(ResourceUtil.getUploadFieldName());// 上传的文件集合
		String[] fileNames = multiPartRequest.getFileNames(ResourceUtil.getUploadFieldName());// 上传文件名称集合

		if (files == null || files.length < 1) {
			uploadError("您没有上传任何文件！");
			return;
		}

		for (int i = 0; i < files.length; i++) {// 循环所有文件
			File file = files[i];// 上传的文件(临时文件)
			String fileName = fileNames[i];// 上传文件名

			if (file.length() > ResourceUtil.getUploadFileMaxSize()) {
				uploadError("上传文件超出限制大小！", fileName);
				return;
			}

			// 检查文件扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(ResourceUtil.getUploadFileExts().split(",")).contains(fileExt)) {
				uploadError("上传文件扩展名是不允许的扩展名。\n只允许" + ResourceUtil.getUploadFileExts() + "格式！");
				return;
			}

			savePath += fileExt + "/";
			saveUrl += fileExt + "/";

			SimpleDateFormat yearDf = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthDf = new SimpleDateFormat("MM");
			SimpleDateFormat dateDf = new SimpleDateFormat("dd");
			Date date = new Date();
			String ymd = yearDf.format(date) + "/" + monthDf.format(date) + "/" + dateDf.format(date) + "/";
			savePath += ymd;
			saveUrl += ymd;

			File uploadDir = new File(savePath);// 创建要上传文件到指定的目录
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;// 新的文件名称
			File uploadedFile = new File(savePath, newFileName);

			try {
				FileCopyUtils.copy(file, uploadedFile);// 利用spring的文件工具上传
			} catch (Exception e) {
				uploadError("上传文件失败！", fileName);
				return;
			}

			uploadSuccess(ServletActionContext.getRequest().getContextPath() + saveUrl + newFileName, fileName, i);// 文件上传成功

		}

	}

	private void uploadError(String err, String msg) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("err", err);
		m.put("msg", msg);
		writeJson(m);
	}

	private void uploadError(String err) {
		uploadError(err, "");
	}

	private void uploadSuccess(String newFileName, String fileName, int id) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("err", "");
		Map<String, Object> nm = new HashMap<String, Object>();
		nm.put("url", newFileName);
		nm.put("localfile", fileName);
		nm.put("id", id);
		m.put("msg", nm);
		writeJson(m);
	}
	
	public JeecgOrderMainSinglePage getJeecgOrderMainSinglePage() {
		return jeecgOrderMainSinglePage;
	}


	public void setJeecgOrderMainSinglePage(JeecgOrderMainSinglePage jeecgOrderMainSinglePage) {
		this.jeecgOrderMainSinglePage = jeecgOrderMainSinglePage;
	}
}
