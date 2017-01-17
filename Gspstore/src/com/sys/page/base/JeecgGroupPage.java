package com.sys.page.base;

import java.math.BigDecimal;
import java.util.Date;
import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 组织机构
 * @author zhangdaihao
 * @date 2013-01-27 18:36:16
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class JeecgGroupPage  extends BasePage implements java.io.Serializable {
	// 自定义属性
	private String id;
	private String state = "open";// 是否展开(open,closed)
	private String cpname;
	private String iconCls;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	/***/
	private java.lang.String obid;
	/***/
	private java.lang.String cdesc;
	/***/
	private java.lang.String cname;
	/***/
	private java.util.Date createDt;
	/***/
	private java.lang.String crtuser;
	/***/
	private java.lang.String crtuserName;
	/***/
	private java.util.Date delDt;
	/***/
	private java.lang.Integer delflag;
	/***/
	private java.lang.String modifier;
	/***/
	private java.lang.String modifierName;
	/***/
	private java.util.Date modifyDt;
	/***/
	private java.lang.String cpid;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getObid(){
		return this.obid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setObid(java.lang.String obid){
		this.obid = obid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getCdesc(){
		return this.cdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setCdesc(java.lang.String cdesc){
		this.cdesc = cdesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getCname(){
		return this.cname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setCname(java.lang.String cname){
		this.cname = cname;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  
	 */
	public java.util.Date getCreateDt(){
		return this.createDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  
	 */
	public void setCreateDt(java.util.Date createDt){
		this.createDt = createDt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getCrtuserName(){
		return this.crtuserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setCrtuserName(java.lang.String crtuserName){
		this.crtuserName = crtuserName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  
	 */
	public java.util.Date getDelDt(){
		return this.delDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  
	 */
	public void setDelDt(java.util.Date delDt){
		this.delDt = delDt;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  
	 */
	public java.lang.Integer getDelflag(){
		return this.delflag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  
	 */
	public void setDelflag(java.lang.Integer delflag){
		this.delflag = delflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getModifier(){
		return this.modifier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	public java.lang.String getModifierName(){
		return this.modifierName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  
	 */
	public void setModifierName(java.lang.String modifierName){
		this.modifierName = modifierName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  
	 */
	public java.util.Date getModifyDt(){
		return this.modifyDt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  
	 */
	public void setModifyDt(java.util.Date modifyDt){
		this.modifyDt = modifyDt;
	}

	public java.lang.String getCpid() {
		return cpid;
	}

	public void setCpid(java.lang.String cpid) {
		this.cpid = cpid;
	}
}
