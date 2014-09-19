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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dsideal.shm.constant.Constants;

/**
 * 
 * @author feilm220
 *
 */
@Entity(name = "sys_resource")
public class Resource implements Serializable{

	private static final long serialVersionUID = 3540277651079573414L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name; // 名称
	private String url; // 菜单路径
	private String description; // 描述
	
	@Temporal(TemporalType.DATE)
	private Date createTime; // 创建时间
	private String icon; // 图标
	private Integer sequence; // 排序号
	private Integer type; // 资源类型, 0菜单 1功能
	private Integer state = Constants.ENABLED; // 状态 0启用 1停用
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	private Resource resource; // 父级
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
	private Set<Resource> resources = new HashSet<Resource>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_resource", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	private Set<Role> roles = new HashSet<Role>();
	
	public Resource(){}
	
	public Resource(String name, String url, String descrition, Date createTime, String icon, Integer sequence, Integer type, Resource resource, Integer state){
		this.name = name;
		this.url = url;
		this.description = descrition;
		this.createTime = createTime;
		this.icon = icon;
		this.sequence = sequence;
		this.type = type;
		this.resource = resource;
		this.state = state;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
