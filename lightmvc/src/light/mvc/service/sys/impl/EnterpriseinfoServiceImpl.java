package light.mvc.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.Tenterpriseinfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Enterpriseinfo;
import light.mvc.service.sys.EnterpriseinfoServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseinfoServiceImpl implements EnterpriseinfoServiceI {

	@Autowired
	private BaseDaoI<Tenterpriseinfo> enterpriseinfoDao;

	@Override
	public void add(Enterpriseinfo r) {
		Tenterpriseinfo t = new Tenterpriseinfo();
		BeanUtils.copyProperties(r, t);
		enterpriseinfoDao.save(t);
	}

	@Override
	public void delete(String code_id) {
		Tenterpriseinfo t = enterpriseinfoDao.get(Tenterpriseinfo.class, code_id);
		enterpriseinfoDao.delete(t);
	}

	@Override
	public void edit(Enterpriseinfo r) {
		Tenterpriseinfo t = enterpriseinfoDao.get(Tenterpriseinfo.class, r.getId());
		BeanUtils.copyProperties(r, t);
		enterpriseinfoDao.update(t);
	}

	@Override
	public Enterpriseinfo get(String code_id) {
		Tenterpriseinfo t = enterpriseinfoDao.get(Tenterpriseinfo.class, code_id);
		Enterpriseinfo r = new Enterpriseinfo();
		BeanUtils.copyProperties(t, r);
		r.setZrxzqh_id(t.getZrxzqh().getZrxzqh_id());
		r.setZrxzqh_name(t.getZrxzqh().getZrxzqh_name());
		return r;
	}

	@Override
	public List<Enterpriseinfo> dataGrid(Enterpriseinfo enterpriseinfo, PageFilter ph) {
		List<Enterpriseinfo> ul = new ArrayList<Enterpriseinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tenterpriseinfo t ";
		List<Tenterpriseinfo> l = enterpriseinfoDao.find(hql + whereHql(enterpriseinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tenterpriseinfo t : l) {
			Enterpriseinfo u = new Enterpriseinfo();
			BeanUtils.copyProperties(t, u);
			u.setZrxzqh_id(t.getZrxzqh().getZrxzqh_id());
			u.setZrxzqh_name(t.getZrxzqh().getZrxzqh_name());
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Enterpriseinfo enterpriseinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tenterpriseinfo t ";
		return enterpriseinfoDao.count("select count(*) " + hql + whereHql(enterpriseinfo, params), params);
	}

	private String whereHql(Enterpriseinfo enterpriseinfo, Map<String, Object> params) {
		String hql = "";
		if (enterpriseinfo != null) {
			hql += " where 1=1 ";
			if (enterpriseinfo.getCode_id() != null && !"".equals(enterpriseinfo.getCode_id())) {
				hql += " and t.code_id like :code_id";
				params.put("code_id", "%%" + enterpriseinfo.getCode_id() + "%%");
			}
			if (enterpriseinfo.getCode_cn() != null && !"".equals(enterpriseinfo.getCode_cn())) {
				hql += " and t.code_cn like :code_cn";
				params.put("code_cn", "%%" + enterpriseinfo.getCode_cn() + "%%");
			}
			if (enterpriseinfo.getCreditlevel() != null && !"ALL".equals(enterpriseinfo.getCreditlevel())) {
				hql += " and t.creditlevel = '" + enterpriseinfo.getCreditlevel() + "'";
			}
			if (enterpriseinfo.getZrxzqh_id() != null && !"".equals(enterpriseinfo.getZrxzqh_id())) {
				hql += " and t.zrxzqh.zrxzqh_id like :zrxzqh_id";
				params.put("zrxzqh_id", "%%" + enterpriseinfo.getZrxzqh_id() + "%%");
			}			
		}
		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public List<Tree> tree() {
		List<Tenterpriseinfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = enterpriseinfoDao.find("select distinct t from Tenterpriseinfo t order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (Tenterpriseinfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getCode_id());
				tree.setText(r.getCode_cn());
				lt.add(tree);
			}
		}
		return lt;
	}

	//按准入行政区划统计
	@Override
	public List<Object[]> statisticByZrxzqh() {
		String sql = "SELECT zrxzqh_id,zrxzqh_name,e_count FROM (SELECT CONCAT(SUBSTRING(e.zrxzqh,1,4),'00') AS zrxzqh,COUNT(*) AS e_count FROM db_enterpriseinfo e GROUP BY SUBSTRING(e.zrxzqh,1,4)) t1,sc_zrxzqh t2 "
				+ "WHERE t1.zrxzqh = t2.zrxzqh_id AND t2.zrxzqh_id IN ('220100','220200','220300','220400','220500','220600','220700','220800') ORDER BY zrxzqh_id";
		List<Object[]> list = enterpriseinfoDao.findBySql(sql );
		return list;
	}

	//按信用等级统计
	@Override
	public List<Object[]> statisticByCreditlevel() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t1.l,IFNULL(t2.e_count,0) FROM (SELECT 'AAA' AS l FROM DUAL");
		sql.append(" UNION ALL");
		sql.append(" SELECT 'AA' AS l FROM DUAL");
		sql.append(" UNION ALL");
		sql.append(" SELECT 'A' AS l FROM DUAL");
		sql.append(" UNION ALL");
		sql.append(" SELECT 'B' AS l FROM DUAL");
		sql.append(" UNION ALL");
		sql.append(" SELECT 'C' AS l FROM DUAL) t1 LEFT JOIN");
		sql.append(" (SELECT e.creditlevel,COUNT(*) AS e_count FROM db_enterpriseinfo e GROUP BY e.creditlevel) t2");
		sql.append(" ON t1.l = t2.creditlevel");
		List<Object[]> list = enterpriseinfoDao.findBySql(sql.toString());
		return list;
	}
	
	
	
}
