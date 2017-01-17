package business.entity.sale;

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
 * @Description: 销售单
 * @author zhangdaihao
 * @date 2013-05-19 10:15:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xs_sale", schema = "")
@SuppressWarnings("serial")
public class SaleEntity implements java.io.Serializable {
	/**销售单号*/
	private java.lang.String salebillno;
	/**销售客户*/
	private java.lang.String customerid;
	/**销售日期*/
	private java.util.Date saledate;
	/**业务员*/
	private java.lang.String accountid;
	/**提成比例*/
	private BigDecimal salerate;
	/**备注*/
	private java.lang.String meno;
	/**是否收款*/
	private java.lang.Integer isreceive;
	/**总金额*/
    private BigDecimal amount;
	/**操作人姓名*/
	private java.lang.String crtusername;
	/**操作人*/
	private java.lang.String crtuser;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**销售退货单列表*/
	private java.lang.String salebackbillitems;
	/**销售日期 开始时间 */
	private java.util.Date beginSaledate;
	/**销售日期 结束时间*/
	private java.util.Date endSaledate;
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
	 *@return: java.lang.String  销售单号
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name ="SALEBILLNO",nullable=false,length=20)
	public java.lang.String getSalebillno(){
		return this.salebillno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售单号
	 */
	public void setSalebillno(java.lang.String salebillno){
		this.salebillno = salebillno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售客户
	 */
	@Column(name ="CUSTOMERID",nullable=false,length=20)
	public java.lang.String getCustomerid(){
		return this.customerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售客户
	 */
	public void setCustomerid(java.lang.String customerid){
		this.customerid = customerid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  销售日期
	 */
	@Column(name ="SALEDATE",nullable=false)
	public java.util.Date getSaledate(){
		return this.saledate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  销售日期
	 */
	public void setSaledate(java.util.Date saledate){
		this.saledate = saledate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */
	@Column(name ="ACCOUNTID",nullable=false,length=20)
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
	 *@return: BigDecimal  提成比例
	 */
	@Column(name ="SALERATE",nullable=false,precision=5,scale=2)
	public BigDecimal getSalerate(){
		return this.salerate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  提成比例
	 */
	public void setSalerate(BigDecimal salerate){
		this.salerate = salerate;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否收款
	 */
	@Column(name ="ISRECEIVE",nullable=false,precision=3,scale=0)
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
	@Column(name ="CRTUSERNAME",nullable=false,length=50)
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
	@Column(name ="CRTUSER",nullable=false,length=50)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  销售退货单列表
	 */
	@Column(name ="SALEBACKBILLITEMS",nullable=true,length=100)
	public java.lang.String getSalebackbillitems(){
		return this.salebackbillitems;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  销售退货单列表
	 */
	public void setSalebackbillitems(java.lang.String salebackbillitems){
		this.salebackbillitems = salebackbillitems;
	}
	
	@Transient
	public java.util.Date getBeginSaledate(){
		return this.beginSaledate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  销售日期开始时间
	 */
	public void setBeginSaledate(java.util.Date beginSaledate){
		this.beginSaledate = beginSaledate;
	}
	
	@Transient
	public java.util.Date getEndSaledate(){
		return this.endSaledate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  销售日期结束时间
	 */
	public void setEndSaledate(java.util.Date endSaledate){
		this.endSaledate = endSaledate;
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
    /**
     *方法: 取得BigDecimal
     *@return: BigDecimal  总金额
     */
    @Column(name ="AMOUNT",nullable=false,precision=8,scale=2)
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
}
