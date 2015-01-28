package com.dsideal.fsys;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		String json = "{aa:11,bb:22,cc:[{a:1,b:1,c:1},{a:2,b:2,c:2}]}";
		
		final JSONObject parseObject = JSON.parseObject(json);
		System.out.println(parseObject.get("aa"));
		System.out.println(parseObject.get("bb"));
		
		m1(parseObject);

		System.out.println(parseObject.toJSONString());
		
		long i = Long.parseLong("2014110509175500933");
		System.out.println(i);
		long k = 2014110509175500933l;
		
		int c1 = 3;
		int b1 = 10*10;
		int m = (int)((float)b1/c1);
		System.out.println(m);
		
		//类名可以用作标识符
		String String = "String"; 
		Object Object = null; 
		Integer Integer = new Integer(1); 
		//让代码难以理解怎么样？ 
		Float Double = 1.0f; 
		Double Float = 2.0d; 
		if (String instanceof String) {
		      if (Float instanceof Double) {
		          if (Double instanceof Float) {
		                System.out.print("Can anyone read this code???");
		            }
		      }
		 }
	}
	
	public static void m1(JSONObject parseObject){
		JSONArray list = parseObject.getJSONArray("cc");
		for(Object obj : list){
			JSONObject jsonObj = (JSONObject)obj;
			jsonObj.put("a", "11111111");
		}
		
	}
	
	public static void m2(Map<String, Object> map){
		System.out.println(map.get("str"));
	}
}
