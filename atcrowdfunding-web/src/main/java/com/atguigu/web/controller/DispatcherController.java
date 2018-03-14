package com.atguigu.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Permission;
import com.atguigu.atcrowdfunding.common.bean.User;
import com.atguigu.atcrowdfunding.common.util.MD5Util;
import com.atguigu.atcrowdfunding.common.util.StringUtil;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"", "/", "/index", "/index/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout( HttpSession session ) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
	@ResponseBody
	@RequestMapping("/checkLogin")
	public Object checkLogin(User user, HttpSession session) {
		AJAXResult result = new AJAXResult();
		
		user.setUserpswd(MD5Util.digest(user.getUserpswd()));
		User dbUser = userService.queryUser4Login(user);
		if ( dbUser == null ) {
			result.setSuccess(false);
		} else {
			result.setSuccess(true);
			// 查询当前登录用户的权限菜单（许可）
			List<Permission> permissions =
				userService.queryPermissionsByUser(dbUser);
			// 整合权限菜单
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			Set<String> userAuthUrlSet = new HashSet<String>();
			for ( Permission permission : permissions ) {
				if ( !StringUtil.isEmpty(permission.getUrl()) ) {
					userAuthUrlSet.add(permission.getUrl());
				}
				permissionMap.put(permission.getId(), permission);
			}
			Permission root = null;
			for ( Permission permission : permissions ) {
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					root = permission;
				} else {
					Permission parent = permissionMap.get(child.getPid());
					parent.getChildren().add(child);
				}
			}
			session.setAttribute("userAuthUrlSet", userAuthUrlSet);
			session.setAttribute("rootPermission", root);
			session.setAttribute("loginUser", dbUser);
		}
		
		return result;
	}
	
	@RequestMapping("/dologin")
	public String dologin( User user, Model model ) {
		// 1) 获取表单中传递的参数
		// 2) 调用服务接口，查询数据
		user.setUserpswd(MD5Util.digest(user.getUserpswd()));
		User dbUser = userService.queryUser4Login(user);
		// 3) 根据返回值判断是否登陆成功
		if ( dbUser == null ) {
			// 4-2) 如果登陆不成功（用户不存在），跳转回到登陆页面，并且提示错误信息
			String errorMsg = "登陆账号或密码不正确，请重新输入";
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:/login";
		} else {
			// 4-1) 如果登陆成功（用户存在），跳转到后台的主页面
			return "main";
		}
		
		
	}
}
