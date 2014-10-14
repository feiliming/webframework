package light.mvc.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.Tproductinfo;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Productinfo;
import light.mvc.service.sys.ProductinfoServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductinfoServiceImpl implements ProductinfoServiceI {

	@Autowired
	private BaseDaoI<Tproductinfo> productinfoDao;

	@Override
	public void add(Productinfo r) {
		Tproductinfo t = new Tproductinfo();
		BeanUtils.copyProperties(r, t);
		productinfoDao.save(t);
	}

	@Override
	public void delete(Long pid) {
		Tproductinfo t = productinfoDao.get(Tproductinfo.class, pid);
		productinfoDao.delete(t);
	}

	@Override
	public void edit(Productinfo r) {
		Tproductinfo t = productinfoDao.get(Tproductinfo.class, r.getPid());
		BeanUtils.copyProperties(r, t);
		productinfoDao.update(t);
	}

	@Override
	public Productinfo get(Long pid) {
		Tproductinfo t = productinfoDao.get(Tproductinfo.class, pid);
		Productinfo r = new Productinfo();
		BeanUtils.copyProperties(t, r);
		r.setCode_id(t.getTenterpriseinfo().getCode_id());
		r.setCode_name(t.getTenterpriseinfo().getCode_cn());
		r.setCreditlevel(t.getTenterpriseinfo().getCreditlevel());
		return r;
	}

	@Override
	public List<Productinfo> dataGrid(String region, Productinfo productinfo, PageFilter ph) {
		List<Productinfo> ul = new ArrayList<Productinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproductinfo t ";
		List<Tproductinfo> l = productinfoDao.find(hql + whereHql(region, productinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tproductinfo t : l) {
			Productinfo u = new Productinfo();
			BeanUtils.copyProperties(t, u);
			u.setCode_id(t.getTenterpriseinfo().getCode_id());
			u.setCode_name(t.getTenterpriseinfo().getCode_cn());
			u.setCreditlevel(t.getTenterpriseinfo().getCreditlevel());
			u.setTwodimension("/2d/product/"+t.getPid()+".png");
			ul.add(u);
		}
		return ul;
	}

	@Override
	public List<Productinfo> getByCodeId(String codeid) {
		List<Productinfo> ul = new ArrayList<Productinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproductinfo t ";
		List<Tproductinfo> l = productinfoDao.find(hql + "where t.tenterpriseinfo.code_id = '" + codeid + "'", params, 1, Integer.MAX_VALUE);
		for (Tproductinfo t : l) {
			Productinfo u = new Productinfo();
			BeanUtils.copyProperties(t, u);
			u.setCode_id(t.getTenterpriseinfo().getCode_id());
			u.setCode_name(t.getTenterpriseinfo().getCode_cn());
			u.setCreditlevel(t.getTenterpriseinfo().getCreditlevel());
			u.setTwodimension("/2d/product/"+t.getPid()+".png");
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(String region, Productinfo productinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproductinfo t ";
		return productinfoDao.count("select count(*) " + hql + whereHql(region, productinfo, params), params);
	}

	private String whereHql(String region, Productinfo productinfo, Map<String, Object> params) {
		String hql = "";
		if (productinfo != null) {
			hql += " where 1=1 ";
			if (productinfo.getCode_id() != null && !"".equals(productinfo.getCode_id())) {
				hql += " and t.tenterpriseinfo.code_id like '%"+productinfo.getCode_id()+"%'";
				//params.put("code_id", "%%" + productinfo.getCode_id() + "%%");
			}
			if (productinfo.getProduct_name() != null && !"".equals(productinfo.getProduct_name())) {
				hql += " and t.product_name like :product_name";
				params.put("product_name", "%%" + productinfo.getProduct_name() + "%%");
			}
			if(region != null && !"".equals(region)){
				hql += " and t.tenterpriseinfo.zrxzqh like '%"+region+"%'";
				//params.put("zrxzqh", "%%" + region + "%%");
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
		List<Tproductinfo> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = productinfoDao.find("select distinct t from Tproductinfo t order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (Tproductinfo r : l) {
				Tree tree = new Tree();
				tree.setId(r.getTenterpriseinfo().getCode_id());
				tree.setText(r.getProduct_name());
				lt.add(tree);
			}
		}
		return lt;
	}

	//按准入行政区统计产品
	@Override
	public List<Object[]> statisticByZrxzqh() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT zrxzqh_id,zrxzqh_name,p_count FROM");
		sql.append(" (SELECT CONCAT(SUBSTRING(e.zrxzqh,1,4),'00') AS zrxzqh,SUM(p_count) AS p_count FROM db_enterpriseinfo e,( SELECT code_id,COUNT(*) AS p_count FROM db_productinfo p GROUP BY p.code_id) t1 ");
		sql.append(" WHERE e.code_id = t1.code_id GROUP BY SUBSTRING(e.zrxzqh,1,4) ) tt1,sc_zrxzqh tt2 ");
		sql.append(" WHERE tt1.zrxzqh = tt2.zrxzqh_id AND tt2.zrxzqh_id IN ('220100','220200','220300','220400','220500','220600','220700','220800') ORDER BY zrxzqh_id");
		List<Object[]> list = productinfoDao.findBySql(sql.toString());
		return list;
	}
	
	
	
}
