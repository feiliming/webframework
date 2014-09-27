package com.dsideal.shm.vo;

import java.util.List;

public class SessionInfo {

	private String userId;// 用户ID
	private String loginName;// 登录名
	private String realName;// 姓名
	private String ip;// 用户IP
	private String roleId;

	private List<String> resourceList;// 用户可以访问的资源地址列表
	
	private List<String> resourceAllList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public List<String> getResourceAllList() {
		return resourceAllList;
	}

	public void setResourceAllList(List<String> resourceAllList) {
		this.resourceAllList = resourceAllList;
	}

}
