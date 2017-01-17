package business.page.fee;

import java.math.BigDecimal;
import java.util.Date;

import business.page.subject.SubjectPage;

import com.core.base.BasePage;/**   
 * @Title: Page
 * @Description: 财务费用信息
 * @author zhangdaihao
 * @date 2013-05-21 20:46:41
 * @version V1.0   
 *
 */
 
 @SuppressWarnings("serial")
public class FeePage  extends BasePage implements java.io.Serializable {
	/**费用记录ID*/
	private java.lang.String feeid;
	/**费用科目*/
	private java.lang.String subjectid;
	private SubjectPage subjectpage;
	/**费用金额*/
	private BigDecimal amount;
	/**支付形式*/
	private java.lang.String feetype;
	/**费用日期*/
	private java.util.Date feedate;
	/**经手人*/
	private java.lang.String feeeman;
	/**备注*/
	private java.lang.String meno;
	/**修改人ID*/
	private java.lang.String modifier;
	/**修改人姓名*/
	private java.lang.String modifiername;
	/**修改日期*/
	private java.util.Date modifydate;
	/**创建ID*/
	private java.lang.String crtuser;
	/**创建人姓名*/
	private java.lang.String crtusername;
	/**创建日期*/
	private java.util.Date createdate;
	/**序号*/
	private java.lang.Integer ordernum;
	/**费用日期 开始时间 */
	private java.util.Date beginFeedate;
	/**费用日期 结束时间*/
	private java.util.Date endFeedate;
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
	 *@return: java.lang.String  费用记录ID
	 */
	public java.lang.String getFeeid(){
		return this.feeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用记录ID
	 */
	public void setFeeid(java.lang.String feeid){
		this.feeid = feeid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用科目
	 */
	public java.lang.String getSubjectid(){
		return this.subjectid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用科目
	 */
	public void setSubjectid(java.lang.String subjectid){
		this.subjectid = subjectid;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  费用金额
	 */
	public BigDecimal getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  费用金额
	 */
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付形式
	 */
	public java.lang.String getFeetype(){
		return this.feetype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付形式
	 */
	public void setFeetype(java.lang.String feetype){
		this.feetype = feetype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  费用日期
	 */
	public java.util.Date getFeedate(){
		return this.feedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  费用日期
	 */
	public void setFeedate(java.util.Date feedate){
		this.feedate = feedate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经手人
	 */
	public java.lang.String getFeeeman(){
		return this.feeeman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经手人
	 */
	public void setFeeeman(java.lang.String feeeman){
		this.feeeman = feeeman;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人ID
	 */
	public java.lang.String getModifier(){
		return this.modifier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人ID
	 */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人姓名
	 */
	public java.lang.String getModifiername(){
		return this.modifiername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人姓名
	 */
	public void setModifiername(java.lang.String modifiername){
		this.modifiername = modifiername;
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
	
	public java.util.Date getBeginFeedate(){
		return this.beginFeedate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  费用日期开始时间
	 */
	public void setBeginFeedate(java.util.Date beginFeedate){
		this.beginFeedate = beginFeedate;
	}
	
	public java.util.Date getEndFeedate(){
		return this.endFeedate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  费用日期结束时间
	 */
	public void setEndFeedate(java.util.Date endFeedate){
		this.endFeedate = endFeedate;
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

    public SubjectPage getSubjectpage()
    {
        return subjectpage;
    }

    public void setSubjectpage(SubjectPage subjectpage)
    {
        this.subjectpage = subjectpage;
    }
}
