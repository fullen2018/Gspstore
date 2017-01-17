package business.page.article;

import java.math.BigDecimal;
import java.util.Date;
import com.core.base.BasePage;import com.sys.page.base.DictParamPage;
/**   
 * @Title: Page
 * @Description: 品种信息
 * @author zhangdaihao
 * @date 2013-05-12 15:00:01
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class ArticlePage  extends BasePage implements java.io.Serializable {
	/**品种ID号*/
	private java.lang.String articleid;
	/**品种名称*/
	private java.lang.String articlename;
	/**品种别名*/
	private java.lang.String articlealias;
	/**品种规格*/
	private java.lang.String articlespec;
	/**生产厂家*/
	private java.lang.String factory;
	/**条码*/
	private java.lang.String barcode;
	/**批准文号*/
	private java.lang.String fileno;
	/**批发价*/
	private BigDecimal pfprice;
	/**统一零售价价*/
    private BigDecimal retailprice;
	/**最新进价*/
	private BigDecimal lastinprice;
	/**单位*/
	private java.lang.String unit;
	/**单位*/
	private DictParamPage unitdic;
	/**产品类别*/
    private java.lang.String catelog;
	/**是否提成*/
	private java.lang.Integer israte;
	/**提成比例*/
	private BigDecimal rate;
	/**备注*/
	private java.lang.String meno;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	/**序号*/
    private java.lang.Integer ordernum;
	
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
	 *@return: java.lang.String  品种名称
	 */
	public java.lang.String getArticlename(){
		return this.articlename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种名称
	 */
	public void setArticlename(java.lang.String articlename){
		this.articlename = articlename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种别名
	 */
	public java.lang.String getArticlealias(){
		return this.articlealias;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种别名
	 */
	public void setArticlealias(java.lang.String articlealias){
		this.articlealias = articlealias;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种规格
	 */
	public java.lang.String getArticlespec(){
		return this.articlespec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种规格
	 */
	public void setArticlespec(java.lang.String articlespec){
		this.articlespec = articlespec;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产厂家
	 */
	public java.lang.String getFactory(){
		return this.factory;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产厂家
	 */
	public void setFactory(java.lang.String factory){
		this.factory = factory;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条码
	 */
	public java.lang.String getBarcode(){
		return this.barcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  条码
	 */
	public void setBarcode(java.lang.String barcode){
		this.barcode = barcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批准文号
	 */
	public java.lang.String getFileno(){
		return this.fileno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准文号
	 */
	public void setFileno(java.lang.String fileno){
		this.fileno = fileno;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  批发价
	 */
	public BigDecimal getPfprice(){
		return this.pfprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  批发价
	 */
	public void setPfprice(BigDecimal pfprice){
		this.pfprice = pfprice;
	}
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  最新进价
	 */
	public BigDecimal getLastinprice(){
		return this.lastinprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  最新进价
	 */
	public void setLastinprice(BigDecimal lastinprice){
		this.lastinprice = lastinprice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
	public java.lang.String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否提成
	 */
	public java.lang.Integer getIsrate(){
		return this.israte;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否提成
	 */
	public void setIsrate(java.lang.Integer israte){
		this.israte = israte;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  提成比例
	 */
	public BigDecimal getRate(){
		return this.rate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  提成比例
	 */
	public void setRate(BigDecimal rate){
		this.rate = rate;
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

    public java.lang.String getCatelog()
    {
        return catelog;
    }

    public void setCatelog(java.lang.String catelog)
    {
        this.catelog = catelog;
    }

    public BigDecimal getRetailprice()
    {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice)
    {
        this.retailprice = retailprice;
    }

    public DictParamPage getUnitdic()
    {
        return unitdic;
    }

    public void setUnitdic(DictParamPage unitdic)
    {
        this.unitdic = unitdic;
    }

   
}
