package business.entity.accounter;

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
 * @Description: 业务员信息
 * @author zhangdaihao
 * @date 2013-05-13 09:26:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_accounter", schema = "")
@SuppressWarnings("serial")
public class AccounterEntity implements java.io.Serializable {
	/**业务员ID*/
	private java.lang.String accountid;
	/**业务员姓名*/
	private java.lang.String accountname;
	/**性别*/
	private java.lang.String sex;
	/**年龄*/
	private BigDecimal age;
	/**出生日期*/
	private java.util.Date birthday;
	/**联系电话*/
	private java.lang.String telephone;
	/**所属片区*/
	private java.lang.String area;
	/**入职日期*/
	private java.util.Date enterdate;
	/**家庭地址*/
	private java.lang.String address;
	/**提成比例*/
	private BigDecimal rate;
	/**是否在职*/
	private java.lang.Integer isactive;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**序号*/
	private java.lang.Integer ordernum;
	/**出生日期 开始时间 */
	private java.util.Date beginBirthday;
	/**出生日期 结束时间*/
	private java.util.Date endBirthday;
	/**入职日期 开始时间 */
	private java.util.Date beginEnterdate;
	/**入职日期 结束时间*/
	private java.util.Date endEnterdate;
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
	 *@return: java.lang.String  业务员ID
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	@Column(name ="ACCOUNTID",nullable=false,length=10)
	public java.lang.String getAccountid(){
		return this.accountid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员ID
	 */
	public void setAccountid(java.lang.String accountid){
		this.accountid = accountid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员姓名
	 */
	@Column(name ="ACCOUNTNAME",nullable=false,length=20)
	public java.lang.String getAccountname(){
		return this.accountname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员姓名
	 */
	public void setAccountname(java.lang.String accountname){
		this.accountname = accountname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=false,length=2)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  年龄
	 */
	@Column(name ="AGE",nullable=false,precision=2,scale=0)
	public BigDecimal getAge(){
		return this.age;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  年龄
	 */
	public void setAge(BigDecimal age){
		this.age = age;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出生日期
	 */
	@Column(name ="BIRTHDAY",nullable=false)
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出生日期
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="TELEPHONE",nullable=false,length=15)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属片区
	 */
	@Column(name ="AREA",nullable=false,length=5)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属片区
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入职日期
	 */
	@Column(name ="ENTERDATE",nullable=false)
	public java.util.Date getEnterdate(){
		return this.enterdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入职日期
	 */
	public void setEnterdate(java.util.Date enterdate){
		this.enterdate = enterdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家庭地址
	 */
	@Column(name ="ADDRESS",nullable=false,length=80)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家庭地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  提成比例
	 */
	@Column(name ="RATE",nullable=false,precision=5,scale=2)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否在职
	 */
	@Column(name ="ISACTIVE",nullable=false,precision=3,scale=0)
	public java.lang.Integer getIsactive(){
		return this.isactive;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否在职
	 */
	public void setIsactive(java.lang.Integer isactive){
		this.isactive = isactive;
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
	
	@Transient
	public java.util.Date getBeginBirthday(){
		return this.beginBirthday;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  出生日期开始时间
	 */
	public void setBeginBirthday(java.util.Date beginBirthday){
		this.beginBirthday = beginBirthday;
	}
	
	@Transient
	public java.util.Date getEndBirthday(){
		return this.endBirthday;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  出生日期结束时间
	 */
	public void setEndBirthday(java.util.Date endBirthday){
		this.endBirthday = endBirthday;
	}
	@Transient
	public java.util.Date getBeginEnterdate(){
		return this.beginEnterdate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  入职日期开始时间
	 */
	public void setBeginEnterdate(java.util.Date beginEnterdate){
		this.beginEnterdate = beginEnterdate;
	}
	
	@Transient
	public java.util.Date getEndEnterdate(){
		return this.endEnterdate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  入职日期结束时间
	 */
	public void setEndEnterdate(java.util.Date endEnterdate){
		this.endEnterdate = endEnterdate;
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
