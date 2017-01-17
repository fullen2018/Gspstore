package business.entity.article;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**   
 * @Title: Entity
 * @Description: 品种信息
 * @author zhangdaihao
 * @date 2013-05-12 15:00:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_article", schema = "")
@SuppressWarnings("serial")
public class ArticleEntity implements java.io.Serializable {
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
	/**序号*/
    private java.lang.Integer ordernum;
	/**最新进价*/
	private BigDecimal lastinprice;
	/**单位*/
	private java.lang.String unit;
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
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种ID号
	 */
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name ="ARTICLEID",nullable=false,length=29)
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
	@Column(name ="ARTICLENAME",nullable=false,length=100)
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
	@Column(name ="ARTICLEALIAS",nullable=false,length=50)
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
	@Column(name ="ARTICLESPEC",nullable=false,length=50)
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
	@Column(name ="FACTORY",nullable=false,length=80)
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
	@Column(name ="BARCODE",nullable=true,length=40)
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
	@Column(name ="FILENO",nullable=true,length=80)
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
	@Column(name ="PFPRICE",nullable=true,precision=8,scale=2)
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
	@Column(name ="LASTINPRICE",nullable=true,precision=8,scale=2)
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
	@Column(name ="UNIT",nullable=false,length=5)
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
	@Column(name ="ISRATE",nullable=true,precision=3,scale=0)
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
	@Column(name ="RATE",nullable=true,precision=5,scale=3)
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
	@Column(name ="MENO",nullable=true,length=100)
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
	@Column(name ="MODIFYDATE",nullable=false)
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
	@Column(name ="CREATEDATE",nullable=false)
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
	
	@Transient
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
	
	@Transient
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
	@Transient
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
	
	@Transient
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
    @Column(name ="ORDERNUM",nullable=false,precision=5,scale=0)
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
    @Column(name ="catelog",nullable=false,length=5)
    public java.lang.String getCatelog()
    {
        return catelog;
    }

    public void setCatelog(java.lang.String catelog)
    {
        this.catelog = catelog;
    }
    @Column(name ="RETAILPRICE",nullable=false,precision=8,scale=2)
    public BigDecimal getRetailprice()
    {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice)
    {
        this.retailprice = retailprice;
    }
}
