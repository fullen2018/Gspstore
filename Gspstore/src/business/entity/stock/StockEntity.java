package business.entity.stock;

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
 * @Description: 库存信息
 * @author zhangdaihao
 * @date 2013-05-16 21:42:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_kc_stock", schema = "")
@SuppressWarnings("serial")
public class StockEntity implements java.io.Serializable {
	/**库存表主键*/
	private java.lang.String stockkey;
	/**仓库ID*/
	private java.lang.String depotid;
	/**品种ID*/
	private java.lang.String articleid;
	/**批号*/
	private java.lang.String serial;
	/**数量*/
	private BigDecimal num;
	/**成本价*/
	private BigDecimal costprice;
	/**有效期*/
	private java.util.Date expiredate;
	/**有效期 开始时间 */
	private java.util.Date beginExpiredate;
	/**有效期 结束时间*/
	private java.util.Date endExpiredate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  库存表主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="STOCKKEY",nullable=false,length=50)
	public java.lang.String getStockkey(){
		return this.stockkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存表主键
	 */
	public void setStockkey(java.lang.String stockkey){
		this.stockkey = stockkey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库ID
	 */
	@Column(name ="DEPOTID",nullable=false,length=5)
	public java.lang.String getDepotid(){
		return this.depotid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库ID
	 */
	public void setDepotid(java.lang.String depotid){
		this.depotid = depotid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品种ID
	 */
	@Column(name ="ARTICLEID",nullable=false,length=20)
	public java.lang.String getArticleid(){
		return this.articleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品种ID
	 */
	public void setArticleid(java.lang.String articleid){
		this.articleid = articleid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批号
	 */
	@Column(name ="SERIAL",nullable=false,length=20)
	public java.lang.String getSerial(){
		return this.serial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批号
	 */
	public void setSerial(java.lang.String serial){
		this.serial = serial;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  数量
	 */
	@Column(name ="NUM",nullable=false,precision=5,scale=0)
	public BigDecimal getNum(){
		return this.num;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  数量
	 */
	public void setNum(BigDecimal num){
		this.num = num;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  成本价
	 */
	@Column(name ="COSTPRICE",nullable=false,precision=8,scale=2)
	public BigDecimal getCostprice(){
		return this.costprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  成本价
	 */
	public void setCostprice(BigDecimal costprice){
		this.costprice = costprice;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期
	 */
	@Column(name ="EXPIREDATE",nullable=false)
	public java.util.Date getExpiredate(){
		return this.expiredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效期
	 */
	public void setExpiredate(java.util.Date expiredate){
		this.expiredate = expiredate;
	}
	
	@Transient
	public java.util.Date getBeginExpiredate(){
		return this.beginExpiredate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  有效期开始时间
	 */
	public void setBeginExpiredate(java.util.Date beginExpiredate){
		this.beginExpiredate = beginExpiredate;
	}
	
	@Transient
	public java.util.Date getEndExpiredate(){
		return this.endExpiredate;
	}
	
	/**
	 *方法: 设置java.util.Date 
	 *@param: java.util.Date  有效期结束时间
	 */
	public void setEndExpiredate(java.util.Date endExpiredate){
		this.endExpiredate = endExpiredate;
	}
	
}
