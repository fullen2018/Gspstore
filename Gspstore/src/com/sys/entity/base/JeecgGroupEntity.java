package com.sys.entity.base;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**   
 * @Title: Entity
 * @Description: 组织机构
 * @author zhangdaihao
 * @date 2013-01-27 18:36:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jeecg_group")
@SuppressWarnings("serial")
public class JeecgGroupEntity implements java.io.Serializable {
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
	
	private JeecgGroupEntity jeecgGroupEntity;
	private Set<JeecgGroupEntity> jeecgGroupEntitys = new HashSet<JeecgGroupEntity>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public JeecgGroupEntity getJeecgGroupEntity() {
		return jeecgGroupEntity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jeecgGroupEntity")
	public Set<JeecgGroupEntity> getJeecgGroupEntitys() {
		return jeecgGroupEntitys;
	}

	public void setJeecgGroupEntity(JeecgGroupEntity jeecgGroupEntity) {
		this.jeecgGroupEntity = jeecgGroupEntity;
	}

	public void setJeecgGroupEntitys(Set<JeecgGroupEntity> jeecgGroupEntitys) {
		this.jeecgGroupEntitys = jeecgGroupEntitys;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  
	 */
	@Id
	@Column(name = "obid",  nullable = false, length = 36)
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
	@Column(name ="CDESC")
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
	@Column(name ="CNAME")
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
	@Column(name ="CREATE_DT")
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
	@Column(name ="CRTUSER")
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
	@Column(name ="CRTUSER_NAME")
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
	@Column(name ="DEL_DT")
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
	@Column(name ="DELFLAG")
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
	@Column(name ="MODIFIER")
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
	@Column(name ="MODIFIER_NAME")
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
	@Column(name ="MODIFY_DT")
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
}
