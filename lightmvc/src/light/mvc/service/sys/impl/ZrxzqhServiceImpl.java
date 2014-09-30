package light.mvc.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.sys.Tzrxzqh;
import light.mvc.pageModel.sys.Zrxzqh;
import light.mvc.service.sys.ZrxzqhServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZrxzqhServiceImpl implements ZrxzqhServiceI{
	
	@Autowired
	BaseDaoI<Tzrxzqh> dao;

	@Override
	public List<Zrxzqh> getLevel2() {
		List<Tzrxzqh> find = dao.find("from sc_zrxzqh tz where tz.zrxzqh_id in ('220100','220200','220300','220400','220500','220600','220700','220800') order by tz.zrxzqh_id asc");
		List<Zrxzqh> list = new ArrayList<Zrxzqh>();
		for(Tzrxzqh t : find){
			Zrxzqh z = new Zrxzqh();
			BeanUtils.copyProperties(t, z);
			list.add(z);
		}
		return list;
	}

	@Override
	public List<Zrxzqh> getLevel3(String level2) {
		List<Tzrxzqh> find = dao.find("from sc_zrxzqh tz where tz.zrxzqh_id like '" + level2.substring(0, 4) + "%' order by tz.zrxzqh_id asc");
		List<Zrxzqh> list = new ArrayList<Zrxzqh>();
		for(Tzrxzqh t : find){
			Zrxzqh z = new Zrxzqh();
			BeanUtils.copyProperties(t, z);
			list.add(z);
		}
		return list;
	}

	@Override
	public List<Zrxzqh> getAll() {
		List<Tzrxzqh> find = dao.find("from sc_zrxzqh tz order by tz.zrxzqh_id asc");
		List<Zrxzqh> list = new ArrayList<Zrxzqh>();
		for(Tzrxzqh t : find){
			Zrxzqh z = new Zrxzqh();
			BeanUtils.copyProperties(t, z);
			list.add(z);
		}
		return list;
	}

}
