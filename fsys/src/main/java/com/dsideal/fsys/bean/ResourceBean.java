package com.dsideal.fsys.bean;

import java.io.Serializable;

/**
 * 页面使用TreeGrid组件, 有easyui tree的属性, 还有Resource的属性
 */
public class ResourceBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String url;
	private String iconCls;
	private int sequence;
	private String type;
	private int pid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
