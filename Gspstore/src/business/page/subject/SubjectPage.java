package business.page.subject;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 财务科目信息
 * @author zhangdaihao
 * @date 2013-05-21 20:45:45
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class SubjectPage  extends BasePage implements java.io.Serializable {
	/**科目ID*/
	private java.lang.String subjectid;
	/**科目名称*/
	private java.lang.String subjectname;
	/**科目类别*/
	private java.lang.String subjecttype;
	/**备注*/
	private java.lang.String meno;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建日期*/
	private java.util.Date createdate;
	/**创建ID*/
	private java.lang.String crtuser;
	/**创建人姓名*/
	private java.lang.String crtusername;
	/**修改日期 开始时间 */
	private java.util.Date beginModifydate;
	/**修改日期 结束时间*/
	private java.util.Date endModifydate;
	/**创建日期 开始时间 */
	private java.util.Date beginCreatedate;
	/**创建日期 结束时间*/
	private java.util.Date endCreatedate;
	/**序号*/
    private java.lang.Integer ordernum;
	/**
     *方法: 取得java.lang.Integer
     *@return: java.lang.Integer  序号
     */
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目ID
	 */
	public java.lang.String getSubjectid(){
		return this.subjectid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目ID
	 */
	public void setSubjectid(java.lang.String subjectid){
		this.subjectid = subjectid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目名称
	 */
	public java.lang.String getSubjectname(){
		return this.subjectname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目名称
	 */
	public void setSubjectname(java.lang.String subjectname){
		this.subjectname = subjectname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目类别
	 */
	public java.lang.String getSubjecttype(){
		return this.subjecttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目类别
	 */
	public void setSubjecttype(java.lang.String subjecttype){
		this.subjecttype = subjecttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
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
	 *@return: java.util.Date  修改日期
	 */
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建ID
	 */
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建ID
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人姓名
	 */
	public java.lang.String getCrtusername(){
		return this.crtusername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人姓名
	 */
	public void setCrtusername(java.lang.String crtusername){
		this.crtusername = crtusername;
	}
	
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
