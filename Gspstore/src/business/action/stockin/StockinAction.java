package business.action.stockin;

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
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import com.util.MyBeanUtils;

import business.page.article.ArticlePage;
import business.page.company.CompanyPage;
import business.page.depot.DepotPage;
import business.page.report.ReportQueryForm;
import business.page.stockin.StockinPage;
import business.entity.stockin.StockinEntity;
import business.service.article.ArticleServiceI;
import business.service.company.CompanyServiceI;
import business.service.depot.DepotServiceI;
import business.service.stockin.StockinServiceI;
import business.service.supplier.SupplierServiceI;
import business.page.stockin.StockinDetailPage;
import business.page.supplier.SupplierPage;
import business.entity.stockin.StockinDetailEntity;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Title: Action
 * @Description: 入库主表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:41
 * @version V1.0   
 *
 */
@Action(value = "stockinAction", results = { @Result(name = "stockin", location = "/business/stockin/stockin.jsp"),
													   @Result(name = "stockin-main-add", location = "/business/stockin/stockin-main-add.jsp"),
													   @Result(name = "stockin-main-detail", location = "/business/stockin/stockin-main-detail.jsp"),
													   @Result(name = "stockin-main-edit", location = "/business/stockin/stockin-main-edit.jsp"),
													   @Result(name = "view-stockin-total", location = "/business/stockin/view-stockin-total.jsp"),
													   @Result(name = "view-stockin-total-data", location = "/business/stockin/view-stockin-total-data.jsp"),
													   @Result(name = "view-stockin-detail", location = "/business/stockin/view-stockin-detail.jsp"),
													   @Result(name = "view-stockin-detail-data", location = "/business/stockin/view-stockin-detail-data.jsp")
													   })
public class StockinAction extends BaseAction implements ModelDriven<StockinPage> {

	private static final Logger logger = Logger.getLogger(StockinAction.class);

	private StockinServiceI stockinService;
	@Autowired
    private SupplierServiceI supplierService;
    @Autowired
    private DepotServiceI depotService;
	@Autowired
	private ArticleServiceI articleService;
	 @Autowired
	private CompanyServiceI companyService;
	    
	private CompanyPage companyPage;
	private StockinPage stockinPage = new StockinPage();
	
	private static String article_combox_data;
	
	private List<Map<String,Object>> viewStockinTotalList;
	private List<Map<String,Object>> viewStockinDetailList;
	private StockinDetailPage viewStockinDetailPage;
	private ReportQueryForm reportQueryForm;

	/**入库明细表*/
	private List<StockinDetailPage> stockinDetailList = new ArrayList<StockinDetailPage>();
	public List<StockinDetailPage> getStockinDetailList() {
		return stockinDetailList;
	}
	public void setStockinDetailList(List<StockinDetailPage> stockinDetailList) {
		this.stockinDetailList = stockinDetailList;
	}
	
	public StockinPage getModel() {
		return stockinPage;
	}


	public StockinServiceI getStockinService() {
		return stockinService;
	}

	@Autowired
	public void setStockinService(StockinServiceI stockinService) {
		this.stockinService = stockinService;
	}


