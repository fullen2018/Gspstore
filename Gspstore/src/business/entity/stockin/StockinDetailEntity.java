package business.entity.stockin;

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
 * @Description: 入库明细表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_rk_stockin_detail", schema = "")
@SuppressWarnings("serial")
public class StockinDetailEntity implements java.io.Serializable {
	/**入库明细单ID*/
	private java.lang.String stockindetailno;
	/**入库单号*/
	private java.lang.String stockinbillno;
	/**品种ID号*/
	private java.lang.String articleid;
	/**批号*/
	private java.lang.String serial;
	/**有效期*/
	private java.util.Date expiredate;
	/**数量*/
	private BigDecimal num;
	/**数量*/
	private BigDecimal backnum;
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
	 *@return: java.lang.String  入库明细单ID
	 */
	@Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="STOCKINDETAILNO",nullable=false,length=50)
	public java.lang.String getStockindetailno(){
		return this.stockindetailno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库明细单ID
	 */
	public void setStockindetailno(java.lang.String stockindetailno){
		this.stockindetailno = stockindetailno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库单号
	 */
	
	
	@Column(name ="STOCKINBILLNO",nullable=false,length=20)
	public java.lang.String getStockinbillno(){
		return this.stockinbillno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入库单号
	 */
	public void setStockinbillno(java.lang.String stockinbillno){
		this.stockinbillno = stockinbillno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种ID号
	 */
	@Column(name ="ARTICLEID",nullable=true,length=10)
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
	@Column(name ="SERIAL",nullable=false,length=20)
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
	@Column(name ="EXPIREDATE",nullable=false)
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
	@Column(name ="NUM",nullable=false,precision=5,scale=0)
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
	@Column(name ="PRICE",nullable=false,precision=8,scale=2)
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
	@Column(name ="AMOUNT",nullable=false,precision=8,scale=2)
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
	
	@Transient
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
	
	@Transient
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
	/**
     *方法: 取得BigDecimal
     *@return: BigDecimal  数量
     */
    @Column(name ="BACKNUM",nullable=false,precision=5,scale=0)
    public BigDecimal getBacknum()
    {
        return backnum;
    }

    public void setBacknum(BigDecimal backnum)
    {
        this.backnum = backnum;
    }
}
