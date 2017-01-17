package business.page.saleback;

import java.math.BigDecimal;
import java.util.Date;

import business.page.accounter.AccounterPage;
import business.page.customer.CustomerPage;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 销售退货单
 * @author zhangdaihao
 * @date 2013-05-20 13:40:48
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class SaleBackPage  extends BasePage implements java.io.Serializable {
	/**销售退货单号*/
	private java.lang.String salebackno;
	/**原销售单号*/
	private java.lang.String salebillno;
	/**退货客户*/
	private java.lang.String customerid;
	private CustomerPage customerpage;
	/**退货日期*/
	private java.util.Date backdate;
	/**业务员*/
	private java.lang.String accountid;
	private AccounterPage accounterpage;
	/**原提成比例*/
	private BigDecimal salerate;
	/**备注*/
	private java.lang.String meno;
	/**是否收款*/
	private java.lang.Integer isreceive;
	/**操作人姓名*/
	private java.lang.String crtusername;
	/**操作人*/
	private java.lang.String crtuser;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**总金额*/
	private BigDecimal amount;
	/**序号*/
	private java.lang.Integer ordernum;
	/**退货日期 开始时间 */
	private java.util.Date beginBackdate;
	/**退货日期 结束时间*/
	private java.util.Date endBackdate;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售退货单号
	 */
	public java.lang.String getSalebackno(){
		return this.salebackno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售退货单号
	 */
	public void setSalebackno(java.lang.String salebackno){
		this.salebackno = salebackno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原销售单号
	 */
	public java.lang.String getSalebillno(){
		return this.salebillno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原销售单号
	 */
	public void setSalebillno(java.lang.String salebillno){
		this.salebillno = salebillno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货客户
	 */
	public java.lang.String getCustomerid(){
		return this.customerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货客户
	 */
	public void setCustomerid(java.lang.String customerid){
		this.customerid = customerid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  退货日期
	 */
	public java.util.Date getBackdate(){
		return this.backdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  退货日期
	 */
	public void setBackdate(java.util.Date backdate){
		this.backdate = backdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  原提成比例
	 */
	public BigDecimal getSalerate(){
		return this.salerate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  原提成比例
	 */
	public void setSalerate(BigDecimal salerate){
		this.salerate = salerate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public java.lang.String getMeno(){
		return this.meno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setMeno(java.lang.String meno){
		this.meno = meno;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否收款
	 */
	public java.lang.Integer getIsreceive(){
		return this.isreceive;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否收款
	 */
	public void setIsreceive(java.lang.Integer isreceive){
		this.isreceive = isreceive;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作人姓名
	 */
	public java.lang.String getCrtusername(){
		return this.crtusername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人姓名
	 */
	public void setCrtusername(java.lang.String crtusername){
		this.crtusername = crtusername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作人
	 */
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改日期
	 */
	public java.util.Date getModifydate(){
		return this.modifydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改日期
	 */
	public void setModifydate(java.util.Date modifydate){
		this.modifydate = modifydate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	public java.util.Date getCreatedate(){
		return this.createdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreatedate(java.util.Date createdate){
		this.createdate = createdate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  总金额
	 */
	public BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  总金额
	 */
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */
	public java.lang.Integer getOrdernum(){
		return this.ordernum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 */
	public void setOrdernum(java.lang.Integer ordernum){
		this.ordernum = ordernum;
	}
	
	public java.util.Date getBeginBackdate(){
		return this.beginBackdate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  退货日期开始时间
	 */
	public void setBeginBackdate(java.util.Date beginBackdate){
		this.beginBackdate = beginBackdate;
	}
	
	public java.util.Date getEndBackdate(){
		return this.endBackdate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  退货日期结束时间
	 */
	public void setEndBackdate(java.util.Date endBackdate){
		this.endBackdate = endBackdate;
	}
	public java.util.Date getBeginModifydate(){
		return this.beginModifydate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  修改日期开始时间
	 */
	public void setBeginModifydate(java.util.Date beginModifydate){
		this.beginModifydate = beginModifydate;
	}
	
	public java.util.Date getEndModifydate(){
		return this.endModifydate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  修改日期结束时间
	 */
	public void setEndModifydate(java.util.Date endModifydate){
		this.endModifydate = endModifydate;
	}
	public java.util.Date getBeginCreatedate(){
		return this.beginCreatedate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  创建日期开始时间
	 */
	public void setBeginCreatedate(java.util.Date beginCreatedate){
		this.beginCreatedate = beginCreatedate;
	}
	
	public java.util.Date getEndCreatedate(){
		return this.endCreatedate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  创建日期结束时间
	 */
	public void setEndCreatedate(java.util.Date endCreatedate){
		this.endCreatedate = endCreatedate;
	}

    public CustomerPage getCustomerpage()
    {
        return customerpage;
    }

    public void setCustomerpage(CustomerPage customerpage)
    {
        this.customerpage = customerpage;
    }

    public AccounterPage getAccounterpage()
    {
        return accounterpage;
    }

    public void setAccounterpage(AccounterPage accounterpage)
    {
        this.accounterpage = accounterpage;
    }
}
