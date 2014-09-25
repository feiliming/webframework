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
		return r;
	}

	@Override
	public List<Productinfo> dataGrid(Productinfo productinfo, PageFilter ph) {
		List<Productinfo> ul = new ArrayList<Productinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproductinfo t ";
		List<Tproductinfo> l = productinfoDao.find(hql + whereHql(productinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tproductinfo t : l) {
			Productinfo u = new Productinfo();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Productinfo productinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproductinfo t ";
		return productinfoDao.count("select count(*) " + hql + whereHql(productinfo, params), params);
	}

	private String whereHql(Productinfo productinfo, Map<String, Object> params) {
		String hql = "";
		if (productinfo != null) {
			hql += " where 1=1 ";
			if (productinfo.getCode_id() != null) {
				hql += " and t.code_id like :code_id";
				params.put("code_id", "%%" + productinfo.getCode_id() + "%%");
			}
			if (productinfo.getProduct_name() != null) {
				hql += " and t.product_name like :product_name";
				params.put("product_name", "%%" + productinfo.getProduct_name() + "%%");
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
				tree.setId(r.getCode_id());
				tree.setText(r.getProduct_name());
				lt.add(tree);
			}
		}
		return lt;
	}
}
