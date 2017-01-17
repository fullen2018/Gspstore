package business.action.sale;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import com.util.MyBeanUtils;

import business.page.accounter.AccounterPage;
import business.page.company.CompanyPage;
import business.page.customer.CustomerPage;
import business.page.report.ReportQueryForm;
import business.page.sale.SalePage;
import business.entity.sale.SaleEntity;
import business.service.accounter.AccounterServiceI;
import business.service.article.ArticleServiceI;
import business.service.company.CompanyServiceI;
import business.service.customer.CustomerServiceI;
import business.service.sale.SaleServiceI;
import business.page.sale.SaleDetailPage;
import business.page.stockin.StockinDetailPage;
import business.entity.sale.SaleDetailEntity;
import business.exception.StockLessBackNumException;

import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Title: Action
 * @Description: 销售单
 * @author zhangdaihao
 * @date 2013-05-19 10:15:01
 * @version V1.0   
 *
 */
@Action(value = "saleAction", results = { @Result(name = "sale", location = "/business/sale/sale.jsp"),
													   @Result(name = "sale-main-add", location = "/business/sale/sale-main-add.jsp"),
													   @Result(name = "sale-main-detail", location = "/business/sale/sale-main-detail.jsp"),
													   @Result(name = "sale-main-edit", location = "/business/sale/sale-main-edit.jsp"),
													   @Result(name = "view-sale-total", location = "/business/sale/view-sale-total.jsp"),
                                                       @Result(name = "view-sale-total-data", location = "/business/sale/view-sale-total-data.jsp"),
                                                       @Result(name = "view-sale-detail", location = "/business/sale/view-sale-detail.jsp"),
                                                       @Result(name = "view-sale-detail-data", location = "/business/sale/view-sale-detail-data.jsp"),
                                                       @Result(name = "view-sale-by-accounter", location = "/business/sale/view-sale-by-accounter.jsp"),
                                                       @Result(name = "view-sale-by-accounter-data-huizong", location = "/business/sale/view-sale-by-accounter-data-huizong.jsp"),
                                                       @Result(name = "view-sale-by-accounter-data-mingxi", location = "/business/sale/view-sale-by-accounter-data-mingxi.jsp")
})
public class SaleAction extends BaseAction implements ModelDriven<SalePage> {

	private static final Logger logger = Logger.getLogger(SaleAction.class);

	private SaleServiceI saleService;

	private SalePage salePage = new SalePage();
	@Autowired
    private ArticleServiceI articleService;
    @Autowired
    private CustomerServiceI customerService;
    @Autowired
    private AccounterServiceI  accounterService;
    @Autowired
    private CompanyServiceI companyService;
    
    private CompanyPage companyPage;

	/**销售单明细*/
	private List<SaleDetailPage> saleDetailList = new ArrayList<SaleDetailPage>();
	
	private List<Map<String,Object>> viewSaleTotalList;
    private List<Map<String,Object>> viewSaleDetailList;
    private List<Map<String,Object>> viewSaleByAccounterList;
    private SaleDetailPage viewSaleDetailPage;
    private ReportQueryForm reportQueryForm;
    
	public List<SaleDetailPage> getSaleDetailList() {
		return saleDetailList;
	}
	public void setSaleDetailList(List<SaleDetailPage> saleDetailList) {
		this.saleDetailList = saleDetailList;
	}
	
	public SalePage getModel() {
		return salePage;
	}


	public SaleServiceI getSaleService() {
		return saleService;
	}

	@Autowired
	public void setSaleService(SaleServiceI saleService) {
		this.saleService = saleService;
	}


