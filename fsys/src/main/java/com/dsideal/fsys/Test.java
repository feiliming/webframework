package com.dsideal.fsys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		String json = "{aa:11,bb:22,cc:[{a:1,b:1,c:1},{a:2,b:2,c:2}]}";
		
		JSONObject parseObject = JSON.parseObject(json);
		System.out.println(parseObject.get("aa"));
		System.out.println(parseObject.get("bb"));
		
		m1(parseObject);

		System.out.println(parseObject.toJSONString());
	}
	
	public static void m1(JSONObject parseObject){
		JSONArray list = parseObject.getJSONArray("cc");
		for(Object obj : list){
			JSONObject jsonObj = (JSONObject)obj;
			jsonObj.put("a", "11111111");
		}
		
	}
}
