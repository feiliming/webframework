package com.dsideal.shm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

/**
 * 
 * @author feilm220
 *
 */
@Entity(name = "sys_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 6921984954073107859L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name; // 角色名称
	private Integer sequence; // 排序号
	private Integer isdefault; // 是否默认
	private String description; // 备注
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_resource", joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "resource_id", nullable = false, updatable = false)})
	@OrderBy("id ASC")
	private Set<Resource> resources = new HashSet<Resource>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	private Set<User> users = new HashSet<User>();
	
	public Role(){}
	
	public Role(String name, Integer sequence, Integer isdefault, String description, Set<Resource> resources, Set<User> users){
		this.name = name;
		this.sequence = sequence;
		this.isdefault = isdefault;
		this.description = description;
		this.resources = resources;
		this.users = users;
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
