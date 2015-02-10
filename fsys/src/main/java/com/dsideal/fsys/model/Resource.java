package com.dsideal.fsys.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.bean.ResourceBean;
import com.dsideal.fsys.bean.Tree;
import com.dsideal.fsys.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Model;

/**
 * 资源Model
 */
public class Resource extends Model<Resource>{

	private static final long serialVersionUID = 1L;
	private static final Logger log_ = LoggerFactory.getLogger(Resource.class);
	
	public static final Resource dao = new Resource();
	
	private static final Integer maxId = Db.queryInt("SELECT MAX(id) FROM sys_resource");
	private static final AtomicInteger ai = new AtomicInteger(maxId == null ? 1 : maxId);

	public int getId() {
		return ai.incrementAndGet();
	}
	
	public List<Resource> getResourceList() {
		String sql = "select * from sys_resource order by sequence asc";
		return dao.findByCache("resource", "getResourceList", sql);
	}
	
	public List<Resource> getResourceList(String resource_type, String pid) {
		String sql = "select * from sys_resource where type = ? and pid = ? order by sequence asc";
		return dao.findByCache("resource", "getResourceList_" + resource_type + "_" + pid, sql, resource_type, pid);
	}
	
	/**
	 * 根据条件查询资源, 只返回下一级资源列表
	 * @param pid
	 * @param type 资源类型,字典码,menu or button
	 * @param disable 0启用,1禁用
	 * @return List<Resource>
	 */
	public List<ResourceBean> getResourceList(String pid, String type, String disable){
		String sql = formatSql(pid, type, disable);
		List<ResourceBean> listb = new ArrayList<ResourceBean>();
		List<Resource> list = dao.find(sql);
		if(list != null){
			for(Resource r : list){
				ResourceBean rb = new ResourceBean();
				rb.setId(r.getInt("id"));
				rb.setName(r.getStr("name"));
				rb.setUrl(r.getStr("url"));
				rb.setIconCls(r.getStr("icon_class"));
				rb.setSequence(r.getInt("sequence"));
				rb.setDisable(r.getInt("disable"));
				rb.setType(r.getStr("type"));
				rb.setPid(r.getInt("pid") == null ? -1 : r.getInt("pid"));
				listb.add(rb);
			}
		}
		return listb;
	}
	
	/**
	 * 根据条件查询资源, 封装成Tree, 返回Tree列表
	 * @param pid
	 * @param type
	 * @param disable
	 * @return List<Tree>
	 */
	public List<Tree> getResourceTreeList(String pid, String type, String disable){
		String sql = formatSql(pid, type, disable);
		List<Resource> rlist = dao.find(sql);
		List<Tree> tlist = new ArrayList<Tree>();
		for(Resource resource : rlist){
			Tree tree = new Tree();
			tree.setId(resource.getInt("id"));
			tree.setText(resource.getStr("name"));
			
			String sql2 = "select id from sys_resource where pid = " + resource.getInt("id") + "";
			List<String> ids = Db.query(sql2);
			if(ids.size() > 0){
				tree.setState("closed");
			}else{
				tree.setState("open");
			}
			
			tree.setPid(resource.getInt("pid"));
			tree.setIconCls(resource.getStr("icon_class"));
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url", resource.getStr("url"));
			tree.setAttributes(attr);
			tlist.add(tree);
		}
		return tlist;
	}
	
	public boolean addResource(final Resource resource){
		resource.set("id", getId());
		if(resource.getInt("pid") == null)
			resource.set("pid", -1);
		return resource.save();
	}
	
	public boolean editResource(final Resource resource){
		if(resource.getInt("pid") == null){
			resource.set("pid", "-1");
		}
		return resource.update();
	}
	
	public boolean deleteResource(final List<String> ids){
		return Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				List<String> deleteids = new ArrayList<String>();
				getDeleteIds(ids, deleteids);
				boolean flag = true;
				for(String id : deleteids){
					boolean result = Db.deleteById("sys_resource", id);
					if(!result){
						flag = false;
						break;
					}
				}
				return flag;
			}
		});
	}
	//递归查询所有要删除的ids
	public void getDeleteIds(List<String> ids, List<String> deleteids){
		for(String id : ids){
			deleteids.add(id);
			String sql2 = "select id from sys_resource where pid = '" + id + "'";
			List<String> ids2 = Db.query(sql2);
			getDeleteIds(ids2, deleteids);
		}
	}
	
	private String formatSql(String pid, String type, String disable){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from sys_resource");
		if(!StringUtil.isNullOrEmpty(pid)){
			if(sql.indexOf("where") == -1){
				sql.append(" where pid = '" + pid + "'");
			}else{
				sql.append(" and pid = '" + pid + "'");
			}
		}
		if(!StringUtil.isNullOrEmpty(type)){
			if(sql.indexOf("where") == -1){
				sql.append(" where type = '" + type + "'");
			}else{
				sql.append(" and type = '" + type + "'");
			}
		}
		if(!StringUtil.isNullOrEmpty(disable)){
			if(sql.indexOf("where") == -1){
				sql.append(" where disable = " + disable);
			}else{
				sql.append(" and disable = " + disable);
			}
		}
		sql.append(" order by sequence asc");
		return sql.toString();
	}
	
}
