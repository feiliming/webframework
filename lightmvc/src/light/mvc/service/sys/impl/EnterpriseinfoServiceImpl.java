package light.mvc.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.Tenterpriseinfo;
import light.mvc.model.sys.Tresource;
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
		return r;
	}

	@Override
	public List<Enterpriseinfo> dataGrid(Enterpriseinfo Enterpriseinfo, PageFilter ph) {
		List<Enterpriseinfo> ul = new ArrayList<Enterpriseinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tenterpriseinfo t ";
		List<Tenterpriseinfo> l = enterpriseinfoDao.find(hql + whereHql(Enterpriseinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tenterpriseinfo t : l) {
			Enterpriseinfo u = new Enterpriseinfo();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Enterpriseinfo Enterpriseinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tenterpriseinfo t ";
		return enterpriseinfoDao.count("select count(*) " + hql + whereHql(Enterpriseinfo, params), params);
	}

	private String whereHql(Enterpriseinfo Enterpriseinfo, Map<String, Object> params) {
		String hql = "";
		if (Enterpriseinfo != null) {
			hql += " where 1=1 ";
			if (Enterpriseinfo.getCode_cn() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + Enterpriseinfo.getCode_cn() + "%%");
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
}
