package business.page.stockback;

import java.math.BigDecimal;
import java.util.Date;

import business.page.depot.DepotPage;
import business.page.supplier.SupplierPage;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 购进退货单
 * @author zhangdaihao
 * @date 2013-05-17 17:21:13
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class StockBackPage  extends BasePage implements java.io.Serializable {
	/**购进退货单号*/
	private java.lang.String stockbackno;
	/**仓库ID*/
	private java.lang.String depotid;
	/**供应商*/
	private java.lang.String supplierid;
	private DepotPage depotpage;
	private SupplierPage supplierpage;
	/**退货人*/
	private java.lang.String crtuser;
	/**退货人姓名*/
	private java.lang.String crtusername;
	/**创建日期*/
	private java.util.Date createdate;
	/**修改日期*/
	private java.util.Date modifydate;
	/**序号*/
	private java.lang.Integer ordernum;
	/**备注*/
	private java.lang.String meno;
	/**入库单号*/
	private java.lang.String stockinno;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	/**金额*/
    private BigDecimal amount;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购进退货单号
	 */
	public java.lang.String getStockbackno(){
		return this.stockbackno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购进退货单号
	 */
	public void setStockbackno(java.lang.String stockbackno){
		this.stockbackno = stockbackno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库ID
	 */
	public java.lang.String getDepotid(){
		return this.depotid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库ID
	 */
	public void setDepotid(java.lang.String depotid){
		this.depotid = depotid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */
	public java.lang.String getSupplierid(){
		return this.supplierid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setSupplierid(java.lang.String supplierid){
		this.supplierid = supplierid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货人
	 */
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货人
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货人姓名
	 */
	public java.lang.String getCrtusername(){
		return this.crtusername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货人姓名
	 */
	public void setCrtusername(java.lang.String crtusername){
		this.crtusername = crtusername;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库单号
	 */
	public java.lang.String getStockinno(){
		return this.stockinno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库单号
	 */
	public void setStockinno(java.lang.String stockinno){
		this.stockinno = stockinno;
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

    public DepotPage getDepotpage()
    {
        return depotpage;
    }

    public void setDepotpage(DepotPage depotpage)
    {
        this.depotpage = depotpage;
    }

    public SupplierPage getSupplierpage()
    {
        return supplierpage;
    }

    public void setSupplierpage(SupplierPage supplierpage)
    {
        this.supplierpage = supplierpage;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
}
