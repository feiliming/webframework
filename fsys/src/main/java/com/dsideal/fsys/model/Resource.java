package com.dsideal.fsys.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsideal.fsys.bean.ResourceBean;
import com.dsideal.fsys.bean.Tree;
import com.dsideal.fsys.util.GuidUtil;
import com.dsideal.fsys.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
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
				rb.setId(r.getStr("id"));
				rb.setName(r.getStr("name"));
				rb.setUrl(r.getStr("url"));
				rb.setIconCls(r.getStr("icon_class"));
				rb.setSequence(r.getInt("sequence"));
				rb.setDisable(r.getInt("disable"));
				rb.setType(r.getStr("type"));
				rb.setHasleaf(r.getInt("hasleaf"));
				rb.setPid(r.getStr("pid"));
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
			tree.setId(resource.getStr("id"));
			tree.setText(resource.getStr("name"));
			if(resource.getInt("hasleaf") == 1){
				tree.setState("closed");
			}else{
				tree.setState("open");
			}
			tree.setPid(resource.getStr("pid"));
			tree.setIconCls(resource.getStr("icon_class"));
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("url", resource.getStr("url"));
			tree.setAttributes(attr);
			tlist.add(tree);
		}
		return tlist;
	}
	
	public boolean addResource(final Resource resource){
		return Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				resource.set("id", GuidUtil.getUuid());
				String pid = resource.getStr("pid");
				int updateResult = 0;
				if(StringUtil.isNullOrEmpty(pid)){
					resource.set("pid", "-1");
					updateResult = 1;
				}else{
					String sql = "update sys_resource set hasleaf = 1 where id = ?";
					updateResult = Db.update(sql, pid);
				}
				boolean saveResult = resource.save();
				return (updateResult == 1) && saveResult;
			}
		});
	}
	
	public boolean editResource(final Resource resource){
		return Db.tx(new IAtom() {
			
			@Override
			public boolean run() throws SQLException {
				String pid = resource.getStr("pid");
				int updateResult = 0;
				if(StringUtil.isNullOrEmpty(pid)){
					resource.set("pid", "-1");
					updateResult = 1;
				}else{
					String sql = "update sys_resource set hasleaf = 1 where id = ?";
					updateResult = Db.update(sql, pid);
				}
				boolean saveResult = resource.update();
				return (updateResult == 1) && saveResult;
			}
		});
	}
	
	public void deleteResource(String ids){
		String sql = "delete from sys_resource where id in ?";
		Db.update(sql, paras);
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
