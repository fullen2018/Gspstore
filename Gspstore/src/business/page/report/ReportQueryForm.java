package business.page.report;

import java.math.BigDecimal;

public class ReportQueryForm
{
    /**供应商ID*/
    private java.lang.String supplierid;
    /**销售单号*/
    private java.lang.String salebillno;
    /**销售客户*/
    private java.lang.String customerid;
    /**销售客户*/
    private java.lang.String articleid;
    private java.lang.String reporttype;
    private java.lang.String ispayed;
    private java.lang.String crtuser;
    private java.lang.String serial;
    private java.lang.String depotid;
    private BigDecimal num;
    
    /**科目ID*/
    private java.lang.String subjectid;
    /**科目类别*/
    private java.lang.String subjecttype;
    /**支付形式*/
    private java.lang.String feetype;
    
    /**开始日期*/
    private java.util.Date expiredate;
    /**开始日期*/
    private java.util.Date begindate;
    /**开始日期*/
    private java.util.Date enddate;
    /**业务员*/
    private java.lang.String accountid;
    public java.lang.String getSupplierid()
    {
        return supplierid;
    }
    public void setSupplierid(java.lang.String supplierid)
    {
        this.supplierid = supplierid;
    }
    public java.lang.String getSalebillno()
    {
        return salebillno;
    }
    public void setSalebillno(java.lang.String salebillno)
    {
        this.salebillno = salebillno;
    }
    public java.lang.String getCustomerid()
    {
        return customerid;
    }
    public void setCustomerid(java.lang.String customerid)
    {
        this.customerid = customerid;
    }
    public java.util.Date getBegindate()
    {
        return begindate;
    }
    public void setBegindate(java.util.Date begindate)
    {
        this.begindate = begindate;
    }
    public java.util.Date getEnddate()
    {
        return enddate;
    }
    public void setEnddate(java.util.Date enddate)
    {
        this.enddate = enddate;
    }
    public java.lang.String getAccountid()
    {
        return accountid;
    }
    public void setAccountid(java.lang.String accountid)
    {
        this.accountid = accountid;
    }
    public java.lang.String getArticleid()
    {
        return articleid;
    }
    public void setArticleid(java.lang.String articleid)
    {
        this.articleid = articleid;
    }
    public java.lang.String getReporttype()
    {
        return reporttype;
    }
    public void setReporttype(java.lang.String reporttype)
    {
        this.reporttype = reporttype;
    }
    public java.lang.String getIspayed()
    {
        return ispayed;
    }
    public void setIspayed(java.lang.String ispayed)
    {
        this.ispayed = ispayed;
    }
    public java.lang.String getCrtuser()
    {
        return crtuser;
    }
    public void setCrtuser(java.lang.String crtuser)
    {
        this.crtuser = crtuser;
    }
    public java.lang.String getSerial()
    {
        return serial;
    }
    public void setSerial(java.lang.String serial)
    {
        this.serial = serial;
    }
    public java.lang.String getDepotid()
    {
        return depotid;
    }
    public void setDepotid(java.lang.String depotid)
    {
        this.depotid = depotid;
    }
    public BigDecimal getNum()
    {
        return num;
    }
    public void setNum(BigDecimal num)
    {
        this.num = num;
    }
    public java.util.Date getExpiredate()
    {
        return expiredate;
    }
    public void setExpiredate(java.util.Date expiredate)
    {
        this.expiredate = expiredate;
    }
    public java.lang.String getSubjectid()
    {
        return subjectid;
    }
    public void setSubjectid(java.lang.String subjectid)
    {
        this.subjectid = subjectid;
    }
    public java.lang.String getSubjecttype()
    {
        return subjecttype;
    }
    public void setSubjecttype(java.lang.String subjecttype)
    {
        this.subjecttype = subjecttype;
    }
    public java.lang.String getFeetype()
    {
        return feetype;
    }
    public void setFeetype(java.lang.String feetype)
    {
        this.feetype = feetype;
    }
}
