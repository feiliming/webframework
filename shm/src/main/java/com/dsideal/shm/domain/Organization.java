package com.dsideal.shm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author feilm220
 *
 */
@Entity(name = "sys_organization")
public class Organization implements Serializable{

	private static final long serialVersionUID = 2138867348669483665L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String code;
	
	@Temporal(TemporalType.DATE)
	private Date createTime;
	private String address;
	private String icon;
	private Integer sequence;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	private Organization organization;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<Organization> organizations = new HashSet<Organization>();
	
	public Organization(){}
	
	public Organization(String name, String code, Date createTime, String address, String icon, Integer sequence, Organization organization){
		this.name = name;
		this.code = code;
		this.createTime = createTime;
		this.address = address;
		this.icon = icon;
		this.sequence = sequence;
		this.organization = organization;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Set<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}
	
}
