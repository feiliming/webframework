package light.mvc.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Enterpriseinfo;
import light.mvc.pageModel.sys.Productinfo;
import light.mvc.pageModel.sys.Zrxzqh;
import light.mvc.service.sys.EnterpriseinfoServiceI;
import light.mvc.service.sys.ProductinfoServiceI;
import light.mvc.service.sys.ZrxzqhServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/enterpriseinfo")
public class EnterpriseinfoController extends BaseController {

	@Autowired
	private EnterpriseinfoServiceI enterpriseinfoService;
	@Autowired
	private ProductinfoServiceI productinfoService;
	@Autowired
	private ZrxzqhServiceI zrxzqhService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/enterpriseinfo";
	}
	@RequestMapping("/compare")
	public String compare() {
		return "/admin/enterpriseinfoCompare";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(HttpServletRequest request, Enterpriseinfo enterpriseinfo, PageFilter ph) {
		String dishi = request.getParameter("dishi");
		String quxian = request.getParameter("quxian");
		if(quxian != null && !"".equals(quxian)){
			enterpriseinfo.setZrxzqh_id(quxian);
		}else if(dishi != null && !"".equals(dishi)){
			enterpriseinfo.setZrxzqh_id(dishi);
		}
		Grid grid = new Grid();
		grid.setRows(enterpriseinfoService.dataGrid(enterpriseinfo, ph));
		grid.setTotal(enterpriseinfoService.count(enterpriseinfo, ph));
		return grid;
	}
	
	@RequestMapping("/eproductsPage")
	public String eproductsPage(HttpServletRequest request){
		String eid = request.getParameter("eid");
		request.setAttribute("eid", eid);
		return "/admin/enterpriseProducts";
	}
	@RequestMapping("/getByCodeId")
	@ResponseBody
	public List<Productinfo> getByCodeId(HttpServletRequest request){
		String eid = request.getParameter("eid");
		List<Productinfo> list = productinfoService.getByCodeId(eid);
		return list;
	}
	
	@RequestMapping("/statistic")
	public String statistics(){
		return "/admin/enterpriseinfostatistic";
	}
	@RequestMapping("/statistic2")
	public String statistics2(){
		return "/admin/enterpriseinfostatistic2";
	}
	@RequestMapping("/creditten")
	public String creditten(){
		return "/admin/enterpriseinfoCreditten";
	}
	@RequestMapping("/credithistory")
	public String credithistory(){
		return "/admin/enterpriseinfoCredithistory";
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
		List<Object[]> list = enterpriseinfoService.statisticByZrxzqh();
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
		totalxml.append("<graph caption='组织机构按准入行政区统计(柱状图)' xAxisName='准入行政区' yAxisName='个数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
		totalxml.append("<categories>");
		totalxml.append(csb);
		totalxml.append("</categories>");
		totalxml.append("<dataset>");
		totalxml.append(dsb);
		totalxml.append("</dataset>");
		totalxml.append("</graph>");
		
		StringBuilder participantPiexml = new StringBuilder();
		participantPiexml.append("<graph  caption='组织机构按准入行政区统计(饼状图)' baseFontSize='12' showNames='1' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
		participantPiexml.append(ssb);
		participantPiexml.append("</graph>");
		Json j = new Json();
		j.setObj(totalxml.toString());
		j.setMsg(participantPiexml.toString());
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/statisticByCreditlevel")
	@ResponseBody
	public Json statisticByCreditlevel(){
		List<Object[]> list = enterpriseinfoService.statisticByCreditlevel();
		StringBuilder csb = new StringBuilder();
		StringBuilder dsb = new StringBuilder();
		StringBuilder ssb = new StringBuilder();
		for(Object[] o : list){
			csb.append("<category name='");
			csb.append(o[0]);
			csb.append("'/>");
			dsb.append("<set value='");
			dsb.append(o[1]);
			dsb.append("'/>");
			
			ssb.append("<set name='" + o[0] + "' value='");
			ssb.append(o[1]);
			ssb.append("'/>");
		}
		StringBuilder totalxml = new StringBuilder();
		totalxml.append("<graph caption='组织机构按信用等级统计(柱状图)' xAxisName='准入行政区' yAxisName='个数' outCnvBaseFontSize='12' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
		totalxml.append("<categories>");
		totalxml.append(csb);
		totalxml.append("</categories>");
		totalxml.append("<dataset>");
		totalxml.append(dsb);
		totalxml.append("</dataset>");
		totalxml.append("</graph>");
		
		StringBuilder participantPiexml = new StringBuilder();
		participantPiexml.append("<graph  caption='组织机构按信用等级统计(饼状图)' baseFontSize='12' showNames='1' bgColor='ffffff' showBorder='1' borderColor='d7e9f3' decimalPrecision='0' showColumnShadow='1' showAlternateHGridColor='1'>");
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
