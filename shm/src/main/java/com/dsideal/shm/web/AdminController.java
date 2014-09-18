package com.dsideal.shm.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsideal.shm.constant.Constants;
import com.dsideal.shm.domain.User;
import com.dsideal.shm.service.UserService;
import com.dsideal.shm.vo.Json;
import com.dsideal.shm.vo.SessionInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final UserService userService;
	
	@Inject
	public AdminController(final UserService userService){
		this.userService = userService;
	}

	@RequestMapping
	public String index(HttpServletRequest request){
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(Constants.SESSION_INFO);
		if(sessionInfo != null && sessionInfo.getUserId() != null){
			return "/admin/index";
		}
		return "/admin/login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Json login(User user, HttpServletRequest request){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(Constants.SESSION_INFO);
		if(sessionInfo != null && sessionInfo.getUserId() != null){
			json.setSuccess(true);
			json.setMsg("登录成功.");
			return json;
		}
		User loginUser = userService.getByLoginNameAndPassword(user.getLoginName(), user.getPassword());
		if(loginUser != null){
			json.setSuccess(true);
			json.setMsg("登录成功.");
			
			SessionInfo sessionInfo2 = new SessionInfo();
			sessionInfo2.setUserId(String.valueOf(loginUser.getId()));
			sessionInfo2.setLoginName(loginUser.getLoginName());
			sessionInfo2.setRealName(loginUser.getRealName());
			request.getSession().setAttribute(Constants.SESSION_INFO, sessionInfo2);
			return json;
		}
		json.setMsg("用户名或密码错误.");
		return json;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Json loginOut(HttpSession session){
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		j.setMsg("注销成功！");
		return j;
	}
	
}
