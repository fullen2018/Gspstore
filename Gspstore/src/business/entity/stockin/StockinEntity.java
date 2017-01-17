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
 * @Description: 入库主表
 * @author zhangdaihao
 * @date 2013-05-13 23:19:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_rk_stockin", schema = "")
@SuppressWarnings("serial")
public class StockinEntity implements java.io.Serializable {
	/**入库单号*/
	private java.lang.String stockinbillno;
	/**入库日期*/
	private java.util.Date stockindate;
	/**仓库*/
	private java.lang.String depotid;
	/**供应商*/
	private java.lang.String supplierid;
	/**备注*/
    private java.lang.String meno;
    /***/
    private java.lang.String stockbacknoitems;
	/**是否付款*/
	private java.lang.Integer ispayed;
	/**付款单号*/
	private java.lang.String paybillno;
	/**创建日期*/
	private java.util.Date createdate;
	/**修改日期*/
	private java.util.Date modifydate;
	/**总金额*/
	private BigDecimal amount;
	/**序号*/
	private java.lang.Integer ordernum;
	
	/**创建人*/
	private java.lang.String crtuser;
	/**创建人*/
	private java.lang.String crtusername;
	/**修改人*/
	private java.lang.String modifier;
	/**修改人*/
	private java.lang.String modifiername;
	
	/**入库日期 开始时间 */
	private java.util.Date beginStockindate;
	/**入库日期 结束时间*/
	private java.util.Date endStockindate;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入库单号
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入库日期
	 */
	@Column(name ="STOCKINDATE",nullable=false)
	public java.util.Date getStockindate(){
		return this.stockindate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入库日期
	 */
	public void setStockindate(java.util.Date stockindate){
		this.stockindate = stockindate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	@Column(name ="DEPOTID",nullable=false,length=5)
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
	 *@return: java.lang.String  供应商
	 */
	@Column(name ="SUPPLIERID",nullable=false,length=5)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否付款
	 */
	@Column(name ="ISPAYED",nullable=false,precision=3,scale=0)
	public java.lang.Integer getIspayed(){
		return this.ispayed;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否付款
	 */
	public void setIspayed(java.lang.Integer ispayed){
		this.ispayed = ispayed;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款单号
	 */
	@Column(name ="PAYBILLNO",nullable=true,length=20)
	public java.lang.String getPaybillno(){
		return this.paybillno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款单号
	 */
	public void setPaybillno(java.lang.String paybillno){
		this.paybillno = paybillno;
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
	
	@Transient
	public java.util.Date getBeginStockindate(){
		return this.beginStockindate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  入库日期开始时间
	 */
	public void setBeginStockindate(java.util.Date beginStockindate){
		this.beginStockindate = beginStockindate;
	}
	
	@Transient
	public java.util.Date getEndStockindate(){
		return this.endStockindate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  入库日期结束时间
	 */
	public void setEndStockindate(java.util.Date endStockindate){
		this.endStockindate = endStockindate;
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

	@Column(name ="CRTUSER",nullable=true,length=50)
    public java.lang.String getCrtuser()
    {
        return crtuser;
    }

    public void setCrtuser(java.lang.String crtuser)
    {
        this.crtuser = crtuser;
    }

    @Column(name ="CRTUSERNAME",nullable=true,length=40)
    public java.lang.String getCrtusername()
    {
        return crtusername;
    }

    public void setCrtusername(java.lang.String crtusername)
    {
        this.crtusername = crtusername;
    }

    @Column(name="MODIFIER",nullable=true,length=10)
    public java.lang.String getModifier()
    {
        return modifier;
    }

    public void setModifier(java.lang.String modifier)
    {
        this.modifier = modifier;
    }

    @Column(name="MODIFIERNAME",nullable=true,length=40)
    public java.lang.String getModifiername()
    {
        return modifiername;
    }

    public void setModifiername(java.lang.String modifiername)
    {
        this.modifiername = modifiername;
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
     *方法: 取得java.lang.String
     *@return: java.lang.String  备注
     */
    @Column(name ="STOCKBACKNOITEMS",nullable=true,length=100)
    public java.lang.String getStockbacknoitems()
    {
        return stockbacknoitems;
    }

    public void setStockbacknoitems(java.lang.String stockbacknoitems)
    {
        this.stockbacknoitems = stockbacknoitems;
    }
}
