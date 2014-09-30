package light.mvc.model.sys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sc_zrxzqh")
public class Tzrxzqh implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	@Id
	private String zrxzqh_id;
	private String zrxzqh_name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getZrxzqh_id() {
		return zrxzqh_id;
	}
	public void setZrxzqh_id(String zrxzqh_id) {
		this.zrxzqh_id = zrxzqh_id;
	}
	public String getZrxzqh_name() {
		return zrxzqh_name;
	}
	public void setZrxzqh_name(String zrxzqh_name) {
		this.zrxzqh_name = zrxzqh_name;
	}
	
}
