package light.mvc.service.sys;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Enterpriseinfo;

public interface EnterpriseinfoServiceI {

	public List<Enterpriseinfo> dataGrid(Enterpriseinfo enterpriseinfo, PageFilter ph);

	public Long count(Enterpriseinfo enterpriseinfo, PageFilter ph);

	public void add(Enterpriseinfo enterpriseinfo);

	public void delete(String code_id);

	public void edit(Enterpriseinfo enterpriseinfo);

	public Enterpriseinfo get(String code_id);

	public List<Tree> tree();
	
	
	
	public List<Object[]> statisticByZrxzqh();
	public List<Object[]> statisticByCreditlevel();
}
