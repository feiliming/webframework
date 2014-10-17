package light.mvc.pageModel.sys;

import java.util.Date;
import java.util.List;

public class Productinfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private Long pid;
	private String code_id;
	private String code_name;
	private String product_name;
	private String product_en;
	private String product_class;
	private String product_commonname;
	private String standard_id;
	private String standard_name;
	private String product_type;
	private String product_status;
	private String measureunit;
	private String fact_id;
	private String fact_timeout;
	private String threec_id;
	private String threec_timeout;
	private String qs_id;
	private String qs_timeout;
	private String appscope;
	private String appfeature;
	private String namekey;
	private String product_xh;
	private String appreadme;
	private String memo;
	private String status;
	private String creditlevel;
	private Date finishdate;
	private String twodimension;
	private List<String> pimages;
	public String getTwodimension() {
		return twodimension;
	}
	public void setTwodimension(String twodimension) {
		this.twodimension = twodimension;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getCode_id() {
		return code_id;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCreditlevel() {
		return creditlevel;
	}
	public void setCreditlevel(String creditlevel) {
		this.creditlevel = creditlevel;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_en() {
		return product_en;
	}
	public void setProduct_en(String product_en) {
		this.product_en = product_en;
	}
	public String getProduct_class() {
		return product_class;
	}
	public void setProduct_class(String product_class) {
		this.product_class = product_class;
	}
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
	}
	public String getStandard_id() {
		return standard_id;
	}
	public void setStandard_id(String standard_id) {
		this.standard_id = standard_id;
	}
	public String getStandard_name() {
		return standard_name;
	}
	public void setStandard_name(String standard_name) {
		this.standard_name = standard_name;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public String getMeasureunit() {
		return measureunit;
	}
	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}
	public String getFact_id() {
		return fact_id;
	}
	public void setFact_id(String fact_id) {
		this.fact_id = fact_id;
	}
	public String getFact_timeout() {
		return fact_timeout;
	}
	public void setFact_timeout(String fact_timeout) {
		this.fact_timeout = fact_timeout;
	}
	public String getThreec_id() {
		return threec_id;
	}
	public void setThreec_id(String threec_id) {
		this.threec_id = threec_id;
	}
	public String getThreec_timeout() {
		return threec_timeout;
	}
	public void setThreec_timeout(String threec_timeout) {
		this.threec_timeout = threec_timeout;
	}
	public String getQs_id() {
		return qs_id;
	}
	public void setQs_id(String qs_id) {
		this.qs_id = qs_id;
	}
	public String getQs_timeout() {
		return qs_timeout;
	}
	public void setQs_timeout(String qs_timeout) {
		this.qs_timeout = qs_timeout;
	}
	public String getAppscope() {
		return appscope;
	}
	public void setAppscope(String appscope) {
		this.appscope = appscope;
	}
	public String getAppfeature() {
		return appfeature;
	}
	public void setAppfeature(String appfeature) {
		this.appfeature = appfeature;
	}
	public String getNamekey() {
		return namekey;
	}
	public void setNamekey(String namekey) {
		this.namekey = namekey;
	}
	public String getProduct_xh() {
		return product_xh;
	}
	public void setProduct_xh(String product_xh) {
		this.product_xh = product_xh;
	}
	public String getAppreadme() {
		return appreadme;
	}
	public void setAppreadme(String appreadme) {
		this.appreadme = appreadme;
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
	public Date getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	public List<String> getPimages() {
		return pimages;
	}
	public void setPimages(List<String> pimages) {
		this.pimages = pimages;
	}

}