	/**
	 * 跳转到销售单管理页面
	 * 
	 * @return
	 */
	public String goSale() {
		return "sale";
	}

	
	/**
	 * 跳转一对多添加页面
	 * 
	 * @return
	 */
	public String toSaleMainAdd() {
		return "sale-main-add";
	}
	
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toSaleMainEdit() {
		//主表KEY参数
		java.lang.String salebillno = this.salePage.getSalebillno();
		//[1].根据主键ID，查询抬头信息
		SaleEntity saleEntity  = this.saleService.get(salebillno);
		try {
			MyBeanUtils.copyBean2Bean(this.salePage, saleEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//[2].根据主表ID,查询子表明细
		//销售单明细
		saleDetailList = saleService.getSaleDetailListByFkey(saleEntity.getSalebillno());
		return "sale-main-edit";
	}
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toSaleMainDetail() {
	    //主表KEY参数
	    java.lang.String salebillno = this.salePage.getSalebillno();
	    //[1].根据主键ID，查询抬头信息
	    SaleEntity saleEntity  = this.saleService.get(salebillno);
	    try {
	        MyBeanUtils.copyBean2Bean(this.salePage, saleEntity);
	      //获得业务员
            AccounterPage ap = new AccounterPage();
            BeanUtils.copyProperties(accounterService.get(saleEntity.getAccountid()),ap);
            this.salePage.setAccounterpage(ap);
            //获得客户
            CustomerPage cp = new CustomerPage();
            BeanUtils.copyProperties(customerService.get(saleEntity.getCustomerid()), cp);
            this.salePage.setCustomerpage(cp);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //[2].根据主表ID,查询子表明细
	    //销售单明细
	    saleDetailList = saleService.getSaleDetailListByFkey(saleEntity.getSalebillno());
	    companyPage = companyService.getOneCompany();
	    return "sale-main-detail";
	}
	
	
	/**
	 * 跳转到查看desc页面
	 * 
	 * @return
	 */
	public void showDesc() {
		writeJson(saleService.get(salePage));
	}

	/**
	 * 获得pageHotel数据表格
	 */
	public void datagrid() {
		writeJson(saleService.datagrid(salePage));
	}
	
	/**
	 * 获得pageHotel数据表格
	 */
	public void saleStockCombox() {
	    writeJson(saleService.getSaleStock());
	}
	
	
	/**
	 * 获得无分页的所有数据
	 */
	public void  combox(){
		writeJson(saleService.listAll(salePage));
	}

	/**
	 * 添加一对多
	 */
	public void addMain() {
		Json j = new Json();
		try {
			saleService.addMain(salePage, saleDetailList);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (StockLessBackNumException e) {
		            j.setMsg(e.getMessage());
		            //logger.error(ExceptionUtil.getExceptionMessage(e));
		}catch (Exception e) {
		            j.setMsg("添加失败！"+e.getMessage());
		            logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}
	
	/**
	 * 编辑销售单
	 */
	public void editMain() {
		Json j = new Json();
		try {
			saleService.editMain(salePage, saleDetailList);
			j.setSuccess(true);
			j.setMsg("修改销售单成功！");
		} catch (Exception e) {
			j.setMsg("修改失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 删除销售单
	 */
	public void delete() {
		Json j = new Json();
		saleService.delete(salePage.getIds());
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
	
	public SalePage getSalePage() {
		return salePage;
	}


	public void setSalePage(SalePage salePage) {
		this.salePage = salePage;
	}
    public List<Map<String, Object>> getViewSaleTotalList()
    {
        return viewSaleTotalList;
    }
    public void setViewSaleTotalList(List<Map<String, Object>> viewSaleTotalList)
    {
        this.viewSaleTotalList = viewSaleTotalList;
    }
    public List<Map<String, Object>> getViewSaleDetailList()
    {
        return viewSaleDetailList;
    }
    public void setViewSaleDetailList(List<Map<String, Object>> viewSaleDetailList)
    {
        this.viewSaleDetailList = viewSaleDetailList;
    }
  
///////////////////////////报表/////////////////////////////////////
    //销售汇总报表
    public String goViewSaleTotal(){
        return "view-sale-total";
    }
   //销售汇总报表数据
    public String viewSaleTotalData(){
        viewSaleTotalList = saleService.viewSaleTotalList(this.reportQueryForm);
        return "view-sale-total-data";
    }
    //销售明细报表
    public String goViewSaleDetail(){
        return "view-sale-detail";
    }
    //销售明细报表数据
    public String viewSaleDetailData(){
        viewSaleDetailList = saleService.viewSaleDetailList(this.reportQueryForm);
        return "view-sale-detail-data";
    }
    //分业务员销售统计
    public String goViewSaleByAccounter(){
        return "view-sale-by-accounter";
    }
    //分业务员销售统计报表数据
    public String viewSaleByAccounterData(){
        if (reportQueryForm.getReporttype().equals("hz")){
            viewSaleByAccounterList = saleService.viewSaleByAccounterList_HuiZong(reportQueryForm);
            return "view-sale-by-accounter-data-huizong";
        }else{
            viewSaleByAccounterList = saleService.viewSaleByAccounterList_MingXi(reportQueryForm);
            return "view-sale-by-accounter-data-mingxi";
        }
    }
    public SaleDetailPage getViewSaleDetailPage()
    {
        return viewSaleDetailPage;
    }
    public void setViewSaleDetailPage(SaleDetailPage viewSaleDetailPage)
    {
        this.viewSaleDetailPage = viewSaleDetailPage;
    }
    public ReportQueryForm getReportQueryForm()
    {
        return reportQueryForm;
    }
    public void setReportQueryForm(ReportQueryForm reportQueryForm)
    {
        this.reportQueryForm = reportQueryForm;
    }
    public List<Map<String, Object>> getViewSaleByAccounterList()
    {
        return viewSaleByAccounterList;
    }
    public void setViewSaleByAccounterList(
        List<Map<String, Object>> viewSaleByAccounterList)
    {
        this.viewSaleByAccounterList = viewSaleByAccounterList;
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
