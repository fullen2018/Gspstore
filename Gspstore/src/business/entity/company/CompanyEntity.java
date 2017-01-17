package business.entity.company;

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
 * @Description: 公司信息
 * @author zhangdaihao
 * @date 2013-06-20 14:31:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_xx_company", schema = "")
@SuppressWarnings("serial")
public class CompanyEntity implements java.io.Serializable {
	/**公司序号*/
	private java.lang.String companyno;
	/**公司名称*/
	private java.lang.String companyname;
	/**电话*/
	private java.lang.String telephone;
	/**传真*/
	private java.lang.String fax;
	/**邮箱*/
	private java.lang.String email;
	/**地址*/
	private java.lang.String address;
	/**联系人*/
	private java.lang.String contactman;
	/**小票备注说明*/
    private java.lang.String billmeno;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司序号
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="COMPANYNO",nullable=false,length=50)
	public java.lang.String getCompanyno(){
		return this.companyno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司序号
	 */
	public void setCompanyno(java.lang.String companyno){
		this.companyno = companyno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */
	@Column(name ="COMPANYNAME",nullable=true,length=50)
	public java.lang.String getCompanyname(){
		return this.companyname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompanyname(java.lang.String companyname){
		this.companyname = companyname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="TELEPHONE",nullable=true,length=30)
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  传真
	 */
	@Column(name ="FAX",nullable=true,length=20)
	public java.lang.String getFax(){
		return this.fax;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  传真
	 */
	public void setFax(java.lang.String fax){
		this.fax = fax;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮箱
	 */
	@Column(name ="EMAIL",nullable=true,length=40)
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
	@Column(name ="ADDRESS",nullable=true,length=150)
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
	 *@return: java.lang.String  联系人
	 */
	@Column(name ="CONTACTMAN",nullable=true,length=50)
	public java.lang.String getContactman(){
		return this.contactman;
	}

	/**
     *方法: 设置java.lang.String
     *@param: java.lang.String  联系人
     */
	public void setContactman(java.lang.String contactman){
		this.contactman = contactman;
	}
	/**
     *方法: 取得java.lang.String
     *@return: java.lang.String  联系人
     */
    @Column(name ="BILLMENO",nullable=true,length=100)
    public java.lang.String getBillmeno()
    {
        return billmeno;
    }

    public void setBillmeno(java.lang.String billmeno)
    {
        this.billmeno = billmeno;
    }
	
	
}
