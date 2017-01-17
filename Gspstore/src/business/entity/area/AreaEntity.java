package business.entity.area;

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
 * @Description: 片区信息
 * @author zhangdaihao
 * @date 2013-05-12 18:15:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_area", schema = "")
@SuppressWarnings("serial")
public class AreaEntity implements java.io.Serializable {
	/**片区ID*/
	private java.lang.String areacode;
	/**片区名称*/
	private java.lang.String areaname;
	/**片区经理*/
	private java.lang.String manage;
	/**提成比例*/
	private BigDecimal rate;
	/**备注*/
	private java.lang.String meno;
	/**创建日期*/
	private java.util.Date createdate;
	/**修改日期*/
	private java.util.Date modifydate;
	/**序号*/
	private java.lang.Integer ordernum;
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
	 *@return: java.lang.String  片区ID
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name ="AREACODE",nullable=false,length=20)
	public java.lang.String getAreacode(){
		return this.areacode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  片区ID
	 */
	public void setAreacode(java.lang.String areacode){
		this.areacode = areacode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  片区名称
	 */
	@Column(name ="AREANAME",nullable=false,length=50)
	public java.lang.String getAreaname(){
		return this.areaname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  片区名称
	 */
	public void setAreaname(java.lang.String areaname){
		this.areaname = areaname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  片区经理
	 */
	@Column(name ="MANAGE",nullable=false,length=10)
	public java.lang.String getManage(){
		return this.manage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  片区经理
	 */
	public void setManage(java.lang.String manage){
		this.manage = manage;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  提成比例
	 */
	@Column(name ="RATE",nullable=false,precision=5,scale=3)
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

	
	
}
