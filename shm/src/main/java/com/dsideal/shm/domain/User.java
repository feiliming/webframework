package com.dsideal.shm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author feilm220
 *
 */
@Entity(name = "sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = -2060265306846847199L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String loginName;
	
	@Column(nullable = false)
	private String password;
	
	private String realName;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date createTime = new Date();
	private Integer state = 0;
	
	public User(){
		super();
	}
	
	public User(String loginName, String password, String realName, String email, Integer state){
		this.loginName = loginName;
		this.password = password;
		this.realName = realName;
		this.email = email;
		this.createTime = new Date();
		this.state = state;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d, loginName='%s', realName='%s', email='%s', createTime='%s', state=%d]", 
				this.id, this.loginName, this.realName, this.email, this.createTime, this.state);
//		return Objects.toStringHelper(this)
//				.add("id", this.id)
//				.add("loginName", this.loginName)
//				.add("realName", this.realName)
//				.add("email", this.email)
//				.add("createTime", this.createTime)
//				.add("state", this.state)
//				.toString();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
