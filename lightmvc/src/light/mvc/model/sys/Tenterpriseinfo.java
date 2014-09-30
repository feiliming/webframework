package light.mvc.model.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "db_enterpriseinfo")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tenterpriseinfo implements java.io.Serializable{

	private static final long serialVersionUID = 1510991658580330434L;

	@Id
	private String code_id;
	private String code_cn;
	private String code_en;
	private String industry_id;
	private String addressname;
	private String postcode;
	private String tel;
	private String email;
	private String fax;
	private String linkman;
	private String website;
	private String brand_cn;
	private String brand_en;
	private String brand_img;
	private String storagepath;
	private String revenue_id;
	private String custom_id;
	private String custom_timeout;
	private String health_id;
	private String health_timeout;
	private String memo;
	private String status;
	private String recommend;
	private String culture;
	private String serviceinfo;
	private String outstanding;
	private String glory;
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishdate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "zrxzqh",referencedColumnName="zrxzqh_id")
	private Tzrxzqh zrxzqh;
	private String workunit;
	private String ly;
	private String zlfzr;
	private String zlfzrlxfs;
	private String oneself;
	
	private String creditlevel;//信用等级

	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}

	public String getCode_cn() {
		return code_cn;
	}

	public void setCode_cn(String code_cn) {
		this.code_cn = code_cn;
	}

	public String getCode_en() {
		return code_en;
	}

	public void setCode_en(String code_en) {
		this.code_en = code_en;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public String getAddressname() {
		return addressname;
	}

	public void setAddressname(String addressname) {
		this.addressname = addressname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getBrand_cn() {
		return brand_cn;
	}

	public void setBrand_cn(String brand_cn) {
		this.brand_cn = brand_cn;
	}

	public String getBrand_en() {
		return brand_en;
	}

	public void setBrand_en(String brand_en) {
		this.brand_en = brand_en;
	}

	public String getBrand_img() {
		return brand_img;
	}

	public void setBrand_img(String brand_img) {
		this.brand_img = brand_img;
	}

	public String getStoragepath() {
		return storagepath;
	}

	public void setStoragepath(String storagepath) {
		this.storagepath = storagepath;
	}

	public String getRevenue_id() {
		return revenue_id;
	}

	public void setRevenue_id(String revenue_id) {
		this.revenue_id = revenue_id;
	}

	public String getCustom_id() {
		return custom_id;
	}

	public void setCustom_id(String custom_id) {
		this.custom_id = custom_id;
	}

	public String getCustom_timeout() {
		return custom_timeout;
	}

	public void setCustom_timeout(String custom_timeout) {
		this.custom_timeout = custom_timeout;
	}

	public String getHealth_id() {
		return health_id;
	}

	public void setHealth_id(String health_id) {
		this.health_id = health_id;
	}

	public String getHealth_timeout() {
		return health_timeout;
	}

	public void setHealth_timeout(String health_timeout) {
		this.health_timeout = health_timeout;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getServiceinfo() {
		return serviceinfo;
	}

	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}

	public String getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(String outstanding) {
		this.outstanding = outstanding;
	}

	public String getGlory() {
		return glory;
	}

	public void setGlory(String glory) {
		this.glory = glory;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	public Tzrxzqh getZrxzqh() {
		return zrxzqh;
	}

	public void setZrxzqh(Tzrxzqh zrxzqh) {
		this.zrxzqh = zrxzqh;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getZlfzr() {
		return zlfzr;
	}

	public void setZlfzr(String zlfzr) {
		this.zlfzr = zlfzr;
	}

	public String getZlfzrlxfs() {
		return zlfzrlxfs;
	}

	public void setZlfzrlxfs(String zlfzrlxfs) {
		this.zlfzrlxfs = zlfzrlxfs;
	}

	public String getOneself() {
		return oneself;
	}

	public void setOneself(String oneself) {
		this.oneself = oneself;
	}

	public String getCreditlevel() {
		return creditlevel;
	}

	public void setCreditlevel(String creditlevel) {
		this.creditlevel = creditlevel;
	}
	
}
