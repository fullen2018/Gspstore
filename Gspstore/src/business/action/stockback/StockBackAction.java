package business.action.stockback;

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

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import com.util.MyBeanUtils;

import business.page.company.CompanyPage;
import business.page.depot.DepotPage;
import business.page.stockback.StockBackPage;
import business.entity.stockback.StockBackEntity;
import business.service.company.CompanyServiceI;
import business.service.depot.DepotServiceI;
import business.service.stockback.StockBackServiceI;
import business.service.stockin.StockinServiceI;
import business.service.supplier.SupplierServiceI;
import business.page.stockback.StockBackDetailPage;
import business.page.stockin.StockinDetailPage;
import business.page.stockin.StockinPage;
import business.page.supplier.SupplierPage;
import business.entity.stockback.StockBackDetailEntity;
import business.entity.stockin.StockinEntity;
import business.exception.StockLessBackNumException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**   
 * @Title: Action
 * @Description: 购进退货单
 * @author zhangdaihao
 * @date 2013-05-17 17:21:12
 * @version V1.0   
 *
 */
@Action(value = "stockBackAction", results = { @Result(name = "stockBack", location = "/business/stockback/stockBack.jsp"),
													   @Result(name = "stockBack-main-add", location = "/business/stockback/stockBack-main-add.jsp"),
													   @Result(name = "stockBack-main-detail", location = "/business/stockback/stockBack-main-detail.jsp"),
													   @Result(name = "stockBack-main-edit", location = "/business/stockback/stockBack-main-edit.jsp")})
public class StockBackAction extends BaseAction implements ModelDriven<StockBackPage> {

	private static final Logger logger = Logger.getLogger(StockBackAction.class);

	private StockBackServiceI stockBackService;
	@Autowired
	private StockinServiceI stockinService;
	 @Autowired
    private CompanyServiceI companyService;
	    
	private CompanyPage companyPage;
	private StockBackPage stockBackPage = new StockBackPage();
	private StockinPage stockinPage = new StockinPage();
	@Autowired
    private SupplierServiceI supplierService;
    @Autowired
    private DepotServiceI depotService;
	/**入库明细表*/
    private List<StockinDetailPage> stockinDetailList = new ArrayList<StockinDetailPage>();
    public List<StockinDetailPage> getStockinDetailList() {
        return stockinDetailList;
    }
    public void setStockinDetailList(List<StockinDetailPage> stockinDetailList) {
        this.stockinDetailList = stockinDetailList;
    }
	/**购进退货明细*/
	private List<StockBackDetailPage> stockBackDetailList = new ArrayList<StockBackDetailPage>();
	public List<StockBackDetailPage> getStockBackDetailList() {
		return stockBackDetailList;
	}
	public void setStockBackDetailList(List<StockBackDetailPage> stockBackDetailList) {
		this.stockBackDetailList = stockBackDetailList;
	}
	
	public StockBackPage getModel() {
		return stockBackPage;
	}


	public StockBackServiceI getStockBackService() {
		return stockBackService;
	}

	@Autowired
	public void setStockBackService(StockBackServiceI stockBackService) {
		this.stockBackService = stockBackService;
	}


	/**
	 * 跳转到购进退货单管理页面
	 * 
	 * @return
	 */
	public String goStockBack() {
		return "stockBack";
	}

	
	/**
	 * 跳转一对多添加页面
	 * 
	 * @return
	 */
	public String toStockBackMainAdd() {
	    ActionContext ct = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ct.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String stockinbillno = request.getParameter("stockinbillno")==null?"0":request.getParameter("stockinbillno").toString();
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
      //入库明细表
        stockinDetailList = stockinService.getStockinDetailListByFkey(stockinbillno);
		return "stockBack-main-add";
	}
	
