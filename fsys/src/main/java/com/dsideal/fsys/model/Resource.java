package com.dsideal.fsys.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.bean.Tree;
import com.jfinal.plugin.activerecord.Model;

/**
 * 资源管理
 * @author feilm220
 *
 */
public class Resource extends Model<Resource>{

	private static final long serialVersionUID = 1L;
	private static final Logger log_ = LoggerFactory.getLogger(Resource.class);
	
	public static final Resource dao = new Resource();

	public List<Resource> getByResourceType(String resourceType){
		String sql = "select * from sys_resource where resourceType = ? order by sequence asc";
		return dao.find(sql, resourceType);
	}
	
	/**
	 * 根据pid和资源类型查询
	 */
	public List<Resource> getByPidAndResourceType(String pid, String resourceType){
		String sql = "select * from sys_resource where pid = ? and type = ? and status = 0 order by sequence asc";
		List<Resource> list = dao.find(sql, pid, resourceType);
		log_.debug("根据pid{}和资源类型{}查询资源, 返回List<Resource>, {}", pid, resourceType, list);
		return list;
	}
	
	/**
	 * 根据pid和资源类型查询, 并封装成easyui的tree返回
	 */
	public List<Tree> getTreeByPidAndResourceType(String pid, String resourceType){
		String sql = "select * from sys_resource where pid = ? and type = ? and status = 0 order by sequence asc";
		List<Resource> rlist = dao.find(sql, pid, resourceType);
		List<Tree> tlist = new ArrayList<Tree>();
		for(Resource resource : rlist){
			Tree tree = new Tree();
			tree.setId(resource.getStr("id"));
			tree.setText(resource.getStr("name"));
			if(resource.getBoolean("hasleaf")){
				tree.setState("closed");
			}
			tree.setPid(resource.getStr("pid"));
			tree.setIconCls(resource.getStr("icon_class"));
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url", resource.getStr("url"));
			tree.setAttributes(attr);
			tlist.add(tree);
		}
		log_.debug("根据pid{}和资源类型{}查询资源, 返回List<Tree>, {}", pid, resourceType, tlist);
		return tlist;
	}
}
