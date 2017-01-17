package business.entity.saleback;

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
 * @Description: 销售退货明细
 * @author zhangdaihao
 * @date 2013-05-20 13:40:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xs_sale_back_detail", schema = "")
@SuppressWarnings("serial")
public class SaleBackDetailEntity implements java.io.Serializable {
	/**退货明细主键*/
	private java.lang.String salebackbilldetailno;
	/**退货单号*/
	private java.lang.String salebackno;
	/**品种*/
	private java.lang.String articleid;
	/**批号*/
	private java.lang.String serial;
	/**数量*/
	private BigDecimal num;
	/**退货价*/
	private BigDecimal saleprice;
	/**成本价*/
	private BigDecimal price;
	/**金额*/
	private BigDecimal amount;
	/**仓库*/
	private java.lang.String depotid;
	/**防伪号*/
	private java.lang.String secretno;
	/**序号*/
	private java.lang.Integer ordernum;
	/**库存序号*/
	private java.lang.String stockkey;
	/**销售明细主键*/
    private java.lang.String salebilldetailno;
	/**有效期*/
	private java.util.Date expiredate;
	/**有效期 开始时间 */
	private java.util.Date beginExpiredate;
	/**有效期 结束时间*/
	private java.util.Date endExpiredate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货明细主键
	 */
	@Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="SALEBACKBILLDETAILNO",nullable=false,length=50)
	public java.lang.String getSalebackbilldetailno(){
		return this.salebackbilldetailno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货明细主键
	 */
	public void setSalebackbilldetailno(java.lang.String salebackbilldetailno){
		this.salebackbilldetailno = salebackbilldetailno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货单号
	 */
	
	
	@Column(name ="SALEBACKNO",nullable=false,length=20)
	public java.lang.String getSalebackno(){
		return this.salebackno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货单号
	 */
	public void setSalebackno(java.lang.String salebackno){
		this.salebackno = salebackno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种
	 */
	@Column(name ="ARTICLEID",nullable=false,length=20)
	public java.lang.String getArticleid(){
		return this.articleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种
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
	 *@return: BigDecimal  退货价
	 */
	@Column(name ="SALEPRICE",nullable=false,precision=8,scale=2)
	public BigDecimal getSaleprice(){
		return this.saleprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  退货价
	 */
	public void setSaleprice(BigDecimal saleprice){
		this.saleprice = saleprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  成本价
	 */
	@Column(name ="PRICE",nullable=false,precision=8,scale=2)
	public BigDecimal getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  成本价
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="DEPOTID",nullable=false,length=20)
	public java.lang.String getDepotid(){
		return this.depotid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setDepotid(java.lang.String depotid){
		this.depotid = depotid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  防伪号
	 */
	@Column(name ="SECRETNO",nullable=false,length=50)
	public java.lang.String getSecretno(){
		return this.secretno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  防伪号
	 */
	public void setSecretno(java.lang.String secretno){
		this.secretno = secretno;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */
	@Column(name ="ORDERNUM",nullable=true,precision=5,scale=0)
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
	 *@return: java.lang.String  库存序号
	 */
	@Column(name ="STOCKKEY",nullable=true,length=50)
	public java.lang.String getStockkey(){
		return this.stockkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存序号
	 */
	public void setStockkey(java.lang.String stockkey){
		this.stockkey = stockkey;
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
	@Column(name ="SALEBILLDETAILNO",nullable=true,length=50)
    public java.lang.String getSalebilldetailno()
    {
        return salebilldetailno;
    }

    public void setSalebilldetailno(java.lang.String salebilldetailno)
    {
        this.salebilldetailno = salebilldetailno;
    }
	
}