	/**
	 * 跳转一对多编辑页面
	 * 
	 * @return
	 */
	public String toStockBackMainEdit() {
	    ActionContext ct = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest)ct.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String stockinbillno = request.getParameter("stockinbillno")==null?"0":request.getParameter("stockinbillno").toString();
        StockinEntity stockinEntity  = this.stockinService.get(stockinbillno);
        try {
            MyBeanUtils.copyBean2Bean(this.stockinPage, stockinEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //主表KEY参数
		java.lang.String stockbackno = this.stockBackPage.getStockbackno();
		//[1].根据主键ID，查询抬头信息
		StockBackEntity stockBackEntity  = this.stockBackService.get(stockbackno);
		try {
			MyBeanUtils.copyBean2Bean(this.stockBackPage, stockBackEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//[2].根据主表ID,查询子表明细
        //入库明细表
        stockinDetailList = stockinService.getStockinDetailListByFkey(stockinbillno);
		//[2].根据主表ID,查询子表明细
		//购进退货明细
		stockBackDetailList = stockBackService.getStockBackDetailListByFkey(stockBackEntity.getStockbackno());
		return "stockBack-main-edit";
	}
	public String toStockBackMainDetail() {
	    //主表KEY参数
	    java.lang.String stockbackno = this.stockBackPage.getStockbackno();
	    //[1].根据主键ID，查询抬头信息
	    StockBackEntity stockBackEntity  = this.stockBackService.get(stockbackno);
	    try {
	        MyBeanUtils.copyBean2Bean(this.stockBackPage, stockBackEntity);
	      //获得供应商
            SupplierPage sp = new SupplierPage();
            BeanUtils.copyProperties(supplierService.get(stockBackEntity.getSupplierid()),sp );
            this.stockBackPage.setSupplierpage(sp);
            //获得仓库
            DepotPage dp = new DepotPage();
            BeanUtils.copyProperties(depotService.get(stockBackEntity.getDepotid()),dp);
            this.stockBackPage.setDepotpage(dp);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	   
	    //[2].根据主表ID,查询子表明细
	    //购进退货明细
	    stockBackDetailList = stockBackService.getStockBackDetailListByFkey(stockBackEntity.getStockbackno());
	    companyPage = companyService.getOneCompany();
	    return "stockBack-main-detail";
	}
	
	
	/**
	 * 跳转到查看desc页面
	 * 
	 * @return
	 */
	public void showDesc() {
		writeJson(stockBackService.get(stockBackPage));
	}

	/**
	 * 获得pageHotel数据表格
	 */
	public void datagrid() {
		writeJson(stockBackService.datagrid(stockBackPage));
	}
	
	
	/**
	 * 获得无分页的所有数据
	 */
	public void  combox(){
		writeJson(stockBackService.listAll(stockBackPage));
	}

	/**
	 * 添加一对多
	 */
	public void addMain() {
		Json j = new Json();
		try {
			stockBackService.addMain(stockBackPage, stockBackDetailList);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (StockLessBackNumException e) {
			j.setMsg(e.getMessage());
			//logger.error(ExceptionUtil.getExceptionMessage(e));
		}catch (Exception e) {
            j.setMsg("添加失败！"+e.getMessage());
            logger.error(ExceptionUtil.getExceptionMessage(e));
        }
		writeJson(j);
	}
	
	/**
	 * 编辑购进退货单
	 */
	public void editMain() {
		Json j = new Json();
		try {
			stockBackService.editMain(stockBackPage, stockBackDetailList);
			j.setSuccess(true);
			j.setMsg("修改购进退货单成功！");
		} catch (Exception e) {
			j.setMsg("修改失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 删除购进退货单
	 */
	public void delete() {
		Json j = new Json();
		stockBackService.delete(stockBackPage.getIds());
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
	
	public StockBackPage getStockBackPage() {
		return stockBackPage;
	}


	public void setStockBackPage(StockBackPage stockBackPage) {
		this.stockBackPage = stockBackPage;
	}
    public StockinPage getStockinPage()
    {
        return stockinPage;
    }
    public void setStockinPage(StockinPage stockinPage)
    {
        this.stockinPage = stockinPage;
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
