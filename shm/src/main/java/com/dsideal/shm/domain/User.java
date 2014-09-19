package com.dsideal.shm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dsideal.shm.constant.Constants;

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
	private Integer sex;
	private Integer age;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date createTime = new Date();
	private Integer state = Constants.ENABLED;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>();
	
	public User(){
		super();
	}
	
	public User(String loginName, String password, String realName, Integer sex, Integer age, String email, Integer state){
		this.loginName = loginName;
		this.password = password;
		this.realName = realName;
		this.email = email;
		this.createTime = new Date();
		this.state = state;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d, loginName='%s', realName='%s', sex=%d, age=%d, email='%s', createTime='%s', state=%d]", 
				this.id, this.loginName, this.realName, this.sex, this.age, this.email, this.createTime, this.state);
//		return Objects.toStringHelper(this)
//				.add("id", this.id)
//				.add("loginName", this.loginName)
//				.add("realName", this.realName)
//				.add("sex", this.sex)
//				.add("age", this.age)
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
