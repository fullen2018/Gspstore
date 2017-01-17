package business.entity.customer;

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
 * @Description: 客户信息
 * @author zhangdaihao
 * @date 2013-05-13 14:23:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_customer", schema = "")
@SuppressWarnings("serial")
public class CustomerEntity implements java.io.Serializable {
	/**客户ID*/
	private java.lang.String customerid;
	/**客户名称*/
	private java.lang.String customername;
	/**业务员*/
	private java.lang.String accountid;
	/**联系人*/
	private java.lang.String connectman;
	/**联系电话*/
	private java.lang.String phone;
	/**邮箱*/
	private java.lang.String email;
	/**地址*/
	private java.lang.String address;
	/**备注*/
	private java.lang.String meno;
	/**序号*/
	private java.lang.Integer ordernum;
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
	 *@return: java.lang.String  客户ID
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name ="CUSTOMERID",nullable=false,length=10)
	public java.lang.String getCustomerid(){
		return this.customerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户ID
	 */
	public void setCustomerid(java.lang.String customerid){
		this.customerid = customerid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */
	@Column(name ="CUSTOMERNAME",nullable=false,length=100)
	public java.lang.String getCustomername(){
		return this.customername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCustomername(java.lang.String customername){
		this.customername = customername;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */
	@Column(name ="CONNECTMAN",nullable=false,length=15)
	public java.lang.String getConnectman(){
		return this.connectman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setConnectman(java.lang.String connectman){
		this.connectman = connectman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="PHONE",nullable=false,length=15)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */
	@Column(name ="EMAIL",nullable=false,length=20)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮箱
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=false,length=100)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
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
	
}
