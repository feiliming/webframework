package light.mvc.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Enterpriseinfo;
import light.mvc.service.sys.EnterpriseinfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/enterpriseinfo")
public class EnterpriseinfoController extends BaseController {

	@Autowired
	private EnterpriseinfoServiceI enterpriseinfoService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/enterpriseinfo";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Enterpriseinfo enterpriseinfo, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(enterpriseinfoService.dataGrid(enterpriseinfo, ph));
		grid.setTotal(enterpriseinfoService.count(enterpriseinfo, ph));
		return grid;
	}
	
	@RequestMapping("/statistic")
	public String statistics(){
		return "/admin/enterpriseinfostatistic";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree() {
		return enterpriseinfoService.tree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/enterpriseinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Enterpriseinfo enterpriseinfo) {
		Json j = new Json();
		try {
			enterpriseinfoService.add(enterpriseinfo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String code_id) {
		Json j = new Json();
		try {
			enterpriseinfoService.delete(code_id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Enterpriseinfo get(String code_id)  {
		return enterpriseinfoService.get(code_id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String code_id) {
		Enterpriseinfo r = enterpriseinfoService.get(code_id);
		request.setAttribute("enterpriseinfo", r);
		return "/admin/enterpriseinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Enterpriseinfo enterpriseinfo) {
		Json j = new Json();
		try {
			enterpriseinfoService.edit(enterpriseinfo);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
