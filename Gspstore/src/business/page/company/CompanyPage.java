package business.page.company;

import java.math.BigDecimal;
import java.util.Date;
import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 公司信息
 * @author zhangdaihao
 * @date 2013-06-20 14:31:52
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class CompanyPage  extends BasePage implements java.io.Serializable {
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

    public java.lang.String getBillmeno()
    {
        return billmeno;
    }

    public void setBillmeno(java.lang.String billmeno)
    {
        this.billmeno = billmeno;
    }
	
}
