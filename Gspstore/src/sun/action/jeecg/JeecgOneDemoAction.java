package sun.action.jeecg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import com.opensymphony.xwork2.ModelDriven;

import com.jeecg.action.BaseAction;
import com.jeecg.pageModel.Json;
import com.jeecg.util.ExceptionUtil;
import com.jeecg.util.ResourceUtil;
import sun.page.jeecg.JeecgOneDemoPage;
import sun.service.jeecg.JeecgOneDemoServiceI;


/**   
 * @Title: Action
 * @Description: 单表模型（DEMO
 * @author zhangdaihao
 * @date 2013-01-23 17:12:40
 * @version V1.0   
 *
 */
@Action(value = "jeecgOneDemoAction", results = { @Result(name = "jeecgOneDemo", location = "/sun/jeecg/jeecgOneDemo.jsp"),
												  @Result(name = "jeecgOneDemo-edit", location = "/sun/jeecg/jeecgOneDemo-edit.jsp")})
public class JeecgOneDemoAction extends BaseAction implements ModelDriven<JeecgOneDemoPage> {

	private static final Logger logger = Logger.getLogger(JeecgOneDemoAction.class);

	private JeecgOneDemoServiceI jeecgOneDemoService;

	private JeecgOneDemoPage jeecgOneDemoPage = new JeecgOneDemoPage();

	public JeecgOneDemoPage getModel() {
		return jeecgOneDemoPage;
	}


	public JeecgOneDemoServiceI getJeecgOneDemoService() {
		return jeecgOneDemoService;
	}

	@Autowired
	public void setJeecgOneDemoService(JeecgOneDemoServiceI jeecgOneDemoService) {
		this.jeecgOneDemoService = jeecgOneDemoService;
	}


	/**
	 * 跳转到单表模型（DEMO）管理页
	 * 
	 * @return
	 */
	public String goJeecgOneDemo() {
		return "jeecgOneDemo";
	}

	
	/**
	 * 跳转到编辑页
	 * 
	 * @return
	 */
	public String toEdit() {
		getRequest().setAttribute("jeecgOneDemo", jeecgOneDemoService.get(jeecgOneDemoPage.getObid()));
		return "jeecgOneDemo-edit";
	}
	
	/**
	 * 获得pageHotel数据表格
	 */
	public void datagrid() {
		writeJson(jeecgOneDemoService.datagrid(jeecgOneDemoPage));
	}
	
	
	/**
	 * 获得无分页的有数
	 */
	public void  combox(){
		writeJson(jeecgOneDemoService.listAll(jeecgOneDemoPage));
	}

	/**
	 * 添加个单表模型（DEMO
	 */
	public void add() {
		Json j = new Json();
		try {
			jeecgOneDemoService.add(jeecgOneDemoPage);
			j.setSuccess(true);
			j.setMsg("添加成功");
		} catch (Exception e) {
			j.setMsg("添加失败");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		writeJson(j);
	}

	/**
	 * 编辑单表模型（DEMO
	 */
	public void edit() {
		Json j = new Json();
		try {
			jeecgOneDemoService.update(jeecgOneDemoPage);
			j.setSuccess(true);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败");
		}
		writeJson(j);
	}

	/**
	 * 删除单表模型（DEMO
	 */
	public void delete() {
		Json j = new Json();
		try {
			jeecgOneDemoService.delete(jeecgOneDemoPage.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败");
		}
		writeJson(j);
	}

	
	public JeecgOneDemoPage getJeecgOneDemoPage() {
		return jeecgOneDemoPage;
	}


	public void setJeecgOneDemoPage(JeecgOneDemoPage jeecgOneDemoPage) {
		this.jeecgOneDemoPage = jeecgOneDemoPage;
	}
}
