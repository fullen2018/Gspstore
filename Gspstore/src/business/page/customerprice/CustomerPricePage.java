package business.page.customerprice;

import java.math.BigDecimal;
import java.util.Date;

import business.page.article.ArticlePage;
import business.page.customer.CustomerPage;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 客户品种价格
 * @author zhangdaihao
 * @date 2013-06-07 00:44:34
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class CustomerPricePage  extends BasePage implements java.io.Serializable {
	/**品种名称*/
	private java.lang.String articleid;
	private ArticlePage articlepage;
	/**客户名称*/
	private java.lang.String customerid;
	private CustomerPage customerpage;
	/**销售价*/
	private BigDecimal saleprice;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**流水号*/
	private java.lang.String flowid;
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
	 *@return: java.lang.String  品种名称
	 */
	public java.lang.String getArticleid(){
		return this.articleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种名称
	 */
	public void setArticleid(java.lang.String articleid){
		this.articleid = articleid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */
	public java.lang.String getCustomerid(){
		return this.customerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCustomerid(java.lang.String customerid){
		this.customerid = customerid;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  销售价
	 */
	public BigDecimal getSaleprice(){
		return this.saleprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  销售价
	 */
	public void setSaleprice(BigDecimal saleprice){
		this.saleprice = saleprice;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流水号
	 */
	public java.lang.String getFlowid(){
		return this.flowid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流水号
	 */
	public void setFlowid(java.lang.String flowid){
		this.flowid = flowid;
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

    public ArticlePage getArticlepage()
    {
        return articlepage;
    }

    public void setArticlepage(ArticlePage articlepage)
    {
        this.articlepage = articlepage;
    }

    public CustomerPage getCustomerpage()
    {
        return customerpage;
    }

    public void setCustomerpage(CustomerPage customerpage)
    {
        this.customerpage = customerpage;
    }
}