	/**
	 * 跳转到入库主表管理页面
	 * 
	 * @return
	 */
	public String goStockin() {
		return "stockin";
	}

	
	/**
	 * 跳转一对多添加页面
	 * 
	 * @return
	 */
	public String toStockinMainAdd() {
		return "stockin-main-add";
	}
	
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toStockinMainEdit() {
		//主表KEY参数
		java.lang.String stockinbillno = this.stockinPage.getStockinbillno();
		//[1].根据主键ID，查询抬头信息
		StockinEntity stockinEntity  = this.stockinService.get(stockinbillno);
		try {
			MyBeanUtils.copyBean2Bean(this.stockinPage, stockinEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//[2].根据主表ID,查询子表明细
		//入库明细表
		stockinDetailList = stockinService.getStockinDetailListByFkey(stockinEntity.getStockinbillno());
		return "stockin-main-edit";
	}
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toStockinMainDetail() {
	    //主表KEY参数
	    java.lang.String stockinbillno = this.stockinPage.getStockinbillno();
	    //[1].根据主键ID，查询抬头信息
	    StockinEntity stockinEntity  = this.stockinService.get(stockinbillno);
	    try {
	        MyBeanUtils.copyBean2Bean(this.stockinPage, stockinEntity);
	      //获得供应商
            SupplierPage sp = new SupplierPage();
            BeanUtils.copyProperties(supplierService.get(stockinEntity.getSupplierid()),sp );
            this.stockinPage.setSupplierpage(sp);
            //获得仓库
            DepotPage dp = new DepotPage();
            BeanUtils.copyProperties(depotService.get(stockinEntity.getDepotid()),dp);
            this.stockinPage.setDepotpage(dp);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //[2].根据主表ID,查询子表明细
	    //入库明细表
	    stockinDetailList = stockinService.getStockinDetailListByFkey(stockinEntity.getStockinbillno());
	    companyPage = companyService.getOneCompany();
	    return "stockin-main-detail";
	}
	
	
	/**
	 * 跳转到查看desc页面
	 * 
	 * @return
	 */
	public void showDesc() {
		writeJson(stockinService.get(stockinPage));
	}

	/**
	 * 获得pageHotel数据表格
	 */
	public void datagrid() {
	     article_combox_data = JSON.toJSONStringWithDateFormat(articleService.listAll(new ArticlePage()), "yyyy-MM-dd HH:mm:ss");
		writeJson(stockinService.datagrid(stockinPage));
	}
	
	
	/**
	 * 获得无分页的所有数据
	 */
	public void  combox(){
		writeJson(stockinService.listAll(stockinPage));
	}

	/**
	 * 添加一对多
	 */
	public void addMain() {
		Json j = new Json();
		try {
			stockinService.addMain(stockinPage, stockinDetailList);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}
	
	/**
	 * 编辑入库主表
	 */
	public void editMain() {
		Json j = new Json();
		try {
			stockinService.editMain(stockinPage, stockinDetailList);
			j.setSuccess(true);
			j.setMsg("修改入库主表成功！");
		} catch (Exception e) {
			j.setMsg("修改失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 删除入库主表
	 */
	public void delete() {
		Json j = new Json();
		stockinService.delete(stockinPage.getIds());
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
	
	public StockinPage getStockinPage() {
		return stockinPage;
	}


	public void setStockinPage(StockinPage stockinPage) {
		this.stockinPage = stockinPage;
	}
    public String getArticle_combox_data()
    {
        return article_combox_data;
    }
    public void setArticle_combox_data(String article_combox_data)
    {
        this.article_combox_data = article_combox_data;
    }
    public ArticleServiceI getArticleService()
    {
        return articleService;
    }
    public void setArticleService(ArticleServiceI articleService)
    {
        this.articleService = articleService;
    }
    ///////////////////////////报表/////////////////////////////////////
    //采购汇总报表
    public String goViewStockinTotal(){
        return "view-stockin-total";
    }
   //采购汇总报表数据
    public String viewStockinTotalData(){
        viewStockinTotalList = stockinService.viewStockinTotalList(this.reportQueryForm);
        return "view-stockin-total-data";
    }
    //采购明细报表
    public String goViewStockinDetail(){
        return "view-stockin-detail";
    }
    //采购明细报表数据
    public String viewStockinDetailData(){
        viewStockinDetailList = stockinService.viewStockinDetailList(this.reportQueryForm);
        return "view-stockin-detail-data";
    }
    public List<Map<String, Object>> getViewStockinTotalList()
    {
        return viewStockinTotalList;
    }
    public void setViewStockinTotalList(
        List<Map<String, Object>> viewStockinTotalList)
    {
        this.viewStockinTotalList = viewStockinTotalList;
    }
    public List<Map<String, Object>> getViewStockinDetailList()
    {
        return viewStockinDetailList;
    }
    public void setViewStockinDetailList(
        List<Map<String, Object>> viewStockinDetailList)
    {
        this.viewStockinDetailList = viewStockinDetailList;
    }
    public StockinDetailPage getViewStockinDetailPage()
    {
        return viewStockinDetailPage;
    }
    public void setViewStockinDetailPage(StockinDetailPage viewStockinDetailPage)
    {
        this.viewStockinDetailPage = viewStockinDetailPage;
    }
    public ReportQueryForm getReportQueryForm()
    {
        return reportQueryForm;
    }
    public void setReportQueryForm(ReportQueryForm reportQueryForm)
    {
        this.reportQueryForm = reportQueryForm;
    }
    public CompanyPage getCompanyPage()
    {
        return companyPage;
    }
    public void setCompanyPage(CompanyPage companyPage)
    {
        this.companyPage = companyPage;
    }
}
