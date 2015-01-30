package com.dsideal.fsys.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装jquery-easyui的datagrid对象
 */
public class DataGrid<T> implements Serializable{

	private static final long serialVersionUID = 7452563918376499642L;
	
	private int total = 0;
	private List<T> rows = new ArrayList<T>();
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
