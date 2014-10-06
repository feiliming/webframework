package light.mvc.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Productinfo;
import light.mvc.pageModel.sys.Zrxzqh;
import light.mvc.service.sys.ProductinfoServiceI;
import light.mvc.service.sys.ZrxzqhServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productinfo")
public class ProductinfoController extends BaseController {

	@Autowired
	private ProductinfoServiceI productinfoService;
	
	@Autowired
	private ZrxzqhServiceI zrxzqhService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/productinfo";
	}
	@RequestMapping("/compare")
	public String compare() {
		return "/admin/productinfoCompare";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(HttpServletRequest request, Productinfo productinfo, PageFilter ph) {
		String dishi = request.getParameter("dishi");
		String quxian = request.getParameter("quxian");
		String region = "";
		if(quxian != null && !"".equals(quxian)){
			region = quxian;
		}else if(dishi != null && !"".equals(dishi)){
			region = dishi;
		}		
		Grid grid = new Grid();
		grid.setRows(productinfoService.dataGrid(region, productinfo, ph));
		grid.setTotal(productinfoService.count(region, productinfo, ph));
		return grid;
	}
	
	@RequestMapping("/statistic")
	public String statistics(){
		return "/admin/productinfostatistic";
	}
	
	@RequestMapping("/getLevel2")
	@ResponseBody
	public List<Zrxzqh> getLevel2(){
		return zrxzqhService.getLevel2();
	}
	@RequestMapping("/getLevel3")
	@ResponseBody
	public List<Zrxzqh> getLevel3(String dishiid){
		return zrxzqhService.getLevel3(dishiid);
	}
	@RequestMapping("/statisticByZrxzqh")
	@ResponseBody
	public Json statisticByZrxzqh(){
		List<Object[]> list = productinfoService.statisticByZrxzqh();
		StringBuilder csb = new StringBuilder();
		StringBuilder dsb = new StringBuilder();
		StringBuilder ssb = new StringBuilder();
		for(Object[] o : list){
			csb.append("<category name='");
			csb.append(o[1]);
			csb.append("'/>");
			dsb.append("<set value='");
			dsb.append(o[2]);
			dsb.append("'/>");
			
			ssb.append("<set name='" + o[1] + "' value='");
			ssb.append(o[2]);
			ssb.append("'/>");
		}
		StringBuilder totalxml = new StringBuilder();
		totalxml.append("<graph caption='产品按准入行政区统计(柱状图)' xAxisName='准入行政区' yAxisName='个数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
		totalxml.append("<categories>");
		totalxml.append(csb);
		totalxml.append("</categories>");
		totalxml.append("<dataset>");
		totalxml.append(dsb);
		totalxml.append("</dataset>");
		totalxml.append("</graph>");
		
		StringBuilder participantPiexml = new StringBuilder();
		participantPiexml.append("<graph  caption='产品按准入行政区统计(饼状图)' baseFontSize='12' showNames='1' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
		participantPiexml.append(ssb);
		participantPiexml.append("</graph>");
		Json j = new Json();
		j.setObj(totalxml.toString());
		j.setMsg(participantPiexml.toString());
		j.setSuccess(true);
		return j;
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
