package light.mvc.service.sys;

import java.util.List;

import light.mvc.pageModel.sys.Zrxzqh;

public interface ZrxzqhServiceI {

	public List<Zrxzqh> getLevel2();
	
	public List<Zrxzqh> getLevel3(String level2);

	public List<Zrxzqh> getAll();

}
