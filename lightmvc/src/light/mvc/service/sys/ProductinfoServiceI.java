package light.mvc.service.sys;

import java.util.List;

import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Productinfo;

public interface ProductinfoServiceI {

	public List<Productinfo> dataGrid(String region, Productinfo productinfo, PageFilter ph);

	public Long count(String region, Productinfo productinfo, PageFilter ph);

	public void add(Productinfo productinfo);

	public void delete(Long pid);

	public void edit(Productinfo productinfo);

	public Productinfo get(Long pid);

	public List<Tree> tree();

	
	public List<Object[]> statisticByZrxzqh();
}
