package sun.entity.jeecg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.math.BigDecimal;
/**
 *@类:JeecgOneRowEntity
 *@作者:zhangdaihao
 *@E-mail:zhangdaiscott@163.com
 *@日期:2011-12-31 14:29:05
 */

@Entity
@Table(name ="jeecg_one_demo")
@SuppressWarnings("serial")
public class JeecgOneRowEntity implements java.io.Serializable {

	/**主键*/
	private java.lang.String obid;

	/**用户名*/
	private java.lang.String name;

	/**年龄*/
	private java.lang.Integer age;

	/**工资*/
	private BigDecimal salary;

	/**生日*/
	private java.util.Date birthday;
	//add-begin author:gaoxingang for:添加时间查询字段  date：20130310
	/**生日 查询开始时间*/
	private java.util.Date beginBirthday;
	
	/**生日 查询结束时间*/
	private java.util.Date endBirthday;
	//add-end author:gaoxingang for:添加时间查询字段  date：20130310

	/**注册时间*/
	private java.util.Date registerDt;

	/**备注*/
	private java.lang.String content;

	/**创建人ID*/
	private java.lang.String crtuser;

	/**创建人*/
	private java.lang.String crtuserName;

	/**创建时间*/
	private java.util.Date createDt;
	//add-begin author:gaoxingang for:添加时间查询字段  date：20130311
	/**创建时间 开始时间*/
	private java.util.Date beginCreateDt;
	
	/**创建时间 结束时间*/
	private java.util.Date endCreateDt;

	/**注册时间 开始时间*/
	private java.util.Date beginRegisterDt;
	
	/**注册时间 结束时间*/
	private java.util.Date endRegisterDt;
	//add-end author:gaoxingang for:添加时间查询字段  date：20130311
	/**
	 *方法: 取得obid
	 *@return: java.lang.String  obid
	 */
	@Id
	@Column(name ="OBID", nullable = false, length = 36)
	public java.lang.String getObid(){
		return this.obid;
	}

	/**
	 *方法: 设置obid
	 *@param: java.lang.String  obid
	 */
	public void setObid(java.lang.String obid){
		this.obid = obid;
	}

	/**
	 *方法: 取得name
	 *@return: java.lang.String  name
	 */
	@Column(name ="name")
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置name
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}

	/**
	 *方法: 取得age
	 *@return: java.lang.Integer  age
	 */
	@Column(name ="age")
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置age
	 *@param: java.lang.Integer  age
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}

	/**
	 *方法: 取得salary
	 *@return: BigDecimal  salary
	 */
	@Column(name ="salary")
	public BigDecimal getSalary(){
		return this.salary;
	}

	/**
	 *方法: 设置salary
	 *@param: BigDecimal  salary
	 */
	public void setSalary(BigDecimal salary){
		this.salary = salary;
	}

	/**
	 *方法: 取得birthday
	 *@return: java.util.Date  birthday
	 */
	@Column(name ="birthday")
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置birthday
	 *@param: java.util.Date  birthday
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}

	/**
	 *方法: 取得registerDt
	 *@return: java.util.Date  registerDt
	 */
	@Column(name ="register_dt")
	public java.util.Date getRegisterDt(){
		return this.registerDt;
	}

	/**
	 *方法: 设置registerDt
	 *@param: java.util.Date  registerDt
	 */
	public void setRegisterDt(java.util.Date registerDt){
		this.registerDt = registerDt;
	}

	/**
	 *方法: 取得content
	 *@return: java.lang.String  content
	 */
	@Column(name ="content")
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置content
	 *@param: java.lang.String  content
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}

	/**
	 *方法: 取得crtuser
	 *@return: java.lang.String  crtuser
	 */
	@Column(name ="crtuser")
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置crtuser
	 *@param: java.lang.String  crtuser
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}

	/**
	 *方法: 取得crtuserName
	 *@return: java.lang.String  crtuserName
	 */
	@Column(name ="crtuser_name")
	public java.lang.String getCrtuserName(){
		return this.crtuserName;
	}

	/**
	 *方法: 设置crtuserName
	 *@param: java.lang.String  crtuserName
	 */
	public void setCrtuserName(java.lang.String crtuserName){
		this.crtuserName = crtuserName;
	}

	/**
	 *方法: 取得createDt
	 *@return: java.util.Date  createDt
	 */
	@Column(name ="create_dt")
	public java.util.Date getCreateDt(){
		return this.createDt;
	}

	/**
	 *方法: 设置createDt
	 *@param: java.util.Date  createDt
	 */
	public void setCreateDt(java.util.Date createDt){
		this.createDt = createDt;
	}
	
	//add-begin author:gaoxingang for:添加时间查询字段set get方法  date：20130310
	@Transient
	public java.util.Date getBeginBirthday() {
		return beginBirthday;
	}

	public void setBeginBirthday(java.util.Date beginBirthday) {
		this.beginBirthday = beginBirthday;
	}
	
	@Transient
	public java.util.Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(java.util.Date endBirthday) {
		this.endBirthday = endBirthday;
	}
	//add-end author:gaoxingang for:添加时间查询字段set get方法  date：20130310
	//add-begin author:gaoxingang for:添加时间查询字段set get方法  date：20130311
	@Transient
	public java.util.Date getBeginCreateDt() {
		return beginCreateDt;
	}

	public void setBeginCreateDt(java.util.Date beginCreateDt) {
		this.beginCreateDt = beginCreateDt;
	}
	
	@Transient
	public java.util.Date getEndCreateDt() {
		return endCreateDt;
	}

	public void setEndCreateDt(java.util.Date endCreateDt) {
		this.endCreateDt = endCreateDt;
	}
	
	@Transient
	public java.util.Date getBeginRegisterDt() {
		return beginRegisterDt;
	}

	public void setBeginRegisterDt(java.util.Date beginRegisterDt) {
		this.beginRegisterDt = beginRegisterDt;
	}
	@Transient
	public java.util.Date getEndRegisterDt() {
		return endRegisterDt;
	}

	public void setEndRegisterDt(java.util.Date endRegisterDt) {
		this.endRegisterDt = endRegisterDt;
	}
	//add-begin author:gaoxingang for:添加时间查询字段set get方法  date：20130311
}