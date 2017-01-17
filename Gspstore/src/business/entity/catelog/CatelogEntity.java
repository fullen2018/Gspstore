package business.entity.catelog;

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
 * @Description: 药品类别信息
 * @author zhangdaihao
 * @date 2013-05-13 16:40:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_catelog", schema = "")
@SuppressWarnings("serial")
public class CatelogEntity implements java.io.Serializable {
	/**catelogcode*/
	private java.lang.String catelogcode;
	/**cname*/
	private java.lang.String cname;
	/**catelogmeno*/
	private java.lang.String catelogmeno;
	/**ciconcls*/
	private java.lang.String ciconcls;
	/**cseq*/
	private java.lang.Integer cseq;
	
	private CatelogEntity catelogEntity;
	private Set<CatelogEntity> catelogEntitys = new HashSet<CatelogEntity>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public CatelogEntity getCatelogEntity() {
		return catelogEntity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catelogEntity")
	public Set<CatelogEntity> getCatelogEntitys() {
		return catelogEntitys;
	}

	public void setCatelogEntity(CatelogEntity catelogEntity) {
		this.catelogEntity = catelogEntity;
	}

	public void setCatelogEntitys(Set<CatelogEntity> catelogEntitys) {
		this.catelogEntitys = catelogEntitys;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catelogcode
	 */
	@Id
	@Column(name = "catelogcode",  nullable = false, length = 36)
	public java.lang.String getCatelogcode(){
		return this.catelogcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catelogcode
	 */
	public void setCatelogcode(java.lang.String catelogcode){
		this.catelogcode = catelogcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cname
	 */
	@Column(name ="CNAME")
	public java.lang.String getCname(){
		return this.cname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cname
	 */
	public void setCname(java.lang.String cname){
		this.cname = cname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  catelogmeno
	 */
	@Column(name ="CATELOGMENO")
	public java.lang.String getCatelogmeno(){
		return this.catelogmeno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  catelogmeno
	 */
	public void setCatelogmeno(java.lang.String catelogmeno){
		this.catelogmeno = catelogmeno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ciconcls
	 */
	@Column(name ="CICONCLS")
	public java.lang.String getCiconcls(){
		return this.ciconcls;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ciconcls
	 */
	public void setCiconcls(java.lang.String ciconcls){
		this.ciconcls = ciconcls;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  cseq
	 */
	@Column(name ="CSEQ")
	public java.lang.Integer getCseq(){
		return this.cseq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  cseq
	 */
	public void setCseq(java.lang.Integer cseq){
		this.cseq = cseq;
	}
}
