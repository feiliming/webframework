package com.dsideal.fsys.bean;

import java.util.List;

/**
 * easyui tree
 */
public class ETree implements java.io.Serializable{

	private static final long serialVersionUID = -7986194015663981710L;
	
	private int id;
	private String text;
	private String iconCls;
	private String state = "open";
	private boolean checked = false;
	private Object attributes;
	private List<ETree> children;
	private int pid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<ETree> getChildren() {
		return children;
	}
	public void setChildren(List<ETree> children) {
		this.children = children;
	}
	
}
