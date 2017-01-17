package business.page.catelog;

import java.math.BigDecimal;
import java.util.Date;
import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 药品类别信息
 * @author zhangdaihao
 * @date 2013-05-13 16:40:33
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class CatelogPage  extends BasePage implements java.io.Serializable {
	// 自定义属性
	private String id;
	private String state = "open";// 是否展开(open,closed)
	private String cpname;
	private String iconCls;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	/**catelogcode*/
	private java.lang.String catelogcode;
	/**cname*/
	private java.lang.String cname;
	/**catelogmeno*/
	private java.lang.String catelogmeno;
	/**ciconcls*/
	private java.lang.String ciconcls;
	/**cpid*/
	private java.lang.String cpid;
	/**cseq*/
	private java.lang.Integer cseq;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catelogcode
	 */
	public java.lang.String getCatelogcode(){
		return this.catelogcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catelogcode
	 */
	public void setCatelogcode(java.lang.String catelogcode){
		this.catelogcode = catelogcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cname
	 */
	public java.lang.String getCname(){
		return this.cname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cname
	 */
	public void setCname(java.lang.String cname){
		this.cname = cname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catelogmeno
	 */
	public java.lang.String getCatelogmeno(){
		return this.catelogmeno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catelogmeno
	 */
	public void setCatelogmeno(java.lang.String catelogmeno){
		this.catelogmeno = catelogmeno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ciconcls
	 */
	public java.lang.String getCiconcls(){
		return this.ciconcls;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ciconcls
	 */
	public void setCiconcls(java.lang.String ciconcls){
		this.ciconcls = ciconcls;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cpid
	 */
	public java.lang.String getCpid(){
		return this.cpid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cpid
	 */
	public void setCpid(java.lang.String cpid){
		this.cpid = cpid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  cseq
	 */
	public java.lang.Integer getCseq(){
		return this.cseq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  cseq
	 */
	public void setCseq(java.lang.Integer cseq){
		this.cseq = cseq;
	}
}
