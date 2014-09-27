package light.mvc.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Productinfo;
import light.mvc.service.sys.ProductinfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productinfo")
public class ProductinfoController extends BaseController {

	@Autowired
	private ProductinfoServiceI productinfoService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/productinfo";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Productinfo productinfo, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(productinfoService.dataGrid(productinfo, ph));
		grid.setTotal(productinfoService.count(productinfo, ph));
		return grid;
	}
	
	@RequestMapping("/statistic")
	public String statistics(){
		return "/admin/productinfostatistic";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree() {
		return productinfoService.tree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/productinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Productinfo productinfo) {
		Json j = new Json();
		try {
			productinfoService.add(productinfo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String pid) {
		Json j = new Json();
		try {
			productinfoService.delete(Long.parseLong(pid));
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Productinfo get(String pid)  {
		return productinfoService.get(Long.parseLong(pid));
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String pid) {
		Productinfo r = productinfoService.get(Long.parseLong(pid));
		request.setAttribute("productinfo", r);
		return "/admin/productinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Productinfo productinfo) {
		Json j = new Json();
		try {
			productinfoService.edit(productinfo);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
