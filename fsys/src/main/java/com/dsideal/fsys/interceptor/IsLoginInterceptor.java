package com.dsideal.fsys.interceptor;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * 
 * @author feilm220
 *
 */
public class IsLoginInterceptor implements Interceptor{
	
	//不需要过滤的路径
	private List<String> nointerceptorList;
	public IsLoginInterceptor() {
		nointerceptorList = new ArrayList<String>();
		nointerceptorList.add("/");
		nointerceptorList.add("/login");
		nointerceptorList.add("/logout");
	}

	@Override
	public void intercept(ActionInvocation ai) {
		if(nointerceptorList.contains(ai.getActionKey())){
			ai.invoke();
		}else{
			Controller controller = ai.getController();
			String userId = controller.getCookie("userId");
			if(userId == null || "".equals(userId)){
				controller.render("login.html");
			}else{
				ai.invoke();
			}
		}
	}

}
