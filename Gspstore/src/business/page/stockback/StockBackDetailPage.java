package business.page.stockback;

import java.math.BigDecimal;
import java.util.Date;

import business.page.article.ArticlePage;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 购进退货明细
 * @author zhangdaihao
 * @date 2013-05-17 17:21:10
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class StockBackDetailPage  extends BasePage implements java.io.Serializable {
	/**购进退货明细主键*/
	private java.lang.String stockbackdetailno;
	/**购进退货单号*/
	private java.lang.String stockbackno;
	/**入库明细单ID*/
    private java.lang.String stockindetailno;
	/**品种ID号*/
	private java.lang.String articleid;
	private ArticlePage articlepage;
	/**批号*/
	private java.lang.String serial;
	/**有效期*/
	private java.util.Date expiredate;
	/**数量*/
	private BigDecimal num;
	/**进价*/
	private BigDecimal price;
	/**金额*/
	private BigDecimal amount;
	/**有效期 开始时间 */
	private java.util.Date beginExpiredate;
	/**有效期 结束时间*/
	private java.util.Date endExpiredate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购进退货明细主键
	 */
	public java.lang.String getStockbackdetailno(){
		return this.stockbackdetailno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购进退货明细主键
	 */
	public void setStockbackdetailno(java.lang.String stockbackdetailno){
		this.stockbackdetailno = stockbackdetailno;
	}
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
	 *@return: java.lang.String  品种ID号
	 */
	public java.lang.String getArticleid(){
		return this.articleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种ID号
	 */
	public void setArticleid(java.lang.String articleid){
		this.articleid = articleid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批号
	 */
	public java.lang.String getSerial(){
		return this.serial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批号
	 */
	public void setSerial(java.lang.String serial){
		this.serial = serial;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期
	 */
	public java.util.Date getExpiredate(){
		return this.expiredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效期
	 */
	public void setExpiredate(java.util.Date expiredate){
		this.expiredate = expiredate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  数量
	 */
	public BigDecimal getNum(){
		return this.num;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  数量
	 */
	public void setNum(BigDecimal num){
		this.num = num;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  进价
	 */
	public BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  进价
	 */
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  金额
	 */
	public BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  金额
	 */
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	
	public java.util.Date getBeginExpiredate(){
		return this.beginExpiredate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  有效期开始时间
	 */
	public void setBeginExpiredate(java.util.Date beginExpiredate){
		this.beginExpiredate = beginExpiredate;
	}
	
	public java.util.Date getEndExpiredate(){
		return this.endExpiredate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  有效期结束时间
	 */
	public void setEndExpiredate(java.util.Date endExpiredate){
		this.endExpiredate = endExpiredate;
	}

    public ArticlePage getArticlepage()
    {
        return articlepage;
    }

    public void setArticlepage(ArticlePage articlepage)
    {
        this.articlepage = articlepage;
    }

    public java.lang.String getStockindetailno()
    {
        return stockindetailno;
    }

    public void setStockindetailno(java.lang.String stockindetailno)
    {
        this.stockindetailno = stockindetailno;
    }
}
