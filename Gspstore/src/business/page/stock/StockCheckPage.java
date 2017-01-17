package business.page.stock;

import java.math.BigDecimal;

import business.page.article.ArticlePage;
import business.page.depot.DepotPage;

import com.core.base.BasePage;
/**   
 * @Title: Page
 * @Description: 库存盘点
 * @author zhangdaihao
 * @date 2013-05-16 23:47:32
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class StockCheckPage  extends BasePage implements java.io.Serializable {
    private java.lang.String stockkey;
	/**盘点单号*/
	private java.lang.String stockcheckcode;
	/**仓库ID*/
	private java.lang.String depotid;
	/**品种ID*/
	private java.lang.String articleid;
	/**批号*/
	private java.lang.String serial;
	/**电脑数量*/
	private BigDecimal computernum;
	/**实际数量*/
	private BigDecimal realnum;
	/**成本价*/
	private BigDecimal costprice;
	/**有效期*/
	private java.util.Date expiredate;
	/**盘点人*/
	private java.lang.String crtuser;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**盘点人姓名*/
	private java.lang.String crtusername;
	/**序号*/
	private java.lang.Integer ordernum;
	/**有效期 开始时间 */
	private java.util.Date beginExpiredate;
	/**有效期 结束时间*/
	private java.util.Date endExpiredate;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	
	private DepotPage depotpage;
	private ArticlePage articlepage;
	
	public DepotPage getDepotpage()
    {
        return depotpage;
    }

    public void setDepotpage(DepotPage depotpage)
    {
        this.depotpage = depotpage;
    }

    public ArticlePage getArticlepage()
    {
        return articlepage;
    }

    public void setArticlepage(ArticlePage articlepage)
    {
        this.articlepage = articlepage;
    }

    /**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点单号
	 */
	public java.lang.String getStockcheckcode(){
		return this.stockcheckcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点单号
	 */
	public void setStockcheckcode(java.lang.String stockcheckcode){
		this.stockcheckcode = stockcheckcode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种ID
	 */
	public java.lang.String getArticleid(){
		return this.articleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种ID
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  电脑数量
	 */
	public BigDecimal getComputernum(){
		return this.computernum;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  电脑数量
	 */
	public void setComputernum(BigDecimal computernum){
		this.computernum = computernum;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  实际数量
	 */
	public BigDecimal getRealnum(){
		return this.realnum;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  实际数量
	 */
	public void setRealnum(BigDecimal realnum){
		this.realnum = realnum;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  成本价
	 */
	public BigDecimal getCostprice(){
		return this.costprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  成本价
	 */
	public void setCostprice(BigDecimal costprice){
		this.costprice = costprice;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点人
	 */
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点人
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  盘点人姓名
	 */
	public java.lang.String getCrtusername(){
		return this.crtusername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  盘点人姓名
	 */
	public void setCrtusername(java.lang.String crtusername){
		this.crtusername = crtusername;
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

    public java.lang.String getStockkey()
    {
        return stockkey;
    }

    public void setStockkey(java.lang.String stockkey)
    {
        this.stockkey = stockkey;
    }

    public java.lang.String getDepotid()
    {
        return depotid;
    }

    public void setDepotid(java.lang.String depotid)
    {
        this.depotid = depotid;
    }
}
